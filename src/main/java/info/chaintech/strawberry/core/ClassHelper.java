package info.chaintech.strawberry.core;

import info.chaintech.strawberry.InstanceFactory;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Class helper
 * Created by shniu on 2018/10/26.
 */
public class ClassHelper {

    private static final String basePackage = ConfigHelper.getString("strawberry.framework.base.package");
    private static ClassScanner classScanner = InstanceFactory.getClassScanner();

    /**
     * 获取指定包名下的所有类
     */
    public static List<Class<?>> getClassList() {
        return classScanner.getClassList(basePackage);
    }

    public static List<Class<?>> getClassListBySuper(Class<?> superClass) {
        return classScanner.getClassListBySuper(basePackage, superClass);
    }

    public static List<Class<?>> getClassListByAnnotation(Class<? extends Annotation> annotationClass) {
        return classScanner.getClassListByAnnotation(basePackage, annotationClass);
    }
}
