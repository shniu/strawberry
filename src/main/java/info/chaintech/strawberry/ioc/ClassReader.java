package info.chaintech.strawberry.ioc;

import info.chaintech.strawberry.ioc.bean.ClassDesc;
import info.chaintech.strawberry.ioc.bean.Scanner;

import java.util.Set;

/**
 * Class reader interface
 *
 * Created by Administrator on 2018/11/15 0015.
 */
public interface ClassReader {

    /**
     * Scan and read the bean and put them in the container
     */
    Set<ClassDesc> read(Scanner scanner);
}
