package com.infogain.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infogain.entity.RecommendEntity;

@Repository
public interface RecommendRepository extends JpaRepository<RecommendEntity, Serializable> {

	RecommendEntity findByUserIdAndMovieId(Integer userId, Integer movieId);

	List<RecommendEntity> findByUserIdInAndIsLiked(List<Integer> userIds, boolean isLiked);

	@Query(value = "SELECT TOP 5 movie_id as movieId, count(*) as likes FROM recommend_metadata WHERE movie_id IN ?1 "
			+ "GROUP BY movie_id HAVING is_movie_liked=true ORDER BY count(*) DESC", nativeQuery = true)
	List<MovieDTO> getRecommendedTop5MovieIds(List<Integer> movieIds);
	
	@Query(value = "SELECT TOP 5 movie_id as movieId, count(*) as likes FROM recommend_metadata "
			+ "GROUP BY movie_id HAVING is_movie_liked=true ORDER BY count(*) DESC", nativeQuery = true)
	List<MovieDTO> getTop5MovieIds();
	
	public static interface MovieDTO {

	     Integer getMovieId();

	     Long getLikes();

	  }

}