package model;

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

}
