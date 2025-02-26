package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class MusicStoreTest {

	@Test
	void testBasic() {
		MusicStore ms = new MusicStore();
		ArrayList<Album> albums = ms.getAlbums();
		/*
		 * for(Album a : albums) {
			System.out.println(a.toString());
			for(Song s : a.getSongs())
				System.out.println(s.toString());
		}
		 */
		
		assertTrue(albums.size() == 15);
	}
	
	@Test
	void testSongTitleSearch() {
		MusicStore ms = new MusicStore();
		
		String search = ms.search("Tired", "Song", "Title");
		
		String expected = "Tired\nArtist: Adele\nAlbum:  19\n";
		assertEquals(search,expected);
	}
	
	@Test
	void testSongArtistSearch() {
		MusicStore ms = new MusicStore();
		
		String search = ms.search("Adele", "Song", "Artist");
		
		String expected = "Daydreamer\n"
				+ "Artist: Adele\n"
				+ "Album:  19\n"
				+ "Best for Last\n"
				+ "Artist: Adele\n"
				+ "Album:  19\n"
				+ "Chasing Pavements\n"
				+ "Artist: Adele\n"
				+ "Album:  19\n"
				+ "Cold Shoulder\n"
				+ "Artist: Adele\n"
				+ "Album:  19\n"
				+ "Crazy for You\n"
				+ "Artist: Adele\n"
				+ "Album:  19\n"
				+ "Melt My Heart to Stone\n"
				+ "Artist: Adele\n"
				+ "Album:  19\n"
				+ "First Love\n"
				+ "Artist: Adele\n"
				+ "Album:  19\n"
				+ "Right as Rain\n"
				+ "Artist: Adele\n"
				+ "Album:  19\n"
				+ "Make You Feel My Love\n"
				+ "Artist: Adele\n"
				+ "Album:  19\n"
				+ "My Same\n"
				+ "Artist: Adele\n"
				+ "Album:  19\n"
				+ "Tired\n"
				+ "Artist: Adele\n"
				+ "Album:  19\n"
				+ "Hometown Glory\n"
				+ "Artist: Adele\n"
				+ "Album:  19\n"
				+ "Rolling in the Deep\n"
				+ "Artist: Adele\n"
				+ "Album:  21\n"
				+ "Rumour Has It\n"
				+ "Artist: Adele\n"
				+ "Album:  21\n"
				+ "Turning Tables\n"
				+ "Artist: Adele\n"
				+ "Album:  21\n"
				+ "Don't You Remember\n"
				+ "Artist: Adele\n"
				+ "Album:  21\n"
				+ "Set Fire to the Rain\n"
				+ "Artist: Adele\n"
				+ "Album:  21\n"
				+ "He Won't Go\n"
				+ "Artist: Adele\n"
				+ "Album:  21\n"
				+ "Take It All\n"
				+ "Artist: Adele\n"
				+ "Album:  21\n"
				+ "I'll Be Waiting\n"
				+ "Artist: Adele\n"
				+ "Album:  21\n"
				+ "One and Only\n"
				+ "Artist: Adele\n"
				+ "Album:  21\n"
				+ "Lovesong\n"
				+ "Artist: Adele\n"
				+ "Album:  21\n"
				+ "Someone Like You\n"
				+ "Artist: Adele\n"
				+ "Album:  21\n"
				+ "I Found a Boy\n"
				+ "Artist: Adele\n"
				+ "Album:  21\n";
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
				+ "Burn Bright\n";
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
				+ "Burn Bright\n";
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
		ArrayList<Song> matches = ms.matchSongsInStore("Tired");
		assertTrue(matches.size() == 1);
		assertTrue(matches.get(0).getTitle().equals("Tired"));
	}
	
	@Test
	void testMatchAlbums() {
		MusicStore ms = new MusicStore();
		ArrayList<Album> matches = ms.matchAlbumsInStore("Sons");
		System.out.println(matches.size());
		assertTrue(matches.size() == 1);
		assertTrue(matches.get(0).getName().equals("Sons"));
	
	}
	
}
