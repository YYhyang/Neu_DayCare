package edu.neu.csye6200.entity;

import edu.neu.csye6200.entity.vo.GroupVO;
import junit.framework.TestCase;
import junit.framework.TestResult;
import org.junit.Test;

import java.util.List;
import java.util.Vector;

/**
 * @author Caspar
 * @date 2020/8/12 13:09
 */
public class GroupTest extends TestCase {

    @Test
    public void testConvertToVO() {
        List<Student> students = new Vector<>();
        Group group = new Group(3, 6);
        GroupVO groupVO = group.convertToVO();

    }

    @Override
    public void run(TestResult result) {
        super.run(result);
    }
}