package com.aldi.tambakudangfinal;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class DataPH {

    public float PH;
    public long timestamp;

    public DataPH() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public DataPH(float PH, long timestamp) {
        this.PH = PH;
        this.timestamp = timestamp;
    }

    public float getPH() {
        return PH;
    }

    public long getTimestamp() {
        return timestamp;
    }
}