package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class LibraryModelTest {
	
	@Test
	void testAddSong() {
		MusicStore ms = new MusicStore();
		LibraryModel library = new LibraryModel();
		
		library.addSong("Tired", "Adele", ms);
		
		assertTrue(library.checkSongInLibrary("Tired", "Adele"));
		
		library.addSong("Selva Negra", "Mana", ms);
		
		assertTrue(library.checkSongInLibrary("Selva Negra", "Mana"));
	}
	
	@Test
	void testAddAlbum() {
		MusicStore ms = new MusicStore();
		LibraryModel library = new LibraryModel();
		
		library.addAlbum("Begin Again", "Norah Jones", ms);
		
		assertTrue(library.checkSongInLibrary("Uh Oh", "Norah Jones"));
		assertTrue(library.checkSongInLibrary("Just a Little Bit", "Norah Jones"));
		assertTrue(library.checkSongInLibrary("Wintertime", "Norah Jones"));
		assertTrue(library.checkSongInLibrary("My Heart Is Full", "Norah Jones"));
		
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
		LibraryModel library = new LibraryModel();
		MusicStore ms = new MusicStore();
		library.addAlbum("Sons", "The Heavy", ms);
		String search = library.search("Sons", "Album", "Title");
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
		LibraryModel library = new LibraryModel();
		MusicStore ms = new MusicStore();
		library.addAlbum("Sons", "The Heavy", ms);
		
		String search = library.search("The Heavy", "Album", "Artist");
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
	void SongGenreSearch(){
		LibraryModel library = new LibraryModel();
		MusicStore ms = new MusicStore();
		library.addSong("Heavy for You", "The Heavy", ms);
		library.addSong("Lullaby", "OneRepublic", ms);
		
		String search = library.search("Rock", "Song", "Genre");
		String expected = "Heavy for You\n"
				+ "Artist: The Heavy\n"
				+ "Album:  Sons\n"
				+ "Lullaby\n"
				+ "Artist: OneRepublic\n"
				+ "Album:  Waking Up\n";
		assertEquals(search,expected);
	}
	
	@Test
	void testIncorrectParamsSearch() {
		LibraryModel library = new LibraryModel();
		String search = library.search("Tired", "S", "title");
		assertEquals(search,"Incorrect parameters sent, check code\n");
	}
	
	@Test
	void testGetSongTitles() {
		LibraryModel library = new LibraryModel();
		MusicStore ms = new MusicStore();
		library.addSong("Tired", "Adele", ms);
		library.addSong("Uh Oh", "Norah Jones", ms);
		library.addSong("Selva Negra", "Mana", ms);
		
		String titles = library.getSongTitles();
		
		String expected = "Tired\nUh Oh\nSelva Negra\n";
		assertEquals(titles,expected);
	}
	
	@Test
	void testGetArtists() {
		LibraryModel library = new LibraryModel();
		MusicStore ms = new MusicStore();
		library.addSong("Tired", "Adele", ms);
		library.addSong("Uh Oh", "Norah Jones", ms);
		library.addSong("Selva Negra", "Mana", ms);
		library.addSong("Ana", "Mana", ms);
		
		String artists = library.getArtists();
		
		String expected = "Adele\nNorah Jones\nMana\n";
		assertEquals(artists,expected);
	}
	
	@Test
	void testGetAlbums() {
		LibraryModel library = new LibraryModel();
		MusicStore ms = new MusicStore();
		library.addSong("Tired", "Adele", ms);
		library.addSong("Uh Oh", "Norah Jones", ms);
		library.addSong("Selva Negra", "Mana", ms);
		library.addSong("Ana", "Mana", ms);
		
		library.addAlbum("Begin Again", "Norah Jones", ms);
		library.addAlbum("Sons", "The Heavy", ms);
		
		String albums = library.getAlbums();
		
		String expected = "19\nBegin Again\nCuando Los Angeles Lloran\nSons\n";
		assertEquals(albums,expected);
	}
	
	
	@Test
	void testRemoveSong() {
		LibraryModel library = new LibraryModel();
		MusicStore ms = new MusicStore();
		library.addSong("Tired", "Adele", ms);
		library.addSong("Uh Oh", "Norah Jones", ms);
		library.addSong("Selva Negra", "Mana", ms);
		
		String titles = library.getSongTitles();
		
		String expected = "Tired\nUh Oh\nSelva Negra\n";
		
		assertEquals(titles,expected);
		
		library.removeSong("Uh Oh", "Norah Jones");
		
		String titles2 = library.getSongTitles();
		
		String expected2 = "Tired\nSelva Negra\n";
		
		assertEquals(titles2,expected2);
	}
	
	@Test
	void removeAlbum() {
		LibraryModel library = new LibraryModel();
		MusicStore ms = new MusicStore();
		library.addSong("Tired", "Adele", ms);
		library.addSong("Uh Oh", "Norah Jones", ms);
		library.addSong("Selva Negra", "Mana", ms);
		library.addSong("Ana", "Mana", ms);
		
		library.addAlbum("Begin Again", "Norah Jones", ms);
		library.addAlbum("Sons", "The Heavy", ms);
		
		String albums = library.getAlbums();
		
		String expected = "19\nBegin Again\nCuando Los Angeles Lloran\nSons\n";
		assertEquals(albums,expected);
		
		library.removeAlbum("Sons", "The Heavy");
		
		albums = library.getAlbums();
		expected = "19\nBegin Again\nCuando Los Angeles Lloran\n";
		System.out.println(albums);
		assertEquals(albums,expected);
	}
	
	@Test
	void testCreateGetPlaylists() {
		LibraryModel library = new LibraryModel();
		MusicStore ms = new MusicStore();
		library.addSong("Tired", "Adele", ms);
		library.addSong("Uh Oh", "Norah Jones", ms);
		library.addSong("Selva Negra", "Mana", ms);
		library.addSong("Ana", "Mana", ms);
		
		library.createPlaylist("Mana");
		library.createPlaylist("Other");
		
		assertEquals(0, library.playlistLength("Mana"));
		assertEquals(0, library.playlistLength("Other"));
		
		library.addToPlaylist("Selva Negra", "Mana", "Mana");
		library.addToPlaylist("Ana", "Mana", "Mana");
		
		library.addToPlaylist("Uh Oh", "Norah Jones", "Other");
		
		assertEquals(2, library.playlistLength("Mana"));
		assertEquals(1, library.playlistLength("Other"));
		
		String playlists = library.getPlaylists();
		String expected = "Playlist : Mana\n"
				+ "Selva Negra, Mana\n"
				+ "Ana, Mana\n"
				+ "\n"
				+ "\n"
				+ "Playlist : Other\n"
				+ "Uh Oh, Norah Jones\n"
				+ "\n"
				+ "\n";
		assertEquals(playlists,expected);
	}
	
	@Test
	void testRemoveFromPlaylist() {
		LibraryModel library = new LibraryModel();
		MusicStore ms = new MusicStore();
		library.addSong("Tired", "Adele", ms);
		library.addSong("Uh Oh", "Norah Jones", ms);
		library.addSong("Selva Negra", "Mana", ms);
		library.addSong("Ana", "Mana", ms);
		
		library.createPlaylist("Mana");
		library.createPlaylist("Other");
		
		assertEquals(0, library.playlistLength("Mana"));
		assertEquals(0, library.playlistLength("Other"));
		
		library.addToPlaylist("Selva Negra", "Mana", "Mana");
		library.addToPlaylist("Ana", "Mana", "Mana");
		
		library.addToPlaylist("Uh Oh", "Norah Jones", "Other");
		
		assertEquals(2, library.playlistLength("Mana"));
		assertEquals(1, library.playlistLength("Other"));
		
		library.removeFromPlaylist("Selva Negra", "Mana", "Mana");
		library.removeFromPlaylist("Uh Oh", "Norah Jones", "Other");
		
		assertEquals(1, library.playlistLength("Mana"));
		assertEquals(0, library.playlistLength("Other"));
	}
	
	@Test
	void testCheckPlaylistExistence() {
		LibraryModel library = new LibraryModel();
		MusicStore ms = new MusicStore();
		library.addSong("Tired", "Adele", ms);
		library.addSong("Uh Oh", "Norah Jones", ms);
		library.addSong("Selva Negra", "Mana", ms);
		library.addSong("Ana", "Mana", ms);
		
		assertFalse(library.checkPlaylistExistence("Mana"));
		assertFalse(library.checkPlaylistExistence("Other"));
		
		library.createPlaylist("Mana");
		library.createPlaylist("Other");
		
		assertTrue(library.checkPlaylistExistence("Mana"));
		assertTrue(library.checkPlaylistExistence("Other"));
		
		library.addToPlaylist("Selva Negra", "Mana", "Mana");
		library.addToPlaylist("Ana", "Mana", "Mana");
		
		library.addToPlaylist("Uh Oh", "Norah Jones", "Other");
		
		assertTrue(library.checkPlaylistExistence("Mana"));
		assertTrue(library.checkPlaylistExistence("Other"));
	}
	
	@Test
	void testCheckSongInPlaylist() {
		LibraryModel library = new LibraryModel();
		MusicStore ms = new MusicStore();
		library.addSong("Tired", "Adele", ms);
		library.addSong("Uh Oh", "Norah Jones", ms);
		library.addSong("Selva Negra", "Mana", ms);
		library.addSong("Ana", "Mana", ms);
		
		library.createPlaylist("Mana");
		library.createPlaylist("Other");
		
		assertFalse(library.checkSongInPlaylist("Selva Negra", "Mana", "Mana"));
		assertFalse(library.checkSongInPlaylist("Uh Oh", "Norah Jones", "Other"));
		
		library.addToPlaylist("Selva Negra", "Mana", "Mana");
		library.addToPlaylist("Ana", "Mana", "Mana");
		
		library.addToPlaylist("Uh Oh", "Norah Jones", "Other");
		
		assertTrue(library.checkSongInPlaylist("Selva Negra", "Mana", "Mana"));
		assertTrue(library.checkSongInPlaylist("Uh Oh", "Norah Jones", "Other"));
		
		assertFalse(library.checkSongInPlaylist("Uh Oh", "Norah Jones", "Final"));
	}
	
	@Test
	void testMarkGetFavorite() {
		LibraryModel library = new LibraryModel();
		MusicStore ms = new MusicStore();
		library.addSong("Tired", "Adele", ms);
		library.addSong("Uh Oh", "Norah Jones", ms);
		library.addSong("Selva Negra", "Mana", ms);
		library.addSong("Ana", "Mana", ms);
		
		library.markFavorite("Tired", "Adele");
		library.markFavorite("Ana", "Mana");
		
		String favorites = library.getFavoriteSongs();
		String expected = "Tired\nAna\n";
		assertEquals(favorites,expected);
	}
	
	@Test
	void testRating() {
		LibraryModel library = new LibraryModel();
		MusicStore ms = new MusicStore();
		library.addSong("Tired", "Adele", ms);
		library.addSong("Uh Oh", "Norah Jones", ms);
		library.addSong("Selva Negra", "Mana", ms);
		library.addSong("Ana", "Mana", ms);
		
		library.rateSong("Selva Negra", "Mana", 4);
		library.rateSong("Ana", "Mana", 5);
		library.rateSong("Tired", "Adele", 3);
		library.rateSong("Uh Oh", "Norah Jones", 1);
		
		assertEquals(library.getSongRating("Selva Negra","Mana"),4);
		assertEquals(library.getSongRating("Ana","Mana"),5);
		assertEquals(library.getSongRating("Tired","Adele"),3);
		assertEquals(library.getSongRating("Uh Oh","Norah Jones"),1);
		
		String favorites = library.getFavoriteSongs();
		String expected = "Ana\n";
		assertEquals(favorites,expected);
	}
	
	@Test
	void testSongInLibrary() {
		LibraryModel library = new LibraryModel();
		MusicStore ms = new MusicStore();
		library.addSong("Tired", "Adele", ms);
		library.addSong("Uh Oh", "Norah Jones", ms);
		
		assertTrue(library.checkSongInLibrary("Tired", "Adele"));
		assertTrue(library.checkSongInLibrary("Uh Oh", "Norah Jones"));
		assertFalse(library.checkSongInLibrary("Ana", "Mana"));
	}
}
