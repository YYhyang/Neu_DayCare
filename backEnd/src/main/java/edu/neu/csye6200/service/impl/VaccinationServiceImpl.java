package edu.neu.csye6200.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import edu.neu.csye6200.base.BaseServiceImpl;
import edu.neu.csye6200.dao.ImmunizationMapper;
import edu.neu.csye6200.dao.VaccinationMapper;
import edu.neu.csye6200.entity.Immunization;
import edu.neu.csye6200.entity.Vaccination;
import edu.neu.csye6200.entity.dto.ImmunizationDO;
import edu.neu.csye6200.entity.dto.VaccinationDO;
import edu.neu.csye6200.entity.vo.VaccinationVO;
import edu.neu.csye6200.service.VaccinationService;
import edu.neu.csye6200.utils.ConverterUtils;
import org.springframework.stereotype.Service;
import edu.neu.csye6200.base.enums.VaccinationStatusEnum;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * @author Caspar
 * @date 2020/8/13 20:35
 */
@Service
public class VaccinationServiceImpl extends BaseServiceImpl<VaccinationMapper, VaccinationDO> implements VaccinationService {
    @Resource
    VaccinationMapper vaccinationMapper;
    @Resource
    ImmunizationMapper immunizationMapper;

    @Override
    public List<Vaccination> getListVaccination(int studentId) {
        List<VaccinationDO> vaccinationDOs = vaccinationMapper.selectList(Wrappers.<VaccinationDO>query().eq("studentId", studentId));
        List<Vaccination> list = new Vector<>();
        ConverterUtils.convertList(vaccinationDOs, list, Vaccination.class);
        return list;
    }

    @Override
    public VaccinationVO getVaccination(int studentId, String immunizationName) {
        VaccinationDO vaccinationDO = vaccinationMapper.selectOne(Wrappers.<VaccinationDO>query()
                .eq("studentId", studentId).eq("immunizationName", immunizationName));
        VaccinationVO vaccinationVO = new VaccinationVO();
        ConverterUtils.convert(vaccinationDO, vaccinationVO);
        return vaccinationVO;
    }

    @Override
    public void addVaccination(int id) {
        VaccinationDO vaccinationDO = vaccinationMapper.selectById(id);
        ImmunizationDO immunizationDO = immunizationMapper.selectOne(Wrappers.<ImmunizationDO>query()
                .eq("name", vaccinationDO.getImmunizationName()));

        vaccinationDO.setRecordDate(new Date());

        int vaccinationNum = vaccinationDO.getVaccinationNumber();
        int requiredNum = immunizationDO.getDose();
        vaccinationDO.setRequiredNumber(requiredNum);

        if (vaccinationNum == 0) {
            vaccinationDO.setCompleteStatus("NOT_TREATED");
        } else if(vaccinationNum > 0 && vaccinationNum < requiredNum) {
            vaccinationDO.setCompleteStatus("UNCOMPLETED");
        } else {
            vaccinationDO.setCompleteStatus("COMPLETED");
        }
    }

    @Override
    public void updateVaccination(int id) {
        VaccinationDO vaccinationDO = vaccinationMapper.selectById(id);
        vaccinationDO.setVaccinationNumber(vaccinationDO.getVaccinationNumber() + 1);

        if (vaccinationDO.getRequiredNumber() == vaccinationDO.getVaccinationNumber()) {
            vaccinationDO.setCompleteStatus("COMPLETED");
        } else {
            vaccinationDO.setCompleteStatus("UNCOMPLETED");
        }
    }

    @Override
    public Date checkDateforVaccination(int studentId) {
        return null;
    }
}
