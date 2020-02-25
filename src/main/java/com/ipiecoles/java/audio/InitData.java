package com.ipiecoles.java.audio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import com.ipiecoles.java.audio.Model.Artiste;
import com.ipiecoles.java.audio.repository.ArtisteRepository;

/**
 * Classe permettant d'insérer des données dans l'application.
 */
@Service
@Transactional
public class InitData implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private ArtisteRepository artisteRepository;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
 
    Iterable<Artiste> artistes = artisteRepository.findAll();
    for(Artiste a: artistes) {
    	System.out.println(a.getName());
    }
    }
}
