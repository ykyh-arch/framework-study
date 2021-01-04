package example.dubbo.provider;

import example.dubbo.api.IndexService;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.RpcContext;

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
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //int num = 100/0;
        URL url = RpcContext.getContext().getUrl();
        return "服务提供者接收参数：" + arg + "；协议为："+url.getProtocol() +"；地址为："+url.getAddress();
    }

}
