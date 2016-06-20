package com.example.kinderriven.zoosystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.http.MessageSolver;
import com.skzh.iot.SerialPort;
import com.skzh.iot.SerialPortApp;
import com.skzh.iot.SerialPortManager;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class tourist_main extends AppCompatActivity {

    private final static String TAG = "tourist_activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourists_main);

    }
}
