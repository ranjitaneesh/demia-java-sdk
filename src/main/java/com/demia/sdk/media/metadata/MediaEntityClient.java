package com.demia.sdk.media.metadata;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.demia.sdk.core.AbstractRestClient;
import com.demia.sdk.media.MediaOperations;
import com.demia.sdk.media.Rendition;
import com.demia.sdk.media.Revison;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MediaEntityClient extends AbstractRestClient implements MediaOperations {

    Logger logger = LogManager.getLogManager().getLogger(MediaEntityClient.class.getName());

    private final String ASSETS_URI = "/assets";
    private final String ASSET_URI = "/asset";
    private final String RENDITION_URI = "/rendition";

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public MediaEntity createMediaEntity(MediaEntity entity, String localFilePath) {
        String url = getUrl(ASSETS_URI);
        MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("file", getFileResource(localFilePath));

        try {
            bodyMap.add("asset", mapper.writeValueAsString(entity));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unable to serialize provided object", e);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);

        ResponseEntity<MediaEntity> response = restTemplate.postForEntity(url, requestEntity, MediaEntity.class);

        if (response.getStatusCode() == HttpStatus.CREATED) {
            return response.getBody();
        } else {
            throw new RuntimeException("Unable to create entity: " + response.getBody().toString());
        }
    }

    private FileSystemResource getFileResource(String file) {
        FileSystemResource resource = new FileSystemResource(file);
        return resource;
    }

    @Override
    public MediaEntity getMediaEntity(String assetId) {
        String url = getUrl(ASSET_URI);
        url = url + "/" + assetId;

        ResponseEntity<MediaEntity> responseEntity = restTemplate.getForEntity(url, MediaEntity.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            return null;
        }
    }
    
    
    @Override
    public MediaEntities getMediaEntities(int start, int size, SortElement element, Order order) {
        String url = getUrl(ASSETS_URI);
        StringBuilder urlBuilder = new StringBuilder(url).append("?start=").append(Integer.toString(start))
                .append("&size=").append(Integer.toString((size == 0 ? 10 : size)));

        if (element != null)
            urlBuilder.append("&sortOn=").append(element.getSortElement());

        if (order != null)
            urlBuilder.append("&order=").append(order.getOrder());

        ResponseEntity<MediaEntities> entities = restTemplate.getForEntity(urlBuilder.toString(), MediaEntities.class);
        if (entities.getStatusCode() == HttpStatus.OK) {
            return entities.getBody();
        } else {
            throw new RuntimeException("Unable to retrieve entities: " + entities.getBody().toString());
        }

    }

    @Override
    public void deleteMediaEntity(String assetId) {
        String url = getUrl(ASSET_URI);
        url = url + "/" + assetId;
        restTemplate.delete(url);

    }

    @Override
    public void performSingleOperationAndGetImage(String renditionId, Command command, OutputStream out) throws Exception {
        String url = getUrl(RENDITION_URI);
        StringBuilder builder = new StringBuilder(url);
        builder.append("/").append(renditionId).append("/manipulate?").append(command.getUrlParameters());
        url = builder.toString();
        
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE + ", " + MediaType.IMAGE_PNG_VALUE);
        RequestEntity<String> req = new RequestEntity<>(headers, HttpMethod.GET, new URI(url));
        ResponseEntity<Resource> respStream = restTemplate.exchange(req, Resource.class);
        
        if (respStream.getStatusCode() == HttpStatus.OK) {
            InputStream in = respStream.getBody().getInputStream();
            IOUtils.copy(in, out);
        }

    }
    
    @Override
    public void performMultipleOperationAndGetImage(String renditionId, Command command, OutputStream out)
            throws Exception {
        // TODO Auto-generated method stub
        
    }

    private String getUrl(String uriBase) {
        String hostUrl = System.getProperty("host.asset");
        hostUrl = validateFormat(hostUrl);
        hostUrl = new StringBuffer(hostUrl).append(uriBase).toString();
        return hostUrl;

    }

    @Override
    public void updateMediaEntity(MediaEntity entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Revison addRevisionForMediaEntity(String assetId, Revison metadata, String localFilePath) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Revison addRenditionForMediaEntity(String assetId, Rendition metadata, String localFilePath) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void retrieveRendtionMedia(String renditionId, OutputStream out) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Revison> getRevisions(String assetId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Rendition> getRenditions(String revisionId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void makeRevisonCurrent(String revisionId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteRevision(String renditionId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteRendition(String renditionId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void multipleImageOperationCreateNewRendition(String renditionId, Command command, OutputStream out) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void multipleImageOperationReplaceRendition(String renditionId, Command command, OutputStream out) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String createFolder(String folderName, String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> getFolders() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> getFolders(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteFolder(String path) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<String> search(String text) {
        // TODO Auto-generated method stub
        return null;
    }

}
