package model;

/*
 * File:	LibraryModel.java
 * Project: LA1-MusicLibrary
 * Author:	Jacob Taylor
 * Editor:	Tristan Emma
 * Purpose:	Contains main Library functionality. 
 * 			i.e. stores songs added, albums added, and created playlists
 * 				 allows for adding, favoriting and rating songs
 * 				 allows for creation and editing of playlists
 * 				 has searching and listing capabilities for within Library
 */

import java.util.ArrayList;

public class LibraryModel {
	
	/*
	 * 		Instance Variables
	 */
	
	private ArrayList<Song> library;
	private ArrayList<Album> albums;
	private ArrayList<Playlist> playlistList;
	
	/*
	 * 		Constructor
	 */
	
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
	
	// private search helper to return a String that lists all songs of a Title in the library
	private String searchSongTitle(String val) {
		String retval = "";
		
		for(Song s : this.library) 
			if(val.equals(s.getTitle())) 
				retval = retval + s.toString();

		return retval;
	}
	
	// private search helper to return a String that lists all songs by an Artist in the library
	private String searchSongArtist(String val) {
		String retval = "";
		
		for(Song s : this.library)
			if(val.equals(s.getArtist()))
				retval = retval + s.toString();		
		return retval;
	}
	
	// private search helper to return a String that lists all albums of a Title in the library
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
	
	// private search helper to return a String that lists all albums by an Artist in the library
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
	
	// boolean addSong - adds a song matching the name and artist from the music store to
	// the library. Returns true if the addition was successful, false otherwise.
	public boolean addSong(String songName, String artist, MusicStore musicStore) {
		Song song = musicStore.getSong(songName,artist);
		
		if (song != null) {
			library.add(song);
			return true;
		} else {
			return false;
		}
	}
	
	// boolean addAlbum - adds an album matching the name and artist from the music store to
	// the library. Returns true if the addition was successful, false otherwise. Also adds
	// all of the songs in the album to the library's library list.
	public boolean addAlbum(String albumName, String artist, MusicStore musicStore) {
		Album album = musicStore.getAlbum(albumName, artist);
	
		if(album == null) {
			return false;
		}
			
		albums.add(album);
		
		for (Song s : album.getSongs())
			if (!library.contains(s))
				library.add(s);
		
		return true;
	}
	
	// String getSongTitles - returns one String with all of the song titles in the library on
	// different lines in the order they were added.
	public String getSongTitles(){
		ArrayList<String> titles = new ArrayList<String>();
		
		// need to add getTitle to song
		for (int i=0; i<library.size();i++) {
			titles.add(library.get(i).getTitle());
		}
		
		String retval = "";
		for (String t : titles)
			retval = retval + t + "\n";
		
		return retval;
	}
	
	// String getArtists - returns one String with all of the artists in the library on
	// different lines in the order they were added.
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
	
	// String getAlbums - returns one String with all of the albums in the library on
	// different lines in the order they were added.
	public String getAlbums(){
		ArrayList<String> albumsReturn = new ArrayList<String>();
		
		
		for(Album a : albums) {
			albumsReturn.add(a.getName());
		}
		
		String retval = "";
		for (String a : albumsReturn)
			retval = retval + a + "\n";
		
		return retval;
	}
	
	// String getPlaylists - returns one String with all of the playlists in the library on
	// different lines in the order they were added.
	public String getPlaylists(){
		ArrayList<String> playlists = new ArrayList<String>();
		
		// need to add getAlbum to song
		for (int i=0; i<playlistList.size();i++) {
			if (!playlists.contains(playlistList.get(i).getName())) {
				playlists.add(playlistList.get(i).toString());
			}
		}
		
		String retval = "";
		for (String p : playlists)
			retval = retval + p + "\n";
		
		return retval;
	}
	
	// String getFavoriteSongs - returns one String with all of the favorite song titles in 
	// the library on different lines in the order they were favorited.
	public String getFavoriteSongs(){
		ArrayList<String> favorites = new ArrayList<String>();
		
		// need to add getTitle and isFavorite to song
		for (int i=0; i<library.size();i++) {
			if (library.get(i).isFavorite()) {
				favorites.add(library.get(i).getTitle());
			}
		}
		
		String retval = "";
		for (String f : favorites)
			retval = retval + f + "\n";
		
		return retval;
	}
	
