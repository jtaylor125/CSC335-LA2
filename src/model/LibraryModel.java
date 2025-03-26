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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryModel {
	
	/*
	 * 		Instance Variables
	 */
	
	private ArrayList<UserSong> library;
	private ArrayList<UserSong> shuffledLibrary;
	private ArrayList<UserAlbum> albums;
	private ArrayList<Playlist> playlistList;
	
	private HashMap<UserSong,Integer> songPlays;
	private ArrayList<UserSong> recentsList;
	private HashMap<String, Integer> genreCounts;
	
	private Playlist recents;
	private Playlist favorites;
	private Playlist frequents;
	private Playlist topRated;
	
	/*
	 * 		Constructor
	 */
	
	public LibraryModel() {
		this.library = new ArrayList<UserSong>();
		this.shuffledLibrary = new ArrayList<UserSong>();
		this.albums = new ArrayList<UserAlbum>();
		
		this.playlistList = new ArrayList<Playlist>();
		
		this.songPlays = new HashMap<UserSong, Integer>();
		this.recentsList = new ArrayList<UserSong>();
		this.genreCounts = new HashMap<String, Integer>();
		
		this.recents   = new Playlist("Recents");
		this.favorites = new Playlist("Favorites");
		this.frequents = new Playlist("Frequents");
		this.topRated  = new Playlist("Top Rated");
		
		this.playlistList.add(recents);
		this.playlistList.add(favorites);
		this.playlistList.add(frequents);
		this.playlistList.add(topRated);
	}
	
	/*
	 * 		Search Methods
	 */
	public String search(String s, String songOrAlbum, String titleOrArtistOrGenre) {
		if(songOrAlbum.equals("Song") && titleOrArtistOrGenre.equals("Title"))
			return searchSongTitle(s);
		if(songOrAlbum.equals("Song") && titleOrArtistOrGenre.equals("Artist"))
			return searchSongArtist(s);
		if(songOrAlbum.equals("Album") && titleOrArtistOrGenre.equals("Title"))
			return searchAlbumTitle(s);
		if(songOrAlbum.equals("Album") && titleOrArtistOrGenre.equals("Artist"))
			return searchAlbumArtist(s);
		if(songOrAlbum.equals("Song") && titleOrArtistOrGenre.equals("Genre"))
			return searchSongGenre(s);
		
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
	
	// private search helper to return songs beiong searched by genre
	private String searchSongGenre(String val) {
		String retval = "";
		
		for (UserAlbum a : this.albums) {
			if (val.equals(a.getGenre())) {
				for (Song s : a.getUserSongs()) {
					retval = retval + s.toString();
				}
			}
		}
		
		return retval;
	}
	
	// boolean addSong - adds a song matching the name and artist from the music store to
	// the library. Returns true if the addition was successful, false otherwise.
	public boolean addSong(String songName, String artist, MusicStore musicStore) {
		Song song = musicStore.getSong(songName,artist);
		
		if (song == null) 
			return false;
		
		// if the song is already added, return true
		if(this.checkSongInLibrary(songName, artist))
			return true;
		
		// if the album does not exist in the library yet, add it
		if(!this.checkAlbumInLibrary(song.getAlbum(), artist))
			this.albums.add(new UserAlbum(musicStore.getAlbum(song.getAlbum(), artist)));
		
		// create a single song object, then add that to the album
		// and the library
		UserSong newSong = new UserSong(song);
		library.add(newSong);
		this.getAlbum(song.getAlbum(), artist).addSong(newSong);
		
		String genre = this.getAlbum(song.getAlbum(), artist).getGenre();
		if (this.genreCounts.containsKey(genre)) {
			genreCounts.put(genre, genreCounts.get(genre)+1);
		} else {
			genreCounts.put(genre, 1);
		}
		
		this.updateFavorites();
		this.updateFrequents();
		this.updateGenres();
		this.updateTopRated();
		this.shuffleLibrary();
		
		return true;
	}
	
	// boolean addAlbum - adds an album matching the name and artist from the music store to
	// the library. Returns true if the addition was successful, false otherwise. Also adds
	// all of the songs in the album to the library's library list.
	public boolean addAlbum(String albumName, String artist, MusicStore musicStore) {
		Album album = musicStore.getAlbum(albumName, artist);
	
		if(album == null) 
			return false;
		
		if(!this.checkAlbumInLibrary(albumName, artist))
				albums.add(new UserAlbum(album));
		
		UserAlbum ua = this.getAlbum(albumName, artist);
		
		for (Song s : album.getSongs()) {
			UserSong tempSong = new UserSong(s);
			if (!this.checkSongInLibrary(s.getTitle(), artist)) {
				library.add(tempSong);
				
				String genre = this.getAlbum(s.getAlbum(), artist).getGenre();
				if (this.genreCounts.containsKey(genre)) {
					genreCounts.put(genre, genreCounts.get(genre)+1);
				} else {
					genreCounts.put(genre, 1);
				}
			}
			
			ua.addSong(tempSong);
		}
		
		this.updateFavorites();
		this.updateFrequents();
		this.updateGenres();
		this.updateTopRated();
		this.shuffleLibrary();
		
		return true;
	}
	
	// removes the song indicated by the title and artist. returns false if it fails
	// true if successful
	public boolean removeSong(String title, String artist) {
		UserSong song = this.getSong(title, artist);
		
		if(song == null) {
			return false;
		}
		
		String genre = this.getAlbum(song.getAlbum(), artist).getGenre();
		
		genreCounts.put(genre, genreCounts.get(genre)-1);
		
		if(this.getAlbum(song.getAlbum(), artist).getUserSongs().size() == 1)
			this.albums.remove(this.getAlbum(song.getAlbum(), artist));
		else
			this.getAlbum(song.getAlbum(), artist).removeSong(song);
		
		this.library.remove(song);

		for(Playlist p : this.playlistList)
			if(p.hasSong(song))
				p.removeSong(song);
		this.updateFavorites();
		this.updateFrequents();
		this.updateGenres();
		this.removeFromRecents(song);
		this.updateTopRated();
		this.shuffleLibrary();
		
		return true;
	}
	
	
	// removes the album indicated by the name and artist. returns false if it fails
	// true if successful. also removes all the songs individually from the library
	public boolean removeAlbum(String name, String artist) {
		UserAlbum album = this.getAlbum(name, artist);
		
		if(album == null) {
			return false;
		}
		
		ArrayList<UserSong> songs = album.getUserSongs();
		
		for(UserSong s : songs) {
			this.library.remove(s);
			this.removeFromRecents(s);
			for(Playlist p : this.playlistList)
				if(p.hasSong(s))
					p.removeSong(s);
		}
		
		albums.removeIf(a -> a.getName().equals(name) && a.getArtist().equals(artist));
		
		this.updateFavorites();
		this.updateFrequents();
		this.updateGenres();
		this.updateTopRated();
		this.shuffleLibrary();
		
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
	
	public String getShuffledSongTitles(){
		ArrayList<String> titles = new ArrayList<String>();
		
		// need to add getTitle to song
		for (int i=0; i<shuffledLibrary.size();i++) {
			titles.add(shuffledLibrary.get(i).getTitle());
		}
		
		String retval = "";
		for (String t : titles)
			retval = retval + t + "\n";
		
		return retval;
	}
	
	// returns a list of song titles in the library sorted by title
	public String getSortedSongTitlesTitle(String ascendingOrDescending) {
		ArrayList<UserSong> sortedSongs = new ArrayList<UserSong>(this.library);
		
		Collections.sort(sortedSongs,UserSong.titleFirstComparator());
		
		if (ascendingOrDescending.toLowerCase().equals("descending")) {
			Collections.reverse(sortedSongs);
		}
		
		ArrayList<String> titles = new ArrayList<String>();
		
		for (int i=0; i<sortedSongs.size();i++) {
			titles.add(sortedSongs.get(i).getTitle());
		}
		
		String retval = "";
		for (String t : titles)
			retval = retval + t + "\n";
		
		return retval;
	}
	
	// returns a list of song titles in the library sorted by artist
	public String getSortedSongTitlesArtist(String ascendingOrDescending) {
		ArrayList<UserSong> sortedSongs = new ArrayList<UserSong>(this.library);
		
		Collections.sort(sortedSongs,UserSong.artistFirstComparator());
		
		if (ascendingOrDescending.toLowerCase().equals("descending")) {
			Collections.reverse(sortedSongs);
		}
		
		ArrayList<String> titles = new ArrayList<String>();
		
		for (int i=0; i<sortedSongs.size();i++) {
			titles.add(sortedSongs.get(i).getTitle());
		}
		
		String retval = "";
		for (String t : titles)
			retval = retval + t + "\n";
		
		return retval;
	}

	// returns a list of song titles in the library sorted by rating
	public String getSortedSongTitlesRating(String ascendingOrDescending) {
		ArrayList<UserSong> sortedSongs = new ArrayList<UserSong>(this.library);
		
		Collections.sort(sortedSongs,UserSong.ratingFirstComparator());
		
		if (ascendingOrDescending.toLowerCase().equals("descending")) {
			Collections.reverse(sortedSongs);
		}
		
		ArrayList<String> titles = new ArrayList<String>();
		
		for (int i=0; i<sortedSongs.size();i++) {
			titles.add(sortedSongs.get(i).getTitle());
		}
		
		String retval = "";
		for (String t : titles)
			retval = retval + t + "\n";
		
		return retval;
	}
	
	// used by UserManager to save library information to file, returns
	// a string containing all the informaiton to be saved to a file for a user
	String getLibrarySongInformation() {
		String retval = "";
		
		for(UserSong s : this.library) {
			retval = retval + s.getTitle()  + ",";
			retval = retval + s.getArtist() + ",";
			
			if(s.isFavorite())
				retval = retval + "1" + ",";
			else
				retval = retval + "0" + ",";
			
			retval = retval + s.getRating()    + ",";
			retval = retval + s.getPlayCount() + "\n";
		}
		
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
		
		for (int i=0; i<playlistList.size();i++) {
			if (!playlists.contains(playlistList.get(i).getName())) {
				playlists.add(playlistList.get(i).toString() + "Shuffled: \n" + playlistList.get(i).getShuffleSongListString());
			}
		}
		
		String retval = "";
		for (String p : playlists) {
			retval = retval + p + "\n";
		}
		
		
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
	
	// returns the album information based upon the song requested after a search
	public String getInfoAfterSearch(String songName, String artist) {
		String retval ="";
		
		for (Album a : this.albums) {
			for (Song s : a.getSongs()) {
				if (s.getTitle().equals(songName) && s.getArtist().equals(artist)) {
					retval += "Title: " + songName + "\n";
					retval += "Artist: " + artist + "\n";
					retval += "Album: " + a.getName() + "\n";
					retval += "Genre: " + a.getGenre() + "\n";
				}
			}
		}
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
		UserSong song = this.getSong(songName, artist);

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
		UserSong song = this.getSong(songName, artist);

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
			if (p.getName().equals(playlistName)) 
				return true;
		
		if (playlistName.equals("Reecents"))
			return true;
		else if (playlistName.equals("Frequents"))
			return true;
		else if (playlistName.equals("Favorites"))
			return true;
		else if (playlistName.equals("Top Rated"))
			return true;
		
		
		return false;
	}
	
	// boolean checkSongInPlaylist - checks if a song identified by its name and artist is in the 
	// playlist identified by playlistName. If either object can't be found, returns false. If
	// they exist, returns true if the song is in the playlist, false otherwise.
	public boolean checkSongInPlaylist(String songName, String artist, String playlistName) {
		UserSong song = this.getSong(songName, artist);
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
	
	// shuffles a playlist for playing
	public void shufflePlaylist(String name) {
		for (Playlist p: playlistList) {
			if (p.getName().equals(name)) {
				p.shuffle();			
			}
		}
	}
	
	// updates the favorites autoplaylist
	void updateFavorites() {
		for(UserSong us : this.library) 
			if(us.isFavorite() && !this.favorites.hasSong(us))
				this.favorites.add(us);
	}
	
	// updates the top rated autoplaylist
	void updateTopRated() {
		for(UserSong us : this.library)
			if(((us.getRating() == 4) || (us.getRating() == 5)) && !this.topRated.hasSong(us))
				this.topRated.add(us);
	}
	
	// updates the frequents autoplaylist
	private void updateFrequents() {
		ArrayList<Map.Entry<UserSong,Integer>> frequents = new ArrayList(this.songPlays.entrySet());
		
		frequents.sort((a,b) -> b.getValue().compareTo(a.getValue()));
		
		ArrayList<UserSong> top10frequents = new ArrayList<UserSong>();
		
		for (int i=0; i< Math.min(10,frequents.size());i++) {
			top10frequents.add(frequents.get(i).getKey());
		}
		
		this.frequents.clear();
		
		for (int i=0; i<top10frequents.size(); i++) {
			this.frequents.add(top10frequents.get(i));
		}
	}
	
	//TODO: COMMENT
	private void updateGenres() {
		ArrayList<Map.Entry<String,Integer>> genreList = new ArrayList(this.genreCounts.entrySet());
		
		ArrayList<String> genresWith10 = new ArrayList<String>();
		
		for (int i=0; i<genreList.size(); i++) {
			if (genreList.get(i).getValue() >= 10) {
				genresWith10.add(genreList.get(i).getKey());
			}
		}
		
		for (String genre : genresWith10) {
			this.createPlaylist(genre);
			
			for (UserAlbum album : this.albums) {
				if (album.getGenre().equals(genre)){
					for (Song song : album.getSongs()) {
						this.addToPlaylist(song.getTitle(), song.getArtist(), genre);
					}
				}
			}
		}
	}
	
	// Used by UserManager when building library from txt file to build
	// most recent autoplaylist
	void setMostRecent(String songTitle, String artist) {
		UserSong mostRecent = this.getSong(songTitle, artist);
		
		if(mostRecent != null) {
			this.updateRecents(mostRecent);
		}
	}
	
	
	// updates recents playlist whenever a song is played
	private void updateRecents(UserSong mostRecent) {
		this.recentsList.add(mostRecent);
		Collections.reverse(recentsList);
		this.recents.clear();
		
		for (int i=0; i<Math.min(10,recentsList.size());i++) {
			this.recents.add(recentsList.get(i));
		}
			
		Collections.reverse(recentsList);
	}
	
	// removes a soing from recents (used when deleteing songs or albums)
	private void removeFromRecents(UserSong toRemove) {
		this.recentsList.remove(toRemove);
		Collections.reverse(recentsList);
		this.recents.clear();
		
		for (int i=0; i<Math.min(10,recentsList.size());i++) {
			this.recents.add(recentsList.get(i));
		}
			
		Collections.reverse(recentsList);
	}

	// returns a String of all the playlist names to be parsed for saving to file
	String getPlaylistNames() {
		String retval = "";
		
		for(Playlist p:this.playlistList) {
			retval = retval + p.getName() + "\n";
		}
		
		return retval;
	}

	// used by UserManager to get all the songs in a playlist for storing in txt file
	public String getPlaylistSongs(String playlistName) {
		Playlist curr = this.getPlaylist(playlistName);
		
		if(curr == null) {
			return null;
		}
		
		String retval = "";
		
		for(Song s :curr.getSongList()) {
			if (s != null)
				retval = retval + s.getTitle() + "," + s.getArtist() + "\n";
		}
		
		return retval;
	}
	
	public String getPlaylistShuffleSongs(String playlistName) {
		Playlist curr = this.getPlaylist(playlistName);
		
		if(curr == null) {
			return null;
		}
		
		String retval = "";
		
		for(Song s :curr.getShuffleSongList()) {
			retval = retval + s.getTitle() + "," + s.getArtist() + "\n";
		}
		
		return retval;
	}
	
	//
	// returns String with songs in recents playlist listed
	// one per line
	public String getRecentsPlaylist() {
		String retval = "";
		
		for(Song s: this.recents.getSongList()) {
			retval = retval + s.getTitle() + "," + s.getArtist() + "\n";
		}
		return retval;
	}
	
	public String getFrequentsPlaylist() {
		return this.frequents.toString();
	}
	
	public String getTopRateddPlaylist() {
		return this.topRated.toString();
	}
	
	public String getGenrePlaylists() {
		String retVal = "";
		
		for (Playlist p : this.playlistList) {
			if (this.genreCounts.keySet().contains(p.getName())) {
				retVal += p.toString();
			}
		}
		
		return retVal;
	}
	
	
	// boolean checkSongInLibrary - checks if a song identified by its name and artist is in the 
	// library. Returns true if the song is in the library, false otherwise. 
	public boolean checkSongInLibrary(String songName, String artist) {
		UserSong song = this.getSong(songName, artist);
		
		return song != null;
	}
	
	public boolean checkAlbumInLibrary(String name, String artist) {
		UserAlbum album = this.getAlbum(name, artist);
		
		return album != null;
	}
	
	// void markFavorite - marks a song identified by its name and artist as a favorite. Assumes
	// that song exists.
	public void markFavorite(String songName, String artist) {
		UserSong song = this.getSong(songName, artist);
		
		song.setFavorite();
		
		this.updateFavorites();
		this.updateTopRated();
	}
	
	// void rateSong - rates a song identified by its name and artist an integer rating. Assumes
	// that song exists, and rating is between 1 and 5.
	public void rateSong(String songName, String artist, int rating) {
		UserSong song = this.getSong(songName, artist);
		
		song.rate(rating);
		
		this.updateFavorites();
		this.updateTopRated();
	}
	
	// int getSongRating - package-only helper method for testing the class. Returns the
	// rating of a song identified by its name and artist. Assumes that song exists.
	int getSongRating(String songName, String artist) {
		UserSong song = this.getSong(songName, artist);
		return song.getRating();
	}
	
	// NEEDS COMMENT
	public void playSong(String title, String artist) {
		UserSong song = this.getSong(title, artist);
		
		song.playSong();
		
		if (!songPlays.containsKey(song)) {
			songPlays.put(song, 1);
		} else {
			songPlays.put(song, songPlays.get(song)+1);
		}
		
		// update playlists
		this.updateRecents(song);
		this.updateFrequents();
	}
	
	// plays the specified playlist
	public void playPlaylist(String playlistName) {
		for (Playlist p : this.playlistList) {
			if (p.getName().equals(playlistName)) {
				for (Song song : p.getSongList()) {
					this.playSong(song.getTitle(), song.getArtist());
				}
			}
		}
	}
	
	// creates a shuffled library for playing
	public void shuffleLibrary() {
		this.shuffledLibrary = new ArrayList<UserSong>(this.library);
		Collections.shuffle(this.shuffledLibrary);
	}
	
	// Song getSong - private helper method to get a song object from the library. Returns null
	// if the song is not found.
	private UserSong getSong(String title, String artist) {
		for (UserSong s:library) {
			if (s.getTitle().equals(title) && s.getArtist().equals(artist)) {
				return s;
			}
		}
		return null;
	}
	
	private UserAlbum getAlbum(String name, String artist) {
		for(UserAlbum ua : this.albums)
			if(name.equals(ua.getName()) && artist.equals(ua.getArtist()))
				return ua;
		
		return null;
	}
	
}
