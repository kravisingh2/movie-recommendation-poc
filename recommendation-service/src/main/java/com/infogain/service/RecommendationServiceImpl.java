package com.infogain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infogain.clients.MovieClient;
import com.infogain.dto.MovieDTO;
import com.infogain.entity.FollowingUserEntity;
import com.infogain.entity.RecommendEntity;
import com.infogain.repository.FollowingRepository;
import com.infogain.repository.RecommendRepository;
import com.infogain.request.FollowRequest;
import com.infogain.request.LikeRequest;

@Service
public class RecommendationServiceImpl implements IRecommendService{

	@Autowired
	private RecommendRepository recommendRepository;
	
	@Autowired
	private FollowingRepository followingRepository;
	
	@Autowired
	private MovieClient movieClient;
	
	@Override
	public RecommendEntity updateUserLikedMovie(LikeRequest request, boolean isLiked) {
		RecommendEntity recommendEntity = recommendRepository.findByUserIdAndMovieId(request.getUserId(), request.getMovieId());
		if(!Objects.nonNull(recommendEntity)) {
			recommendEntity = new RecommendEntity();
			recommendEntity.setMovieId(request.getMovieId());
			recommendEntity.setUserId(request.getUserId());
		} 
		if(isLiked) {
			recommendEntity.setIsLiked(true);
			recommendEntity.setIsDisliked(false);
		}
		else {
			recommendEntity.setIsLiked(false);
			recommendEntity.setIsDisliked(true);
		}
		return recommendRepository.save(recommendEntity);
	}

	@Override
	public FollowingUserEntity updateFollowRequest(FollowRequest request) {
		FollowingUserEntity followingEntity = followingRepository.findByUserIdAndFollowingUserId(request.getFollowerId(), request.getFollowingUserid());
		if(!Objects.nonNull(followingEntity)) {
			followingEntity = new FollowingUserEntity(request.getFollowerId(), request.getFollowingUserid());
			followingEntity = followingRepository.saveAndFlush(followingEntity);
		}
		return followingEntity;
	}
	
	@Override
	public boolean updateUnfollowRequest(FollowRequest request) {
		FollowingUserEntity followingEntity = followingRepository.findByUserIdAndFollowingUserId(request.getFollowerId(), request.getFollowingUserid());
		if(Objects.nonNull(followingEntity)) {
			followingRepository.delete(followingEntity);
			return true;
		}
		return false;
	}
	
	public List<MovieDTO> getRecommendationForUser(Long userId) {
		List<FollowingUserEntity> followingUserList = followingRepository.findByUserId(userId);
		List<Long> followingUserIdList = followingUserList.stream().map(FollowingUserEntity::getFollowingUserId).collect(Collectors.toList());
		List<RecommendRepository.MovieDTO> recommendMovieList = null;
		List<MovieDTO> movieList = new ArrayList<>();
		if(!followingUserIdList.isEmpty()) {
			List<RecommendEntity> recommendList = recommendRepository.findByUserIdInAndIsLiked(followingUserIdList, true);
			List<Long> movieIdList = recommendList.stream().map(RecommendEntity::getMovieId).collect(Collectors.toList());
			recommendMovieList = recommendRepository.getRecommendedTop5MovieIds(movieIdList);
		} else {
			recommendMovieList = recommendRepository.getTop5MovieIds();
		}
		recommendMovieList.forEach(recommendMovie -> {
			MovieDTO movieDTO = movieClient.getMovieById(recommendMovie.getMovieId());
			movieDTO.setLikes(recommendMovie.getLikes());
			movieList.add(movieDTO);
		});
		return movieList;
	}

	
}
