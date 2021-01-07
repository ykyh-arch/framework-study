package example.dubbo.spi.dubbo.api;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

/**
 * @ClassName: Car
 * @Description: 汽车接口。
 * @Author: Uetec
 * @Date: 2021-01-05-16:48
 * @Version: 1.0
 **/
@SPI
public interface Flower {

    @Adaptive("Flower")
    public String type(URL url);
}
