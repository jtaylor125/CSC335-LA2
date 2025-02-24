package model;

public class Song {

	private String title;
	private String artist;
	private String album;
	
	private boolean favorite;
	
	private int rating;
	
	//public Song(Song s) {
	//	this(s.getTitle(), s.getArtist(), s.getAlbum());
	//}

	public Song(String title, String artist, String album) {
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.rating = 0;
		this.favorite = false;
	}

	public void rate(int rating) {
		this.rating = rating;
		
		if(rating == 5)
			this.setFavorite();
	}
	
	public void setFavorite() {
		this.favorite = true;
	}
	
	public boolean isFavorite(){
		return this.favorite;
	}
	
	public String getArtist() {
		return this.artist;
	}


	public String getTitle() {
		return this.title;
	}


	public String getAlbum() {
		return this.album;
	}
	
	public int getRating() {
		return this.rating;
	}
	
	@Override
	public String toString() {
		String retval =   "Title:  " + this.title  + "\n"
						+ "Author: " + this.artist + "\n"
						+ "Album:  " + this.album  + "\n";
		
		return retval;		
	}
}
