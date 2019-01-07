package com.aldi.tambakudangfinal;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Parameter {

    public  float PHMin;
    public  float PHMax;
    public  float SuhuMin;
    public  float SuhuMax;
    public  long waktuIn;
    public  long waktuOut;

    public  Parameter(){

    }

    public Parameter(float PHMin, float PHMax, float suhuMin, float suhuMax, long waktuIn, long waktuOut) {
        this.PHMin = PHMin;
        this.PHMax = PHMax;
        SuhuMin = suhuMin;
        SuhuMax = suhuMax;
        this.waktuIn = waktuIn;
        this.waktuOut = waktuOut;
    }
}
