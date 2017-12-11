package com.example.taufanseptaufani.myapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    Button queryButton;
    Spinner optCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kenalan();
        ketikaButtonDipencet();
        isiOptCity();
    }

    private void isiOptCity() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.city_array,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        optCity.setAdapter(adapter);
    }


    private void ketikaButtonDipencet() {
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = optCity.getSelectedItem().toString();
                new RetrieveFeedTask(MainActivity.this,city).execute();
            }
        });
    }

    private void kenalan() {
        queryButton = (Button)findViewById(R.id.queryButton);
        optCity = (Spinner)findViewById(R.id.optCity);
    }
}
