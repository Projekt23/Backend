package com.project23.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO-Klasse zum Datentransfer zwischen Spring-Web-Applikation und Web-Client entsprechend der API-Spezifikation.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOFavourite {

    private Long userId;

    private Long businessObjectId;

    private String businessObjectName;

    private String businessObjectDescription;

}
