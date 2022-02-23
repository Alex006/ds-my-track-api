package com.telus.ds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telus.ds.entity.Track;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {

	Track findByIsrc(String isrc);
}
