package com.demia.sdk.media.metadata;

public class CropCommand implements Command {

    public static final String OPERATION = "crop";

    public static final String TOP_LEFT_WIDTH = "topLeftW";
    public static final String TOP_LEFT_HEIGHT = "topLeftH";
    public static final String CROP_WIDTH = "w";
    public static final String CROP_HEIGHT = "h";

    int topLeftWidthOffset;
    int topLeftHeightOffset;
    int width;
    int height;

    @Override
    public String getUrlParameters() {
        StringBuilder builder = new StringBuilder();
        
        builder.append(OP).append(OPERATION).append("&topLeftWidthOffset=").append(topLeftHeightOffset)
                .append("&topLeftHeightOffset=").append(topLeftWidthOffset).append("&w=").append(width).append("&h=")
                .append(height);
        
        return builder.toString();
    }

}
