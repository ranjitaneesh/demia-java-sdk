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
    private String HOST = System.getProperty("host");

    private String token;

    public String authenticate(String username, String password) {
        String hosturl = validateFormat(HOST);
        String authenticationUrl = new StringBuffer().append(hosturl).append("/authenticate?username=").append(username)
                .append("&password=").append(password).toString();

        try {

            ResponseEntity<String> response = restTemplate.getForEntity(new URI(authenticationUrl), String.class);
            String responseString = response.getBody();
            if (response.getStatusCode() == HttpStatus.OK)
                token = responseString;
            else
                throw new RuntimeException(
                        "Authenetication failed, received message: " + (responseString == null ? "" : responseString));

        } catch (RestClientException | URISyntaxException e) {
            logger.log(Level.SEVERE, "Error Authenticating", e);
        }

        return token;
    }

    private String validateFormat(String host) {
        if (host == null || host.isEmpty())
            throw new RuntimeException("Unable to find host");

        if (host.startsWith("http://") || host.startsWith("https://")) {
            return host;
        } else
            throw new RuntimeException("Please check if the host contains 'http://' or 'https://' ");

    }

    public String authenticate() {
        String userName = System.getProperty(USERNAME);
        String password = System.getProperty(PASSWORD);

        return authenticate(userName, password);
    }
}
