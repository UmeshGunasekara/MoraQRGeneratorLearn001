/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slmora.learn.app.qrgenerate.test.test;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DILAN
 */
public class GetCurrentDateAndTime {

    Calendar currentdate = Calendar.getInstance();
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    TimeZone obj = TimeZone.getTimeZone("Asia/Colombo");

    public Date dateTime() {
        formatter.setTimeZone(obj);
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            d = sdf.parse(formatter.format(currentdate.getTime()));
        } catch (ParseException ex) {
            Logger.getLogger(GetCurrentDateAndTime.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }
    
    public Date dateTimeWithAddDate(int days) {
        currentdate.add(Calendar.DATE, days);
        formatter.setTimeZone(obj);
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            d = sdf.parse(formatter.format(currentdate.getTime()));
        } catch (ParseException ex) {
            Logger.getLogger(GetCurrentDateAndTime.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }
    
    public Date dateTimeWithAddMonth(int months) {
        currentdate.add(Calendar.MONTH, months);
        formatter.setTimeZone(obj);
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            d = sdf.parse(formatter.format(currentdate.getTime()));
        } catch (ParseException ex) {
            Logger.getLogger(GetCurrentDateAndTime.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }
    
    public Date addDateToDate(Date d,int days) {
        Calendar curD = Calendar.getInstance();
        curD.setTime(d);
        curD.add(Calendar.DATE, days);
        return curD.getTime();
    }
    
    public Date addMonthToDate(Date d,int months) {
        Calendar curD = Calendar.getInstance();
        curD.setTime(d);
        curD.add(Calendar.MONTH, months);
        return curD.getTime();
    }

    public long getDateTimeInstance() {
        return System.currentTimeMillis();
    }

    public String convertTime(long time) {
        Date date = new Date(time);
        Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
        return format.format(date);
    }

    public long daystolongtime(int days) {
        return days * 86400000;
    }

    public long longtimetodays(long longtime) {
        return longtime / 86400000;
    }

    public long getTimeDiffLong(long keyactivatedtime) {
        long systime = System.currentTimeMillis();
        return systime - keyactivatedtime;
    }

    public boolean getTimeDiffBoolean(long timeperiod, long longkeytime) {
        long systime = System.currentTimeMillis();
        if ((systime - longkeytime) >= timeperiod) {
            return true;
        } else {
            return false;
        }
    }

}
