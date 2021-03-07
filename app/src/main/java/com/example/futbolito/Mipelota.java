package com.example.futbolito;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class Mipelota extends View implements SensorEventListener {
    Paint pincel=new Paint();
    int tam=40;
    int alto, ancho;
    int borde=12;
    float ejx=0,ejy=0,ejz1=0,ejz=0;
    String x,y,z;
    public Mipelota(Context context) {
        super(context);
        SensorManager sd= (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        Sensor sr=sd.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sd.registerListener(this,sr,SensorManager.SENSOR_DELAY_FASTEST);
        Display pantalla= ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        ancho=pantalla.getWidth();
        alto=pantalla.getHeight();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        ejx-=event.values[0];
        x=Float.toString(ejx);
        if (ejx<(tam+borde)){
            ejx=(tam+borde);
        }else if (ejx>(ancho-(tam+borde))){
            ejx=ancho-(tam+borde);
        }
        ejy+=event.values[1];
        y=Float.toString(ejy);
        if (ejy<(tam+borde)){
            ejy=(tam+borde);
        }else if (ejy>(alto-tam-170)){
            ejy=alto-tam-170;
        }
        ejz=event.values[2];
        z=Float.toString(ejz);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    public void OnDraw(Canvas lin){
        pincel.setColor(Color.RED);
        lin.drawCircle(ejx,ejy,ejz+tam,pincel);
        pincel.setColor(Color.BLUE);
        pincel.setTextSize(25);
        lin.drawText("hola",ejx-35,ejy+3,pincel);
    }
}
