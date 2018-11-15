package info.chaintech.strawberry.ioc;

import info.chaintech.strawberry.ioc.injecor.InjectorImpl;

/**
 * Injector creator
 * <p>
 * Created by Administrator on 2018/11/15 0015.
 */
public final class InjectorCreator {

    /**
     * The package name to scan and read
     */
    private String packageName;

    private Injector injector = new InjectorImpl();

    /**
     * Constructor
     *
     * @param packageName package name, e.g. info.chaintech.strawberry
     */
    InjectorCreator(String packageName) {
        this.packageName = packageName;
    }

    /**
     * Build the IoC, this is the core
     *
     * @return Injector
     */
    Injector build() {

        // todo 1. scan and read classes
        ClassReader classReader = DynamicReaderContext.getClassReader(packageName);
        //classReader.read();

        // todo 2. auto inject
        // todo 3. get the container with some functional to visit the container

        return injector;
    }
}
