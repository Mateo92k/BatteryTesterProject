package com.example.mateusz.batterytester;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

//import com.example.mateusz.app.Model.Domain.Objects.FuzzyConclusionSet;
import com.example.mateusz.batterytester.Model.Domain.Objects.ArduinoResponse;
import com.example.mateusz.batterytester.Model.Service.ArduinoTranslationService;
import com.example.mateusz.batterytester.Model.Service.RatingService;
import com.example.mateusz.batterytester.Model.Service.ServiceTimer;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;


public class TesterActivity extends AppCompatActivity {

    /*
    * R1 IF Price = Cheap AND Time = VeryBad THEN Rating = 2
    * R2 IF Price = Cheap AND Time = Bad THEN Rating = 3
    * R3 IF Price = Cheap AND Time = Good THEN Rating = 4
    * R4 IF Price = Cheap AND Time = VeryGood THEN Rating = 5
    * R5 IF Price = Average AND Time = VeryBad THEN Rating = 1
    * R6 IF Price = Average AND Time = Bad THEN Rating = 2
    * R7 IF Price = Average AND Time = Good THEN Rating = 4
    * R8 IF Price = Average AND Time = VeryGood Rating = 5
    * R9 IF Price = Expensive AND Time = VeryBad THEN Rating = 1
    * R10 IF Price = Expensive AND Time = Bad THEN Rating = 2
    * R11 IF Price = Expensive AND Time = Good THAN Rating = 3
    * R12 IF Price = Expensive AND Time = VeryGood THAN Rating = 4
    */

    Timer timer;
    boolean receive = false;
    ServiceTimer myTimerTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        timer = new Timer();
        myTimerTask = new ServiceTimer(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tester);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tester, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonStart(View view) {
        //TextView textViewStart = (TextView) findViewById(R.id.textView2);
        // textViewStart.setText("timer start");
        Button timerStartButton = (Button) findViewById(R.id.buttonStart);
        timerStartButton.setEnabled(false);
        timer.schedule(myTimerTask, 1000, 1000);
    }

    public void buttonStop(View view) {


        java.text.DecimalFormat df=new java.text.DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);

        //cena2 = Double.parseDouble(cena.getText().toString());
        //czas2 = Double.parseDouble(czas.getText().toString());

        RatingService ratingService = new RatingService();
        //FuzzyConclusionSet conclusionSet = new FuzzyConclusionSet();

       // double rate = ratingService.RateBattery(cena2,czas2);
        double rate = ratingService.RateBattery(1.2,20);
        RatingBar rBar = (RatingBar) findViewById(R.id.ratingBar);
        rBar.setRating((float)rate);

    }


    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

}
