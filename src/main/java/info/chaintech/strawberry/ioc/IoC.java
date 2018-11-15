package info.chaintech.strawberry.ioc;

/**
 * IoC entrance
 * <p>
 * Created by Administrator on 2018/11/15 0015.
 */
public final class IoC {

    /**
     * Create a injector with the package
     *
     * @param packageName package name
     * @return The injector that's already initialized
     */
    public static Injector createInjector(String packageName) {
        return new InjectorCreator(packageName).build();
    }
}
