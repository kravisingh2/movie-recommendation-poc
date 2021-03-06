package com.infogain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infogain.clients.MovieClient;
import com.infogain.clients.UserClient;
import com.infogain.dto.MovieDTO;
import com.infogain.dto.UserDTO;
import com.infogain.entity.FollowingUserEntity;
import com.infogain.entity.RecommendEntity;
import com.infogain.request.FollowRequest;
import com.infogain.request.LikeRequest;
import com.infogain.service.IRecommendService;

@RestController
@RequestMapping("/recommend")
public class RecommendationController {
	
	@Autowired
	private MovieClient movieClient;
	
	@Autowired
	private UserClient userClient;
	
	@Autowired
	private IRecommendService recommendService;

	@GetMapping("/movies")
	public List<MovieDTO> getMovies() {
		return movieClient.getAllMovies();
	}
	
	@GetMapping("/users")
	public List<UserDTO> getUsers() {
		return userClient.getAllUsers();
	}
	
	@PostMapping("/liked")
	public RecommendEntity postLikedMovie(@RequestBody LikeRequest request) {
		return recommendService.updateUserLikedMovie(request, true);
	}
	
	@PostMapping("/disliked")
	public RecommendEntity postDislikedMovie(@RequestBody LikeRequest request) {
		return recommendService.updateUserLikedMovie(request, false);
	}
	
	@PostMapping("/follow")
	public FollowingUserEntity postFollowRequest(@RequestBody FollowRequest request) {
		return recommendService.updateFollowRequest(request);
	}
	
	@PostMapping("/unfollow")
	public String postUnfollowRequest(@RequestBody FollowRequest request) {
		boolean isUnfollowed = recommendService.updateUnfollowRequest(request);
		if(isUnfollowed) {
			return "Unfollowed successfully";
		} else {
			return "Unfollowed failed, Please try again";
		}
	}
	
	@GetMapping("/recommendation/{userId}")
	public List<MovieDTO> getRecommendedMovies(@PathVariable Long userId) {
		return recommendService.getRecommendationForUser(userId);
	}
	
}
