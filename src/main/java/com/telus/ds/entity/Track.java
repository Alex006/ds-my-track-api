package com.telus.ds.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@Table(name = "TRACKS")
public class Track {
	
	public Track() {}
	
	@Id
	@Column(name = "TRACK_ID", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "ISRC", updatable = false)
	@Length(max = 12, min = 12)
	@NotNull(message = "ISRC is required")
	private String isrc;
	
	@Column(name = "DURATION", updatable = true)
	@NotNull(message = "Duration is required")
	private Integer duration;
	
	@Column(name = "CREATION_DATE", updatable = false)
	@NotNull(message = "Creation date is required")
	private LocalDateTime creationDate; //LocalDate, LocalDateTime, LocalTime;
	
	@Column(name = "MODIFICATION_DATE", updatable = true)
	@NotNull(message = "Modification date is required")
	private LocalDateTime modificationDate;
	
	//--Relationships---
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ALBUM_ID", nullable = false)
	@NotNull(message = "ALBUM is required")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private Album albumObj;
}
