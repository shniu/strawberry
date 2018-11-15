package info.chaintech.strawberry.ioc.injecor;

import info.chaintech.strawberry.ioc.Injector;
import info.chaintech.strawberry.ioc.bean.BeanDefine;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of injector
 * <p>
 * Created by Administrator on 2018/11/15 0015.
 */
public class InjectorImpl implements Injector {

    /**
     * Bean container
     */
    private Map<String, BeanDefine> beanContainer = new HashMap<>();

    @Override
    public Map<String, BeanDefine> getBeans() {
        return beanContainer;
    }
}
