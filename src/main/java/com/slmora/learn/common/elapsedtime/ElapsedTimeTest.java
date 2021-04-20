/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 9/27/2020 2:07 AM
 */
package com.slmora.learn.common.elapsedtime;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * This Class created for Elapsed Time
 *
 * @Author: SLMORA
 * @DateTime: 9/27/2020 2:07 AM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          9/27/2020      SLMORA                Initial Code
 */
public class ElapsedTimeTest
{
    final static Logger LOGGER = LogManager.getLogger(ElapsedTimeTest.class);

    /**
     * Sleep 5 second usingTimeUnit
     * @throws InterruptedException with thread failure issue
     * @apiNote waiting for 5 second in main thread sleep mode
     * @Note to analyze elapsed time created this method
     */
    public void waitFiveSecondSleep(){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(ex));
            ex.printStackTrace();
        }
    }

    /**
     * Simplest method make console pring Hello World
     * @apiNote analyze elapsed time
     * @Note to analyze elapsed time created this method
     */
    public void printHelloWorld(){
        System.out.println("Hello World");
    }
}
