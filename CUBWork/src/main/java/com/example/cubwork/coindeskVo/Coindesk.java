package com.example.cubwork.coindeskVo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * @author light
 */
public class Coindesk {


    public CoindeskTime time;

    public String disclaimer;

    public String chartName;

    private Map<String, CoindeskBpi> bpi;

    public CoindeskTime getTime() {
        return time;
    }

    public void setTime(CoindeskTime time) {
        this.time = time;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public String getChartName() {
        return chartName;
    }

    public void setChartName(String chartName) {
        this.chartName = chartName;
    }

    public Map<String, CoindeskBpi> getBpi() {
        return bpi;
    }

    public void setBpi(Map<String, CoindeskBpi> bpi) {
        this.bpi = bpi;
    }

}
