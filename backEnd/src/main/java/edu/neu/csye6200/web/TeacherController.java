package edu.neu.csye6200.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import edu.neu.csye6200.base.BaseController;
import edu.neu.csye6200.base.Result;
import edu.neu.csye6200.base.convertor.TeacherConverter;
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
  public Result<Object> add(@RequestBody TeacherVO teacherVO) {
    TeacherDO teacherDO = TeacherConverter.Vo2Do(teacherVO);
    boolean insert = teacherService.save(teacherDO);
    if (insert) {
      return Result.buildOkData(null);
    }
    return Result.buildFail();
  }

  @PostMapping(value = "/listByTargetAge")
  public Result<Object> listByTargetAge(@RequestParam Integer targetAge) {
    List<TeacherVO> teacherVOs = teacherService.listByTargetAge(targetAge);
    return Result.buildOkData(teacherVOs);
  }

  @GetMapping(value = "/detail")
  public Result<Object> selectById(@RequestParam Integer teacherId) {
    TeacherVO teacherVO = teacherService.selectById(teacherId);
    return Result.buildOkData(teacherVO);
  }

}
