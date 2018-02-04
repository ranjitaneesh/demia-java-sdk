package com.demia.sdk.core;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractRestClient {

    protected RestTemplate restTemplate;
    

    public AbstractRestClient() {

        restTemplate = new RestTemplate();

        ClientHttpRequestInterceptor interceptor = new ClientHttpRequestInterceptor() {

            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
                    throws IOException {
                if (!isAuthenticationRequest(request)) {
                    
                    HttpHeaders headers = request.getHeaders();
                    headers.set(HttpHeaders.AUTHORIZATION, HttpHeaders.AUTHORIZATION + ": " + TokenHolder.getToken());
                }

                return execution.execute(request, body);
            }

        };

        restTemplate.setInterceptors(Arrays.asList(new ClientHttpRequestInterceptor[] { interceptor }));
    }

    private boolean isAuthenticationRequest(HttpRequest request) {
        return request.getURI().getPath().contains("/authenticate");
    }

    protected String validateFormat(String host) {
        if (host == null || host.isEmpty())
            throw new RuntimeException("Unable to find host");

        if (host.startsWith("http://") || host.startsWith("https://")) {
            return host;
        } else
            throw new RuntimeException("Please check if the host contains 'http://' or 'https://' ");

    }

}
