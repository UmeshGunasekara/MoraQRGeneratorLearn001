/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 4/4/2021 11:29 PM
 */
package com.slmora.learn.common.qr;

import com.google.zxing.WriterException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * This Class created for
 *
 * @Author: SLMORA
 * @DateTime: 4/4/2021 11:29 PM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          4/4/2021      SLMORA                Initial Code
 */
public class MoraEmvCoQR
{
    final static Logger LOGGER = LogManager.getLogger(MoraEmvCoQR.class);

    public void reValidateEmvCoQR(String qrSourceData, String outQRImagePath) throws IOException, WriterException
    {
        MoraCreateQR moraCreateQR =  new MoraCreateQR();
        MoraCalculateCRC moraCalculateCRC =  new MoraCalculateCRC();
        String payload = qrSourceData.substring(0, qrSourceData.length()-4);
        System.out.println(payload);
        String reValidatedPayload = payload.concat(moraCalculateCRC.getCRC16_CCITT_False_X16_X12_X5_1_Poly_1021(payload));

        moraCreateQR.createMoraQR(outQRImagePath, reValidatedPayload, 2000, 0, 0, 0);

    }
}
