package com.zoo;

/**
 * Created by KinderRiven on 2016/6/17.
 */
public class SettingManager {

    private final static SettingManager indoorSetting = new SettingManager();

    private SettingGroup[] settingGroups;

    private String serverURL;

    private String id;

    private int time;

    private boolean isSend;

    public void setTime(int time){
        this.time = time;
    }

    public int getTime(){
        return this.time;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public String getServerURL(){
        return this.serverURL;
    }

    public void setServerURL(String serverURL){
        this.serverURL = serverURL;
    }

    private SettingManager(){

        this.settingGroups = new SettingGroup[10];
        this.isSend = false;
        this.id = "1";
        this.time = 5000;
        this.serverURL = "http://www.leedabao.com/aZigbee/ReceiveData.php";

        for(int i = 0; i < 10; i++)
            this.settingGroups[i] = new SettingGroup(i);
    }

    public boolean getSend(){
        return isSend;
    }

    public void setSend(boolean isSend){
        this.isSend = isSend;
    }

    public static SettingManager getIndoorSetting(){
        return indoorSetting;
    }

    public SettingGroup getSettingGroup(int index){
        return this.settingGroups[index];
    }
}
