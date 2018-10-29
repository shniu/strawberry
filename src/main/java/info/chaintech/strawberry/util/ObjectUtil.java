package info.chaintech.strawberry.util;

/**
 * 对象操作工具类
 * <p>
 * Created by shniu on 2018/10/27.
 */
public class ObjectUtil {

    @SuppressWarnings("unchecked")
    public static <T> T newInstance(String className) {
        T instance;
        try {
            Class<?> commandClass = ClassUtil.loadClass(className);
            instance = (T) commandClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return instance;
    }
}
