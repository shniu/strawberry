package info.chaintech.strawberry.ioc;


import info.chaintech.strawberry.ioc.bean.BeanDefine;

import java.util.Map;

/**
 * Injector
 * Created by Administrator on 2018/11/15 0015.
 */
public interface Injector {

    /**
     * Get all beans in the IoC
     *
     * @return bean map
     */
    Map<String, BeanDefine> getBeans();
}
