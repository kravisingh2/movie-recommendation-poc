package com.infogain.controller;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infogain.dto.MovieDTO;
import com.infogain.service.IMovieService;


@RestController
@RequestMapping("/movie")
public class MovieController {
    
    @Autowired
    private IMovieService movieService;
    
    @PostMapping
    public MovieDTO createMovie(@RequestBody @Valid MovieDTO movieDto) {
    	return movieService.createMovie(movieDto);
    }
    
    @GetMapping
    public Collection<MovieDTO> getAllMovies(){
        return movieService.getAllMovies();
    }
    
    @GetMapping("/list")
    public Collection<MovieDTO> getListMovies(@RequestParam List<Long> ids){
        return movieService.getMoviesById(ids);
    }
    
    @GetMapping("/{movieId}")
    public MovieDTO getMovie(@PathVariable(value = "movieId") Long movieId){
        return movieService.findMovieById(movieId);
     }
    
    @GetMapping("/dummyData")
    public void insertDummyData(){
    	movieService.insertDummyData();
    }
    
}
