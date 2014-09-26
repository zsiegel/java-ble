package com.zsiegel.bluetooth.le;

/**
 * GAP Data types
 *
 * see <a href="https://www.bluetooth.org/en-us/specification/assigned-numbers/generic-access-profile">Generic Access Profile</a>
 *
 * @author zsiegel (zac.s@akta.com)
 */
public class ADType {

    public static final int FLAGS = 0x01;
    public static final int UUID16_INC = 0x02;
    public static final int UUID16 = 0x03;
    public static final int UUID32_INC = 0x04;
    public static final int UUID32 = 0x05;
    public static final int UUID128_INC = 0x06;
    public static final int UUID128 = 0x07;
    public static final int LOCAL_NAME_INCOMPLETE = 0x08;
    public static final int LOCAL_NAME_COMPLETE = 0x09;
    public static final int TX_POWER_LEVEL = 0x0A;
    public static final int DEVICE_ID = 0x10;
    public static final int MANUFACTURER_SPECIFIC_DATA = 0xFF;
    public static final int SERVICE_DATA = 0x16;
    public static final int DEVICE_CLASS = 0x0D;
    public static final int SP_HASH_C = 0x0E;
    public static final int SP_RANDOMIZER_R = 0x0F;

    public static String descriptionForType(int type) {
        switch (type) {
            case FLAGS:
                return "Flags";
            case UUID16_INC:
                return "16-bit UUIDs (Incomplete)";
            case UUID16:
                return "16-bit UUIDs (Complete)";
            case UUID32_INC:
                return "32-bit UUIDs (Incomplete)";
            case UUID32:
                return "32-bit UUIDs (Complete)";
            case UUID128_INC:
                return "128-bit UUIDs (Incomplete)";
            case UUID128:
                return "128-bit UUIDs (Complete)";
            case LOCAL_NAME_COMPLETE:
                return "Local Name (Complete)";
            case LOCAL_NAME_INCOMPLETE:
                return "Local Name (Incomplete)";
            case TX_POWER_LEVEL:
                return "Tx Power Level";
            case DEVICE_CLASS:
                return "Device Class";
            case DEVICE_ID:
                return "Device ID";
            case MANUFACTURER_SPECIFIC_DATA:
                return "Manufacturer Data";
            case SERVICE_DATA:
                return "Service Data";
            case SP_HASH_C:
                return "Simple Pairing Hash C";
            case SP_RANDOMIZER_R:
                return "Simple Pairing Randomizer R";
            default:
                return "Unknown AdRecord Structure: " + type;
        }
    }
}
