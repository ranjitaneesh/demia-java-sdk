package com.demia.sdk.media.metadata;

import java.util.Date;
import java.util.List;

public class MediaEntity {

    private String assetId;
    private String name;
    private String description;
    private String path;
    private Date createdOn;
    private Date modifiedOn;
    private List<MediaRevision> revisions;
    private String tags;

    private boolean expired;
    private boolean inactive;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public List<MediaRevision> getRevisions() {
        return revisions;
    }

    public void setRevisions(List<MediaRevision> revisions) {
        this.revisions = revisions;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public boolean isInactive() {
        return inactive;
    }

    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }

    @Override
    public String toString() {
        return "MediaEntity [assetId=" + assetId + ", name=" + name + ", description=" + description + ", path=" + path
                + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn + ", revisions=" + revisions + ", tags="
                + tags + ", expired=" + expired + ", inactive=" + inactive + "]";
    }

}
