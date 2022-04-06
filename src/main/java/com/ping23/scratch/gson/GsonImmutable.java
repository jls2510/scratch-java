package com.ping23.scratch.gson;

import com.google.gson.Gson;

public class GsonImmutable {
    
    public static void main(String[] args) {

        System.out.println("hello");
        
        /**
         * This demostrates Gson's ability to deserialize into an immutable class
         */
        ImmutableClass one = new Gson().fromJson(ImmutableClass.JSON_ONE, ImmutableClass.class);
        System.out.println("instance one: " + one.toString());
    }

}
