/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 5/21/2020 1:44 PM
 */
package com.slmora.learn.common.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This Class created for modify all jva base fie read and write actions
 *
 * @Author: SLMORA
 * @DateTime: 5/21/2020 1:44 PM
 * <p>
 * Version      Date            Editor              Note
 * ---------    ----------      ----------------    --------------------------------------------------------------------
 * 1.0          5/21/2020      SLMORA                Initial Code
 */
public class MoraFileAccess
{
    final static Logger LOGGER = LogManager.getLogger(MoraFileAccess.class);

    public void loggerTest()
    {
        LOGGER.info("The main() method is called");
        LOGGER.warn("Warning message");
        LOGGER.error("Error message");
    }

    /**
     * Console print full content of given file
     *
     * @param filePath as String Object with location of filter file
     * @throws IOException with file notfound aor compatibility issue
     * @apiNote Print full content of given file by reading using Stream, File.lines() and Path Print with method reference
     */
    public void printFileFullContentUsingStream(String filePath)
    {
        //read file into stream, try-with-resources
        try (Stream<String> fileStream = Files.lines(Paths.get(filePath))) {
            fileStream.forEach(System.out::println);
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }
    }

    /**
     * Console print full content of given file
     *
     * @param filePath as String Object with location of filter file
     * @throws IOException with file notfound aor compatibility issue
     * @apiNote Print full content of given file by reading using Stream, File.newBufferedReader() and Path Print with method reference
     */
    public void printFileFullContentUsingBufferedReader(String filePath)
    {
        //read file into BufferedReader, try-with-resources
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            br.lines().forEach(System.out::println);
        //with while loop
//            String line = null;
//            while ((line = br.readLine()) != null){
//                System.out.println(line);
//            }
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }
    }

    /**
     * Console print full content of given file
     *
     * @param filePath as String Object with location of filter file
     * @throws IOException with file notfound aor compatibility issue
     * @apiNote Print full content of given file by reading using BufferedReader, FileReader and File Print with while loop
     */
    public void printFileFullContentUsingBufferedReaderFileReader(String filePath)
    {
        //read file into BufferedReader, try-with-resources
        try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath)))) {
            //with while loop
            String line = null;
            while ((line = br.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }
    }

    /**
     * Console print full content of given file
     *
     * @param filePath as String Object with location of filter file
     * @throws IOException with file notfound aor compatibility issue
     * @apiNote Print full content of given file by reading using Scanner and File Print with while loop
     */
    public void printFileFullContentToListUsingScanner(String filePath)
    {
        //read file into Scanner, try-with-resources
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }
    }

    /**
     * Read file in given path and return it with List object
     *
     * @param filePath as String Object with location of filter file
     * @return List<String> Object will return with file content
     * @throws IOException with file notfound aor compatibility issue
     * @apiNote Read file and collect full content in to list using Stream, File.lines() and Path add to list with Stream collectors
     */
    public List getFileFullContentToListUsingStream(String filePath)
    {
        List<String> list = new ArrayList<>();
        //read file into stream, try-with-resources
        try (Stream<String> fileStream = Files.lines(Paths.get(filePath))) {
            list = fileStream.collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }finally {
            return list;
        }
    }

    /**
     * Read file with UTF-8 encoding in given path and return it with List object
     *
     * @param filePath as String Object with location of filter file
     * @return List<String> Object will return with file content
     * @throws IOException with file notfound aor compatibility issue
     * @apiNote Read file and collect full content in to list using Stream, File.lines() and Path with Decode for UTF-8 add to list with Stream collectors
     */
    public List getFileFullContentInUTF8ToListUsingStream(String filePath)
    {
        List<String> list = new ArrayList<>();
        //read file into stream, try-with-resources
        try (Stream<String> fileStream = Files.lines(Paths.get(filePath),StandardCharsets.UTF_8)) {
            list = fileStream.collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }finally {
            return list;
        }
    }

    /**
     * Read file in given path and return filtered out put in to List object
     * Filter each line not starts with given startsWith value
     * Map value 10 with given numberReplace value with 4 leftpad
     * Map all Characters in to UpperCase
     *
     * @param filePath for resource file, startsWith filter lines, numberReplace for replacing number as String Objects
     * @return List<String> Object will return with file content
     * @throws IOException with file notfound aor compatibility issue
     * @apiNote Read file and collect full content in to list using Stream, File.lines() and Path add to list with Stream collectors
     */
    public List getFilteredFileContentToListUsingStream(String filePath, String startsWith, String numberReplace)
    {
        List<String> list = new ArrayList<>();
        //read file into stream, try-with-resources
        try (Stream<String> fileStream = Files.lines(Paths.get(filePath))) {
            list = fileStream
                    .filter(line -> !line.startsWith(startsWith))
                    .map(line -> line.replaceAll("10", StringUtils.leftPad(numberReplace, 4, "0")))
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }finally {
            return list;
        }
    }

    /**
     * Read file in given path and return it with List object
     *
     * @param filePath as String Object with location of filter file
     * @return List<String> Object will return with file content
     * @throws IOException with file notfound aor compatibility issue
     * @apiNote Read file and collect full content in to list using Stream, File.newBufferedReader() and Path add to list with Stream collectors
     */
    public List getFileFullContentToListUsingBufferedReader(String filePath)
    {
        List<String> list = new ArrayList<>();
        //read file into BufferedReader, try-with-resources
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            list = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }finally {
            return list;
        }
    }

    /**
     * Read file in given path and return filtered out put in to List object
     * Filter each line not starts with given startsWith value
     * Map value 10 with given numberReplace value with 4 leftpad
     * Map all Characters in to UpperCase
     *
     * @param filePath for resource file, startsWith filter lines, numberReplace for replacing number as String Objects
     * @return List<String> Object will return with file content
     * @throws IOException with file notfound aor compatibility issue
     * @apiNote Read file and collect full content in to list using Stream, BufferedReader and FileReader add to list with collect in while loop
     */
    public List getFilteredFileContentToListUsingBufferedReader(String filePath, String startsWith, String numberReplace)
    {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                if(!line.startsWith(startsWith)){
                    if(line.contains("10")){
                        line = line.replaceAll("10", StringUtils.leftPad(numberReplace, 4, "0"));
                    }
                    line = line.toUpperCase();
                    list.add(line);
                }
            }
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    /**
     * Read file in given path and return it with List object
     *
     * @param filePath as String Object with location of filter file
     * @return List<String> Object will return with file content
     * @throws IOException with file notfound aor compatibility issue
     * @apiNote Read file and collect full content in to list using Scanner and File add to list with collect in while loop
     */
    public List getFileFullContentToListUsingScanner(String filePath)
    {
        List<String> list = new ArrayList<>();
        //read file into Scanner, try-with-resources
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNext()) {
                list.add(scanner.nextLine());
            }
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        } finally {
          return list;
        }
    }

    /**
     * Read file in given path and return it with String object
     * This allow upto Java 7
     *
     * @param filePath as String Object with location of filter file
     * @return String Object will return with file content
     * @throws IOException with file notfound aor compatibility issue
     * @apiNote Read file and collect full content in to one String Object
     */
    public String getFileFullContentInAllBytesToString(String filePath)
    {
        String content = null;
        try {
            content = new String (Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }finally {
            return content;
        }
    }

    /**
     * Read file in given path and return it with String object
     * This allow upto Java 7
     *
     * @param fileName as String Object with location of filter file
     * @return String Object will return with file content
     * @throws IOException with file notfound aor compatibility issue
     * @apiNote Read file and collect full content in to one String Object
     */
    public String getFileFullContentToStringUsingFileUtilsReadFile(String fileName)
    {
        String content = null;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());
            content = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }finally {
            return content;
        }
    }

    /**
     * Read file in given path and return it with String object
     * This allow upto Java 7
     *
     * @param filePath as String Object with location of filter file
     * @return String Object will return with file content
     * @throws IOException with file notfound aor compatibility issue
     * @apiNote Read file and collect full content in to one String Object
     */
    public String getFileFullContentToStringUsingIOUtilsReadFile(String filePath)
    {
        String content = null;
        try {
            InputStream iStream = new FileInputStream(filePath);
            content = IOUtils.toString(iStream, "UTF-8");
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }finally {
            return content;
        }
    }

    /**
     * Read file with UTF-8 encoding in given path and return it with StringBuilder object
     *
     * @param filePath as String Object with location of filter file
     * @return StringBuilder Object will return with file content
     * @throws IOException with file notfound aor compatibility issue
     * @apiNote Read file and collect full content in to one StringBuilder Object using Stream
     */
    public StringBuilder getFileFullContentInUTF8ToStringBuilderUsingStream(String filePath)
    {
        StringBuilder contentBuilder = new StringBuilder();
        //read file into stream, try-with-resources
        try {
            Stream<String> fileStream = Files.lines(Paths.get(filePath),StandardCharsets.UTF_8);
            fileStream.forEach(line -> contentBuilder.append(line).append("\n"));
            fileStream.close();
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }finally {
//            return contentBuilder.toString(); //this can use for return single string object
            return contentBuilder;
        }
    }

    /**
     * Read file with UTF-8 encoding in given path and return it with StringBuilder object
     *
     * @param filePath as String Object with location of filter file
     * @return List<String> Object will return with file content
     * @throws IOException with file notfound aor compatibility issue
     * @apiNote Read file and collect full content in to one StringBuilder Object using Stream
     */
    public StringBuilder getFileFullContentInUTF8ToStringBuilderUsingReadAllLines(String filePath)
    {
        StringBuilder contentBuilder = new StringBuilder(1024);
        try {
            List lines = Files.readAllLines(Paths.get(filePath),StandardCharsets.UTF_8);
            lines.forEach(line -> contentBuilder.append(line).append("\n"));
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }finally {
//            return contentBuilder.toString(); //this can use for return single string object
            return contentBuilder;
        }
    }

    /**
     * Read file with UTF-8 encoding in given path and return it with StringBuilder object
     *
     * @param filePath as String Object with location of filter file
     * @return List<String> Object will return with file content
     * @throws IOException with file notfound aor compatibility issue
     * @apiNote Read file and collect full content in to StringBuilder using Stream
     */
    public StringBuilder getFileFullContentInUTF8ToStringBuilderUsingInputStream(String filePath, String startsWith, String numberReplace)
    {
        StringBuilder contentBuilder = new StringBuilder(1024);

        try {
            InputStream iStream = new FileInputStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));

            String line = reader.readLine();
            while (line != null) {
                if(!line.startsWith(startsWith)){
                    if(line.contains("10")){
                        line = line.replaceAll("10", StringUtils.leftPad(numberReplace, 4, "0"));
                    }
                    line = line.toUpperCase();
                    contentBuilder.append(line).append("\n");
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }finally {
//            return contentBuilder.toString(); //this can use for return single string object
            return contentBuilder;
        }
    }

    /**
     * Read file with UTF-8 encoding in given path and return it with StringBuilder object
     *
     * @param filePath as String Object with location of filter file
     * @return List<String> Object will return with file content
     * @throws IOException with file notfound aor compatibility issue
     * @apiNote Read file and collect full content in to StringBuilder using Stream
     */
    public String getFileFullContentToStringBuilderUsingDataInputStream(String filePath)
    {
        String content = null;

        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(filePath))){
            int nBytesToRead = dataInputStream.available();
            if(nBytesToRead > 0) {
                byte[] bytes = new byte[nBytesToRead];
                dataInputStream.read(bytes);
                content = new String(bytes);
            }
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }finally {
            return content;
        }
    }

    /**
     * Read file with UTF-8 encoding in given path and return it with StringBuilder object
     *
     * @param filePath as String Object with location of filter file
     * @return List<String> Object will return with file content
     * @throws IOException with file notfound aor compatibility issue
     * @apiNote Read file and collect full content in to StringBuilder using Stream
     */
    public String getFileFullContentToStringUsingFileChanel(String filePath)
    {
        String content = null;

        try (RandomAccessFile accessFile = new RandomAccessFile(filePath, "r");
             FileChannel channel = accessFile.getChannel()){
            int bufferSize = 1024;
            if (bufferSize > channel.size()) {
                bufferSize = (int) channel.size();
            }
            ByteBuffer buff = ByteBuffer.allocate(bufferSize);
            channel.read(buff);
            buff.flip();
            content = new String(buff.array());
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }finally {
            return content;
        }
    }

    /**
     * Read file in given path and return it with String object
     * This allow upto Java 8
     *
     * @param filePath as String Object with location of filter file
     * @return String Object will return with file content
     * @throws IOException with file notfound aor compatibility issue
     * @apiNote Read file and all characters content in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public String getFileAllCharactersToString(String filePath)
    {
        String content = null;
        //read file into BufferedReader, try-with-resources
        try {
            content = Files.lines(Paths.get(filePath)).collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        } finally {
            return content;
        }
    }

    /**
     * Read file in given path and return it with String object
     * This allow upto Java 8
     *
     * @param urlPath as String Object with location of filter file
     * @return String Object will return with file content
     * @throws IOException with file notfound aor compatibility issue
     * @apiNote Read file and all characters content in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public StringBuilder getContentFromURL(String urlPath)
    {
        StringBuilder resultStringBuilder = new StringBuilder();
        //read file into BufferedReader, try-with-resources
        try {
            URL urlObject = new URL(urlPath);
            URLConnection urlConnection = urlObject.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            resultStringBuilder = readFromInputStream(inputStream);
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        } finally {
            return resultStringBuilder;
        }
    }

    /**
     * Read file in given path and return it with String object
     * This allow upto Java 8
     *
     * @param inputStream as String Object with location of filter file
     * @return String Object will return with file content
     * @throws IOException with file notfound aor compatibility issue
     * @apiNote Read file and all characters content in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public StringBuilder readFromInputStream(InputStream inputStream)
    {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        } finally {
            return resultStringBuilder;
        }
//        StringBuilder contentBuilder = new StringBuilder(1024);
//        try {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//            reader.lines().forEach(line -> contentBuilder.append(line).append("\n"));
//        } catch (IOException e) {
//            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
//            e.printStackTrace();
//        }finally {
////            return contentBuilder.toString(); //this can use for return single string object
//            return contentBuilder;
//        }

    }

    /**
     * Read Given property form given property file in resource
     *
     * @param propertyFile as String Object with location of filter file
     * @return String Object will return with file content
     * @throws IOException with file notfound aor compatibility issue
     * @apiNote Read file and all characters content in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public String getPropertyValueFromThisPropertyFile(String propertyFile, String propertyRef)
    {
        Properties properties = new Properties();
        String property = null;
        //read file into BufferedReader, try-with-resources
        try (InputStream iStream = getClass().getClassLoader().getResourceAsStream(propertyFile)){
            properties.load(iStream);
            property = properties.getProperty(propertyRef);
        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        } finally {
            return property;
        }
    }
}
/**
 *
 */
