package com.example.kinderriven.zoosystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.skzh.iot.SerialPortManager;

public class animal_manager extends AppCompatActivity {

    private final String TAG = "ANIMAL_MANAGER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_manager);

        ImageView[] imageViews = new ImageView[6];
        imageViews[0] = (ImageView) findViewById(R.id.image_bear);
        imageViews[1] = (ImageView) findViewById(R.id.image_panda);
        imageViews[2] = (ImageView) findViewById(R.id.image_tortoise);
        imageViews[3] = (ImageView) findViewById(R.id.image_ocean);
        imageViews[4] = (ImageView) findViewById(R.id.image_insects);
        imageViews[5] = (ImageView) findViewById(R.id.image_snake);

        for(int i = 0; i < 6; i++){

            final char id = (char)(i + 'A');

            imageViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent();
                    intent.setClass(animal_manager.this, animal_status.class);
                    intent.putExtra("type", String.valueOf(id));
                    startActivity(intent);
                }
            });
        }
    }
}
