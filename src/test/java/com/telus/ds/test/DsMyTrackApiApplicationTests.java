package com.telus.ds.test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.telus.ds.DsMyTrackApiApplication;
import com.telus.ds.controller.TrackController;
import com.telus.ds.entity.Album;
import com.telus.ds.entity.Track;
import com.telus.ds.entity.dto.TrackDTO;
import com.telus.ds.repository.AlbumRepository;
import com.telus.ds.repository.TrackRepository;
import com.telus.ds.service.TrackService;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DsMyTrackApiApplication.class)
class DsMyTrackApiApplicationTests {


	@Autowired
	private TrackRepository trackRepository;

	@Autowired
	private TrackService trackService;

	@Autowired
	private TrackController trackController;

	@Autowired
	private AlbumRepository albumRepository;
	
	@Test
	void getTrackController() {
		// with
		Optional<Album> albumFound = albumRepository.findById(1L); //getting instance from data.sql
		Album album = albumFound.get();
		
		TrackService trackServiceMock = mock(TrackService.class);
		Track track = new Track(1L, "USVT10300001", 232106, LocalDateTime.now(), LocalDateTime.now(), album);

		// when
		Mockito.when(trackServiceMock.getTrack("USVT10300001")).thenReturn(track);
		TrackDTO resultTrackDTO = trackController.getTrack("USVT10300001");

		// then
		MatcherAssert.assertThat(resultTrackDTO.getIsrc(), equalTo("USVT10300001"));
	}

	@Test
	void getTrackService() {
		// with
		Album album = new Album(1L, "AlbumName", 55, LocalDateTime.now(), LocalDateTime.now(), new ArrayList<Track>());
		
		TrackRepository trackRepositoryMock = mock(TrackRepository.class);
		
		Track track = new Track(1L, "USVT10300001", 232106, LocalDateTime.now(), LocalDateTime.now(), album);

		// when
		Mockito.when(trackRepositoryMock.findByIsrc("USVT10300001")).thenReturn(track);
		Track resultTrack = trackService.getTrack("USVT10300001");

		// then
		MatcherAssert.assertThat(resultTrack.getIsrc(), equalTo("USVT10300001"));
	}

	@Test
	void saveTrackRepository() {
		// with
		Optional<Album> albumFound = albumRepository.findById(1L); //getting instance from data.sql
		Album album = albumFound.get();
		
		Track track = new Track(1L, "USVT10300001", 232106, LocalDateTime.now(), LocalDateTime.now(), album);
		track.setCreationDate(LocalDateTime.now());

		// when
		trackRepository.save(track);
		Track resultTtrack = trackRepository.findByIsrc("USVT10300001");

		// then
		MatcherAssert.assertThat(resultTtrack.getDuration().longValue(), equalTo(232106L));

	}

	@Test
	void findByIsrcTrackRepository() {
		// with
		// insert data from data.sql

		// when
		Track resultTrack = trackRepository.findByIsrc("USVT10300001");

		// then
		MatcherAssert.assertThat(resultTrack.getIsrc(), equalTo("USVT10300001"));

	}
	
	@Test
	void getCustomizedTracksRepository() {
		// with
		// insert data from data.sql
		int top = 3;
		
		//when
		List<Track> top5tracks = (List<Track>) trackRepository.getTopTracks(top);//getting instance from data.sql
		
		// then
		MatcherAssert.assertThat(top5tracks.size(), equalTo(top));

	}
	
	@Test
	void getNegativeTestForTracksRepository() {
		// with
		// insert data from data.sql
		int top = 100;
		
		//when
		List<Track> top5tracks = (List<Track>) trackRepository.getTopTracks(top);//getting instance from data.sql
		
		// then
		MatcherAssert.assertThat(top5tracks.size(), not(equalTo(top)));//inserted data via data.sql is less than this value

	}
	
}
