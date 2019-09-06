package com.example.fishfeed;



public class time {

    public String set_time;
    public String duration;

    public time() {
    }

    public time(String duration,String set_time) {
        this.duration = duration;
        this.set_time = set_time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSet_time() {
        return set_time;
    }

    public void setSet_time(String set_time) {
        this.set_time = set_time;
    }
}
