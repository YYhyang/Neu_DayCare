package edu.neu.csye6200.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.neu.csye6200.base.BaseController;
import edu.neu.csye6200.base.Result;
import edu.neu.csye6200.service.GroupService;
import lombok.extern.slf4j.Slf4j;

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
    return Result.buildOkData(groupService.selectGroupVOByGroupId(groupId));
  }

}
