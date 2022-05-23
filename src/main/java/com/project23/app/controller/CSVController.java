package com.project23.app.controller;

import com.project23.app.helper.CSVHelper;
import com.project23.app.service.CSVService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/csv")
@Log
public class CSVController {

    private final CSVService csvService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCSVFile(@RequestParam("file")MultipartFile file){
        String responseMessage = "";
        if(CSVHelper.hasCSVFormat(file)){
            try {
                csvService.save(file);
                responseMessage= "Uploaded file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
            }catch (Exception e){
                responseMessage = "Couldn't upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(responseMessage);
            }
        }
        responseMessage = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
    }
}
