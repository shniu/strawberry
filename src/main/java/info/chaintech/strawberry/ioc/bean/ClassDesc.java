package info.chaintech.strawberry.ioc.bean;

/**
 * Class info
 * <p>
 * Created by Administrator on 2018/11/15 0015.
 */
public class ClassDesc {

    private String className;
    private Class<?> cls;

    public ClassDesc(Class<?> cls) {
        this(cls.getName(), cls);
    }

    public ClassDesc(String className, Class<?> cls) {
        this.className = className;
        this.cls = cls;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Class<?> getCls() {
        return cls;
    }

    public void setCls(Class<?> cls) {
        this.cls = cls;
    }

    @Override
    public String toString() {
        return "ClassDesc{" +
                "className='" + className + '\'' +
                ", cls=" + cls +
                '}';
    }
}
