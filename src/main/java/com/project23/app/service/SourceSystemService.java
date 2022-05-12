package com.project23.app.service;

import com.project23.app.Entity.SourceSystem;
import com.project23.app.repository.SourceSystemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SourceSystemService {

    private final SourceSystemRepository sourceSystemRepository;

    public SourceSystem getSourceSystem(long id){
        return sourceSystemRepository.getById(id);
    }

}
