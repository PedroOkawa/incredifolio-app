package com.daltonicchameleon.portfolio.model;

import java.util.Date;
import java.util.List;

/**
 * portfolio-app
 * Created in 4/4/17 by the following authors:
 * Pedro Okawa
 */
public class Portfolio {

    private String id;
    private String name;
    private String description;
    private String image;
    private Date createdAt;
    private List<Screenshot> screenshots;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Screenshot> getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(List<Screenshot> screenshots) {
        this.screenshots = screenshots;
    }
}
