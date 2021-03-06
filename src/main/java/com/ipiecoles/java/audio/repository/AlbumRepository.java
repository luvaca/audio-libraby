package com.ipiecoles.java.audio.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ipiecoles.java.audio.Model.Album;
import com.ipiecoles.java.audio.Model.Artiste;


@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
	
	 Album findByTitle (String title);



	Set<Album> findByArtist(Integer id);
	
	@Query("from Album where artistId = :id")
	Set<Album> findByArtistId(@Param("id") int id);



	boolean existsByTitle(String title);
		
	
}