package info.chaintech.strawberry;

import info.chaintech.strawberry.beans.BeanHelper;
import info.chaintech.strawberry.beans.IoCHelper;
import info.chaintech.strawberry.util.ClassUtil;

/**
 * 加载器
 *
 * Created by Administrator on 2018/11/5 0005.
 */
public class HelperLoader {

    public static void init() {
        // 需要加载的类
        Class<?>[] classList = {
                BeanHelper.class,
                IoCHelper.class
        };

        // load
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
        }
    }

}
