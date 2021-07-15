package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileListService {

    private List<File> files;

    public FileListService() {
        this.files = new ArrayList<>();
    }

    public void addFile(File file) {
        files.add(file);
    }


    public List<File> getFiles() {
        return new ArrayList<File>(this.files);
    }
}

