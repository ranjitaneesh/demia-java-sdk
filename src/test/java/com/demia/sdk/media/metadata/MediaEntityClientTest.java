package com.demia.sdk.media.metadata;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;

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

    //@Test
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

    //@Test
    public void test3GetAllMediaEntities() {
        authenticationClient.authenticate();
        MediaEntities entities = mediaEntityClient.getMediaEntities(0, 10, SortElement.CREATED_DATETIME, Order.DESCENDING);
        assertNotNull(entities);
        System.out.println(entities);
        
    }
    
    //@Test(expected=HttpClientErrorException.class)
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
    
    
    @Test
    public void test5GetMediaEntity() throws Exception {
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
        
        //Rendition retrieval
        String tempLocation = System.getProperty("java.io.tmpdir");
        
        String orig_rend_id = entity.getRevisions().get(0).getRenditions().get(0).getRenditionId();
        ResizeCommand command = new ResizeCommand();
        command.setHeight(100);
        command.setWidth(100);
        command.setMaintainAr(true);
        
        File f = new File(tempLocation + "\\"+orig_rend_id+".png");
        FileOutputStream fos = new FileOutputStream(f);
        mediaEntityClient.performAnOperationAndGetImage(orig_rend_id, command, fos);
        fos.close();
        assertTrue(f.length()>0);
        f.delete();
        
        //Delete
        mediaEntityClient.deleteMediaEntity(entity.getAssetId());
        entityRetrieved = mediaEntityClient.getMediaEntity(entity.getAssetId());
        
    }

}
