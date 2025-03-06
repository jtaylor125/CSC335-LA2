package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UserTest {
	
	@Test
	void testLogIn() {
		User user = new User("jtaylor125","password");
		
		assertFalse(user.logIn("jtaylor125","PASSWORD"));
		assertFalse(user.logIn("jtaylor125",""));
		assertFalse(user.logIn("jtaylor125","Password"));
		assertFalse(user.logIn("jtaylor125","pass word"));
		assertFalse(user.logIn("jtaylor125"," password "));
		assertFalse(user.logIn(" jtaylor125"," password "));
		assertFalse(user.logIn("jtaylor125 "," password "));
		assertFalse(user.logIn("wrong"," password "));
		
		assertTrue(user.logIn("jtaylor125","password"));
	}
	
	@Test
	void testShortPassword() {
		User user = new User("jtaylor125","p");
		
		assertFalse(user.logIn("jtaylor125",""));
		assertFalse(user.logIn("jtaylor125","P"));
		assertFalse(user.logIn("jtaylor125"," P "));

		assertTrue(user.logIn("jtaylor125","p"));
	}
	
	@Test
	void testLongPassword() {
		User user = new User("temma22","qwertyuiopasdfghjklzxcvbnmQWERTYUIOPLKJHMNBGVFCDXSZA`1234567890-=+_)(  *&^%$#@![]{}|;':,./<>?");
		
		assertFalse(user.logIn("temma22",""));

		assertTrue(user.logIn("temma22","qwertyuiopasdfghjklzxcvbnmQWERTYUIOPLKJHMNBGVFCDXSZA`1234567890-=+_)(  *&^%$#@![]{}|;':,./<>?"));
	}
	
	@Test
	void testTwoUsers() {
		User user1 = new User("jtaylor125","passwordJ");
		User user2 = new User("temma22","passwordT");
		
		assertFalse(user2.logIn("jtaylor125", "passwordJ"));
		assertFalse(user1.logIn("temma22", "passwordT"));
		
		assertTrue(user1.logIn("jtaylor125", "passwordJ"));
		assertTrue(user2.logIn("temma22", "passwordT"));
	}
	
	@Test
	void testAddToLibrary() {
		MusicStore ms = new MusicStore();
		
		User user = new User("ankitGARGMAN","MyFavoritePassword1975!");
		
		user.addSong("Lullaby", "Leonard Cohen", ms);
		user.addAlbum("Waking Up", "OneRepublic", ms);
		
		String search1 = user.searchLibrary("Lullaby", "Song", "Title");
		String search2 = user.searchLibrary("NotASong", "Song", "Title");
		String search3 = user.searchLibrary("Waking Up", "Album", "Title");
		String search4 = user.searchLibrary("NotAnAlbum", "Album", "Title");
		
		String expected1 = "Lullaby\n"
				+ "Artist: Leonard Cohen\n"
				+ "Album:  Old Ideas\n"
				+ "Lullaby\n"
				+ "Artist: OneRepublic\n"
				+ "Album:  Waking Up\n";
		
		String expected2 = "";
		
		String expected3 = "Waking Up\n"
				+ "Artist: OneRepublic\n"
				+ "Genre:  Rock\n"
				+ "Year:   2009\n"
				+ "Made for You\n"
				+ "All the Right Moves\n"
				+ "Secrets\n"
				+ "Everybody Loves Me\n"
				+ "Missing Persons 1 & 2\n"
				+ "Good Life\n"
				+ "All This Time\n"
				+ "Fear\n"
				+ "Waking Up\n"
				+ "Marchin On\n"
				+ "Lullaby\n";
		
		String expected4 = "";
		assertEquals(search1,expected1);
		assertEquals(search2,expected2);
		assertEquals(search3,expected3);
		assertEquals(search4,expected4);
	}
	
	@Test
	void testLogInOneUser() {
		User user = new User("ankitGARGMAN","MyFavoritePassword1975!");
		
		assertFalse(user.logOut());
		
		assertFalse(user.isLoggedIn());
		user.logIn("ankitGARGMAN", "WRONG");
		
		assertFalse(user.isLoggedIn());
		user.logIn("ankitGARGMAN", "MyFavoritePassword1975!");
		
		assertTrue(user.isLoggedIn());
		
		user.logOut();
		
		assertFalse(user.isLoggedIn());
	}
	
	@Test
	void DELETETHIS() {
		int a = 1;
		int b = 2;
		double x = 1.1;
		double y = 2.2;
		String s = "hi";
		String t = "bye";
		
		System.out.println(a + b + s);
		System.out.println(s + a + b);
		System.out.println(s + (a + b));
		System.out.println(a + x);
		System.out.println(x+y);
		System.out.println(a + s);
		System.out.println(s + y);
		System.out.println(s + t);
	}
}
