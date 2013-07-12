package com.tld;

import java.util.Random;

/**
 *
 * @author armando
 */
public class RandomNumber {
    public static String pickRandomColor(){   //notice that this method doesn't have to be  the same as the one that will apear in your tag 'randomColor'. This is only true for a class that implements a Custom El Function.
        String [] colors = new String[3];
        colors[0] = "Red";
        colors[1] = "Blue";
        colors[2] = "Yellow";
        
        Random r = new Random();
        return colors[Math.abs(r.nextInt(3)) % 4]; //return one of the colors in array at random
    }
    
    
}
