package com.zsiegel.bluetooth.le;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @author zsiegel
 */
public class IBeacon {

    /*
        IBeacon format found at http://stackoverflow.com/questions/18906988/what-is-the-ibeacon-bluetooth-profile

        02 # Number of bytes that follow in first AD structure
        01 # Flags AD type
        1A # Flags value 0x1A = 000011010
        bit 0 (OFF) LE Limited Discoverable Mode
        bit 1 (ON) LE General Discoverable Mode
        bit 2 (OFF) BR/EDR Not Supported
        bit 3 (ON) Simultaneous LE and BR/EDR to Same Device Capable (controller)
        bit 4 (ON) Simultaneous LE and BR/EDR to Same Device Capable (Host)
        1A # Number of bytes that follow in second (and last) AD structure
        FF # Manufacturer specific data AD type
        4C 00 # Company identifier code (0x004C == Apple)
        02 # Byte 0 of iBeacon advertisement indicator
        15 # Byte 1 of iBeacon advertisement indicator
        e2 c5 6d b5 df fb 48 d2 b0 60 d0 f5 a7 10 96 e0 # iBeacon proximity uuid
        00 00 # major
        00 00 # minor
        c5 # The 2's complement of the calibrated Tx Power
    */

    private static final byte[] IBEACON_PREFIX = new byte[]{0x4C, 0x00, 0x02, 0x15};

    private int txPower, companyId, iBeaconAdvertisement, major, minor = 0;
    private String uuid = "";

    /**
     * Method that determines if the manufacturer data conforms to the ibeacon format
     *
     * @param mfrData the manufacturer data from the advertisement
     * @return true if the data conforms to the ibeacon format
     */
    public static boolean isIBeacon(byte[] mfrData) {

        if (mfrData == null) return false;
        if (!(mfrData.length >= 25)) return false;
        if (mfrData.length < IBEACON_PREFIX.length) return false;

        return Arrays.equals(Arrays.copyOfRange(mfrData, 0, 4), IBEACON_PREFIX);
    }

    /**
     * Creates and parses the ibeacon data if possible
     * <p/>
     * If the data is not an ibeacon no data will be parsed
     *
     * @param mfrData the manufacturer data from the advertisement
     */
    public IBeacon(byte[] mfrData) {

        if (!isIBeacon(mfrData)) {
            return;
        }

        companyId = getIntFrom2ByteArray(reverseArray(Arrays.copyOfRange(mfrData, 0, 2)));
        iBeaconAdvertisement = getIntFrom2ByteArray(Arrays.copyOfRange(mfrData, 2, 4));
        uuid = uuidFromBytes(Arrays.copyOfRange(mfrData, 4, 20));
        major = getIntFrom2ByteArray(Arrays.copyOfRange(mfrData, 20, 22));
        minor = getIntFrom2ByteArray(Arrays.copyOfRange(mfrData, 22, 24));
        txPower = mfrData[24];
    }

    private String uuidFromBytes(byte[] data) {
        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < data.length; i++) {
            if (i == 4) {
                sb.append('-');
            }
            if (i == 6) {
                sb.append('-');
            }
            if (i == 8) {
                sb.append('-');
            }
            if (i == 10) {
                sb.append('-');
            }

            int digit = data[i] & 0xFF;
            sb.append(Integer.toHexString(digit));
        }

        return sb.toString();
    }

    public static int getIntFrom2ByteArray(byte[] data) {
        final byte[] result = new byte[4];

        result[0] = 0;
        result[1] = 0;
        result[2] = data[0];
        result[3] = data[1];

        return ByteBuffer.wrap(result).getInt();
    }

    public static byte[] reverseArray(byte[] data) {
        final int size = data.length;
        byte temp;

        for (int i = 0; i < size / 2; i++) {
            temp = data[i];
            data[i] = data[size - 1 - i];
            data[size - 1 - i] = temp;
        }

        return data;
    }

    public int getTxPower() {
        return txPower;
    }

    public int getCompanyId() {
        return companyId;
    }

    public int getiBeaconAdvertisement() {
        return iBeaconAdvertisement;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public String getUuid() {
        return uuid;
    }
}