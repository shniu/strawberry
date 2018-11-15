package info.chaintech.strawberry.ioc.bean;

/**
 * Bean define
 * Created by Administrator on 2018/11/15 0015.
 */

public class BeanDefine {

    private Class<?> type;
    private Object bean;
    private boolean singleton;

    public BeanDefine(Object bean) {
        this(bean, bean.getClass());
    }

    public BeanDefine(Object bean, Class<?> type) {
        this.bean = bean;
        this.type = type;
        this.singleton = true;
    }

    public BeanDefine(Class<?> type, Object bean, boolean singleton) {
        this.type = type;
        this.bean = bean;
        this.singleton = singleton;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public boolean isSingleton() {
        return singleton;
    }

    public void setSingleton(boolean singleton) {
        this.singleton = singleton;
    }

    @Override
    public String toString() {
        return "BeanDefine{" +
                "type=" + type +
                ", bean=" + bean +
                ", singleton=" + singleton +
                '}';
    }
}
