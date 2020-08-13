package edu.neu.csye6200.manager;

import edu.neu.csye6200.DayCareApplicationTests;
import edu.neu.csye6200.bean.Student;
import edu.neu.csye6200.manager.EnrollmentManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Caspar
 * @date 2020/8/13 15:10
 */
@RunWith(SpringRunner.class)
public class EnrollmentManagerTest extends DayCareApplicationTests {

    @Autowired
    EnrollmentManager enrollmentManager;

    @Test
    public void testTest(){
        enrollmentManager.test();
    }
}