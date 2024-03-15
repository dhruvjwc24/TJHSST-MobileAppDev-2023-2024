package com.chandnadhruv.lab02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText edit_name_edittext;
    Button send_name_button;
    TextView show_name_textview;
    RadioButton left_radio;
    RadioButton right_radio;
    int count=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit_name_edittext = findViewById(R.id.edit_name_edittext);
        send_name_button = findViewById(R.id.send_name_button);
        show_name_textview = findViewById(R.id.show_name_textview);
        left_radio = findViewById(R.id.radio_left);
        right_radio = findViewById(R.id.radio_right);
    }
    public void send_name(View view) {
        String name = edit_name_edittext.getText().toString();
        String[] greetingsArray = getResources().getStringArray(R.array.greetings_array);
        if (left_radio.isChecked()) {
            --count;
            if (count<0) count = greetingsArray.length-1;
        }
        else {
            count = (count + 1) % greetingsArray.length;
        }
        System.out.println("Entering Text: " + name);
        String greeting = getString(R.string.greeting, greetingsArray[count], name);
        show_name_textview.setText(greeting);
    }
}