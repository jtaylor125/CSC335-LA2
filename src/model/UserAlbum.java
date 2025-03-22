package model;

import java.util.ArrayList;

public class UserAlbum extends Album {
	private ArrayList<UserSong> addedSongs;
	
	public UserAlbum(Album a) {
		super(a.getName(), a.getArtist(), a.getGenre(), a.getYear(), a.getSongs());
	
		addedSongs = new ArrayList<UserSong>();
	}
	
	public void addSong(UserSong song) {
		ArrayList<Song> albumSongs = this.getSongs();
		
		int index = -1;
		String songTitle = song.getTitle();
		
		for(int i = 0; i < albumSongs.size(); i++)
			if(songTitle.equals(albumSongs.get(i).getTitle()))
				index = i;
		
		addedSongs.add(song);
	}
	
	public void removeSong(UserSong song) {
		for(int i = 0; i < addedSongs.size(); i++) 
			if(song.getTitle().equals(addedSongs.get(i).getTitle()))
				addedSongs.remove(i);
	}
	
	public ArrayList<UserSong> getUserSongs(){
		return new ArrayList<UserSong>(addedSongs);
	}
	
	
}
