package com.nucleus.model.engine;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "links_search_Engine_Project")
public class Link {

    @Id
    @Column(name = "url_id")
    private String id;

    @Column(name = "url_title")
    private String urlTitle;

    @Column(name = "url")
    private String url;

    @ElementCollection
    @CollectionTable(name = "keyword_table_search_Engine_Project", joinColumns = @JoinColumn(name = "url"))
    @Column(name = "url_keywords")
    private List<String> urlKeywords;

    @Column(name = "url_description")
    private String urlDescription;

    public Link() {
    }

    public Link(String id, String urlTitle, String url, List<String> urlKeywords, String urlDescription) {
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
        return "Link{" +
                "id=" + id +
                ", urlTitle='" + urlTitle + '\'' +
                ", url='" + url + '\'' +
                ", urlKeywords='" + urlKeywords + '\'' +
                ", urlDescription='" + urlDescription + '\'' +
                '}';
    }
}
