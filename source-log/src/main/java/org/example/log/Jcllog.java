package org.example.log;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @ClassName: Jcllog
 * @Description: Jcl日志。Jakarta common logging
 * 是日志的门面（接口），通过日志工厂,底层有4个实现类可供选择,核心代码块:
 * for(int i=0; i<classesToDiscover.length && result == null; ++i) {
 *             result = createLogFromClass(classesToDiscover[i], logCategory, true);
 *         }
 *
 * @Author: Uetec
 * @Date: 2021-01-11-15:20
 * @Version: 1.0
 **/
public class Jcllog {

    public static void main(String[] args) {
        Log log = LogFactory.getLog("Jcl");
        log.info("Jcl");
    }
}
