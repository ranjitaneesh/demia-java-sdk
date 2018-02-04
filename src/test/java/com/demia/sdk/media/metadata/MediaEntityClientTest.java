package com.demia.sdk.media.metadata;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.demia.sdk.core.AuthenticationClient;

public class MediaEntityClientTest {
    
    private AuthenticationClient authenticationClient = new AuthenticationClient();
    private MediaEntityClient mediaEntityClient = new MediaEntityClient();
    
    @Before
    public void setup() {
        mediaEntityClient = new MediaEntityClient();
    }
    

    @Test
    public void testCreateMediaEntity() {
        authenticationClient.authenticate();
        
        MediaEntity entity = new MediaEntity();
        entity.setName("Test 123");
        entity.setPath("l1/l2");
        entity = mediaEntityClient.createMediaEntity(entity, "C:\\Users\\Ranjit\\Pictures\\sample.jpg");
        System.out.println(entity);
        
    }

    //@Test
    public void testGetMediaEntity() {
        fail("Not yet implemented");
    }

    //@Test
    public void testGetAllMediaEntities() {
        fail("Not yet implemented");
    }

}
