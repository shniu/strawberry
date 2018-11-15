package info.chaintech.strawberry.ioc.bean;

import lombok.Builder;
import lombok.Data;

import java.lang.annotation.Annotation;

/**
 * 扫描类的元信息
 * <p>
 * Created by shniu on 2018/11/11.
 */

@Data
@Builder
public class Scanner {

    /**
     * 要扫描的包路径
     */
    private String packageName;

    /**
     * 是否递归扫描
     */
    private boolean recursive;

    /**
     *  获取指定包名中指定父类或接口的相关类
     */
    private Class<?> parent;

    /**
     * 获取指定包名中指定注解的相关类
     */
    private Class<? extends Annotation> annotation;
}
