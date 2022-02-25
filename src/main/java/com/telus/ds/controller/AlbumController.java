package com.telus.ds.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telus.ds.entity.Album;
import com.telus.ds.service.AlbumService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/albums")
public class AlbumController {
	
	@Autowired
	AlbumService albumService;
	
	@GetMapping("/")
	public List<Album> getAlbums() {
		log.info("Getting albums");
		
		return albumService.getAlbums()
							.stream()
							.collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public Album getAlbum(@PathVariable("id") Long id) {
		log.info("Getting album");
		Album albumFound = albumService.getAlbum(id);
		
		return albumFound;
	}
	
	@PostMapping("/")
	public Album create(@RequestBody Album album) {
		log.info("Creating a album");
		
		return albumService.create(album);
	}
	
}
