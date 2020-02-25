package com.ipiecoles.java.audio.Controller;


import java.awt.print.Pageable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ipiecoles.java.audio.Exception.ConflictException;
import com.ipiecoles.java.audio.Model.Album;
import com.ipiecoles.java.audio.Model.Artiste;
import com.ipiecoles.java.audio.repository.AlbumRepository;
import com.ipiecoles.java.audio.repository.ArtisteRepository;



@RestController
@RequestMapping(value = "/artists")
public class ArtisteController {

	@Autowired
	private ArtisteRepository artisteRepository;
	@Autowired
	private AlbumRepository albumRepository;
	
	
	//afficher un artiste
	@RequestMapping (method = RequestMethod.GET, value = "/{id}")
	public Artiste getArtisteId(
			@PathVariable("id") Integer id
			) { 
		Optional<Artiste> a= artisteRepository.findById(id);
		if(a.isPresent()) {
			return a.get();
			}
			 //gestion d'erreur 404
			 throw new EntityNotFoundException("L'artiste d'identifiant : " + 
					  id + " n'a pas été trouvé.");
	}
	
	
	// rechercher un artiste par nom
	@RequestMapping (method = RequestMethod.GET, value = "", params ="name")
	public List <Artiste>  searchArtiste(@RequestParam("name") String name) {
		return artisteRepository.findByNameContaining(name);
	}
	
	// Liste des artistes
	 @RequestMapping(value = "" , method = RequestMethod.GET)
	 public Page<Artiste> listArtiste(@RequestParam(value ="page", defaultValue = "0") Integer page, @RequestParam(value ="size", defaultValue = "10") Integer size, @RequestParam(value = "sortDirection", defaultValue = "ASC") Sort.Direction sortDirection, @RequestParam(value = "sortProperty", defaultValue = "name") String sortProperty) {
			//recuperer le nb employes et envoyer au client
		 	if(size <= 0 || size > 50) {
		 		throw new IllegalArgumentException("la page" + page + "doit être compris entre 0 et 50");
		 	}
		 	
		 
		 	if(Arrays.stream(Artiste.class.getDeclaredFields()).map(Field::getName).filter(s-> s.equals(sortProperty)).count() !=1) {
		 	throw new IllegalArgumentException("la propriété " + sortProperty + " n'existe pas")	;
		 	}
		 	
		 	PageRequest pageRequest = PageRequest.of(page, size, sortDirection, sortProperty);
			return artisteRepository.findAll(pageRequest);
		} 
	 
	 
	 //création d'un artiste
	 @RequestMapping(value = "" , method = RequestMethod.POST, consumes ="application/json", produces ="application/json")
	 public Artiste createArtiste (@RequestBody Artiste artist) throws ConflictException {
		 if(artisteRepository.findByName(artist.getName()).isPresent()) {
			 throw new ConflictException("L'artiste existe déjà");
				}
				 //gestion d'erreur 409
				 
				 return artisteRepository.save(artist);
				 
	 }
		 
	// modification d'un artiste
		@RequestMapping (method = RequestMethod.PUT, value="/{id}")
		public Artiste modifArtiste (@PathVariable ("id") Long idArtiste, @RequestBody Artiste artist) {
			return artisteRepository.save(artist);
				}
		
	//Suppresion artiste
		 @RequestMapping (method = RequestMethod.DELETE , value="/{id}")
		 @ResponseStatus (HttpStatus.NO_CONTENT)
		
		 public void deleteArtiste (@PathVariable ("id") Integer id ) {
			 //Artiste artiste= this.findById(idArtiste);
			 Set<Album> albums = albumRepository.findByArtistId(id);
			 
			for(Album album : albums){
				 Integer idAlbum = album.getId();
				albumRepository.deleteById(idAlbum);
			}
			 
			 
			  artisteRepository.deleteById(id);
		 };

				 
				 
		 
		 
	 
}