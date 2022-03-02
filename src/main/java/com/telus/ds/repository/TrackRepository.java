package com.telus.ds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.telus.ds.entity.Track;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {

	Track findByIsrc(String isrc);
	
	@Query(value = "SELECT * FROM tracks LIMIT :top", nativeQuery = true)
	List<Track> getTopTracks(@Param("top") int top);
	
}
