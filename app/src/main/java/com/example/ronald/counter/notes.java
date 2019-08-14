package com.example.ronald.counter;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class notes extends AppCompatActivity {

    static final String NOTES_USER = "notes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState); // Always call the superclass first

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_notes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setTitle("Notes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        EditText mytext = (EditText) findViewById(R.id.UserNotes);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //TextView temps = (TextView) findViewById(R.id.textView2);

        //mytext.setHorizontallyScrolling(false);
        //mytext.setMaxLines(300);
        String temp = readFromFile(this.getApplicationContext());
        mytext.setText(temp);
        //mytext.setText(readFromFile(this.getApplicationContext()));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.notes_save);
        Button clear = (Button) findViewById(R.id.clearButton);

        //fab.setVisibility(View.INVISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // = mytext.getText().toString();
                EditText mytext = (EditText) findViewById(R.id.UserNotes);
                writeToFile(mytext.getText().toString(), notes.this);
                Toast.makeText(getApplicationContext(),"Note saved", Toast.LENGTH_SHORT).show();

                /* Snackbar.make(view,"Saved note", Snackbar.LENGTH_LONG).
                        setAction("Undo", new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                EditText mytext = (EditText) findViewById(R.id.UserNotes);
                                writeToFile(mytext.getText().toString(), notes.this);

                                Toast.makeText(getApplicationContext(),"Undid save", Toast.LENGTH_SHORT).show();

                            }

                        }).show();*/

        }
        });

        //p2 poison subtracting
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText mytext = (EditText) findViewById(R.id.UserNotes);
                final String temp = mytext.getText().toString();
                mytext.setText("");

                Snackbar.make(view,"Cleared note", Snackbar.LENGTH_LONG).
                        setAction("Undo", new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                EditText mytext = (EditText) findViewById(R.id.UserNotes);
                                mytext.setText(temp);
                                Toast.makeText(getApplicationContext(),"Clear undone", Toast.LENGTH_SHORT).show();

                            }

                        }).show();

            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {


        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);

        // Save the user's current note state
        EditText mytext = (EditText) findViewById(R.id.UserNotes);
        String note = mytext.getText().toString();

        savedInstanceState.putString(NOTES_USER , note);



    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        // Save the user's current note state
        EditText mytext = (EditText) findViewById(R.id.UserNotes);

        String note = savedInstanceState.getString(NOTES_USER);
        mytext.setText(note);



    }

    // code from http://stackoverflow.com/questions/14376807/how-to-read-write-string-from-a-file-in-android
    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("notes.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString).append("\n");
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            //Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            //Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    private void writeToFile(String data,Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("notes.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            //Log.e("Exception", "File write failed: " + e.toString());
        }
    }

}
