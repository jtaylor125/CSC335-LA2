package model;

import java.util.Comparator;

public class UserSong extends Song{
	private int rating;
	private boolean isFavorite;
	
	private int plays;
	
	public UserSong(String title, String artist, String album) {
		super(title, artist, album);
		
		this.rating = 0;
		this.isFavorite = false;
		this.plays = 0;
	}
	
	public UserSong(Song s) {
		super(s.getTitle(), s.getArtist(), s.getAlbum());
		
		this.rating = 0;
		this.isFavorite = false;
		this.plays = 0;
	}
	
	public void playSong() {
		this.plays++;
	}
	
	public void setFavorite() {
		this.isFavorite = true;
	}
	
	public void rate(int rating) {
		this.rating = rating;
		
		if(rating == 5)
			this.setFavorite();
	}
	
	public int getRating() {
		return this.rating;
	}
	
	public int getPlayCount() {
		return this.plays;
	}
	
	public boolean isFavorite() {
		return isFavorite;
	}
	
	public static Comparator<UserSong> titleFirstComparator(){
		return new Comparator<UserSong>() {
			public int compare(UserSong song1, UserSong song2) {
				int comp = song1.getTitle().compareTo(song2.getTitle());
				if (comp == 0) {
					comp = song1.getArtist().compareTo(song2.getArtist());
				}
				return comp;
			}
		};
	}
	
	public static Comparator<UserSong> artistFirstComparator(){
		return new Comparator<UserSong>() {
			public int compare(UserSong song1, UserSong song2) {
				int comp = song1.getArtist().compareTo(song2.getArtist());
				if (comp == 0) {
					comp = song1.getTitle().compareTo(song2.getTitle());
				}
				return comp;
			}
		};
	}
	
	public static Comparator<UserSong> ratingFirstComparator(){
		return new Comparator<UserSong>() {
			public int compare(UserSong song1, UserSong song2) {
				int comp = Integer.compare(song1.rating,song2.rating);
				if (comp == 0) {
					comp = song1.getTitle().compareTo(song2.getTitle());
				}
				if (comp == 0) {
					comp = song1.getArtist().compareTo(song2.getArtist());
				}
				return comp;
			}
		};
	}

}
