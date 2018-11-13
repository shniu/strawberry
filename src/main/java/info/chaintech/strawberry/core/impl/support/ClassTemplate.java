package info.chaintech.strawberry.core.impl.support;

import info.chaintech.strawberry.util.ClassUtil;
import info.chaintech.strawberry.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 获取类的模板类
 * <p>
 * Created by Administrator on 2018/11/3 0003.
 */
public abstract class ClassTemplate {

    private static final Logger logger = LoggerFactory.getLogger(ClassTemplate.class);

    protected final String packageName;

    protected ClassTemplate(String packageName) {
        this.packageName = packageName;
    }

    public final List<Class<?>> getClassList() {
        List<Class<?>> classList = new ArrayList<Class<?>>();

        try {
            // 从包名获取所有相关的资源
            Enumeration<URL> urls = ClassUtil.getClassLoader().getResources(packageName.replace(".", "/"));

            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    // the protocol to use, jar or file etc.
                    String protocol = url.getProtocol();

                    // class 目录中
                    if ("file".equals(protocol)) {
                        String packagePath = url.getPath().replaceAll("%20", " ");
                        // 执行添加类操作
                        addClass(classList, packagePath, packageName);
                    }
                    // 若在 jar 包中，则解析 jar 包中的 entry
                    else if ("jar".equals(protocol)) {
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        JarFile jarFile = jarURLConnection.getJarFile();
                        Enumeration<JarEntry> jarEntries = jarFile.entries();
                        while (jarEntries.hasMoreElements()) {
                            JarEntry jarEntry = jarEntries.nextElement();
                            String jarEntryName = jarEntry.getName();
                            // 判断 entry 是否为 class
                            if (jarEntryName.endsWith(".class")) {
                                // 获取类名
                                String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                // 执行添加类操作
                                doAddClass(classList, className);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            logger.error("获取类出错！", e);
        }

        return classList;
    }

    private void doAddClass(List<Class<?>> classList, String className) {
        // 加载类
        Class<?> cls = ClassUtil.loadClass(className, false);

        // 是否可以添加类
        if (checkAddClass(cls)) {
            classList.add(cls);
        }
    }

    private void addClass(List<Class<?>> classList, String packagePath, String packageName) {
        // 获取包名路径下的 class 文件或目录
        File[] files = new File(packagePath)
                .listFiles(file -> (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory());

        // 遍历文件或目录
        for (File file : files) {
            String filename = file.getName();
            // 判断是否为文件或目录
            if (file.isFile()) {
                String classname = filename.substring(0, filename.lastIndexOf("."));
                if (StringUtil.isNotEmpty(packageName)) {
                    classname = packageName + "." + classname;
                }
                doAddClass(classList, classname);
            } else {
                // 子包
                String subPackagePath = filename;
                if (StringUtil.isNotEmpty(packagePath)) {
                    subPackagePath = packagePath + "/" + subPackagePath;
                }

                String subPackageName = filename;
                if (StringUtil.isNotEmpty(packageName)) {
                    subPackageName = packageName + "." + subPackageName;
                }

                // 递归查找
                addClass(classList, subPackagePath, subPackageName);
            }
        }
    }

    /**
     * 验证是否允许添加类
     */
    public abstract boolean checkAddClass(Class<?> cls);

}
