/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 4/4/2021 12:03 AM
 */
package com.slmora.learn.app.qrgenerate.test;

import java.nio.charset.StandardCharsets;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

/**
 * This Class created for
 *
 * @Author: SLMORA
 * @DateTime: 4/4/2021 12:03 AM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          4/4/2021      SLMORA                Initial Code
 */
public class CalculateCRC32ChecksumForByteArray
{
    public static void main(String[] args) {

        //43F4
        CalculateCRC32ChecksumForByteArray a = new CalculateCRC32ChecksumForByteArray();
        a.testD();

    }

    public void testD(){

        //43F4
        String payload="00020101021126440010com.myfave0126https://myfave.com/qr/511727630010com.alipay0145https://qr.alipay.com/ocx08230mbjrpvogjn4mm1751800007SG.SGQR01121809132E11F3020701.00030306339511040202050209060400000708201809185204000053037025802SG5911DRAGON BOWL6009Singapore6304";

        //5855
//        String payload ="000201010211153125000344000203441000000000000065204597253033445802HK5913Test Merchant6002HK62600120000000000000000000000520000000000000000000000708000000106304";

        //4F34
//        String payload = "000201010212021646494686498667022632002816454000000001000001000000005204541153031445406150.525502015802LK5924RAVINDIKA KALHARA HERATH6013Agarapathana 6212070840100000";


        int checksum = 0xffff;
        int polynomial = 0x1021;

        byte[] data = payload.getBytes(StandardCharsets.UTF_8);
        for (byte b : data) {
            for (int i = 0; i < 8; i++) {
                boolean bit = ((b >> (7 - i) & 1) == 1);
                boolean c15 = ((checksum >> 15 & 1) == 1);
                checksum <<= 1;
                if (c15 ^ bit) {
                    checksum ^= polynomial;
                }
            }
        }
        checksum &= 0xffff;

        System.out.println(String.format("%04X", checksum));
    }

    public void testC(){
        //43F4
//        String str="00020101021126440010com.myfave0126https://myfave.com/qr/511727630010com.alipay0145https://qr.alipay.com/ocx08230mbjrpvogjn4mm1751800007SG.SGQR01121809132E11F3020701.00030306339511040202050209060400000708201809185204000053037025802SG5911DRAGON BOWL6009Singapore";//A228";

        //5855
        String str ="000201010211153125000344000203441000000000000065204597253033445802HK5913Test Merchant6002HK62600120000000000000000000000520000000000000000000000708000000106304";
        byte data[] = str.getBytes();

        long POLYNOMIAL   = 0x1021;
        long PRESET_VALUE = 0xFFFF;
        long current_crc_value = PRESET_VALUE;

        for (int i = 0; i < data.length; i++ )
        {
            current_crc_value ^= data[i] & 0xFF;
            for (int j = 0; j < 8; j++)
            {
                if ((current_crc_value & 1) != 0)
                {
                    current_crc_value = (current_crc_value >>> 1) ^ POLYNOMIAL;
                }
                else
                {
                    current_crc_value = current_crc_value >>> 1;
                }
            }
        }

        System.out.println(current_crc_value);

        System.out.println(current_crc_value & 0xFFFF);

        current_crc_value = ~current_crc_value;

        System.out.println(current_crc_value);

//        return current_crc_value & 0xFFFF;

        System.out.println(current_crc_value & 0xFFFF);
    }

    public void testB(){
        String str="00020101021126440010com.myfave0126https://myfave.com/qr/511727630010com.alipay0145https://qr.alipay.com/ocx08230mbjrpvogjn4mm1751800007SG.SGQR01121809132E11F3020701.00030306339511040202050209060400000708201809185204000053037025802SG5911DRAGON BOWL6009Singapore";//A228";

        byte arr[] = str.getBytes();

        long polynomial = 0x1021;
        long CRC = 0xFFFF;

        for (byte b : arr)
        {
            CRC ^= b & 0xFF;
            for (int i = 8; i != 0; i--)
            {
                if ((CRC & 0x0001) != 0)
                {
                    CRC = (CRC >> 1) ^ polynomial;
                }
                else
                {
                    CRC >>= 1;
                }
            }
        }
        System.out.println(CRC);
    }

    public void testA(){
        String input = "Java Code Geeks - Java Examples";

        // get bytes from string
        byte bytes[] = input.getBytes();

        Checksum checksum = new CRC32();

        // update the current checksum with the specified array of bytes
        checksum.update(bytes, 0, bytes.length);

        // get the current checksum value
        long checksumValue = checksum.getValue();

        System.out.println("CRC32 checksum for input string is: " + checksumValue);
    }
}
