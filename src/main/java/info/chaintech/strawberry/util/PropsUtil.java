package info.chaintech.strawberry.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
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

        InputStream inputStream = null;

        if (StringUtil.isEmpty(propsPath)) {
            throw new IllegalArgumentException();
        }

        // String suffix = ".properties";

        try {
            inputStream = ClassUtil.getClassLoader().getResourceAsStream(propsPath);

            if (inputStream != null) {
                props.load(inputStream);
            }
        } catch (IOException e) {
            logger.error("加载属性文件出错", e);
            throw new RuntimeException(e);
        } finally {

            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.error("释放资源出错", e);
            }

        }

        return props;
    }

    /**
     * 加载属性文件，并转 Map
     */
    public static Map<String, String> loadPropsToMap(String propsPath) {
        Properties properties = loadProps(propsPath);
        Map<String, String> map = new HashMap<String, String>();

        for (String key : properties.stringPropertyNames()) {
            map.put(key, properties.getProperty(key));
        }

        return map;
    }

    /**
     * 获取字符型属性
     */
    public static String getString(Properties props, String key) {
        return getString(props, key, "");
    }

    public static String getString(Properties props, String key, String defalutValue) {
        String value = defalutValue;

        if (props.containsKey(key)) {
            value = props.getProperty(key);
        }

        return value;
    }

    /**
     * 获取数值型属性
     */
    public static int getNumber(Properties props, String key) {
        return getNumber(props, key, 0);
    }

    /**
     * 获取数值型属性（带有默认值）
     */
    public static int getNumber(Properties props, String key, int defaultValue) {
        int value = defaultValue;
        if (props.containsKey(key)) {
            value = CastUtil.castInt(props.getProperty(key));
        }
        return value;
    }

    /**
     * 获取布尔型属性
     */
    public static boolean getBoolean(Properties props, String key) {
        return getBoolean(props, key, false);
    }

    /**
     * 获取布尔型属性（带有默认值）
     */
    public static boolean getBoolean(Properties props, String key, boolean defalutValue) {
        boolean value = defalutValue;
        if (props.containsKey(key)) {
            value = CastUtil.castBoolean(props.getProperty(key));
        }
        return value;
    }
}
