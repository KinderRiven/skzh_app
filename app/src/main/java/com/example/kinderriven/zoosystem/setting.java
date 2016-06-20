package com.example.kinderriven.zoosystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ImageView[] imageViews = new ImageView[6];
        imageViews[0] = (ImageView) findViewById(R.id.image_sbear);
        imageViews[1] = (ImageView) findViewById(R.id.image_spanda);
        imageViews[2] = (ImageView) findViewById(R.id.image_stortoise);
        imageViews[3] = (ImageView) findViewById(R.id.image_socean);
        imageViews[4] = (ImageView) findViewById(R.id.image_sinsects);
        imageViews[5] = (ImageView) findViewById(R.id.image_ssnake);

        for(int i = 0; i < 6; i++){

            final char id = (char)(i + 'A');

            imageViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(setting.this, animal_setting.class);
                    intent.putExtra("type", String.valueOf(id));
                    startActivity(intent);
                }
            });
        }
    }
}
