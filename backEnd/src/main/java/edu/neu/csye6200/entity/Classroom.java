package edu.neu.csye6200.entity;

import lombok.Data;

import java.util.Vector;

/**
 * @author Caspar
 * @date 2020/8/13 18:40
 *  functions to implement
 * 1. updateSate()，update the value of groupNum and isFull after some operation
 * 2. addGroup(), add a new group into the classroom. Remenber to check whether the classroon is full
 * 3. constructor to create a new classroom
 *
 */
@Data
public class Classroom {

    private Integer classroomId;

    private Integer ageState;

    /**
     * number of groups
     */
    private Integer groupNum;

    /**
     * group number is the maximum capacity and all groups are full
     * 0 not full, 1 full
     */
    private String fullState;

    private Vector<Group> groupList;

}
