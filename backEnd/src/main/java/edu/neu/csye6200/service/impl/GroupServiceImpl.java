package edu.neu.csye6200.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import edu.neu.csye6200.base.BaseServiceImpl;
import edu.neu.csye6200.dao.GroupMapper;
import edu.neu.csye6200.dao.StudentMapper;
import edu.neu.csye6200.entity.Group;
import edu.neu.csye6200.entity.Student;
import edu.neu.csye6200.entity.dto.GroupDO;
import edu.neu.csye6200.entity.dto.StudentDO;
import edu.neu.csye6200.entity.vo.GroupVO;
import edu.neu.csye6200.service.GroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * @author Caspar
 * @date 2020/8/12 14:36
 */
@Service
public class GroupServiceImpl extends BaseServiceImpl<GroupMapper, GroupDO> implements GroupService {
    @Resource
    GroupMapper groupDOMapper;
    @Resource
    StudentMapper studentMapper;

    public Group selectGroupByGroupId(int groupId) {
        GroupDO groupDO = groupDOMapper.selectOne(Wrappers.<GroupDO>query().eq("groupId", groupId));
        List<Student> studentList = getStudentListByGroupId(groupId);
        Group group = new Group();
        BeanUtils.copyProperties(groupDO, group);
        group.setStudentList(new Vector<>(studentList));
        group.updateStudentCount();
        //todo Assign Teacher
        return group;

    }

    @Override
    public GroupVO selectGroupVOByGroupId(int groupId) {
        Group group = selectGroupByGroupId(groupId);
        return group.convertToVO();
    }

    public List<Student> getStudentListByGroupId(int groupId) {
        List<StudentDO> studentDOS = studentMapper.selectList(Wrappers.<StudentDO>query().eq("groupId", groupId));
        return studentDOS.stream().map(ele->{
            Student student = new Student();
            BeanUtils.copyProperties(ele, student);
            return student;
        }).collect(Collectors.toList());
    }


}