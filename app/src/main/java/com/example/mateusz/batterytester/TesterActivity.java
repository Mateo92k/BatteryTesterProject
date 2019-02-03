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

        Button timerStopButton = findViewById(R.id.buttonStop);
        timerStopButton.setEnabled(false);

    }

    protected void inicialize(){
        timer = new Timer();
        myTimerTask = new ServiceTimer(this);
        activityContext = this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tester, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_about) {
            Intent about_intent = new Intent(this,AboutActivity.class);
            startActivity(about_intent);
            return true;
        }

        if (id == R.id.action_exit){
            System.exit(0);
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonStart(View view) {
        inicialize();
        priceDialogBox();
        Button timerStartButton = findViewById(R.id.buttonStart);
        Button timerStopButton = findViewById(R.id.buttonStop);
        timerStartButton.setEnabled(false);
        timerStopButton.setEnabled(true);
        timer.schedule(myTimerTask, 1000, 1000);
    }

    public void buttonStop(View view) {

        timer.cancel();
        Button timerStartButton = findViewById(R.id.buttonStart);
        timerStartButton.setEnabled(true);
        RatingBar rBar = findViewById(R.id.ratingBar);
        rBar.setRating((float)0.0);
    }

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
