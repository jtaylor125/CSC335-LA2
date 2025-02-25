package model;

import java.util.ArrayList;

public class Playlist {
	private ArrayList<Song> playlist;
	private final String name;
	
	public Playlist(String name) {
		this.name = name;
		this.playlist = new ArrayList<Song>();
	}
	
	// assumes songs are immutable (check this)
	public void addSong(Song song) {
		playlist.add(song);
	}
	
	public void removeSong(String songName) {
		for (int i=0;i<playlist.size();i++) {
			if (playlist.get(i).getTitle().equals(songName)) {
				playlist.remove(i);
			}
		}
	}
	
	public String getName() {
		return name;
	}
	
	
	
}
