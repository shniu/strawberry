package info.chaintech.strawberry.injector;

/**
 * Created by Administrator on 2018/11/17 0017.
 */
public class InjectorException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InjectorException() {
        super();
    }

    public InjectorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InjectorException(String message) {
        super(message);
    }

    public InjectorException(Throwable cause) {
        super(cause);
    }
}
