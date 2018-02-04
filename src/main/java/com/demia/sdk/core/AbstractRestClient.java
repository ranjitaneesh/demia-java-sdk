package com.demia.sdk.core;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractRestClient {
    
    protected RestTemplate restTemplate;
    
    public AbstractRestClient() {
        restTemplate = new RestTemplate(getMessageConverters());
    }

    private ClientHttpRequestFactory getMessageConverters() {
        // TODO Auto-generated method stub
        return null;
    }

}
