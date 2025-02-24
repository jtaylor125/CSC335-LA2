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
	public Song searchSongTitle(String title) {
		// TO DO
		return null;
	}
	
	// make sure to return copy/just info
	// search music store for song by artist
	public Song searchSongArtist(String artist) {
		// TO DO
		return null;
	}
	
	// make sure to return copy/just info
	// search music store for album by title
	public Album searchAlbumTitle(String title) {
		// TO DO
		return null;
	}
	
	// make sure to return copy/just info
	// search music store for album by artist
	public Album searchAlbumArtist(String artist) {
		// TO DO
		return null;
	}
	
	// add a song from music store to library
	public void addSong(String songName) {
		//TO DO
		return;
	}
	
	// add a whole album from music store to library
	public void addAlbum(String albumName) {
		//TO DO
		return;
	}
	
	// get list of song titles in whole library
	public ArrayList<String> getSongTitles(){
		//TO DO
		return null;
	}
	
	// get list of artists in whole library
	public ArrayList<String> getArtists(){
		//TO DO
		return null;
	}
	
	// get list of albums in whole library
	public ArrayList<String> getAlbums(){
		//TO DO
		return null;
	}
	
	// get list of playlists in whole library
	public ArrayList<String> getPlaylists(){
		//TO DO
		return null;
	}
	
	// get list of favorite songs in whole library
	public ArrayList<String> getFavoriteSongs(){
		//TO DO
		return null;
	}
	
	// create a playlist and add it to list of playlists
	public void createPlaylist(String playlistName) {
		//TO DO
		return;
	}
	
	// add a song to a playlist
	public void addToPlaylist(String songName) {
		//TO DO 
		return;
	}
	
	// remove a song from a playlist
	public void removeFromPlaylist(String songName) {
		//TO DO
		return;
	}
	
	// mark a song as a favorite
	public void markFavorite(String songName) {
		//TO DO
		return;
	}
	
	// rate a song from 1 to 5
	public void rateSong(String songName, int rating) {
		//TO DO
		return;
	}
	
}