	// boolean createPlaylist - creates a playlist named by the parameter. If a playlist already
	// exists with that name, does not create a new one and returns false. Otherwise, creates
	// a new playlist and returns true.
	public boolean createPlaylist(String playlistName) {
		Playlist checkExists = this.getPlaylist(playlistName);
		if(checkExists != null) {
			return false;
		}
		Playlist newPlaylist = new Playlist(playlistName);
		playlistList.add(newPlaylist);
		return true;
	}
	
	// boolean addToPlaylist - adds a song found by the song name and artist to the playlist
	// with playlist name. Assumes the song exists, returns false if the playlist does not exist.
	// If the playlist and song both exist, add the song and return true.
	public boolean addToPlaylist(String songName, String artist, String playlistName) {
		Song song = this.getSong(songName, artist);

		Playlist p = this.getPlaylist(playlistName);
		if (p == null) {
			return false;
		}
		
		if(!p.hasSong(song))
			p.add(song);
		return true;
	}
	
	// boolean removeFromPlaylist - removes a song found by the song name and artist from the 
	// playlist with playlist name. Assumes the song exists, returns false if the playlist does 
	// not exist. If the playlist and song both exist, remove the song and return true.
	public boolean removeFromPlaylist(String songName, String artist, String playlistName) {
		Song song = this.getSong(songName, artist);

		Playlist p = this.getPlaylist(playlistName);
		if (p == null) {
			return false;
		}
		
		if(p.hasSong(song))
			p.removeSong(song);
		return true;
	}
	
	// int playlistLength - package-only helper method for testing the class. Returns the
	// number of songs in the playlist that matches playlistName, returns -1 if that
	// playlist isn't found
	int playlistLength(String playlistName) {
		int playlistLength = -1;
		for (int i=0; i < playlistList.size(); i++) {
			if (playlistList.get(i).getName().equals(playlistName)) {
				playlistLength = playlistList.get(i).getSize();
			}
		}
		return playlistLength;
	}
	
	// boolean checkPlaylistExistence - checks if a playlist of playlistName exists, returning true
	// if it does and false if it does not.
	public boolean checkPlaylistExistence(String playlistName) {
		for (Playlist p : playlistList)
			if (p.getName().equals(playlistName)) {
				return true;
			}
		return false;
	}
	
	// boolean checkSongInPlaylist - checks if a song identified by its name and artist is in the 
	// playlist identified by playlistName. If either object can't be found, returns false. If
	// they exist, returns true if the song is in the playlist, false otherwise.
	public boolean checkSongInPlaylist(String songName, String artist, String playlistName) {
		Song song = this.getSong(songName, artist);
		if (song == null) {
			return false;
		}
		Playlist p = this.getPlaylist(playlistName);
		if (p == null) {
			return false;
		}
		
		return p.hasSong(song);
	}
	
	// private getter to get a playlist by its name. Returns null if no playlist is found.
	private Playlist getPlaylist(String name) {
		for(Playlist p : playlistList) 
			if(p.getName().equals(name))
				return p;
		
		return null;
	}
	
	// boolean checkSongInLibrary - checks if a song identified by its name and artist is in the 
	// library. Returns true if the song is in the library, false otherwise. 
	public boolean checkSongInLibrary(String songName, String artist) {
		Song song = this.getSong(songName, artist);
		
		return song != null;
	}
	
	// void markFavorite - marks a song identified by its name and artist as a favorite. Assumes
	// that song exists.
	public void markFavorite(String songName, String artist) {
		Song song = this.getSong(songName, artist);
		
		song.setFavorite();

	}
	
	// void rateSong - rates a song identified by its name and artist an integer rating. Assumes
	// that song exists, and rating is between 1 and 5.
	public void rateSong(String songName, String artist, int rating) {
		Song song = this.getSong(songName, artist);
		
		song.rate(rating);
	}
	
	// int getSongRating - package-only helper method for testing the class. Returns the
	// rating of a song identified by its name and artist. Assumes that song exists.
	int getSongRating(String songName, String artist) {
		Song song = this.getSong(songName, artist);
		return song.getRating();
	}
	
	// Song getSong - private helper method to get a song object from the library. Returns null
	// if the song is not found.
	private Song getSong(String title, String artist) {
		for (Song s:library) {
			if (s.getTitle().equals(title) && s.getArtist().equals(artist)) {
				return s;
			}
		}
		return null;
	}
	
}
