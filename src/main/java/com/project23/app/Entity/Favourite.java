package com.project23.app.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * JPA-Entity Favourite zum Datentransfer zwischen der Spring-Web-Applikation und der Datenbank.
 */

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