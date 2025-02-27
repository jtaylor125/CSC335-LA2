package model;

/*
 * File:	Song.java
 * Project: LA1-MusicLibrary
 * Author:	Tristan Emma
 * Editor:	Jacob Taylor
 * Purpose:	Song object used in the program. Stores
 * 			name, artist, and album name of each song
 * 			and stores rating (if there is one) and 
 * 			whether or not the song is a favorite
 * 			Each song is designed to be instantiated 
 * 			only once (i.e. no copies).
 */

public class Song {


	/*
	 * 		Instance Variables
	 */


	private final String title;
	private final String artist;
	private final String album;

	
	private boolean favorite;
	
	// if rating == 0, then it is considered "unrated"
	private int rating;
	
	/*
	 * 		Constructor
	 */
	public Song(String title, String artist, String album) {
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.rating = 0;
		this.favorite = false;
	}

	/*
	 * 		Setters
	 */
	// sets the rating in the instance
	// and checks if the rating was set 
	// to 5 so favorite can be turned on
	public void rate(int rating) {
		this.rating = rating;
		
		if(rating == 5)
			this.setFavorite();
	}
	
	public void setFavorite() {
		this.favorite = true;
	}
	

	/*
	 * 		Getters
	 */
	
	public boolean isFavorite(){
		return this.favorite;
	}
	
	
	public String getTitle() {
		return this.title;
	}
	
	public String getArtist() {
		return this.artist;
	}

	public String getAlbum() {
		return this.album;
	}
	
	public int getRating() {
		return this.rating;
	}
	
	
	
	@Override
	public String toString() {
		String retval = this.title  + "\n"
						+ "Artist: " + this.artist + "\n"
						+ "Album:  " + this.album  + "\n";
		
		return retval;		
	}
}
