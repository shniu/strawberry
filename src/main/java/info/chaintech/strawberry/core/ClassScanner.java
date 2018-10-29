package info.chaintech.strawberry.core;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * 扫描类
 * <p>
 * Created by shniu on 2018/10/26.
 */
public interface ClassScanner {

    /**
     * 获取指定包下的所有类
     *
     * @param packageName 包名
     * @return 包下的所有类
     */
    List<Class<?>> getClassList(String packageName);

    /**
     * 获取指定包名和注解的所有类
     *
     * @param packageName     包名
     * @param annotationClass 注解类
     * @return 指定包名和注解的所有类
     */
    List<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass);

    /**
     * 获取指定包名中指定父类或接口的相关类
     *
     * @param packageName 包名
     * @param superClass  父类或接口
     * @return 指定包名中指定父类或接口的相关类
     */
    List<Class<?>> getClassListBySuper(String packageName, Class<?> superClass);
}
