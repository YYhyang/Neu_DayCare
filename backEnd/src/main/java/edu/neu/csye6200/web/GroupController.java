package edu.neu.csye6200.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import edu.neu.csye6200.base.BaseController;
import edu.neu.csye6200.base.Result;
import edu.neu.csye6200.base.annotation.LogOperate;
import edu.neu.csye6200.entity.dto.StudentDO;
import edu.neu.csye6200.entity.vo.GroupVO;
import edu.neu.csye6200.service.GroupService;
import edu.neu.csye6200.service.StudentService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Caspar
 * @date 2020/8/18 16:41
 */

@RestController
@RequestMapping("/v1/groups")
@Slf4j
public class GroupController extends BaseController {

  @Resource
  GroupService groupService;

  @GetMapping(value = "/{id}")
  @LogOperate(value = "获取group详情")
  public Result<Object> detail(@PathVariable int id) {
    GroupVO groupVO = groupService.selectGroupVOByGroupId(id);
    return Result.buildOkData(groupVO);
  }

  @Resource
  StudentService studentService;

  @GetMapping(value = "/list/{id}")
  public Result<List<StudentDO>> list(@PathVariable int id) {
    List<StudentDO> groupId = studentService.list(Wrappers.<StudentDO>query().eq("groupId", id));
    return Result.buildOkData(groupId);
  }

}
