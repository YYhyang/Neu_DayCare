package edu.neu.csye6200.web;

import edu.neu.csye6200.base.BaseController;
import edu.neu.csye6200.base.Result;
import edu.neu.csye6200.base.annotation.LogOperate;
import edu.neu.csye6200.entity.dto.ImmunizationDO;
import edu.neu.csye6200.entity.vo.ImmunizationVO;
import edu.neu.csye6200.service.ImmunizationService;
import edu.neu.csye6200.utils.ConverterUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Caspar
 * @date 2020/8/14 13:33
 */

@RestController
@RequestMapping("/v1/immunization")
@Slf4j
public class ImmunizationController extends BaseController {
    @Resource
    private ImmunizationService immunizationService;

    @PostMapping(value = "")
    @LogOperate(value = "添加immunization")
    public Result<Object> add(@RequestBody ImmunizationVO immunizationVO, HttpServletRequest request){
        ImmunizationDO immunizationDO = new ImmunizationDO();
        ConverterUtils.convert(immunizationVO, immunizationDO);
        boolean insert = immunizationService.save(immunizationDO);
        if(insert){
            return Result.buildOkData(immunizationDO);
        }
        return  Result.buildFailData(immunizationDO);
    }

    @GetMapping(value = "/id/{id}")
    @LogOperate(value = "获取immunization详情")
    public Result<Object> detail(@PathVariable int id) {
        ImmunizationVO immunizationVO = immunizationService.selectOneById(id);
        return Result.buildOkData(immunizationVO);
    }

    @PostMapping("/update")
    @LogOperate(value = "更新")
    public Result<Object> update(@RequestBody ImmunizationVO immunizationVO, HttpServletRequest request) {
        // todo
        ImmunizationDO immunizationDO = new ImmunizationDO();
        ConverterUtils.convert(immunizationVO, immunizationDO);
        boolean update = immunizationService.updateById(immunizationDO);
        if (update) {
            return Result.buildOkData(immunizationDO);
        }
        return Result.buildFailData(immunizationDO);
    }

    @PostMapping("/delete/{id}")
    @LogOperate(value = "删除")
    public Result<Object> delete(@PathVariable int id){
        Boolean success = immunizationService.removeById(id);
        if(success){
            return Result.buildOkData(id);
        }
        return Result.buildFailData(id);

    }
}
