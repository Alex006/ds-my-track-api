package com.telus.ds.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@Table(name = "ALBUMS")
public class Album {
	
	public Album() {}
	
	@Id
	@Column(name="ALBUM_ID", updatable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="NAME", updatable=false)
	@NotNull(message = "NAME is required")
	private String name;
	
	@Column(name="YEAR", updatable=true)
	@NotNull(message = "YEAR is required")
	private Integer year;
	
	@Column(name = "CREATION_DATE", updatable = true)
	@NotNull(message = "Creation is required")
	private LocalDateTime creationDate;
	
	@Column(name = "MODIFICATION_DATE", updatable = false)
	@NotNull(message = "Modification date is required")
	private LocalDateTime modificationDate;
	
	//--Relationships---
	
	@Transient
	@JsonIgnore
	@OneToMany(mappedBy = "albumObj")
	private List<Track> tracks;

}
