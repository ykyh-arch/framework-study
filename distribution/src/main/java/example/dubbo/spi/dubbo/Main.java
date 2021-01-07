package example.dubbo.spi.dubbo;

import example.dubbo.spi.dubbo.api.Car;
import example.dubbo.spi.dubbo.api.Flower;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * @ClassName: Main
 * @Description: 主类。
 * @Author: Uetec
 * @Date: 2021-01-05-16:53
 * @Version: 1.0
 **/
public class Main {

    public static void main(String[] args) {
//        ExtensionLoader<Car> extensionLoader = ExtensionLoader.getExtensionLoader(Car.class);
//        Car car = extensionLoader.getExtension("Black");
//        car.color();

        ExtensionLoader<Flower> extensionLoader = ExtensionLoader.getExtensionLoader(Flower.class);
        Map<String,String> param = new HashMap<String, String>();
        param.put("Flower","Peony");
        //总线控制
        URL url = new URL("","",1000,"",param);
        Flower flower = extensionLoader.getExtension("Injection");
        System.out.println(flower.type(url));
    }
}
