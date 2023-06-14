package com.example.levelcal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText inputEditText;
    private ListView resultsListView1;
    private ListView resultsListView2;
    private ArrayAdapter<Double> adapter1;
    private ArrayAdapter<Double> adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputEditText = findViewById(R.id.price_input);
        resultsListView1 = findViewById(R.id.supportListView);
        resultsListView2 = findViewById(R.id.resistanceListView);
        TextView get_value = findViewById(R.id.btn_level);

        adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        resultsListView2.setAdapter(adapter1);
        resultsListView1.setAdapter(adapter2);

        get_value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResults();
            }
        });



    }

    private void calculateResults() {
        String inputText = inputEditText.getText().toString();
        if (inputText.isEmpty()) {
            Toast.makeText(this, "Please enter a value for Closed Price", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            double initialX = Double.parseDouble(inputText);
            int iterations = 4;

            double[] finalResults = calculateResults(initialX, iterations);
            double[] finalRest = calculateRest(initialX, iterations);
            adapter1.clear();
            adapter2.clear();

            for (double result : finalResults) {
                adapter2.add(result);

            }
            for (double result : finalRest) {
                adapter1.add(result);
            }

            Toast.makeText(this,"Have a Nice Trade", Toast.LENGTH_SHORT).show();
        }
    }

    public double[] calculateResults(double initialX, int iterations) {
        double[] results = new double[iterations];

        double currentValue = initialX;
        for (int i = 0; i < iterations; i++) {
            double result = Math.sqrt(currentValue) - 0.25;
            result *= result;

            results[i] = result;

            currentValue = result;
        }

        return results;
    }

    public double[] calculateRest(double initialX, int iterations) {
        double[] results = new double[iterations];

        double currentValue = initialX;
        for (int i = 0; i < iterations; i++) {
            double result = Math.sqrt(currentValue) + 0.25;
            result *= result;

            results[i] = result;

            currentValue = result;
        }

        return results;
    }
}