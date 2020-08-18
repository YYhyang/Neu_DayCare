package edu.neu.csye6200.entity;


import edu.neu.csye6200.base.enums.AgeStateEnum;
import edu.neu.csye6200.base.enums.ClassroomStateEnum;
import edu.neu.csye6200.base.enums.GroupStateEnum;
import lombok.Data;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

/**
 * @author Caspar
 * @date 2020/8/12 09:25
 */
@Data
public class Group {
    private Integer groupId;

    private Integer classroomId;

    private String ageState;

    private Integer teacherId;

    private Integer studentCount;

    private Integer ratio;

    private String fullState;

    private Vector<Student> studentList;

    private Teacher teacher;

    private Hashtable<String, Integer> ageRatioTable;

    public Group() {
        super();
        setGroupId(-1);
        setClassroomId(-1);
        setAgeState("");
        setTeacherId(-1);
        setStudentCount(0);
        setRatio(4);
        setFullState(GroupStateEnum.NOT_FULL.getCode());
        setStudentList(new Vector<>());
        setAgeRatioTable(new Hashtable<>());
        populateAgeRatioTable();
    }

    public Group(String ageState, int ratio) {
        setAgeState(ageState);
        setRatio(ratio);
        setFullState(GroupStateEnum.NOT_FULL.getCode());
        setClassroomId(-1);
        setTeacherId(-1);
        setStudentCount(0);
        setStudentList(new Vector<>());
        setAgeRatioTable(new Hashtable<>());
        populateAgeRatioTable();
    }

    public void populateAgeRatioTable(){
        ageRatioTable.put(AgeStateEnum.AGE_STATE_0.getCode(), 4);
        ageRatioTable.put(AgeStateEnum.AGE_STATE_1.getCode(), 4);
        ageRatioTable.put(AgeStateEnum.AGE_STATE_2.getCode(), 5);
        ageRatioTable.put(AgeStateEnum.AGE_STATE_3.getCode(), 6);
        ageRatioTable.put(AgeStateEnum.AGE_STATE_4.getCode(), 8);
        ageRatioTable.put(AgeStateEnum.AGE_STATE_5.getCode(), 12);
        ageRatioTable.put(AgeStateEnum.AGE_STATE_6.getCode(), 15);

    }

    public boolean verifyStateRegulation(Teacher teacher) {
        return teacher.getTargetAgeState().equals(getAgeState());
    }


    public void assignTeacher(Teacher teacher) {
        if (verifyStateRegulation(teacher)) {
            setTeacher(teacher);
            setTeacherId(teacher.getTeacherId());
        }
    }

    public boolean addStudent(Student student, Classroom classroom) {
        boolean success = false;
        if (getFullState().equals(GroupStateEnum.NOT_FULL.getCode())) {
            success = studentList.add(student);
            updateStudentCount(classroom);
        }
        return success;
    }

    public void updateStudentCount(Classroom classroom) {
        setStudentCount(getStudentCount() + 1);
        if (getStudentCount() >= getRatio()) {
            setFullState(GroupStateEnum.FULL.getCode());
            if(classroom.getGroupNum() >= classroom.getMaxCapacity()){
                classroom.setFullState(ClassroomStateEnum.FULL.getCode());
            }
        }

    }

//    public boolean removeStudent(Student student) {
//        boolean success = false;
//        success = studentList.remove(student);
//        updateStudentCount();
//        return success;
//    }

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

//    public void removeStudentByStudentId(int studentId) {
//        studentList.remove(findStudentByStudentId(studentId));
//    }

    
}
