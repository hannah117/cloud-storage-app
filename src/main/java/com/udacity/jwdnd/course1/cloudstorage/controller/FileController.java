package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.security.Principal;
import java.sql.Blob;
import java.sql.SQLException;

@Controller
@RequestMapping("/home")
public class FileController {


    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    public String getFile(FileForm fileForm, Model model) {
        model.addAttribute("files", this.fileService.getAllFiles());
        return "home";
    }

    @PostMapping("/home")
    public String postFile(@RequestParam("fileUpload") MultipartFile fileUpload, Authentication auth, Model model) throws IOException, SQLException {
        if(fileUpload.isEmpty()) {
            model.addAttribute("success",false);
            model.addAttribute("message","No file selected to upload!");
            return "result";
        }
        Blob blob = null;
        try {
            blob = new SerialBlob(fileUpload.getBytes() );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        User user = (User) auth.getDetails();


        File file = new File(null, fileUpload.getOriginalFilename(), fileUpload.getContentType(), String.valueOf(fileUpload.getSize()), user.getUserId(),  blob );


        fileService.insert(file);
        model.addAttribute("success",true);
        model.addAttribute("message","New File added successfully!");
        return "result";
    }


    @ModelAttribute("FileForm")
    public FileForm getFileForm() {
        return new FileForm();
    }

}
