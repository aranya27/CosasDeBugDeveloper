package com.jugando.log4j;

import org.apache.log4j.Logger;



public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);

    public void runMe(String parameter) {
        
        if (logger.isDebugEnabled()) {
            logger.debug("This is debug : " + parameter);
        }

        if (logger.isInfoEnabled()) {
            logger.info("This is info : " + parameter);
        }

        logger.warn("This is warn : " + parameter);
        logger.error("This is error : " + parameter);
        logger.fatal("This is fatal : " + parameter, new Exception("Valio verga!!"));
        
    }

    public static void main(String[] args) {
        new Main().runMe("JESUS");
    }
}
