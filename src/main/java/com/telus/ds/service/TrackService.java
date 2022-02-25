package com.telus.ds.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telus.ds.entity.Track;
import com.telus.ds.exception.BadInputParamException;
import com.telus.ds.exception.ResourceNotFoundException;
import com.telus.ds.repository.TrackRepository;

@Service
public class TrackService {
	
	@Autowired
	private TrackRepository trackRepository;

	public List<Track> getTracks() {
		return trackRepository.findAll();
	}
	
	public Track getTrack(String isrc) {
		Track trackFound = trackRepository.findByIsrc(isrc);
		
		if (trackFound == null) {
			throw new ResourceNotFoundException("Track not found in DS repository with ISRC=" + isrc);
		}
		
		return trackFound;
	}
	
	public Track create(Track track) {
		
		if(track.getId() != null) {
			throw new BadInputParamException("The ID must not be provided for creation. " + track.getId());
		}
		
		track.setCreationDate(LocalDateTime.now());
		
		return trackRepository.save(track);
	}
	
	public Track update(Track track) {
		
		if(track.getId() != null) {
			
			Optional<Track> trackFound = trackRepository.findById(track.getId());
			
			if(trackFound.isPresent()) {
				track = updateEntity(track, trackFound.get());//Please try to update removing this line and compare the results
				track = trackRepository.save(track);
				
			}else {
				throw new BadInputParamException("The ID provided doesn´t exists. ID = " + track.getId());
			}
		}else {
			throw new BadInputParamException("Missing ID.");
		}
		
		return track;
	}
	
	private Track updateEntity(Track track, Track trackFound) {
		if(track.getIsrc() != null) {
			throw new BadInputParamException("The ISRC must not be provided on update.");
		}
		
		if(track.getDuration() != null) {
			trackFound.setDuration(track.getDuration());
		}
		
		if(track.getCreationDate() != null) {
			throw new BadInputParamException("The CREATION DATE must not be provided on update.");
		}
		
		if(track.getModificationDate() != null) {
			throw new BadInputParamException("The MODIFICATION DATE must not be provided on update.");
		}
		
		track.setModificationDate(LocalDateTime.now());
		
		return trackFound;
	}
}
