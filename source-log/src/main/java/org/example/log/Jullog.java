package org.example.log;


import java.util.logging.Logger;

/**
 * @ClassName: JUClog
 * @Description: JUL日志。可以单独使用。
 * @Author: Uetec
 * @Date: 2021-01-11-12:04
 * @Version: 1.0
 **/
public class Jullog {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger("Jul");
        logger.info("Jul");
    }

}
