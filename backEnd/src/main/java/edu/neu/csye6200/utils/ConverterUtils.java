package edu.neu.csye6200.utils;

import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Caspar
 * @date 2020/8/13 12:17
 */
public class ConverterUtils {
    /**
     * Cast an Object source with first Class to another Object target with anther Class
     * @param source T
     * @param target T
     * @param <T> Class
     *
     */
    public static <T> void convert(T source, T target){

        if (source==null) {
            return;
        }

        Method[] arrSourceMethods =  source.getClass().getMethods();
        Method[] arrTargetMethods = target.getClass().getMethods();
        Field[] arrSourceFields = source.getClass().getDeclaredFields();

        List<String> sourceMethodName = Arrays.stream(arrSourceMethods).map(ele -> ele.getName().toLowerCase())
                .collect(Collectors.toList());
        List<String> targetMethodNames = Arrays.stream(arrTargetMethods).map(ele -> ele.getName().toLowerCase())
                .collect(Collectors.toList());
        List<String> fields = Arrays.stream(arrSourceFields).map(ele -> ele.getName().toLowerCase())
                .collect(Collectors.toList());

        try {
            for(int i=0;i<fields.size();i++) {

                StringBuffer setterSb = new StringBuffer("set").append(fields.get(i));
                StringBuffer getterSb = new StringBuffer("get").append(fields.get(i));

                int indexGetter = sourceMethodName.indexOf(getterSb.toString());
                Object value = null;

                if (indexGetter!=-1) {
                    Method getter = arrSourceMethods[indexGetter];
                    value = getter.invoke(source);
                    if (value != null) {
                        int indexSetter = targetMethodNames.indexOf(setterSb.toString());
                        if (indexSetter!=-1) {
                            Method setter = arrTargetMethods[indexSetter];
                            invoke(setter, value, target);
                        }
                    }
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public static <T> void convert2(T source, T target){

        if (source==null) {
            return;
        }

        Method[] arrSourceMethods =  source.getClass().getMethods();
        Method[] arrTargetMethods = target.getClass().getMethods();
        Field[] arrSourceFields = source.getClass().getDeclaredFields();

        List<String> sourceMethodName = Arrays.stream(arrSourceMethods).map(ele -> ele.getName().toLowerCase())
                .collect(Collectors.toList());
        List<String> targetMethodNames = Arrays.stream(arrTargetMethods).map(ele -> ele.getName().toLowerCase())
                .collect(Collectors.toList());
        List<String> fields = Arrays.stream(arrSourceFields).map(ele -> ele.getName().toLowerCase())
                .collect(Collectors.toList());

        try {
            for(int i=0;i<fields.size();i++) {
                StringBuffer setterSb = new StringBuffer("set").append(fields.get(i));
                StringBuffer getterSb = new StringBuffer("get").append(fields.get(i));

                int indexGetter = sourceMethodName.indexOf(getterSb.toString());
                Object value = null;

                if (indexGetter!=-1) {
                    Method getter = arrSourceMethods[indexGetter];
                    value = getter.invoke(source);
                    if (value != null) {
                        int indexSetter = targetMethodNames.indexOf(setterSb.toString());
                        if (indexSetter!=-1) {
                            Method setter = arrTargetMethods[indexSetter];
                            invoke(setter, value, target);
                        }
                    }
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public static <T> void convertList(T[] sourceArr, T[] targetArr) {
        if (sourceArr==null||sourceArr.length==0) {
            return;
        }

        try {

            for (T item:targetArr) {
                item = (T) targetArr.getClass().getComponentType().newInstance();
            }

            Method[] arrSourceMethods =  sourceArr[0].getClass().getMethods();
            Method[] arrTargetMethods = targetArr.getClass().getComponentType().getMethods();
            Field[] arrSourceFields =  targetArr.getClass().getComponentType().getDeclaredFields();

            List<String> sourceMethodName = Arrays.stream(arrSourceMethods).map(ele -> ele.getName().toLowerCase())
                    .collect(Collectors.toList());
            List<String> targetMethodNames = Arrays.stream(arrTargetMethods).map(ele -> ele.getName().toLowerCase())
                    .collect(Collectors.toList());
            List<String> fields = Arrays.stream(arrSourceFields).map(ele -> ele.getName().toLowerCase())
                    .collect(Collectors.toList());


            for (int j=0;j<sourceArr.length;j++) {

                T instance = (T) targetArr.getClass().getComponentType().newInstance();
                for(int i=0;i<fields.size();i++) {
                    StringBuffer setterSb = new StringBuffer("set").append(fields.get(i));
                    StringBuffer getterSb = new StringBuffer("get").append(fields.get(i));

                    int indexGetter = sourceMethodName.indexOf(getterSb.toString());
                    Object value = null;
                    if (indexGetter!=-1) {
                        Method getter = arrSourceMethods[indexGetter];
                        value = getter.invoke(sourceArr[j]);
                        if (value != null) {
                            int indexSetter = targetMethodNames.indexOf(setterSb.toString());
                            if (indexSetter!=-1) {
                                Method setter = arrTargetMethods[indexSetter];
                                invoke(setter, value, instance);
                                targetArr[j] = instance;
                            }
                        }
                    }
                }
            }

        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }




    }
    public static void invoke(Method setter, Object value, Object instance)
            throws IllegalAccessException, InvocationTargetException {
        if (instance == null || value == null || setter == null) {
            return;
        }
        if (setter.getParameterCount() != 1) {
            return;
        }
        if (!(value instanceof String) && !(value instanceof Integer) && !(value instanceof Double)
                && !(value instanceof Date) && !(value instanceof Boolean) ) {
            return;
        }

        String type = setter.getParameters()[0].getType().getTypeName();
        switch (type) {
            case "java.lang.String":
                value = value.toString();
                break;
            case "int":
                value = Integer.parseInt(String.valueOf(value));
                break;
            case "double":
                value = Double.parseDouble(String.valueOf(value));
                break;
            case "boolean":
                value = Boolean.parseBoolean(String.valueOf(value));
                break;
            case "java.util.Date":
//                value = DateUtils.string2Date(value.toString(), false);
                value = (Date) value;
                break;
            default:
                break;
        }
        setter.invoke(instance, value);
    }

    /**
     * Cast an List containing Objects of first Class to another List containing Objects with anther Class
     * @param sourceList source list
     * @param targetList target list
     * @param clzTarget target Class
     * @param <T>
     * @return List<T>
     */
    public static <T> void convertList(List<? extends T> sourceList, List targetList, Class clzTarget) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return;
        }
        try {

            Method[] arrSourceMethods =  sourceList.get(0).getClass().getMethods();
            Method[] arrTargetMethods = clzTarget.getMethods();
            Field[] arrSourceFields = sourceList.get(0).getClass().getDeclaredFields();

            List<String> sourceMethodName = Arrays.stream(arrSourceMethods).map(ele -> ele.getName().toLowerCase())
                    .collect(Collectors.toList());
            List<String> targetMethodNames = Arrays.stream(arrTargetMethods).map(ele -> ele.getName().toLowerCase())
                    .collect(Collectors.toList());
            List<String> fields = Arrays.stream(arrSourceFields).map(ele -> ele.getName().toLowerCase())
                    .collect(Collectors.toList());

            for (int j=0;j<sourceList.size();j++) {

                T instance = (T) clzTarget.newInstance();
                targetList.add(instance);
                for(int i=0;i<fields.size();i++) {
                    StringBuffer setterSb = new StringBuffer("set").append(fields.get(i));
                    StringBuffer getterSb = new StringBuffer("get").append(fields.get(i));

                    int indexGetter = sourceMethodName.indexOf(getterSb.toString());
                    Object value = null;
                    if (indexGetter!=-1) {
                        Method getter = arrSourceMethods[indexGetter];
                        value = getter.invoke(sourceList.get(j));
                        if (value != null) {
                            int indexSetter = targetMethodNames.indexOf(setterSb.toString());
                            if (indexSetter!=-1) {
                                Method setter = arrTargetMethods[indexSetter];
                                invoke(setter, value, instance);
                            }
                        }
                    }
                }


            }
            targetList.stream().map(ele->clzTarget.cast(ele));
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static <T> List convertListAndReturn(List<? extends T> sourceList, Class clzTarget) {
        List targetList = new ArrayList();
        convertList(sourceList, targetList, clzTarget);
        return targetList;
    }

    public static <T> T convertAndReturn(T source, T target) {
        List targetList = new ArrayList();
        convert(source, target);
        return target;
    }

}
