package com.project23.app.repository;

import com.project23.app.pojo.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteRepository extends JpaRepository<Favourite, Integer> {
}