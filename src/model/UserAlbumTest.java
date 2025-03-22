package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class UserAlbumTest {
	
	UserAlbum getAlbum() {
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
		
		Album a = new Album("Please Please Me", "The Beatles", "60s Rock", "1963", arr);
		UserAlbum returnAlbum = new UserAlbum(a);
		return returnAlbum;
	}
	
	@Test
	void testBasic() {
		UserAlbum a = getAlbum();
		
		assertEquals(a.getName(), "Please Please Me");
		assertEquals(a.getArtist(), "The Beatles");
		assertEquals(a.getGenre(), "60s Rock");
		assertEquals(a.getYear(), "1963");
	}
	
	@Test
	void testList() {
		Album a = getAlbum();
		UserAlbum album = new UserAlbum(a);
		
		assertEquals(album.getSongs().get(1).getTitle(), "Misery");
	}
	
	@Test
	void testToString() {
		Album a = getAlbum();
		UserAlbum album = new UserAlbum(a);
		
		String expected = "Please Please Me\nArtist: The Beatles\nGenre:  60s Rock\nYear:   1963\n";
		
		assertEquals(album.toString(),expected);
	}
	
	@Test
	void testAddSong() {
		Album a = getAlbum();
		UserAlbum album = new UserAlbum(a);
		
		UserSong s = new UserSong("I Saw Her Standing There", "The Beatles", "Please Please Me");
		
		assertEquals(0,album.getUserSongs().size());
		
		album.addSong(s);
		
		assertEquals(1,album.getUserSongs().size());
		
		assertEquals(album.getUserSongs().get(0).getTitle(),"I Saw Her Standing There");
		
		UserSong s2 = new UserSong("Misery", "The Beatles", "Please Please Me");
		
		album.addSong(s2);
		
		assertEquals(2,album.getUserSongs().size());
		
		assertEquals(album.getUserSongs().get(1).getTitle(),"Misery");
	}
	
	@Test
	void testRemoveSongs() {
		Album a = getAlbum();
		UserAlbum album = new UserAlbum(a);
		
		UserSong s = new UserSong("I Saw Her Standing There", "The Beatles", "Please Please Me");
		
		album.addSong(s);
		
		UserSong s2 = new UserSong("Misery", "The Beatles", "Please Please Me");
		
		album.addSong(s2);
		
		assertEquals(2,album.getUserSongs().size());
		
		assertEquals(album.getUserSongs().get(1).getTitle(),"Misery");
		
		album.removeSong(s);
		assertEquals(1,album.getUserSongs().size());
		
		assertEquals(album.getUserSongs().get(0).getTitle(),"Misery");
		
		UserSong s3 = new UserSong("Chains", "The Beatles", "Please Please Me");
		album.removeSong(s3);
		
		assertEquals(1,album.getUserSongs().size());
		
		assertEquals(album.getUserSongs().get(0).getTitle(),"Misery");
	}
}
