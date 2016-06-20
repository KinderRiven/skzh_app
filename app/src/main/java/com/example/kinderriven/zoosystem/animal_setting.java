package com.example.kinderriven.zoosystem;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zoo.SettingManager;

public class animal_setting extends AppCompatActivity {

    private final String TAG = "SETTING";
    private int id = 0;

    public void setUp(){

        EditText max_temper = (EditText) findViewById(R.id.max_temper_text);
        EditText min_temper = (EditText) findViewById(R.id.min_temper_text);
        EditText max_light = (EditText) findViewById(R.id.max_light_text);
        EditText min_light = (EditText) findViewById(R.id.min_light_text);

        int _max_temper =
                SettingManager.getIndoorSetting().getSettingGroup(id).getMaxTemper();
        int _min_temper =
                SettingManager.getIndoorSetting().getSettingGroup(id).getMinTemper();
        int _max_light =
                SettingManager.getIndoorSetting().getSettingGroup(id).getMaxLight();
        int _min_light =
                SettingManager.getIndoorSetting().getSettingGroup(id).getMinLight();

        Log.i(TAG, "" + _max_temper);
        
        if(max_temper != null)
            max_temper.setText(String.valueOf(_max_temper));
        if(min_temper != null)
            min_temper.setText(String.valueOf(_min_temper));
        if(max_light != null)
            max_light.setText(String.valueOf(_max_light));
        if(min_light != null)
            min_light.setText(String.valueOf(_min_light));
    }

    public void change(){

        EditText max_temper = (EditText) findViewById(R.id.max_temper_text);
        EditText min_temper = (EditText) findViewById(R.id.min_temper_text);
        EditText max_light = (EditText) findViewById(R.id.max_light_text);
        EditText min_light = (EditText) findViewById(R.id.min_light_text);

        String s_max_temper = max_temper.getText().toString();
        String s_min_temper = min_temper.getText().toString();
        String s_max_light = max_light.getText().toString();
        String s_min_light = min_light.getText().toString();

/*        if(s_max_temper != null)*/
        SettingManager.getIndoorSetting().getSettingGroup(id).
                setMaxTemper(Integer.parseInt(s_max_temper));

/*        if(s_min_temper != null)*/
        SettingManager.getIndoorSetting().getSettingGroup(id).
                setMinTemper(Integer.parseInt(s_min_temper));

/*        if(s_max_light != null)*/
        SettingManager.getIndoorSetting().getSettingGroup(id).
                setMaxLight(Integer.parseInt(s_max_light));

/*        if(s_min_light != null)*/
        SettingManager.getIndoorSetting().getSettingGroup(id).
                setMinLight(Integer.parseInt(s_min_light));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_animal_setting);

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

        Log.i(TAG, "" + id);

        setUp();

        Button change_button = (Button) findViewById(R.id.animal_setting_change_button);
        Button smart_button = (Button) findViewById(R.id.setting_smart_button);
        Button un_smart_button = (Button) findViewById(R.id.setting_un_smart_button);



        assert change_button != null;
        change_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(animal_setting.this)
                        .setTitle("终端提示")
                        .setMessage("修改成功")
                        .setPositiveButton("OK", null).show();
                change();
            }
        });

        assert smart_button != null;
        smart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(animal_setting.this)
                        .setTitle("终端提示")
                        .setMessage("智能模式开启")
                        .setPositiveButton("OK", null).show();
                SettingManager.getIndoorSetting().getSettingGroup(id).setSmart(true);
            }
        });

        assert un_smart_button != null;
        un_smart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(animal_setting.this)
                        .setTitle("终端提示")
                        .setMessage("智能模式关闭")
                        .setPositiveButton("OK", null).show();
                SettingManager.getIndoorSetting().getSettingGroup(id).setSmart(false);
            }
        });
    }
}
