package com.example.futbolito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView balon,ver;
    TextView gool;
    private SensorManager sensorManager;
    private Sensor sensor;
    float ejx=0,ejy=0;
    float px=490,py=950;
    private SensorEventListener evento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        balon=findViewById(R.id.imageView2);
        ver=findViewById(R.id.imageView);
        gool=findViewById(R.id.textView);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        evento=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                ejx=event.values[0];
                ejy=event.values[1];
                px=px+(ejx*-10);
                py=py+(ejy*10);
                if (px<38){
                    px=38;
                }else if(px>945){
                    px=945;
                }
                if (py<40){
                    py=40;
                }else if (py>1847){
                    py=1847;
                }
                balon.setTranslationX(px);
                balon.setTranslationY(py);
                if(py<=40&&px>400&&px<590){
                    gool.setVisibility(View.VISIBLE);
                }else if (py>=1847&&px>400&&px<590){
                    gool.setVisibility(View.VISIBLE);
                }else{
                    gool.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sensorManager.registerListener(evento,sensor,SensorManager.SENSOR_DELAY_GAME);
    }

}

