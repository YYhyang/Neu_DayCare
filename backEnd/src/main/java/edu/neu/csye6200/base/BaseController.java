package edu.neu.csye6200.base;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author Caspar
 * @CreateTime 2020/8/10 19:19
 * @Description:
 */
@Slf4j
public class BaseController {

  /**
   * 鉴权类型枚举
   */
  protected enum BaseControllerEnum {
    /** 无需鉴权 */
    IGNORE_VERIFY("IGNORE_VERIFY", "无需鉴权"),
    /** 管理人员权限 */
    MANAGEMENT_OPERATION("MANAGEMENT_OPERATION", "管理人员权限"),
    /** 后台管理入口 */
    BACK_MANAGE("BACK_MANAGE", "后台管理入口"),;

    /** 枚举编码 */
    private String code;

    /** 枚举描述 */
    private String description;

    BaseControllerEnum(String code, String description) {
      this.code = code;
      this.description = description;
    }

    public String getCode() {
      return code;
    }
  }

}
