package com.skzh.iot;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import android.util.Log;

/* Get Serial_Port IO Stream */

/*
 	咳咳，这里是获取串口输入输出流的类
 	但是调用了底层的os包
 	以前在PC端上开发的时候用了一个comm的jar包，但是那个DT的comm貌似被废了，
 	而且在安卓设备上貌似无法调用。
 	咳咳，但是友♂善的skzh工作人员给我们写好了现成的接口！！！！
 	直接调用就可以了，看了一下原理，底层貌似是用c++编译成的os静态库
 	然后用JNI的API去调用了一下。（炫酷啊23333）
 	说时候挺想把这个os静态库反编译出来的QAQ
 */
//First
public class SerialPort {

	private static final String TAG = "SerialPort";


	private FileDescriptor mFd;
	private FileInputStream mFileInputStream;
	private FileOutputStream mFileOutputStream;

	//Dev
	public SerialPort(File device, int baudrate, int flags) throws SecurityException, IOException {

		//Permission
		if (!device.canRead() || !device.canWrite()) {
			try {
				Process su;
				su = Runtime.getRuntime().exec("/system/bin/su");
				String cmd = "chmod 666 " + device.getAbsolutePath() + "\n"
						+ "exit\n";
				Log.d("SerialPort", "cmd:" + cmd);
				
				su.getOutputStream().write(cmd.getBytes());
				if ((su.waitFor() != 0) || !device.canRead()
						|| !device.canWrite()) {
					throw new SecurityException();
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new SecurityException();
			}
		}

		
		Log.i(TAG,"正在打开串口流");
		mFd = open(device.getAbsolutePath(), baudrate, flags);
		if (mFd == null) {
			Log.e(TAG, "native open returns null");
			throw new IOException();
		}
		mFileInputStream = new FileInputStream(mFd);
		mFileOutputStream = new FileOutputStream(mFd);
	}


	public FileInputStream getInputStream() {
		return mFileInputStream;
	}

	public FileOutputStream getOutputStream() {
		return mFileOutputStream;
	}

	// JNI
	private native static FileDescriptor open(String path, int baudrate, int flags);
	public native void close();
	static {
		System.loadLibrary("serial_port");
	}
}
