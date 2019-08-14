package com.example.ronald.counter;

/**
 * Created by Ronald on 2016-08-18.
 */
public class Globals {
    private static Globals instance;

    private int increment = 0;

    public void setInc(int num)// set increment
    {
        this.increment = num;
    }

    public int getInc()
    {
        return this.increment;
    }

    public static synchronized Globals getInstance()
    {
        if(instance == null)
        {
            instance = new Globals();

        }

        return instance;

    }
}
