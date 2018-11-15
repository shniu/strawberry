package info.chaintech.strawberry.old;

/**
 * 注入器接口
 *
 * Created by shniu on 2018/11/11.
 */
public interface Injector {

    /**
     * 注入bean
     *
     * @param bean bean 实例
     */
    void injection(Object bean);
}
