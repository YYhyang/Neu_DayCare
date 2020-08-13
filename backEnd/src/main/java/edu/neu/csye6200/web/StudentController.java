package edu.neu.csye6200.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import edu.neu.csye6200.base.convertor.StudentConverter;
import edu.neu.csye6200.base.enums.DayCareResultCodeEnum;
import edu.neu.csye6200.entity.dto.StudentDO;
import edu.neu.csye6200.utils.ConverterUtils;
import edu.neu.csye6200.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import edu.neu.csye6200.base.BaseController;
import edu.neu.csye6200.base.Result;
import edu.neu.csye6200.entity.vo.StudentVO;
import edu.neu.csye6200.service.StudentService;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

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

  @RequestMapping(value = "/add",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
  public Result<Object> add(@RequestBody StudentVO studentVO, HttpServletRequest request) {
    return protectController(request, () -> {
      Result<Object> result = new Result<>();
      StudentDO studentDO = new StudentDO();
//      studentDO = StudentConverter.vo2Do(studentVO);

      ConverterUtils.convert(studentVO, studentDO);
      studentDO.setAgeState(StudentConverter.getAgeState(DateUtils.calculateAge(studentVO.getBirthday())));
      studentDO.setRegistrationDate(new Date());

      boolean insertOpereate = studentService.save(studentDO);
      result.setResultCode(insertOpereate?DayCareResultCodeEnum.SUCCESS.getCode():DayCareResultCodeEnum.ERROR.getCode());
      return result;
    }, BaseControllerEnum.IGNORE_VERIFY.getCode());
  }

  @RequestMapping(value="/listByAgeState",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
  public Result<Object> queryStudentByAgeState(@RequestParam int ageState, HttpServletRequest request) {
    return protectController(request, () -> {
      Result<Object> result = new Result<>();
       List<StudentVO> studentVOList = studentService.queryByAgeState(ageState);
       result.setResultObj(studentVOList);
      return result;
    }, BaseControllerEnum.IGNORE_VERIFY.getCode());
  }

  @RequestMapping(value="/detail",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
  public Result<Object> detail(@RequestParam int studentId, HttpServletRequest request) {
    return protectController(request, () -> {
      Result<Object> result = new Result<>();
      StudentVO studentVO = studentService.selectOneById(studentId);
      result.setResultObj(studentVO);
      return result;
    }, BaseControllerEnum.IGNORE_VERIFY.getCode());
  }

  @PostMapping("/update")
  public Result update(StudentVO studentVO) {
    //todo
    return null;
  }

}
