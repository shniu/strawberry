package info.chaintech.strawberry.beans;

import info.chaintech.strawberry.beans.annotation.Bean;
import info.chaintech.strawberry.core.ClassHelper;
import info.chaintech.strawberry.core.exception.InitializationError;
import info.chaintech.strawberry.tx.annotation.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shniu on 2018/10/26.
 */
public class BeanHelper {
    private static final Map<Class<?>, Object> beanMap = new HashMap<>();

    static {
        try {
            // 获取应用包路径下的所有类
            List<Class<?>> classList = ClassHelper.getClassList();
            for (Class<?> cls : classList) {
                // 处理有特定注解的类
                if (cls.isAnnotationPresent(Bean.class)
                        || cls.isAnnotationPresent(Service.class)) {
                    // 创建 Bean 实例
                    Object instance = cls.newInstance();

                    // 将 Bean 实例放入 Map 容器中
                    beanMap.put(cls, instance);
                }
            }
        } catch (Exception e) {
            throw new InitializationError("初始化 BeanHelper 出错", e);
        }
    }

    /**
     * 获取 beanMap
     *
     * @return
     */
    public static Map<Class<?>, Object> getBeanMap() {
        return beanMap;
    }

    /**
     * 获取 bean 实例
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> cls) {
        if (!beanMap.containsKey(cls)) {
            throw new RuntimeException("无法根据类名获取实例" + cls);
        }

        return (T) beanMap.get(cls);
    }

    /**
     * 设置 Bean 实例
     */
    public static void setBean(Class<?> cls, Object obj) {
        beanMap.put(cls, obj);
    }
}
