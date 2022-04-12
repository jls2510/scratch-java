package com.ping23.scratch.gson.immutable;

public final class ImmutableClassTwo {
    
    public static final String JSON_TWO = "{"
            + "name: \"Two\","
            + "code: 209,"
            + "message: \"Aww Shucks...\","
            + "count: 17,"
            + "clean: true,"
            + "ready: \"false\""
            + "}";
    
    private final String name;
    private final int code;
    private final String message;
    private final Integer count;
    private final boolean clean;
    private final Boolean ready;
    
    private ImmutableClassTwo() {
        this.name = "bad";
        this.code = 401;
        this.message = "bad";
        this.count = 99;
        this.clean = false;
        this.ready = true;
    }
    
    public String toString() {
        StringBuilder stringValue = new StringBuilder().append("ImmutableClass: " + name + ": ");
        stringValue.append("code: " + code + ": ");
        stringValue.append("message: " + message + ": ");
        stringValue.append("count: " + count + ": ");
        stringValue.append("clean: " + clean + ": ");
        stringValue.append("ready: " + ready);
        
        
        return stringValue.toString();
    }

}
