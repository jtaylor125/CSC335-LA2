package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UserListTest {
	
	@Test
	void testConstructor() {
		UserList list = new UserList();
		
		list.createUser("jtaylor125", "password");
		list.createUser("temma22", "pass");
		list.createUser("PDiddley", "word");
		
		assertTrue(list.isUser("jtaylor125"));
		assertTrue(list.isUser("temma22"));
		assertTrue(list.isUser("PDiddley"));
	}
	
	@Test
	void testOneLogin() {
		UserList list = new UserList();
		
		list.createUser("jtaylor125", "password");
		list.createUser("temma22", "pass");
		list.createUser("PDiddley", "word");
		
		list.userLogIn("jtaylor125", "password");
		
		assertTrue(list.isLoggedIn("jtaylor125"));
		assertFalse(list.isLoggedIn("temma22"));
		assertFalse(list.isLoggedIn("PDiddley"));
	}
	
	@Test
	void testMultipleLogins() {
		UserList list = new UserList();
		
		list.createUser("jtaylor125", "password");
		list.createUser("temma22", "pass");
		list.createUser("PDiddley", "word");
		
		assertFalse(list.isLoggedIn("jtaylor125"));
		assertFalse(list.isLoggedIn("temma22"));
		assertFalse(list.isLoggedIn("PDiddley"));
		
		list.userLogIn("jtaylor125", "password");
		
		assertTrue(list.isLoggedIn("jtaylor125"));
		assertFalse(list.isLoggedIn("temma22"));
		assertFalse(list.isLoggedIn("PDiddley"));
		
		list.userLogOut();
		
		list.userLogIn("temma22", "pass");
		
		assertFalse(list.isLoggedIn("jtaylor125"));
		assertTrue(list.isLoggedIn("temma22"));
		assertFalse(list.isLoggedIn("PDiddley"));
		
		list.userLogOut();
		
		list.userLogIn("PDiddley", "word");
		
		assertFalse(list.isLoggedIn("jtaylor125"));
		assertFalse(list.isLoggedIn("temma22"));
		assertTrue(list.isLoggedIn("PDiddley"));
		
		list.userLogOut();
		
		assertFalse(list.isLoggedIn("jtaylor125"));
		assertFalse(list.isLoggedIn("temma22"));
		assertFalse(list.isLoggedIn("PDiddley"));
	}
	
	@Test
	void testWrongLoginsLogouts() {
		UserList list = new UserList();
		
		list.createUser("jtaylor125", "password");
		
		assertFalse(list.userLogIn("jtaylor125", "pass"));
		
		assertFalse(list.userLogIn("NotAUser", "password"));
		
		assertFalse(list.isLoggedIn("jtaylor125"));
		
		list.userLogIn("jtaylor125", "password");
		
		assertTrue(list.userLogOut());
		
		assertFalse(list.userLogOut());
	}
}
