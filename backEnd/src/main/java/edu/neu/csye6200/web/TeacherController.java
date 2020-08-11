package edu.neu.csye6200.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import edu.neu.csye6200.base.BaseController;
import edu.neu.csye6200.base.Result;
import edu.neu.csye6200.service.TeacherService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Yuhan Yang
 * @CreatTime 2020/8/11 12:15
 */

@RestController
@RequestMapping("teacher")
@Slf4j
public class TeacherController extends BaseController {
  @Resource
  private TeacherService teacherService;

  @RequestMapping("insertTeacher")
  public String insertTeacher(@RequestBody String jsonString, HttpServletRequest request) {
    Result<Object> res = protectController(request, () -> {
      Result<Object> result = new Result<>();
      JSONObject jsonObject = JSON.parseObject(jsonString);
      return result;
    });
    return JSON.toJSONString(res);
  }
}
