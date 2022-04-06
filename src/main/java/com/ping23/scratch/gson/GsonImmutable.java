package com.ping23.scratch.gson;

import com.google.gson.Gson;

public class GsonImmutable {
    
    public static void main(String[] args) {

        System.out.println("hello");
        
        /**
         * This demostrates Gson's ability to deserialize into an immutable class with a parameterized constructor
         */
        ImmutableClassOne one = new Gson().fromJson(ImmutableClassOne.JSON_ONE, ImmutableClassOne.class);
        System.out.println("instance one: " + one.toString());
        
        
        /**
         * This demostrates Gson's ability to deserialize into an immutable class with a default constructor
         * The deserialized values will overwrite the default values
         */
        ImmutableClassOne two = new Gson().fromJson(ImmutableClassTwo.JSON_TWO, ImmutableClassOne.class);
        System.out.println("instance two: " + two.toString());
    }

}
