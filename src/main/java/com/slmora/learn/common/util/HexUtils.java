/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 10/17/2020 1:00 PM
 */
package com.slmora.learn.common.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * This Class created for String Hex all utilities
 * https://mkyong.com/java/how-to-convert-hex-to-ascii-in-java/
 * https://mkyong.com/java/java-how-to-convert-bytes-to-hex/
 * https://en.wikipedia.org/wiki/Hexadecimal
 * https://stackoverflow.com/questions/32180069/how-do-i-perform-mysql-unhex-function-in-java
 * @Author: SLMORA
 * @DateTime: 10/17/2020 1:00 PM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          10/17/2020      SLMORA                Initial Code
 */
public class HexUtils
{
    final static Logger LOGGER = LogManager.getLogger(HexUtils.class);

    private static final char[] HEX_UPPER = "0123456789ABCDEF".toCharArray();
    private static final char[] HEX_LOWER = "0123456789abcdef".toCharArray();

    /**
     * Read Given property form given property file in project resource
     *
     * @param propertyFileName as String Object with file name of property file
     * @param propertyRef as String Object with reference of property
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public String convertStringToHexWithApacheCommons(String inputString) {
        // display in uppercase
        //char[] chars = Hex.encodeHex(inputString.getBytes(StandardCharsets.UTF_8), false);
        // display in lowercase, default
        char[] chars = org.apache.commons.codec.binary.Hex.encodeHex(inputString.getBytes(StandardCharsets.UTF_8));
        return String.valueOf(chars);
    }

    /**
     * Read Given property form given property file in project resource
     *
     * @param propertyFileName as String Object with file name of property file
     * @param propertyRef as String Object with reference of property
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public String convertByteArrayToHexWithApacheCommons(byte[] bytes) {
        char[] result = org.apache.commons.codec.binary.Hex.encodeHex(bytes);
        return String.valueOf(result);
    }

    /**
     * Read Given property form given property file in project resource
     *
     * @param propertyFileName as String Object with file name of property file
     * @param propertyRef as String Object with reference of property
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public String convertStringToUnHexWithApacheCommons(String hexString) {
        String result = "";
        try {
            byte[] bytes = org.apache.commons.codec.binary.Hex.decodeHex(hexString);
            result = new String(bytes, StandardCharsets.UTF_8);
        } catch (DecoderException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Read Given property form given property file in project resource
     *
     * @param propertyFileName as String Object with file name of property file
     * @param propertyRef as String Object with reference of property
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public byte[] convertStringToUnHexByteArrayWithApacheCommons(String hexString) {
        byte[] bytes = new byte[hexString.length()/2];
        try {
            bytes = org.apache.commons.codec.binary.Hex.decodeHex(hexString);
        } catch (DecoderException e) {
            LOGGER.error(ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * Read Given property form given property file in project resource
     *
     * @param propertyFileName as String Object with file name of property file
     * @param propertyRef as String Object with reference of property
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public String convertStringToHexWithIntegerWrapper(String inputString) {

        StringBuffer hex = new StringBuffer();

        // loop chars one by one
        for (char temp : inputString.toCharArray()) {
            // convert char to int, for char `a` decimal 97
            int decimal = (int) temp;
            // convert int to hex, for decimal 97 hex 61
            hex.append(Integer.toHexString(decimal));
        }
        return hex.toString();

    }

    /**
     * Read Given property form given property file in project resource
     *
     * @param propertyFileName as String Object with file name of property file
     * @param propertyRef as String Object with reference of property
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    // Hex -> Decimal -> Char
    public String convertStringToUnHexWithIntegerWrapper(String hexString) {

        StringBuilder result = new StringBuilder();

        // split into two chars per loop, hexString, 0A, 0B, 0C...
        for (int i = 0; i < hexString.length() - 1; i += 2) {
            String tempInHex = hexString.substring(i, (i + 2));
            //convert hexString to decimal
            int decimal = Integer.parseInt(tempInHex, 16);
            // convert the decimal to char
            result.append((char) decimal);
        }
        return result.toString();

    }

    /**
     * Read Given property form given property file in project resource
     *
     * @param propertyFileName as String Object with file name of property file
     * @param propertyRef as String Object with reference of property
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public String convertByteArrayToHexWithIntegerWrapper(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte aByte : bytes) {
            int decimal = (int) aByte & 0xff;               // bytes widen to int, need mask, prevent sign extension
            // get last 8 bits
            String hex = Integer.toHexString(decimal);
            if (hex.length() % 2 == 1) {                    // if half hex, pad with zero, e.g \t
                hex = "0" + hex;
            }
            result.append(hex);
        }
        return result.toString();
    }

    /**
     * Read Given property form given property file in project resource
     *
     * @param propertyFileName as String Object with file name of property file
     * @param propertyRef as String Object with reference of property
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public String convertStringToHexWithBitwiseSift(String inputString, boolean lowercase) {

        char[] HEX_ARRAY = lowercase ? HEX_LOWER : HEX_UPPER;

        byte[] bytes = inputString.getBytes(StandardCharsets.UTF_8);

        // two chars form the hex value.
        char[] hex = new char[bytes.length * 2];

        for (int j = 0; j < bytes.length; j++) {

            // 1 byte = 8 bits,
            // upper 4 bits is the first half of hex
            // lower 4 bits is the second half of hex
            // combine both and we will get the hex value, 0A, 0B, 0C

            int v = bytes[j] & 0xFF;               // byte widened to int, need mask 0xff
            // prevent sign extension for negative number

            hex[j * 2] = HEX_ARRAY[v >>> 4];       // get upper 4 bits

            hex[j * 2 + 1] = HEX_ARRAY[v & 0x0F];  // get lower 4 bits

        }

        return new String(hex);

    }

    /**
     * Read Given property form given property file in project resource
     *
     * @param propertyFileName as String Object with file name of property file
     * @param propertyRef as String Object with reference of property
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public String convertByteArrayToHexWithBitwiseSift(byte[] bytes, boolean lowercase) {

        char[] HEX_ARRAY = lowercase ? HEX_LOWER : HEX_UPPER;

        final int nBytes = bytes.length;
        char[] result = new char[2 * nBytes];         //  1 hex contains two chars
        //  hex = [0-f][0-f], e.g 0f or ff

        int j = 0;
        for (byte aByte : bytes) {                    // loop byte by byte

            // 0xF0 = FFFF 0000
            result[j++] = HEX_ARRAY[(0xF0 & aByte) >>> 4];    // get the top 4 bits, first half hex char

            // 0x0F = 0000 FFFF
            result[j++] = HEX_ARRAY[(0x0F & aByte)];          // get the bottom 4 bits, second half hex char

            // combine first and second half, we get a complete hex
        }

        return String.valueOf(result);

    }

    /**
     * Read Given property form given property file in project resource
     *
     * @param propertyFileName as String Object with file name of property file
     * @param propertyRef as String Object with reference of property
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public byte[] convertStringToUnHexByteArrayWithBitwiseSift(String inputString, boolean lowercase) {

        char[] HEX_ARRAY = lowercase ? HEX_LOWER : HEX_UPPER;

        int nChars = inputString.length();

        if (nChars % 2 != 0) {
            throw new IllegalArgumentException(
                    "Hex-encoded string must have an even number of characters");
        }

        byte[] result = new byte[nChars / 2];                                  // 1 hex = 2 char

        for (int i = 0; i < nChars; i += 2) {                                  // step 2, 1 hex = 2 char
            int msb = Character.digit(inputString.charAt(i), 16);                         // char -> hex, base16
            int lsb = Character.digit(inputString.charAt(i + 1), 16);

            if (msb < 0 || lsb < 0) {
                throw new IllegalArgumentException(
                        "Detected a Non-hex character at " + (i + 1) + " or " + (i + 2) + " position");
            }
            result[i / 2] = (byte) ((msb << 4) | lsb);
        }
        return result;

    }

    /**
     * Read Given property form given property file in project resource
     *
     * @param propertyFileName as String Object with file name of property file
     * @param propertyRef as String Object with reference of property
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public String convertByteArrayToHexWithStringFormat(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte aByte : bytes) {
            result.append(String.format("%02x", aByte));
            // upper case
            // result.append(String.format("%02X", aByte));
        }
        return result.toString();
    }

    /**
     * Read Given property form given property file in project resource
     *
     * @param propertyFileName as String Object with file name of property file
     * @param propertyRef as String Object with reference of property
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public String convertStringToHexWithSpringCrypto(String inputString) {
        // display in lowercase, default
        char[] chars = org.springframework.security.crypto.codec.Hex.encode(inputString.getBytes(StandardCharsets.UTF_8));
        return String.valueOf(chars);
    }

    /**
     * Read Given property form given property file in project resource
     *
     * @param propertyFileName as String Object with file name of property file
     * @param propertyRef as String Object with reference of property
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public String convertByteArrayToHexWithSpringCrypto(byte[] bytes) {
        char[] result = org.springframework.security.crypto.codec.Hex.encode(bytes);
        return new String(result);
    }

    /**
     * Read Given property form given property file in project resource
     *
     * @param propertyFileName as String Object with file name of property file
     * @param propertyRef as String Object with reference of property
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public String convertStringToUnHexWithSpringCrypto(String hexString) {
        byte[] bytes = org.springframework.security.crypto.codec.Hex.decode(hexString);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * Read Given property form given property file in project resource
     *
     * @param propertyFileName as String Object with file name of property file
     * @param propertyRef as String Object with reference of property
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public byte[] convertStringToUnHexByteArrayWithSpringCrypto(String hexString) {
        byte[] bytes = org.springframework.security.crypto.codec.Hex.decode(hexString);
        return bytes;
    }



    /**
     * Read Given property form given property file in project resource
     *
     * @param propertyFileName as String Object with file name of property file
     * @param propertyRef as String Object with reference of property
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public void convertToHex(PrintStream out, File file) throws IOException
    {
        InputStream is = new FileInputStream(file);

        int bytesCounter =0;
        int value = 0;
        StringBuilder sbHex = new StringBuilder();
        StringBuilder sbText = new StringBuilder();
        StringBuilder sbResult = new StringBuilder();

        while ((value = is.read()) != -1) {
            //convert to hex value with "X" formatter
            sbHex.append(String.format("%02X ", value));

            //If the chracater is not convertable, just print a dot symbol "."
            if (!Character.isISOControl(value)) {
                sbText.append((char)value);
            }else {
                sbText.append(".");
            }

            //if 16 bytes are read, reset the counter,
            //clear the StringBuilder for formatting purpose only.
            if(bytesCounter==15){
                sbResult.append(sbHex).append("      ").append(sbText).append("\n");
                sbHex.setLength(0);
                sbText.setLength(0);
                bytesCounter=0;
            }else{
                bytesCounter++;
            }
        }

        //if still got content
        if(bytesCounter!=0){
            //add spaces more formatting purpose only
            for(; bytesCounter<16; bytesCounter++){
                //1 character 3 spaces
                sbHex.append("   ");
            }
            sbResult.append(sbHex).append("      ").append(sbText).append("\n");
        }

        out.print(sbResult);
        is.close();
    }

}
