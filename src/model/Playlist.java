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
	private ArrayList<Song> shuffledPlaylist;
	
	private final String name;
	
	/*
	 * 		Constructor
	 */
	
	public Playlist(String name) {
		this.name = name;
		this.playlist = new ArrayList<Song>();
		this.shuffledPlaylist = new ArrayList<Song>();  
	}
	
	// void add(Song song) - add a song to the playlist. Assumes song is not already in the
	// playlist to avoid duplicates
	public void add(Song song) {
		playlist.add(song);
		this.shuffle();
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
		this.shuffle();
	}
	
	// boolean hasSong(Song song) - returns true if the song is in the playlist, false otherwise.
	public boolean hasSong(Song song) {
		if (playlist.size() == 0) {
			return false;
		}
		for (Song s : playlist) {
			if (s != null) {
				if(s.equals(song)) {
					return true;
				}
			}
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
	
	public ArrayList<Song> getSongList(){
		return new ArrayList<Song>(this.playlist);
	}
	
	public ArrayList<Song> getShuffleSongList(){
		return new ArrayList<Song>(this.shuffledPlaylist);
	}
	
	public String getShuffleSongListString(){
		String retval = "";
		for (Song s : this.shuffledPlaylist) {
			retval += s.toString();
		}
		return retval;
	}
	
	/*
	 * 		Overridden toString method (playlist name and songs)
	 */
	
	public void shuffle() {
		this.shuffledPlaylist = new ArrayList<Song>(this.playlist);
		Collections.shuffle(this.shuffledPlaylist);
	}
	
	public void clear() {
		this.playlist = new ArrayList<Song>();
	}
	
	@Override
	public String toString() {
		String retval = "Playlist : " + this.name + "\n";
		
		for (Song s : playlist) {
			if (s != null)
				retval = retval + s.getTitle() + ", " + s.getArtist() + "\n";
		}
		
		retval = retval + "\n";
		
		return retval;
	}
	
	
	
}
