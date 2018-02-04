package com.demia.sdk.media.metadata;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.web.client.HttpClientErrorException;

import com.demia.sdk.core.AuthenticationClient;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MediaEntityClientTest {
    
    private AuthenticationClient authenticationClient = new AuthenticationClient();
    private MediaEntityClient mediaEntityClient = new MediaEntityClient();
    
    private String assetId;
    
    @Before
    public void setup() {
        mediaEntityClient = new MediaEntityClient();
    }
    

    //@Test
    public void test1CreateMediaEntity() {
        authenticationClient.authenticate();
        
        MediaEntity entity = new MediaEntity();
        entity.setName("Test 123");
        entity.setPath("l1/l2");
        entity = mediaEntityClient.createMediaEntity(entity, "C:\\Users\\Ranjit\\Pictures\\sample.jpg");
        System.out.println(entity);
        
    }

    @Test
    public void test2GetMediaEntity() {
        //Create
        authenticationClient.authenticate();
        MediaEntity entity = new MediaEntity();
        entity.setName("Test 123");
        entity.setPath("l1/l2");
        entity = mediaEntityClient.createMediaEntity(entity, "C:\\Users\\Ranjit\\Pictures\\sample.jpg");
        
        MediaEntity entityRetrieved = mediaEntityClient.getMediaEntity(entity.getAssetId());
        assertNotNull(entityRetrieved);
        
    }

    @Test
    public void test3GetAllMediaEntities() {
        authenticationClient.authenticate();
        MediaEntities entities = mediaEntityClient.getMediaEntities(0, 10, SortElement.CREATED_DATETIME, Order.DESCENDING);
        assertNotNull(entities);
        System.out.println(entities);
        
    }
    
    @Test(expected=HttpClientErrorException.class)
    public void test4GetMediaEntity() {
        //Create
        authenticationClient.authenticate();
        
        MediaEntity entity = new MediaEntity();
        entity.setName("Test 123");
        entity.setPath("l1/l2");
        entity = mediaEntityClient.createMediaEntity(entity, "C:\\Users\\Ranjit\\Pictures\\sample.jpg");
        assertNotNull(entity);
        //Get
        MediaEntity entityRetrieved = mediaEntityClient.getMediaEntity(entity.getAssetId());
        assertNotNull(entityRetrieved);
        
        //Delete
        mediaEntityClient.deleteMediaEntity(entity.getAssetId());
        entityRetrieved = mediaEntityClient.getMediaEntity(entity.getAssetId());
        
    }

}
