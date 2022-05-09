package com.project23.app.helper;

import com.project23.app.dto.DTOBusinessObject;
import com.project23.app.dto.DTOUser;
import com.project23.app.pojo.BusinessObject;
import com.project23.app.pojo.User;
import com.project23.app.service.SourceSystemService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class Mapper {

    @Autowired
    private final SourceSystemService sourceSystemService;


    public DTOUser userToDtoUser(User user) {
        return new DTOUser(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEMail(),
                user.getPassword()
        );
    }

    public User dtoUserToUser(DTOUser dtoUser) {
        return new User(
                dtoUser.getId(),
                dtoUser.getFirstName(),
                dtoUser.getLastName(),
                dtoUser.getUsername(),
                dtoUser.getEMail(),
                dtoUser.getPassword()
        );
    }

    public BusinessObject dtoBoToBo(DTOBusinessObject dtoBo){
        BusinessObject bo = new BusinessObject();
        bo.setId(dtoBo.getId());
        bo.setName(dtoBo.getName());
        bo.setDescription(dtoBo.getDescription());
        bo.setSourceSystem(sourceSystemService.getSourceSystem(dtoBo.getSourceSystemId()));
        return bo;
    }

    public DTOBusinessObject boToDtoBo(BusinessObject bo) {
        return new DTOBusinessObject(
                bo.getId(),
                bo.getName(),
                bo.getDescription(),
                bo.getSourceSystem().getId()
        );
    }

}
