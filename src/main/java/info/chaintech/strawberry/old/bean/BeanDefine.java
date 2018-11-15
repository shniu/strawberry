package info.chaintech.strawberry.old.bean;

/**
 * Bean 定义，描述一个 Bean
 *
 * Created by shniu on 2018/11/11.
 */
public class BeanDefine {

    private Object bean;
    private Class<?> type;
    private boolean isSingle;

    public BeanDefine(Object bean) {
        this(bean, bean.getClass());
    }

    public BeanDefine(Object bean, Class<?> type) {
        this.bean = bean;
        this.type = type;
        this.isSingle = true;
    }

    public BeanDefine(Object bean, Class<?> type, boolean isSingle) {
        this.bean = bean;
        this.type = type;
        this.isSingle = isSingle;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public boolean isSingle() {
        return isSingle;
    }

    public void setSingle(boolean single) {
        isSingle = single;
    }

    @Override
    public String toString() {
        return "BeanDefine{" +
                "bean=" + bean +
                ", type=" + type +
                ", isSingle=" + isSingle +
                '}';
    }
}
