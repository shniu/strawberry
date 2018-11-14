package info.chaintech.strawberry.ioc.bean;

import java.lang.annotation.Annotation;

/**
 * 扫描类的元信息
 * <p>
 * Created by shniu on 2018/11/11.
 */
public class Scanner {

    /**
     * 要扫描的包路径
     */
    private String packageName;

    /**
     * 是否递归扫描
     */
    private boolean recursive;

    /**
     *  获取指定包名中指定父类或接口的相关类
     */
    private Class<?> parent;

    /**
     * 获取指定包名中指定注解的相关类
     */
    private Class<? extends Annotation> annotation;


    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public boolean isRecursive() {
        return recursive;
    }

    public void setRecursive(boolean recursive) {
        this.recursive = recursive;
    }

    public Class<?> getParent() {
        return parent;
    }

    public void setParent(Class<?> parent) {
        this.parent = parent;
    }

    public Class<? extends Annotation> getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Class<? extends Annotation> annotation) {
        this.annotation = annotation;
    }

    @Override
    public String toString() {
        return "Scanner{" +
                "packageName='" + packageName + '\'' +
                ", recursive=" + recursive +
                ", parent=" + parent +
                ", annotation=" + annotation +
                '}';
    }
}
