package com.nucleus.dto.file;

import java.util.List;

public class PdfDTO {
    private String id;

    private String fileTitle;

    private byte[] file;

    private List<String> fileKeywords;

    private String fileDescription;

    public PdfDTO() {
    }

    public PdfDTO(String id, String fileTitle, byte[] file, List<String> fileKeywords, String fileDescription) {
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
                ", fileKeywords=" + fileKeywords +
                ", fileDescription='" + fileDescription + '\'' +
                '}';
    }
}

