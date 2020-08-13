package edu.neu.csye6200.manager;

import edu.neu.csye6200.entity.Student;

/**
 * @author Caspar
 * @date 2020/8/13 14:20
 */

public interface EnrollmentManager {

    /**
     * @param student Student
     * assign a new student to a classroom and to a group
     */
    public void enroll(Student student);

    public void test();
}
