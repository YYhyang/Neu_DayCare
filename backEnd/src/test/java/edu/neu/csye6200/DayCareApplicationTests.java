package edu.neu.csye6200;

import java.util.List;

import javax.annotation.Resource;

import edu.neu.csye6200.dao.GroupMapper;
import edu.neu.csye6200.entity.Group;
import edu.neu.csye6200.entity.dto.GroupDO;
import edu.neu.csye6200.entity.dto.StudentDO;
import edu.neu.csye6200.utils.ConverterUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import edu.neu.csye6200.dao.StudentMapper;
import edu.neu.csye6200.entity.Student;
import edu.neu.csye6200.service.StudentService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DayCareApplication.class)
public class DayCareApplicationTests {

  @Resource
  StudentMapper studentMapper;
  @Resource
  GroupMapper groupMapper;

  @Test
  public void mapperTest() {
    List<StudentDO> students = studentMapper.selectList(Wrappers.emptyWrapper());
    students.forEach(ele -> System.out.println(ele.toString()));
  }

  @Autowired
  StudentService studentService;

  @Test
  public void serviceTest() {
    int count = studentService.count();
    System.out.println(count);
  }

  @Test
  public void servicePageTest() {
    List<StudentDO> studentId = studentService.list(Wrappers.<StudentDO>query().eq("studentId", 1));
    if (CollectionUtils.isNotEmpty(studentId)) {
      studentId.forEach(System.out::println);
    }
  }

  @Test
  public void converterUtilsTest() {
    GroupDO groupDO = groupMapper.selectById(1);
    Group group = new Group();
    ConverterUtils.convert(groupDO, group);
    System.out.println(group.toString());
  }

}
