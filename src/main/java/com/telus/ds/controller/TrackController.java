package com.telus.ds.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telus.ds.entity.Track;
import com.telus.ds.entity.dto.TrackDTO;
import com.telus.ds.exception.BadInputParamException;
import com.telus.ds.service.TrackService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/tracks")
public class TrackController {
	
	@Autowired
	TrackService trackService;

	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/")
	public List<TrackDTO> getTracks() {
		log.info("Getting tracks");
		
		/*
		List<TrackDTO> mylist = new ArrayList<>();
		for(Track item : trackService.getTracks()) {
			TrackDTO newItem = convertToDTO(item);
			mylist.add(newItem);
			
		}
		return mylist;*/
		return trackService.getTracks()
							.stream()
							.map(track -> convertToDTO(track))
							.collect(Collectors.toList());
	}
	
	@GetMapping("/{isrc}")
	public TrackDTO getTrack(@PathVariable("isrc") String isrc) {
		log.info("Getting track");
		Track trackFound = trackService.getTrack(isrc);
		
		return convertToDTO(trackFound);
	}
	
	@PostMapping("/")
	public TrackDTO create(@RequestBody Track track) {
		log.info("Creating a track");
		
		return convertToDTO(trackService.create(track));
	}
	
	@PutMapping("/")
	public TrackDTO update(@RequestBody Track track) {
		log.info("Update a track");
		
		return convertToDTO(trackService.update(track));
	}
	
	//DTO
	private TrackDTO convertToDTO(Track track) {
		configModelMapper();
		return modelMapper.map(track, TrackDTO.class);
	}
	
	@SuppressWarnings("unused")
	private Track convertToEntity(TrackDTO trackDTO) {
		configModelMapper();
		return modelMapper.map(trackDTO, Track.class);
	}

	private void configModelMapper() {
		if(!modelMapper.getConfiguration().getMatchingStrategy().equals(MatchingStrategies.LOOSE)){
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		}
	}
}
