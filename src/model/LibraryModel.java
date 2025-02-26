package model;

import java.util.ArrayList;

public class LibraryModel {
	private ArrayList<Song> library;
	private ArrayList<Album> albums;
	private ArrayList<Playlist> playlistList;
	
	public LibraryModel() {
		this.library = new ArrayList<Song>();
		this.albums = new ArrayList<Album>();
		this.playlistList = new ArrayList<Playlist>();
	}
	
	/*
	 * 		Search Methods
	 */
	public String search(String s, String songOrAlbum, String titleOrArtist) {
		if(songOrAlbum.equals("Song") && titleOrArtist.equals("Title"))
			return searchSongTitle(s);
		if(songOrAlbum.equals("Song") && titleOrArtist.equals("Artist"))
			return searchSongArtist(s);
		if(songOrAlbum.equals("Album") && titleOrArtist.equals("Title"))
			return searchAlbumTitle(s);
		if(songOrAlbum.equals("Album") && titleOrArtist.equals("Artist"))
			return searchAlbumArtist(s);
		
		return "Incorrect parameters sent, check code\n";
	}
	
	private String searchSongTitle(String val) {
		String retval = "";
		
		for(Song s : this.library) 
			if(val.equals(s.getTitle())) 
				retval = retval + s.toString();

		return retval;
	}
	
	private String searchSongArtist(String val) {
		String retval = "";
		
		for(Song s : this.library)
			if(val.equals(s.getArtist()))
				retval = retval + s.toString();		
		return retval;
	}
	
	private String searchAlbumTitle(String val) {
		String retval = "";
		
		for(Album a : this.albums) {
			if(val.equals(a.getName())) {
				retval = retval + a.toString();
				for(Song s: a.getSongs())
					retval = retval + s.getTitle() + "\n";
			}
		}
		return retval;
	}
	
	private String searchAlbumArtist(String val) {
		String retval = "";
		
		for(Album a : this.albums) {
			if(val.equals(a.getArtist())) {
				retval = retval + a.toString();
				for(Song s: a.getSongs())
					retval = retval + s.getTitle() + "\n";
			}
		}
		return retval;
	}
	
	// add a song from music store to library
	public void addSong(String songName, String artist, MusicStore musicStore) {
		ArrayList<Song> matchSongs = musicStore.getSongsFromStore(songName,artist);
		
		for (Song s : matchSongs)
			library.add(s);
	}
	
	// add a whole album from music store to library
	public void addAlbum(String albumName, String artist, MusicStore musicStore) {
		ArrayList<Album> matchAlbums = musicStore.getAlbumsFromStore(albumName, artist);
		
		for (Album a : matchAlbums) {
			albums.add(a);
			for (Song s : a.getSongs())
				if (!library.contains(s))
					library.add(s);
		}
	}
	
	// get list of song titles in whole library
	public String getSongTitles(){
		ArrayList<String> titles = new ArrayList<String>();
		
		// need to add getTitle to song
		for (int i=0; i<library.size();i++) {
			if (!titles.contains(library.get(i).getTitle())) {
				titles.add(library.get(i).getTitle());
			}
		}
		
		String retval = "";
		for (String t : titles)
			retval = retval + t + "\n";
		
		return retval;
	}
	
	// get list of artists in whole library
	public String getArtists(){
		ArrayList<String> artists = new ArrayList<String>();
		
		// need to add getArtist to song
		for (int i=0; i<library.size();i++) {
			if (!artists.contains(library.get(i).getArtist())) {
				artists.add(library.get(i).getArtist());
			}
		}
		
		String retval = "";
		for (String a : artists)
			retval = retval + a + "\n";
		
		return retval;
	}
	
	// get list of albums in whole library
	public String getAlbums(){
		ArrayList<String> albumsReturn = new ArrayList<String>();
		
		// need to add getAlbum to song
		for (int i=0; i<albums.size();i++) {
			if (!albumsReturn.contains(albums.get(i))) {
				albumsReturn.add(albums.get(i).getName());
			}
		}
		
		String retval = "";
		for (String a : albumsReturn)
			retval = retval + a + "\n";
		
		return retval;
	}
	
