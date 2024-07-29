package com.nucleus.dto.link;

import java.util.List;

public class LinkDTO {
    private String id;
    private String urlTitle;
    private String url;
    private List<String> urlKeywords;
    private String urlDescription;

    public LinkDTO() {
    }

    public LinkDTO(String id, String urlTitle, String url, List<String> urlKeywords, String urlDescription) {
        this.id = id;
        this.urlTitle = urlTitle;
        this.url = url;
        this.urlKeywords = urlKeywords;
        this.urlDescription = urlDescription;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlTitle() {
        return urlTitle;
    }

    public void setUrlTitle(String urlTitle) {
        this.urlTitle = urlTitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getUrlKeywords() {
        return urlKeywords;
    }

    public void setUrlKeywords(List<String> urlKeywords) {
        this.urlKeywords = urlKeywords;
    }

    public String getUrlDescription() {
        return urlDescription;
    }

    public void setUrlDescription(String urlDescription) {
        this.urlDescription = urlDescription;
    }

    @Override
    public String toString() {
        return "LinkDTO{" +
                "id=" + id +
                ", urlTitle='" + urlTitle + '\'' +
                ", url='" + url + '\'' +
                ", urlKeywords='" + urlKeywords + '\'' +
                ", urlDescription='" + urlDescription + '\'' +
                '}';
    }
}
