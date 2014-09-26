package com.zsiegel.le;

import com.zsiegel.bluetooth.le.IBeacon;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author zsiegel
 */
public class IBeaconTest {

    //Example mfr data pulled from http://stackoverflow.com/questions/18906988/what-is-the-ibeacon-bluetooth-profile
    private byte[] MFR_DATA = new byte[]{0x4c, 0x00, 0x02, 0x15, (byte) 0xe2, (byte) 0xc5, 0x6d, (byte) 0xb5, (byte) 0xdf, (byte) 0xfb, 0x48, (byte) 0xd2, (byte) 0xb0, 0x60, (byte) 0xd0, (byte) 0xf5, (byte) 0xa7, 0x10, (byte) 0x96, (byte) 0xe0, 0x00, 0x00, 0x00, 0x00, (byte) 0xc5};

    private String UUID = "E2C56DB5-DFFB-48D2-B060-D0F5A71096E0";

    @Test
    public void testParseIBeaconData() {
        IBeacon iBeacon = new IBeacon(MFR_DATA);
        assertEquals(0, iBeacon.getMajor());
        assertEquals(0, iBeacon.getMinor());
        assertEquals(-59, iBeacon.getTxPower());
        assertEquals(UUID, iBeacon.getUuid().toUpperCase());
        assertEquals(76, iBeacon.getCompanyId());
        assertEquals(533, iBeacon.getiBeaconAdvertisement());
    }

    @Test
    public void testIsIBeacon() {
        assertEquals(true, IBeacon.isIBeacon(MFR_DATA));
    }
}
