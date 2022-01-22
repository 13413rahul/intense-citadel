package com.rk.mtms.controller;

import com.rk.mtms.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/upload")
public class UploadController {
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String UploadPage(Model model) {
        return "uploadview";
    }

    @Autowired
    UploadService uploadService;


    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public ResponseEntity<Map<String,Boolean>> upload(Model model, @RequestParam("files") MultipartFile[] files, @RequestParam("userId") String userId)throws IOException {
        return uploadService.uploadHelper(model, files, userId);

    }
}
