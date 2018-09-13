package com.example.mateusz.batterytester.Model.Service;

import android.app.Activity;

import com.example.mateusz.batterytester.Model.DataAccess.ArduinoConnection;

import java.util.TimerTask;

public class ServiceTimer extends TimerTask {

    private Activity _activity;

    public ServiceTimer(Activity activity)
    {
        _activity = activity;
    }

    @Override
    public void run() {

        _activity.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                String buf = ("V                              ");
                ArduinoConnection connection = new ArduinoConnection(_activity);
                connection.set_receive(true);
                connection.execute(buf);
            }
        });
    }
}
