package com.example.getmesocialservice.model;

import com.example.getmesocialservice.validation.ValidCreatedByName;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

public class Comment {
    private String id;
    private String photoId;
    @Length(min = 5)
    private String message;
    //@ValidCreatedByName
    private String createdBy;
    private Date dateCreated;

    public Comment(String id, String photoId, @Length(min = 5) String message, String createdBy, Date dateCreated) {
        this.id = id;
        this.photoId = photoId;
        this.message = message;
        this.createdBy = createdBy;
        this.dateCreated = dateCreated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
