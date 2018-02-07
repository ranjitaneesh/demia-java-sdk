package com.demia.sdk.media.metadata;

public class ConvertCommand implements Command {

    private static final String OPERATION = "convert";

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getUrlParameters() {
        return "op=" + OPERATION + "&type=" + type;
    }

}
