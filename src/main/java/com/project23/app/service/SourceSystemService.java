package com.project23.app.service;

import com.project23.app.pojo.BusinessObject;
import com.project23.app.pojo.SourceSystem;
import com.project23.app.repository.SourceSystemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SourceSystemService {

    private final SourceSystemRepository sourceSystemRepository;

    public SourceSystem getSourceSystem(long id){
        return sourceSystemRepository.getById(id);
    }

}
