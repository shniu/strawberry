package info.chaintech.strawberry.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by shniu on 2018/10/26.
 */
public class StringUtil {

    /**
     * 判断字符串是否为空
     *
     * @param str 字符串
     * @return 是否空
     */
    public static boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
