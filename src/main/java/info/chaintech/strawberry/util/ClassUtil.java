package info.chaintech.strawberry.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

/**
 * Created by shniu on 2018/10/27.
 */
public class ClassUtil {

    private static final Logger logger = LoggerFactory.getLogger(ClassUtil.class);

    /**
     * 加载类
     *
     * @param className 类名称
     * @return class
     */
    public static Class<?> loadClass(String className) {
        return loadClass(className, true);
    }

    public static Class<?> loadClass(String className, boolean initialize) {
        Class<?> cls;

        try {
            cls = Class.forName(className, initialize, getClassLoader());
        } catch (ClassNotFoundException e) {
            logger.error("类加载失败", e);
            throw new RuntimeException(e);
        }

        return cls;
    }

    /**
     * 获取ClassLoader
     *
     * @return class loader
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 获取类路径
     */
    public static String getClassPath() {
        String classpath = "";
        URL resource = getClassLoader().getResource("");
        if (resource != null) {
            classpath = resource.getPath();
        }

        return classpath;
    }

    public static boolean isInt(Class<?> type) {
        return type.equals(int.class) || type.equals(Integer.class);
    }

    public static boolean isLong(Class<?> type) {
        return type.equals(long.class) || type.equals(Long.class);
    }

    public static boolean isDouble(Class<?> type) {
        return type.equals(double.class) || type.equals(Double.class);
    }

    public static boolean isString(Class<?> type) {
        return type.equals(String.class);
    }
}
