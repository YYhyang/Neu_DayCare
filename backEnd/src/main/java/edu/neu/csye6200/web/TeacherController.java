package edu.neu.csye6200.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.*;

import edu.neu.csye6200.base.BaseController;
import edu.neu.csye6200.base.Result;
import edu.neu.csye6200.base.convertor.TeacherConverter;
import edu.neu.csye6200.base.enums.DayCareResultCodeEnum;
import edu.neu.csye6200.entity.dto.TeacherDO;
import edu.neu.csye6200.entity.vo.TeacherVO;
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

  @PostMapping(value = "/add")
  public Result<Object> add(@RequestBody TeacherVO teacherVO, HttpServletRequest request) {
    return protectController(request, () -> {
      Result<Object> result = new Result<>();
      TeacherDO teacherDO = TeacherConverter.Vo2Do(teacherVO);
      boolean insert = teacherService.save(teacherDO);
      result.setResultCode(insert ? DayCareResultCodeEnum.SUCCESS.getCode() : DayCareResultCodeEnum.ERROR.getCode());
      return result;
    }, BaseControllerEnum.IGNORE_VERIFY.getCode());
  }

  @PostMapping(value = "/listByTargetAge")
  public Result<Object> listByTargetAge(@RequestParam Integer targetAge, HttpServletRequest request) {
    return protectController(request, () -> {
      Result<Object> result = new Result<>();
      List<TeacherVO> teacherVOs = teacherService.listByTargetAge(targetAge);
      result.setResultObj(teacherVOs);
      return result;
    }, BaseControllerEnum.IGNORE_VERIFY.getCode());
  }

  @GetMapping(value = "/detail")
  public Result<Object> selectById(@RequestParam Integer teacherId, HttpServletRequest request) {
    return protectController(request, () -> {
      TeacherVO teacherVO = teacherService.selectById(teacherId);
      return Result.buildOkData(teacherVO);
    }, BaseControllerEnum.IGNORE_VERIFY.getCode());
  }

}
