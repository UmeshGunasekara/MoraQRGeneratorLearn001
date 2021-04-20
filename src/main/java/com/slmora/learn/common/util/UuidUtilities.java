/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 9/26/2020 4:24 PM
 */
package com.slmora.learn.common.util;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.StringJoiner;
import java.util.UUID;

/**
 * This Class created for manipulate UUIDs
 *
 * @Author: SLMORA
 * @DateTime: 9/26/2020 4:24 PM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          9/26/2020      SLMORA                Initial Code
 */
public class UuidUtilities
{
    final static Logger LOGGER = LogManager.getLogger(UuidUtilities.class);

    private volatile SecureRandom numberGenerator = null;
    private final long MSB = 0x8000000000000000L;

    /**
     * Read Given UUID and print properties of UUID
     * UUID String
     * UUID Variant
     * UUID Version
     * UUID Most Significant 64 Bits
     * UUID Last Significant 64 Bits
     * UUID Time Stamp
     * UUID Clock Sequence
     * UUID Node
     * @param uuid as UUID Object for analyze
     * @return return console out with UUID analyzed details
     * @throws UnsupportedOperationException with file notfound or compatibility issue
     * @apiNote Read properties of given UUID and print
     * @Note analyze the given UUID object
     */
    public void printUUIDDetails(UUID uuid){
        LOGGER.info("printUUIDDetails() is called with uuid = "+uuid);
        System.out.println("UUID String : "+uuid.toString());
        System.out.println("UUID Variant : "+uuid.variant());
        System.out.println("UUID Version : "+uuid.version());
        System.out.println("UUID Most Significant 64 Bits : "+uuid.getMostSignificantBits());
        System.out.println("UUID Last Significant 64 Bits : "+uuid.getLeastSignificantBits());
        long uuidTimeStamp = 0;
        try{
            uuidTimeStamp =  uuid.timestamp();
        }catch (UnsupportedOperationException ex){
            LOGGER.error(ExceptionUtils.getFullStackTrace(ex));
        }
        if(uuidTimeStamp != 0L){
            System.out.println("UUID Time Stamp : "+ uuidTimeStamp);
            System.out.println("UUID Clock Sequence : "+uuid.clockSequence());
            System.out.println("UUID Node : "+uuid.node());
        }else{
            System.out.println("UUID Time Stamp : Give UUID is not an time based one, No Time Stamp");
            System.out.println("UUID Clock Sequence : Give UUID is not an time based one, No Clock Sequence");
            System.out.println("UUID Node : Give UUID is not an time based one, No Node");
        }

    }

    /**
     * Will Returns string of given UUID without hyphens
     * @param uuid as UUID Object
     * @return return String Object with by removing hyphens from given UUID String
     * @apiNote remove hyphens from given UUID
     * @Note this will remove all hyphens in uuid as example 123e4567-e89b-12d3-a456-426614174000 will
     * returns 123e4567e89b12d3a456426614174000
     */
    public String removeHyphens(UUID uuid){
        return uuid.toString().replace("-","");
    }

    /**
     * Read Given UUID and print properties of UUID
     * @return return console out with UUID analyzed details
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read properties of given UUID and print
     * @Note analyze the given UUID object
     */
    public String getUniqueStringUUID() {
        SecureRandom ng = numberGenerator;
        if (ng == null) {
            numberGenerator = ng = new SecureRandom();
        }
        return Long.toHexString(MSB | ng.nextLong()) + Long.toHexString(MSB | ng.nextLong());
    }

    /**
     * Read Given UUID and print properties of UUID
     * @return return console out with UUID analyzed details
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read properties of given UUID and print
     * @Note analyze the given UUID object
     */
    public String getOrderedUUIDString(UUID uuid) {
        String uuidString = uuid.toString();
        return uuidString.substring(14,18) +
                uuidString.substring(9,13) +
                uuidString.substring(0,8) +
                uuidString.substring(19,23) +
                uuidString.substring(24);
    }

    /**
     * Read Given UUID and print properties of UUID
     * @return return console out with UUID analyzed details
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read properties of given UUID and print
     * @Note analyze the given UUID object
     */
    public UUID getUUIDFromOrderedUUIDString(String orderedUUIDString) {
        StringJoiner joiner = new StringJoiner("-");
        joiner
                .add(orderedUUIDString.substring(8,16))
                .add(orderedUUIDString.substring(4,8))
                .add(orderedUUIDString.substring(0,4))
                .add(orderedUUIDString.substring(16,20))
                .add(orderedUUIDString.substring(20));
        return UUID.fromString(joiner.toString());
    }

    /**
     * Read Given UUID and print properties of UUID
     * @return return console out with UUID analyzed details
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read properties of given UUID and print
     * @Note analyze the given UUID object
     */
    public byte[] getOrderedUUIDByteArrayFromUUIDWithApacheCommons(UUID uuid) {
        HexUtils hexUtils = new HexUtils();
        return hexUtils.convertStringToUnHexByteArrayWithApacheCommons(getOrderedUUIDString(uuid));
    }

    /**
     * Read Given UUID and print properties of UUID
     * @return return console out with UUID analyzed details
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read properties of given UUID and print
     * @Note analyze the given UUID object
     */
    public UUID getUUIDFromOrderedUUIDByteArrayWithApacheCommons(byte[] orderedUUID) {
        HexUtils hexUtils = new HexUtils();
        String orderedUUIDString = hexUtils.convertByteArrayToHexWithApacheCommons(orderedUUID);
        return getUUIDFromOrderedUUIDString(orderedUUIDString);
    }


//    public byte[] hexStringToByteArray(String s) {
//        int len = s.length();
//        byte[] data = new byte[len / 2];
//        for (int i = 0; i < len; i += 2) {
//            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
//                    + Character.digit(s.charAt(i+1), 16));
//        }
//        return data;
//    }
//
//    static final String HEXES = "0123456789ABCDEF";
//
//    public static String byteArrayToHexString( byte [] raw ) {
//        if ( raw == null ) {
//            return null;
//        }
//        final StringBuilder hex = new StringBuilder( 2 * raw.length );
//        for ( final byte b : raw ) {
//            hex.append(HEXES.charAt((b & 0xF0) >> 4))
//                    .append(HEXES.charAt((b & 0x0F)));
//        }
//        return hex.toString();
//    }
}
