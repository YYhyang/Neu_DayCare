package edu.neu.csye6200.utils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

/**
 * @author arronshentu
 */
public class CsvUtils {
  private static final int HEAD_ROWS = 2;
  private static final String COMMA = ",";
  private static final String LEFT_CURLY_BRACKET = "{";
  private static final String RIGHT_CURLY_BRACKET = "}";
  private static final String SEPARATOR = System.getProperty("line.separator");

  private static void invoke(Method setter, Object value, Object instance)
    throws IllegalAccessException, InvocationTargetException {
    if (instance == null || value == null || setter == null) {
      return;
    }
    if (setter.getParameterCount() != 1) {
      return;
    }
    if (!(value instanceof String) && !(value instanceof Integer)) {
      return;
    }

    String type = setter.getParameters()[0].getType().getTypeName();
    switch (type) {
      case "java.lang.String":
        value = value.toString();
        break;
      case "java.lang.Integer":
        value = Integer.parseInt(String.valueOf(value));
        break;
      case "java.lang.Double":
        value = Double.parseDouble(String.valueOf(value));
        break;
      case "java.util.Date":
        value = DateUtils.string2Date(String.valueOf(value), true);
        break;
      default:
        break;
    }
    setter.invoke(instance, value);
  }

  private static List<int[]> getCurlyBracketIndex(String csvData) {
    List<int[]> jsonIndex = new ArrayList<>(2);
    if (csvData.contains(LEFT_CURLY_BRACKET)) {
      int start = csvData.indexOf(LEFT_CURLY_BRACKET);
      int end = csvData.lastIndexOf(RIGHT_CURLY_BRACKET);
      int nextEnd = start;
      for (int i = start; i <= end; i++) {
        // 取出下一个{}
        nextEnd = csvData.indexOf(RIGHT_CURLY_BRACKET, nextEnd + 1);
        jsonIndex.add(new int[] {i, nextEnd});
        if (nextEnd == end) {
          break;
        }
        i = csvData.indexOf(LEFT_CURLY_BRACKET, nextEnd + 1);
      }
    }
    return jsonIndex;
  }

  private static String[] splitContentWithJson(String csvData, String[] fieldValues) {
    List<int[]> jsonIndex = getCurlyBracketIndex(csvData);
    List<String> datas = new ArrayList<>();
    jsonIndex.forEach(array -> {
      int start = array[0];
      int end = array[1];
      String left = org.apache.commons.lang3.StringUtils.left(csvData, start - 2);
      String mid = org.apache.commons.lang3.StringUtils.substring(csvData, start, end + 1);
      String right = org.apache.commons.lang3.StringUtils.substring(csvData, end + 3, csvData.length());
      datas.add(left);
      datas.add(mid);
      datas.add(right);
    });
    return datas.toArray(fieldValues);
  }

  public static <T> List<T> buildObjects(String filePath, Class<T> clz) {
    File file = new File(filePath);
    if (file.exists() && file.canRead()) {
      try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        List<String> datas = reader.lines().collect(Collectors.toList());
        if (datas.size() < HEAD_ROWS) {
          return Collections.emptyList();
        }
        String[] headers = datas.get(0).split(COMMA);
        Arrays.stream(headers).map(header -> header.toLowerCase().trim()).collect(Collectors.toList()).toArray(headers);
        Method[] methods = clz.getMethods();
        List<String> methodNames =
          Arrays.stream(methods).map(ele -> ele.getName().toLowerCase()).collect(Collectors.toList());

        List<T> dataObject = new ArrayList<>();
        // Field - Index的映射
        // 1. 在csv中 先获取到header 语义同field
        // 2. 如果fields中有name对应的header
        // 3. 拿到Field类进行set
        // 对每一行csv数据进行处理
        for (int row = 1, datasSize = datas.size(); row < datasSize; row++) {
          String data = datas.get(row);
          String[] fieldValues;
          fieldValues = data.split(COMMA);
          if (data.contains(LEFT_CURLY_BRACKET)) {
            fieldValues = splitContentWithJson(data, fieldValues);
          }
          try {
            T instance = clz.newInstance();
            // i 代表某一列
            // 通过i定位对应的header 再定位field
            for (int i = 0; i < fieldValues.length; i++) {
              String header = headers[i];
              if (methodNames.contains("set" + header)) {
                int indexOfMethod = methodNames.indexOf("set" + header);
                Method method = methods[indexOfMethod];
                invoke(method, fieldValues[i], instance);
              }
            }
            System.out.println(instance);
            dataObject.add(instance);
          } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
          }
        }
        if (!dataObject.isEmpty()) {
          dataObject.forEach(System.out::println);
        }
        return dataObject;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    System.out.println("文件不存在");
    return Collections.emptyList();
  }

  public static <T> void writeObjects(String filePath, List<T> list) {
    if (CollectionUtils.isEmpty(list)) {
      return;
    }
    File file = new File(filePath);
    if (!file.exists()) {
      try {
        boolean newFile = file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    if (file.canWrite()) {
      try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));) {
        bufferedWriter.write(buildCsvString(list));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public static <T> String buildCsvString(List<T> lists) {
    if (CollectionUtils.isEmpty(lists)) {
      return "";
    }
    T instance = lists.get(0);
    Class<?> clz = instance.getClass();
    Method[] methods = clz.getMethods();
    List<Method> list = new ArrayList<>(Arrays.asList(methods));
    List<Method> getters =
      list.stream().filter(method -> method.getName().startsWith("get")).collect(Collectors.toList());
    getters.removeIf(method -> "java.lang.Class".equals(method.getReturnType().getName()));
    getters.sort(Comparator.comparing(Method::getName));
    StringBuilder stringBuilder = new StringBuilder();
    for (Method getter : getters) {
      // 跳过有参数的get
      if (getter.getParameterCount() > 0) {
        continue;
      }
      if (StringUtils.isBlank(getFieldName(getter))) {
        return "";
      }
      stringBuilder.append(getFieldName(getter)).append(COMMA);
    }
    replaceCommaWithLineSeparator(stringBuilder);

    lists.forEach(innerInstance -> {
      for (Method getter : getters) {
        Object field = null;
        try {
          field = getter.invoke(innerInstance);
        } catch (IllegalAccessException | InvocationTargetException e) {
          e.printStackTrace();
        }
        stringBuilder.append(field).append(COMMA);
      }
      replaceCommaWithLineSeparator(stringBuilder);
    });
    return stringBuilder.toString();
  }

  private static void replaceCommaWithLineSeparator(StringBuilder stringBuilder) {
    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
    stringBuilder.append(SEPARATOR);
  }

  private static String getFieldName(Method method) {
    String fieldName = method.getName().substring("get".length());
    char[] chars = fieldName.toCharArray();
    if (chars.length < 1) {
      return "";
    }
    char alpha = chars[0];
    fieldName = String.valueOf(alpha).toLowerCase() + fieldName.substring(1);
    return fieldName;
  }
}
