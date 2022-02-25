package com.telus.ds.entity.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TrackDTO {
	
	private String isrc;
	private Integer duration;
	private LocalDateTime creationDate;
	private LocalDateTime modificationDate;
	private AlbumDTO albumObj;
	private String albumName;
	
}
