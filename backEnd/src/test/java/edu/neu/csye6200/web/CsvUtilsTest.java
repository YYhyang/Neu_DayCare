package edu.neu.csye6200.web;

import static edu.neu.csye6200.utils.CsvUtils.buildObjects;
import static edu.neu.csye6200.utils.CsvUtils.writeObjects;

import java.io.File;
import java.util.*;

import edu.neu.csye6200.base.Result;
import edu.neu.csye6200.entity.Teacher;
import edu.neu.csye6200.entity.dto.StudentDO;
import edu.neu.csye6200.entity.dto.TeacherDO;
import edu.neu.csye6200.entity.vo.StudentVO;
import edu.neu.csye6200.service.StudentService;
import edu.neu.csye6200.service.TeacherService;
import edu.neu.csye6200.utils.ConverterUtils;
import edu.neu.csye6200.utils.CsvUtils;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import edu.neu.csye6200.entity.Group;
import edu.neu.csye6200.entity.Student;

import javax.annotation.Resource;

/**
 * @author arronshentu
 */
public class CsvUtilsTest {
  String filePath = "/Users/caspar/Desktop/csv/";
  String stuFileName = "daycare_student.csv";
  String groupFileName = "daycare_group.csv";
  String stuFileOutName = "out.csv";
  String ruleFileName = "rules.csv";
  String packagePath = "edu.neu.csye6200.entity.dto.";
  String csvData = "6,AGE_STATE_0,2,\"{\"1\":12,\"2\":48}\",description,VERICELLA;"
          + "6,AGE_STATE_0,2,\"{\"1\":12,\"2\":48}\",description,VERICELLA";

  private static final String SEPARATOR = System.getProperty("line.separator");

  @Resource
  StudentController studentController;
  @Resource
  TeacherController teacherController;
  @Resource
  StudentService studentService;
  @Resource
  TeacherService teacherService;

  @Test
  public void test() {
    List<int[]> jsonIndex = getCurlyBracketIndex(csvData);
    System.out.println(JSON.toJSONString(jsonIndex));
    List<String> datas = new ArrayList<>();
    for (int[] array : jsonIndex) {
      int start = array[0];
      int end = array[1];
      String left = org.apache.commons.lang3.StringUtils.left(csvData, start - 2);
      String mid = org.apache.commons.lang3.StringUtils.substring(csvData, start, end + 1);
      String right = org.apache.commons.lang3.StringUtils.substring(csvData, end + 3, csvData.length());
      System.out.println(left);
      System.out.println(mid);
      System.out.println(right);
    }
    System.out.println(JSON.toJSONString(datas));
  }

  private List<int[]> getCurlyBracketIndex(String csvData) {
    String leftCurlyBracket = "{";
    String rightCurlyBracket = "}";
    List<int[]> jsonIndex = new ArrayList<>(2);
    if (csvData.contains(leftCurlyBracket)) {
      int start = csvData.indexOf(leftCurlyBracket);
      int end = csvData.lastIndexOf(rightCurlyBracket);
      int nextEnd = start;
      for (int i = start; i <= end; i++) {
        // 取出下一个{}
        nextEnd = csvData.indexOf(rightCurlyBracket, nextEnd + 1);
        jsonIndex.add(new int[]{i, nextEnd});
        if (nextEnd == end) {
          break;
        }
        i = csvData.indexOf(leftCurlyBracket, nextEnd + 1);
      }
    }
    return jsonIndex;
  }

  @Test
  public void directroy() {
    List<String> fileNames = getFileNames();
    if (fileNames == null)
      return;
    System.out.println(fileNames.size());
  }

  private List<String> getFileNames() {
    List<String> fileNames = null;
    File path = new File(filePath);
    if (path.isDirectory()) {
      fileNames = new ArrayList<>(Arrays.asList(Objects.requireNonNull(path.list())));
    }
    return fileNames;
  }

  @Test
  public void allFile() throws ClassNotFoundException {
    for (String fileName : getFileNames()) {
      if (fileName.contains("mmunization")) {
        buildObjects(filePath + fileName, Class.forName(packagePath + formatCsvName(fileName)));
      }
    }
  }

  public String formatCsvName(String fileName) {
    int start = fileName.indexOf("_") + 1;
    int end = fileName.indexOf(".");
    return StringUtils.upperFirst(fileName.substring(start, end) + "DO");
  }

  @Before
  public void init() {
    System.out.println(filePath);
  }

  @Test
  public void buildObjectsTest() {
    buildObjects(filePath + groupFileName, Group.class);
  }

  @Test
  public void writeTest() {
    writeObjects(filePath + stuFileOutName, buildObjects(filePath + stuFileName, Student.class));
  }


}
