/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 9/27/2020 9:25 PM
 */
package com.slmora.learn.common.elapsedtime;

import com.google.common.base.Stopwatch;
import com.slmora.learn.common.elapsedtime.ElapsedTimeTest;
import org.apache.commons.lang.time.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * This Class created for unit testing com.slmora.learn.app.elapsedtime.ElapsedTimeTest
 *
 * @Author: SLMORA
 * @DateTime: 9/27/2020 9:25 PM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          9/27/2020      SLMORA                Initial Code
 */
public class UnitTestElapsedTimeTest
{
    TestInfo testInfo;
    TestReporter testReporter;
    final static Logger LOGGER = LogManager.getLogger(UnitTestElapsedTimeTest.class);

    ElapsedTimeTest elapsedTimeTest;

    /**
     * Runs this method before initialize this test class
     * */
    @BeforeAll
    public static void beforeAllInit()
    {
        System.out.println("Before all initialized .......");
    }

    /**
     * Runs this method before each test execution
     * @param testInfo, testReporter to inject reporting information
     * */
    @BeforeEach
    public void beforeEachInit(TestInfo testInfo, TestReporter testReporter)
    {
        this.testInfo =  testInfo;
        this.testReporter = testReporter;
        testReporter.publishEntry("Running " + testInfo.getDisplayName() + "with tags " + testInfo.getTags() + "\n");

        elapsedTimeTest = new ElapsedTimeTest();

    }

    /**
     * Runs this method after each test execution
     * */
    @AfterEach
    private void afterEachEnd()
    {
        System.out.println("Test Completed "+testInfo.getDisplayName());
        System.out.println("After Each Clean Test........");
    }

    /**
     * This method runs After all Test Cases execution
     * This must be static because of this will execute after instance destroyed
     * */
    @AfterAll
    public static void AfterAllInit()
    {
        System.out.println("After All Initiated........");
    }

    /**
     * This method runs waitFiveSecondSleep() method in ElapsedTimeTest class
     * This will testing is it working as expected waitFiveSecondSleep() method
     * */
    @Test
    @Tag("ANALYSE")
    @Tag("PRINT")
    @DisplayName("Test run for waitFiveSecondSleep()")
    public void testRunWaitFiveSecondSleep(){
        System.out.println("Programme Start");
        elapsedTimeTest.waitFiveSecondSleep();
        System.out.println("Programme End");
    }

    /**
     * This method runs waitFiveSecondSleep() method in ElapsedTimeTest class
     * This will testing performance of System.nanoTime() elapse time calculation
     * */
    @Test
    @Tag("ANALYSE")
    @Tag("PRINT")
    @DisplayName("Calculate Elapsed Time for waitFiveSecondSleep() with System.nanoTime()")
    public void testNanoTimeWithWaitFiveSecondSleep(){
        long startTime = System.nanoTime();
        System.out.println("Programme Start");
        elapsedTimeTest.waitFiveSecondSleep();
        System.out.println("Programme End");
        long endTime = System.nanoTime();
        System.out.println("Elapsed TIme : " + (endTime - startTime) + " ns");
    }

    /**
     * This method runs waitFiveSecondSleep() method in ElapsedTimeTest class
     * This will testing performance of System.nanoTime() elapse time calculation with 4 repetition
     * This will return all nano seconds, milli seconds and seconds values
     * */
    @RepeatedTest(4)
    @Tag("ANALYSE")
    @Tag("PRINT")
    @DisplayName("Calculate Elapsed Time repeated for waitFiveSecondSleep() with System.nanoTime()")
    public void testNanoTimeRepeatedWithWaitFiveSecondSleep(){
        long startTime = System.nanoTime();
        System.out.println("Programme Start");
        elapsedTimeTest.waitFiveSecondSleep();
        System.out.println("Programme End");
        long endTime = System.nanoTime();
        long elapseTime = endTime - startTime;
        long elapseTimeMS = TimeUnit.NANOSECONDS.toMillis(elapseTime);
        long elapseTimeS = TimeUnit.NANOSECONDS.toSeconds(elapseTime);
        System.out.println("Elapsed TIme : " + elapseTime + " ns");
        System.out.println("Elapsed TIme : " + elapseTimeMS + " ms");
        System.out.println("Elapsed TIme : " + elapseTimeS + " s");
    }

    /**
     * This method runs waitFiveSecondSleep() method in ElapsedTimeTest class
     * This will testing performance of System.nanoTime() elapse time calculation with 4 repetition
     * */
    @RepeatedTest(4)
    @Tag("ANALYSE")
    @Tag("PRINT")
    @DisplayName("Calculate Elapsed Time repeated for printHelloWorld() with System.nanoTime()")
    public void testNanoTimeRepeatedWithPrintHelloWorld(){
        long startTime = System.nanoTime();
        System.out.println("Programme Start");
        elapsedTimeTest.printHelloWorld();
        System.out.println("Programme End");
        long endTime = System.nanoTime();
        System.out.println("Elapsed TIme : " + (endTime - startTime) + " ns");
    }

    /**
     * This method runs waitFiveSecondSleep() method in ElapsedTimeTest class
     * This will testing performance of System.currentTimeMillis() elapse time calculation with 4 repetition
     * */
    @RepeatedTest(4)
    @Tag("ANALYSE")
    @Tag("PRINT")
    @DisplayName("Calculate Elapsed Time repeated for waitFiveSecondSleep() with System.currentTimeMillis()")
    public void testCurrentTimeMillisRepeatedWithWaitFiveSecondSleep(){
        long startTime = System.currentTimeMillis();
        System.out.println("Programme Start");
        elapsedTimeTest.waitFiveSecondSleep();
        System.out.println("Programme End");
        long endTime = System.currentTimeMillis();
        System.out.println("Elapsed TIme : " + (endTime - startTime) + " ms");
    }

