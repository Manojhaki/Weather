package com.example.manojpun.weather;

/**
 * Created by manojpun on 7/19/17.
 */

public class CurrentWeather {

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }

    public double getTemperature() {
        return mTemperature;
    }

    public void setTemperature(double temperature) {
        mTemperature = temperature;
    }

    public double getHumidity() {
        return mHumidity;
    }

    public void setHumidity(double humidity) {
        mHumidity = humidity;
    }

    public double getPerceipChannel() {
        return mPerceipChannel;
    }

    public void setPerceipChannel(double perceipChannel) {
        mPerceipChannel = perceipChannel;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    private String mIcon;
    private long mTime;
    private double mTemperature;
    private double mHumidity;
    private double mPerceipChannel;
    private String mSummary;


}
