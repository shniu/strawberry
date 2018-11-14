package info.chaintech.strawberry.ioc;

import info.chaintech.strawberry.ioc.bean.Scanner;
import info.chaintech.strawberry.ioc.bean.ClassInfo;

import java.util.Set;

/**
 * 读取类的接口
 * <p>
 * Created by shniu on 2018/11/11.
 */
public interface ClassReader {

    /**
     * 读取类
     *
     * @param scanner 扫描类的元信息
     * @return 扫描到的所有类信息
     */
    Set<ClassInfo> readClasses(Scanner scanner);

}
