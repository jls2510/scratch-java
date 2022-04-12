package com.ping23.scratch.gson.nested;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonDeserializeCollection {
    
    public static void main(String[] args) {
        
        System.out.println("\nGsonDeserializeCollection");
        
        System.out.println("\ndeserialize ONE...");
        TemplateEntity recordOne = new Gson().fromJson(ExampleJsonResponses.ONE, TemplateEntity.class);
        System.out.println("result: " + recordOne);

        System.out.println("\ndeserialize TWO...");
        TemplateEntity recordTwo = new Gson().fromJson(ExampleJsonResponses.TWO, TemplateEntity.class);
        System.out.println("result: " + recordTwo);

        System.out.println("\ndeserialize THREE...");
        TemplateEntity recordThree = new Gson().fromJson(ExampleJsonResponses.THREE, TemplateEntity.class);
        System.out.println("result: " + recordThree);

        System.out.println("\ndeserialize LIST...");
        Type collectionType = new TypeToken<List<TemplateEntity>>(){}.getType();
        List<TemplateEntity> templateEntities = new Gson().fromJson(ExampleJsonResponses.LIST, collectionType);
        System.out.println("result: " + templateEntities);

    }

}
