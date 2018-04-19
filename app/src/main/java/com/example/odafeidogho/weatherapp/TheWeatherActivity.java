package com.example.odafeidogho.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TheWeatherActivity extends AppCompatActivity {

    Button returnButtonm;
    TextView city, temperature, minTemp, maxTemp, tempDescription;
    String cityName, tempValue, minTempValue, maxTempValue, descString, weather;

    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weatherdetails);

        //initialising text views
        city = findViewById(R.id.cityName);
        temperature = findViewById(R.id.temperature);
        minTemp = findViewById(R.id.mintemp);
        maxTemp = findViewById(R.id.maxtemp);
        tempDescription= findViewById(R.id.description);
        returnButtonm=findViewById(R.id.returnButton);

        if(savedInstanceState == null){
            Intent myIntent = getIntent();
            if(myIntent != null){
                Bundle theWeatherBundle = myIntent.getBundleExtra("WeatherBundle");
                weather = theWeatherBundle.getString("weather");
                try{
                    jsonObject = new JSONObject(weather);
                    cityName = jsonObject.getString("name");
                    tempValue = Integer.toString(jsonObject.getJSONObject("main").getInt("temp"));
                    minTempValue = Integer.toString(jsonObject.getJSONObject("main").getInt("minTemp"));
                    maxTempValue = Integer.toString(jsonObject.getJSONObject("main").getInt("maxTemp"));

                    JSONArray fullWeatherArray = jsonObject.getJSONArray("weather");
                    JSONObject descriptionJson = (JSONObject) fullWeatherArray.get(0);
                    descString = descriptionJson.getString("description");
                }catch(JSONException e){
                    e.printStackTrace();
                }
                city.setText(cityName);
                temperature.setText(tempValue);
                minTemp.setText(minTempValue);
                maxTemp.setText(maxTempValue);
                tempDescription.setText(descString);
            }

        }else{
            cityName = savedInstanceState.getString("city");
            tempValue = savedInstanceState.getString("temp");
            minTempValue = savedInstanceState.getString("minTemp");
            maxTempValue = savedInstanceState.getString("maxTemp");
            descString = savedInstanceState.getString("description");

            city.setText(cityName);
            temperature.setText(tempValue);
            minTemp.setText(minTempValue);
            maxTemp.setText(maxTempValue);
            tempDescription.setText(descString);
        }
        returnButtonm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
