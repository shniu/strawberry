package info.chaintech.strawberry.core.impl.support;

/**
 * Created by Administrator on 2018/11/5 0005.
 */
public abstract class SuperClassTemplate extends ClassTemplate {

    protected final Class<?> superClass;

    protected SuperClassTemplate(String packageName, Class<?> superClass) {
        super(packageName);
        this.superClass = superClass;
    }
}
