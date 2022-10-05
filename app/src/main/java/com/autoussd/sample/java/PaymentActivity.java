package com.autoussd.sample.java;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.autoussd.AutoUssd;

import java.util.HashMap;
import java.util.Map;

public class PaymentActivity extends AppCompatActivity {
    private static final String TAG = "PaymentActivity";

    private final String RESULT_CALLBACK_KEY = "result-callback-key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // TODO #1: Initialize AutoUssd
        AutoUssd.Companion.init(this);

        AutoUssd.Companion.getInstance().registerSessionResultListener(RESULT_CALLBACK_KEY, result -> {
            switch (result.getStatus()) {
                case COMPLETED:
                    Log.d(TAG, "Completed");
                    break;
                case PARSED:
                    Log.d(TAG, "Parsed");
                    break;
                case INVALID_SESSION:
                    Log.d(TAG, "Invalid session Id");
                    break;
                case UNSUPPORTED_SIM:
                    Log.d(TAG, "Unsupported SIM");
                    break;
                case SESSION_TIMEOUT:
                    Log.d(TAG, "Session timed-out");
                    break;
                case MENU_CONTENT_MISMATCH:
                    Log.d(TAG, "USSD content did not match menu content");
                    break;
                case ACCOUNT_SUBSCRIPTION_EXPIRED:
                    Log.d(TAG, "Account subscription expired");
                    break;
                case UNKNOWN_ERROR:
                    Log.d(TAG, "Unknown error occurred");
                    break;
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // TODO #3: Dispose AutoUssd SDK
        AutoUssd.Companion.getInstance().unregisterSessionResultListener(RESULT_CALLBACK_KEY);
        AutoUssd.Companion.getInstance().dispose();
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
        final Map<String, String> variables = new HashMap<>();

        variables.put("number", recipientNumber);
        variables.put("amount", amount);
        variables.put("reference", reference);

        AutoUssd.Companion.getInstance().executeSession(
                "60a53f240000000000000000",
                variables,
                null,
                null
        );
    }
}