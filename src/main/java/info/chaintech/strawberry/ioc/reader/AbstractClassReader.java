package info.chaintech.strawberry.ioc.reader;

import info.chaintech.strawberry.ioc.ClassReader;
import info.chaintech.strawberry.ioc.bean.ClassDesc;
import info.chaintech.strawberry.ioc.bean.Scanner;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * Abstract class reader, scanner all classes
 * <p>
 * Created by Administrator on 2018/11/17 0017.
 */
@Slf4j
public abstract class AbstractClassReader implements ClassReader {

    /**
     *
     * @param scanner scanner info
     * @return class sets
     */
    @Override
    public Set<ClassDesc> read(Scanner scanner) {

        // 1. get the package name
        // 2. package name replace . to /
        // 3. find all resources below the package, by ClassLoader.getResources(packageName)
        // 4. loop the URLs
        //   - url.getProtocol() -> file or jar
        //   - if file, parse file
        //   - if jar, parse jar

        // --- parse file

        // --- parse jar
        // 1. check for jar
        // 2. url.openConnection()).getJarFile()
        // 3. loop jarFile.entries()

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
                Set<ClassDesc> subClasses = findClassByPackage(packageName, url, parent, annotation, recursive, classes);
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

    protected abstract Set<ClassDesc> findClassByPackage(String packageName,
                                                         URL url,
                                                         Class<?> parent,
                                                         Class<? extends Annotation> annotation,
                                                         boolean recursive,
                                                         Set<ClassDesc> classes) throws ClassNotFoundException, URISyntaxException;

}
