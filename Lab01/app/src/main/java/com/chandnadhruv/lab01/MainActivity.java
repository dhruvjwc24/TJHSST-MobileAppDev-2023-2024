package com.chandnadhruv.lab01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button incrementButton;
    TextView greetingDisplay;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        greetingDisplay = findViewById(R.id.greeting_textview);
    }

    public void increment(View view) {
        System.out.println("incrementing: "+ ++count);
        greetingDisplay.setText(""+count);
    }
    public void decrement(View view) {
        System.out.println("decrementing: "+ --count);
        greetingDisplay.setText(""+count);
    }

    public void multiply(View view) {
        count *= 10;
        System.out.println("multiplying: "+ count);
        greetingDisplay.setText(""+count);
    }
    public void divide(View view) {
        count /= 10;
        System.out.println("dividing: "+ count);
        greetingDisplay.setText(""+count);
    }
}