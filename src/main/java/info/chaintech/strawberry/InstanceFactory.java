package info.chaintech.strawberry;

import info.chaintech.strawberry.core.ClassScanner;
import info.chaintech.strawberry.core.ConfigHelper;
import info.chaintech.strawberry.core.impl.DefaultClassScannerImpl;
import info.chaintech.strawberry.util.ObjectUtil;
import info.chaintech.strawberry.util.StringUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by shniu on 2018/10/26.
 */
public class InstanceFactory {

    /**
     * 缓存实例
     */
    private static final Map<String, Object> cache = new ConcurrentHashMap<>();

    private static final String CLASS_SCANNER = "strawberry.framework.custom.class_scanner";

    public static ClassScanner getClassScanner() {
        return getInstance(CLASS_SCANNER, DefaultClassScannerImpl.class);
    }

    @SuppressWarnings("unchecked")
    private static <T> T getInstance(String cacheKey, Class<T> defaultImplClass) {
        if (cache.containsKey(cacheKey)) {
            return (T) cache.get(cacheKey);
        }

        String implClassName = ConfigHelper.getString(cacheKey);
        if (StringUtil.isEmpty(implClassName)) {
            implClassName = defaultImplClass.getName();
        }

        // 通过反射创建该实现类对应的实例
        T instance = ObjectUtil.newInstance(implClassName);
        if (instance != null) {
            cache.put(cacheKey, instance);
        }
        return instance;
    }
}