    /**
     * This method runs waitFiveSecondSleep() method in ElapsedTimeTest class
     * This will testing performance of Instant.now().toEpochMilli() elapse time calculation with 4 repetition
     * */
    @RepeatedTest(4)
    @Tag("ANALYSE")
    @Tag("PRINT")
    @DisplayName("Calculate Elapsed Time repeated for waitFiveSecondSleep() with Instant.now().toEpochMilli()")
    public void testInstantNowToEpochMilliRepeatedWithWaitFiveSecondSleep(){
        long startTime = Instant.now().toEpochMilli();
        System.out.println("Programme Start");
        elapsedTimeTest.waitFiveSecondSleep();
        System.out.println("Programme End");
        long endTime = Instant.now().toEpochMilli();
        System.out.println("Elapsed TIme : " + (endTime - startTime) + " ms");
    }

    /**
     * This method runs waitFiveSecondSleep() method in ElapsedTimeTest class
     * This will testing performance of Instant.now() elapse time calculation with 4 repetition
     * */
    @RepeatedTest(4)
    @Tag("ANALYSE")
    @Tag("PRINT")
    @DisplayName("Calculate Elapsed Time repeated for waitFiveSecondSleep() with Instant.now()")
    public void testInstantNowRepeatedWithWaitFiveSecondSleep(){
        Instant startTime = Instant.now();
        System.out.println("Programme Start");
        elapsedTimeTest.waitFiveSecondSleep();
        System.out.println("Programme End");
        Instant endTime = Instant.now();
        Duration duration = Duration.between(startTime, endTime);
        System.out.println("Elapsed TIme : " + (duration.getSeconds()) + " s");
    }

    /**
     * This method runs waitFiveSecondSleep() method in ElapsedTimeTest class
     * This will testing performance of google Stopwatch elapse time calculation with 4 repetition
     * This will return all nano seconds, milli seconds and seconds values
     * */
    @RepeatedTest(4)
    @Tag("ANALYSE")
    @Tag("PRINT")
    @DisplayName("Calculate Elapsed Time repeated for waitFiveSecondSleep() with google Stopwatch")
    public void testGoogleStopwatchRepeatedWithWaitFiveSecondSleep(){
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println("Programme Start");
        elapsedTimeTest.waitFiveSecondSleep();
        System.out.println("Programme End");
        stopwatch.stop();
        long elapsedTimeNS = stopwatch.elapsed(TimeUnit.NANOSECONDS);
        long elapsedTimeMS = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        long elapsedTimeS = stopwatch.elapsed(TimeUnit.SECONDS);
        System.out.println("Elapsed TIme : " + elapsedTimeNS + " ns");
        System.out.println("Elapsed TIme : " + elapsedTimeMS + " ms");
        System.out.println("Elapsed TIme : " + elapsedTimeS + " s");
    }

    /**
     * This method runs waitFiveSecondSleep() method in ElapsedTimeTest class
     * This will testing performance of apache StopWatch elapse time calculation with 4 repetition
     * */
    @RepeatedTest(4)
    @Tag("ANALYSE")
    @Tag("PRINT")
    @DisplayName("Calculate Elapsed Time repeated for waitFiveSecondSleep() with apache StopWatch")
    public void testApacheStopWatchRepeatedWithWaitFiveSecondSleep(){
        StopWatch stopWatch =  new StopWatch();
        stopWatch.start();
        System.out.println("Programme Start");
        elapsedTimeTest.waitFiveSecondSleep();
        System.out.println("Programme End");
        stopWatch.stop();
        long elapsedTime = stopWatch.getTime();
        System.out.println("Elapsed TIme : " + elapsedTime + " ms");
    }

    /**
     * This method runs waitFiveSecondSleep() method in ElapsedTimeTest class
     * This will testing performance of Date().getTime() elapse time calculation with 4 repetition
     * */
    @RepeatedTest(4)
    @Tag("ANALYSE")
    @Tag("PRINT")
    @DisplayName("Calculate Elapsed Time repeated for waitFiveSecondSleep() with Date().getTime()")
    public void testDateRepeatedWithWaitFiveSecondSleep(){
        long startTime = new Date().getTime();
        System.out.println("Programme Start");
        elapsedTimeTest.waitFiveSecondSleep();
        System.out.println("Programme End");
        long endTime = new Date().getTime();
        System.out.println("Elapsed TIme : " + (endTime - startTime) + " ms");
    }

    /**
     * This method runs waitFiveSecondSleep() method in ElapsedTimeTest class
     * This will testing performance of Calendar.getInstance().getTimeInMillis() elapse time calculation with 4 repetition
     * */
    @RepeatedTest(4)
    @Tag("ANALYSE")
    @Tag("PRINT")
    @DisplayName("Calculate Elapsed Time repeated for waitFiveSecondSleep() with Calendar.getInstance().getTimeInMillis()")
    public void testCalendarRepeatedWithWaitFiveSecondSleep(){
        long startTime = Calendar.getInstance().getTimeInMillis();
        System.out.println("Programme Start");
        elapsedTimeTest.waitFiveSecondSleep();
        System.out.println("Programme End");
        long endTime = Calendar.getInstance().getTimeInMillis();
        System.out.println("Elapsed TIme : " + (endTime - startTime) + " ms");
    }
}
