package info.chaintech.strawberry.ioc;

import info.chaintech.strawberry.ioc.bean.BeanDefine;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by shniu on 2018/11/11.
 */
public class DefaultIoCTest {

    private static final Logger logger = LoggerFactory.getLogger(DefaultIoCTest.class);


    @Before
    public void setUp() {
    }

    @Test
    public void testIoC() throws Exception {
        // 1. Scan and read
        // 2. Auto inject
        Injector injector = IoC.createInjector("info.chaintech.strawberry.example");
        Map<String, BeanDefine> beans = injector.getBeans();
    }


}