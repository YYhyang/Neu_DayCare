package edu.neu.csye6200.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import edu.neu.csye6200.base.BaseController;
import edu.neu.csye6200.base.Result;
import edu.neu.csye6200.base.annotation.LogOperate;
import edu.neu.csye6200.entity.dto.StudentDO;
import edu.neu.csye6200.entity.vo.StudentVO;
import edu.neu.csye6200.service.StudentService;
import edu.neu.csye6200.utils.ConverterUtils;
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

  @GetMapping(value = "/state/{ageState}")
  @LogOperate(value = "根据年龄段查询")
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

  @DeleteMapping("/{id}")
  @LogOperate(value = "删")
  public Result<Object> remove(@PathVariable String id) {
    return Result.buildOkData(studentService.removeById(id));
  }

  @GetMapping("/list")
  @LogOperate(value = "列")
  public Result<Object> list() {
    return Result.buildOkData(studentService.list());
  }

  @GetMapping("/{id}")
  @LogOperate(value = "查")
  public Result<Object> get(@PathVariable String id) {
    return Result.buildOkData(studentService.selectOneById(Integer.parseInt(id)));
  }

  @PostMapping(value = "")
  @LogOperate(value = "增改")
  public Result<Object> update2(@RequestBody StudentVO vo) {
    StudentDO student = studentService.getOne(Wrappers.<StudentDO>query().eq("studentId", vo.getStudentId()));
    if (student == null) {
      student = new StudentDO();
    }
    BeanUtils.copyProperties(vo, student);
    boolean b = studentService.saveOrUpdate(student);
    return b ? Result.buildOkData(student) : Result.buildFail();
  }

}
