/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 10/29/2020 9:22 PM
 */
package com.slmora.learn.common.collection.map;

import com.slmora.learn.common.model.Person01;
import com.slmora.learn.common.util.MoraJsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.w3c.dom.ls.LSOutput;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * This Class created for
 *
 * @Author: SLMORA
 * @DateTime: 10/29/2020 9:22 PM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          10/29/2020      SLMORA                Initial Code
 */
@DisplayName("Testing Collections Map Iterate Samples")
public class UnitTestCollectionMapIterateSampleApp
{
    TestInfo testInfo;
    TestReporter testReporter;
    long ELAPSED_TIME;
    final static Logger LOGGER = LogManager.getLogger(UnitTestCollectionMapIterateSampleApp.class);

    /**
     * Runs this method before initialize this test class
     */
    @BeforeAll
    public static void beforeAllInit()
    {
        System.out.println("Before all initialized .......");
    }

    /**
     * Runs this method before each test execution
     *
     * @param testInfo, testReporter to inject reporting information
     */
    @BeforeEach
    public void beforeEachInit(TestInfo testInfo, TestReporter testReporter)
    {
        this.testInfo = testInfo;
        this.testReporter = testReporter;
        testReporter.publishEntry("Running " + testInfo.getDisplayName() + "with tags " + testInfo.getTags() + "\n");

    }

    /**
     * Runs this method after each test execution
     */
    @AfterEach
    private void afterEachEnd()
    {
        System.out.println("*****************************************************************************************");
        System.out.println("**** Test Completed : " + testInfo.getDisplayName());
        System.out.println("**** Elapsed TIme : " + ELAPSED_TIME + " ns");
        System.out.println("*****************************************************************************************");
        LOGGER.info("Elapsed TIme for " + testInfo.getDisplayName() + " : " + ELAPSED_TIME);
    }

    /**
     * This method runs After all Test Cases execution
     * This must be static because of this will execute after instance destroyed
     */
    @AfterAll
    public static void AfterAllInit()
    {
        System.out.println("After All Initiated........");
    }

    /**
     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     */
    @Test
    @Tag("LAMBDA")
    @Tag("ALGO")
    @DisplayName("Test 01 Entry Set and For Loop 01")
    public void test01EntrySetAndForLoop01()
    {
        System.out.println("Programme Start--------------------------------------------------------------------------");
        long startTime = System.nanoTime();

        String jsonPath = "D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraStreamLearn001\\src\\main\\resources\\json\\person01.json";
        MoraJsonUtils jsonUtils = new MoraJsonUtils();

        Optional<Map<Integer, Person01>> oPPerson01List = jsonUtils.getAllPersonsFromJsonToMap(jsonPath);
        Map<Integer,Person01> personMap = oPPerson01List.get();
        System.out.println("Person List Size : "+ personMap.size());

        if(!personMap.isEmpty()){
            for (Map.Entry<Integer, Person01> entry: personMap.entrySet()){
                System.out.println(entry.getKey()+" : "+entry.getValue());
            }

        }

        long endTime = System.nanoTime();
        ELAPSED_TIME = endTime - startTime;
        System.out.println("Programme End----------------------------------------------------------------------------");

    }

    /**
     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     */
    @Test
    @Tag("LAMBDA")
    @Tag("ALGO")
    @DisplayName("Test 02 Iterator And EntrySet 01")
    public void test02IteratorAndEntrySet01()
    {
        System.out.println("Programme Start--------------------------------------------------------------------------");
        long startTime = System.nanoTime();

        String jsonPath = "D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraStreamLearn001\\src\\main\\resources\\json\\person01.json";
        MoraJsonUtils jsonUtils = new MoraJsonUtils();

        Optional<Map<Integer,Person01>> oPPerson01List = jsonUtils.getAllPersonsFromJsonToMap(jsonPath);
        Map<Integer,Person01> personMap = oPPerson01List.get();
        System.out.println("Person List Size : "+ personMap.size());

        if(!personMap.isEmpty()){
            Iterator<Map.Entry<Integer,Person01>> iterator = personMap.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<Integer,Person01> entry = iterator.next();
                System.out.println(entry.getKey()+" : "+entry.getValue());
            }

        }

        long endTime = System.nanoTime();
        ELAPSED_TIME = endTime - startTime;
        System.out.println("Programme End----------------------------------------------------------------------------");

    }

    /**
     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     */
    @Test
    @Tag("LAMBDA")
    @Tag("ALGO")
    @DisplayName("Test 03 Foreach and Lambda 01")
    public void test03ForeachAndLambda01()
    {
        System.out.println("Programme Start--------------------------------------------------------------------------");
        long startTime = System.nanoTime();

        String jsonPath = "D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraStreamLearn001\\src\\main\\resources\\json\\person01.json";
        MoraJsonUtils jsonUtils = new MoraJsonUtils();

        Optional<Map<Integer,Person01>> oPPerson01List = jsonUtils.getAllPersonsFromJsonToMap(jsonPath);
        Map<Integer,Person01> personMap = oPPerson01List.get();
        System.out.println("Person List Size : "+ personMap.size());

        if(!personMap.isEmpty()){
            personMap.forEach((k,v)-> System.out.println(k+" : "+v));

        }

        long endTime = System.nanoTime();
        ELAPSED_TIME = endTime - startTime;
        System.out.println("Programme End----------------------------------------------------------------------------");

    }

    /**
     * This method runs getPropertyFromResource(String propertyFileName, String propertyRef) methods in MoraAccessProperties class
     * This compare for expected TEST_OUT_PUT_STRING
     */
    @Test
    @Tag("LAMBDA")
    @Tag("ALGO")
    @DisplayName("Test 04 With Stream API 01")
    public void test04WithStreamAPI01()
    {
        System.out.println("Programme Start--------------------------------------------------------------------------");
        long startTime = System.nanoTime();

        String jsonPath = "D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraStreamLearn001\\src\\main\\resources\\json\\person01.json";
        MoraJsonUtils jsonUtils = new MoraJsonUtils();

        Optional<Map<Integer,Person01>> oPPerson01List = jsonUtils.getAllPersonsFromJsonToMap(jsonPath);
        Map<Integer,Person01> personMap = oPPerson01List.get();
        System.out.println("Person List Size : "+ personMap.size());

        if(!personMap.isEmpty()){
            personMap.entrySet().stream()
                    .filter(p -> p.getValue().getPerson01LastName().startsWith("G"))
                    .forEach(e-> System.out.println(e.getKey()+" : "+e.getValue()));

        }

        long endTime = System.nanoTime();
        ELAPSED_TIME = endTime - startTime;
        System.out.println("Programme End----------------------------------------------------------------------------");

    }
}
