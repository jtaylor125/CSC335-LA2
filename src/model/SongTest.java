package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SongTest {

	@Test
	void testBasic() {
		Song s = new Song("22", "Taylor Swift", "Red");
		
		assertEquals(s.getTitle(), "22");
		assertEquals(s.getArtist(), "Taylor Swift");
		assertEquals(s.getAlbum(), "Red");
	}

	@Test
	void testRating() {
		Song s = new Song("22", "Taylor Swift", "Red");
		
		s.rate(4);
		
		assertEquals(s.getRating(), 4);
		assertFalse(s.isFavorite());
	}
	
	@Test
	void testRating5() {
		Song s = new Song("22", "Taylor Swift", "Red");
		
		s.rate(5);
		
		assertEquals(s.getRating(), 5);
		assertTrue(s.isFavorite());
	}
	
	@Test
	void testFavorite() {
		Song s = new Song("22", "Taylor Swift", "Red");
		
		s.rate(4);
		
		assertEquals(s.getRating(), 4);
		assertFalse(s.isFavorite());
		
		s.setFavorite();
		
		assertTrue(s.isFavorite());
	}
}
