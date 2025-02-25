package model;

import java.util.ArrayList;

public class Album {
	private final ArrayList<Song> songList;
	
	private final String name;
	private final String artist;
	private final String genre;
	private final String year;
	
	public Album(String name, String artist, String genre, String year, ArrayList<Song> songs) {
		this.name   = name;
		this.artist = artist;
		this.genre  = genre;
		this.year   = year;
		
		this.songList = new ArrayList<Song>(songs);
	}
	
	
	
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
	
	ArrayList<Song> getSongs(){
		return songList;
	}
	
	@Override
	public String toString() {
		String retval =   "Album:  " + this.name   + "\n"
						+ "Artist: " + this.artist + "\n"
						+ "Genre:  " + this.genre  + "\n"
						+ "Year:   " + this.year   + "\n";
		
		return retval;		
	}
}
