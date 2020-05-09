package com.infogain.service;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import com.infogain.dto.MovieDTO;
import com.infogain.repository.MovieRepository;

public class MovieServiceMockTest {

	@Mock
	private MovieRepository movieRepository;
	@InjectMocks
	private MovieServiceImpl movieServiceImpl;

	//@Before
	public MovieDTO dummyMovieDto() {
		MovieDTO movieDTO = new MovieDTO();
		movieDTO.setName("One Man Army");
		return movieDTO;
	}

	@Test
	public void testCreateMovie() {
		MovieDTO movieDTO = dummyMovieDto();
		movieDTO = movieServiceImpl.createMovie(movieDTO);
		assertEquals(movieDTO.getName(), movieDTO.getName());
	}
	/**
	 * public void getAllMovies() { return this.movieRepository.findAll() .stream()
	 * .map(MovieEntity::toDTO) .collect(Collectors.toList()); }
	 * 
	 * public Collection<MovieDTO> getMoviesById(List<Long> ids) { List<MovieDTO>
	 * retVal = new ArrayList<>(); ids.stream() .forEach(id -> {
	 * Optional<MovieEntity> movieEntityOpt = this.movieRepository.findById(id);
	 * if(!movieEntityOpt.isPresent()) { throw new MovieNotFoundException(); }
	 * MovieDTO dto = new MovieDTO(this.movieRepository.getOne(id.longValue()));
	 * retVal.add(dto); }); return retVal; }
	 * 
	 * public MovieDTO findMovieById(Long movieId) { Optional<MovieEntity>
	 * movieEntityOpt = movieRepository.findById(movieId);
	 * if(!movieEntityOpt.isPresent()) { throw new MovieNotFoundException(); }
	 * return new MovieDTO(movieEntityOpt.get()); }
	 **/
}