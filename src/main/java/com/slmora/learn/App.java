/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 7/10/2020 04:45 PM
 */

package com.slmora.learn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * This Class created for MoraServeltJSPLearn001 test
 *
 * @Author: SLMORA
 * @DateTime: 8/1/2020 11:51 AM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          8/1/2020        SLMORA              Initial Code
 */
public class App 
{
    final static Logger LOGGER = LogManager.getLogger(App.class);

    public void loggerTest()
    {
        LOGGER.info("The App constructor is called");
        LOGGER.warn("Warning message");
        LOGGER.error("Error message");
    }

    public static void main(String[] args )
    {
        App a = new App();
        a.loggerTest();//FIXME Call another method when fixed
        System.out.println( "Hello World!" );

    }
}
