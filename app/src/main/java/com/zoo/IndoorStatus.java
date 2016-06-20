package com.zoo;

/* Created by KinderRiven on 2016/6/16.*/
public class IndoorStatus {

    private int temper;
    private int humidity;
    private int light;
    private int motor;

    public IndoorStatus(){
        temper = 0;
        humidity = 0;
        light = 0;
        motor = 0;
    }

    public void setTemper(int temper){
        this.temper = temper;
    }

    public void setHumidity(int humidity){
        this.humidity = humidity;
    }

    public void setLight(int light){
        this.light = light;
    }

    public void setMotor(int motor){
        this.motor = motor;
    }

    public int getTemper(){
        return temper;
    }
    public int getHumidity(){
        return humidity;
    }
    public int getLight(){
        return light;
    }
    public int getMotor(){
        return motor;
    }
}
