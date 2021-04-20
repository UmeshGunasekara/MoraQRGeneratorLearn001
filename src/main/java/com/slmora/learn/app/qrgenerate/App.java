/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 4/4/2021 11:52 AM
 */
package com.slmora.learn.app.qrgenerate;

/**
 * This Class created for
 *
 * @Author: SLMORA
 * @DateTime: 4/4/2021 11:52 AM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          4/4/2021      SLMORA                Initial Code
 */
public class App
{
    public static void main(String[] args)
    {
        System.out.println("0&0 : "+(0&0));
        System.out.println("0|0 : "+(0|0));
        System.out.println("0^0 : "+(0^0));
        System.out.println("~0 : "+(~0));
        System.out.println("1&0 : "+(1&0));
        System.out.println("1|0 : "+(1|0));
        System.out.println("1^0 : "+(1^0));
        System.out.println("~1 : "+(~1));
        System.out.println("0&1 : "+(0&1));
        System.out.println("0|1 : "+(0|1));
        System.out.println("0^1 : "+(0^1));
        System.out.println("1&1 : "+(1&1));
        System.out.println("1|1 : "+(1|1));
        System.out.println("1^1 : "+(1^1));

        System.out.println("1(00000000000000000000000000000001)>>1(00000000000000000000000000000001) : "+(1>>1));
        System.out.println("1(00000000000000000000000000000001)>>2(00000000000000000000000000000010) : "+(1>>2));
        System.out.println("1(00000000000000000000000000000001)>>3(00000000000000000000000000000011) : "+(1>>3));
        System.out.println("1(00000000000000000000000000000001)>>4(00000000000000000000000000000100) : "+(1>>4));
        System.out.println("1(00000000000000000000000000000001)>>8(00000000000000000000000000001000) : "+(1>>8));

        System.out.println("1(00000000000000000000000000000001)>>1(00000000000000000000000000000001) : "+(1>>1));
        System.out.println("2(00000000000000000000000000000010)>>1(00000000000000000000000000000001) : "+(1>>2));
        System.out.println("3(00000000000000000000000000000011)>>1(00000000000000000000000000000001) : "+(1>>3));
        System.out.println("4(00000000000000000000000000000100)>>1(00000000000000000000000000000001) : "+(1>>4));
        System.out.println("8(00000000000000000000000000001000)>>1(00000000000000000000000000000001) : "+(1>>8));
    }
}
