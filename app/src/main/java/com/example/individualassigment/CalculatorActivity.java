package com.example.individualassigment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

    private EditText etBaggageWeight, etRatePerKg;
    private RadioGroup rgTicketClass;
    private RadioButton rbEconomy;
    private TextView tvExcessWeight, tvExcessCharge, tvTotalPayable;
    private Button btnCalculate, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Baggage Calculator");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        etBaggageWeight = findViewById(R.id.etBaggageWeight);
        etRatePerKg = findViewById(R.id.etRatePerKg);
        rgTicketClass = findViewById(R.id.rgTicketClass);
        rbEconomy = findViewById(R.id.rbEconomy);
        tvExcessWeight = findViewById(R.id.tvExcessWeight);
        tvExcessCharge = findViewById(R.id.tvExcessCharge);
        tvTotalPayable = findViewById(R.id.tvTotalPayable);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnReset = findViewById(R.id.btnReset);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateFee();
            }
        });


        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
            }
        });
    }

    private void calculateFee() {
        String weightStr = etBaggageWeight.getText().toString().trim();
        String rateStr = etRatePerKg.getText().toString().trim();

        if (weightStr.isEmpty() || rateStr.isEmpty()) {
            Toast.makeText(this, "Please enter all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double weight = Double.parseDouble(weightStr);
        double rate = Double.parseDouble(rateStr);


        double allowanceX = rbEconomy.isChecked() ? 20.0 : 40.0;

        double excessWeight = weight - allowanceX;
        if (excessWeight < 0) {
            excessWeight = 0;
        }

        double excessCharge = excessWeight * rate;

        double totalPayable = excessCharge + (0.08 * excessCharge);

        tvExcessWeight.setText(String.format("Excess Baggage Weight: %.2f kg", excessWeight));
        tvExcessCharge.setText(String.format("Excess Baggage Charge: RM %.2f", excessCharge));
        tvTotalPayable.setText(String.format("Total Amount Payable: RM %.2f", totalPayable));
    }


    private void resetFields() {
        etBaggageWeight.setText("");
        etRatePerKg.setText("");
        rgTicketClass.check(R.id.rbEconomy);
        tvExcessWeight.setText("Excess Baggage Weight: 0 kg");
        tvExcessCharge.setText("Excess Baggage Charge: RM 0.00");
        tvTotalPayable.setText("Total Amount Payable: RM 0.00");
        etBaggageWeight.requestFocus();
        Toast.makeText(this, "Fields cleared", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}