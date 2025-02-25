package model;

import java.util.ArrayList;

public class Album {

	/*
	 * 		Instance Variables
	 */
	private ArrayList<Song> songList;
	
	private String name;
	private String artist;
	private String genre;
	private String year;
	
	/*
	 * 		Constructor
	 */
	public Album(String name, String artist, String genre, String year, ArrayList<Song> songs) {
		this.name   = name;
		this.artist = artist;
		this.genre  = genre;
		this.year   = year;
		
		this.songList = new ArrayList<Song>(songs);
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
		return new ArrayList<Song>(this.songList);
	}
	
	
	
	@Override
	public String toString() {
		String retval =  this.name   + "\n"
						+ "Artist: " + this.artist + "\n"
						+ "Genre:  " + this.genre  + "\n"
						+ "Year:   " + this.year   + "\n";
		
		return retval;		
	}
}
