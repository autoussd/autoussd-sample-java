package com.autoussd.sample.java;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // TODO #1: Initialize AutoUssd
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // TODO #3: Dispose AutoUssd SDK
    }

    public void completeTransaction(View v) {
        // Get references to the EditText components in the view
        EditText recipientNumberInput = findViewById(R.id.numberInput);
        EditText amountInput = findViewById(R.id.amountInput);
        EditText referenceInput = findViewById(R.id.referenceInput);

        // Get the recipient number, amount and reference from the EditText components
        String recipientNumber = recipientNumberInput.getText().toString();
        String amount = amountInput.getText().toString();
        String reference = referenceInput.getText().toString();

        // Check if any of the values are empty and display a toast message if so
        if (recipientNumber.isEmpty() || amount.isEmpty() || reference.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_LONG).show();
            return;
        }

        // TODO #2: Execute session with the AutoUssd SDK
    }
}