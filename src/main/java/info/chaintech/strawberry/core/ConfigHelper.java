package info.chaintech.strawberry.core;

import info.chaintech.strawberry.FrameworkConstant;
import info.chaintech.strawberry.util.PropsUtil;

import java.util.Properties;

/**
 * 读取框架配置文件的配置项
 * <p>
 * Created by shniu on 2018/10/26.
 */
public class ConfigHelper {

    /**
     * 属性配置文件对象
     */
    private static final Properties configProps = PropsUtil.loadProps(FrameworkConstant.CONFIG_PROPS);

    public static String getString(String key) {
        // todo
        return "";
    }
}
