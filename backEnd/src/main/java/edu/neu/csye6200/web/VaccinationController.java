package edu.neu.csye6200.web;

import edu.neu.csye6200.base.BaseController;
import edu.neu.csye6200.base.Result;
import edu.neu.csye6200.base.annotation.LogOperate;
import edu.neu.csye6200.entity.Teacher;
import edu.neu.csye6200.entity.Vaccination;
import edu.neu.csye6200.entity.dto.TeacherDO;
import edu.neu.csye6200.entity.dto.VaccinationDO;
import edu.neu.csye6200.entity.vo.StudentVO;
import edu.neu.csye6200.entity.vo.VaccinationVO;
import edu.neu.csye6200.service.VaccinationService;
import edu.neu.csye6200.utils.ConverterUtils;

import edu.neu.csye6200.utils.CsvUtils;
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

    @GetMapping(value = "/getall")
    @LogOperate(value = "all list")
    public Result<Object> listByStudentId() {
        List<VaccinationVO> vaccinationVOs = new Vector<>();
        ConverterUtils.convertList(vaccinationService.getAll(), vaccinationVOs,VaccinationVO.class);
        return Result.buildOkData(vaccinationVOs);
    }

    @GetMapping(value = "/listByStudentId/{studentId}")
    @LogOperate(value = "find by student id")
    public Result<Object> listByStudentId(@PathVariable Integer studentId) {
        List<VaccinationVO> vaccinationVOs = new Vector<>();
        ConverterUtils.convertList(vaccinationService.getListVaccination(studentId),vaccinationVOs,VaccinationVO.class);
        return Result.buildOkData(vaccinationVOs);
    }

    @GetMapping(value = "/getVaccination/{studentId}/{immunizationName}")
    @LogOperate(value = "find vaccination by student id and immunization name")
    public Result<Object> getVaccination(@PathVariable Integer studentId, @PathVariable String immunizationName) {
        List<VaccinationVO> vaccinationVOs = vaccinationService.getVaccination(studentId, immunizationName);
        return Result.buildOkData(vaccinationVOs);
    }

    @GetMapping(value = "/getVaccinationLast/{studentId}/{immunizationName}")
    @LogOperate(value = "find newest vaccination by student id and immunization name")
    public Result<Object> getVaccinationLast(@PathVariable Integer studentId, @PathVariable String immunizationName) {
        VaccinationVO vaccinationVO = vaccinationService.getVaccinationLast(studentId, immunizationName);
        return Result.buildOkData(vaccinationVO);
    }

    @PostMapping(value = "/add")
    @LogOperate(value = "add new vaccination record")
    public Result<Object> add(@RequestBody VaccinationVO vaccinationVO) {
        VaccinationDO vaccinationDO = new VaccinationDO();
        ConverterUtils.convert(vaccinationVO,vaccinationDO);
        vaccinationService.addVaccination(vaccinationDO);

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
        vaccinationService.updateVaccination(vaccinationDO);

        boolean update = vaccinationService.updateById(vaccinationDO);
        if(update) {
            return Result.buildOkData(vaccinationDO);
        } else {
            return Result.buildFailData(vaccinationDO);
        }
    }

    @PostMapping("/checkStatusAndNextDate/{studentId}")
    @LogOperate(value = "check for student's vaccination status and next date for injection")
    public Result<Object> checkStatusAndNextDate(@PathVariable Integer studentId) {
        List<Vaccination> list = vaccinationService.checkNextDateforVaccination(studentId);
        List<VaccinationVO> vaccinationVOs = new Vector<>();
        List<VaccinationDO> vaccinationDOs = new Vector<>();
        ConverterUtils.convertList(list, vaccinationDOs, VaccinationDO.class);

        boolean update = vaccinationService.updateBatchById(vaccinationDOs);
        if (update) {
            ConverterUtils.convertList(list, vaccinationVOs, VaccinationVO.class);
            return Result.buildOkData(vaccinationVOs);
        } else {
            return Result.buildFailData(vaccinationVOs);
        }
    }

    @GetMapping("/checkMonth")
    @LogOperate(value = "find all students who need injection in next month")
    public Result<Object> checkMonth() {
        List<StudentVO> studentVOs = new Vector<>();
        ConverterUtils.convertList(vaccinationService.checkMonth(), studentVOs, StudentVO.class);

        return Result.buildOkData(studentVOs);
    }

    @GetMapping("/checkNeedVaccinationMonth/{studentId}")
    @LogOperate(value = "find all vaccination need injection for the student in next month")
    public Result<Object> checkNeedVaccinationMonth(@PathVariable int studentId) {
        List<VaccinationVO> list = new Vector<>();

        ConverterUtils.convertList(vaccinationService.checkNeedVaccinationMonth(studentId), list, VaccinationVO.class);

        return Result.buildOkData(list);
    }

    @GetMapping("/checkStudentNeedVaccinationMonth/{immunizationName}")
    @LogOperate(value = "Find all students who need to inject the vaccin in next month")
    public Result<Object> checkStudentNeedVaccinationMonth(@PathVariable String immunizationName) {
        List<StudentVO> list = new Vector<>();

        ConverterUtils.convertList(vaccinationService.checkStudentNeedVaccinationMonth(immunizationName), list, StudentVO.class);

        return Result.buildOkData(list);
    }
}
