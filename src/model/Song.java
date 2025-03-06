package model;

/*
 * File:	Song.java
 * Project: LA1-MusicLibrary
 * Author:	Tristan Emma
 * Editor:	Jacob Taylor
 * Purpose:	Song object used in the program. Stores
 * 			name, artist, and album name of each song
 * 			Designed to be immutable with single instances
 * 			of each unique song.
 */

public class Song {


	/*
	 * 		Instance Variables
	 */
	private final String title;
	private final String artist;
	private final String album;

	
	/*
	 * 		Constructor
	 */
	public Song(String title, String artist, String album) {
		this.title = title;
		this.artist = artist;
		this.album = album;
	}

	/*
	 * 		Getters
	 */
	public String getTitle() {
		return this.title;
	}
	
	public String getArtist() {
		return this.artist;
	}

	public String getAlbum() {
		return this.album;
	}
	
	
	/*
	 * 		Overridden toString method (title, artist, and album)
	 */
	
	@Override
	public String toString() {
		String retval = this.title  + "\n"
						+ "Artist: " + this.artist + "\n"
						+ "Album:  " + this.album  + "\n";
		
		return retval;		
	}
}
