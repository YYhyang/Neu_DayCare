package edu.neu.csye6200.manager.impl;

import edu.neu.csye6200.base.enums.AgeStateEnum;
import edu.neu.csye6200.base.enums.ClassroomStateEnum;
import edu.neu.csye6200.entity.Classroom;
import edu.neu.csye6200.entity.Group;
import edu.neu.csye6200.entity.Student;
import edu.neu.csye6200.entity.Teacher;
import edu.neu.csye6200.entity.dto.ClassroomDO;
import edu.neu.csye6200.entity.dto.GroupDO;
import edu.neu.csye6200.entity.dto.TeacherDO;
import edu.neu.csye6200.entity.dto.StudentDO;
import edu.neu.csye6200.entity.vo.GroupVO;
import edu.neu.csye6200.manager.EnrollmentManager;
import edu.neu.csye6200.service.ClassroomService;
import edu.neu.csye6200.service.GroupService;
import edu.neu.csye6200.service.StudentService;
import edu.neu.csye6200.service.TeacherService;
import edu.neu.csye6200.utils.ConverterUtils;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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
    @Resource
    TeacherService teacherService;

    /**
     * 封装Group对象， 根据groupId从数据库取得特定的GroupDO对象，
     * 将GroupDO对象转化为一个Group对象，需要给他补充其他属性，Vector<Student>和Teacher
     * @param groupId int
     * @return Group
     */
    public Group getGroup(int groupId) {
        GroupVO groupVO = groupService.selectGroupVOByGroupId(groupId);
        Group group = new Group();
        ConverterUtils.convert(groupVO, group);
        List<Student> students = studentService.queryStudentByGroupId(groupId);
        group.setStudentList(new Vector<>(students));
        // todo 完成教师赋值工作
        Teacher teacher = teacherService.selectByGroupID(groupId);
        group.assignTeacher(teacher);

        return group;
    }

    /**
     * 根据学生id从数据库获取一个StudentVO对象，转化为Student对象并返回
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
    public void assignStudent(Classroom classroom, Group group, Student student) {
        group.addStudent(student, classroom);
        student.setGroupId(group.getGroupId());
        saveStudent(student);
        updateGroup(group);
        updateClassroom(classroom);
    }

    public void assignGroup(Classroom classroom, Group group) {
        classroom.addGroup(group);
        updateClassroom(classroom);

    }

    /**
     * 持久化Student对象
     */
    public void saveStudent(Student student) {
        StudentDO studentDO = new StudentDO();
        ConverterUtils.convert(student, studentDO);
        studentService.save(studentDO);
        student.setStudentId(studentDO.getStudentId());
    }
    /**
     * 持久化Group对象
     */
    public void saveGroup(Group group) {
        GroupDO groupDO = new GroupDO();
        ConverterUtils.convert(group, groupDO);
        groupService.save(groupDO);
        group.setGroupId(groupDO.getGroupId());
    }

    /**
     * 持久化Group对象，update
     */
    public void updateGroup(Group group) {
        GroupDO groupDO = new GroupDO();
        ConverterUtils.convert(group, groupDO);
        groupService.updateById(groupDO);
    }

    /**
     * 持久化Teacher对象
     */
    public void saveTeacher(Teacher teacher) {
        TeacherDO teacherDO = new TeacherDO();
        ConverterUtils.convert(teacher, teacherDO);
        teacherService.save(teacherDO);
        teacher.setTeacherId(teacherDO.getTeacherId());
    }

    /**
     * 持久化Classroom对象，update
     */
    public void updateTeacher(Teacher teacher) {
        TeacherDO teacherDO = new TeacherDO();
        ConverterUtils.convert(teacher, teacherDO);
        teacherService.updateById(teacherDO);
    }

    /**
     * 持久化Classroom对象，insert
     */
    public void saveClassroom(Classroom classroom) {
        ClassroomDO classroomDO = new ClassroomDO();
        ConverterUtils.convert(classroom, classroomDO);
        classroomService.save(classroomDO);
        classroom.setClassroomId(classroomDO.getClassroomId());

    }

    /**
     * 持久化Classroom对象，update
     */
    public void updateClassroom(Classroom classroom) {
        ClassroomDO classroomDO = new ClassroomDO();
        ConverterUtils.convert(classroom, classroomDO);
        classroomService.updateById(classroomDO);
    }

    /**
     * 寻找合适的教室
     */
    public Classroom getAvailableClassroom(Student student) {
        String ageState = student.getAgeState();
        List<Classroom> classroomList = classroomService.queryClassroomByAgeState(ageState);
        for(Classroom c : classroomList){
            if(c.getFullState().equals(ClassroomStateEnum.NOT_FULL.getCode())){
                c.setMaxCapacity(c.getAgeCapacityTable().get(ageState));
                return c;
            }
        }

        Classroom classroom = new Classroom();
        classroom.setAgeState(ageState);
        classroom.setMaxCapacity(classroom.getAgeCapacityTable().get(ageState));

        saveClassroom(classroom);

        return classroom;
    }

    /**
     * 寻找合适的组
     */
    public Group getAvailableGroup(Classroom classroom) {

        int classroomId = classroom.getClassroomId();
        String ageState = classroom.getAgeState();

        if(classroom.getGroupNum() != 0){

            //不需要新建组的情况：该教室里有的组人数没有达到Ratio；
            List<Group> groupList = groupService.queryGroupByClassroomId(classroomId);
            for(Group g : groupList) {
                if(g.getStudentCount() < g.getRatio()) {
                    return g;
                }
            }
        }

        /**
         需要新建组的情况：
         1.如果是新的教室，我们就新建一个组;
         2. 这个教室里面都是满Raito的组，但是还没有达到 maximum capacity。
         */
        Group group = new Group();
        group.setClassroomId(classroomId);
        group.setAgeState(ageState);
        group.setRatio(group.getAgeRatioTable().get(ageState));

        saveGroup(group);
        assignGroup(classroom, group);

        return group;
    }


    public Group assignTeacher(Group group){

        String ageState = group.getAgeState();
        int groupId = group.getGroupId();
        int classroomId = group.getClassroomId();
        int teacherId = group.getTeacherId();

        if(teacherId == -1) {
            List<Teacher> teacherList = teacherService.queryByAgeState(ageState);
            for (Teacher t : teacherList) {
                if (t.getTargetAgeState().equals(ageState) && t.getGroupId() == -1) {
                    t.setGroupId(groupId);
                    t.setClassroomId(classroomId);
                    updateTeacher(t);
                    group.setTeacher(t);
                    group.setTeacherId(t.getTeacherId());
                    updateGroup(group);
                    return group;
                } else {
                    System.out.println("Need to add required agestate teacher");
                }
            }
        }
        return group;
    }


    /**
     * 新生入学注册
     */
    @Override
    public void enroll(Student student) {
        // 1. 根据ageState以及是否满员，确定是否有教室,如果没有则新建一个教室
        Classroom classroom = getAvailableClassroom(student);

        // 2. 寻找在已有教室里的未满员的组或建立新组/在新教室新建新组
        Group group = getAvailableGroup(classroom);

        // 3. 给新建的组分配老师
        Group doneGroup = assignTeacher(group);

        // 4. 把学生分配到组和班级中
        assignStudent(classroom, doneGroup, student);
    }

    @Override
    public void test() {
//        Student student = getStudent(2);
//        System.out.println(student);

        Group group = getGroup(68);
        System.out.println(group);
//        Student student = new Student();
//        student.setName("Bi1");
//        student.setParentName("BiParent1");
//        student.setAddress("Beijing");
//        student.setBirthday(new Date());
//        student.setPhone("13917652864");
//        student.setAgeState(AgeStateEnum.AGE_STATE_1.getCode());
//        enroll(student);

    }
}
