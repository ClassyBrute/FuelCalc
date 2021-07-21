package com.example.fuelcalc;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

Button mButton;
EditText raceHours;
EditText raceMinutes;
EditText aveLapMin;
EditText aveLapSec;
EditText litersLap;
EditText totalLaps;
EditText totalFuel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        raceHours = findViewById(R.id.raceHours);
        raceMinutes = findViewById(R.id.raceMinutes);
        aveLapMin = findViewById(R.id.lapMinutes);
        aveLapSec = findViewById(R.id.lapSeconds);
        litersLap = findViewById(R.id.litersLap);

        totalLaps = findViewById(R.id.totalLaps);
        totalFuel = findViewById(R.id.totalFuel);


        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(
                new View.OnClickListener(){
                   public void onClick(View view){

                       if ((raceHours.getText().toString().equals("0") && raceMinutes.getText().toString().equals("0")) ||
                               (aveLapMin.getText().toString().equals("0") && aveLapSec.getText().toString().equals("0")) ||
                               litersLap.getText().toString().equals("0.0") || (raceHours.getText().toString().equals("") &&
                            raceMinutes.getText().toString().equals("")) || aveLapMin.getText().toString().equals("") ||
                               aveLapSec.getText().toString().equals("") || litersLap.getText().toString().equals("")){
                           new AlertDialog.Builder(MainActivity.this)
                                   .setTitle("zwolnij kowboju")
                                   .setMessage("moze jednak warto wprowadzic odpowiednie dane pajacu?")

                                   .setPositiveButton(android.R.string.ok, null)
                                   .setIcon(android.R.drawable.ic_dialog_alert)
                                   .show();
                       }
                       else {

                           int raceMinutesInt;
                           int raceHoursInt;
                           int aveLapMinInt = Integer.parseInt(aveLapMin.getText().toString());
                           int aveLapSecInt = Integer.parseInt(aveLapSec.getText().toString());
                           double litersLapDoub = Float.parseFloat(litersLap.getText().toString());

                           if (raceMinutes.getText().toString().equals("")){
                               raceMinutesInt = 0;
                           }
                           else{
                               raceMinutesInt = Integer.parseInt(raceMinutes.getText().toString());
                           }

                           if (raceHours.getText().toString().equals("")){
                               raceHoursInt = 0;
                           }
                           else{
                               raceHoursInt = Integer.parseInt(raceHours.getText().toString());
                           }

                           double raceTimeSec = raceHoursInt * 60 * 60 + raceMinutesInt * 60;
                           double lapTimeSec = aveLapMinInt * 60 + aveLapSecInt;

                           double totalLapsCalc = Math.ceil(raceTimeSec / lapTimeSec);
                           double totalFuelCalc = Math.ceil(totalLapsCalc * litersLapDoub);


                           totalLaps.setText(String.valueOf(totalLapsCalc));

                           totalFuel.setText(String.valueOf(totalFuelCalc));
                       }
                   }
                });

    }
}