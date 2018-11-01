package info.chaintech.strawberry.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2018/11/1 0001.
 */


public class PropsUtilTest {

    private static final Logger logger = LoggerFactory.getLogger(PropsUtilTest.class);

    @Test
    public void loadProps() throws Exception {
        PropsUtil.loadProps("");
    }

}