package com.skzh.iot;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kinderriven.zoosystem.R;
import com.zoo.IndoorManager;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by KinderRiven on 2016/6/15.
 */

/*
 *  你觉得我会告诉你这个类用来封装发送数据接口的吗？
 */
public class SerialPortApp_Write extends Thread{

    private final  static String TAG = "Serial Port Writing";
    private FileOutputStream fileOutputStream;
    private Context context;

    public SerialPortApp_Write(){

    }

    public SerialPortApp_Write(FileOutputStream fileOutputStream, Context context){
        this.fileOutputStream = fileOutputStream;
        this.context = context;
    }

    public void write(byte[] bytes){
        try {
            Log.i(TAG ,"发送数据zzz");
            fileOutputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
