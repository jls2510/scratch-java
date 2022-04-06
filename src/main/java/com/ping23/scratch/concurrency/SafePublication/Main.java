package com.ping23.scratch.concurrency.SafePublication;

public class Main {

    public static void main(String[] args) {

        Singleton singleton;

        System.out.println("Singleton from SynchronizedCLFactory");
        singleton = new SynchronizedCLFactory().getInstance();
        singleton.increment();
        System.out.println(singleton);
        singleton.increment();
        System.out.println(singleton);

        System.out.println("Singleton from HolderFactory");
        singleton = HolderFactory.getInstance();
        singleton.increment();
        System.out.println(singleton);
        singleton.increment();
        System.out.println(singleton);

        System.out.println("Singleton from SafeDCLFactory");
        singleton = new SafeDCLFactory().getInstance();
        singleton.increment();
        System.out.println(singleton);
        singleton.increment();
        System.out.println(singleton);

        System.out.println("Singleton from FinalWrapperFactory");
        singleton = new FinalWrapperFactory().getInstance();
        singleton.increment();
        System.out.println(singleton);
        singleton.increment();
        System.out.println(singleton);

    }

}
