package edu.neu.csye6200.entity;

import edu.neu.csye6200.base.convertor.StudentConverter;
import edu.neu.csye6200.entity.vo.GroupVO;
import edu.neu.csye6200.entity.vo.StudentVO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

/**
 * @author Caspar
 * @date 2020/8/12 09:25
 */
@Data
public class Group extends AbstractGroup{
    private Integer groupId;

    private Integer classroomId;

    private Integer ageState;

    private Integer teacherId;

    private Integer studentCount;

    private Integer ratio;

    private Boolean isFull;

    private Vector<Student> studentList;

    private Teacher teacher;

    public Group() {
        super();
        setGroupId(-1);
        setClassroomId(-1);
        setAgeState(0);
        setTeacherId(-1);
        setStudentCount(0);
        setRatio(4);
        setIsFull(Boolean.FALSE);
        setStudentList(new Vector<>());
    }

    public Group(int ageState, int ratio) {
        setAgeState(ageState);
        setRatio(ratio);

        setIsFull(false);
        setClassroomId(-1);
        setTeacherId(-1);
        setStudentCount(0);
        setStudentList(new Vector<>());
    }

    public boolean verifyStateRegulation(Teacher teacher) {
        return teacher.getTargetAgeState().equals(getAgeState());
    }

    @Override
    public void assignTeacher(Teacher teacher) {
        if (verifyStateRegulation(teacher)) {
            setTeacher(teacher);
            setTeacherId(teacher.getTeacherId());
        }
    }

    @Override
    public boolean addStudent(Student student) {
        boolean success = false;
        if (!getIsFull()) {
            success = studentList.add(student);
            setStudentCount(studentList.size());
        }
        return success;
    }

    public void updateStudentCount() {
        setStudentCount(studentList.size());
        if (getStudentCount()>=getRatio()) {
            setIsFull(Boolean.TRUE);
        }
    }

    @Override
    public boolean removeStudent(Student student) {
        boolean success = false;
        success = studentList.remove(student);
        updateStudentCount();
        return success;
    }

    @Override
    public Student findStudentByStudentId(int studentId) {
        Student student = null;
        for (Iterator<Student> iterator=studentList.iterator();iterator.hasNext();) {
            student = iterator.next();
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return student;
    }

    @Override
    public void save() {
        // todo
        // Persist the Group object

    }

    public void removeStudentByStudentId(int studentId) {
        studentList.remove(findStudentByStudentId(studentId));
    }

//    public GroupVO convertToVO() {
//        GroupVO groupVO = GroupConverter.model2Vo(this)
//        return groupVO;
//    }


    
}
