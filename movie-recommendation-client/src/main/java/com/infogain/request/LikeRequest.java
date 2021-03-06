package com.infogain.request;

import javax.validation.constraints.NotNull;

public class LikeRequest {

	@NotNull(message = "movieId can not be null or empty")
	private Integer movieId;
	@NotNull(message = "userId can not be null or empty")
	private Integer userId;
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
}
