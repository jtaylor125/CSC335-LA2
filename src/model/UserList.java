package model;

import java.util.ArrayList;

public class UserList {

	/*
	 * 		Instance Variables
	 */
	
	private ArrayList<User> userList;
	private User currentUser;
	
	/*
	 * 		Contructor
	 */
	
	public UserList() {
		this.userList = new ArrayList<User>();
		this.currentUser = null;
	}
	
	public void createUser(String username, String password) {
		User newUser = new User(username,password);
		userList.add(newUser);
	}
	
	public boolean isUser(String username) {
		for (User u:userList) {
			if (u.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean userLogIn(String username, String password) {
		User user = null;
		
		for (User u:userList) {
			if (u.getUsername().equals(username)) {
				user = u;
			}
		}
		
		if (user == null) {
			return false;
		}
		
		if (user.logIn(username, password)){
			this.currentUser = user;
			return true;
		}
		return false;		
	}
	
	public boolean userLogOut() {
		if (this.currentUser != null) {
			if (this.currentUser.isLoggedIn()) {
				this.currentUser.logOut();
				this.currentUser = null;
				return true;
			}
		}
		return false;
		
	}
	
	public String searchLibrary(String s, String songOrAlbum, String titleOrArtist) {
		return this.currentUser.searchLibrary(s, songOrAlbum, titleOrArtist);
	}
	
	public boolean addSong(String songName, String artist, MusicStore musicStore) {
		return this.currentUser.addSong(songName, artist, musicStore);
	}
	
	public boolean addAlbum(String albumName, String artist, MusicStore musicStore) {
		return this.currentUser.addAlbum(albumName, artist, musicStore);
	}
	
	public String getSongTitles() {
		return this.currentUser.getSongTitles();
	}
	
	public String getArtists() {
		return this.currentUser.getArtists();
	}
	
	public String getAlbums() {
		return this.currentUser.getAlbums();
	}
	
	public String getPlaylists() {
		return this.currentUser.getPlaylists();
	}
	
	public String getFavoriteSongs() {
		return this.currentUser.getFavoriteSongs();
	}
	
	public boolean createPlaylist(String playlistName) {
		return this.currentUser.createPlaylist(playlistName);
	}
	
	public boolean addToPlaylist(String songName, String artist, String playlistName) {
		return this.currentUser.addToPlaylist(songName, artist, playlistName);
	}
	
	public boolean removeFromPlaylist(String songName, String artist, String playlistName) {
		return this.currentUser.removeFromPlaylist(songName, artist, playlistName);
	}
	
	public boolean checkPlaylistExistence(String playlistName) {
		return this.currentUser.checkPlaylistExistence(playlistName);
	}
	
	public boolean checkSongInPlaylist(String songName, String artist, String playlistName) {
		return this.currentUser.checkSongInPlaylist(songName, artist, playlistName);
	}
	
	public boolean checkSongInLibrary(String songName, String artist) {
		return this.currentUser.checkSongInLibrary(songName, artist);
	}
	
	public void markFavorite(String songName, String artist) {
		this.currentUser.markFavorite(songName, artist);
	}
	
	public void rateSong(String songName, String artist, int rating) {
		this.currentUser.rateSong(songName, artist, rating);
	}
	
	public boolean isLoggedIn(String username) {
		for (User u:userList) {
			if (u.getUsername().equals(username) && u.isLoggedIn()) {
				return true;
			}
		}
		return false;
	}
}
