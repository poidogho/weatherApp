package com.example.odafeidogho.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConnectionErrorActivity extends AppCompatActivity{

    TextView errorMessage;
    Button returnButton;
    String errorString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_errormessages);

        //initialising views
        errorMessage = findViewById(R.id.errorMessage);
        returnButton = findViewById(R.id.returnButton);

        if(savedInstanceState == null){
            Intent myIntent = getIntent();
            if(myIntent != null){
                Bundle errorBundle = myIntent.getBundleExtra("errorMessage");
                errorString = errorBundle.getString("error");

                errorMessage.setText(errorString);
            }
        }else{
            errorString = savedInstanceState.getString("error");
            errorMessage.setText(errorString);
        }
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
