/**
 * 
 */
package com.skzh.iot;

import com.function.BinarySolver;

import java.util.Vector;

/*
 *	嗯，这里就是所有命令的中枢，不论是命令的读取识别，还是获取发送命令都在这里
 *  基本所有传感器都做了，除了几个电压电流用不上的~~~~~
 *	少年，我看你骨骼惊奇、天资聪慧（差一点赶上我了）
 *  这里的代码你就自己看吧
 */
public class CmdManager {

	private final static CmdManager cmdManager = new CmdManager();

	private Vector<byte[]> vecBytes = new Vector<>();

	public final static int THB = 0;
	public final static int ULT = 1;
	public final static int VIB = 2;
	public final static int INF = 3;
	public final static int TOUCH = 4;

	public static CmdManager getCmdManager(){
		return cmdManager;
	}


	private CmdManager(){

		/*
			1-5位 检验位置
			6、7位 温度		XH*256+XL
			8、9位 湿度		XH*256+XL
			10、11位 光照	 	(GH*256+GL)*3012.9/(32768*4)
		*/
		byte [] CMD_HEAD_THB =
				{(byte)0x40, (byte)0x0C, (byte)0x01, (byte)0x02, (byte)0x01};
		vecBytes.add(CMD_HEAD_THB);

		/*
			1-5位 检验位置
			6-9位 距离
		 */
		byte [] CMD_HEAD_ULT =
				{(byte)0x40, (byte)0x0A, (byte)0x01, (byte)0x08, (byte)0x01};
		vecBytes.add(CMD_HEAD_ULT);

		/* 震动消息 */
		byte [] CMD_HEAD_VIB =
				{(byte)0x40, (byte)0x07, (byte)0x01, (byte)0x03, (byte)0x01};
		vecBytes.add(CMD_HEAD_VIB);

		/* 红外线 */
		byte [] CMD_HEAD_INF =
				{(byte)0x40, (byte)0x07, (byte)0x01, (byte)0x05,(byte)0x01};
		vecBytes.add(CMD_HEAD_INF);

		/* 触摸*/
		byte [] CMD_HEAD_TOUCH =
				{(byte)0x40, (byte)0x07, (byte)0x01, (byte)0x07,(byte)0x01};
		vecBytes.add(CMD_HEAD_TOUCH);
	}

	public int getID(byte[] bytes){
		int id = (int)bytes[2];
		return  id;
	}

	public int getTemper(byte[] bytes){

		int temper = 0;

		int h_bit = BinarySolver.getBinarySolver().byteToInt(bytes[5]);
		int l_bit = BinarySolver.getBinarySolver().byteToInt(bytes[6]);

		temper = h_bit * 256 + l_bit;
		return temper;
	}

	public int getHumidity(byte[] bytes){

		int humidity = 0;

		int h_bit = BinarySolver.getBinarySolver().byteToInt(bytes[7]);
		int l_bit = BinarySolver.getBinarySolver().byteToInt(bytes[8]);

		humidity = h_bit * 256 + l_bit;
		return humidity;
	}

	public int getLight(byte[] bytes){

		int light = 0;

		int h_bit = BinarySolver.getBinarySolver().byteToInt(bytes[9]);
		int l_bit = BinarySolver.getBinarySolver().byteToInt(bytes[10]);

		light = (int)((h_bit * 256 + l_bit) * 3012.9 / (32768 * 4));
		return  light;
	}

	/* 距离、单位MM*/
	public int getDist(byte[] bytes){

		int dist = 0;

		int h_bit = BinarySolver.getBinarySolver().byteToInt(bytes[5]);
		int l_bit = BinarySolver.getBinarySolver().byteToInt(bytes[6]);

		dist = h_bit * 256 + l_bit;
		return dist;
	}

	/* 是否有震动 */
	public int getVib(byte[] bytes){
		return (int)(bytes[5]);
	}

	/* 红外线是否有人*/
	public int getINF(byte[] bytes){
		return (int)(bytes[5]);
	}

	/* 判断是否有触摸*/
	public int getTouch(byte[] bytes){
		return (int)(bytes[5]);
	}

	public int getCmdType(byte[] bytes){

		for(int i = 0; i < vecBytes.size(); i++){

			byte [] tmpBytes = vecBytes.get(i);
			boolean ok = true;

			for(int j = 0; j < tmpBytes.length && j < bytes.length; j++){
				if(j!= 2 && bytes[j] != tmpBytes[j]){
					ok = false;
					break;
				}
			}
			if(ok)
				return i;
		}
		return -1;
	}

	public byte[] getCmdLight(int id, int level){

		if(level < 0) level = 0;
		if(level > 9) level = 9;
		int sum = 0x40 + 0x06 + id + 0x09 + level;
		byte[] bytes = {(byte)0x40, (byte)0x06, (byte)id, (byte)0x09,(byte)level, (byte)sum};
		return bytes;
	}

	/*
		type: 12 停止 10正转 11反转
	*/
	public byte[] getCmdMotor(int id, int type){

		int sum = 0x40 + 0x06 + id + 0x06 + type;
		byte[] bytes = {(byte)0x40, (byte)0x06, (byte)id, (byte)0x06, (byte)type, (byte)sum};
		return bytes;

	}


}
