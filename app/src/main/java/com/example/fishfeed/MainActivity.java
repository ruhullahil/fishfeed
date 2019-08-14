package com.example.fishfeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private LinearLayout lr;
    private CheckBox min30,min40,min45,min60;
    private int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lr = findViewById(R.id._layout);
        min30 = findViewById(R.id.min30);
        min40 = findViewById(R.id.min40);
        min45 = findViewById(R.id.min45);
        min60 = findViewById(R.id.min60);



    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();


        switch(view.getId()) {
            case R.id.min30:
                if (checked)
                {
                   value=30;
                   min30.setChecked(true);
                   min40.setChecked(false);
                   min45.setChecked(false);
                   min60.setChecked(false);

                }
                break;
            case R.id.min40:
                if (checked)
                {
                    value=40;
                    min30.setChecked(false);
                    min40.setChecked(true);
                    min45.setChecked(false);
                    min60.setChecked(false);
                }
                break;
            case R.id.min45:
                if (checked)
                {
                    value=45;
                    min30.setChecked(false);
                    min40.setChecked(false);
                    min45.setChecked(true);
                    min60.setChecked(false);
                }
                break;
            case R.id.min60:
                if (checked)
                {
                    value=60;
                    min30.setChecked(false);
                    min40.setChecked(false);
                    min45.setChecked(false);
                    min60.setChecked(true);
                }
                break;




        }
    }
}
