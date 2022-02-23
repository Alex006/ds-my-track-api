package com.telus.ds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telus.ds.entity.Track;
import com.telus.ds.exception.ResourceNotFoundException;
import com.telus.ds.service.TrackService;

@RestController
@RequestMapping("/tracks")
public class TrackController {

	@Autowired
	TrackService trackService;
	
	@GetMapping("/{isrc}")
	public Track getTrack(@PathVariable("isrc") String isrc) {
		
		Track trackFound = trackService.getTrack(isrc);
		
		if(trackFound == null) {
			throw new ResourceNotFoundException("Track not found with ISRC=" + isrc);
		}
		
		return trackFound;
	}
}
