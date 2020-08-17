package edu.neu.csye6200.web;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;

import edu.neu.csye6200.base.BaseController;
import edu.neu.csye6200.base.Result;
import edu.neu.csye6200.base.annotation.LogOperate;
import edu.neu.csye6200.base.convertor.StudentConverter;
import edu.neu.csye6200.entity.dto.StudentDO;
import edu.neu.csye6200.entity.vo.StudentVO;
import edu.neu.csye6200.service.StudentService;
import edu.neu.csye6200.utils.ConverterUtils;
import edu.neu.csye6200.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author Caspar
 * @CreateTime 2020/8/10 19:31
 * @Description:
 */
@RestController
@RequestMapping("/v1/students")
@Slf4j
public class StudentController extends BaseController {

  @Resource
  private StudentService studentService;

  @PostMapping(value = "")
  @LogOperate(value = "增")
  public Result<Object> add(@RequestBody StudentVO studentVO) {
    StudentDO studentDO = new StudentDO();
    ConverterUtils.convert(studentVO, studentDO);
    // todo 计算 ageState
    studentDO.setAgeState(StudentConverter.getAgeState(DateUtils.calculateAge(studentVO.getBirthday())));
    studentDO.setRegistrationDate(new Date());
    boolean insert = studentService.save(studentDO);
    if (insert) {
      return Result.buildOkData(studentDO);
    }
    return Result.buildFailData(studentDO);
  }

  @GetMapping(value = "/state/{ageState}")
  @LogOperate(value = "根据年龄段查询")
  public Result<Object> queryStudentByAgeState(@PathVariable String ageState) {
    List<StudentVO> studentVOList = studentService.queryByAgeState(ageState);
    return Result.buildOkData(studentVOList);
  }

  @GetMapping(value = "/pageNo/{pageNo}/pageSize/{pageSize}")
  @LogOperate(value = "查询分页学生")
  public Result<Object> queryStudentByPage(@PathVariable int pageNo, @PathVariable int pageSize) {
    IPage<StudentDO> iPage = studentService.queryByPage(pageNo, pageSize);
    IPage<StudentVO> iPageStudentVO = iPage.convert((ele)->{
      StudentVO studentVO = new StudentVO();
      return (StudentVO)ConverterUtils.convertAndReturn(ele, studentVO);
    });
    return Result.buildOkData(iPageStudentVO);
  }

  @GetMapping(value = "/id/{id}")
  @LogOperate(value = "获取详情")
  public Result<Object> detail(@PathVariable int id) {
    StudentVO studentVO = studentService.selectOneById(id);
    return Result.buildOkData(studentVO);
  }

  @PostMapping("/update")
  @LogOperate(value = "更新")
  public Result<Object> update(@RequestBody StudentVO studentVO) {
    // todo
    StudentDO studentDO = new StudentDO();
    ConverterUtils.convert(studentVO, studentDO);
    boolean update = studentService.updateById(studentDO);
    if (update) {
      return Result.buildOkData(studentDO);
    }
    return Result.buildFailData(studentDO);
  }

}
