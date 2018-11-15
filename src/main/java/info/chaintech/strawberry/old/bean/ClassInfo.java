package info.chaintech.strawberry.old.bean;

/**
 * 类信息
 *
 * Created by shniu on 2018/11/11.
 */
public class ClassInfo {

    private String className;
    private Class<?> clazz;

    public ClassInfo(String className) {
        this.className = className;

    }

    public ClassInfo(Class<?> clazz) {
        this.className = clazz.getName();
        this.clazz = clazz;
    }

    public ClassInfo(String className, Class<?> clazz) {
        this.className = className;
        this.clazz = clazz;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return clazz.toString();
    }
}
