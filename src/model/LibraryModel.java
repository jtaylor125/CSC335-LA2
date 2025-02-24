package model;

import java.util.ArrayList;

public class LibraryModel {
	private ArrayList<Song> library;
	private ArrayList<Playlist> playlistList;
	private MusicStore musicStore;
	
	public LibraryModel() {
		this.library = new ArrayList<Song>();
		this.playlistList = new ArrayList<Playlist>();
		this.musicStore = new MusicStore();
	}
	
	// make sure to return copy/just info
	// search music store for song by title
	public Song searchStoreSongTitle(String title) {
		Song searchSong = MusicStore.searchSongTitle(title);
		return searchSong;
	}
	
	// make sure to return copy/just info
	// search music store for song by artist
	public Song searchStoreSongArtist(String artist) {
		Song searchSong = MusicStore.searchSongArtist(artist);
		return searchSong;
	}
	
	// make sure to return copy/just info
	// search music store for album by title
	public Album searchStoreAlbumTitle(String title) {
		Album searchAlbum = MusicStore.searchAlbumTitle(title);
		return searchAlbum;
	}
	
	// make sure to return copy/just info
	// search music store for album by artist
	public Album searchStoreAlbumArtist(String artist) {
		Album searchAlbum = MusicStore.searchAlbumArtist(artist);
		return searchAlbum;
	}
	
	// add a song from music store to library
	public void addSong(String songName) {
		//TO DO, search for song from MusicStore, add it to library
		return;
	}
	
	// add a whole album from music store to library
	public void addAlbum(String albumName) {
		//TO DO, search for album from MusicStore, add it to library
		return;
	}
	
	// get list of song titles in whole library
	public ArrayList<String> getSongTitles(){
		ArrayList<String> titles = new ArrayList<String>();
		
		// need to add getTitle to song
		for (int i=0; i<library.size();i++) {
			if (!titles.contains(library.get(i).getTitle())) {
				titles.add(library.get(i).getTitle());
			}
		}
		
		return titles; // do we need an unmodifiable list here?
	}
	
	// get list of artists in whole library
	public ArrayList<String> getArtists(){
		ArrayList<String> artists = new ArrayList<String>();
		
		// need to add getArtist to song
		for (int i=0; i<library.size();i++) {
			if (!artists.contains(library.get(i).getArtist())) {
				artists.add(library.get(i).getArtist());
			}
		}
		
		return artists; // do we need an unmodifiable list here?
	}
	
	// get list of albums in whole library
	public ArrayList<String> getAlbums(){
		ArrayList<String> albums = new ArrayList<String>();
		
		// need to add getAlbum to song
		for (int i=0; i<library.size();i++) {
			if (!albums.contains(library.get(i).getAlbum())) {
				albums.add(library.get(i).getAlbum());
			}
		}
		
		return albums; // do we need an unmodifiable list here?
	}
	
	// get list of playlists in whole library
	public ArrayList<String> getPlaylists(){
		ArrayList<String> playlists = new ArrayList<String>();
		
		// need to add getAlbum to song
		for (int i=0; i<playlistList.size();i++) {
			if (!playlists.contains(playlistList.get(i).getName())) {
				playlists.add(playlistList.get(i).getName());
			}
		}
		
		return playlists; // do we need an unmodifiable list here?
	}
	
	// get list of favorite songs in whole library
	public ArrayList<String> getFavoriteSongs(){
		ArrayList<String> favorites = new ArrayList<String>();
		
		// need to add getTitle and isFavorite to song
		for (int i=0; i<library.size();i++) {
			if (!favorites.contains(library.get(i).getTitle()) && library.get(i).isFavorite()) {
				favorites.add(library.get(i).getTitle());
			}
		}
		
		return favorites; // do we need an unmodifiable list here?
	}
	
	// create a playlist and add it to list of playlists
	public void createPlaylist(String playlistName) {
		Playlist newPlaylist = new Playlist(playlistName);
		playlistList.add(newPlaylist);
	}
	
	// add a song to a playlist
	public void addToPlaylist(Song song, String playlistName) {
		for (int i=0; i < playlistList.size(); i++) {
			if (playlistList.get(i).getName().equals(playlistName)) {
				playlistList.get(i).addSong(song);
			}
		}
	}
	
	// remove a song from a playlist
	public void removeFromPlaylist(Song songName, String playlistName) {
		for (int i=0; i < playlistList.size(); i++) {
			if (playlistList.get(i).getName().equals(playlistName)) {
				playlistList.get(i).removeSong(song);
			}
		}
	}
	
	// mark a song as a favorite
	// need a markFavorite method in Song
	public void markFavorite(String songName) {
		for (int i =0; i< library.size();i++) {
			if (library.get(i).getName().equals(songName)) {
				library.get(i).markFavorite();
			}
		}
	}
	
	// rate a song from 1 to 5
	// need a rating method
	public void rateSong(String songName, int rating) {
		for (int i =0; i< library.size();i++) {
			if (library.get(i).getName().equals(songName)) {
				library.get(i).rate(rating);
			}
		}
	}
	
}
