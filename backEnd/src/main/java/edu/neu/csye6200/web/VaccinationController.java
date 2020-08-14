package edu.neu.csye6200.web;

import edu.neu.csye6200.base.BaseController;
import edu.neu.csye6200.service.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Caspar
 * @date 2020/8/14 13:36
 */
public class VaccinationController extends BaseController {

    @Autowired
    VaccinationService vaccinationService;

    /**
     * 1. 查询特定学生的疫苗记录list
     *
     * 2. 查询特定学生特定疫苗记录
     *
     * 3. 为学生新增一条疫苗记录
     *
     * 4. 为学生修改一条疫苗记录
     *
     * 5. 检查学生的疫苗状态,计算预计需要注射疫苗的时间，service层实现
     *
     * 6. 完善 DateUtils工具，用于计算日期
     *
     * PS. 在Controller层只调用Service层接口，业务逻辑在Service层调用
     */



}
