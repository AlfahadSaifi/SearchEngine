package com.nucleus.utility;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.springframework.stereotype.Component;

//factory class for logger
@Component
public class
LoggerFactory {

    //Logger
    private LoggerFactory(){}
    static
    {
        System.setProperty("logFileName", "Main");
    }
}
