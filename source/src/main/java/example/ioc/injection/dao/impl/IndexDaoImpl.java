package example.ioc.injection.dao.impl;

import example.ioc.injection.dao.IndexDao;
import org.springframework.stereotype.Component;

/**
 * @ClassName: IndexDaoImpl
 * @Description: 实现类。
 * @Author: Uetec
 * @Date: 2020-11-19-10:18
 * @Version: 1.0
 **/
@Component
public class IndexDaoImpl implements IndexDao {

    private String index;

    public void method() {
        System.out.println("method is running");
        System.out.println("index:"+index);
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
