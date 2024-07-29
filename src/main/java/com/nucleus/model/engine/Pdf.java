package com.nucleus.model.engine;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "files_search_Engine_Project")
public class Pdf {
    @Id
    @Column(name = "file_id")
    private String id;

    @Column(name = "file_title")
    private String fileTitle;

    @Lob
    @Column(name = "file_data", nullable = false)
    private byte[] file;

    @ElementCollection
    @CollectionTable(name = "file_keyword_table_search_Engine_Project", joinColumns = @JoinColumn(name = "file_id"))
    @Column(name = "file_keywords")
    private List<String> fileKeywords;

    @Column(name = "file_description")
    private String fileDescription;

    public Pdf() {
    }

    public Pdf(String id, String fileTitle, byte[] file, List<String> fileKeywords, String fileDescription) {
        this.id = id;
        this.fileTitle = fileTitle;
        this.file = file;
        this.fileKeywords = fileKeywords;
        this.fileDescription = fileDescription;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileTitle() {
        return fileTitle;
    }

    public void setFileTitle(String urlTitle) {
        this.fileTitle = urlTitle;
    }

    public byte[] getFileData() {
        return file;
    }

    public void setFileData(byte[] file) {
        this.file = file;
    }

    public List<String> getFileKeywords() {
        return fileKeywords;
    }

    public void setFileKeywords(List<String> fileKeywords) {
        this.fileKeywords = fileKeywords;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

    @Override
    public String toString() {
        return "Pdf{" +
                "id='" + id + '\'' +
                ", urlTitle='" + fileTitle + '\'' +
                ", file=" + Arrays.toString(file) +
                ", fileKeywords=" + fileKeywords +
                ", fileDescription='" + fileDescription + '\'' +
                '}';
    }
}
