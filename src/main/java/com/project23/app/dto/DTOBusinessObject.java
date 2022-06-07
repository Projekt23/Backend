package com.project23.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO-Klasse zum Datentransfer zwischen Spring-Web-Applikation und Web-Client entsprechend der API-Spezifikation.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOBusinessObject {

    private Long id;

    private String name;

    private String description;

    private List<DTOSynonym> synonyms;

    private List<DTOLabel> labels;

    private List<DTOContext> contextList;

}


