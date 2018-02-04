package com.demia.sdk.media.metadata;

public class MediaRendition {

    private String renditionId;
    private String renditionName;
    private String renditionType;
    private long size;

    public String getRenditionId() {
        return renditionId;
    }

    public void setRenditionId(String renditionId) {
        this.renditionId = renditionId;
    }

    public String getRenditionName() {
        return renditionName;
    }

    public void setRenditionName(String renditionName) {
        this.renditionName = renditionName;
    }

    public String getRenditionType() {
        return renditionType;
    }

    public void setRenditionType(String renditionType) {
        this.renditionType = renditionType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "MediaRendition [renditionId=" + renditionId + ", renditionName=" + renditionName + ", renditionType="
                + renditionType + ", size=" + size + "]";
    }

}
