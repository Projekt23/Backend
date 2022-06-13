package com.project23.app.service;

import com.project23.app.Entity.BusinessObject;
import com.project23.app.helper.CSVHelper;
import com.project23.app.repository.BusinessObjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CSVService {

    private final BusinessObjectRepository businessObjectRepository;

    public void save(MultipartFile file){
        try {
            List<BusinessObject> businessObjects = CSVHelper.csvToBusinessObjects(file.getInputStream());
            businessObjectRepository.saveAll(businessObjects);
        } catch (IOException e) {
            throw  new ResponseStatusException(HttpStatus.CONFLICT, "Fail to store csv business object data" + e.getMessage());
        }
    }

}
