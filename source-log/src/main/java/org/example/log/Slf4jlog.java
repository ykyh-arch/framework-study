package org.example.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: Slf4jlog
 * @Description: Slf4j日志。
 * @Author: Uetec
 * @Date: 2021-01-11-16:31
 * @Version: 1.0
 **/
public class Slf4jlog {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger("slf4j");
        logger.info("slf4j");
    }
}
