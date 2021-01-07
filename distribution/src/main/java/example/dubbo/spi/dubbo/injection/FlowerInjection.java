package example.dubbo.spi.dubbo.injection;

import example.dubbo.spi.dubbo.api.Flower;
import org.apache.dubbo.common.URL;

/**
 * @ClassName: FlowerInject
 * @Description: 自动注入。
 * @Author: Uetec
 * @Date: 2021-01-06-10:19
 * @Version: 1.0
 **/
public class FlowerInjection implements Flower {

    private Flower flower;

    //setter注入
    public void setFlower(Flower flower) {
        this.flower = flower;
    }

    @Override
    public String type(URL url) {
        System.out.println("注入成功");
        return flower.type(url);
    }

}
