package com.project23.app.dto;

import com.project23.app.pojo.BusinessObject;
import com.project23.app.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Data
@AllArgsConstructor
public class DTOFavourite {

    private Long id;

    private Long userId;

    private Long businessObjectId;
}
