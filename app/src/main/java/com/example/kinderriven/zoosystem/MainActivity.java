package com.example.kinderriven.zoosystem;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.http.MessageSolver;
import com.skzh.iot.SerialPort;
import com.skzh.iot.SerialPortApp;
import com.skzh.iot.SerialPortManager;
import com.zoo.IndoorManager;
import com.zoo.SettingManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "Main";

    public void sendMessage(){

        Map<String, String> map = new HashMap<>();

        int temper = IndoorManager.getIndoorManager().getIndoorStatus(1).getTemper();
        int hum = IndoorManager.getIndoorManager().getIndoorStatus(1).getHumidity();
        int light = IndoorManager.getIndoorManager().getIndoorStatus(1).getLight();

        //发送温度、亮度、湿度
        map.put("SiteNum", "A");
        map.put("Tem", String.valueOf(temper));
        map.put("Hum", String.valueOf(hum));
        map.put("Lux", String.valueOf(light));

        new MessageSolver(this, SettingManager.getIndoorSetting().getServerURL())
                .sendMessage(map);

        //发送接近的动物

        Log.i(TAG, "正在向服务器发送消息");
    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            if(SettingManager.getIndoorSetting().getSend() == true) {
                sendMessage();
            }
            handler.postDelayed(this, 5000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //串口
        SerialPortApp serialPortApp = new SerialPortApp();
        SerialPort serialPort;
        SerialPortManager serialPortManager = null;

        try {

            //打开
            serialPortApp = new SerialPortApp();
            serialPort = serialPortApp.getSerialPort(this);

            //初始化
            SerialPortManager.getSerialPortManager().setSerialPort(serialPort, this);
            SerialPortManager.getSerialPortManager().init();

            //数据获取
            SerialPortManager.getSerialPortManager().startRead();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Button admin_button     = (Button)findViewById(R.id.admin_button);
        Button smart_button   = (Button) findViewById(R.id.smart_button);
        Button exit_button      = (Button)findViewById(R.id.exit_button);
        Button setting_button = (Button)findViewById(R.id.setting_button);
        Button about_button     = (Button)findViewById(R.id.about_button);


        assert admin_button != null;

        admin_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, animal_manager.class);
                startActivity(intent);
            }

        });

        assert smart_button != null;
        smart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, setting.class));
            }
        });

        assert setting_button != null;
        setting_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, app_setting.class));
            }
        });

        assert about_button != null;
        about_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, about.class));
            }
        });

        assert exit_button != null;
        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

        handler.postDelayed(runnable, 5000);
    }
}
