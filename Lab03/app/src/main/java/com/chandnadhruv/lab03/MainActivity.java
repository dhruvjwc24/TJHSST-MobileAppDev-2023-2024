package com.chandnadhruv.lab03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String TAG = "com.chandnadhruv.lab03.sharedprefs";
    TextView topLeftTextView, bottomRightTextView;
    Button bottomLeftButton, topRightButton;
    TextView[] views;
    SeekBar seekBar;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ConstraintLayout layout;
    int count=0;
    long startTime, clicks;
    float cps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomRightTextView = findViewById(R.id.bottomRightTextView);
        topLeftTextView = findViewById(R.id.topLeftTextView);
        bottomLeftButton = findViewById(R.id.bottomLeftButton);
        topRightButton = findViewById(R.id.topRightButton);
        seekBar = findViewById(R.id.seekBar);
        views = new TextView[]{bottomRightTextView, topLeftTextView, bottomLeftButton, topRightButton};
        bottomRightTextView.setOnClickListener(this);
        topLeftTextView.setOnClickListener(this);
        bottomLeftButton.setOnClickListener(this);
        topRightButton.setOnClickListener(this);
        sharedPreferences = getSharedPreferences(TAG, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        layout = findViewById(R.id.activity_main_layout);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int lastProgress;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                for (TextView x: views){x.setTextSize(progress);}
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                lastProgress = seekBar.getProgress();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Snackbar snackbar = Snackbar.make(layout, "Font size changed to " + seekBar.getProgress() + "sp", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                seekBar.setProgress(lastProgress);
                                for (TextView x: views){x.setTextSize(lastProgress);}
                                Snackbar.make(layout, "Font Size Reverted To " + lastProgress + "sp", Snackbar.LENGTH_LONG);
                            }
                        }

                );
                snackbar.setActionTextColor(Color.BLUE);
                View snackBarView = snackbar.getView();
                snackbar.show();
            }
        });
        layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                editor.clear().apply();
                setInitialValues();
                return false;
            }
        });
        setInitialValues();
        startTime = System.currentTimeMillis();

    }

    private void setInitialValues() {
        for (TextView x: views){
            x.setText(sharedPreferences.getString(x.getTag().toString(), "0"));
        }
        seekBar.setProgress(30);
    }

    @Override
    public void onClick(View v) {
        TextView t = (TextView) v;
        t.setText(""+(Integer.parseInt(t.getText().toString())+1));
        editor.putString(t.getTag().toString(), t.getText().toString()).apply();
        cps = ++clicks/((System.currentTimeMillis()-startTime)/1000f);
        Toast.makeText(this, ""+cps, Toast.LENGTH_SHORT).show();
    }


}