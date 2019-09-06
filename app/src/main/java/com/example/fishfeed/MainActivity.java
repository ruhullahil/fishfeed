package com.example.fishfeed;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.constraint.solver.widgets.Snapshot;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private LinearLayout lr ,nx;
    private CheckBox min30,min40,min45,min60;
    private String value;
    private Button setValue , feed ;
    private TextView statstr, timeSelect , timeView;
    private DatabaseReference mDatabase ,db;
    private time t1;
    private  String set_time;

   // @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lr = findViewById(R.id._layout);
        min30 = findViewById(R.id.min30);
        min40 = findViewById(R.id.min40);
        min45 = findViewById(R.id.min45);
        min60 = findViewById(R.id.min60);
        nx = findViewById(R.id._ly2);
        statstr = findViewById(R.id.fixdtext);
        timeSelect = findViewById(R.id._setText);
        setValue = findViewById(R.id.setvalue);
        timeView = findViewById(R.id.timeview);
        feed = findViewById(R.id.button);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        db = FirebaseDatabase.getInstance().getReference("time");


        db.addValueEventListener(new ValueEventListener() {
            public time tt;
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                 for(DataSnapshot snp : snapshot.getChildren()) {
                      tt = snp.getValue(time.class);
                 }
                 value = tt.duration;
                 set_time = tt.set_time;
                 timeSelect.setText(value);
                 timeView.setText(set_time);


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Context context = getApplicationContext();
                CharSequence text = databaseError.getMessage();
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });



        setValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeSelect.setText(value);
                timeView.setText(set_time);
                t1 = new time(value,set_time);
                mDatabase.child("time").child("1").setValue(t1);

            }
        });

     feed.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             set_time= new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
             timeSelect.setText(value);
             timeView.setText(set_time);
             t1 = new time(value,set_time);
             mDatabase.child("time").child("1").setValue(t1);
         }
     });



    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();


        switch(view.getId()) {
            case R.id.min30:
                if (checked)
                {
                   value="1";
                   min30.setChecked(true);
                   min40.setChecked(false);
                   min45.setChecked(false);
                   min60.setChecked(false);

                }
                break;
            case R.id.min40:
                if (checked)
                {
                    value="6";
                    min30.setChecked(false);
                    min40.setChecked(true);
                    min45.setChecked(false);
                    min60.setChecked(false);
                }
                break;
            case R.id.min45:
                if (checked)
                {
                    value="12";
                    min30.setChecked(false);
                    min40.setChecked(false);
                    min45.setChecked(true);
                    min60.setChecked(false);
                }
                break;
            case R.id.min60:
                if (checked)
                {
                    value="24";
                    min30.setChecked(false);
                    min40.setChecked(false);
                    min45.setChecked(false);
                    min60.setChecked(true);
                }
                break;




        }
    }
}
