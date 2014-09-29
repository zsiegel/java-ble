package com.zsiegel.bluetooth.le.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * @author zsiegel (zac.s@akta.com)
 */
public class LittleEndian {

    /**
     * Converts a byte array of data to an integer
     * Automatically pads byte arrays with length < 4
     * @param data a byte array
     * @return an integer
     */
    public static int getInt(byte[] data) {
        return ByteBuffer.wrap(Arrays.copyOf(data, 4)).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }
}
