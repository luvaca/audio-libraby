package com.ipiecoles.java.audio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ipiecoles.java.audio.repository.AlbumRepository;
import com.ipiecoles.java.audio.repository.ArtisteRepository;

@Component
public class MyRunner implements CommandLineRunner {

	@Autowired
	private AlbumRepository albumRepository;
	
	@Autowired
	private ArtisteRepository artisteRepository;

	@Override
	public void run(String... strings) throws Exception {
		System.out.println("toto");	
		System.out.println(albumRepository.count());
}
	


} 
	