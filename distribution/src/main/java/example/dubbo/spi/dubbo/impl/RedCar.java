package example.dubbo.spi.dubbo.impl;

import example.dubbo.spi.dubbo.api.Car;

/**
 * @ClassName: BlackCar
 * @Description: 实现类。
 * @Author: Uetec
 * @Date: 2021-01-05-16:49
 * @Version: 1.0
 **/
public class RedCar implements Car {

    public String color() {
        System.out.println("Red");
        return "Red";
    }
}
