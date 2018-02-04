package com.demia.sdk.media.metadata;

public enum Order {
    
    ASCENDING("asc"),DESCENDING("desc");
    
    private String order;
    
    private Order(String order) {
        this.order = order;
    }
    
    public String getOrder() {
        return order;
    }
    

}
