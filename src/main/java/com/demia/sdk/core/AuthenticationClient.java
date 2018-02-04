package com.demia.sdk.core;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

public class AuthenticationClient extends AbstractRestClient {

    Logger logger = LogManager.getLogManager().getLogger(AuthenticationClient.class.getName());

    private final String USERNAME = "username";
    private final String PASSWORD = "password";

    // TODO Till we don't have this lets lets populate externally the host
    // values
    // private final String HOST = "https://api.demia.com";
    private String HOST = System.getProperty("host.identity");

    

    public boolean authenticate(String username, String password) {
        boolean authenticated = false;
        String hosturl = validateFormat(HOST);
        String authenticationUrl = new StringBuffer().append(hosturl).append("/authenticate?username=").append(username)
                .append("&password=").append(password).toString();

        try {

            ResponseEntity<String> response = restTemplate.getForEntity(new URI(authenticationUrl), String.class);
            String responseString = response.getBody();
            if (response.getStatusCode() == HttpStatus.OK){
                TokenHolder.setToken(responseString);
                authenticated = true;
            } else
                throw new RuntimeException(
                        "Authenetication failed, received message: " + (responseString == null ? "" : responseString));

        } catch (RestClientException | URISyntaxException e) {
            logger.log(Level.SEVERE, "Error Authenticating", e);
        }

        return authenticated;
    }

    public boolean authenticate() {
        String userName = System.getProperty(USERNAME);
        String password = System.getProperty(PASSWORD);

        return authenticate(userName, password);
    }
    
}
