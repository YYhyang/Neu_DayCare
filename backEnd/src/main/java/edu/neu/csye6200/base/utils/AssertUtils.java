package edu.neu.csye6200.base.utils;

import edu.neu.csye6200.base.enums.DayCareResultCodeEnum;
import edu.neu.csye6200.base.exceptions.DayCareException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Assert Util
 * @author Visionary
 * @since 2019/8/28 11:59 PM
 */
public class AssertUtils {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(AssertUtils.class);

    /**
     * 对象不为空
     * @param object 对象
     */
    public static void AssertNotNull(Object object) {
        if(object == null) {
            logger.error("empty object");
            throw new DayCareException("object is null", DayCareResultCodeEnum.OBJECT_NULL);
        }
    }

    /**
     * 字符串不为空
     * @param s 字符串
     */
    public static void StringNotEmpty(String s) {
        if(!StringUtils.isNotEmpty(s)) {
            logger.error("empty string");
            throw new DayCareException("String is empty", DayCareResultCodeEnum.DATA_INVALID);
        }
    }

    /**
     * 对象不为空
     * @param object 对象
//     * @param message
     */
    public static void AssertNotNull(Object object, String message) {
        if(object == null) {
            logger.error("empty object");
            throw new DayCareException(message, DayCareResultCodeEnum.OBJECT_NULL);
        }
    }

    /**
     * 字符串不为空
     * @param s 字符串
     */
    public static void StringNotEmpty(String s, String message) {
        if(!StringUtils.isNotEmpty(s)) {
            logger.error("empty string");
            throw new DayCareException(message, DayCareResultCodeEnum.DATA_INVALID);
        }
    }

}
