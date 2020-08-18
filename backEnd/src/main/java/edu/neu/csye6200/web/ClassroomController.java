package edu.neu.csye6200.web;

import edu.neu.csye6200.base.BaseController;
import edu.neu.csye6200.base.Result;
import edu.neu.csye6200.base.annotation.LogOperate;
import edu.neu.csye6200.entity.Classroom;
import edu.neu.csye6200.entity.dto.ClassroomDO;
import edu.neu.csye6200.entity.dto.GroupDO;
import edu.neu.csye6200.entity.vo.ClassroomVO;
import edu.neu.csye6200.entity.vo.GroupVO;
import edu.neu.csye6200.service.ClassroomService;
import edu.neu.csye6200.utils.ConverterUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Caspar
 * @date 2020/8/18 17:52
 */
@RestController
@RequestMapping("/v1/classroom")
@Slf4j
public class ClassroomController extends BaseController {

    @Resource
    ClassroomService classroomService;


    @GetMapping(value = "/{id}")
    @LogOperate(value = "获取classroom详情")
    public Result<Object> detail(@PathVariable int id) {
        ClassroomDO classroomDO = classroomService.getById(id);
        ClassroomVO classroomVO = new ClassroomVO();
        ConverterUtils.convert(classroomDO, classroomVO);
        return Result.buildOkData(classroomVO);
    }

    @GetMapping(value = "/lists")
    @LogOperate(value = "获取教室内所有的group详情")
    public Result<Object> lists() {
        List<ClassroomDO> classroomDOS = classroomService.list();
        List<ClassroomVO>  classrooms = new ArrayList<>();
        ConverterUtils.convertList(classroomDOS, classrooms, ClassroomVO.class);
        return Result.buildOkData(classrooms);
    }


}
