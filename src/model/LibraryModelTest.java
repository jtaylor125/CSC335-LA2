package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class LibraryModelTest {
	
	@Test
	void testAddSong() {
		MusicStore ms = new MusicStore();
		LibraryModel library = new LibraryModel();
		
		library.addSong("Tired", "Adele", ms);
		
		assertTrue(library.checkSongInLibrary("Tired"));
		
		library.addSong("Selva Negra", "Mana", ms);
		
		assertTrue(library.checkSongInLibrary("Selva Negra"));
	}
	
	@Test
	void testAddAlbum() {
		MusicStore ms = new MusicStore();
		LibraryModel library = new LibraryModel();
		
		library.addAlbum("Begin Again", "Norah Jones", ms);
		
		assertTrue(library.checkSongInLibrary("Uh Oh"));
		assertTrue(library.checkSongInLibrary("Just a Little Bit"));
		assertTrue(library.checkSongInLibrary("Wintertime"));
		assertTrue(library.checkSongInLibrary("My Heart Is Full"));
		
	}
	
	@Test
	void testSongTitleSearch() {
		MusicStore ms = new MusicStore();
		LibraryModel library = new LibraryModel();
		
		library.addSong("Tired", "Adele", ms);
		
		String search = library.search("Tired", "Song", "Title");
		
		String expected = "Tired\nArtist: Adele\nAlbum:  19\n";
		assertEquals(search,expected);
	}
	
	@Test
	void testSongArtistSearch() {
		MusicStore ms = new MusicStore();
		LibraryModel library = new LibraryModel();
		
		library.addSong("Daydreamer", "Adele", ms);
		library.addSong("Best for Last", "Adele", ms);
		library.addSong("Chasing Pavements", "Adele", ms);
		
		String search = library.search("Adele", "Song", "Artist");
		
		String expected = "Daydreamer\n"
				+ "Artist: Adele\n"
				+ "Album:  19\n"
				+ "Best for Last\n"
				+ "Artist: Adele\n"
				+ "Album:  19\n"
				+ "Chasing Pavements\n"
				+ "Artist: Adele\n"
				+ "Album:  19\n";
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
}
