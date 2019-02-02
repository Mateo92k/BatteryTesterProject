package com.example.mateusz.batterytester;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.mateusz.batterytester.Model.Domain.Objects.InterClassDataHolder;
import com.example.mateusz.batterytester.Model.Service.ServiceTimer;

import java.util.Timer;


public class TesterActivity extends AppCompatActivity {

    Timer timer;
    ServiceTimer myTimerTask;
    EditText EditTextPriceDialog;
    double PriceDialog;
    Context activityContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tester);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




    }

    protected void inicialize(){
        timer = new Timer();
        myTimerTask = new ServiceTimer(this);

        activityContext = this;

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
            Intent about_intent = new Intent(this,AboutActivity.class);
            startActivity(about_intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonStart(View view) {
        inicialize();
        priceDialogBox();
        Button timerStartButton = findViewById(R.id.buttonStart);
        timerStartButton.setEnabled(false);
        timer.schedule(myTimerTask, 1000, 1000);
    }

    public void buttonStop(View view) {

        timer.cancel();
        Button timerStartButton = findViewById(R.id.buttonStart);
        timerStartButton.setEnabled(true);

        RatingBar rBar = findViewById(R.id.ratingBar);
        rBar.setRating((float)0.0);
/*        java.text.DecimalFormat df=new java.text.DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);

        RatingService ratingService = new RatingService();

        double rate = ratingService.RateBattery(1.2,20);
        RatingBar rBar = findViewById(R.id.ratingBar);
        rBar.setRating((float)rate);
*/
    }

  /*  private void priceDialogBox(){

        final Dialog dialogBox = new Dialog(activityContext);
        dialogBox.setContentView(R.layout.price_dialog);

        ((Button)dialogBox.findViewById(R.id.buttonOk)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                priceDialog = 2.0;

                dialogBox.dismiss();
            }
        });
        dialogBox.show();
    }
*/
  private void priceDialogBox(){


      final Dialog dialogBox = new Dialog(activityContext);

      dialogBox.setContentView(R.layout.price_dialog);

      ((Button)dialogBox.findViewById(R.id.buttonOk)).setOnClickListener(new View.OnClickListener() {

          @Override
          public void onClick(View view) {

              EditTextPriceDialog = (EditText) dialogBox.findViewById(R.id.editTextPrice);
              PriceDialog = Double.parseDouble(EditTextPriceDialog.getText().toString());


              InterClassDataHolder interClassDataHolder = InterClassDataHolder.getInstance();
              interClassDataHolder.setPrice(PriceDialog);

              dialogBox.dismiss();
          }
      });
      dialogBox.show();
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
