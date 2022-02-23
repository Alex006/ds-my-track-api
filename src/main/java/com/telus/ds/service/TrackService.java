package com.telus.ds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telus.ds.entity.Track;
import com.telus.ds.repository.TrackRepository;

@Service
public class TrackService {
	
	@Autowired
	TrackRepository trackRepository;
	
	public Track getTrack(String isrc) {
		return trackRepository.findByIsrc(isrc);
	}
}
