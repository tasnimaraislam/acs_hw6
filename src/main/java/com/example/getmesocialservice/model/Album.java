package com.example.getmesocialservice.model;

import com.example.getmesocialservice.validation.ValidCreatedByName;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class Album {
    @Id
    private String id;
    @Length(max = 10) @NotEmpty
    private String name;
    private String coverPhotoUrl;
    //@ValidCreatedByName
    private String createdBy;
    private Date dateCreated;

    public Album(String id, @Length(max = 10) @NotEmpty String name, String coverPhotoUrl, String createdBy, Date dateCreated) {
        this.id = id;
        this.name = name;
        this.coverPhotoUrl = coverPhotoUrl;
        this.createdBy = createdBy;
        this.dateCreated = dateCreated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCoverPhotoUrl() {
        return coverPhotoUrl;
    }

    public void setCoverPhotoUrl(String coverPhotoUrl) {
        this.coverPhotoUrl = coverPhotoUrl;
    }
}
