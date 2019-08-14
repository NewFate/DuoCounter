package com.example.ronald.counter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class gameSelector extends AppCompatActivity {


    boolean mtgToggle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game_selector);

        //Intent intent = new Intent(this, Counter.class);
        //startActivity(intent);
        //finish();

        final Button mtgSel = (Button) findViewById(R.id.mtgButtonStart);
        final Button mtg20Start = (Button) findViewById(R.id.mtg20Start);
        final Button mtg40Start = (Button) findViewById(R.id.mtg40Start);

        Button yghStart = (Button) findViewById(R.id.yghButtonStart);

        yghStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getBaseContext(), Counter.class);
                intent.putExtra("game_mode", "2");
                intent.putExtra("life", "8000");
                startActivity(intent);
                finish();

            }
        });

        mtgSel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!mtgToggle)
                {
                    mtg20Start.setVisibility(View.VISIBLE);
                    mtg40Start.setVisibility(View.VISIBLE);
                    //mtgSel.setEnabled(false);
                    mtgToggle = true;
                }

                else
                {
                    mtg20Start.setVisibility(View.INVISIBLE);
                    mtg40Start.setVisibility(View.INVISIBLE);
                    //mtgSel.setEnabled(true);
                    mtgToggle = false;
                }

            }
        });

        mtg20Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getBaseContext(), Counter.class);
                intent.putExtra("game_mode", "1");
                intent.putExtra("life", "20");
                startActivity(intent);
                finish();

            }
        });

        mtg40Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getBaseContext(), Counter.class);
                intent.putExtra("game_mode", "1");
                intent.putExtra("life", "40");
                startActivity(intent);
                finish();

            }
        });
    }
}
