package com.telus.ds.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telus.ds.entity.Album;
import com.telus.ds.exception.BadInputParamException;
import com.telus.ds.repository.AlbumRepository;

@Service
public class AlbumService {
	
	@Autowired
	private AlbumRepository albumRepository;
	
	public List<Album> getAlbums() {
		return albumRepository.findAll();
	}
	
	public Album getAlbum(Long id) {
		Optional<Album> albumFound = albumRepository.findById(id);
		
		if(albumFound.isPresent()) {
			return albumFound.get();
		}else {
			throw new BadInputParamException("The entity doesn't exists. Id = " + id);
		}
	}
	
	public Album create(Album album) {
		
		if(album.getId() != null) {
			throw new BadInputParamException("The ID must not be provided for creation. " + album.getId());
		}
		
		album.setCreationDate(LocalDateTime.now());
		
		return albumRepository.save(album);
	}
	
	public Album update(Album album) {
		
		if(album.getId() != null) {
			
			Optional<Album> albumFound = albumRepository.findById(album.getId());
			
			if(albumFound.isPresent()) {
				album = updateEntity(album, albumFound.get());
				album = albumRepository.save(album);
				
			}else {
				throw new BadInputParamException("The ID provided doesn´t exists. ID = " + album.getId());
			}
		}else {
			throw new BadInputParamException("Missing ID.");
		}
		
		return album;
	}
	
	private Album updateEntity(Album album, Album albumFound) {
		if(album.getName() != null) {
			throw new BadInputParamException("The NAME must not be provided on update. ");
		}
		
		if(album.getYear() != null) {
			albumFound.setYear(album.getYear());
		}
		
		if(album.getCreationDate() != null) {
			throw new BadInputParamException("The CREATION DATE must not be provided on update.");
		}
		
		if(album.getModificationDate() != null) {
			throw new BadInputParamException("The MODIFICATION DATE must not be provided on update.");
		}
		
		album.setModificationDate(LocalDateTime.now());
		
		return albumFound;
	}
	
}
