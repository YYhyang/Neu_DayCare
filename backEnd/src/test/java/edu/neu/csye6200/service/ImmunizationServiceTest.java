package edu.neu.csye6200.service;

import edu.neu.csye6200.DayCareApplication;
import edu.neu.csye6200.DayCareApplicationTests;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Caspar
 * @date 2020/8/14 17:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DayCareApplication.class)
public class ImmunizationServiceTest extends DayCareApplicationTests {
    @Resource
    ImmunizationService immunizationService;

    @Test
    void initImmunization() {



    }
}