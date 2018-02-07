package com.demia.sdk.media.metadata;

public class FlopCommand implements Command {

    public static final String OPERATION = "flop";
    
    @Override
    public String getUrlParameters() {
        return OP + OPERATION;
    }
}
