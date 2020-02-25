package com.ipiecoles.java.audio.Controller;

import java.util.Set;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ipiecoles.java.audio.Exception.ConflictException;
import com.ipiecoles.java.audio.Model.Album;
import com.ipiecoles.java.audio.repository.AlbumRepository;

@RestController
@RequestMapping(value = "/albums")
public class AlbumController {

	@Autowired
	private AlbumRepository albumRepository;
	
	
	//ajout d'album
	 @RequestMapping(method = RequestMethod.POST, consumes ="application/json", produces ="application/json", value= "")
	 //@ResponseStatus (HttpStatus.CREATED)
	 public Album createAlbum (@RequestBody Album album) throws ConflictException {
		 if(albumRepository.existsByTitle(album.getTitle())== true) {
			 throw new ConflictException("L'album " + album.getTitle() + " existe déjà");
				}
				 //gestion d'erreur 409
				 
				 return albumRepository.save(album);
				 
	 }
	 //suppression d'album
	 @RequestMapping (method = RequestMethod.DELETE , value="/{id}")
	 @ResponseStatus (HttpStatus.NO_CONTENT)
	 public void deleteAlbum (@PathVariable ("id") Integer id ) {
		 //Artiste artiste= this.findById(idArtiste);
		 Set<Album> albums = albumRepository.findByArtistId(id);
		 
		for(Album album : albums){
			 Integer idAlbum = album.getId();
			albumRepository.deleteById(idAlbum);
		}
		
	 };
	 
}