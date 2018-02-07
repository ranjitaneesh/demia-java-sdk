package com.demia.sdk.media.metadata;

public class RotateCommand implements Command {
    float angle;

    public static final String OPERATION = "rotate";

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    @Override
    public String getUrlParameters() {
        return OP + OPERATION + "&angle=" + angle;
    }

}
