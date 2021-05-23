package com.example.yemeksiparis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CheckoutActivity extends AppCompatActivity {

    TextView tvTotal;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        btnSend = (Button) findViewById(R.id.btnSend);
        tvTotal = (TextView) findViewById(R.id.tvTotal);

        Intent intent = getIntent();
        Double inputTotal = 0.0;
        inputTotal = intent.getDoubleExtra("inputCartTotal", 0.0);
        tvTotal.setText("Sepet Tutarı: " + inputTotal);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CheckoutActivity.this, "Siparişinizi aldık!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CheckoutActivity.this, PostLoginActivity.class));
            }
        });


    }
}