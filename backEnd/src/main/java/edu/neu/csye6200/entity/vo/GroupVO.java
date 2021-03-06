package edu.neu.csye6200.entity.vo;

import edu.neu.csye6200.entity.Student;
import edu.neu.csye6200.entity.Teacher;
import lombok.Data;

import java.util.Vector;

/**
 * @author Caspar
 * @date 2020/8/12 13:11
 */
@Data
public class GroupVO {

    private Integer groupId;

    private Integer classroomId;

    private String ageState;

    private Integer teacherId;

    private Integer studentCount;

    private Integer ratio;

    private String fullState;

}
