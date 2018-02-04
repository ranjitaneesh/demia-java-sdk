package com.demia.sdk.media.metadata;

public enum SortElement {

    NAME("name"), MODIFIED_DATETIME("modifiedDatetime"), CREATED_DATETIME("createdDatetime"), ID("assetId");

    private String sortElement;

    private SortElement(String name) {
        this.sortElement = name;
    }

    public String getSortElement() {
        return sortElement;
    }

}
