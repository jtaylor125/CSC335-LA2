package model;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class MusicStoreTest {


	
	@Test
	void testSongTitleSearch() {
		MusicStore ms = new MusicStore();
		
		String search = ms.search("Tired", "Song", "Title");
		
		System.out.println(search);
		
		String expected = "Tired\nArtist: Adele\nAlbum:  19\n\n";
		assertEquals(search,expected);
	}
	
	@Test
	void testSongArtistSearch() {
		MusicStore ms = new MusicStore();
		
		String search = ms.search("Carol King", "Song", "Artist");
		
		String expected = "I Feel The Earth Move\n"
				+ "Artist: Carol King\n"
				+ "Album:  Tapestry\n"
				+ "\n"
				+ "So Far Away\n"
				+ "Artist: Carol King\n"
				+ "Album:  Tapestry\n"
				+ "\n"
				+ "Home Again\n"
				+ "Artist: Carol King\n"
				+ "Album:  Tapestry\n"
				+ "\n"
				+ "Beautiful\n"
				+ "Artist: Carol King\n"
				+ "Album:  Tapestry\n"
				+ "\n"
				+ "Way Over Yonder\n"
				+ "Artist: Carol King\n"
				+ "Album:  Tapestry\n"
				+ "\n"
				+ "You've Got A Friend\n"
				+ "Artist: Carol King\n"
				+ "Album:  Tapestry\n"
				+ "\n"
				+ "Where You Lead\n"
				+ "Artist: Carol King\n"
				+ "Album:  Tapestry\n"
				+ "\n"
				+ "Will You Love Me Tomorrow?\n"
				+ "Artist: Carol King\n"
				+ "Album:  Tapestry\n"
				+ "\n"
				+ "Tapestry\n"
				+ "Artist: Carol King\n"
				+ "Album:  Tapestry\n"
				+ "\n"
				+ "(You Make Me Feel Like) A Natural Woman\n"
				+ "Artist: Carol King\n"
				+ "Album:  Tapestry\n\n";
		System.out.println(search.length());
		System.out.println(search);
		System.out.println(expected.length());
		assertEquals(search,expected);
	}
	
	@Test
	void testAlbumTitleSearch() {
		MusicStore ms = new MusicStore();
		
		String search = ms.search("Sons", "Album", "Title");
		String expected = "Sons\n"
				+ "Artist: The Heavy\n"
				+ "Genre:  Rock\n"
				+ "Year:   2019\n"
				+ "Heavy for You\n"
				+ "The Thief\n"
				+ "Better as One\n"
				+ "Fire\n"
				+ "Fighting for the Same Thing\n"
				+ "Hurt Interlude\n"
				+ "Put the Hurt on Me\n"
				+ "Simple Things\n"
				+ "A Whole Lot of Love\n"
				+ "What Don't Kill You\n"
				+ "Burn Bright\n\n";
		assertEquals(search,expected);
	}
	
	@Test
	void testAlbumArtistSearch() {
		MusicStore ms = new MusicStore();
		
		String search = ms.search("The Heavy", "Album", "Artist");
		String expected = "Sons\n"
				+ "Artist: The Heavy\n"
				+ "Genre:  Rock\n"
				+ "Year:   2019\n"
				+ "Heavy for You\n"
				+ "The Thief\n"
				+ "Better as One\n"
				+ "Fire\n"
				+ "Fighting for the Same Thing\n"
				+ "Hurt Interlude\n"
				+ "Put the Hurt on Me\n"
				+ "Simple Things\n"
				+ "A Whole Lot of Love\n"
				+ "What Don't Kill You\n"
				+ "Burn Bright\n\n";
		assertEquals(search,expected);
	}
	
	@Test
	void testIncorrectParamsSearch() {
		MusicStore ms = new MusicStore();
		String search = ms.search("Tired", "S", "title");
		assertEquals(search,"Incorrect parameters sent, check code\n");
	}
	
	@Test
	void testSongInStore() {
		MusicStore ms = new MusicStore();
		assertTrue(ms.checkSongInStore("Tired"));
		assertFalse(ms.checkSongInStore("Wish You Were Here"));
	}
	
	@Test
	void testAlbumInStore() {
		MusicStore ms = new MusicStore();
		assertTrue(ms.checkAlbumInStore("21"));
		assertFalse(ms.checkAlbumInStore("Wish You Were Here"));
	}
	
	@Test
	void testMatchSongs() {
		MusicStore ms = new MusicStore();
		Song matches = ms.getSong("Tired","Adele");
		assertTrue(matches.getArtist().equals("Adele"));
		assertTrue(matches.getTitle().equals("Tired"));
	}
	
	@Test
	void testMatchAlbums() {
		MusicStore ms = new MusicStore();
		Album match = ms.getAlbum("Sons","The Heavy");
		assertTrue(match.getName().equals("Sons"));
		assertTrue(match.getArtist().equals("The Heavy"));
	
	}
	
	@Test
	void testgetSongAlbum() {
		MusicStore ms = new MusicStore();
		Song song = ms.getSong("Tired", "Adele");
		Album alb = ms.getAlbum("19", "Adele");
		
		Song wSong = ms.getSong("Tired", "Adel");
		Album wAlb = ms.getAlbum("19", "Adel");
		
		assertTrue(song.getAlbum().equals("19"));
		assertEquals(alb.getArtist(), "Adele");
		
		assertEquals(wSong, null);
		assertEquals(wAlb, null);
		
	}
	
}
