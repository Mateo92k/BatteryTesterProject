package com.example.mateusz.batterytester.Model.DataAccess;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.mateusz.batterytester.Model.Domain.Objects.ArduinoResponse;
import com.example.mateusz.batterytester.Model.Service.ArduinoTranslationService;
import com.example.mateusz.batterytester.Model.Service.RatingService;
import com.example.mateusz.batterytester.R;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ArduinoConnection extends AsyncTask<String, byte[], Boolean> {

    Activity _activity;
    int server_port = 8888;
    DatagramSocket s;
    InetAddress local;
    // int msg_length;
    //  byte[] message;
    DatagramPacket p;
    private String _rawResponse;
    private Boolean _recive;


    public ArduinoConnection(Activity activity){
        _activity = activity;
    }

    protected void onPreExecute() {
        Log.v("dd", "exec");

    }


    public Boolean doInBackground(String... buf1) {

        boolean result = false;
        byte[] buf = buf1[0].getBytes();
        Log.v("dd", "buf1[0] " + buf1[0]);

        try {
            s = new DatagramSocket();
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            local = InetAddress.getByName("192.168.1.177");
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //msg_length = messageStr.length();
        //message = messageStr.getBytes();
        p = new DatagramPacket(buf, buf.length, local, server_port);
        try {
            s.send(p);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }


        if (_recive == true) {
            Log.v("dd", "try to receive");
            try {
                s.receive(p);
                _rawResponse = new String(p.getData());

                Log.v("dd", "S: Received: '" + _rawResponse + "'");

            } catch (IOException e) {

                Log.v("dd", "blad odbioru danych");

            }
        }


        return result;
    }


    @Override
    protected void onCancelled() {
    }

    //Method is called after taskexecution
    @Override
    protected void onPostExecute(Boolean result) {
        Boolean haveToStop = false;
        String part4 = "";
        String stop = "";
        if (result) {
            Log.v("dd", "onPostExecute: Completed with an Error.");
        } else {
            Log.v("dd", "onPostExecute: Completed.");


            if (_recive == true) {
                _recive = false;

                TextView time = _activity.findViewById(R.id.textViewTimeResult);
                TextView temperature = _activity.findViewById(R.id.textViewTempResult);
                TextView voltage = _activity.findViewById(R.id.textViewVoltageResult);
                TextView current = _activity.findViewById(R.id.textViewCurrentResult);
                TextView capacity = _activity.findViewById(R.id.textViewCapacityResult);

                ArduinoTranslationService translationService = new ArduinoTranslationService();
                translationService.set_rawResponse(_rawResponse);
                ArduinoResponse response = null;
                try {
                    response = translationService.ProcessResponse();
                }catch (Exception e){
                    Log.v("Translation Exception", "Some Exception occurred during translation " + e.getMessage());
                }

                if(response == null){
                    return;
                }


                temperature.setText(response.get_temperature());
                voltage.setText(response.get_voltage());
                current.setText(response.get_current());
                time.setText(response.get_time());
                capacity.setText(response.get_capacity());

                Log.v("haveToStop", "Stop: " + stop);

                if(response.get_stop()){
                    haveToStop=true;
                    Log.v("haveToStop", "Value: " + true);
                }
            }
        }


        if(haveToStop){
            RatingService ratingService = new RatingService();
            double rate = ratingService.RateBattery(1.2,20);
            RatingBar rBar = _activity.findViewById(R.id.ratingBar);
            rBar.setRating((float)rate);

            this.cancel(haveToStop);
        }

    }

    //Methods is called everytime a new String is recieved from the socket connection
    @Override
    protected void onProgressUpdate(byte[]... values) {

        //   MainActivity.TempUpdate(odebrane);
        if (values.length > 0) {//if the recieved data is at least one byte

        }
    }

    public Boolean get_recive() {
        return _recive;
    }

    public void set_recive(Boolean _recive) {
        this._recive = _recive;
    }
}
