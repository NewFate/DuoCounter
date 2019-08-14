package com.example.ronald.counter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

public class CustomInc extends AppCompatActivity {

    Globals incr = Globals.getInstance();

    int customInc = 0;

    //Globals incr = Globals.getInstance();//globals =============================== incr.setInc(BuildConfig.VERSION_CODE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_custom_inc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Set Custom Increment");

        //---------------------------------
        Intent i = getIntent();
        //Bundle data = i.getExtras();




        EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.mipmap.custom_inc_enter_icon);//change to a reroll icon
        fab.setBackgroundTintList(getResources().getColorStateList(R.color.pastelBlue));

        //fab.setBackgroundColor(getResources().getColor(R.color.pastelBlue));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText myInc = (EditText)findViewById(R.id.customInc);

                String input = myInc.getText().toString();
                //Intent i = new Intent (getApplicationContext(), Counter.class);

                if(!input.equals(""))
                {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result",input);
                    setResult(Counter.RESULT_OK,returnIntent);
                    finish();
                }

            }
        });

        //-------


    }

    // my functions here



}
