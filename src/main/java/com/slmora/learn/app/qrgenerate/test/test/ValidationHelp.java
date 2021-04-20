/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slmora.learn.app.qrgenerate.test.test;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author A.U.D.M.Gunasekara
 */
public class ValidationHelp {
    public boolean checkEMailAddres(String email) {
        boolean ok=false;
        String mail = email;

        boolean o = mail.contains(".") && (!mail.contains(" ") && mail.contains("@"));

        if (o) {

            int a = mail.lastIndexOf(".");
            int l = mail.length();
            String com = mail.substring(a + 1, l);

            Pattern p = Pattern.compile("[0-9]");
            Matcher m = p.matcher(com);
            int i = 0;
            while (m.find()) {
                i++;
            }

            String notcom = mail.substring(0, a);

            boolean z = notcom.contains("@");

            String surche = null;
            String name = null;
            if (z) {

                boolean z2 = com.contains("@");

                int n = notcom.length();
                int b = notcom.lastIndexOf("@");
                surche = notcom.substring(b + 1, n);
                name = notcom.substring(0, b);

                boolean z3 = name.contains("@");
                boolean z4 = surche.contains(".");

                if (z2 || z3 || z4 || com.length() < 2 || surche.length() < 2 || name.length() < 3 || i > 0) {
                    ok=false;
                }else{
                    ok=true;
                }

            } else {
                ok=false;

            }
        } else {
            ok=false;
        }
        return ok;

    }
    
    public String chooceDigits(long value, int maxInt, int minInt) {

        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumIntegerDigits(maxInt);
        nf.setMinimumIntegerDigits(minInt);
        nf.setGroupingUsed(false);
        String vals = nf.format(value);
        return vals;
    }
}
