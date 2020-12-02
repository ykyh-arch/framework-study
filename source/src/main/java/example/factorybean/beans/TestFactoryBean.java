package example.factorybean.beans;

/**
 * @ClassName: TestFactoryBean
 * @Description: 测试类。
 * @Author: Uetec
 * @Date: 2020-12-02-10:51
 * @Version: 1.0
 **/
public class TestFactoryBean {

    String msg1;

    String msg2;

    String msg3;

    public String getMsg1() {
        return msg1;
    }

    public void setMsg1(String msg1) {
        this.msg1 = msg1;
    }

    public String getMsg2() {
        return msg2;
    }

    public void setMsg2(String msg2) {
        this.msg2 = msg2;
    }

    public String getMsg3() {
        return msg3;
    }

    public void setMsg3(String msg3) {
        this.msg3 = msg3;
    }

    public void test() {
        System.out.println("msg:"+msg1+msg2+msg3);
        System.out.println("test factoryBean is running");
    }
}
