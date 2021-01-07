package example.dubbo.spi.dubbo.wrapper;

import example.dubbo.spi.dubbo.api.Car;

/**
 * @ClassName: Car1Wrapper
 * @Description: 包装类。dubbo 中的AOP思想
 *               层层包装，在example.dubbo.spi.java.api.Car中规定的最后一个包装类最先执行
 * @Author: Uetec
 * @Date: 2021-01-06-9:45
 * @Version: 1.0
 **/
public class Car1Wrapper implements Car {

    private Car car;

    public Car1Wrapper(Car car) {
        this.car = car;
    }

    @Override
    public String color() {

        System.out.println("before1");
        car.color();
        System.out.println("after1");

        return null;
    }
}
