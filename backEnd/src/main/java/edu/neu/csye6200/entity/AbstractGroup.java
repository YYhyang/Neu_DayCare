package edu.neu.csye6200.entity;

import lombok.Data;

import java.util.Vector;

/**
 * @author Caspar
 * @date 2020/8/12 19:59
 */
public abstract class AbstractGroup {
    private Integer groupId;

    private Integer classroomId;

    private Integer ageState;

    private Integer teacherId;

    private Integer studentCount;

    private Integer ratio;

    private Boolean isFull;

    private Vector<AbstractStudent> studentList;

    private Teacher teacher;

    public abstract boolean addStudent(Student student);

    public abstract boolean removeStudent(Student student);

    public abstract Student findStudentByStudentId(int studentId);

    public abstract void assignTeacher(Teacher teacher);


    /**
     * persist the object
     */
    public abstract void save();

}
