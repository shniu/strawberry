package info.chaintech.strawberry.beans;

import info.chaintech.strawberry.beans.annotation.Inject;
import info.chaintech.strawberry.core.ClassHelper;
import info.chaintech.strawberry.core.exception.InitializationError;
import org.apache.commons.collections.CollectionUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 初始化 IoC 容器
 * <p>
 * Created by shniu on 2018/10/27.
 */
public class IoCHelper {

    static {
        try {
            // 获取所有的Bean类
            Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
            for (Map.Entry<Class<?>, Object> entry : beanMap.entrySet()) {
                // 得到 Bean 类与 Bean 实例
                Class<?> beanClass = entry.getKey();
                Object beanInstance = entry.getValue();

                // 获取 bean 中所有的字段
                Field[] beanFields = beanClass.getDeclaredFields();
                List<Field> beanFieldList = new ArrayList<>(beanFields.length);
                Collections.addAll(beanFieldList, beanFields);
                beanFieldList.forEach(field -> {
                    // 判断 Field 是否被 Inject 注解修饰
                    if (field.isAnnotationPresent(Inject.class)) {
                        // Bean 字段对应的接口
                        Class<?> interfaceClass = field.getType();
                        // 通过接口获取接口对应的实现类
                        Class<?> implementClass = findImplementClass(interfaceClass);

                        // 若存在实现类
                        if (null != implementClass) {
                            Object implementInstance = beanMap.get(implementClass);
                            if (implementInstance != null) {
                                field.setAccessible(true);
                                try {
                                    field.set(beanInstance, implementInstance);
                                } catch (IllegalAccessException e) {
                                    throw new InitializationError("依赖注入失败！类名：" + beanClass.getSimpleName() + "，字段名：" + interfaceClass.getSimpleName(), e);
                                }
                            } else {
                                throw new InitializationError("依赖注入失败！类名：" + beanClass.getSimpleName() + "，字段名：" + interfaceClass.getSimpleName());
                            }
                        }
                    }
                });
            }
        } catch (Exception e) {
            throw new InitializationError("初始化 IocHelper 出错！", e);
        }
    }

    /**
     * 查找实现类
     *
     * @param interfaceClass 接口类
     * @return 实现类
     */
    private static Class<?> findImplementClass(Class<?> interfaceClass) {
        // todo 还可以扩展使用特定的实现类
        Class<?> implementClass = interfaceClass;
        List<Class<?>> classListBySuper = ClassHelper.getClassListBySuper(interfaceClass);
        if (CollectionUtils.isNotEmpty(classListBySuper)) {
            // 返回第一个
            implementClass = classListBySuper.get(0);
        }
        return implementClass;
    }
}
