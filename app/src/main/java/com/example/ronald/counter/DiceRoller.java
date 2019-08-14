package com.example.ronald.counter;

import android.app.ActionBar;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class DiceRoller extends AppCompatActivity {

    //Global Variables Here (bad style..)
    int rand = 0, maximumDiceNumber = 6;
    boolean isDSixSelected = false, isDTwentySelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dice_roller);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Roll Dice");
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.mtg_game_icon);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setImageResource(R.drawable.rolling_dice);//change to a reroll icon
        fab.setBackgroundTintList(getResources().getColorStateList(R.color.pastelBlue));
        fab.setVisibility(View.GONE);

        //ActionBar actionBar = getActionBar();
        //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView randNumOutput = (TextView) findViewById(R.id.randomNumberGen);
                TextView randNumOutput2 = (TextView) findViewById(R.id.randomNumberGen2);

                rand = randNum(maximumDiceNumber);
                randNumOutput.setText("" + rand);

                rand = randNum(maximumDiceNumber);
                randNumOutput2.setText("" + rand);

                Toast.makeText(getApplicationContext(),"Dice rolled", Toast.LENGTH_SHORT).show();

            }
        });

        //my code here



        //D6------------------------------------------------
        Button dSix = (Button) findViewById(R.id.dSixIcon);
        dSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageView dSixImage = (ImageView) findViewById(R.id.dSixIconPic);
                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                Button dTwentyButton = (Button) findViewById(R.id.dTwentyIcon);
                ImageView dTwentyImage = (ImageView) findViewById(R.id.dTwentyIconPic);

                Button dSixButton = (Button) findViewById(R.id.dSixIcon);

                fab.setVisibility(View.VISIBLE);

                    if (!isDSixSelected)
                    {
                        dSixImage.setImageResource(R.mipmap.dice_rolling_clicked);
                        dTwentyImage.setImageResource(R.mipmap.d_twenty_idle);
                        isDSixSelected = true;
                        isDTwentySelected = false;
                        //dTwentyButton.setEnabled(false);
                        maximumDiceNumber = 6;

                    }
                    else
                    {
                        dSixImage.setImageResource(R.mipmap.dice_icon_idle);
                        isDSixSelected = false;
                        //dTwentyButton.setEnabled(true);
                        maximumDiceNumber = 20;
                        fab.setVisibility(View.GONE);
                    }



            }
        });

        //D20----------------------------------------------------
        Button dTwenty = (Button) findViewById(R.id.dTwentyIcon);
        dTwenty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageView dTwentyImage = (ImageView) findViewById(R.id.dTwentyIconPic);
                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                Button dSixButton = (Button) findViewById(R.id.dSixIcon);
                ImageView dSixImage = (ImageView) findViewById(R.id.dSixIconPic);

                Button dTwentyButton = (Button) findViewById(R.id.dTwentyIcon);

                fab.setVisibility(View.VISIBLE);


                    if (!isDTwentySelected)
                    {
                        dTwentyImage.setImageResource(R.mipmap.d_twenty_clicked);
                        dSixImage.setImageResource(R.mipmap.dice_icon_idle);
                        isDTwentySelected = true;
                        //dSixButton.setEnabled(false);
                        isDSixSelected = false;
                        maximumDiceNumber = 20;
                    }
                    else
                    {
                        dTwentyImage.setImageResource(R.mipmap.d_twenty_idle);
                        isDTwentySelected = false;
                        //isDSixSelected = true;
                        //dSixButton.setEnabled(true);
                        maximumDiceNumber = 6;
                        fab.setVisibility(View.GONE);
                    }

            }
        });
    }

    // other function here
    int randNum (int max)// get max number in interval 0-x, and returns a random number form 1-x
    {
        Random number = new Random();
        int finalNum = number.nextInt(max - 0) + 1;

        //rand = finalNum;

        return finalNum;
    }


}
