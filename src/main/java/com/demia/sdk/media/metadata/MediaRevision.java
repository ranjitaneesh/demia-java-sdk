package com.demia.sdk.media.metadata;

import java.util.List;

public class MediaRevision {

    private String revisionId;
    private int revisionNo;
    private String revision;
    private String name;
    private String desc;
    private List<MediaRendition> renditions;
    private boolean isCurrentRevision;

    public String getRevisionId() {
        return revisionId;
    }

    public void setRevisionId(String revisionId) {
        this.revisionId = revisionId;
    }

    public int getRevisionNo() {
        return revisionNo;
    }

    public void setRevisionNo(int revisionNo) {
        this.revisionNo = revisionNo;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<MediaRendition> getRenditions() {
        return renditions;
    }

    public void setRenditions(List<MediaRendition> renditions) {
        this.renditions = renditions;
    }

    public boolean isCurrentRevision() {
        return isCurrentRevision;
    }

    public void setCurrentRevision(boolean isCurrentRevision) {
        this.isCurrentRevision = isCurrentRevision;
    }

    @Override
    public String toString() {
        return "MediaRevision [revisionId=" + revisionId + ", revisionNo=" + revisionNo + ", revision=" + revision
                + ", name=" + name + ", desc=" + desc + ", renditions=" + renditions + ", isCurrentRevision="
                + isCurrentRevision + "]";
    }
    
    

}
