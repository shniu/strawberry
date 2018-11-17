package info.chaintech.strawberry.ioc.reader;


import info.chaintech.strawberry.ioc.bean.ClassDesc;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Jar class reader
 * <p>
 * Created by shniu on 2018/11/13.
 */
@Slf4j
public class JarClassReader extends AbstractClassReader {
    private static final String CLASS_SUFFIX = ".class";


    @Override
    protected Set<ClassDesc> findClassByPackage(String packageName, URL url, Class<?> parent, Class<? extends Annotation> annotation, boolean recursive, Set<ClassDesc> classes)
            throws ClassNotFoundException, URISyntaxException {

        try {

            if ("jar".equals(url.getProtocol())) {
                JarFile jarFile = ((JarURLConnection) url.openConnection()).getJarFile();

                Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements()) {
                    JarEntry jarEntry = entries.nextElement();

                    if (jarEntry.isDirectory()) {
                        continue;
                    }

                    String name = jarEntry.getName();

                    // filter the inner class
                    if (name.contains("$")) {
                        continue;
                    }

                    if (name.endsWith(CLASS_SUFFIX)) {
                        String className = name.substring(0, name.lastIndexOf("."));
                        String classPackageName = className.replace("/", ".");
                        Class<?> cls = Class.forName(classPackageName);

                        if (parent != null && annotation != null) {
                            if (cls.getSuperclass() != null && cls.getSuperclass().equals(parent) && cls.getAnnotation(annotation) != null) {
                                classes.add(new ClassDesc(cls));
                            }

                            continue;
                        }

                        if (parent != null) {
                            if (cls.getSuperclass() != null && cls.getSuperclass().equals(parent)) {
                                classes.add(new ClassDesc(cls));
                            }
                            continue;
                        }

                        if (annotation != null) {
                            if (cls.getAnnotation(annotation) != null) {
                                classes.add(new ClassDesc(cls));
                            }

                            continue;
                        }
                        classes.add(new ClassDesc(cls));
                    }

                }
            }

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return classes;
    }
}
