package edu.neu.csye6200.base.convertor;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Caspar
 * @date 2020/8/12 22:01
 */
public class DayCareBeanUtil {

    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }

//    public static void copyListProperties(List<Object> sourceList, List<Object> targetList) {
//        Class classSource = sourceList.get(0).getClass();
//        Class classTarget = targetList.get(0).getClass();
//
//        targetList = sourceList.stream().map(item->{
//            Object obj = new Object();
//            BeanUtils.copyProperties(item, obj, classSource, classTarget);
//            return obj;
//        }).collect(Collectors.toList());
//    }

}
