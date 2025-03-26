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
		
		assertTrue(library.removeSong("Uh Oh", "Norah Jones"));
		
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
	void testPlaylistShuffle() {
		LibraryModel library = new LibraryModel();
		MusicStore ms = new MusicStore();
		library.addSong("Tired", "Adele", ms);
		library.addSong("Uh Oh", "Norah Jones", ms);
		library.addSong("Selva Negra", "Mana", ms);
		library.addSong("Ana", "Mana", ms);
		library.addSong("Waking Up", "Leonard Cohen", ms);
		library.addSong("Waking Up", "OneRepublic", ms);
		
		
		library.createPlaylist("Mana");
		
		library.addToPlaylist("Selva Negra", "Mana", "Mana");
		library.addToPlaylist("Ana", "Mana", "Mana");
		library.addToPlaylist("Uh Oh", "Norah Jones", "Mana");
		library.addToPlaylist("Tired", "Adele", "Mana");
		library.addToPlaylist("Waking Up", "Leonard Cohen", "Mana");
		library.addToPlaylist("Waking Up", "OneRepublic", "Mana");
		
		String playlistInfo = library.getPlaylistSongs("Mana");
		library.shufflePlaylist("Mana");
		String newPlaylistInfo = library.getPlaylistSongs("Mana");
		
		assertFalse(playlistInfo.equals(newPlaylistInfo));	
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
	
	@Test
	void testGetSortedSongTitlesTitle() {
		LibraryModel library = new LibraryModel();
		MusicStore ms = new MusicStore();
		library.addSong("Tired", "Adele", ms);
		library.addSong("Uh Oh", "Norah Jones", ms);
		library.addSong("Selva Negra", "Mana", ms);
		library.addSong("Ana", "Mana", ms);
		
		String ascending = library.getSortedSongTitlesTitle("ascending");
		String descending = library.getSortedSongTitlesTitle("descending");
		
		String ascendingExpected = "Ana\n"
				+ "Selva Negra\n"
				+ "Tired\n"
				+ "Uh Oh\n";
		
		String descendingExpected = "Uh Oh\n"
				+ "Tired\n"
				+ "Selva Negra\n"
				+ "Ana\n";
		
		assertEquals(ascending,ascendingExpected);
		assertEquals(descending,descendingExpected);
	}
	
	@Test
	void testGetSortedSongTitlesArtist() {
		LibraryModel library = new LibraryModel();
		MusicStore ms = new MusicStore();
		library.addSong("Tired", "Adele", ms);
		library.addSong("Uh Oh", "Norah Jones", ms);
		library.addSong("Selva Negra", "Mana", ms);
		library.addSong("Ana", "Mana", ms);
		
		String ascending = library.getSortedSongTitlesArtist("ascending");
		String descending = library.getSortedSongTitlesArtist("descending");
		
		String ascendingExpected = "Tired\n"
				+ "Ana\n"
				+ "Selva Negra\n"
				+ "Uh Oh\n";
		
		String descendingExpected = "Uh Oh\n"
				+ "Selva Negra\n"
				+ "Ana\n"
				+ "Tired\n";
		
		assertEquals(ascending,ascendingExpected);
		assertEquals(descending,descendingExpected);
	}
	
	@Test
	void testGetSortedSongTitlesRating() {
		LibraryModel library = new LibraryModel();
		MusicStore ms = new MusicStore();
		library.addSong("Tired", "Adele", ms);
		library.addSong("Uh Oh", "Norah Jones", ms);
		library.addSong("Selva Negra", "Mana", ms);
		library.addSong("Ana", "Mana", ms);
		
		library.rateSong("Tired", "Adele", 3);
		library.rateSong("Selva Negra", "Mana", 5);
		library.rateSong("Ana", "Mana", 4);
		
		String ascending = library.getSortedSongTitlesRating("ascending");
		String descending = library.getSortedSongTitlesRating("descending");
		
		String ascendingExpected = "Uh Oh\n"
				+ "Tired\n"
				+ "Ana\n"
				+ "Selva Negra\n";
		
		String descendingExpected = "Selva Negra\n"
				+ "Ana\n"
				+ "Tired\n"
				+ "Uh Oh\n";
		
		assertEquals(ascending,ascendingExpected);
		assertEquals(descending,descendingExpected);
	}
	
	@Test
	void testRecents() {
		LibraryModel library = new LibraryModel();
		MusicStore ms = new MusicStore();
		library.addSong("Tired", "Adele", ms);
		library.addSong("Uh Oh", "Norah Jones", ms);
		library.addSong("Selva Negra", "Mana", ms);
		library.addSong("Ana", "Mana", ms);
		
		library.playSong("Uh Oh", "Norah Jones");
		library.playSong("Ana", "Mana");
		library.playSong("Selva Negra", "Mana");
		library.playSong("Selva Negra", "Mana");
		
		String recents = library.getRecentsPlaylist();
		String expected = "Selva Negra,Mana\n"
				+ "Selva Negra,Mana\n"
				+ "Ana,Mana\n"
				+ "Uh Oh,Norah Jones\n";
		
		assertEquals(recents,expected);
	}
	
	@Test
	void testFrequents() {
		LibraryModel library = new LibraryModel();
		MusicStore ms = new MusicStore();
		library.addSong("Tired", "Adele", ms);
		library.addSong("Uh Oh", "Norah Jones", ms);
		library.addSong("Selva Negra", "Mana", ms);
		library.addSong("Ana", "Mana", ms);
		
		library.playSong("Uh Oh", "Norah Jones");
		library.playSong("Ana", "Mana");
		library.playSong("Ana", "Mana");
		library.playSong("Ana", "Mana");
		library.playSong("Ana", "Mana");
		library.playSong("Ana", "Mana");
		library.playSong("Ana", "Mana");
		library.playSong("Ana", "Mana");
		library.playSong("Ana", "Mana");
		library.playSong("Ana", "Mana");
		library.playSong("Ana", "Mana");
		library.playSong("Ana", "Mana");
		library.playSong("Ana", "Mana");

		library.playSong("Selva Negra", "Mana");
		library.playSong("Selva Negra", "Mana");
		library.playSong("Selva Negra", "Mana");
		library.playSong("Selva Negra", "Mana");
		library.playSong("Selva Negra", "Mana");
		library.playSong("Selva Negra", "Mana");
		library.playSong("Selva Negra", "Mana");
		
		String frequents = library.getFrequentsPlaylist();
		String expected = "Playlist : Frequents\n"
				+ "Ana, Mana\n"
				+ "Selva Negra, Mana\n"
				+ "Uh Oh, Norah Jones\n\n";
		
		assertEquals(frequents,expected);
	}
	
	@Test
	void testBadSongInput(){
		LibraryModel library = new LibraryModel();
		MusicStore ms = new MusicStore();
		boolean val = library.addSong("Awake", "Adele", ms);
		assertFalse(val);
	}
	
	@Test
	void testDuplicateSongInput() {
		LibraryModel library = new LibraryModel();
		MusicStore ms = new MusicStore();
		library.addSong("Tired", "Adele", ms);
		boolean val = library.addSong("Tired", "Adele", ms);
		assertTrue(val);
	}
	
	@Test
	void testBadAlbumInput() {
		LibraryModel library = new LibraryModel();
		MusicStore ms = new MusicStore();
		boolean val = library.addAlbum("GDJH", "Adele", ms);
		assertFalse(val);
	}
	
	@Test
	void testGenres() {
		LibraryModel library = new LibraryModel();
		MusicStore ms = new MusicStore();
		library.addAlbum("Waking Up", "OneRepublic", ms);
		library.addAlbum("Sons", "The Heavy", ms);
		
		String genres = library.getGenrePlaylists();
		String titles = library.getSongTitles();
		String expected = "Playlist : Rock\n"
				+ "Made for You, OneRepublic\n"
				+ "All the Right Moves, OneRepublic\n"
				+ "Secrets, OneRepublic\n"
				+ "Everybody Loves Me, OneRepublic\n"
				+ "Missing Persons 1 & 2, OneRepublic\n"
				+ "Good Life, OneRepublic\n"
				+ "All This Time, OneRepublic\n"
				+ "Fear, OneRepublic\n"
				+ "Waking Up, OneRepublic\n"
				+ "Marchin On, OneRepublic\n"
				+ "Lullaby, OneRepublic\n"
				+ "Heavy for You, The Heavy\n"
				+ "The Thief, The Heavy\n"
				+ "Better as One, The Heavy\n"
				+ "Fire, The Heavy\n"
				+ "Fighting for the Same Thing, The Heavy\n"
				+ "Hurt Interlude, The Heavy\n"
				+ "Put the Hurt on Me, The Heavy\n"
				+ "Simple Things, The Heavy\n"
				+ "A Whole Lot of Love, The Heavy\n"
				+ "What Don't Kill You, The Heavy\n"
				+ "Burn Bright, The Heavy\n\n";
		
		assertEquals(genres,expected);
	}
	
	@Test
	void testRemoveSongBadInput() {
		LibraryModel library = new LibraryModel();
		MusicStore ms = new MusicStore();
		library.addSong("Tired", "Adele", ms);
		
		boolean val = library.removeSong("Awake", "Adele");
		assertFalse(val);
		
		boolean val2 = library.removeSong("Tired", "Adele");
		assertTrue(val2);
	}
	
	@Test
	void testGetLibrarySongInfo() {
		LibraryModel library = new LibraryModel();
		MusicStore ms = new MusicStore();
		library.addAlbum("Waking Up", "OneRepublic", ms);
		library.addAlbum("Sons", "The Heavy", ms);
		
		String info = library.getLibrarySongInformation();
		String expected = "Made for You,OneRepublic,0,0,0\n"
				+ "All the Right Moves,OneRepublic,0,0,0\n"
				+ "Secrets,OneRepublic,0,0,0\n"
				+ "Everybody Loves Me,OneRepublic,0,0,0\n"
				+ "Missing Persons 1 & 2,OneRepublic,0,0,0\n"
				+ "Good Life,OneRepublic,0,0,0\n"
				+ "All This Time,OneRepublic,0,0,0\n"
				+ "Fear,OneRepublic,0,0,0\n"
				+ "Waking Up,OneRepublic,0,0,0\n"
				+ "Marchin On,OneRepublic,0,0,0\n"
				+ "Lullaby,OneRepublic,0,0,0\n"
				+ "Heavy for You,The Heavy,0,0,0\n"
				+ "The Thief,The Heavy,0,0,0\n"
				+ "Better as One,The Heavy,0,0,0\n"
				+ "Fire,The Heavy,0,0,0\n"
				+ "Fighting for the Same Thing,The Heavy,0,0,0\n"
				+ "Hurt Interlude,The Heavy,0,0,0\n"
				+ "Put the Hurt on Me,The Heavy,0,0,0\n"
				+ "Simple Things,The Heavy,0,0,0\n"
				+ "A Whole Lot of Love,The Heavy,0,0,0\n"
				+ "What Don't Kill You,The Heavy,0,0,0\n"
				+ "Burn Bright,The Heavy,0,0,0\n";
		assertEquals(info,expected);
	}
	
	@Test
	void testLibraryShuffle(){
		LibraryModel library = new LibraryModel();
		MusicStore ms = new MusicStore();
		library.addAlbum("Waking Up", "OneRepublic", ms);
		library.addAlbum("Sons", "The Heavy", ms);
		
		String shuffle1 = library.getShuffledSongTitles();
		library.shuffleLibrary();
		String shuffle2 = library.getShuffledSongTitles();
		assertFalse(shuffle1.equals(shuffle2));
	}
	
	@Test
	void testGetPlaylistsNames() {
		LibraryModel library = new LibraryModel();
		MusicStore ms = new MusicStore();
		library.addSong("Tired", "Adele", ms);
		library.addSong("Uh Oh", "Norah Jones", ms);
		library.addSong("Selva Negra", "Mana", ms);
		library.addSong("Ana", "Mana", ms);
		
		library.createPlaylist("Mana");
		library.createPlaylist("Other");
		
		
		library.addToPlaylist("Selva Negra", "Mana", "Mana");
		library.addToPlaylist("Ana", "Mana", "Mana");
		library.addToPlaylist("Uh Oh", "Norah Jones", "Other");
		
		String names = library.getPlaylistNames();
		String expected = "Mana\n"
				+ "Other\n";
		assertEquals(names,expected);
	}
	
	@Test
	void testGetPlaylistsSongs() {
		LibraryModel library = new LibraryModel();
		MusicStore ms = new MusicStore();
		library.addSong("Tired", "Adele", ms);
		library.addSong("Uh Oh", "Norah Jones", ms);
		library.addSong("Selva Negra", "Mana", ms);
		library.addSong("Ana", "Mana", ms);
		
		library.createPlaylist("Mana");
		library.createPlaylist("Other");
		
		
		library.addToPlaylist("Selva Negra", "Mana", "Mana");
		library.addToPlaylist("Ana", "Mana", "Mana");
		library.addToPlaylist("Uh Oh", "Norah Jones", "Other");
		
		String names = library.getPlaylistSongs("Mana");
		System.out.println(names);
		String expected = "Selva Negra,Mana\n"
				+ "Ana,Mana\n";
		assertEquals(names,expected);
	}
	
	@Test
	void testGetPlaylistsShuffle() {
		LibraryModel library = new LibraryModel();
		MusicStore ms = new MusicStore();
		library.addSong("Tired", "Adele", ms);
		library.addSong("Uh Oh", "Norah Jones", ms);
		library.addSong("Selva Negra", "Mana", ms);
		library.addSong("Ana", "Mana", ms);
		
		library.createPlaylist("Mana");
		library.createPlaylist("Other");
		
		
		library.addToPlaylist("Selva Negra", "Mana", "Mana");
		library.addToPlaylist("Ana", "Mana", "Mana");
		library.addToPlaylist("Uh Oh", "Norah Jones", "Other");
		
		String shuffle1 = library.getPlaylistShuffleSongs("Mana");
		library.shufflePlaylist("Mana");
		String shuffle2 = library.getPlaylistShuffleSongs("Mana");
		assertFalse(shuffle1.equals(shuffle2));
	}
}
