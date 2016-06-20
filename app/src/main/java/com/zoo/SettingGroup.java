package com.zoo;

import android.os.Handler;
import android.util.Log;

import com.skzh.iot.CmdManager;
import com.skzh.iot.SerialPortManager;

/**
 * Created by KinderRiven on 2016/6/17.
 */
public class SettingGroup {

    private int id;
    private final static String TAG = "SettingGroup";

    private int maxTemper;
    private int minTemper;

    private int maxLight;
    private int minLight;

    private boolean isSmart;

    public void setSmart(boolean isSmart){
        this.isSmart = isSmart;
    }

    public boolean getSmart(){
        return this.isSmart;
    }

    public void smartSolve(){

        int light = IndoorManager.getIndoorManager().getIndoorStatus(id).getLight();

        if(light < minLight){
            byte[] bytes = CmdManager.getCmdManager().getCmdLight(id, 5);
            SerialPortManager.getSerialPortManager().startWrite(bytes);
            Log.i(TAG, "[" + id + "] 室内亮度过低 " + light + " " + minLight);
        }
        if(light > maxLight){
            byte[] bytes = CmdManager.getCmdManager().getCmdLight(id, 0);
            SerialPortManager.getSerialPortManager().startWrite(bytes);
            Log.i(TAG, "[" + id + "] 室内亮度过高 " + light + " " + maxLight);
        }
    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(isSmart == true) {
                smartSolve();
            }
            handler.postDelayed(this, 10000);
        }
    };

    public SettingGroup(int id){

        this.id = id;

        maxTemper = 50;
        minTemper = 10;

        maxLight = 130;
        minLight = 80;

        handler.postDelayed(runnable, 10000);
    }

    public void setMaxTemper(int maxTemper){
        this.maxTemper = maxTemper;
    }

    public void setMaxLight(int maxLight) {
        this.maxLight = maxLight;
    }

    public void setMinLight(int minLight) {
        this.minLight = minLight;
    }

    public int getMaxTemper() {
        return maxTemper;
    }

    public int getMinTemper() {
        return minTemper;
    }

    public int getMaxLight() {
        return maxLight;
    }

    public int getMinLight() {
        return minLight;
    }

    public void setMinTemper(int minTemper){
        this.minTemper = minTemper;
    }

}
