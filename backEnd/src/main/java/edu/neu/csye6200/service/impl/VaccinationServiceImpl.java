package edu.neu.csye6200.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import edu.neu.csye6200.base.BaseServiceImpl;
import edu.neu.csye6200.dao.ImmunizationMapper;
import edu.neu.csye6200.dao.VaccinationMapper;
import edu.neu.csye6200.entity.Immunization;
import edu.neu.csye6200.entity.Vaccination;
import edu.neu.csye6200.entity.dto.VaccinationDO;
import edu.neu.csye6200.service.VaccinationService;
import edu.neu.csye6200.utils.ConverterUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}
