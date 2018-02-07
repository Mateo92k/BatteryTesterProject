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
    * R12 IF Price = Expensive AND Time = VeryGood THAN Rating 4
    */

    Timer timer;
    boolean receive = false;
    MyTimerTask myTimerTask;
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        timer = new Timer();
        myTimerTask = new MyTimerTask();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tester);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

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
        RatingBar rBar = (RatingBar) findViewById(R.id.ratingBar);
        int a = 3;
        rBar.setRating(a);

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

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


    class polaczenie extends AsyncTask<String, byte[], Boolean> {

        int server_port = 8888;
        DatagramSocket s;
        InetAddress local;
       // int msg_length;
      //  byte[] message;
        DatagramPacket p;
        String odebrane;



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


            if (receive == true) {
                Log.v("dd", "try to receive");
                try {
                    s.receive(p);
                    byte[] test = p.getData();
                    odebrane = new String(p.getData());

                    Log.v("dd", "S: Received: '" + odebrane + "'");

                } catch (IOException e) {

                    Log.v("dd", "blad odbioru danych");

                }
            }
            //Log.v("dd", "S: Received: '" + new String(p.getData()) + "'");
            //	MainActivity.Tview1.setText(new String(p.getData()));


            return result;
        }


        @Override
        protected void onCancelled() {

        }

        //Method is called after taskexecution
        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                Log.v("dd", "onPostExecute: Completed with an Error.");
            } else {
                Log.v("dd", "onPostExecute: Completed.");

                // MainActivity.TempUpdate(odebrane);
                if (receive == true) {
                    receive = false;

                    TextView time = (TextView) findViewById(R.id.textViewTimeResult);
                    TextView temperature = (TextView) findViewById(R.id.textViewTempResult);
                    TextView voltage = (TextView) findViewById(R.id.textViewVoltageResult);
                    TextView current = (TextView) findViewById(R.id.textViewCurrentResult);
                    TextView capacity = (TextView) findViewById(R.id.textViewCapacityResult);

                    String[] parts_odebrane = odebrane.split("-");
                    String part1 = parts_odebrane[0];
                    String part2 = parts_odebrane[1];
                    String part3 = parts_odebrane[2];
                    String part4 = parts_odebrane[3];
                    String part5 = parts_odebrane[4];

                    temperature.setText(part1);
                    voltage.setText(part2);
                    current.setText(part3);
                    time.setText(part4);
                    capacity.setText(part5);


                }


            }

        }

        //Methods is called everytime a new String is recieved from the socket connection
        @Override
        protected void onProgressUpdate(byte[]... values) {

            //   MainActivity.TempUpdate(odebrane);
            if (values.length > 0) {//if the recieved data is at least one byte

            }
        }

    }
    class MyTimerTask extends TimerTask {

        @Override
        public void run() {

            runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    String buf = ("V                              ");
                    receive = true;
                    new polaczenie().execute(buf, null, null);

                }
            });
        }
    }
}
