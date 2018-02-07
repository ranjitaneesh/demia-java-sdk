package com.demia.sdk.media.metadata;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
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
import com.fasterxml.jackson.databind.ObjectMapper;

public class MediaEntityClient extends AbstractRestClient {

    Logger logger = LogManager.getLogManager().getLogger(MediaEntityClient.class.getName());

    private final String ASSETS_URI = "/assets";
    private final String ASSET_URI = "/asset";
    private final String RENDITION_URI = "/rendition";

    ObjectMapper mapper = new ObjectMapper();

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

    public void deleteMediaEntity(String assetId) {
        String url = getUrl(ASSET_URI);
        url = url + "/" + assetId;
        restTemplate.delete(url);

    }

    public void performAnOperationAndGetImage(String renditionId, Command command, OutputStream out) throws Exception {
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

    private String getUrl(String uriBase) {
        String hostUrl = System.getProperty("host.asset");
        hostUrl = validateFormat(hostUrl);
        hostUrl = new StringBuffer(hostUrl).append(uriBase).toString();
        return hostUrl;

    }

}
