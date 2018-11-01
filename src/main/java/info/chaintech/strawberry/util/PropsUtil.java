package info.chaintech.strawberry.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by shniu on 2018/10/27.
 */
public class PropsUtil {
    private static final Logger logger = LoggerFactory.getLogger(PropsUtil.class);


    /**
     * 加载配置文件
     *
     * @param propsPath 文件路径
     * @return Properties
     */
    public static Properties loadProps(String propsPath) {
        Properties props = new Properties();

        InputStream inputStream;

        if (StringUtil.isEmpty(propsPath)) {
            throw new IllegalArgumentException();
        }

        String suffix = ".properties";

        return props;
    }
}