	// get list of playlists in whole library
	public String getPlaylists(){
		ArrayList<String> playlists = new ArrayList<String>();
		
		// need to add getAlbum to song
		for (int i=0; i<playlistList.size();i++) {
			if (!playlists.contains(playlistList.get(i).getName())) {
				playlists.add(playlistList.get(i).getName());
			}
		}
		
		String retval = "";
		for (String p : playlists)
			retval = retval + p + "\n";
		
		return retval;
	}
	
	// get list of favorite songs in whole library
	public String getFavoriteSongs(){
		ArrayList<String> favorites = new ArrayList<String>();
		
		// need to add getTitle and isFavorite to song
		for (int i=0; i<library.size();i++) {
			if (!favorites.contains(library.get(i).getTitle()) && library.get(i).isFavorite()) {
				favorites.add(library.get(i).getTitle());
			}
		}
		
		String retval = "";
		for (String f : favorites)
			retval = retval + f + "\n";
		
		return retval;
	}
	
	// create a playlist and add it to list of playlists
	public void createPlaylist(String playlistName) {
		Playlist newPlaylist = new Playlist(playlistName);
		playlistList.add(newPlaylist);
	}
	
	// add a song to a playlist
	public void addToPlaylist(String songName, String artist, String playlistName) {
		Song song = this.getSong(songName, artist);
		for (int i=0; i < playlistList.size(); i++) {
			if (playlistList.get(i).getName().equals(playlistName)) {
				playlistList.get(i).add(song);
			}
		}
	}
	
	// remove a song from a playlist
	public void removeFromPlaylist(String songName, String artist, String playlistName) {
		Song song = this.getSong(songName, artist);
		for (int i=0; i < playlistList.size(); i++) {
			if (playlistList.get(i).getName().equals(playlistName)) {
				playlistList.get(i).removeSong(song);
			}
		}
	}
	
	int playlistLength(String playlistName) {
		int playlistLength = -1;
		for (int i=0; i < playlistList.size(); i++) {
			if (playlistList.get(i).getName().equals(playlistName)) {
				playlistLength = playlistList.get(i).getSize();
			}
		}
		return playlistLength;
	}
	
	public boolean checkPlaylistExistence(String playlistName) {
		for (Playlist p : playlistList)
			if (p.getName().equals(playlistName)) {
				return true;
			}
		return false;
	}
	
	public boolean checkSongInPlaylist(String songName, String playlistName) {
		for (Playlist p : playlistList)
			if (p.getName().equals(playlistName)) {
				return p.checkSongInPlaylist(songName);
			}
		return false;
	}
	
	public boolean checkSongInLibrary(String songName) {
		for (Song s : library)
			if (s.getTitle().equals(songName)) {
				return true;
			}
		return false;
	}
	
	// mark a song as a favorite
	// need a markFavorite method in Song
	public void markFavorite(String songName, String artist) {
		Song song = this.getSong(songName, artist);
		for (int i =0; i< library.size();i++) {
			if (library.get(i).getTitle().equals(song.getTitle()) && library.get(i).getArtist().equals(song.getArtist())) {
				library.get(i).setFavorite();
			}
		}
	}
	
	// rate a song from 1 to 5
	// need a rating method
	public void rateSong(String songName, String artist, int rating) {
		Song song = this.getSong(songName, artist);
		for (int i =0; i< library.size();i++) {
			if (library.get(i).getTitle().equals(song.getTitle()) && library.get(i).getArtist().equals(song.getArtist())) {
				library.get(i).rate(rating);
			}
		}
	}
	
	int getSongRating(String songName, String artist) {
		Song song = this.getSong(songName, artist);
		return song.getRating();
	}
	
	private Song getSong(String title, String artist) {
		for (Song s:library) {
			if (s.getTitle().equals(title) && s.getArtist().equals(artist)) {
				return s;
			}
		}
		return null;
	}
	
}
