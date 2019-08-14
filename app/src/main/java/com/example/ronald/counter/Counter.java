package com.example.ronald.counter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;

public class Counter extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // global variables here (player scores etc..)
    int pOneScore = 0, pTwoScore =0;
    int pOnePoison = 0, pTwoPoison = 0;
    int inc = 0;
    int currentTurn = 0;
    int increment = inc;
    int gameMode = 0;
    boolean isFabClicked = false, isPoisonClicked = false;
    int lifeMtg = 20;

    //vector for both players turns
    ArrayList<int[]> turnHistory = new ArrayList<>();

    static final int PICK_INC_REQUEST = 1;

    Globals incr = Globals.getInstance();//globals ===============================


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_counter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*Intent sel = new Intent(Counter.this, gameSelector.class); // get a new game going
        sel.putExtra("gameMode", 0); //Optional parameters
        //Counter.this.startActivity(customInc);
        startActivityForResult(sel, PICK_INC_REQUEST);*/

        //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.mtg_game_icon);
        //incr.setInc(BuildConfig.VERSION_CODE);
        //incr.setInc(15);

        // set this inc. option disabled if a game is not started
        //MenuItem cusIncrementShow = (MenuItem) findViewById(R.id.nav_increment);
        //cusIncrementShow.setEnabled(false);
        //invalidateOptionsMenu();

        //set poison counters invis.

        Button pOnePAdd = (Button) findViewById(R.id.pOneAddP);
        Button pOnePSub = (Button) findViewById(R.id.pOneSubP);
        final TextView pOnePois = (TextView) findViewById(R.id.pOnePoison);

        Button pTwoPAdd = (Button) findViewById(R.id.pTwoAddP);
        Button pTwoPSub = (Button) findViewById(R.id.pTwoSubP);
        TextView pTwoPois = (TextView) findViewById(R.id.pTwoPoison);

        pOnePAdd.setVisibility(View.GONE);
        pOnePSub.setVisibility(View.GONE);
        pTwoPAdd.setVisibility(View.GONE);
        pTwoPSub.setVisibility(View.GONE);

        pOnePois.setVisibility(View.GONE);
        pTwoPois.setVisibility(View.GONE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        FloatingActionButton fabP = (FloatingActionButton) findViewById(R.id.fabPoison);
        FloatingActionButton fabSave = (FloatingActionButton) findViewById(R.id.fabSaveTurn);


        fab.setVisibility(View.GONE);
        fabP.setVisibility(View.GONE);

        fab.setImageResource(R.drawable.increment_icon_fab);
        fabP.setImageResource(R.drawable.mtg_poison_counter_icon);
        fabSave.setImageResource(R.drawable.save_button_icon);

        Intent intentSel = getIntent();

        String gm = intentSel.getStringExtra("game_mode");// change to get integer instead?
        String lifeSent = intentSel.getStringExtra("life");// above comment

        int gmAsInt = Integer.parseInt(gm);
        int lifeSentAsInt = Integer.parseInt(lifeSent);

        //init. new game modes

        //noinspection SimplifiableIfStatement
        TextView playerOneLifeScore = (TextView) findViewById(R.id.playerOneLife);
        TextView playerTwoLifeScore = (TextView) findViewById(R.id.playerTwoLife);

        Button playerOneLifeAdd = (Button) findViewById(R.id.playerOneAdd);
        Button playerOneLifeSub = (Button) findViewById(R.id.playerOneSub);

        Button playerTwoLifeAdd = (Button) findViewById(R.id.playerTwoAdd);
        Button playerTwoLifeSub = (Button) findViewById(R.id.playerTwoSub);

        if(gmAsInt == 1)
        {
            if(lifeSentAsInt == 20)
            {

                turnHistory.clear();
                currentTurn = 1;
                this.setTitle("Magic: The Gathering");

                playerOneLifeScore.setTextColor(getResources().getColor(R.color.pastelBlue));
                playerTwoLifeScore.setTextColor(getResources().getColor(R.color.pastelOrange));

                fab.setVisibility(View.VISIBLE);
                fabP.setVisibility(View.VISIBLE);

                isFabClicked = false;

                gameMode = 1;

                pOnePoison = 0;
                pTwoPoison = 0;

                lifeMtg = 20; // set start life for 20

                pOneScore = lifeMtg;
                pTwoScore = lifeMtg;

                playerOneLifeScore.setText("" + pOneScore);
                playerTwoLifeScore.setText("" + pTwoScore);


                playerOneLifeAdd.setVisibility(View.VISIBLE);
                playerOneLifeSub.setVisibility(View.VISIBLE);
                playerTwoLifeAdd.setVisibility(View.VISIBLE);
                playerTwoLifeSub.setVisibility(View.VISIBLE);

                pOnePAdd.setVisibility(View.GONE);
                pOnePSub.setVisibility(View.GONE);
                pTwoPAdd.setVisibility(View.GONE);
                pTwoPSub.setVisibility(View.GONE);

                pOnePois.setVisibility(View.GONE);
                pTwoPois.setVisibility(View.GONE);

                pOnePois.setText("" + pOnePoison);
                pTwoPois.setText("" + pTwoPoison);

                isPoisonClicked = false;

                increment = 1;

                Toast.makeText(getApplicationContext(),"New game set for Magic: The Gathering", Toast.LENGTH_SHORT).show();

            }

            else if (lifeSentAsInt == 40)
            {

                turnHistory.clear();
                currentTurn = 1;
                this.setTitle("Magic: The Gathering");

                playerOneLifeScore.setTextColor(getResources().getColor(R.color.pastelBlue));
                playerTwoLifeScore.setTextColor(getResources().getColor(R.color.pastelOrange));

                fab.setVisibility(View.VISIBLE);
                fabP.setVisibility(View.VISIBLE);

                isFabClicked = false;

                gameMode = 1;

                pOnePoison = 0;
                pTwoPoison = 0;

                //set start life at 40
                lifeMtg = 40;

                pOneScore = lifeMtg;
                pTwoScore = lifeMtg;

                playerOneLifeScore.setText("" + pOneScore);
                playerTwoLifeScore.setText("" + pTwoScore);

                pOnePois.setText("" + pOnePoison);
                pTwoPois.setText("" + pTwoPoison);

                playerOneLifeAdd.setVisibility(View.VISIBLE);
                playerOneLifeSub.setVisibility(View.VISIBLE);
                playerTwoLifeAdd.setVisibility(View.VISIBLE);
                playerTwoLifeSub.setVisibility(View.VISIBLE);

                pOnePAdd.setVisibility(View.GONE);
                pOnePSub.setVisibility(View.GONE);
                pTwoPAdd.setVisibility(View.GONE);
                pTwoPSub.setVisibility(View.GONE);

                pOnePois.setVisibility(View.GONE);
                pTwoPois.setVisibility(View.GONE);

                isPoisonClicked = false;

                increment = 1;

                Toast.makeText(getApplicationContext(),"New game set for Magic: The Gathering", Toast.LENGTH_SHORT).show();

            }
        }

        else if(gmAsInt == 2)
        {

            turnHistory.clear();
            currentTurn = 1;
            this.setTitle("Yu-Gi-Oh");

            playerOneLifeScore.setTextColor(getResources().getColor(R.color.pastelBlue));
            playerTwoLifeScore.setTextColor(getResources().getColor(R.color.pastelOrange));

            fab.setVisibility(View.VISIBLE);
            fabP.setVisibility(View.GONE);

            isFabClicked = false;

            gameMode = 2;

            pOnePoison = 0;
            pTwoPoison = 0;

            pOneScore = 8000;
            pTwoScore = 8000;

            playerOneLifeScore.setText("" + pOneScore);
            playerTwoLifeScore.setText("" + pTwoScore);

            playerOneLifeAdd.setVisibility(View.VISIBLE);
            playerOneLifeSub.setVisibility(View.VISIBLE);
            playerTwoLifeAdd.setVisibility(View.VISIBLE);
            playerTwoLifeSub.setVisibility(View.VISIBLE);

            pOnePAdd.setVisibility(View.GONE);
            pOnePSub.setVisibility(View.GONE);
            pTwoPAdd.setVisibility(View.GONE);
            pTwoPSub.setVisibility(View.GONE);

            pOnePois.setVisibility(View.GONE);
            pTwoPois.setVisibility(View.GONE);

            pOnePois.setText("" + pOnePoison);
            pTwoPois.setText("" + pTwoPoison);

            isPoisonClicked = false;

            increment = 100;

            Toast.makeText(getApplicationContext(),"New game set for Yu-Gi-Oh", Toast.LENGTH_SHORT).show();
        }

        // event listener to toggle vis. of poison counters
        fabP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Button pOnePAdd = (Button) findViewById(R.id.pOneAddP);
                Button pOnePSub = (Button) findViewById(R.id.pOneSubP);
                TextView pOnePois = (TextView) findViewById(R.id.pOnePoison);

                Button pTwoPAdd = (Button) findViewById(R.id.pTwoAddP);
                Button pTwoPSub = (Button) findViewById(R.id.pTwoSubP);
                TextView pTwoPois = (TextView) findViewById(R.id.pTwoPoison);

                if (!isPoisonClicked)
                {
                    pOnePAdd.setVisibility(View.VISIBLE);
                    pOnePSub.setVisibility(View.VISIBLE);
                    pTwoPAdd.setVisibility(View.VISIBLE);
                    pTwoPSub.setVisibility(View.VISIBLE);

                    pOnePois.setVisibility(View.VISIBLE);
                    pTwoPois.setVisibility(View.VISIBLE);

                    isPoisonClicked = true;
                }

                else
                {
                    pOnePAdd.setVisibility(View.GONE);
                    pOnePSub.setVisibility(View.GONE);
                    pTwoPAdd.setVisibility(View.GONE);
                    pTwoPSub.setVisibility(View.GONE);

                    pOnePois.setVisibility(View.GONE);
                    pTwoPois.setVisibility(View.GONE);

                    isPoisonClicked = false;
                }

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //my code here

        //fabSave.setVisibility(View.INVISIBLE);
        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"Turn saved", Toast.LENGTH_SHORT).show();
                currentTurn = currentTurn + 1;
                int[] currTurn = {pOneScore, pTwoScore, pOnePoison, pTwoPoison};
                turnHistory.add(currTurn);

                Snackbar.make(view,"Saved scores to turn " + (currentTurn - 1), Snackbar.LENGTH_LONG).
                        setAction("Undo", new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                currentTurn = currentTurn - 1;
                                //int[] currTurn = {pOneScore, pTwoScore, pOnePoison, pTwoPoison};
                                turnHistory.remove(turnHistory.size() - 1);
                                Toast.makeText(getApplicationContext(),"Turn scores undone", Toast.LENGTH_SHORT).show();

                            }

                        }).show();


                //Log.d("ron", "added, size is " + turnHistory.size());

            }
        });


        //FloatingActionButton incrementChanger = (FloatingActionButton)findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    incr.setInc(BuildConfig.VERSION_CODE);

                    if(gameMode == 1)
                    {
                        //toggle increment from 1 to 5 (for mtg)
                        if(isFabClicked)
                        {
                            incr.setInc(1);
                            increment = incr.getInc();
                            isFabClicked = false;
                            Toast.makeText(getApplicationContext(),"Increment changed to " + increment, Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            incr.setInc(5);
                            increment = incr.getInc();
                            isFabClicked = true;
                            Toast.makeText(getApplicationContext(),"Increment changed to " + increment, Toast.LENGTH_SHORT).show();
                        }
                    }

                    else if (gameMode == 2)
                    {
                        // toggle increment from 100 to 1000 (for yugioh)
                        if(isFabClicked)
                        {
                            incr.setInc(100);
                            increment = incr.getInc();
                            isFabClicked = false;
                            Toast.makeText(getApplicationContext(),"Increment changed to " + increment, Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            incr.setInc(1000);
                            increment = incr.getInc();
                            isFabClicked = true;
                            Toast.makeText(getApplicationContext(),"Increment changed to " + increment, Toast.LENGTH_SHORT).show();
                        }
                    }

                    else
                    {
                        increment = 0;
                    }
                }
            });

        //textViews used here
        //TextView playerOneLifeScore = (TextView) findViewById(R.id.playerOneLife);
        //TextView playerTwoLifeScore = (TextView) findViewById(R.id.playerTwoLife);

        //Buttons used here
        /*Button playerOneLifeAdd = (Button) findViewById(R.id.playerOneAdd);
        Button playerOneLifeSub = (Button) findViewById(R.id.playerOneSub);

        Button playerTwoLifeAdd = (Button) findViewById(R.id.playerTwoAdd);
        Button playerTwoLifeSub = (Button) findViewById(R.id.playerTwoSub);*/

        //Button pOnePAdd = (Button) findViewById(R.id.pOneAddP);
        //Button pOnePSub = (Button) findViewById(R.id.pOneSubP);

        //Button pTwoPAdd = (Button) findViewById(R.id.pTwoAddP);
        //Button pTwoPSub = (Button) findViewById(R.id.pTwoSubP);

        //p1 adding
        playerOneLifeAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView playerOneLifeScore = (TextView) findViewById(R.id.playerOneLife);
                if(validScore(pOneScore + increment))
                {
                    pOneScore = pOneScore + increment;
                    playerOneLifeScore.setText("" + pOneScore);
                }
            }
        });

        //p1 subtracting
        playerOneLifeSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView playerOneLifeScore = (TextView) findViewById(R.id.playerOneLife);

                if(validScore(pOneScore - increment))
                {
                    pOneScore = pOneScore - increment;
                    playerOneLifeScore.setText("" + pOneScore);
                }

                else
                {
                    pOneScore = 0;
                    playerOneLifeScore.setText("" + pOneScore);
                }
            }
        });

        //p2 adding
        playerTwoLifeAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView playerTwoLifeScore = (TextView) findViewById(R.id.playerTwoLife);

                if(validScore(pTwoScore + increment))
                {
                    pTwoScore = pTwoScore + increment;
                    playerTwoLifeScore.setText("" + pTwoScore);
                }
            }
        });

        //p2 subtracting
        playerTwoLifeSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView playerTwoLifeScore = (TextView) findViewById(R.id.playerTwoLife);

                if(validScore(pTwoScore - increment))
                {
                    pTwoScore = pTwoScore - increment;
                    playerTwoLifeScore.setText("" + pTwoScore);
                }

                else
                {
                    pTwoScore = 0;
                    playerTwoLifeScore.setText("" + pTwoScore);

                }
            }
        });

        //p1 poison add
        pOnePAdd.setOnClickListener(new View.OnClickListener() {
            @Override

            //TextView pTwoPois = (TextView) findViewById(R.id.pTwoPoison);

            public void onClick(View view) {

                TextView pOnePois = (TextView) findViewById(R.id.pOnePoison);

                if((pOnePoison + 1) <= 10)
                {
                    pOnePoison = pOnePoison + 1;
                    pOnePois.setText("" + pOnePoison);
                }

                else
                {
                    pOnePois.setText("10");
                }
            }
        });

        //p1 poison subtracting
        pOnePSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView pOnePois = (TextView) findViewById(R.id.pOnePoison);

                if(validScore(pOnePoison - 1))
                {
                    pOnePoison = pOnePoison - 1;
                    pOnePois.setText("" + pOnePoison);
                }

                else
                {
                    pOnePoison = 0;
                    pOnePois.setText("" + pOnePoison);
                }
            }
        });


        //p2 poison add
        pTwoPAdd.setOnClickListener(new View.OnClickListener() {
            @Override

            //TextView pTwoPois = (TextView) findViewById(R.id.pTwoPoison);

            public void onClick(View view) {

                TextView pTwoPois = (TextView) findViewById(R.id.pTwoPoison);

                if((pTwoPoison + 1) <= 10)
                {
                    pTwoPoison = pTwoPoison + 1;
                    pTwoPois.setText("" + pTwoPoison);
                }

                else
                {
                    pTwoPois.setText("10");
                }
            }
        });

        //p2 poison subtracting
        pTwoPSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView pTwoPois = (TextView) findViewById(R.id.pTwoPoison);

                if(validScore(pTwoPoison - 1))
                {
                    pTwoPoison = pTwoPoison - 1;
                    pTwoPois.setText("" + pTwoPoison);
                }

                else
                {
                    pTwoPoison = 0;
                    pTwoPois.setText("" + pTwoPoison);
                }
            }
        });


        TextView p1Sc = (TextView) findViewById(R.id.playerOneLife);
        TextView p2Sc = (TextView) findViewById(R.id.playerTwoLife);

        //touch capab. for p1
        //RelativeLayout pOneLayout = (RelativeLayout) findViewById(R.id.mainLayout);

        p1Sc.setOnTouchListener(new View.OnTouchListener() {// p1 touch cab
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                RelativeLayout pOneLayout = (RelativeLayout) findViewById(R.id.mainLayout);


                TextView p1Sc = (TextView) findViewById(R.id.playerOneLife);
                int width = p1Sc.getWidth();
                //int height = pOneLayout.getHeight();

                int xc = (int)event.getX();
                int yc = (int)event.getY();

                TextView playerOneLifeScore = (TextView) findViewById(R.id.playerOneLife);
                //TextView playerTwoLifeScore = (TextView) findViewById(R.id.playerTwoLife);

                if(event.getAction() == MotionEvent.ACTION_DOWN) {

                    if ( xc < width / 2 )//p1 sub
                    {
                        //Log.d("ron", "Pone subbed " + xc + "," + yc + "|" + width );
                        if (validScore(pOneScore - increment)) {
                            playerOneLifeScore.setText("" + (pOneScore - increment));
                            pOneScore = pOneScore - increment;
                        }
                        else {
                            playerOneLifeScore.setText("0");
                            pOneScore = 0;
                        }

                    } else if ( xc > width / 2 )//p1 add
                    {
                        //Log.d("ron", "Pone added");
                        if (validScore(pOneScore + increment)) {
                            playerOneLifeScore.setText("" + (pOneScore + increment));
                            pOneScore = pOneScore + increment;
                        }
                    }

                }
                return true;//always return true to consume event
            }
        });

        p2Sc.setOnTouchListener(new View.OnTouchListener() {// p1 touch cab
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                RelativeLayout pOneLayout = (RelativeLayout) findViewById(R.id.mainLayout);
                TextView p2Sc = (TextView) findViewById(R.id.playerTwoLife);

                int width = p2Sc.getWidth();
                //int height = pOneLayout.getHeight();

                int xc = (int)event.getX();
                int yc = (int)event.getY();

                //TextView playerOneLifeScore = (TextView) findViewById(R.id.playerOneLife);
                TextView playerTwoLifeScore = (TextView) findViewById(R.id.playerTwoLife);

                if(event.getAction() == MotionEvent.ACTION_DOWN) {

                    if ( xc < width / 2 )//p2 sub
                    {
                        //Log.d("ron", "Ptwo subbed");
                        if (validScore(pTwoScore - increment)) {
                            playerTwoLifeScore.setText("" + (pTwoScore - increment));
                            pTwoScore = pTwoScore - increment;
                        }
                        else {
                            playerTwoLifeScore.setText("0");
                            pTwoScore = 0;
                        }
                    }

                    else if ( xc > width / 2 )//p2 add
                    {
                        //Log.d("ron", "Ptwo added");
                        if (validScore(pTwoScore + increment)) {
                            playerTwoLifeScore.setText("" + (pTwoScore + increment));
                            pTwoScore = pTwoScore + increment;
                        }
                    }

                }
                return true;//always return true to consume event
            }
        });


        //touch capab. for p1
        /*RelativeLayout pOneLayout = (RelativeLayout) findViewById(R.id.mainLayout);

        pOneLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int width = size.x;
                int height = size.y;
                int xc = (int)event.getX();
                int yc = (int)event.getY();
                TextView playerOneLifeScore = (TextView) findViewById(R.id.playerOneLife);
                TextView playerTwoLifeScore = (TextView) findViewById(R.id.playerTwoLife);

                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (xc < width / 2 && yc < height / 2)//p1 sub
                    {
                        //Log.d("ron", "Pone subbed");
                        if (validScore(pOneScore - increment)) {
                            playerOneLifeScore.setText("" + (pOneScore - increment));
                            pOneScore = pOneScore - increment;
                        }
                        else {
                            playerOneLifeScore.setText("0");
                            pOneScore = 0;
                        }

                    } else if (xc > width / 2 && yc < height / 2)//p1 add
                    {
                        //Log.d("ron", "Pone added");
                        if (validScore(pOneScore + increment)) {
                            playerOneLifeScore.setText("" + (pOneScore + increment));
                            pOneScore = pOneScore + increment;
                        }
                    }

                    else if (xc < width / 2 && yc > height / 2)//p2 sub
                    {
                        //Log.d("ron", "Ptwo subbed");
                        if (validScore(pTwoScore - increment)) {
                            playerTwoLifeScore.setText("" + (pTwoScore - increment));
                            pTwoScore = pTwoScore - increment;
                        }
                        else {
                            playerTwoLifeScore.setText("0");
                            pTwoScore = 0;
                        }
                    } else if (xc > width / 2 && yc > height / 2)//p2 add
                    {
                        //Log.d("ron", "Ptwo added");
                        if (validScore(pTwoScore + increment)) {
                            playerTwoLifeScore.setText("" + (pTwoScore + increment));
                            pTwoScore = pTwoScore + increment;
                        }
                    }
                }
                return true;//always return true to consume event
            }
        });*/



    }

    // code for edittext from http://stackoverflow.com/questions/4828636/edittext-clear-focus-on-touch-outside
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {

                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }


            }
        }
        return super.dispatchTouchEvent( event );

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String ans = data.getStringExtra("result");
                increment = Integer.parseInt(ans);
                isFabClicked = true; // to reset fab button to change increment to lower value
                Toast.makeText(getApplicationContext(),"Increment changed to " + increment, Toast.LENGTH_SHORT).show();

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            moveTaskToBack(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.counter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        TextView playerOneLifeScore = (TextView) findViewById(R.id.playerOneLife);
        TextView playerTwoLifeScore = (TextView) findViewById(R.id.playerTwoLife);

        Button playerOneLifeAdd = (Button) findViewById(R.id.playerOneAdd);
        Button playerOneLifeSub = (Button) findViewById(R.id.playerOneSub);

        Button playerTwoLifeAdd = (Button) findViewById(R.id.playerTwoAdd);
        Button playerTwoLifeSub = (Button) findViewById(R.id.playerTwoSub);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        FloatingActionButton fabP = (FloatingActionButton) findViewById(R.id.fabPoison);

        // poison counter stuff
        Button pOnePAdd = (Button) findViewById(R.id.pOneAddP);
        Button pOnePSub = (Button) findViewById(R.id.pOneSubP);
        TextView pOnePois = (TextView) findViewById(R.id.pOnePoison);

        Button pTwoPAdd = (Button) findViewById(R.id.pTwoAddP);
        Button pTwoPSub = (Button) findViewById(R.id.pTwoSubP);
        TextView pTwoPois = (TextView) findViewById(R.id.pTwoPoison);

        //MenuItem cusIncrementShow = (MenuItem) findViewById(R.id.nav_increment);

        switch (id){
            case R.id.action_20_mtg: // set scores and enable adding/sub

                turnHistory.clear();
                currentTurn = 1;

                this.setTitle("Magic: The Gathering");

                playerOneLifeScore.setTextColor(getResources().getColor(R.color.pastelBlue));
                playerTwoLifeScore.setTextColor(getResources().getColor(R.color.pastelOrange));

                fab.setVisibility(View.VISIBLE);
                fabP.setVisibility(View.VISIBLE);

                isFabClicked = false;

                gameMode = 1;

                pOnePoison = 0;
                pTwoPoison = 0;

                lifeMtg = 20; // set start life for 20

                pOneScore = lifeMtg;
                pTwoScore = lifeMtg;

                playerOneLifeScore.setText("" + pOneScore);
                playerTwoLifeScore.setText("" + pTwoScore);


                playerOneLifeAdd.setVisibility(View.VISIBLE);
                playerOneLifeSub.setVisibility(View.VISIBLE);
                playerTwoLifeAdd.setVisibility(View.VISIBLE);
                playerTwoLifeSub.setVisibility(View.VISIBLE);

                pOnePAdd.setVisibility(View.GONE);
                pOnePSub.setVisibility(View.GONE);
                pTwoPAdd.setVisibility(View.GONE);
                pTwoPSub.setVisibility(View.GONE);

                pOnePois.setVisibility(View.GONE);
                pTwoPois.setVisibility(View.GONE);

                pOnePois.setText("" + pOnePoison);
                pTwoPois.setText("" + pTwoPoison);

                isPoisonClicked = false;

                increment = 1;

                Toast.makeText(getApplicationContext(),"New game set for Magic: The Gathering", Toast.LENGTH_SHORT).show();

                break;

            case R.id.action_40_mtg:

                turnHistory.clear();
                currentTurn = 1;

                this.setTitle("Magic: The Gathering");

                playerOneLifeScore.setTextColor(getResources().getColor(R.color.pastelBlue));
                playerTwoLifeScore.setTextColor(getResources().getColor(R.color.pastelOrange));

                fab.setVisibility(View.VISIBLE);
                fabP.setVisibility(View.VISIBLE);

                isFabClicked = false;

                gameMode = 1;

                pOnePoison = 0;
                pTwoPoison = 0;

                //set start life at 40
                lifeMtg = 40;

                pOneScore = lifeMtg;
                pTwoScore = lifeMtg;

                playerOneLifeScore.setText("" + pOneScore);
                playerTwoLifeScore.setText("" + pTwoScore);

                pOnePois.setText("" + pOnePoison);
                pTwoPois.setText("" + pTwoPoison);

                playerOneLifeAdd.setVisibility(View.VISIBLE);
                playerOneLifeSub.setVisibility(View.VISIBLE);
                playerTwoLifeAdd.setVisibility(View.VISIBLE);
                playerTwoLifeSub.setVisibility(View.VISIBLE);

                pOnePAdd.setVisibility(View.GONE);
                pOnePSub.setVisibility(View.GONE);
                pTwoPAdd.setVisibility(View.GONE);
                pTwoPSub.setVisibility(View.GONE);

                pOnePois.setVisibility(View.GONE);
                pTwoPois.setVisibility(View.GONE);

                isPoisonClicked = false;

                increment = 1;

                Toast.makeText(getApplicationContext(),"New game set for Magic: The Gathering", Toast.LENGTH_SHORT).show();

                break;


            case R.id.action_ygh: // set scores and enable adding/sub

                //cusIncrementShow.setEnabled(true);
                turnHistory.clear();
                currentTurn = 1;

                this.setTitle("Yu-Gi-Oh");

                playerOneLifeScore.setTextColor(getResources().getColor(R.color.pastelBlue));
                playerTwoLifeScore.setTextColor(getResources().getColor(R.color.pastelOrange));

                fab.setVisibility(View.VISIBLE);
                fabP.setVisibility(View.GONE);

                isFabClicked = false;

                gameMode = 2;

                pOnePoison = 0;
                pTwoPoison = 0;

                pOneScore = 8000;
                pTwoScore = 8000;

                playerOneLifeScore.setText("" + pOneScore);
                playerTwoLifeScore.setText("" + pTwoScore);

                playerOneLifeAdd.setVisibility(View.VISIBLE);
                playerOneLifeSub.setVisibility(View.VISIBLE);
                playerTwoLifeAdd.setVisibility(View.VISIBLE);
                playerTwoLifeSub.setVisibility(View.VISIBLE);

                pOnePAdd.setVisibility(View.GONE);
                pOnePSub.setVisibility(View.GONE);
                pTwoPAdd.setVisibility(View.GONE);
                pTwoPSub.setVisibility(View.GONE);

                pOnePois.setVisibility(View.GONE);
                pTwoPois.setVisibility(View.GONE);

                pOnePois.setText("" + pOnePoison);
                pTwoPois.setText("" + pTwoPoison);

                isPoisonClicked = false;

                increment = 100;

                Toast.makeText(getApplicationContext(),"New game set for Yu-Gi-Oh", Toast.LENGTH_SHORT).show();
                break;

        }


        return super.onOptionsItemSelected(item);
    }

    // function to check for valid scores in various games using a game id
    public boolean validScore (int score){

        boolean isOk = true;
        if (gameMode == 1) // for mtg cases
        {
            if(score < 0)
            {
                isOk = false;
            }
        }

        else if (gameMode == 2) // for ygh cases
        {
            if (score < 0)
            {
                isOk = false;
            }
        }
        return isOk;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_increment) {
            // Handle the increment action
            //startActivity(new Intent(Counter.this.customInc.class));

            if(gameMode != 0)
            {
                Intent customInc = new Intent(Counter.this, CustomInc.class);
                customInc.putExtra("Inc", 0); //Optional parameters
                //Counter.this.startActivity(customInc);
                startActivityForResult(customInc, PICK_INC_REQUEST);
            }

            else
            {
                Toast.makeText(getApplicationContext(),"Please select a game first", Toast.LENGTH_SHORT).show();
            }

        }
        else if (id == R.id.nav_diceRoll) {
            Intent dice = new Intent(Counter.this, DiceRoller.class);
            Counter.this.startActivity(dice);

        }
        else if (id == R.id.nav_coinFlip) {
            Intent coin = new Intent(Counter.this, coin_flipper.class);
            Counter.this.startActivity(coin);

        }
        else if (id == R.id.nav_timer) {
            Intent time = new Intent(Counter.this, timer.class);
            Counter.this.startActivity(time);

        }
        else if (id == R.id.nav_history) {

            EditText p1 = (EditText) findViewById(R.id.playerOne);
            EditText p2 = (EditText) findViewById(R.id.playerTwo);

            Intent history = new Intent(Counter.this, turn_history.class);
            history.putExtra("vector", turnHistory);
            history.putExtra("gm", "" + gameMode);
            history.putExtra("playerOne", p1.getText().toString());
            history.putExtra("playerTwo", p2.getText().toString());

            Counter.this.startActivity(history);

        }
        else if (id == R.id.nav_notes) {
            Intent notes = new Intent(Counter.this, notes.class);
            Counter.this.startActivity(notes);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
