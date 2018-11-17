package info.chaintech.strawberry.ioc.reader;


import info.chaintech.strawberry.ioc.bean.ClassDesc;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Set;

/**
 * Classpath class reader
 * <p>
 * Created by shniu on 2018/11/13.
 */
@Slf4j
public class ClassPathClassReader extends AbstractClassReader {

    @Override
    public Set<ClassDesc> findClassByPackage(String packageName, URL url, Class<?> parent, Class<? extends Annotation> annotation, boolean recursive, Set<ClassDesc> classes) throws ClassNotFoundException, URISyntaxException {
        String packagePath = new URI(url.getFile()).getPath();
        return getClassDescs(packageName, packagePath, parent, annotation, recursive, classes);
    }

    private Set<ClassDesc> getClassDescs(String packageName, String packagePath, Class<?> parent, Class<? extends Annotation> annotation, boolean recursive, Set<ClassDesc> classes) throws ClassNotFoundException, URISyntaxException {
        File dir = new File(packagePath);

        if ((!dir.exists()) || (!dir.isDirectory())) {
            log.warn("Package {} not found", packageName);
        }

        File[] dirAndFiles = accept(dir, recursive);
        // loop files
        if (null != dirAndFiles && dirAndFiles.length > 0) {
            for (File file : dirAndFiles) {
                if (file.isDirectory()) {
                    getClassDescs(packageName + "." + file.getName(), file.getAbsolutePath(), parent, annotation, recursive, classes);
                } else {
                    // Get the class name
                    String className = file.getName().substring(0, file.getName().lastIndexOf("."));

                    // load the class
                    String classFullName = packageName + "." + className;
                    Class<?> cls = Class.forName(classFullName);

                    if (parent != null && annotation != null) {
                        if (cls.getSuperclass() != null && cls.getSuperclass().equals(parent) && cls.getAnnotation(annotation) != null) {
                            classes.add(new ClassDesc(cls));
                        }

                        continue;
                    }

                    if (parent != null) {
                        if (cls.getSuperclass() != null && cls.getSuperclass().equals(parent)) {
                            classes.add(new ClassDesc(cls));
                        } else {
                            if (cls.getInterfaces() != null && cls.getInterfaces().length > 0 && cls.getInterfaces()[0].equals(parent)) {
                                classes.add(new ClassDesc(cls));
                            }
                        }
                    }

                    if (annotation != null) {
                        if (cls.getAnnotation(annotation) != null) {
                            classes.add(new ClassDesc(cls));
                        }

                        continue;
                    }

                    // When not specify the parent or annotation
                    classes.add(new ClassDesc(cls));
                }
            }
        }

        return classes;
    }

    /**
     * Filter the file rules
     *
     * @param file      files
     * @param recursive recursive
     * @return match the rules
     */
    private File[] accept(File file, final boolean recursive) {
        return file.listFiles(file1 -> (recursive && file1.isDirectory()) || file1.getName().endsWith(".class"));
    }
}
