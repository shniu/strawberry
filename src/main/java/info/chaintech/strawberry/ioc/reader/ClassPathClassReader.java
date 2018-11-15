package info.chaintech.strawberry.ioc.reader;


import info.chaintech.strawberry.ioc.ClassReader;
import info.chaintech.strawberry.ioc.bean.ClassDesc;
import info.chaintech.strawberry.ioc.bean.Scanner;
import lombok.extern.slf4j.Slf4j;

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
            Enumeration<URL> urls = this.getClass().getClassLoader().getResources(packageDirName);

            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                String file = url.getFile();
                String path = new URI(file).getPath();
                log.info(path);
            }

        } catch (IOException e) {
            log.error("", e);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return null;
    }
}
