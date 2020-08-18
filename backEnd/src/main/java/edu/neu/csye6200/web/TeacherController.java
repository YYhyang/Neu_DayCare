package edu.neu.csye6200.web;

import java.util.List;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.neu.csye6200.entity.Teacher;
import edu.neu.csye6200.utils.ConverterUtils;
import edu.neu.csye6200.utils.CsvUtils;
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
@RequestMapping("/teacher")
@Slf4j
public class TeacherController extends BaseController {
  @Resource
  private TeacherService teacherService;

  @PostMapping(value = "/add")
  public Result<Object> add(@RequestBody TeacherVO teacherVO) {
    TeacherDO teacherDO = TeacherConverter.Vo2Do(teacherVO);
    boolean insert = teacherService.save(teacherDO);
    if (insert) {
      return Result.buildOkData(teacherDO);
    }
    return Result.buildFailData(teacherDO);
  }

  @PostMapping(value = "/listByTargetAge")
  public Result<Object> listByTargetAge(@RequestParam String targetAge) {
    List<TeacherVO> teacherVOs = teacherService.listByTargetAge(targetAge);
    return Result.buildOkData(teacherVOs);
  }

  @GetMapping(value = "/detail")
  public Result<Object> selectById(@RequestParam Integer teacherId) {
    TeacherVO teacherVO = teacherService.selectById(teacherId);
    return Result.buildOkData(teacherVO);
  }

  @PostMapping(value = "/page")
  public Result<Object> pageAllTeacher(@RequestParam Integer pageNumber,@RequestParam Integer pageSize){
    IPage<TeacherDO> teacherDOS=teacherService.pageAllTeacher(pageNumber,pageSize);
    IPage<TeacherVO> teacherVOIPage=teacherDOS.convert((ele)->{
      TeacherVO teacherVO=new TeacherVO();
      return (TeacherVO) ConverterUtils.convertAndReturn(ele,teacherVO);
            }
            );
    return Result.buildOkData(teacherVOIPage);
  }

  @PostMapping(value = "/update")
  public Result<Object> updateTeacher(@RequestBody TeacherVO teacherVO){
    TeacherDO teacherDO=new TeacherDO();
    ConverterUtils.convert(teacherVO,teacherDO);
    boolean update=teacherService.updateById(teacherDO);
    if(update){
      return Result.buildOkData(teacherDO);
    }
    else {
      return Result.buildFailData(teacherDO);
    }
  }


  @GetMapping("/csvAdd")
  public Result csvAdd(){
    String filePath="I:\\360MoveData\\Users\\Administrator\\Desktop\\daycare\\daycare\\";
    String stuFileName="daycare_teacher.csv";
    List<Teacher> list = CsvUtils.buildObjects(filePath + stuFileName, Teacher.class);
    for(Teacher teacher:list) {
      TeacherDO teacherDO=new TeacherDO();
      ConverterUtils.convert(teacher,teacherDO);
      teacherService.save(teacherDO);
    }
    return Result.buildOkData(list) ;
  }
}
