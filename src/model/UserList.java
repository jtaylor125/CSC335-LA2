package model;

import java.util.ArrayList;

public class UserList {

	/*
	 * 		Instance Variables
	 */
	
	private ArrayList<User> userList;
	private User currentUser;
	
	/*
	 * 		Contructor
	 */
	
	public UserList() {
		this.userList = new ArrayList<User>();
		this.currentUser = null;
	}
	
	public void createUser(String username, String password) {
		User newUser = new User(username,password);
		userList.add(newUser);
	}
	
	public boolean isUser(String username) {
		for (User u:userList) {
			if (u.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean userLogIn(String username, String password) {
		User user = null;
		
		for (User u:userList) {
			if (u.getUsername().equals(username)) {
				user = u;
			}
		}
		
		if (user.logIn(username, password)){
			this.currentUser = user;
			return true;
		}
		return false;		
	}
}
