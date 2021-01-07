package example.dubbo.spi.java;

import example.dubbo.spi.java.api.Car;

import java.util.Iterator;
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
        ServiceLoader<Car> serviceLoader = ServiceLoader.load(Car.class);
        Iterator<Car> iterator = serviceLoader.iterator();
        while (iterator.hasNext()){
            System.out.println("进来了");
            Car next = iterator.next();
            next.color();
        }
    }
}
