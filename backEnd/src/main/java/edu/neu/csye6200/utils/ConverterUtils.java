package edu.neu.csye6200.utils;

import edu.neu.csye6200.entity.Student;
import edu.neu.csye6200.entity.vo.StudentVO;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * @author Caspar
 * @date 2020/8/12 23:25
 */
public class ConverterUtils {

    public static void test() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        //注册mapper
        mapperFactory.classMap(StudentVO.class, Student.class);

        //获取mapper门面
        MapperFacade mapper = mapperFactory.getMapperFacade();
        //创建PersonSource对象
        StudentVO sourceVO = new StudentVO();
        // 给source对象设置一些字段

        // 将PersonSource对象转换为PersonDest
        Student student = mapper.map(sourceVO, Student.class);
    }

}
