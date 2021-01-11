package org.example.log;

import org.apache.log4j.Logger;

/**
 * @ClassName: Log4jlog
 * @Description: Log4j日志。可以单独使用。
 * @Author: Uetec
 * @Date: 2021-01-11-15:12
 * @Version: 1.0
 **/
public class Log4jlog {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger("Log4j");
        logger.info("Log4j");
    }
}
