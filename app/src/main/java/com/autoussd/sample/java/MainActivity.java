package com.autoussd.sample.java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startPayment(View v) {
//        startActivity(
//            new Intent(
//                this,
//                PaymentActivity.
//            )
//        );
    }
}