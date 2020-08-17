package edu.neu.csye6200.entity;

import edu.neu.csye6200.base.enums.ClassroomStateEnum;
import edu.neu.csye6200.base.enums.GroupStateEnum;
import lombok.Data;

import java.util.Hashtable;
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

    private String ageState;

    /**
     * number of groups
     */
    private Integer groupNum;

    private Integer maxCapacity;

    private Hashtable<String, Integer> ageCapacityTable;

    /**
     * group number is the maximum capacity and all groups are full
     * 0 not full, 1 full
     */
    private String fullState;

    private Vector<Group> groupList;



    public Classroom(){
        super();
        setClassroomId(-1);
        setAgeState("");
        setGroupNum(0);
        setFullState(GroupStateEnum.NOT_FULL.getCode());
        setGroupList(new Vector<>());
        setAgeCapacityTable(new Hashtable<>());
        populateAgeCapacityTable();
    }



    public boolean verifyStateRegulation(Group group) {
        return group.getAgeState().equals(getAgeState());
    }


    public boolean addGroup(Group group) {
        boolean success = false;
        if (getFullState().equals(ClassroomStateEnum.NOT_FULL.getCode())) {
            success = groupList.add(group);
            updateGroupCount();
        }
        return success;
    }

    public void updateGroupCount() {
        setGroupNum(groupList.size());
        if (getGroupNum()>=getMaxCapacity()) {
            setFullState(ClassroomStateEnum.FULL.getCode());
        }
    }

    public void populateAgeCapacityTable(){
        ageCapacityTable.put("AGE_STATE_0", 3);
        ageCapacityTable.put("AGE_STATE_1", 3);
        ageCapacityTable.put("AGE_STATE_2", 3);
        ageCapacityTable.put("AGE_STATE_3", 3);
        ageCapacityTable.put("AGE_STATE_4", 3);
        ageCapacityTable.put("AGE_STATE_5", 2);
        ageCapacityTable.put("AGE_STATE_6", 2);

    }

}
