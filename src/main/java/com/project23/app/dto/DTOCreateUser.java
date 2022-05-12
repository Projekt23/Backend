package com.project23.app.dto;

import com.project23.app.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DTOCreateUser {
    private String username;
    private String eMail;
    private String password;
    private String name;

    public User convertToEntity(){
        User u = new User();
        u.setUsername(this.getUsername());
        u.setEmail(this.getEMail());
        u.setPassword(this.getPassword());
        u.setName(this.getName());
        return u;
    }

    public User convertToEntityWithId(long id){
        User u = new User();
        u.setId(id);
        u.setUsername(this.getUsername());
        u.setEmail(this.getEMail());
        u.setPassword(this.getPassword());
        u.setName(this.getName());
        return u;
    }
}
