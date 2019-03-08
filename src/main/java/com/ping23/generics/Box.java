package com.ping23.generics;

public class Box<T> {
    private T t;

    public void add(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public static void main(String[] args) {
        Box<Integer> integerBox = new Box<>();
        Box<String> stringBox = new Box<>();

        integerBox.add(13);
        stringBox.add("Hello World");

        System.out.printf("integerBox Value :%d\n\n", integerBox.get());
        System.out.printf("stringBox Value :%s\n", stringBox.get());
    }
}
