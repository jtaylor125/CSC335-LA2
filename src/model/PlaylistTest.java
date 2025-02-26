package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlaylistTest {
	@Test
	void testAdd() {
		Playlist playlist = new Playlist("newPlaylist");
		
		Song t0 = new Song("I Saw Her Standing There", "The Beatles", "Please Please Me");
		Song t1 = new Song("Misery", "The Beatles", "Please Please Me");
		Song t2 = new Song("Anna (Go To Him)", "The Beatles", "Please Please Me");
		Song t3 = new Song("Chains", "The Beatles", "Please Please Me");
		Song t4 = new Song("Boys", "The Beatles", "Please Please Me");
		
		assertEquals(playlist.getSize(),0);
		
		playlist.add(t0);
		playlist.add(t1);
		playlist.add(t2);
		playlist.add(t3);
		playlist.add(t4);
		
		assertEquals(playlist.getSize(),5);
	}
	
	@Test
	void testName() {
		Playlist playlist = new Playlist("newPlaylist");
		
		assertEquals(playlist.getName(),"newPlaylist");
	}
	
	@Test
	void testRemove() {
		Playlist playlist = new Playlist("newPlaylist");
		
		Song t0 = new Song("I Saw Her Standing There", "The Beatles", "Please Please Me");
		Song t1 = new Song("Misery", "The Beatles", "Please Please Me");
		Song t2 = new Song("Anna (Go To Him)", "The Beatles", "Please Please Me");
		Song t3 = new Song("Chains", "The Beatles", "Please Please Me");
		Song t4 = new Song("Boys", "The Beatles", "Please Please Me");
		
		playlist.add(t0);
		playlist.add(t1);
		playlist.add(t2);
		playlist.add(t3);
		playlist.add(t4);
		
		assertEquals(playlist.getSize(),5);
		
		playlist.removeSong(t2.getTitle());
		
		assertEquals(playlist.getSize(),4);
		
		playlist.removeSong(t0.getTitle());
		playlist.removeSong(t1.getTitle());
		playlist.removeSong(t3.getTitle());
		playlist.removeSong(t4.getTitle());
		
		assertEquals(playlist.getSize(),0);
	}
	
	@Test
	void testCheckSong() {
		Playlist playlist = new Playlist("newPlaylist");
		
		Song t0 = new Song("I Saw Her Standing There", "The Beatles", "Please Please Me");
		Song t1 = new Song("Misery", "The Beatles", "Please Please Me");
		
		playlist.add(t0);
		
		assertTrue(playlist.checkSongInPlaylist("I Saw Her Standing There"));
		assertFalse(playlist.checkSongInPlaylist("Misery"));
	}
	
	@Test
	void testToString() {
		Playlist playlist = new Playlist("newPlaylist");
		
		Song t0 = new Song("I Saw Her Standing There", "The Beatles", "Please Please Me");
		Song t1 = new Song("Misery", "The Beatles", "Please Please Me");
		
		playlist.add(t0);
		playlist.add(t1);
		
		String compareString = "Playlist : newPlaylist" + "\n" + "I Saw Her Standing There, The Beatles\n" 
		+ "Misery, The Beatles\n\n";
		
		System.out.println(compareString);
		
		System.out.println(playlist.toString());
		
		assertEquals(playlist.toString(), compareString);
	}
	
	
}
