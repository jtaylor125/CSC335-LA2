package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class UserSongTest {
	@Test
	void testBasic() {
		UserSong s = new UserSong("22", "Taylor Swift", "Red");
		
		assertEquals(s.getTitle(), "22");
		assertEquals(s.getArtist(), "Taylor Swift");
		assertEquals(s.getAlbum(), "Red");
	}
	
	@Test
	void testBasic2() {
		Song s = new Song("22", "Taylor Swift", "Red");
		UserSong song = new UserSong(s);
		
		assertEquals(song.getTitle(), "22");
		assertEquals(song.getArtist(), "Taylor Swift");
		assertEquals(song.getAlbum(), "Red");
	}

	
	@Test
	void testToString() {
		UserSong s = new UserSong("22", "Taylor Swift", "Red");
		
		String expected = "22\nArtist: Taylor Swift\nAlbum:  Red\n";
		
		assertEquals(s.toString(),expected);
	}
	
	@Test
	void testPlays() {
		UserSong s = new UserSong("22", "Taylor Swift", "Red");
		
		assertEquals(s.getPlayCount(),0);
		
		s.playSong();
		
		assertEquals(s.getPlayCount(),1);
		
		s.playSong();
		s.playSong();
		s.playSong();
		s.playSong();
		
		assertEquals(s.getPlayCount(),5);
	}
	
	@Test
	void testFavorite() {
		UserSong s = new UserSong("22", "Taylor Swift", "Red");
		assertFalse(s.isFavorite());
		s.setFavorite();
		assertTrue(s.isFavorite());
	}
	
	@Test
	void testRating() {
		UserSong s = new UserSong("22", "Taylor Swift", "Red");
		assertEquals(s.getRating(),0);
		assertFalse(s.isFavorite());
		
		s.rate(4);
		
		assertEquals(s.getRating(),4);
		assertFalse(s.isFavorite());
		
		s.rate(5);
		
		assertEquals(s.getRating(),5);
		assertTrue(s.isFavorite());
	}
}
