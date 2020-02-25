package com.ipiecoles.java.audio.Model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "Album")
public class Album {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="id")
	private Integer id;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "title")
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@ManyToOne
	@JoinColumn(name = "artistId")
	private Artiste artist;


	
	

	public Artiste getArtist() {
		return artist;
	}

	public void setArtist(Artiste artist) {
		this.artist = artist;
	}

	public Album(Integer id, String title, Artiste artistId) {
		super();
		this.id = id;
		this.title = title;
		this.artist = artistId;
	}



public Album() {
	// TODO Auto-generated constructor stub
}

	//création ajout d'album vérifie s'il existe
	public boolean isPresent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		return "Album [id=" + id + ", title=" + title + ", artist=" + artist + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(artist, id, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Album other = (Album) obj;
		return Objects.equals(artist, other.artist) && Objects.equals(id, other.id)
				&& Objects.equals(title, other.title);
	} 

	
	
	 
	
	
	
	
	
	
}