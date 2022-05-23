package com.project23.app.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(FavouriteKey.class)
@Table(name = "\"favourite\"", schema = "public")
public class Favourite {

    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Id
    @Column(name = "object_id", nullable = false)
    private Long businessObjectId;


}