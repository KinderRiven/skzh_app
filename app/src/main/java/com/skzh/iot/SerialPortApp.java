package com.skzh.iot;

import java.io.File;
import java.io.IOException;

import android.content.Context;//���ݵ������� �����������ﾳʲô��
import android.content.SharedPreferences; 


//Second
public class SerialPortApp {

    private SerialPort mSerialPort = null;
    public static final String PREFS_NAME = "SerialPort";

    public SerialPort getSerialPort(Context context) throws SecurityException,IOException {

		//bt lv
		int baudrate = 115200;
    	String path  = "/dev/";
        if (mSerialPort == null) {

            /* Open the serial port */
        	SharedPreferences sp = context.getSharedPreferences(SerialPortApp.PREFS_NAME, Context.MODE_PRIVATE);
        	//String  device = "s3c2410_serial3";//A8:s3c2410_serial0,S3C6410:s3c_serial0,s3c2410_serial3
        	String  device = "s3c2410_serial1";

        	if(sp.contains("device")){
        		device = sp.getString("device", "s3c2410_serial1");
        	

        	}else{
        		sp.edit().putString("device", device).commit();
        	}
        	path = path + device;
        	
            mSerialPort = new SerialPort(new File(path), baudrate, 0);

        }
        return mSerialPort;
    }

	public void closeSerialPort() {
	        if (mSerialPort != null) {
	                mSerialPort.close();
	                mSerialPort = null;
	        }
	}


}
