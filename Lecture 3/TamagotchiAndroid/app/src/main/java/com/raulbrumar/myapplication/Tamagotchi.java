package com.raulbrumar.myapplication;

import android.util.Log;

public class Tamagotchi extends Thread
{
    public interface TamagotchiCallBack
    {
        public void tamagotchiStatusChanged(int index, int food);
    }

    private boolean alive;
    private int index;
    private int foodLeft;
    private int feedingTimeMs;
    private TamagotchiCallBack callbackInterface;

    // initialising the tamagotchi
    public Tamagotchi(int index, int initialFood, int feedingTimeMs, TamagotchiCallBack callbackInterface)
    {
        this.index = index;
        foodLeft = initialFood;
        this.feedingTimeMs = feedingTimeMs;
        alive = true;

        this.callbackInterface = callbackInterface;
    }

    public boolean getAlive()
    {
        return alive;
    }

    public boolean feed(int foodGiven) // feed the tamagotchi if it is still alive
    {
        if (alive)
        {
            foodLeft += foodGiven;

            if (foodLeft > 20)
                alive = false;

            callbackInterface.tamagotchiStatusChanged(index, foodLeft);
            return true;
        }
        return false;
    }

    public int getFood()
    {
        return foodLeft;
    }

    public void run()
    {
        try
        {
            while(alive)
            {
                Log.d("AAA", "run: ");
                if (foodLeft > 20) // if tamagotchi has been overfed kill it
                {
                    alive = false;
                }
                else // remove 1 food
                {
                    foodLeft--;
                    if (foodLeft < 0) // kill if it ran out of food
                        alive = false;
                }

                // callback with status changed
                callbackInterface.tamagotchiStatusChanged(index, foodLeft);
                Log.d("AAA", "run: " + alive);
                sleep(feedingTimeMs);
            }
        }
        catch(Exception e)
        {

        }
    }
}