package com.project23.app.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Zusammengesetzter Primärschlüssel für die Entity Favourite.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavouriteKey implements Serializable {
    private Long userId;
    private Long businessObjectId;
}
