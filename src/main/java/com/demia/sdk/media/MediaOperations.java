package com.demia.sdk.media;

import java.io.OutputStream;
import java.util.List;

import com.demia.sdk.media.metadata.Command;
import com.demia.sdk.media.metadata.MediaEntities;
import com.demia.sdk.media.metadata.MediaEntity;
import com.demia.sdk.media.metadata.Order;
import com.demia.sdk.media.metadata.SortElement;

public interface MediaOperations {

    public MediaEntities getMediaEntities(int start, int size, SortElement element, Order order);

    public MediaEntity createMediaEntity(MediaEntity entity, String localFilePath);

    public void updateMediaEntity(MediaEntity entity);

    public void deleteMediaEntity(String assetId);

    public Revison addRevisionForMediaEntity(String assetId, Revison metadata, String localFilePath);

    public Revison addRenditionForMediaEntity(String assetId, Rendition metadata, String localFilePath);

    public void retrieveRendtionMedia(String renditionId, OutputStream out);

    public List<Revison> getRevisions(String assetId);

    public List<Rendition> getRenditions(String revisionId);

    public void makeRevisonCurrent(String revisionId);

    public void deleteRevision(String renditionId);

    public void deleteRendition(String renditionId);

    public MediaEntity getMediaEntity(String assetId);

    public void performSingleOperationAndGetImage(String renditionId, Command command, OutputStream out)
            throws Exception;

    public void performMultipleOperationAndGetImage(String renditionId, Command command, OutputStream out)
            throws Exception;

    public void multipleImageOperationCreateNewRendition(String renditionId, Command command, OutputStream out);

    public void multipleImageOperationReplaceRendition(String renditionId, Command command, OutputStream out);
    
    public String createFolder(String folderName, String path);
    
    public List<String> getFolders();
    
    public List<String> getFolders(String path);
    
    public void deleteFolder(String path);
    
    public List<String> search(String text);
    
    
    
    
    
    
    

}
