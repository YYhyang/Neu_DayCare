package edu.neu.csye6200.web;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.neu.csye6200.entity.Student;
import edu.neu.csye6200.entity.Teacher;
import edu.neu.csye6200.entity.dto.TeacherDO;
import edu.neu.csye6200.manager.EnrollmentManager;
import edu.neu.csye6200.utils.CsvUtils;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;

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
    studentService.addStudent(studentDO);
    return Result.buildOkData(studentDO);
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

  @GetMapping(value = "/queryByGroup/{groupId}")
  @LogOperate(value = "根据分组查询学生")
  public Result<Object> queryByGroupId(@PathVariable int groupId) {
    List<StudentVO> studentVOS = studentService.queryByGroupId(groupId);
    return Result.buildOkData(studentVOS);
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

  @PostMapping("/checkStatus")
  public Result<Object> checkStatus(@RequestParam Date registrationDate){
    List<StudentDO> studentDOS=studentService.checkStatus(registrationDate);
    List<StudentVO> studentVOS=new Vector<>();
    ConverterUtils.convertList(studentDOS,studentVOS,StudentVO.class);
    return Result.buildOkData(studentVOS);
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

  @GetMapping("/csvAdd")
  public Result csvAdd(){
    String filePath="I:\\360MoveData\\Users\\Administrator\\Desktop\\daycare\\daycare\\";
    String stuFileName="daycare_student.csv";
    List<StudentVO> list = CsvUtils.buildObjects(filePath + stuFileName, StudentVO.class);
    List<StudentDO>studentDOS= new Vector<>();
    ConverterUtils.convertList(list,studentDOS,StudentDO.class);
    for(StudentDO studentDO:studentDOS) {
      studentService.addStudent(studentDO);
    }
    return Result.buildOkData(studentDOS) ;
  }


//  @PostMapping(value = "")
//  @LogOperate(value = "增改")
//  public Result<Object> update2(@RequestBody StudentVO vo) {
//    StudentDO student = studentService.getOne(Wrappers.<StudentDO>query().eq("studentId", vo.getStudentId()));
//    if (student == null) {
//      student = new StudentDO();
//    }
//    ConverterUtils.convert(vo, student);
//    boolean b = studentService.saveOrUpdate(student);
//    return b ? Result.buildOkData(student) : Result.buildFail();
//  }
//
//  @GetMapping("/csvAdd")
//  public Result csvAdd(){
//    String filePath="I:\\360MoveData\\Users\\Administrator\\Desktop\\daycare\\daycare\\daycare_student.csv";
//  }
  
}
