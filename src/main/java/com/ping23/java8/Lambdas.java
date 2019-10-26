package com.ping23.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Lambdas {


    public static void main(String[] args) {
        List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6);

        values.forEach(new Consumer<Integer>() {
            public void accept(Integer value) {
                System.out.println(value);
            }
        });

        values.forEach(value -> {
            value += 1;
            System.out.println("value: " + value);
        });

        values.forEach(value ->
                System.out.println("value: " + value));

        values.forEach(System.out::println);

        Consumer<Integer> myConsumer = (Integer value) -> System.out.println("value: " + value);

        values.forEach(myConsumer);

        // lambda expression to implement interface. This interface
        // by default implements abstractFunction()
        MyFunctionalInterface myFunctionalInterfaceImpl = (String label, int anInt)->System.out.println(label + 2*anInt);

        // This calls above lambda expression and prints 10.
        myFunctionalInterfaceImpl.abstractFunction("result = ", 5);

    }


    @FunctionalInterface
    interface MyFunctionalInterface {
        // An abstract function
        void abstractFunction(String aString, int anInt);

    }
}
