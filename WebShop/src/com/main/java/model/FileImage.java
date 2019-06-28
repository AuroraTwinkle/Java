package com.main.java.model;

import java.io.File;

public class FileImage {
    private File file;
    private String contentType;
    private String filename;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType(){
        return contentType;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilename(){
        return filename;
    }
}
