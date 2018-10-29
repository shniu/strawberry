package info.chaintech.strawberry.core.impl;

import info.chaintech.strawberry.core.ClassScanner;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Created by shniu on 2018/10/26.
 */
public class DefaultClassScannerImpl implements ClassScanner {

    @Override
    public List<Class<?>> getClassList(String packageName) {
        return null;
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
