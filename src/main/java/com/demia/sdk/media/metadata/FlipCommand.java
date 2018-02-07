package com.demia.sdk.media.metadata;

public class FlipCommand implements Command {
    
    public static final String OPERATION = "flip";
    
    @Override
    public String getUrlParameters() {
        return OPERATION;
    }
}
