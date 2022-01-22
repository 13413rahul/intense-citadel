package com.rk.mtms.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Component
public interface UploadService {
    ResponseEntity<Map<String,Boolean>> uploadHelper(Model model, @RequestParam("files") MultipartFile[] files, @RequestParam("userId") String userId);
}
