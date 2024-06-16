package com.chandnadhruv.lab07;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment_container, FragmentB.newInstance(99, "DhruvC"), "FragmentB");
        ft.commit();
    }

    public void update_greeting(View view) {
        FragmentB fragmentB = (FragmentB) getSupportFragmentManager().findFragmentByTag("FragmentB");
        ((TextView)fragmentB.view.findViewById(R.id.b_textview)).setText(R.string.greeting2);
    }
}