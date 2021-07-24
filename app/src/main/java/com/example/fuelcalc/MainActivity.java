package com.example.fuelcalc;

import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

Button mButton;
EditText raceHours;
EditText raceMinutes;
EditText aveLapMin;
EditText aveLapSec;
EditText litersLap;
EditText totalLaps;
EditText totalFuel;
Spinner spinnerCars;
EditText tankCapa;
EditText maxStint;


    // class made to simulate key:value pair behaviour
    // because i couldn't find an easier way to do this
    public class Car{
        private String fuel_capacity;
        private String name;

        public Car(String name, String fuel_capacity){
            this.name = name;
            this.fuel_capacity = fuel_capacity;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFuel() {
            return fuel_capacity;
        }

        public void setFuel(String name) {
            this.fuel_capacity = fuel_capacity;
        }

        //to display object as a string in spinner
        @Override
        public String toString() {
            return name;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Car){
                Car c = (Car )obj;
                if(c.getFuel().equals(fuel_capacity) && c.getName().equals(name))
                    return true;
            }

            return false;
        }
    }

    // custom comparator used to compare attributes of different objects
    public class CarComparator implements Comparator<Car> {
        @Override
        public int compare(Car c1, Car c2){
            return c1.getName().compareTo(c2.getName());
        }
    }

    // creates the array for spinner
    private void setData(){
        ArrayList<Car> carList = new ArrayList<>();

        carList.add(new Car("Porsche 991 GT3 R 2018","120"));
        carList.add(new Car("Mercedes-AMG GT3 2015","120"));
        carList.add(new Car("Ferrari 488 GT 3 2018","110"));
        carList.add(new Car("Audi R8 LMS 2015","120"));
        carList.add(new Car("Lamborghini Huracan GT3 2015","120"));
        carList.add(new Car("McLaren 650S GT3 2015","125"));
        carList.add(new Car("Nissan GT-R Nismo GT3 2018","132"));
        carList.add(new Car("BMW M6 GT3 2017","125"));
        carList.add(new Car("Bentley Continental GT 3 2018","132"));
        carList.add(new Car("Porsche 991 II GT3 Cup 2017","100"));
        carList.add(new Car("Nissan GT-R Nismo GT3 2015","132"));
        carList.add(new Car("Bentley Continental GT 3 2015","132"));
        carList.add(new Car("Aston Martin V12 Vantage GT3 2013","132"));
        carList.add(new Car("Reiter Engineering R-EX GT3 2017","130"));
        carList.add(new Car("Emil Frey Jaguar G3 2012","119"));
        carList.add(new Car("Lexus RC F GT3 2016","120"));
        carList.add(new Car("Lamborghini Huracan GT3 Evo 2019","120"));
        carList.add(new Car("Honda NSX GT3 2017","120"));
        carList.add(new Car("Lamborghini Huracan ST 2015","120"));
        carList.add(new Car("Audi R8 LMS Evo 2019","120"));
        carList.add(new Car("Aston Martin V8 Vantage GT3 2019","120"));
        carList.add(new Car("Honda NSX GT3 Evo 2019","120"));
        carList.add(new Car("McLaren 720S GT3 2019","125"));
        carList.add(new Car("Porsche 991 II GT3 R 2019","120"));

        // sorting the above list using a custom comparator
        Collections.sort(carList, new CarComparator());

        ArrayAdapter<Car> adapter = new ArrayAdapter<Car>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, carList);
        spinnerCars.setAdapter(adapter);
    }

    // main function?
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

        tankCapa = findViewById(R.id.tankCapa);
        spinnerCars = findViewById(R.id.spinnerCars);
        maxStint = findViewById(R.id.maxStint);

        // gotta create the array somewhere
        setData();

        // what happens after selecting from the spinner
        spinnerCars.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){

                Car car = (Car) parent.getSelectedItem();
                tankCapa.setText(car.getFuel());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent){
                tankCapa.setText("nothing");
            }
        });

        // clicking the button makes the program go brrr
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(
                new View.OnClickListener(){
                   public void onClick(View view){

                       // logic to check if all the fields are occupied
                       if ((raceHours.getText().toString().equals("0") && raceMinutes.getText().toString().equals("0")) ||
                               (aveLapMin.getText().toString().equals("0") && aveLapSec.getText().toString().equals("0")) ||
                               litersLap.getText().toString().equals("0.0") || (raceHours.getText().toString().equals("") &&
                            raceMinutes.getText().toString().equals("")) || (aveLapMin.getText().toString().equals("") &&
                               aveLapSec.getText().toString().equals("")) || litersLap.getText().toString().equals("")){

                           // alert if some of the fields are not occupied or were left with a 0
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
                           int aveLapSecInt;
                           int aveLapMinInt;
                           double litersLapDoub = Float.parseFloat(litersLap.getText().toString());
                           double tankCapaDoub = Integer.parseInt(tankCapa.getText().toString());

                           // when some of the fields are empty, give them 0 instead
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

                           if (aveLapMin.getText().toString().equals("")){
                               aveLapMinInt = 0;
                           }
                           else{
                               aveLapMinInt = Integer.parseInt(aveLapMin.getText().toString());
                           }

                           if (aveLapSec.getText().toString().equals("")){
                               aveLapSecInt = 0;
                           }
                           else{
                               aveLapSecInt = Integer.parseInt(aveLapSec.getText().toString());
                           }

                           // all of the calculations
                           double raceTimeSec = raceHoursInt * 60 * 60 + raceMinutesInt * 60;
                           double lapTimeSec = aveLapMinInt * 60 + aveLapSecInt;

                           double totalLapsCalc = Math.ceil(raceTimeSec / lapTimeSec);
                           double totalFuelCalc = Math.ceil(totalLapsCalc * litersLapDoub);
                           double maxStintLen = Math.floor(tankCapaDoub/litersLapDoub);

                           // results
                           maxStint.setText(String.valueOf(maxStintLen));
                           totalLaps.setText(String.valueOf(totalLapsCalc));
                           totalFuel.setText(String.valueOf(totalFuelCalc));
                       }
                   }
                });

    }
}