package com.skzh.iot;

import android.content.Context;
import android.util.Log;

import com.http.MessageSolver;
import com.zoo.IndoorManager;
import com.zoo.SettingManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
 * Created by KinderRiven on 2016/6/15.
 */
public class SerialPortApp_Read extends  Thread{

    private final  static String TAG = "Serial Port Reading";
    private FileInputStream fileInputStream;
    private Context context;
/*    private Map<String, String>messageMap = new HashMap<>();*/

    public SerialPortApp_Read(){

    }


    public SerialPortApp_Read(FileInputStream fileInputStream, Context context){
        this.fileInputStream = fileInputStream;
        this.context = context;
    }

    /* 更新温度等信息 */
    public void updateMessage(int id, int temper, int hum, int light){

        char cid = (char)(id + 'A' - 1);

        String _id = String.valueOf(cid);
        String _temper = String.valueOf(temper);
        String _hum = String.valueOf(hum);
        String _light = String.valueOf(light);

/*      messageMap.put("SiteNum", _id);
        messageMap.put("Tem", _temper);
        messageMap.put("Hum", _hum);
        messageMap.put("Lux", _light);*/

        Log.i(TAG, "[温度]" + _temper + " [湿度]" + _hum + " [亮度]" + _light);

        IndoorManager.getIndoorManager().getIndoorStatus(id).setTemper(temper);
        IndoorManager.getIndoorManager().getIndoorStatus(id).setHumidity(hum);
        IndoorManager.getIndoorManager().getIndoorStatus(id).setLight(light);
    }

    /* 接触发送信息*/
    public void touchMessage(int id, int value){

        if(value == 0){

        }
        if(value == 1){


            char type = 'A';

            switch (id){
                case 1: type = 'T'; break;
                default: break;
            }

            Log.i(TAG, "动物" + type + "靠近了");
            //发送消息
            Map<String, String>sendMap = new HashMap<>();
            sendMap.put("AnimalType", String.valueOf(type));
            sendMap.put("MobileNum", SettingManager.getIndoorSetting().getId());

            new MessageSolver(context, SettingManager.getIndoorSetting().getServerURL())
                    .sendMessage(sendMap);
        }
    }
    /* 读消息 */
    public void run(){

        Log.i(TAG, "串口读取启动");

        int len = 0;
        byte[] bytes = new byte[1024];

        int id = 0, temper = 0, humidity = 0, light = 0, touch = 0;

        try {
            while((len = fileInputStream.read(bytes)) > 0){

                int type = CmdManager.getCmdManager().getCmdType(bytes);
                Log.i(TAG, "读入字节流" + len + " " + type);
                Log.i(TAG, "编号" + CmdManager.getCmdManager().getID(bytes));
                switch (type){
                    case CmdManager.THB:

                        id = CmdManager.getCmdManager().getID(bytes);
                        temper = CmdManager.getCmdManager().getTemper(bytes);
                        humidity = CmdManager.getCmdManager().getHumidity(bytes);
                        light = CmdManager.getCmdManager().getLight(bytes);
                        updateMessage(id, temper, humidity, light);

                        break;
                    case CmdManager.ULT:
                        Log.i(TAG, "距离" + CmdManager.getCmdManager().getDist(bytes));
                        break;
                    case CmdManager.VIB:
                        Log.i(TAG, "震动传感器");
                        break;
                    case CmdManager.INF:
                        Log.i(TAG, "红外线");
                        break;
                    case CmdManager.TOUCH:
                        id = CmdManager.getCmdManager().getID(bytes);
                        touch = CmdManager.getCmdManager().getTouch(bytes);
                        touchMessage(id, touch);
                        break;
                    default:
                        break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "串口读取结束");
    }

}
