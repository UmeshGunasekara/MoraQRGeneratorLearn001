/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slmora.learn.app.qrgenerate.test.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Umesh Gunasekara
 */
public class GenereteQR {
    
    public static void main(String[] args) {
        File srichatLogo = new File("D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraQRGeneratorLearn001_20210403001\\images\\in\\srichatlogo.png");
        try {
            MoraCreateNewQR createQR = new MoraCreateNewQR();
//            String qrText = "https://qr.alipay.com/ocx00500vovy3fhtvuddmd6";//Jewel Qudsi
//            String qrText = "https://qr.alipay.com/ocx08230mbjrpvogjn4mm17";
//            String qrText = "00020101021126440010com.myfave0126https://myfave.com/qr/511727630010com.alipay0145https://qr.alipay.com/ocx00500vovy3fhtvuddmd651800007SG.SGQR01121809132E11F3020701.00030306339511040202050209060400000708201809185204000053037025802SG5911DRAGON BOWL6009Singapore630443F4";

//            String qrText = "00020101021126440010com.mifave0126https://myfave.com/qr/511727630010com.alipay0145https://qr.alipay.com/ocx08230mbjrpvogjn4mm1751800007SG.SGQR01121809132E11F3020701.00030306339511040202050209060400000708201809185204000053037025802SG5911DRAGON BOWL6009Singapore6304F692";

            String qrText = "00020101021126440010com.myfave0126https://myfave.com/qr/511727630010com.alipay0145https://qr.alipay.com/ocx08230mbjrpvogjn4mm1751800007SG.SGQR01121809132E11F3020701.00030306339511040202050209060400000708201809185204000053037025802SG5911DRAGON BOWL6009Singapore630443F4";
            BufferedImage myQR = createQR.makeQRBuf(qrText, 2000, 0, 0, 0);
            ImageIO.write(myQR, "PNG", new File("D:\\SLMORAWorkSpace\\IntelliJProjects\\MoraQRGeneratorLearn001_20210403001\\images\\out\\myQR.png"));
            
//            BufferedImage milogobuf=createQR.resizeImageWithHint(srichatLogo,300,300);
//            
//            BufferedImage devicefinalQRbuf=createQR.mergeImageBuf(milogobuf,myQR);
//            ImageIO.write(devicefinalQRbuf, "PNG", new File("F:/otherimg/devicefinalQRbuf.png"));
        
        } catch (IOException ex) {
            Logger.getLogger(GenereteQR.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
