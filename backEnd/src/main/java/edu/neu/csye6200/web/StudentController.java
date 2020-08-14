package edu.neu.csye6200.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import edu.neu.csye6200.base.convertor.StudentConverter;
import edu.neu.csye6200.base.enums.DayCareResultCodeEnum;
import edu.neu.csye6200.entity.dto.StudentDO;
import edu.neu.csye6200.utils.ConverterUtils;
import org.springframework.web.bind.annotation.*;
import edu.neu.csye6200.base.BaseController;
import edu.neu.csye6200.base.Result;
import edu.neu.csye6200.base.annotation.LogOperate;
import edu.neu.csye6200.entity.vo.StudentVO;
import edu.neu.csye6200.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;


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
  public Result<Object> add(@RequestBody StudentVO studentVO, HttpServletRequest request) {
    StudentDO studentDO = new StudentDO();
    ConverterUtils.convert(studentVO, studentDO);
    // todo 计算 ageState
//    studentDO.setAgeState(StudentConverter.getAgeState(DateUtils.calculateAge(studentVO.getBirthday())));
    studentDO.setRegistrationDate(new Date());
    boolean insert = studentService.save(studentDO);
    if (insert) {
      return Result.buildOkData(studentDO);
    }
    return Result.buildFailData(studentDO);
  }

  @GetMapping(value = "/state/{ageState}")
  public Result<Object> queryStudentByAgeState(@PathVariable int ageState) {
    List<StudentVO> studentVOList = studentService.queryByAgeState(ageState);
    return Result.buildOkData(studentVOList);
  }

  @GetMapping(value = "/id/{id}")
  @LogOperate(value = "获取详情")
  public Result<Object> detail(@PathVariable int id) {
    StudentVO studentVO = studentService.selectOneById(id);
    return Result.buildOkData(studentVO);
  }

  @PostMapping("/update")
  public Result update(StudentVO studentVO) {
    // todo
    return null;
  }

}
