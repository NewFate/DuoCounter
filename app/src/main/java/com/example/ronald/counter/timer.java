package com.example.ronald.counter;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class timer extends AppCompatActivity {

    boolean isStart = false, isFirstTimeEntered = true, isReset = false;
    long totalSeconds = 0;// in sec
    long savedTime = 0;// in seconds

    private Handler handler;
    private long timeRem = 0;// in msec


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_timer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Timer");

        handler = new Handler();
        final Runnable runnable = new Runnable(){

            EditText minTimer = (EditText) findViewById(R.id.minTimer);
            EditText secTimer = (EditText) findViewById(R.id.secTimer);

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

            @Override
            public void run() {
                //Log.d ("ron", "ran!");

                if(timeRem > 0)
                {
                    if(isStart)
                    {
                        timeRem = timeRem - 1000;
                        if(isReset)
                        {
                            //timeRem = savedTime * 1000;
                            isReset = false;
                        }

                        if((timeRem % 60000 / 1000) < 10)
                        {
                            secTimer.setText("0" + timeRem % 60000 / 1000);
                        }
                        else
                        {
                            secTimer.setText("" + timeRem % 60000 / 1000);
                        }

                        if((timeRem / 60000) < 10)
                        {
                            minTimer.setText("0" + timeRem / 60000);
                        }

                        else
                        {
                            minTimer.setText("" + timeRem / 60000);
                        }

                        // toasts here
                        if(timeRem == 60000)
                        {
                            Toast.makeText(getApplicationContext(), "1 minute remaining", Toast.LENGTH_SHORT).show();
                        }

                        else if(timeRem == 5000)
                        {
                            Toast.makeText(getApplicationContext(), "5 seconds remaining", Toast.LENGTH_SHORT).show();
                        }

                        handler.postDelayed(this, 1000);


                    }

                    else
                    {
                        //handler.removeCallbacks(this);
                        //Toast.makeText(getApplicationContext(), "Timer paused", Toast.LENGTH_SHORT).show();
                        //timeRem = 0;
                    }
                }

                else
                {
                    Toast.makeText(getApplicationContext(), "Timer finished", Toast.LENGTH_SHORT).show();
                    fab.setImageResource(R.drawable.start_timer);

                    Button saveTimeButton = (Button) findViewById(R.id.saveTimeButton);

                    saveTimeButton.setEnabled(true);

                    isStart = false;

                    minTimer.setEnabled(true);
                    secTimer.setEnabled(true);

                    secTimer.setText("");
                    minTimer.setText("");

                    try {
                        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);

                        r.play();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        };



        //make a new timer
        //final MyCounter myCountDownTimerObject = new MyCounter(1000 * totalSeconds,1000);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        Button resetTime = (Button) findViewById(R.id.resetTimerButton);
        Button savTime = (Button) findViewById(R.id.saveTimeButton);


        fab.setImageResource(R.drawable.start_timer);

        savTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText minTimer = (EditText) findViewById(R.id.minTimer);
                EditText secTimer = (EditText) findViewById(R.id.secTimer);

                String min = minTimer.getText().toString();
                String secs = secTimer.getText().toString();
                int minutes, seconds;

                // deciding if input is empty or sec is greater than 60
                if(min.equals(""))
                {
                    minutes = 0;
                }

                else
                {
                    minutes = Integer.parseInt(min);
                }

                if(secs.equals(""))
                {
                    seconds = 0;
                }

                else if(Integer.parseInt(secs) > 60)
                {
                    seconds = 60;
                }

                else
                {
                    seconds = Integer.parseInt(secs);
                }

                savedTime = (minutes * 60) + seconds;

                Toast.makeText(getApplicationContext(), "Time saved", Toast.LENGTH_SHORT).show();

            }
        });

        // set time
        resetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timeRem = savedTime * 1000;
                EditText minTimer = (EditText) findViewById(R.id.minTimer);
                EditText secTimer = (EditText) findViewById(R.id.secTimer);
                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

                Button saveTimeButton = (Button) findViewById(R.id.saveTimeButton);

                saveTimeButton.setEnabled(true);

                fab.setImageResource(R.drawable.start_timer);
                isStart = false;

                minTimer.setEnabled(true);
                secTimer.setEnabled(true);

                if(((savedTime*1000) % 60000 / 1000) < 10)
                {
                    secTimer.setText("0" + (savedTime*1000) % 60000 / 1000);
                }
                else
                {
                    secTimer.setText("" + (savedTime*1000) % 60000 / 1000);
                }

                if(((savedTime*1000) / 60000) < 10)
                {
                    minTimer.setText("0" + (savedTime*1000) / 60000);
                }

                else
                {
                    minTimer.setText("" + (savedTime*1000) / 60000);
                }

                isReset = true;

                Toast.makeText(getApplicationContext(), "Timer reset", Toast.LENGTH_SHORT).show();
                handler.removeCallbacks(runnable);
                //handler.postDelayed(runnable, 1000);


            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


                if (!isStart)
                {


                    //myCountDownTimerObject.start();

                    EditText minTimer = (EditText) findViewById(R.id.minTimer);
                    EditText secTimer = (EditText) findViewById(R.id.secTimer);
                    Button saveTimeButton = (Button) findViewById(R.id.saveTimeButton);



                    String min = minTimer.getText().toString();
                    String secs = secTimer.getText().toString();
                    int minutes, seconds;

                    // deciding if input is empty or sec is greater than 60
                    if(min.equals(""))
                    {
                        minutes = 0;
                    }

                    else
                    {
                        minutes = Integer.parseInt(min);
                    }

                    if(secs.equals(""))
                    {
                        seconds = 0;
                    }

                    else if(Integer.parseInt(secs) > 60)
                    {
                        seconds = 60;
                    }

                    else
                    {
                        seconds = Integer.parseInt(secs);
                    }

                    totalSeconds = (minutes * 60) + seconds;

                    if(isFirstTimeEntered)// saving start time user entered
                    {
                        savedTime = totalSeconds;
                        timeRem = ((long) totalSeconds) * 1000;
                        isFirstTimeEntered = false;
                    }

                    //Toast.makeText(getApplicationContext(), "Time set", Toast.LENGTH_SHORT).show();

                    if(isReset && (savedTime == totalSeconds))
                    {
                        timeRem = ((long) savedTime) * 1000;
                        //Toast.makeText(getApplicationContext(), "Time " + savedTime + " " + totalSeconds, Toast.LENGTH_SHORT).show();
                        isReset = false;
                    }

                    else{
                        timeRem = ((long) totalSeconds) * 1000;
                        //Toast.makeText(getApplicationContext(), "Time else  " + savedTime + " " + totalSeconds, Toast.LENGTH_SHORT).show();
                    }
                    //savedTime = totalSeconds;



                    // timer start
                    if(timeRem != 0) {
                        Toast.makeText(getApplicationContext(), "Timer started", Toast.LENGTH_SHORT).show();
                        fab.setImageResource(R.drawable.pause_timer);

                        isStart = true;

                        //disable editText fields
                        minTimer.setEnabled(false);
                        secTimer.setEnabled(false);

                        saveTimeButton.setEnabled(false);

                        handler.postDelayed(runnable, 1000);
                    }




                }

                else
                {
                    if(timeRem == 0) {
                        //Toast.makeText(getApplicationContext(), "Timer finished", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Timer paused", Toast.LENGTH_SHORT).show();
                    }
                    fab.setImageResource(R.drawable.start_timer);
                    EditText minTimer = (EditText) findViewById(R.id.minTimer);
                    EditText secTimer = (EditText) findViewById(R.id.secTimer);

                    Button saveTimeButton = (Button) findViewById(R.id.saveTimeButton);

                    saveTimeButton.setEnabled(true);
                    isStart = false;

                    minTimer.setEnabled(true);
                    secTimer.setEnabled(true);

                    handler.removeCallbacks(runnable);


                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.moveTaskToBack(true);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //timer stuff here



}
