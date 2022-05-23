package com.project23.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOCreateBusinessObject {

    private String name;

    private String description;

    private ArrayList<Long> synonymIds;

    private ArrayList<String> labels;

    private ArrayList<Long> contextIds;

}


