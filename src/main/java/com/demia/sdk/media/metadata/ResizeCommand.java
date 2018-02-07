package com.demia.sdk.media.metadata;

public class ResizeCommand implements Command {
    
    public static final String OPERATION = "resize";
    
    private int width;
    private int height;
    private boolean maintainAr = false;
    private boolean isPercent;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isMaintainAr() {
        return maintainAr;
    }

    public void setMaintainAr(boolean maintainAr) {
        this.maintainAr = maintainAr;
    }

    public boolean isPercent() {
        return isPercent;
    }

    public void setPercent(boolean isPercent) {
        this.isPercent = isPercent;
    }

    @Override
    public String getUrlParameters() {
        StringBuilder builder = new StringBuilder(OP);
        builder.append(OPERATION).append("&w=").append(width).append("&h=").append(height).append("&maintainAspect=")
                .append(maintainAr);

        return builder.toString();
    }
}
