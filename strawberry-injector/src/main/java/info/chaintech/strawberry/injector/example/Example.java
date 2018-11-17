package info.chaintech.strawberry.injector.example;

import info.chaintech.strawberry.injector.Injector;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Example for injector
 * <p>
 * Created by Administrator on 2018/11/17 0017.
 */
public class Example {

    interface OrderService {
        void runBusiness();
    }

    @Named("mobileOrderService")
    class MobileOrderServiceImpl implements OrderService {

        @Override
        public void runBusiness() {
            System.out.println("Mobile order service impl");
        }
    }

    @Named("wechatOrderService")
    class WechatOrderServiceImpl implements OrderService {

        @Override
        public void runBusiness() {
            System.out.println("Wechat order service impl");
        }
    }

    @Singleton
    class OrderDispatcher {
        private final OrderService mobileOrderService;
        private final OrderService wechatOrderService;


        @Inject
        public OrderDispatcher(@Named("mobileOrderService") OrderService mobileOrderService,
                               @Named("wechatOrderService") OrderService wechatOrderService) {
            this.mobileOrderService = mobileOrderService;
            this.wechatOrderService = wechatOrderService;
        }

        public void dispatch(String source) {
            if ("mobile".equals(source)) {
                mobileOrderService.runBusiness();
            } else {
                wechatOrderService.runBusiness();
            }
        }
    }


    public static void main(String[] args) {
        Injector injector = new Injector();
        injector.registerQualifiedClass(OrderService.class, MobileOrderServiceImpl.class);
        injector.registerQualifiedClass(OrderService.class, WechatOrderServiceImpl.class);
        OrderDispatcher instance = injector.getInstance(OrderDispatcher.class);
        instance.dispatch("mobile");
    }
}
