package com.demia.sdk.media.metadata;

public class IdentifyCommand implements Command {
    
    public static final String OPERATION = "identify";
 
    @Override
    public String getUrlParameters() {
        return OPERATION;
    }
}
