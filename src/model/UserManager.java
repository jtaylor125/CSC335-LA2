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
	
	// buildPasswords(Scanner) -- parses the user information from text file
	// and builds HashMap containing usernames and passwords
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
	
	// isUse(String) -- determines if the username passed is already a user
	public boolean isUser(String username) {
		return passwords.keySet().contains(username);
	}
	
	// createUser(String, String) -- builds a new LibraryModel for the user and returns
	// the Object. also stores necessary user informatiuon to associated databases
	public LibraryModel createUser(String username, String password) {
		this.passwords.put(username, this.hashPassword(password));
		
		LibraryModel newLib = new LibraryModel();
		
		this.database.put(username, newLib);
		
		return newLib;
	}
	
	// checkCredentials(String, String) --  checks to see if the password is valid
	// for the user
	public boolean checkCredentials(String username, String password) {
		if(!this.isUser(username))
			return false;
		
		return this.hashPassword(password).equals(this.passwords.get(username));
	}
	
	// login(String, String, MusicStore) -- returns the LibraryModel for the
	// specified user, building it from file if not locally loaded
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
	
	// buildLibrary -- parsees a user txt file to build a LibraryModel from
	// the txt file.
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
	
	// saveToFiles() -- stores all relevant informatrion stored in local HashMaps
	// into the UserInformation.txt and specific usaer account txt files.
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
				int count = 0;
				String writeVal = "";
				
				String playlists[] = currLib.getPlaylistNames().split("\n");
				
				for(int i = 0; i < playlists.length; i++) {
					count++;
					
					writeVal = writeVal + "Playlist:\n";
					writeVal = writeVal + playlists[i] + "\n"; 
					writeVal = writeVal + currLib.getPlaylistSongs(playlists[i]);
				}
				
				currUserFile.write(String.valueOf(count) + "\n");
				currUserFile.write(writeVal);
				currUserFile.write("End");
				currUserFile.close();
			}		
		}
		
		userInfo.close();
	}
	
	// hashPassword() -- Hashes the String representation of a users password
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
