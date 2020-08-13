package edu.neu.csye6200.manager.impl;

import edu.neu.csye6200.entity.Classroom;
import edu.neu.csye6200.entity.Group;
import edu.neu.csye6200.entity.Student;
import edu.neu.csye6200.entity.Teacher;
import edu.neu.csye6200.entity.dto.ClassroomDO;
import edu.neu.csye6200.entity.dto.GroupDO;
import edu.neu.csye6200.entity.dto.StudentDO;
import edu.neu.csye6200.entity.vo.GroupVO;
import edu.neu.csye6200.manager.EnrollmentManager;
import edu.neu.csye6200.service.ClassroomService;
import edu.neu.csye6200.service.GroupService;
import edu.neu.csye6200.service.StudentService;
import edu.neu.csye6200.utils.ConverterUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Vector;

/**
 * @author Caspar
 * @date 2020/8/13 20:13
 */
@Service
public class EnrollmentManagerImpl implements EnrollmentManager {
    /**
     * Service层 web框架逻辑层，提供与前端，数据库交互的接口
     */
    @Resource
    GroupService groupService;
    @Resource
    StudentService studentService;
    @Resource
    ClassroomService classroomService;

    /**
     * 封装Group对象， 根据groupId从数据库取得特定的GroupDO对象，
     * 将GroupDO对象转化为一个Group对象，需要给他补充其他属性，Vector<Student>和Teacher
     * @param groupId int
     * @return Group
     */
    public Group getGroup(int groupId) {
        GroupVO groupVO = groupService.selectGroupVOByGroupId(groupId);
        Group group = new Group();
//        BeanUtils.copyProperties(groupVO, group);
        ConverterUtils.convert(groupVO, group);
        List<Student> students = studentService.queryStudentByGroupId(groupId);
        group.setStudentList(new Vector<>(students));
        // todo 完成教师赋值工作
        // group.assignTeacher();

        return group;
    }

    /**
     * 根据学生id从数据库获取一个StudentDO对象，转化为Student对象并返回
     * @param studentId int
     * @return Student
     */
    public Student getStudent(int studentId) {
        Student student = new Student();
        ConverterUtils.convert(studentService.selectOneById(studentId), student);
        return student;
    }
    /**
     * 将一个Student对象加入到一个Group对象中, 即给他分配班级
     * 每次对一个对象的值进行过修改后，需要将其持久化到数据库中，
     * 这要求我们调用save方法完成持久化
     * @param group Group
     * @param student Student
     * @return Student
     */
    public void assignStudent(Group group, Student student) {
        group.addStudent(student);
        saveStudent(student);
        saveGroup(group);
    }

    /**
     * 持久化Student对象
     */
    public void saveStudent(Student student) {
        StudentDO studentDO = new StudentDO();
        ConverterUtils.convert(student, studentDO);
        studentService.save(studentDO);
    }
    /**
     * 持久化Group对象
     */
    public void saveGroup(Group group) {
        GroupDO groupDO = new GroupDO();
        ConverterUtils.convert(group, groupDO);
        groupService.save(groupDO);
    }
    /**
     * 持久化Teacher对象
     */
    public void saveTeacher(Teacher teacher) {
        // todo  TeacherService 完成
//        TeacherDO teacherDO = new TeacherDO();
//        ConverterUtils.convert(teacher, teacherDO);
//        teacherService.save(teacherDO);
    }
    /**
     * 持久化Classroom对象
     */
    public void saveClassroom(Classroom classroom) {
        ClassroomDO classroomDO = new ClassroomDO();
        ConverterUtils.convert(classroom, classroomDO);
        classroomService.save(classroomDO);

    }

    /**
     * 寻找合适的教室
     */
    public Classroom getAvailableClassroom(Student student) {
        int ageState = student.getAgeState();
        Classroom classroom = classroomService.selectOneClassroomByState(ageState);
        return classroom;
    }

    //todo
    /**
     * 新生入学, 将学生分配到group中
     */
    @Override
    public void enroll(Student student) {
        // 1. 根据ageState以及是否满员，确定是否有空教室
        Classroom classroom = getAvailableClassroom(student);
        // 2. 检查是否获得一个合适的教室，没有的话则需要新建一个教室(分配老师)

        // 3. 确定教室之后，需要将学生分配到group中
//        assignStudent()
        // 4. 每次修改后，需要save对象


    }

    @Override
    public void test() {
        Student student = getStudent(1);
    }
}
