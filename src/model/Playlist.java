package model;

/*
 * File:	Playlist.java
 * Project: LA1-MusicLibrary
 * Author:	Jacob Taylor
 * Editor:	Tristan Emma
 * Purpose:	Playlist objects store a list of songs in the
 * 			order they are added. They have a name that is
 * 			used to identify them
 */

import java.util.ArrayList;
import java.util.Collections;

public class Playlist {
	
	/*
	 * 		Instance Variables
	 */
	
	private ArrayList<Song> playlist;
	private final String name;
	
	/*
	 * 		Constructor
	 */
	
	public Playlist(String name) {
		this.name = name;
		this.playlist = new ArrayList<Song>();
	}
	
	// void add(Song song) - add a song to the playlist. Assumes song is not already in the
	// playlist to avoid duplicates
	public void add(Song song) {
		playlist.add(song);
	}
	
	// void removeSong(Song song) - remove a song from the playlist. Assumes song is in the
	// playlist, makes no removal otherwise.
	public void removeSong(Song song) {
		for (int i=0;i<playlist.size();i++) {
			if (playlist.get(i).getTitle().equals(song.getTitle()) 
					&& playlist.get(i).getArtist().equals(song.getArtist())) {
				playlist.remove(i);
			}
		}
	}
	
	// boolean hasSong(Song song) - returns true if the song is in the playlist, false otherwise.
	public boolean hasSong(Song song) {
		for (Song s : playlist)
			if(s.equals(song)) {
				return true;
			}
		return false;
	}
	
	/*
	 * 		Getters
	 */
	
	public String getName() {
		return name;
	}
	
	public int getSize() {
		return playlist.size();
	}
	
	/*
	 * 		Overridden toString method (playlist name and songs)
	 */
	
	public void shuffle() {
		Collections.shuffle(playlist);
	}
	
	@Override
	public String toString() {
		String retval = "Playlist : " + this.name + "\n";
		
		for (Song s : playlist) {
			retval = retval + s.getTitle() + ", " + s.getArtist() + "\n";
		}
		
		retval = retval + "\n";
		
		return retval;
	}
	
	
	
}
