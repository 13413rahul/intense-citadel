package com.rk.mtms.serviceImpl;

import com.rk.mtms.repository.ImageRepository;
import com.rk.mtms.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Component
public class UploadServiceImpl implements UploadService {
    public static String uploadBaseDirectory = "C://Users//Rahul kumar//Desktop";
    public static String uploadDirectory = "../../assets/";

    @Autowired
    ImageRepository imageRepository ;

    @Override
    public ResponseEntity<Map<String,Boolean>> uploadHelper(Model model, MultipartFile[] files, String userId){
        StringBuilder fileUrl = new StringBuilder(uploadBaseDirectory);
        StringBuilder fileName = new StringBuilder(uploadDirectory);
        StringBuilder fileNames = new StringBuilder();

        Map<String,Boolean> resultMap = new HashMap<String,Boolean>();

        for (MultipartFile file : files) {
            Path fileNameAndPath = Paths.get(fileUrl.toString(), file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename()+" ");
            fileName.append(file.getOriginalFilename());
            imageRepository.insertIntoImageTable(fileName.toString(),userId);
            try {
                Files.write(fileNameAndPath, file.getBytes());
                resultMap.put("uploadStatusView",true);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("msg", "Successfully uploaded files "+fileNames.toString());
         return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
