package com.ping23.scratch.gson;

public final class ImmutableClass {
    
    public static final String JSON_ONE = "{"
            + "name: \"One\","
            + "code: 404,"
            + "message: \"Hiya!\","
            + "count: 23,"
            + "clean: false,"
            + "ready: \"true\""
            + "}";
    
    private final String name;
    private final int code;
    private final String message;
    private final Integer count;
    private final boolean clean;
    private final Boolean ready;
    
    private ImmutableClass(String name, int code, String message, Integer count, boolean clean, Boolean ready) {
        this.name = name;
        this.code = code;
        this.message = message;
        this.count = count;
        this.clean = clean;
        this.ready = ready;
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
