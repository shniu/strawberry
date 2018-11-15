package info.chaintech.strawberry.old;


/**
 * IoC 容器，提供了一个注册 bean 和获取 bean 的接口
 *
 * Created by shniu on 2018/11/11.
 */
public interface IoC {

    /**
     * add bean
     *
     * @param bean bean 实例
     */
    void addBean(Object bean);

    /**
     * add bean
     */
    void addBean(String name, Object bean);

    /**
     * 根据类型创建 bean，并注册到容器中
     */
    <T> T addBean(Class<T> type);

    <T> T getBean(Class<T> type);

}
