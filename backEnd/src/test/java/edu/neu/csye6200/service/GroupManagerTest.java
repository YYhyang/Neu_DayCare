package edu.neu.csye6200.service;

import edu.neu.csye6200.dao.GroupMapper;
import edu.neu.csye6200.entity.Group;
import junit.framework.TestCase;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Caspar
 * @date 2020/8/13 15:10
 */
public class GroupManagerTest extends TestCase {

    @Autowired
    GroupManager groupManager;
    @Autowired
    GroupMapper groupMapper;
    public void testGetGroup() {
        groupMapper.selectById(1);
        Group group = groupManager.getGroup(1);
        assertNotNull(group);
        assertEquals(java.util.Optional.ofNullable(group.getStudentCount()), 4);
    }

    public void testGetStudent() {
    }

    public void testAssignStudent() {
    }

    public void testSaveStudent() {
    }

    public void testSaveGroup() {
    }
}