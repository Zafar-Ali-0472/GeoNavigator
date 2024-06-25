package com.example.geonavigator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private LinearLayout historyLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        historyLinearLayout = findViewById(R.id.historyLinearLayout);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String place = editText.getText().toString().trim();
                if (!place.isEmpty()) {
                    addPlaceToHistory(place);
                    openMap(place);
                }
            }
        });
    }

    private void addPlaceToHistory(final String place) {
        TextView textView = new TextView(this);
        textView.setText(place);
        textView.setClickable(true);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap(place);
            }
        });
        historyLinearLayout.addView(textView,0);
    }

    private void openMap(String locationName) {
        String encodedLocationName = Uri.encode(locationName);
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + encodedLocationName);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}
