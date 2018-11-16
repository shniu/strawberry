package info.chaintech.strawberry.ioc.reader;


import info.chaintech.strawberry.ioc.ClassReader;
import info.chaintech.strawberry.ioc.bean.ClassDesc;
import info.chaintech.strawberry.ioc.bean.Scanner;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * Classpath class reader
 * <p>
 * Created by shniu on 2018/11/13.
 */
@Slf4j
public class ClassPathClassReader implements ClassReader {

    @Override
    public Set<ClassDesc> read(Scanner scanner) {

        return getClassByAnnotation(scanner.getPackageName(), scanner.getParent(), scanner.getAnnotation(), scanner.isRecursive());
    }

    public Set<ClassDesc> getClassByAnnotation(String packageName,
                                               Class<?> parent,
                                               Class<? extends Annotation> annotation,
                                               boolean recursive) {
        Set<ClassDesc> classes = new HashSet<>();

        // Replace the package name to dir name
        String packageDirName = packageName.replace(".", "/");

        try {
            // Get resources by class loader, return URLs
            Enumeration<URL> urls = this.getClass().getClassLoader().getResources(packageDirName);

            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                String path = new URI(url.getFile()).getPath();
                Set<ClassDesc> subClasses = findClassByPackage(packageName, path, parent, annotation, recursive, classes);
                if (subClasses != null && !subClasses.isEmpty()) {
                    classes.addAll(subClasses);
                }
            }

        } catch (IOException | URISyntaxException e) {
            log.error(e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            log.error("Add user custom view class error Can't find such Class files.");
        }

        return classes;
    }

    private Set<ClassDesc> findClassByPackage(String packageName, String packagePath, Class<?> parent, Class<? extends Annotation> annotation, boolean recursive, Set<ClassDesc> classes) throws ClassNotFoundException {

        File dir = new File(packagePath);

        if ((!dir.exists()) || (!dir.isDirectory())) {
            log.warn("Package {} not found", packageName);
        }

        File[] dirAndFiles = accept(dir, recursive);
        // loop files
        if (null != dirAndFiles && dirAndFiles.length > 0) {
            for (File file : dirAndFiles) {
                if (file.isDirectory()) {
                    findClassByPackage(packageName + "." + file.getName(), file.getAbsolutePath(), parent, annotation, recursive, classes);
                } else {
                    // Get the class name
                    String className = file.getName().substring(0, file.getName().lastIndexOf(".") - 1);  // todo test .lastIndexOf

                    // load the class
                    Class<?> cls = Class.forName(packageName + "." + className);

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

                    // ?
                    // classes.add(new ClassDesc(cls));
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
