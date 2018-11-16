package info.chaintech.strawberry.test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2018/11/16 0016.
 */
public class ClassLoaderTest {


    public static void main(String[] args) throws Exception {
        ClassLoader myClassLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {

                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream is = getClass().getResourceAsStream(fileName);
                if (is == null) {
                    return super.loadClass(name);
                }

                try {
                    byte[] bytes = new byte[is.available()];
                    is.read(bytes);
                    return defineClass(name, bytes, 0, bytes.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object obj = myClassLoader.loadClass("info.chaintech.strawberry.test.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj.getClass().getClassLoader());
        System.out.println(obj.getClass().getClassLoader().getParent());
        System.out.println(obj.getClass().getClassLoader().getParent().getParent());
        System.out.println(obj.getClass().getClassLoader().getParent().getParent().getParent());
        System.out.println(obj instanceof info.chaintech.strawberry.test.ClassLoaderTest);

    }

}
