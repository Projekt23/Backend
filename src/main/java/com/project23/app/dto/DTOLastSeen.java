package com.project23.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOLastSeen {

    private Long boId;
    private String boName;
    private Date timestamp;

}
