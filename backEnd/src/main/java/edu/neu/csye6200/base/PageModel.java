package edu.neu.csye6200.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author arronshentu
 */
public class PageModel<T> extends Page<T> {

  public static final String F_DESC = "desc";
  public static final String F_ASC = "asc";
  /**
   * 查询条件json
   */
  private String queryConditionJson;

}
