package com.demia.sdk.core;

import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractRestClient {

    protected RestTemplate restTemplate;
    

    public AbstractRestClient() {

        restTemplate = new RestTemplate();

        //remove ResourceHttpMessageConverter that has streaming set to false with the default which has implicitly set to true
        restTemplate.getMessageConverters().removeIf(f->f.getClass().getName().equals(ResourceHttpMessageConverter.class.getName()));
        restTemplate.getMessageConverters().add(new ResourceHttpMessageConverter());
        
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setBufferRequestBody(false);//Not to buffer request body, will help when sending large content 
        requestFactory.setOutputStreaming(false);
        
        restTemplate.setRequestFactory(requestFactory);
        
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
        
        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                if(response.getRawStatusCode()>=400)
                    return true;
                
                return false;
            }
            
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                IOUtils.copy(response.getBody(), System.out);
                
            }
        });
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
