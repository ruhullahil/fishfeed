package com.example.fishfeed;

public class Sub_set {
    private  String time;
    private  String mode ;

    public Sub_set() {
    }

    public Sub_set(String time , String mode) {
        this.time = time;
        this.mode = mode;
    }

    public String getTime() {
        return time;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
