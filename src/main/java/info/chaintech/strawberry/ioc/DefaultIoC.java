package info.chaintech.strawberry.ioc;

import info.chaintech.strawberry.ioc.bean.BeanDefine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * IoC 容器的一个默认实现
 *
 * Created by shniu on 2018/11/11.
 */

public class DefaultIoC implements IoC {

    private Logger logger = LoggerFactory.getLogger(DefaultIoC.class);

    /**
     * Bean pool，实际存放 bean 及其定义的地方
     */
    private final Map<String, BeanDefine> beanPool = new HashMap<>();

    public Map<String, BeanDefine> getBeanPool() {
        return beanPool;
    }

    @Override
    public void addBean(Object bean) {
        addBean(bean.getClass().getName(), bean);
    }

    @Override
    public void addBean(String name, Object bean) {
        BeanDefine beanDefine = new BeanDefine(bean);
        addBean(name, beanDefine);

        // add interface
        Class<?>[] interfaces = beanDefine.getType().getInterfaces();
        for (Class<?> interfaceClz : interfaces) {
            this.addBean(interfaceClz.getName(), beanDefine);
        }
    }

    @Override
    public <T> T addBean(Class<T> type) {
        Object bean = addBean(type, true);
        return type.cast(bean);
    }

    @Override
    public <T> T getBean(Class<T> type) {
        return null;
    }

    private Object addBean(Class<?> type, boolean singleton) {
        return addBean(type.getName(), type, singleton);
    }

    private Object addBean(String name, Class<?> type, boolean singleton) {
        BeanDefine beanDefine = getBeanDefine(type, true);

        if (beanPool.put(name, beanDefine) != null) {
            logger.warn("Duplicated Bean: {}", name);
        }

        // add interface
        Class<?>[] interfaces = type.getInterfaces();
        for (Class<?> interfaceClz : interfaces) {
            // 已经存在bean了，就ignore
            if (this.getBean(interfaceClz) != null) {
                break;
            }

            this.addBean(interfaceClz.getName(), beanDefine);
        }

        return beanDefine.getBean();
    }

    private BeanDefine getBeanDefine(Class<?> type, boolean singleton) {
        try {
            Object bean = type.newInstance();
            return new BeanDefine(bean, type, singleton);
        } catch (InstantiationException| IllegalAccessException e) {
            logger.error("Class newInstance error", e);
        }
        return null;
    }

    private void addBean(String name, BeanDefine beanDefine) {
        if (beanPool.put(name, beanDefine) != null) {
            logger.warn("Duplicated Bean: {}", name);
        }
    }
}
