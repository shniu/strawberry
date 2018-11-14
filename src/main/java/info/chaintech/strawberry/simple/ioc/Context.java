package info.chaintech.strawberry.simple.ioc;

import info.chaintech.strawberry.simple.ioc.bean.ClassInfo;
import info.chaintech.strawberry.simple.ioc.reader.ClassPathClassReader;
import info.chaintech.strawberry.simple.ioc.reader.JarClassReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.stream.Stream;

/**
 * Get ClassReader by jar or file
 * <p>
 * Created by shniu on 2018/11/13.
 */
public final class Context {

    private static final Logger logger = LoggerFactory.getLogger(Context.class);

    private static final ClassReader classpathClassReader = new ClassPathClassReader();
    private static final ClassReader jarClassReader = new JarClassReader();

    private static boolean isJarContext = false;

    /**
     * 在指定包下，递归查找类
     *
     * @param packageName 包名
     * @return
     */
    public static Stream<ClassInfo> recursionFindClasses(String packageName) {
//        Scanner.
    }

    /**
     * 获取Class Reader
     *
     * @param packageName 包名
     * @return
     */
    public static ClassReader getClassReader(String packageName) {

        if (isJarPackage(packageName)) {
            return jarClassReader;
        }

        return classpathClassReader;
    }

    /**
     * 判断跟定的包路径是不是在jar包中
     *
     * @param packageName
     * @return
     */
    public static boolean isJarPackage(String packageName) {

        if (packageName == null || "".equals(packageName.trim())) {
            return false;
        }

        try {
            packageName = packageName.replace(".", "/");
            Enumeration<URL> resources = Context.class.getClassLoader().getResources(packageName);
            if (resources.hasMoreElements()) {
                String url = resources.nextElement().toString();
                return url.contains(".jar!") || url.contains(".zip!");
            }
        } catch (IOException e) {
            logger.error("", e);
        }

        return false;
    }

    public static boolean isJarContext() {
        return isJarContext;
    }

}
