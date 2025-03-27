package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class AlbumTest {

	Album getAlbum() {
		Song t0 = new Song("I Saw Her Standing There", "The Beatles", "Please Please Me");
		Song t1 = new Song("Misery", "The Beatles", "Please Please Me");
		Song t2 = new Song("Anna (Go To Him)", "The Beatles", "Please Please Me");
		Song t3 = new Song("Chains", "The Beatles", "Please Please Me");
		Song t4 = new Song("Boys", "The Beatles", "Please Please Me");
		
		ArrayList<Song> arr = new ArrayList<>();
		arr.add(t0);
		arr.add(t1);
		arr.add(t2);
		arr.add(t3);
		arr.add(t4);
		
		return new Album("Please Please Me", "The Beatles", "60s Rock", "1963", arr);
	}
	
	@Test
	void testBasic() {
		Album a = getAlbum();
		Album b = new Album(a);
		
		assertEquals(a.getName(), "Please Please Me");
		assertEquals(a.getArtist(), "The Beatles");
		assertEquals(a.getGenre(), "60s Rock");
		assertEquals(a.getYear(), "1963");
		
		assertEquals(b.getName(), "Please Please Me");
		assertEquals(b.getArtist(), "The Beatles");
		assertEquals(b.getGenre(), "60s Rock");
		assertEquals(b.getYear(), "1963");
	}
	
	@Test
	void testList() {
		Album a = getAlbum();
		
		assertEquals(a.getSongs().get(1).getTitle(), "Misery");
	}
	
	@Test
	void testToString() {
		Album a = getAlbum();
		
		String expected = "Please Please Me\nArtist: The Beatles\nGenre:  60s Rock\nYear:   1963\n";
		
		assertEquals(a.toString(),expected);
	}
}
