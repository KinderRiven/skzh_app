package com.zoo;

/*
 * Created by KinderRiven on 2016/6/16.
 */
public class IndoorManager {

    private final  static IndoorManager indoorManager = new IndoorManager();
    private IndoorStatus[] indoorStatuses;

    private IndoorManager(){

        indoorStatuses = new IndoorStatus[10];

        for(int i = 0; i < 10; i++){
            indoorStatuses[i] = new IndoorStatus();
        }
    }

    public static IndoorManager getIndoorManager(){
        return indoorManager;
    }

    public IndoorStatus getIndoorStatus(int index){
        return indoorStatuses[index];
    }
}
