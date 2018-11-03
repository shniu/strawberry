package info.chaintech.strawberry.core.impl;

import info.chaintech.strawberry.core.ClassScanner;
import info.chaintech.strawberry.core.impl.support.ClassTemplate;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * 默认类扫描器
 * <p>
 * Created by shniu on 2018/10/26.
 */
public class DefaultClassScannerImpl implements ClassScanner {

    @Override
    public List<Class<?>> getClassList(String packageName) {

        return new ClassTemplate(packageName) {
            @Override
            public boolean checkAddClass(Class<?> cls) {
                return false;
            }
        }.getClassList();

    }

    @Override
    public List<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass) {
        return null;
    }

    @Override
    public List<Class<?>> getClassListBySuper(String packageName, Class<?> superClass) {
        return null;
    }
}
