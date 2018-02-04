package com.demia.sdk.media.metadata;

public class MediaEntitySummary {

    private String assetId;
    private String name;
    private String description;

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MediaEntitySummary [assetId=" + assetId + ", name=" + name + ", description=" + description + "]";
    }

}
