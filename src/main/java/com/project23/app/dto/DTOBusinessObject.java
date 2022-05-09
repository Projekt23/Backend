package com.project23.app.dto;

import com.project23.app.pojo.SourceSystem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOBusinessObject {

    private Long id;

    private String name;

    private String description;

    private Long sourceSystemId;
}
