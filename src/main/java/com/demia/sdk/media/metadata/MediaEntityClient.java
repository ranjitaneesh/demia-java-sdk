package com.demia.sdk.media.metadata;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.demia.sdk.core.AbstractRestClient;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MediaEntityClient extends AbstractRestClient {
    
    Logger logger = LogManager.getLogManager().getLogger(MediaEntityClient.class.getName());
    
    ObjectMapper mapper = new ObjectMapper();
    
    public MediaEntity createMediaEntity(MediaEntity entity, String localFilePath) {
        String url = getUrl("/assets");
        MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("file", getFileResource(localFilePath));

        try{
            bodyMap.add("asset", mapper.writeValueAsString(entity));
        }catch (Exception e) {
            logger.log(Level.SEVERE, "Unable to serialize provided object", e);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);

        ResponseEntity<MediaEntity> response = restTemplate.postForEntity(url, requestEntity, MediaEntity.class);
        
        if(response.getStatusCode()==HttpStatus.CREATED) {
            return response.getBody();
        } else{
            throw new RuntimeException("Unable to create entity");
        }
    }
    
    private FileSystemResource getFileResource(String file) {
        FileSystemResource resource = new FileSystemResource(file);
        return resource;
    }

    public MediaEntities getMediaEntity(String assetId){
        return null;
    }
    
    public List<MediaEntities> getAllMediaEntities(){
        String url = getUrl("/assets");
        return null;
    }
    
    private String getUrl(String uriBase){
        String hostUrl = System.getProperty("host.asset");
        hostUrl = validateFormat(hostUrl);
        hostUrl = new StringBuffer(hostUrl).append(uriBase).toString();
        return hostUrl;
        
    }
    
    
    


}
