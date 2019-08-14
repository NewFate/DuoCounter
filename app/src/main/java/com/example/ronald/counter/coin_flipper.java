package com.example.ronald.counter;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class coin_flipper extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_coin_flipper);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
       

        fab.setImageResource(R.mipmap.coin_flip_icon_new);//change to a reroll icon
        fab.setBackgroundTintList(getResources().getColorStateList(R.color.pastelBlue));
       

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Flip Coin");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // my code here for action upon press
                TextView numR = (TextView) findViewById(R.id.coinResult);
                TextView resDis = (TextView) findViewById(R.id.resultDis);
                int num = randNum(2);

                if(num == 1){

                    numR.setText("H");
                    resDis.setText("Heads");

                }

                else
                {
                    numR.setText("T");
                    resDis.setText("Tails");
                }

                Toast.makeText(getApplicationContext(),"Coin flipped", Toast.LENGTH_SHORT).show();
            }
        });


     
    }

    //my code here
    int randNum(int max)// get max number in interval 0-x, and returns a random number form 1-x
    {
        Random number = new Random();
        int finalNum = number.nextInt(max - 0) + 1;

        return finalNum;
    }




}
