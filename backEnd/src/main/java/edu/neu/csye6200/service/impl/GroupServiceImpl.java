package edu.neu.csye6200.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.neu.csye6200.base.BaseServiceImpl;
import edu.neu.csye6200.dao.GroupMapper;
import edu.neu.csye6200.dao.StudentMapper;
import edu.neu.csye6200.entity.Group;
import edu.neu.csye6200.entity.Student;
import edu.neu.csye6200.entity.dto.GroupDO;
import edu.neu.csye6200.entity.dto.StudentDO;
import edu.neu.csye6200.entity.vo.GroupVO;
import edu.neu.csye6200.service.GroupService;
import edu.neu.csye6200.utils.ConverterUtils;
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
  GroupMapper groupMapper;
  @Resource
  StudentMapper studentMapper;

  public Group selectGroupByGroupId(int groupId) {
    GroupDO groupDO = groupMapper.selectOne(Wrappers.<GroupDO>query().eq("groupId", groupId));
    List<Student> studentList = getStudentListByGroupId(groupId);
    Group group = new Group();
    BeanUtils.copyProperties(groupDO, group);
    group.setStudentList(new Vector<>(studentList));
    // todo Assign Teacher
    return group;

  }

    @Override
    public GroupVO selectGroupVOByGroupId(int groupId) {
        Group group = selectGroupByGroupId(groupId);
        GroupVO groupVO = new GroupVO();
        ConverterUtils.convert(group, groupVO);
        return groupVO;
    }

    @Override
    public IPage<GroupVO> pageSelectByClassId(int pageNo, int pageSize, int classroomId) {
      IPage<GroupDO>groupDOIPage=groupMapper.selectPage(new Page<>(pageNo,pageSize),Wrappers.<GroupDO>query().eq("classroomId", classroomId));
      IPage<GroupVO>groupVOIPage=groupDOIPage.convert(ele->{
          GroupVO groupVO=new GroupVO();
          return (GroupVO)ConverterUtils.convertAndReturn(ele,groupVO);
      });
        return groupVOIPage;
    }

    public List<Student> getStudentListByGroupId(int groupId) {
        List<StudentDO> studentDOS = studentMapper.selectList(Wrappers.<StudentDO>query().eq("groupId", groupId));
        List<Student> studentList = new Vector<>();
        ConverterUtils.convertList(studentDOS, studentList, Student.class);

        return studentList;
    }

    @Override
    public List<Group> queryGroupByClassroomId(int classroomId) {
        List<GroupDO> groupDOList = groupMapper.selectList(Wrappers.<edu.neu.csye6200.entity.dto.GroupDO>query().eq("classroomId", classroomId));
        List<Group> groupList = new Vector<>();
        ConverterUtils.convertList(groupDOList, groupList, Group.class);
        return groupList;
    }



}
