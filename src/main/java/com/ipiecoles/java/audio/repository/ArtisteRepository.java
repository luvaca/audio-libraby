package com.ipiecoles.java.audio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;



import com.ipiecoles.java.audio.Model.Artiste;


//@Repository
public interface ArtisteRepository extends JpaRepository<Artiste, Integer> {

	List<Artiste> findByNameContaining(String name);

	Optional <Artiste> findByName(String name);



	
	//Page<Artiste> findAll(Pageable pageable);


	

	Optional<Artiste> findById(Long id);
}