package info.chaintech.strawberry.ioc;

import info.chaintech.strawberry.ioc.bean.BeanDefine;
import org.junit.Assert;
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

    private IoC ioc;

    @Before
    public void setUp() {
        ioc = new DefaultIoC();
    }

    @Test
    public void addBean() throws Exception {
        ioc.addBean(ioc);
        Map<String, BeanDefine> beanPool = ioc.getBeanPool();
        Assert.assertTrue(beanPool.size() >= 2);
    }

    @Test
    public void addBean1() throws Exception {
        ioc.addBean("custom", ioc);
        BeanDefine beanDefine = ioc.getBeanPool().get("custom");
        logger.info(beanDefine.toString());
        Assert.assertNotNull(beanDefine);
    }

}