package edu.neu.csye6200.web;

import edu.neu.csye6200.base.BaseController;
import edu.neu.csye6200.base.Result;
import edu.neu.csye6200.entity.Group;
import edu.neu.csye6200.entity.Student;
import edu.neu.csye6200.entity.vo.StudentVO;
import edu.neu.csye6200.service.GroupService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Caspar
 * @date 2020/8/12 15:49
 */

@RestController
@RequestMapping("/v1/test")
@Slf4j
public class TestController extends BaseController {
    @Resource
    GroupService groupService;

    @GetMapping("/testGroup")
    public Result testGroup(@Param("groupId") Integer groupId, HttpServletRequest request) {
        return protectController(request, () -> {
            Result<Object> result = new Result<>();
            result.setResultObj(groupService.selectGroupVOByGroupId(groupId));
            return result;
        }, BaseControllerEnum.IGNORE_VERIFY.getCode());
    }


}
