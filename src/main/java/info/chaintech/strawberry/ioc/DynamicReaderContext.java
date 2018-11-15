package info.chaintech.strawberry.ioc;


import info.chaintech.strawberry.ioc.reader.ClassPathClassReader;

/**
 * Created by Administrator on 2018/11/15 0015.
 */
public class DynamicReaderContext {

    /**
     * Get class reader by the package name
     *
     * @param packageName package name
     * @return {Ref @ClassReader}
     */
    public static ClassReader getClassReader(String packageName) {
        return new ClassPathClassReader();
    }
}
