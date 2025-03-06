package model;

public class SongWrapper {
	private Song song;
	
	private int rating;
	private boolean isFavorite;
	
	private int plays;
	
	public SongWrapper(Song song) {
		this.song = song;
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
	
	/*
	 * 		Getters
	 */
	public String getTitle() {
		return this.song.getTitle();
	}
	
	public String getArtist() {
		return this.song.getArtist();
	}

	public String getAlbum() {
		return this.song.getAlbum();
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
