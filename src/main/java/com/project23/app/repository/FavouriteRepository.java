package com.project23.app.repository;

import com.project23.app.pojo.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {

    public List<Favourite> getFavouritesByUser_Id(Long userId);

}