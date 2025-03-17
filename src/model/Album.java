package model;

/*
 * File:	Album.java
 * Project: LA1-MusicLibrary
 * Author:	Tristan Emma
 * Editor:	Jacob Taylor
 * Purpose:	Album object used in the program. Stores
 * 			name, artist, genre and year of each album
 * 			as well as its song list.
 * 			Each Album is designed to be instantiated 
 * 			only once (i.e. no copies).
 */


import java.util.ArrayList;

public class Album {


	/*
	 * 		Instance Variables
	 */

	private final ArrayList<Song> songList;

	
	private final String name;
	private final String artist;
	private final String genre;
	private final String year;
	
	/*
	 * 		Constructor
	 */
	public Album(String name, String artist, String genre, String year, ArrayList<Song> songs) {
		this.name   = name;
		this.artist = artist;
		this.genre  = genre;
		this.year   = year;
		
		this.songList = new ArrayList<Song>();
		for(Song s : songs) 
			this.songList.add(s);
	}
	
	public Album(Album a) {
		this.name   = a.getName();
		this.artist = a.getArtist();
		this.genre  = a.getGenre();
		this.year   = a.getYear();
		
		this.songList = a.getSongs();
	}
	
	
	/*
	 * 		Getters
	 */
	public String getName() {
		return this.name;
	}
	public String getArtist() {
		return this.artist;
	}
	public String getGenre() {
		return this.genre;
	}
	public String getYear() {
		return this.year;
	}	

	public ArrayList<Song> getSongs(){
		return new ArrayList<Song>(songList);
	}
	
	/*
	 * 		Overridden toString(name, artist, genre, year)
	 */

	@Override
	public String toString() {
		String retval =  this.name   + "\n"
						+ "Artist: " + this.artist + "\n"
						+ "Genre:  " + this.genre  + "\n"
						+ "Year:   " + this.year   + "\n";
		
		return retval;		
	}
}