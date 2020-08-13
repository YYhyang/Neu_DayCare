package edu.neu.csye6200.utils;

import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Caspar
 * @date 2020/8/13 12:17
 */
public class ConverterUtils {

    public static <T> void convert(T source, T target){

        if (source==null) {
            return;
        }

        Method[] arrSourceMethods =  source.getClass().getMethods();
        Method[] arrTargetMethods = target.getClass().getMethods();
        Field[] arrSourceFields = source.getClass().getDeclaredFields();

        List<String> sourceMethodName = Arrays.stream(arrSourceMethods).map(ele -> ele.getName().toLowerCase())
                .collect(Collectors.toList());
        List<String> targetMethodNames = Arrays.stream(arrTargetMethods).map(ele -> ele.getName().toLowerCase())
                .collect(Collectors.toList());
        List<String> fields = Arrays.stream(arrSourceFields).map(ele -> ele.getName().toLowerCase())
                .collect(Collectors.toList());

        try {
            for(int i=0;i<fields.size();i++) {
                StringBuffer setterSb = new StringBuffer("set").append(fields.get(i));
                StringBuffer getterSb = new StringBuffer("get").append(fields.get(i));

                int indexGetter = sourceMethodName.indexOf(getterSb.toString());
                Object value = null;

                if (indexGetter!=-1) {
                    Method getter = arrSourceMethods[indexGetter];
                    value = getter.invoke(source);
                    if (value != null) {
                        int indexSetter = targetMethodNames.indexOf(setterSb.toString());
                        if (indexSetter!=-1) {
                            Method setter = arrTargetMethods[indexSetter];
                            CsvUtils.invoke(setter, value, target);
                        }
                    }
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public static <T> void convertList(T[] sourceArr, T[] targetArr) {
        if (sourceArr==null||sourceArr.length==0) {
            return;
        }

        try {

            for (T item:targetArr) {
                item = (T) targetArr.getClass().getComponentType().newInstance();
            }

            Method[] arrSourceMethods =  sourceArr[0].getClass().getMethods();
            Method[] arrTargetMethods = targetArr.getClass().getComponentType().getMethods();
            Field[] arrSourceFields =  targetArr.getClass().getComponentType().getDeclaredFields();

            List<String> sourceMethodName = Arrays.stream(arrSourceMethods).map(ele -> ele.getName().toLowerCase())
                    .collect(Collectors.toList());
            List<String> targetMethodNames = Arrays.stream(arrTargetMethods).map(ele -> ele.getName().toLowerCase())
                    .collect(Collectors.toList());
            List<String> fields = Arrays.stream(arrSourceFields).map(ele -> ele.getName().toLowerCase())
                    .collect(Collectors.toList());


            for (int j=0;j<sourceArr.length;j++) {

                T instance = (T) targetArr.getClass().getComponentType().newInstance();
                for(int i=0;i<fields.size();i++) {
                    StringBuffer setterSb = new StringBuffer("set").append(fields.get(i));
                    StringBuffer getterSb = new StringBuffer("get").append(fields.get(i));

                    int indexGetter = sourceMethodName.indexOf(getterSb.toString());
                    Object value = null;
                    if (indexGetter!=-1) {
                        Method getter = arrSourceMethods[indexGetter];
                        value = getter.invoke(sourceArr[j]);
                        if (value != null) {
                            int indexSetter = targetMethodNames.indexOf(setterSb.toString());
                            if (indexSetter!=-1) {
                                Method setter = arrTargetMethods[indexSetter];
                                CsvUtils.invoke(setter, value, instance);
                                targetArr[j] = instance;
                            }
                        }
                    }
                }
            }

        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }




    }


    /**
     * @param sourceList source list
     * @param clzTarget target class
     * @param <T>
     * @return List<T>
     */
    public static <T> List<T> convertList(List<? extends T> sourceList, Class clzTarget) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return null;
        }
        try {

            Method[] arrSourceMethods =  sourceList.get(0).getClass().getMethods();
            Method[] arrTargetMethods = clzTarget.getMethods();
            Field[] arrSourceFields = sourceList.get(0).getClass().getDeclaredFields();

            List<String> sourceMethodName = Arrays.stream(arrSourceMethods).map(ele -> ele.getName().toLowerCase())
                    .collect(Collectors.toList());
            List<String> targetMethodNames = Arrays.stream(arrTargetMethods).map(ele -> ele.getName().toLowerCase())
                    .collect(Collectors.toList());
            List<String> fields = Arrays.stream(arrSourceFields).map(ele -> ele.getName().toLowerCase())
                    .collect(Collectors.toList());

            List targetlist = sourceList.getClass().newInstance();
            for (int j=0;j<sourceList.size();j++) {

                T instance = (T) clzTarget.newInstance();
                targetlist.add(instance);
                for(int i=0;i<fields.size();i++) {
                    StringBuffer setterSb = new StringBuffer("set").append(fields.get(i));
                    StringBuffer getterSb = new StringBuffer("get").append(fields.get(i));

                    int indexGetter = sourceMethodName.indexOf(getterSb.toString());
                    Object value = null;
                    if (indexGetter!=-1) {
                        Method getter = arrSourceMethods[indexGetter];
                        value = getter.invoke(sourceList.get(j));
                        if (value != null) {
                            int indexSetter = targetMethodNames.indexOf(setterSb.toString());
                            if (indexSetter!=-1) {
                                Method setter = arrTargetMethods[indexSetter];
                                CsvUtils.invoke(setter, value, instance);
                            }
                        }
                    }
                }
            }
            return targetlist;

        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }

        return null;
    }

//    public static <T> void convertList(List<? extends T> sourceList, List<? extends T> targetList) {
//        // todo
//        //  List<? extends T>类型不允许插入元素
//        if (CollectionUtils.isEmpty(sourceList)) {
//            return;
//        }
//        // targetList中放入一个目标转化类的对象，以确认转化对象的类，获取类信息之后将其删除
//        Class targetClz = targetList.get(0).getClass();
//        targetList.remove(0);
//
//        try {
//
//            Method[] arrSourceMethods =  sourceList.get(0).getClass().getMethods();
//            Method[] arrTargetMethods = targetClz.getMethods();
//            Field[] arrSourceFields = sourceList.get(0).getClass().getDeclaredFields();
//
//            List<String> sourceMethodName = Arrays.stream(arrSourceMethods).map(ele -> ele.getName().toLowerCase())
//                    .collect(Collectors.toList());
//            List<String> targetMethodNames = Arrays.stream(arrTargetMethods).map(ele -> ele.getName().toLowerCase())
//                    .collect(Collectors.toList());
//            List<String> fields = Arrays.stream(arrSourceFields).map(ele -> ele.getName().toLowerCase())
//                    .collect(Collectors.toList());
//
//            for (int j=0;j<sourceList.size();j++) {
//
//                Object instance =  targetClz.newInstance();
//                targetList.add(instance);
//                for(int i=0;i<fields.size();i++) {
//                    StringBuffer setterSb = new StringBuffer("set").append(fields.get(i));
//                    StringBuffer getterSb = new StringBuffer("get").append(fields.get(i));
//
//                    int indexGetter = sourceMethodName.indexOf(getterSb.toString());
//                    Object value = null;
//                    if (indexGetter!=-1) {
//                        Method getter = arrSourceMethods[indexGetter];
//                        value = getter.invoke(sourceList.get(j));
//                        if (value != null) {
//                            int indexSetter = targetMethodNames.indexOf(setterSb.toString());
//                            if (indexSetter!=-1) {
//                                Method setter = arrTargetMethods[indexSetter];
//                                CsvUtils.invoke(setter, value, instance);
//                            }
//                        }
//                    }
//                }
//            }
//
//        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
//            e.printStackTrace();
//        }
//    }

}
