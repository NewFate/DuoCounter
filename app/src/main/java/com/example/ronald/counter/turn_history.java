package com.example.ronald.counter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public class turn_history extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_turn_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("History");

        Intent getVector = getIntent();

        // print the turn history (use parcelable instead?)
        ArrayList<int[]> turnHistory = (ArrayList<int[]>) getVector.getSerializableExtra("vector");
        TextView playerNames = (TextView) findViewById(R.id.turnHist);

        //get the gamemode
        String gameMode = getVector.getStringExtra("gm");
        String playerOneName = getVector.getStringExtra("playerOne");
        String playerTwoName = getVector.getStringExtra("playerTwo");

        if(playerOneName.equals(""))
        {
            playerOneName = "Player 1";
        }

        if(playerTwoName.equals(""))
        {
            playerTwoName = "Player 2";
        }

        //playerNames.setText("" + playerOneName + "      " + playerTwoName);

        Spannable pone = new SpannableString("\n               " + playerOneName);
        pone.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.pastelBlue)), 0, pone.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        playerNames.append(pone);

        Spannable ptwo = new SpannableString("\t\t\t\t\t" + playerTwoName + "\n");
        ptwo.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.pastelOrange)), 0, ptwo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        playerNames.append(ptwo);

        LinearLayout myLayout = (LinearLayout) findViewById(R.id.turnsLayout);

        for(int elm = 0; elm < turnHistory.size(); elm++)
        {
            TextView textView = new TextView(this);

            // note:gamemode 1 = mtg, gamemode 2  = yugioh
            if(Integer.parseInt(gameMode) != 2) //gamemode is not yugioh
            {
                Spannable p1 = new SpannableString("\n" + "Turn " + (elm + 1) + ":\t\t\t\t\t" + (turnHistory.get(elm))[0] + " / " + (turnHistory.get(elm))[2]);
                p1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.pastelBlue)), 0, p1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                textView.append(p1);

                Spannable p2 = new SpannableString("\t\t\t\t\t" + (turnHistory.get(elm))[1] + " / " + (turnHistory.get(elm))[3] + "\n");
                p2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.pastelOrange)), 0, p2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                textView.append(p2);


            }

            else// gamemode is magic etc...
            {
                Spannable p1 = new SpannableString("\n" + "Turn " + (elm+1) + ":\t\t\t\t\t" +  (turnHistory.get(elm))[0]);
                p1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.pastelBlue)), 0, p1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                textView.append(p1);

                Spannable p2 = new SpannableString("\t\t\t\t\t" + (turnHistory.get(elm))[1] + "\n");
                p2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.pastelOrange)), 0, p2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                textView.append(p2);
            }

            myLayout.addView(textView);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);

            TextView textViewDiff = new TextView(this);// new text for diff

            if (elm != turnHistory.size()-1)// printing out diff in scores
            {
                int diffP1 = 0, diffP2 = 0, diffP1P = 0, diffP2P = 0;
                diffP1 = (turnHistory.get(elm+1))[0] - (turnHistory.get(elm))[0];
                diffP2 = (turnHistory.get(elm+1))[1] - (turnHistory.get(elm))[1];

                diffP1P = (turnHistory.get(elm+1))[2] - (turnHistory.get(elm))[2];
                diffP2P = (turnHistory.get(elm+1))[3] - (turnHistory.get(elm))[3];

                if(Integer.parseInt(gameMode) != 2)// print mtg diff
                {
                    Spannable p1diff = new SpannableString("\n" + "         " + "\t\t\t\t\t" +  diffP1 + " / " + diffP1P);
                    p1diff.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.pastelBlue)), 0, p1diff.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    textViewDiff.append(p1diff);

                    Spannable p2diff = new SpannableString("\t\t\t\t\t   " + diffP2 + " / " + diffP2P + "\n");
                    p2diff.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.pastelOrange)), 0, p2diff.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    textViewDiff.append(p2diff);
                }

                else //print yugioh diff w/o poison counters
                {
                    Spannable p1diff = new SpannableString("\n" + "         " + "\t\t\t\t\t" + diffP1);
                    p1diff.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.pastelBlue)), 0, p1diff.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    textViewDiff.append(p1diff);

                    Spannable p2diff = new SpannableString("\t\t\t\t\t   " + diffP2 + "\n");
                    p2diff.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.pastelOrange)), 0, p2diff.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    textViewDiff.append(p2diff);
                }

                myLayout.addView(textViewDiff);
                textViewDiff.setGravity(Gravity.CENTER);
                textViewDiff.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);

            }

            // add diff here

            // to show the diff in scores

            /*if(turnHistory.size() == 1 || (turnHistory.size()%2) != 0)// on first turn and odd turns
            {
                if()
            }

            else
            {

            }*/
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
