package com.zsiegel.bluetooth.le.common;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zsiegel
 */
public class AdvertisementData {

    private final byte[] rawData;
    private Map<Integer, AdvertisementRecord> records;

    public AdvertisementData(byte[] rawData) {
        super();
        this.rawData = rawData;
        parseRecords();
    }

    /**
     * Records are advertised in the format [LENGTH][TYPE][PAYLOAD]
     */
    private void parseRecords() {
        records = new HashMap<>();

        int idx = 0;
        while (idx < rawData.length) {

            final int recordLength = rawData[idx++];

            if (recordLength == 0) break;

            final byte typeData = rawData[idx];
            final int type = typeData & 0xFF;

            if (type == 0) break;

            final byte[] recordData = Arrays.copyOfRange(rawData, idx + 1, idx + recordLength);
            records.put(type, new AdvertisementRecord(recordLength, type, recordData));

            idx += recordLength;
        }
    }

    /**
     * @return the raw bytes of data
     */
    public byte[] getRawData() {
        return rawData;
    }

    /**
     * @return an unmodifiable view of the current advertising records
     */
    public Map<Integer, AdvertisementRecord> getRecords() {
        return Collections.unmodifiableMap(records);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[Advertisement Data \n --- Records ---\n");
        for (Integer type : records.keySet()) {
            builder.append("Type: " + ADType.descriptionForType(type));
            builder.append("\n");
            builder.append("Data :" + records.get(type));
            builder.append("\n");
        }
        builder.append("--- END Records ---" + "\n]\n");
        return builder.toString();
    }
}
