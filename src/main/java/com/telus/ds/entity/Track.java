package com.telus.ds.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.ws.soap.Addressing;

import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "TRACKS")
@AllArgsConstructor
public class Track {
	/*
	public Track() {}
	
	public Track(String isrc, Integer duration) {
		super();
		this.isrc = isrc;
		this.duration = duration;
	}*/

	@Id
	@Column(name = "TRACK_ID", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name = "ISRC", updatable = false)
	@Length(max = 12, min = 12)
	@NotNull(message = "ISRC is required")
	String isrc;
	
	@Column(name = "DURATION", updatable = true)
	@NotNull(message = "Duration is required")
	Integer duration;
	
	@Column(name = "CREATION_DATE", updatable = true)
	@NotNull(message = "Creation is required")
	LocalDateTime creation; //LocalDate, LocalDateTime, LocalTime;
	
	
}
