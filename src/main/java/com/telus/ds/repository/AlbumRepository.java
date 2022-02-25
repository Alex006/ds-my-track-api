package com.telus.ds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telus.ds.entity.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

}
