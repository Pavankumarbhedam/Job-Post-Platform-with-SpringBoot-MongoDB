package com.pavan.JobPostApplication.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;

@Document(collection = "JobPostCollection")
public class JobData {
    private String profile;
    private String des;
    private int exp;
    private String[] tech;

  public JobData(){}
    public JobData(String des, int exp, String profile, String[] tech) {
        this.des = des;
        this.exp = exp;
        this.profile = profile;
        this.tech = tech;
    }
    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String[] getTech() {
        return tech;
    }

    public void setTech(String[] tech) {
        this.tech = tech;
    }

    @Override
    public String toString() {
        return "JobData{" +
                "profile='" + profile + '\'' +
                ", des='" + des + '\'' +
                ", exp=" + exp +
                ", tech=" + Arrays.toString(tech) +
                '}';
    }

}
