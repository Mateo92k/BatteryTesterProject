package com.example.mateusz.batterytester.Model.DataAccess;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.mateusz.batterytester.Model.Domain.Objects.ArduinoResponse;
import com.example.mateusz.batterytester.Model.Domain.Objects.InterClassDataHolder;
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
//  private double price;
    private final int _serverPort = 8888;
    private final String _servierAddres = "192.168.1.177";
    private DatagramSocket _datagramSocked;
    private InetAddress _inetAddress;
    private DatagramPacket _datagramPacket;
    private String _rawResponse;
    private Boolean _receive;


    public ArduinoConnection(Activity activity){
        _activity = activity;
    }

    protected void onPreExecute() {
    }


    public Boolean doInBackground(String... buf1) {

        boolean result = false;
        byte[] buffer = buf1[0].getBytes();

        try {
            _datagramSocked = new DatagramSocket();
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            _inetAddress = InetAddress.getByName(_servierAddres);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        _datagramPacket = new DatagramPacket(buffer, buffer.length, _inetAddress, _serverPort);
        try {
            _datagramSocked.send(_datagramPacket);
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }


        if (_receive == true) {
            Log.v("dd", "try to receive");
            try {
                _datagramSocked.receive(_datagramPacket);
                _rawResponse = new String(_datagramPacket.getData());

                Log.v("Data received ", "S: Received: '" + _rawResponse + "'");

            } catch (IOException e) {

                Log.v("Exception", "Error :" + e.getMessage());

            }
        }
        return result;
    }


    @Override
    protected void onCancelled() {
    }

    @Override
    protected void onPostExecute(Boolean result) {
        Boolean haveToStop = false;
        ArduinoResponse response = null;
        ArduinoTranslationService translationService = new ArduinoTranslationService();
 //       TesterActivity testerActivity = new TesterActivity();
        if (result) {
            Log.v("Results", "onPostExecute: Completed with an Error.");
        } else {
            Log.v("Results", "onPostExecute: Completed.");


            if (_receive == true) {

                TextView time = _activity.findViewById(R.id.textViewTimeResult);
                TextView temperature = _activity.findViewById(R.id.textViewTempResult);
                TextView voltage = _activity.findViewById(R.id.textViewVoltageResult);
                TextView current = _activity.findViewById(R.id.textViewCurrentResult);
                TextView capacity = _activity.findViewById(R.id.textViewCapacityResult);



                translationService.set_rawResponse(_rawResponse);

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


                if(response.get_stop()){
                    haveToStop=true;
                    Log.v("haveToStop", "Value: " + true);
                }
            }
        }

        InterClassDataHolder interClassDataHolder = InterClassDataHolder.getInstance();
        if(haveToStop){
            RatingService ratingService = new RatingService();
            double rate = ratingService.RateBattery(interClassDataHolder.getPrice(),translationService.GetSecondsFromTime());

            RatingBar rBar = _activity.findViewById(R.id.ratingBar);
            rBar.setRating((float)rate);

            this.cancel(haveToStop);
        }

    }

    //Methods is called everytime a new String is recieved from the socket connection
    @Override
    protected void onProgressUpdate(byte[]... values) {
    }


    public void set_receive(Boolean _receive) {
        this._receive = _receive;
    }
}
