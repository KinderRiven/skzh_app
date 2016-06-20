package com.function;

/**
 * Created by KinderRiven on 2016/6/15.
 */

/*
 *  功能类、单例化
 */
public class BinarySolver {


    private final  static BinarySolver binarySolver = new BinarySolver();

    private BinarySolver(){

    }

    public static BinarySolver getBinarySolver(){
        return binarySolver;
    }

    //1个byte转2个16进制
    //_ _ _ _ / _ _ _ _
    public int[] _1byteTo2Hex(byte _byte){

        int []hex = new int[2];
        int _bit;

        for(int i = 1; i >= 0; i--){
            hex[i] = 0;
            _bit = 1;
            for(int j = 0; j < 4; j++){
                if((_byte & 1) == 1){
                    hex[i] += _bit;
                }
                _bit = _bit << 1;
                _byte = (byte) (_byte >> 1);
            }
        }
        return hex;
    }

    public int byteToInt(byte _byte){

        int value = 0;
        int bit = 1;

        for(int i = 0; i < 8; i++){

            if((_byte & 1) == 1){
                value += bit;
            }

            bit = bit << 1;
            _byte = (byte)(_byte >> 1);
        }

        return value;
    }
}
