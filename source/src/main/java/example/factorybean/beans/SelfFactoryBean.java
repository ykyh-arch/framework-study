package example.factorybean.beans;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: SelfFactoryBean
 * @Description: factorybean。
 *               如果一个类实现了FactoryBean，那么spring容器中存在2个对象
 *               一个是getObject返回的对象 当前类指定类的对象
 *               还有一个是当前对象 ”&“+当前类的名字
 *               场景：spring集成mybatis中sqlSessionFeactoryBean就是一个典型例子
 * @Author: Uetec
 * @Date: 2020-12-02-10:44
 * @Version: 1.0
 **/
//@Component("factoryBean")
public class SelfFactoryBean implements FactoryBean {

    String msg;

    public void test()  {
        System.out.println("self factoryBean is running");
    }

    @Override
    public Object getObject() throws Exception {

        TestFactoryBean testFactoryBean = new TestFactoryBean();
        String[] temp = msg.split(",");
        testFactoryBean.setMsg1(temp[0]);
        testFactoryBean.setMsg2(temp[1]);
        testFactoryBean.setMsg3(temp[2]);
        return testFactoryBean;
    }

    @Override
    public Class<?> getObjectType() {
        return TestFactoryBean.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
