package info.chaintech.strawberry.ioc.reader;

import info.chaintech.strawberry.ioc.ClassReader;
import info.chaintech.strawberry.ioc.bean.ClassDesc;
import info.chaintech.strawberry.ioc.bean.Scanner;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Named;
import java.util.Set;

/**
 * Class path class reader test
 * <p>
 * Created by Administrator on 2018/11/15 0015.
 */

@Slf4j
public class ClassPathClassReaderTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void read() throws Exception {

        Scanner scanner = Scanner.builder()
                .packageName("info.chaintech.strawberry.example")
                .recursive(true)
                .annotation(Named.class)
                .build();

        ClassReader classReader = new ClassPathClassReader();
        Set<ClassDesc> classes = classReader.read(scanner);

    }

    @Test
    public void testReadJar() {
        Scanner scanner = Scanner.builder()
                .packageName("org.slf4j.impl")
                .recursive(true)
                .build();
        ClassReader classReader = new JarClassReader();
        Set<ClassDesc> classes = classReader.read(scanner);
        log.info(classes.toString());
    }

}