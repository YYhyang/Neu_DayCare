package edu.neu.csye6200.web;

import edu.neu.csye6200.base.BaseController;
import edu.neu.csye6200.base.Result;
import edu.neu.csye6200.base.annotation.LogOperate;
import edu.neu.csye6200.entity.dto.VaccinationDO;
import edu.neu.csye6200.entity.vo.VaccinationVO;
import edu.neu.csye6200.service.VaccinationService;
import edu.neu.csye6200.utils.ConverterUtils;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Vector;

/**
 * @author Caspar, Yuhan Yang, Yue Fang
 * @date 2020/8/14 13:36
 */
@RestController
@RequestMapping("/vaccination")
@Slf4j
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

    @GetMapping(value = "/listByStudentId/{studentId}")
    @LogOperate(value = "find by student id")
    public Result<Object> listByStudentId(@PathVariable Integer studentId){
        List<VaccinationVO> vaccinationVOS= new Vector<>();
        ConverterUtils.convertList(vaccinationService.getListVaccination(studentId),vaccinationVOS,VaccinationVO.class);
        return Result.buildOkData(vaccinationVOS);
    }

    @GetMapping(value = "/getVaccination/{studentId}/{immunizationName}")
    @LogOperate(value = "find vaccination by student id and immunization name")
    public Result<Object> getVaccination(@PathVariable Integer studentId, @PathVariable String immunizationName) {
        VaccinationVO vaccinationVO = vaccinationService.getVaccination(studentId, immunizationName);
        return Result.buildOkData(vaccinationVO);
    }

    @GetMapping(value = "/add")
    @LogOperate(value = "add new vaccination record")
    public Result<Object> add(@RequestBody VaccinationVO vaccinationVO) {
        VaccinationDO vaccinationDO = new VaccinationDO();
        ConverterUtils.convert(vaccinationVO,vaccinationDO);
        vaccinationService.addVaccination(vaccinationDO.getId());

        boolean insert = vaccinationService.save(vaccinationDO);
        if(insert) {
            return Result.buildOkData(vaccinationDO);
        } else {
            return Result.buildFailData(vaccinationDO);
        }
    }

    @PostMapping("/update")
    @LogOperate(value = "update vaccination record")
    public Result<Object> update(@RequestBody VaccinationVO vaccinationVO) {
        VaccinationDO vaccinationDO = new VaccinationDO();
        ConverterUtils.convert(vaccinationVO,vaccinationDO);
        vaccinationService.updateVaccination(vaccinationDO.getId());

        boolean update = vaccinationService.updateById(vaccinationDO);
        if(update) {
            return Result.buildOkData(vaccinationDO);
        } else {
            return Result.buildFailData(vaccinationDO);
        }
    }

    @PostMapping("/checkStatusAndNextDate/{studentId}")
    @LogOperate(value = "check for student's vaccination status and next date for injection")
    public Result<Object> checkStatusAndNextDate(@RequestAttribute Integer studentId) {
        List<VaccinationVO> vaccinationVOS= new Vector<>();
        ConverterUtils.convertList(vaccinationService.checkNextDateforVaccination(studentId),
                vaccinationVOS,VaccinationVO.class);
        return Result.buildOkData(vaccinationVOS);
    }
}
