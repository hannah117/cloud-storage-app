package com.udacity.jwdnd.course1.cloudstorage.service;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FileService {

    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public boolean isFileNameAvailable(String fileName) {
        return fileMapper.getFile(fileName) == null;
    }

    public int insert(File file) {
        return fileMapper.insert(new File(null, file.getFileName(), file.getContentType(), file.getFileSize(), file.getUserId(), file.getFileData()));
    }

    public File getFile(String filename) {
        return fileMapper.getFile(filename);
    }

    public List<File> getAllFiles() {
        return fileMapper.getAllFiles();
    }

}
