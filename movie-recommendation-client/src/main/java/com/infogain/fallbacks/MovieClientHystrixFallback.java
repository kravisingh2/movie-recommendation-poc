package com.infogain.fallbacks;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.infogain.clients.MovieClient;
import com.infogain.dto.MovieDTO;
import com.infogain.request.CreateMovieRequest;

@Component
public class MovieClientHystrixFallback implements MovieClient{

	@Override
	public List<MovieDTO> getAllMovies() {
		List<MovieDTO> movies = new ArrayList<>();
        movies.add(new MovieDTO(10L, "The Lord of the Rings: The Fellowship of the Ring"));
        movies.add(new MovieDTO(14L, "One Flew Over the Cuckoo's Nest"));
        movies.add(new MovieDTO(4L, "12 Angry Men"));
        movies.add(new MovieDTO(9L, "Fight Club"));
        movies.add(new MovieDTO(6L, "Pulp Fiction"));
        return movies;
	}

	@Override
	public MovieDTO getMovieById(Long movieId) {
		return new MovieDTO(movieId, "Dhoom-3");
	}

	@Override
	public List<MovieDTO> getMoviesById(List<Long> ids) {
		List<MovieDTO> movies = new ArrayList<>();
		ids.forEach(id -> {
			movies.add(new MovieDTO(id, "dummy - " + id.toString()));
		});
        return movies;
	}

	@Override
	public void insertDummyData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MovieDTO createMovie(CreateMovieRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
