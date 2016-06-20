package com.example.kinderriven.zoosystem;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zoo.SettingManager;

public class app_setting extends AppCompatActivity {

    public void setUp(){


        EditText idText = (EditText)findViewById(R.id.app_id_text);
        EditText serverText = (EditText)findViewById(R.id.app_server_text);
        EditText timeText = (EditText)findViewById(R.id.app_time_text);

        String serverIP = SettingManager.getIndoorSetting().getServerURL();
        String id = SettingManager.getIndoorSetting().getId();
        int time = SettingManager.getIndoorSetting().getTime();

        if (idText != null) {
            idText.setText(id);
        }
        if (serverText != null) {
            serverText.setText(serverIP);
        }
        if (timeText != null) {
            timeText.setText(String.valueOf(time));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_setting);
        setUp();

        Button change_button = (Button)findViewById(R.id.app_change_button);
        Button start_button = (Button)findViewById(R.id.app_start_button);
        Button stop_button = (Button)findViewById(R.id.app_stop_button);

        assert change_button != null;
        change_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(app_setting.this)
                        .setTitle("终端提示")
                        .setMessage("修改成功")
                        .setPositiveButton("OK", null).show();
            }
        });

        assert start_button != null;
        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingManager.getIndoorSetting().setSend(true);
                new AlertDialog.Builder(app_setting.this)
                        .setTitle("终端提示")
                        .setMessage("开始发送")
                        .setPositiveButton("OK", null).show();
            }
        });

        assert stop_button != null;
        stop_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingManager.getIndoorSetting().setSend(false);
                new AlertDialog.Builder(app_setting.this)
                        .setTitle("终端提示")
                        .setMessage("停止发送")
                        .setPositiveButton("OK", null).show();
            }
        });
    }
}
