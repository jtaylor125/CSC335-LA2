package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class UserManager {

	private HashMap<String, LibraryModel> database;
	private HashMap<String, String> passwords;
	
	private final byte[] salt;
	
	public UserManager() {
		Scanner userInfo;
		try {
			userInfo = new Scanner(new File("UserInformation.txt"));
			passwords = this.buildPasswords(userInfo);
		} catch (FileNotFoundException e) {
			passwords = new HashMap<String, String>();
		}
		
		database = new HashMap<String, LibraryModel>();
		for(String s : passwords.keySet()) {
			database.put(s, null);
		}
		
		this.salt = "[B@254989ff".getBytes();
	}
	
	// NEEDS COMMENT
	private HashMap<String, String> buildPasswords(Scanner inFile) {
		HashMap<String, String> retMap = new HashMap<>();
		
		String line;
		
		while(inFile.hasNextLine()) {
			line = inFile.nextLine();
			String splitLine[] = line.split(" ");
			retMap.put(splitLine[0], splitLine[1]);
		}
		
		return retMap;
	}
	
	// NEEDS COMMENT
	public boolean isUser(String username) {
		return passwords.keySet().contains(username);
	}
	
	// NEEDS COMMENT
	public LibraryModel createUser(String username, String password) {
		this.passwords.put(username, this.hashPassword(password));
		
		LibraryModel newLib = new LibraryModel();
		
		this.database.put(username, newLib);
		
		return newLib;
	}
	
	// NEEDS COMMENT
	public boolean checkCredentials(String username, String password) {
		if(!this.isUser(username))
			return false;
		
		return this.hashPassword(password).equals(this.passwords.get(username));
	}
	
	// NEEDS COMMENT
	public LibraryModel login(String username, String password, MusicStore ms) {
		if(!checkCredentials(username, password))
			return null;
		
		LibraryModel userLib = this.database.get(username);
		
		if(userLib == null) {
			userLib = this.buildLibrary(username, ms);
			this.database.put(username, userLib);
		}
		
		return userLib;
	}
	
	// NEEDS COMMENT
	private LibraryModel buildLibrary(String username, MusicStore ms) {
		LibraryModel lib = new LibraryModel();
		String fileName = "User__" + username + "__.txt";
		Scanner userFile;
		
		try {
			userFile = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			return lib;
		}
		
		String currLine = userFile.nextLine();
		
		while(!currLine.equals("Recent")) {
			String songSplit[] = currLine.split(",");
			lib.addSong(songSplit[0], songSplit[1], ms);
			
			if(songSplit[2].equals("1"))
				lib.markFavorite(songSplit[0], songSplit[1]);
			if(Integer.parseInt(songSplit[3]) > 0)
				lib.rateSong(songSplit[0], songSplit[1], Integer.parseInt(songSplit[3]));
			
			int numPlays = Integer.parseInt(songSplit[4]);	
			for(int i = 0; i < numPlays; i++)
				lib.playSong(songSplit[0], songSplit[1]);
			
			currLine = userFile.nextLine();
		}
		
		currLine = userFile.nextLine();
		while(!currLine.equals("Playlists")) {
			String splitSong[] = currLine.split(",");
			lib.setMostRecent(splitSong[0], splitSong[1]);
			
			currLine = userFile.nextLine();
		}
		
		int playlistCount = Integer.parseInt(userFile.nextLine());

		currLine = userFile.nextLine();
		String prevLine = currLine;
		currLine = userFile.nextLine();
		
		for(int i = 0; i < playlistCount; i++) {
			String currPlaylistName = null;
			while(!currLine.equals("Playlist:") && !currLine.equals("End")) {
				if(prevLine.equals("Playlist:")) {
					lib.createPlaylist(currLine);
					currPlaylistName = currLine;
				} else {
					String splitSong[] = currLine.split(",");	
					lib.addToPlaylist(splitSong[0], splitSong[1], currPlaylistName);
				}
				prevLine = currLine;
				currLine = userFile.nextLine();
			} // end while loop
			
			if(!currLine.equals("End")) {
				prevLine = currLine;
				currLine = userFile.nextLine();
			}
			
		}//end for loop
		
		return lib;
	} // end method buildLibrary
	
	// NEEDS COMMENT
	public void saveToFiles() throws IOException {
		Set<String> users = this.passwords.keySet();
		
		FileWriter userInfo = new FileWriter("UserInformation.txt", false);
		userInfo.close();
		userInfo = new FileWriter("UserInformation.txt", true);
		
		for(String s : users) {
			userInfo.write(s + " " + this.passwords.get(s) + "\n");
			LibraryModel currLib = this.database.get(s);
			
			if(currLib != null) {
				FileWriter currUserFile = new FileWriter("User__" + s + "__.txt", false);
				//DO ALL WRITING FOR CURRENT USER
				currUserFile.write(currLib.getLibrarySongInformation());
				currUserFile.write("Recents\n");
				currUserFile.write(currLib.getRecentsPlaylist());
				
				
				currUserFile.write("Playlists\n");
				//TODO: write playlist count
				//TODO: write playlists
				
				currUserFile.write("End");
				currUserFile.close();
			}		
		}
		
		//TODO
		userInfo.close();
	}
	
	// NEEDS COMMENT
	private String hashPassword(String password) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Error hashing password", e);
		}
		md.update(salt);
		
		byte[] hashedPasswordBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
		String hashedPassword = new String(hashedPasswordBytes, StandardCharsets.UTF_8);
		hashedPassword = hashedPassword.replaceAll("\\s+","");
		return hashedPassword;
	}
}
