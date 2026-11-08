package com.example.individualassigment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnGoToCalculator, btnGoToAbout, btnShareApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Main Menu");
        }

        btnGoToCalculator = findViewById(R.id.btnGoToCalculator);
        btnGoToAbout = findViewById(R.id.btnGoToAbout);
        btnShareApp = findViewById(R.id.btnShareApp);


        btnGoToCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
                startActivity(intent);
            }
        });


        btnGoToAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });


        btnShareApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out my Excess Baggage Fee app here: https://github.com/Panadhaa/IndividualAssigment_ICT602.git");
                startActivity(Intent.createChooser(shareIntent, "Share via"));
            }
        });
    }
}
