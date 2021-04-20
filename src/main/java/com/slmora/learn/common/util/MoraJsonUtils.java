/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 10/25/2020 10:06 PM
 */
package com.slmora.learn.common.util;

import com.slmora.learn.common.model.Person01;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * This Class created for
 *
 * @Author: SLMORA
 * @DateTime: 10/25/2020 10:06 PM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          10/25/2020      SLMORA                Initial Code
 */
public class MoraJsonUtils
{
    final static Logger LOGGER = LogManager.getLogger(MoraJsonUtils.class);
    final static String TEST_FILE_LOCATION = "D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraServletJSPLearn019\\data.json";


    /**
     * Read Given property form given json file in given location
     *
     * @param userName as String Object with location of filter file
     * @return String Object will return with file content
     * @throws IOException with file notfound aor compatibility issue
     * @apiNote Read file and all characters content in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public Optional<Person01> getPersonFromJson(Integer personId, String filePath)
    {
        JSONParser jsonParser = new JSONParser();
        Person01 returnPerson = null;

        try (FileReader reader = new FileReader(filePath)){
            Object obj = jsonParser.parse(reader);
            JSONArray personList = (JSONArray) obj;
            LOGGER.info(personList);
            System.out.println(personList);

            for(Object person: personList){
                JSONObject personObject = (JSONObject) person;
                JSONObject selectedPerson = (JSONObject) personObject.get("user");
                Long jsonPeronId = (Long) selectedPerson.get("id");
                if(jsonPeronId.intValue()==personId.intValue()){
                    returnPerson = new Person01();
                    returnPerson.setPerson01Id(jsonPeronId.intValue());
                    returnPerson.setPerson01FirstName((String)selectedPerson.get("firstName"));
                    returnPerson.setPerson01LastName((String)selectedPerson.get("lastName"));
                    returnPerson.setPerson01FullName(returnPerson.getPerson01FirstName()+" "+returnPerson.getPerson01LastName());
                    returnPerson.setPerson01Age(((Long) selectedPerson.get("age")).intValue());
                    returnPerson.setPerson01Email((String)selectedPerson.get("email"));
                    returnPerson.setPerson01Salary(((Long) selectedPerson.get("salary")).doubleValue());
                    returnPerson.setPerson01Birthday(new SimpleDateFormat("yyyy-MM-dd").parse((String)selectedPerson.get("birthday")));
                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        } catch (ParseException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }finally {
            return Optional.ofNullable(returnPerson);
        }
    }

    /**
     * Read Given property form given json file in given location
     *
     * @param userName as String Object with location of filter file
     * @return String Object will return with file content
     * @throws IOException with file notfound aor compatibility issue
     * @apiNote Read file and all characters content in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public Optional<List<Person01>> getAllPersonsFromJson(String filePath)
    {
        JSONParser jsonParser = new JSONParser();
        List<Person01> returnPersons = new ArrayList<>();

        try (FileReader reader = new FileReader(filePath)){
            Object obj = jsonParser.parse(reader);
            JSONArray personList = (JSONArray) obj;
            LOGGER.info(personList);
            System.out.println(personList);

            for(Object person: personList){
                JSONObject personObject = (JSONObject) person;
                JSONObject selectedPerson = (JSONObject) personObject.get("user");
                Person01 rPerson = new Person01();
                rPerson.setPerson01Id(((Long) selectedPerson.get("id")).intValue());
                rPerson.setPerson01FirstName((String)selectedPerson.get("firstName"));
                rPerson.setPerson01LastName((String)selectedPerson.get("lastName"));
                rPerson.setPerson01FullName(rPerson.getPerson01FirstName()+" "+rPerson.getPerson01LastName());
                rPerson.setPerson01Age(((Long) selectedPerson.get("age")).intValue());
                rPerson.setPerson01Email((String)selectedPerson.get("email"));
                rPerson.setPerson01Salary(((Long) selectedPerson.get("salary")).doubleValue());
                rPerson.setPerson01Birthday(new SimpleDateFormat("yyyy-MM-dd").parse((String)selectedPerson.get("birthday")));
                returnPersons.add(rPerson);
            }
        } catch (FileNotFoundException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        } catch (ParseException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }finally {
            return Optional.ofNullable(returnPersons);
        }
    }

    /**
     * Read Given property form given json file in given location
     *
     * @param userName as String Object with location of filter file
     * @return String Object will return with file content
     * @throws IOException with file notfound aor compatibility issue
     * @apiNote Read file and all characters content in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public Optional<Map<Integer,Person01>> getAllPersonsFromJsonToMap(String filePath)
    {
        JSONParser jsonParser = new JSONParser();

        Map<Integer,Person01> returnPersons = new HashMap<>();

        try (FileReader reader = new FileReader(filePath)){
            Object obj = jsonParser.parse(reader);
            JSONArray personList = (JSONArray) obj;
            LOGGER.info(personList);
            System.out.println(personList);

            for(Object person: personList){
                JSONObject personObject = (JSONObject) person;
                JSONObject selectedPerson = (JSONObject) personObject.get("user");
                Person01 rPerson = new Person01();
                rPerson.setPerson01Id(((Long) selectedPerson.get("id")).intValue());
                rPerson.setPerson01FirstName((String)selectedPerson.get("firstName"));
                rPerson.setPerson01LastName((String)selectedPerson.get("lastName"));
                rPerson.setPerson01FullName(rPerson.getPerson01FirstName()+" "+rPerson.getPerson01LastName());
                rPerson.setPerson01Age(((Long) selectedPerson.get("age")).intValue());
                rPerson.setPerson01Email((String)selectedPerson.get("email"));
                rPerson.setPerson01Salary(((Long) selectedPerson.get("salary")).doubleValue());
                rPerson.setPerson01Birthday(new SimpleDateFormat("yyyy-MM-dd").parse((String)selectedPerson.get("birthday")));
                returnPersons.put(rPerson.getPerson01Id(),rPerson);
            }
        } catch (FileNotFoundException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        } catch (ParseException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }finally {
            return Optional.ofNullable(returnPersons);
        }
    }
}
