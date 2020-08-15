package edu.neu.csye6200.web;

import com.baomidou.mybatisplus.extension.api.R;
import edu.neu.csye6200.base.BaseController;
import edu.neu.csye6200.base.Result;
import edu.neu.csye6200.base.annotation.LogOperate;
import edu.neu.csye6200.entity.dto.VaccinationDO;
import edu.neu.csye6200.entity.vo.VaccinationVO;
import edu.neu.csye6200.service.VaccinationService;
import edu.neu.csye6200.utils.ConverterUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Vector;

/**
 * @author Caspar Yuhan Yang
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

    @GetMapping(value = "/getByStudentIdAndImmunizationName/{studentId}/{immunizationName}")
    @LogOperate(value = "find by student id and immunization name")
    public Result<Object> getByStudentIdAndImmunizationName(@PathVariable Integer studentId,@PathVariable String immunizationName){
        VaccinationVO vaccinationVO=new VaccinationVO();
        ConverterUtils.convert(vaccinationService.getVaccination(studentId,immunizationName),vaccinationVO);
        return Result.buildOkData(vaccinationVO);
    }

    @PostMapping(value = "/add")
    @LogOperate(value = "add one record")
    public Result<Object> add(@RequestBody VaccinationVO vaccinationVO){
        VaccinationDO vaccinationDO=new VaccinationDO();
        ConverterUtils.convert(vaccinationVO,vaccinationDO);
        boolean insert=vaccinationService.save(vaccinationDO);
        if(insert)
        {
            return Result.buildOkData(vaccinationDO);
        }
        else {
            return Result.buildFailData(vaccinationDO);
        }
    }


}
