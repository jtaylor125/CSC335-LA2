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
	void testToString() {
		Song s = new Song("22", "Taylor Swift", "Red");
		
		String expected = "22\nArtist: Taylor Swift\nAlbum:  Red\n";
		
		assertEquals(s.toString(),expected);
	}
}
