package com.telus.ds.entity.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AlbumDTO {
	
	private String name;
	private Integer year;
	private LocalDateTime creationDate;
	private LocalDateTime modificationDate;
	
}
