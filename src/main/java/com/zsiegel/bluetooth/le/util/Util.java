package com.zsiegel.bluetooth.le.util;

import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * @author zsiegel (zac.s@akta.com)
 */
public class Util {

    public static UUID uuidFromBytes(byte[] data) {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        return new UUID(buffer.getLong(), buffer.getLong());
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
