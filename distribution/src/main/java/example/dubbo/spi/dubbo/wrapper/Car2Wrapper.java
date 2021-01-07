package example.dubbo.spi.dubbo.wrapper;

import example.dubbo.spi.dubbo.api.Car;

/**
 * @ClassName: Car1Wrapper
 * @Description: 包装类。dubbo 中的AOP
 * @Author: Uetec
 * @Date: 2021-01-06-9:45
 * @Version: 1.0
 **/
public class Car2Wrapper implements Car {

    private Car car;

    public Car2Wrapper(Car car) {
        this.car = car;
    }

    @Override
    public String color() {

        System.out.println("before2");
        car.color();
        System.out.println("after2");

        return null;
    }
}
