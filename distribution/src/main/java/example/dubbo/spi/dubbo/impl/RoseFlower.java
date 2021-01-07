package example.dubbo.spi.dubbo.impl;

import example.dubbo.spi.dubbo.api.Flower;
import org.apache.dubbo.common.URL;

/**
 * @ClassName: RoseFlower
 * @Description: 玫瑰。
 * @Author: Uetec
 * @Date: 2021-01-06-10:04
 * @Version: 1.0
 **/
public class RoseFlower implements Flower {

    @Override
    public String type(URL url) {
        return "药用";
    }
}
