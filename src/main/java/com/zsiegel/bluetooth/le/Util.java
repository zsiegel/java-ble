package com.zsiegel.bluetooth.le;

import java.nio.ByteBuffer;

/**
 * @author zsiegel (zac.s@akta.com)
 */
public class Util {

    public static String uuidFromBytes(byte[] data) {
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

    public static int intFromUint16(byte[] data) {
        final byte[] result = new byte[4];

        result[0] = 0;
        result[1] = 0;
        result[2] = data[0];
        result[3] = data[1];

        return ByteBuffer.wrap(result).getInt();
    }
}
