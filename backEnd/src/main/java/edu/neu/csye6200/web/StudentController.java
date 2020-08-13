package edu.neu.csye6200.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import edu.neu.csye6200.base.BaseController;
import edu.neu.csye6200.base.Result;
import edu.neu.csye6200.entity.Student;
import edu.neu.csye6200.entity.vo.StudentVO;
import edu.neu.csye6200.service.StudentService;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author Caspar
 * @CreateTime 2020/8/10 19:31
 * @Description:
 */
@RestController
@RequestMapping("student")
@Slf4j
public class StudentController extends BaseController {

  @Resource
  private StudentService studentService;

  @PostMapping("insertStudent")
  public String insertDemand(@RequestBody String jsonString, HttpServletRequest request) {
    Result<Object> res = protectController(request, () -> {
      JSONObject jsonObject = JSON.parseObject(jsonString);
      StudentVO studentVO = jsonObject.getObject("student", StudentVO.class);
      Student student = new Student();
      BeanUtils.copyProperties(studentVO, student);
      boolean studentId = studentService.save(student);
      return Result.buildData("studentId" + studentId);
    }, BaseControllerEnum.IGNORE_VERIFY.getCode());

    return JSON.toJSONString(res);
  }

  @RequestMapping("queryStudentByAgeState")
  public Result<Object> queryStudentByAgeState(@RequestBody String jsonString, HttpServletRequest request) {
    return protectController(request, () -> {
      Result<Object> result = new Result<>();
      JSONObject jsonObject = JSON.parseObject(jsonString);
      int ageState = jsonObject.getObject("ageState", Integer.class);
      // List<StudentVO> studentVOS = studentService.queryByAgeState(ageState); // todo
      // result.setResultObj(studentVOS);
      return result;
    }, BaseControllerEnum.IGNORE_VERIFY.getCode());
  }

}
