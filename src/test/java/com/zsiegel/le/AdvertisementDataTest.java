package com.zsiegel.le;

import com.zsiegel.bluetooth.le.ADType;
import com.zsiegel.bluetooth.le.AdvertisementData;
import com.zsiegel.bluetooth.le.AdvertisementRecord;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author zsiegel
 */
public class AdvertisementDataTest {

    private static final byte[] BLE_SPEC_EXT_INQUIRY_EXAMPLE = new byte[]{0x06, 0x09, 0x50, 0x68, 0x6F, 0x6E, 0x65, 0x05, 0x03, 0x15, 0x11, 0x1F, 0x11, 0x01, 0x05, 0x01, 0x07, 0x00};
    public static final String UTF_8 = "UTF-8";

    @Test
    public void testParseExtendedSpecExample() throws UnsupportedEncodingException {

        AdvertisementData advertisementData = new AdvertisementData(BLE_SPEC_EXT_INQUIRY_EXAMPLE);

        Map<Integer,AdvertisementRecord> records = advertisementData.getRecords();
        assertEquals(4, records.size());
        for (AdvertisementRecord record : records.values()) {
            if (record.getType() == ADType.LOCAL_NAME_COMPLETE) {
                assertTrue("Phone".equals(new String(record.getData(), UTF_8)));
            }

            if (record.getType() == ADType.UUID16) {
                assertTrue(record.getData().length == 4);
            }

            if (record.getType() == ADType.UUID32) {
                assertTrue(record.getData().length == 0);
            }

            if (record.getType() == ADType.UUID128) {
                assertTrue(record.getData().length == 0);
            }
        }
    }
}
