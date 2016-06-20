package com.example.kinderriven.zoosystem;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.http.MessageSolver;
import com.skzh.iot.CmdManager;
import com.skzh.iot.SerialPort;
import com.skzh.iot.SerialPortApp;
import com.skzh.iot.SerialPortApp_Write;
import com.skzh.iot.SerialPortManager;
import com.zoo.IndoorManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class animal_status extends AppCompatActivity {

    private final String TAG = "STATUS";
    private int id;

    public void setUp(){

        ImageView image = (ImageView) findViewById(R.id.animal_status);

        image.setImageResource(R.drawable.movie);

        TextView temperText = (TextView)findViewById(R.id.temper_text);
        TextView humText = (TextView)findViewById(R.id.hum_text);
        TextView lightText = (TextView)findViewById(R.id.light_text);

        int temper = IndoorManager.getIndoorManager().getIndoorStatus(id).getTemper();
        int hum = IndoorManager.getIndoorManager().getIndoorStatus(id).getHumidity();
        int light = IndoorManager.getIndoorManager().getIndoorStatus(id).getLight();

        temperText.setText("温度 " + String.valueOf(temper));
        humText.setText("湿度 " + String.valueOf(hum));
        lightText.setText("亮度" + String.valueOf(light));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_status);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String sid = bundle.getString("type");

        switch (sid){
            case "A":
                id = 1;
                break;
            case "B":
                id = 2;
                break;
            case "C":
                id = 3;
                break;
            case "D":
                id = 4;
                break;
            case "E":
                id = 5;
                break;
            case "F":
                id = 6;
                break;
            default:
                id = 0;
                break;
        }
        setUp();
        handler.postDelayed(runnable, 1000);

        Button motor_run    = (Button)findViewById(R.id.motor_run);
        Button motor_stop   = (Button)findViewById(R.id.motor_stop);
        Button light_run    = (Button)findViewById(R.id.light_run);
        Button light_stop   = (Button)findViewById(R.id.light_stop);

        //控制电机
        assert motor_run != null;
        motor_run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i(TAG, "风扇启动 " + id);
                byte[] bytes = CmdManager.getCmdManager().getCmdMotor(id, 10);
                SerialPortManager.getSerialPortManager().startWrite(bytes);
            }
        });

        assert motor_stop != null;
        motor_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i(TAG, "风扇停止 " + id);
                byte[] bytes = CmdManager.getCmdManager().getCmdMotor(id, 12);
                SerialPortManager.getSerialPortManager().startWrite(bytes);
            }
        });

        assert light_run != null;
        light_run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i(TAG, "照明启动 " + id);
                byte[] bytes = CmdManager.getCmdManager().getCmdLight(id, 5);
                SerialPortManager.getSerialPortManager().startWrite(bytes);
            }
        });

        assert light_stop != null;
        light_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i(TAG, "照明停止 " + id);
                byte[] bytes = CmdManager.getCmdManager().getCmdLight(id, 0);
                SerialPortManager.getSerialPortManager().startWrite(bytes);
            }
        });
    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            setUp();
            handler.postDelayed(this, 1000);
        }
    };
}
