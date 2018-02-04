package com.demia.sdk.media.metadata;

import java.util.List;

public class MediaEntities {

    private List<MediaEntitySummary> assets;
    private long totalEntitiesAvailable;
    private int currentPage;
    private int totalPages;

    public List<MediaEntitySummary> getAssets() {
        return assets;
    }

    public void setAssets(List<MediaEntitySummary> assets) {
        this.assets = assets;
    }

    public long getTotalEntitiesAvailable() {
        return totalEntitiesAvailable;
    }

    public void setTotalEntitiesAvailable(long totalEntitiesAvailable) {
        this.totalEntitiesAvailable = totalEntitiesAvailable;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

}
