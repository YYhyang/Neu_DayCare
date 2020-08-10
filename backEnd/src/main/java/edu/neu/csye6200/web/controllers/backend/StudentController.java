package edu.neu.csye6200.web.controllers.backend;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.JSONObject;
import edu.neu.csye6200.base.converters.StudentConverter;
import edu.neu.csye6200.base.utils.AssertUtils;

import edu.neu.csye6200.entity.dto.Student;
import edu.neu.csye6200.entity.vo.StudentVO;
import edu.neu.csye6200.service.student.StudentService;
import edu.neu.csye6200.web.controllers.DayCareBaseController;
import edu.neu.csye6200.web.controllers.DayCareResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.List;


/**
 * @Author Caspar
 * @CreateTime 2020/8/10 19:31
 * @Description:
 */
@RestController
@RequestMapping("student")
@Slf4j
public class StudentController extends DayCareBaseController {

    @Resource
    private StudentService studentService;
    @RequestMapping("insertStudent")
    public String insertDemand(@RequestBody String jsonString, HttpServletRequest request) {
        DayCareResult<Object> res = protectController(request, () -> {
            DayCareResult<Object> result = new DayCareResult<>();
            JSONObject jsonObject = JSON.parseObject(jsonString);
            StudentVO studentVO = jsonObject.getObject("student", StudentVO.class);
            AssertUtils.AssertNotNull(studentVO, "student is null");
            Student student = StudentConverter.Vo2model(studentVO);
            int studentId = studentService.insertStudent(student);
            result.setResultObj("studentId"+studentId);
            return result;
        }, BaseControllerEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }

    @RequestMapping("queryStudentByAgeState")
    public String queryStudentByAgeState(@RequestBody String jsonString, HttpServletRequest request) {
        DayCareResult<Object> res = protectController(request, () -> {
            DayCareResult<Object> result = new DayCareResult<>();
            JSONObject jsonObject = JSON.parseObject(jsonString);
            int ageState = jsonObject.getObject("ageState", Integer.class);
            AssertUtils.AssertNotNull(ageState, "ageState is null");
            List<StudentVO> studentVOS = studentService.queryByAgeState(ageState);


            result.setResultObj(studentVOS);
            return result;
        }, BaseControllerEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }


}
