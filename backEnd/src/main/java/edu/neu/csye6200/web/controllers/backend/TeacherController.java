package edu.neu.csye6200.web.controllers.backend;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import edu.neu.csye6200.service.teacher.TeacherService;
import edu.neu.csye6200.web.controllers.DayCareBaseController;
import edu.neu.csye6200.web.controllers.DayCareResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Yuhan Yang
 * @CreatTime 2020/8/11 12:15
 */

@RestController
@RequestMapping("teacher")
@Slf4j
public class TeacherController extends DayCareBaseController {
    @Resource
    private TeacherService teacherService;
    @RequestMapping("insertTeacher")
    public String insertTeacher(@RequestBody String jsonString, HttpServletRequest request){
        DayCareResult<Object> res= protectController(request,()->{
            DayCareResult<Object> result=new DayCareResult<>();
            JSONObject jsonObject= JSON.parseObject(jsonString);



            return result;
                }
                );
        return JSON.toJSONString(res);
    }
}
