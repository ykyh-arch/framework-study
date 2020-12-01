package example.utils;

/**
 * @ClassName: SelfSpringException
 * @Description: 自定义异常。
 * @Author: Uetec
 * @Date: 2020-12-01-17:32
 * @Version: 1.0
 **/
public class SelfSpringException extends RuntimeException{
    public SelfSpringException(String msg) {
        super(msg);
    }
}
