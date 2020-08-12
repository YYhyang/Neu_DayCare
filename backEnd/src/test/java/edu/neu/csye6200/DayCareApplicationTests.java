package edu.neu.csye6200;

import java.util.List;

import javax.annotation.Resource;

import edu.neu.csye6200.entity.dto.StudentDO;
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

  @Test
  public void mapperTest() {
    List<StudentDO> studentDOS = studentMapper.selectList(Wrappers.emptyWrapper());
    studentDOS.forEach(ele -> System.out.println(ele.getName()));
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
    List<StudentDO> studentDOS = studentService.list(Wrappers.<StudentDO>query().eq("studentId", 1));
    if (CollectionUtils.isNotEmpty(studentDOS)) {
      studentDOS.forEach(x->System.out.println(x.getName()));
    }
  }

}
