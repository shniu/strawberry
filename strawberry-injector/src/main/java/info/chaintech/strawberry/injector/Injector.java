package info.chaintech.strawberry.injector;

import javax.inject.Inject;
import javax.inject.Qualifier;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.function.Consumer;

/**
 * Injector
 * <p>
 * Created by Administrator on 2018/11/17 0017.
 *
 * Ref:
 *   - https://github.com/pyloque/iockids/blob/master/src/main/java/iockids/Injector.java
 *   - https://zhuanlan.zhihu.com/p/36311670
 */
public class Injector {
    // Bean instance container
    private Map<Class<?>, Object> singletonBeans = Collections.synchronizedMap(new HashMap<>());

    {
        singletonBeans.put(Injector.class, this);
    }

    private Map<Class<?>, Map<Annotation, Object>> qualifieds = Collections.synchronizedMap(new HashMap<>());

    // The qualified category singleton class that has not been initialized is placed here
    private Map<Class<?>, Map<Annotation, Class<?>>> qualifiedClasses = Collections.synchronizedMap(new HashMap<>());

    // Ready class to be initialized
    private Set<Class<?>> readyClasses = Collections.synchronizedSet(new HashSet<>());


    public <T> Injector registerQualifiedClass(Class<?> parentType, Class<T> clazz) {

        for (Annotation annotation : clazz.getAnnotations()) {
            if (annotation.annotationType().isAnnotationPresent(Qualifier.class)) {
                return registerQualifiedClass(parentType, annotation, clazz);
            }
        }

        throw new InjectorException("class should decorated with annotation tagged by Qualifier");
    }

    private <T> Injector registerQualifiedClass(Class<?> parentType, Annotation annotation, Class<T> clazz) {

        if (!annotation.annotationType().isAnnotationPresent(Qualifier.class)) {
            throw new InjectorException("annotation must be decorated with Qualifier " + annotation.annotationType().getCanonicalName());
        }

        Map<Annotation, Class<?>> annotationClassMap = qualifiedClasses.computeIfAbsent(parentType, k -> Collections.synchronizedMap(new HashMap<>()));
        if (annotationClassMap.put(annotation, clazz) != null) {
            throw new InjectorException(String.format("duplicated qualifier %s with the same class %s",
                    annotation.annotationType().getCanonicalName(), parentType.getCanonicalName()));
        }

        return this;
    }

    /**
     * Get bean
     *
     * @param clazz bean's class type
     */
    public <T> T getInstance(Class<T> clazz) {
        return createNew(clazz);
    }

    public <T> T createNew(Class<T> clazz) {
        return createNew(clazz, null);
    }

    @SuppressWarnings("unchecked")
    public <T> T createNew(Class<T> clazz, Consumer<T> consumer) {
        Object o = singletonBeans.get(clazz);
        if (o != null) {
            return (T) o;
        }

        T target = null;
        ArrayList<Constructor<T>> constructors = new ArrayList<>();
        for (Constructor constructor : clazz.getDeclaredConstructors()) {
            if (!constructor.isAnnotationPresent(Inject.class) && constructor.getParameterCount() > 0) {
                continue;
            }
            /*if (constructor.setAccessible();) {
                continue;
            }*/
            constructors.add((Constructor<T>) constructor);
        }

        if (constructors.size() > 1) {
            throw new InjectorException("dupcated constructor for injection class " + clazz.getCanonicalName());
        }
        if (constructors.size() == 0) {
            throw new InjectorException("no accessible constructor for injection class " + clazz.getCanonicalName());
        }

        readyClasses.add(clazz);

        // constructor inject
        target = createFromConstructor(constructors.get(0));

        return target;
    }

    private <T> T createFromConstructor(Constructor<T> constructor) {
        Object[] params = new Object[constructor.getParameterCount()];
        int i = 0;
        for (Parameter parameter : constructor.getParameters()) {
            if (readyClasses.contains(parameter.getType())) {
                throw new InjectorException(
                        String.format("circular dependency on constructor , the root class is %s", constructor.getDeclaringClass().getCanonicalName())
                );
            }

            Object param = createFromParameter(parameter);
            if (param == null) {
                throw new InjectorException(String.format("parameter should not be empty with name %s of class %s",
                        parameter.getName(), constructor.getDeclaringClass().getCanonicalName()));
            }

            params[i++] = param;
        }

        try {
            return constructor.newInstance(params);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new InjectorException("create instance from constructor error", e);
        }
    }

    private <T> T createFromParameter(Parameter parameter) {
        Class<?> parameterType = parameter.getType();
        T t = createFromQualified(parameter.getDeclaringExecutable().getDeclaringClass(), parameterType, parameter.getAnnotations());

        if (t != null) {
            return t;
        }

        return createNew((Class<T>) parameterType);
    }

    private <T> T createFromQualified(Class<?> declaringClass, Class<?> clazz, Annotation[] annotations) {

        Map<Annotation, Object> annotationObjectMap = qualifieds.get(clazz);
        if (annotationObjectMap != null) {

        }

        return null;
    }


}
