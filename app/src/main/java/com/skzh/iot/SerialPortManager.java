package com.skzh.iot;

import android.content.Context;

import java.io.Serializable;

/*
    写这些串口参数是非常非常非常....(x2048)辛苦的
    碰到了各种问题
    当然，最蛋疼的是os包无法读取，不过最后还是解决了 23333
    原来是包名不对，需要把包名修改成skzh.iot
    wc，这个问题真的是非常的DT
 */
/*
 * Created by KinderRiven on 2016/6/15.
 */
public class SerialPortManager implements Serializable{

    private final static SerialPortManager serialPortManager = new SerialPortManager();
    private Context context;

    /* My Class*/
    private SerialPortApp_Write serialPortApp_write;
    private SerialPortApp_Read 	serialPortApp_read;
    private SerialPort serialPort;

    private SerialPortManager(){

    }

    public void setSerialPort(SerialPort serialPort, Context context){
        this.serialPort = serialPort;
        this.context = context;
    }

    public static SerialPortManager getSerialPortManager(){
        return serialPortManager;
    }

    public boolean init(){

        if(this.serialPort != null){

            if(this.serialPort.getInputStream() != null && this.serialPort.getOutputStream() != null){
                this.serialPortApp_read
                        = new SerialPortApp_Read(this.serialPort.getInputStream(), this.context);
                this.serialPortApp_write
                        = new SerialPortApp_Write(this.serialPort.getOutputStream(), this.context);
                return true;
            }
            else
                return false;
        }
        else{
            return false;
        }
    }

    public boolean startRead(){
        serialPortApp_read.start();
        return true;
    }

    public boolean startWrite(byte[] bytes){
        serialPortApp_write.write(bytes);
        return true;
    }
}
