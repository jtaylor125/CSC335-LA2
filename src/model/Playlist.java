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
	public void add(Song song) {
		playlist.add(song);
	}
	
	public void removeSong(Song song) {
		for (int i=0;i<playlist.size();i++) {
			if (playlist.get(i).getTitle().equals(song.getTitle())) {
				playlist.remove(i);
			}
		}
	}
	
	public boolean hasSong(Song song) {
		for (Song s : playlist)
			if(s.equals(song)) {
				return true;
			}
		return false;
	}
	
	public String getName() {
		return name;
	}
	
	public int getSize() {
		return playlist.size();
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
