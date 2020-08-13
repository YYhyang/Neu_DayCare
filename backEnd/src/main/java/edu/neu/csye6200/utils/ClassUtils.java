package edu.neu.csye6200.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotEmpty;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.SynthesizingMethodParameter;
import org.springframework.web.method.HandlerMethod;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * 类工具类
 *
 * @author arronshentu
 */
@UtilityClass
@Slf4j
public class ClassUtils extends org.springframework.util.ClassUtils {

  private final String SETTER_PREFIX = "set";

  private final String GETTER_PREFIX = "get";

  private final ParameterNameDiscoverer PARAMETERNAMEDISCOVERER = new DefaultParameterNameDiscoverer();

  /**
   * 获取方法参数信息
   *
   * @param constructor
   *          构造器
   * @param parameterIndex
   *          参数序号
   * @return {MethodParameter}
   */
  public MethodParameter getMethodParameter(Constructor<?> constructor, int parameterIndex) {
    MethodParameter methodParameter = new SynthesizingMethodParameter(constructor, parameterIndex);
    methodParameter.initParameterNameDiscovery(PARAMETERNAMEDISCOVERER);
    return methodParameter;
  }

  /**
   * 获取方法参数信息
   *
   * @param method
   *          方法
   * @param parameterIndex
   *          参数序号
   * @return {MethodParameter}
   */
  public MethodParameter getMethodParameter(Method method, int parameterIndex) {
    MethodParameter methodParameter = new SynthesizingMethodParameter(method, parameterIndex);
    methodParameter.initParameterNameDiscovery(PARAMETERNAMEDISCOVERER);
    return methodParameter;
  }

  /**
   * 循环向上转型, 获取对象的DeclaredField, 并强制设置为可访问. 如向上转型到Object仍无法找到, 返回null.
   */
  public Field getAccessibleField(final Object obj, final String fieldName) {
    return getAccessibleField(obj.getClass(), fieldName);
  }

  /**
   * 改变private/protected的成员变量为public，尽量不调用实际改动的语句，避免JDK的SecurityManager抱怨。
   */
  public void makeAccessible(Field field) {
    boolean flag =
      (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())
        || Modifier.isFinal(field.getModifiers())) && !field.isAccessible();
    if (flag) {
      field.setAccessible(true);
    }
  }

  /**
   * 循环向上转型, 获取对象的DeclaredField, 并强制设置为可访问. 如向上转型到Object仍无法找到, 返回null.
   */
  public Field getAccessibleField(@NonNull final Class<?> clz, @NotEmpty final String fieldName) {
    for (Class<?> superClass = clz; superClass != Object.class; superClass = superClass.getSuperclass()) {
      try {
        Field field = superClass.getDeclaredField(fieldName);
        makeAccessible(field);
        return field;
      } catch (NoSuchFieldException e) {// NOSONAR
        // Field不在当前类定义,继续向上转型
        continue;// new add
      }
    }
    return null;
  }

  /**
   * 获取Annotation
   *
   * @param clz
   * @param pName
   * @param annotationClass
   * @param <A>
   * @return
   */
  public <A extends Annotation> A findAnnotation(Class<?> clz, String pName, Class<A> annotationClass) {
    Class<?> temp = clz;

    A an = null;
    while (an == null) {
      try {
        an = temp.getDeclaredField(pName).getAnnotation(annotationClass);
      } catch (Exception ignored) {
      }
      try {
        if (an == null) {
          an = temp.getDeclaredMethod(StrUtil.genGetter(pName)).getAnnotation(annotationClass);
        }
      } catch (Exception ignored) {
      }
      if (temp != null && !(temp.getClass().getName().equals(Object.class.getName()))) {
        temp = temp.getSuperclass();
      } else {
        break;
      }
    }

    return an;
  }

  /**
   * 获取Annotation
   *
   * @param method
   *          Method
   * @param annotationType
   *          注解类
   * @param <A>
   *          泛型标记
   * @return {Annotation}
   */
  public <A extends Annotation> A getAnnotation(Method method, Class<A> annotationType) {
    Class<?> targetClass = method.getDeclaringClass();
    // The method may be on an interface, but we need attributes from the target class.
    // If the target class is null, the method will be unchanged.
    Method specificMethod = ClassUtils.getMostSpecificMethod(method, targetClass);
    // If we are dealing with method with generic parameters, find the original method.
    specificMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);
    // 先找方法，再找方法上的类
    A annotation = AnnotatedElementUtils.findMergedAnnotation(specificMethod, annotationType);
    if (null != annotation) {
      return annotation;
    }
    // 获取类上面的Annotation，可能包含组合注解，故采用spring的工具类
    return AnnotatedElementUtils.findMergedAnnotation(specificMethod.getDeclaringClass(), annotationType);
  }

  /**
   * 获取Annotation
   *
   * @param handlerMethod
   *          HandlerMethod
   * @param annotationType
   *          注解类
   * @param <A>
   *          泛型标记
   * @return {Annotation}
   */
  public <A extends Annotation> A getAnnotation(HandlerMethod handlerMethod, Class<A> annotationType) {
    // 先找方法，再找方法上的类
    A annotation = handlerMethod.getMethodAnnotation(annotationType);
    if (null != annotation) {
      return annotation;
    }
    // 获取类上面的Annotation，可能包含组合注解，故采用spring的工具类
    Class<?> beanType = handlerMethod.getBeanType();
    return AnnotatedElementUtils.findMergedAnnotation(beanType, annotationType);
  }

  /**
   * 创建对象，注入指定属性值
   *
   * @param clz
   * @param fields
   * @param value
   * @param <T>
   * @return
   */
  @SuppressWarnings("unchecked")
  public <T> T createObj(Class<T> clz, List<String> fields, Object... value) {
    Object obj = null;
    try {
      obj = updateObj(clz.newInstance(), fields, value);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return (T)obj;
  }

  /**
   * 更新对象，注入指定属性值
   *
   * @param obj
   * @param fields
   * @param value
   * @return
   */
  @SuppressWarnings("unchecked")
  public <T> T updateObj(@NotEmpty Object obj, @NotEmpty List<String> fields, Object... value) {
    try {
      if (obj != null) {
        if (value.length == fields.size()) {
          for (int i = 0; i < fields.size(); i++) {
            BeanUtil.setFieldValue(obj, fields.get(i), value[i]);
          }
        } else {
          log.warn("obj {} fields {} value {}", obj, fields, value);
        }
      } else {
        log.warn("obj is null");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return (T)obj;
  }

  /**
   * 调用Getter方法. 支持多级，如：对象名.对象名.方法
   */
  public Object invokeGetter(Object obj, String propertyName) {
    if (obj instanceof Map) {
      return ((Map)obj).get(propertyName);
    }
    Object object = obj;
    for (String name : StringUtils.split(propertyName, ".")) {
      object = ReflectUtil.invoke(object, GETTER_PREFIX + StrUtil.upperFirst(name));
    }
    return object;
  }

  /**
   * 调用Setter方法, 仅匹配方法名。 支持多级，如：对象名.对象名.方法
   */
  public void invokeSetter(Object obj, String propertyName, Object value) {
    Object object = obj;
    String[] names = StringUtils.split(propertyName, ".");
    for (int i = 0; i < names.length; i++) {
      if (i < names.length - 1) {
        object = ReflectUtil.invoke(object, GETTER_PREFIX + StrUtil.upperFirst(names[i]), value);
      } else {
        ReflectUtil.invoke(object, SETTER_PREFIX + StrUtil.upperFirst(names[i]), value);
      }
    }
  }

}
