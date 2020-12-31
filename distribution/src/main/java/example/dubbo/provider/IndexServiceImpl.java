package example.dubbo.provider;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.RpcContext;
import example.dubbo.api.IndexService;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: IndexService
 * @Description: 测试类。
 * @Author: Uetec
 * @Date: 2020-12-30-17:22
 * @Version: 1.0
 **/
public class IndexServiceImpl implements IndexService {

    public String index(String arg) {
        /*try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        URL url = RpcContext.getContext().getUrl();
        return "服务提供者接收参数：" + arg + "；协议为："+url.getProtocol() +"；地址为："+url.getAddress();
    }

}
