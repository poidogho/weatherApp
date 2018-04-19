package com.example.odafeidogho.weatherapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    private static final String APIID = "7b46002873dc8555cf1ae0b170fcd19e";

    //in case of error connecting due to incorrect api id
    private static final String ERROR401 = "{\"code\":401,\"message\":" +
            "\"Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.\"}";

    EditText locationField;
    Button sendButton, clearButton;

    HttpURLConnection connection = null;
    BufferedReader reader = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationField = (EditText)findViewById(R.id.locationText);
        sendButton = (Button)findViewById(R.id.sendButton);
        clearButton = (Button)findViewById(R.id.clearButton);

        //clearing the text in the location field
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationField.setText("");
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private class GetWeatherInfoTaske extends AsyncTask<String,Integer,String> {

        @Override
        protected String doInBackground(String... strings) {
            URL apiUrl = null;
            StringBuffer stringBuffer = null;
            try{
                apiUrl = new URL(strings[0]);
            }catch (MalformedURLException e){
                e.printStackTrace();
            }
            try{
                connection = (HttpURLConnection)apiUrl.openConnection();
                InputStream inputStream = new BufferedInputStream(connection.getInputStream());
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String stream = "";
                stringBuffer = new StringBuffer();

                while ((stream = reader.readLine()) != null){
                    stringBuffer.append(stream);
                }
            }catch (UnknownHostException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(reader !=null){
                   // reader.close();
                }
            }
            return null;
        }
    }
}
