package view;

/*
 * File:	Main.java
 * Project: LA1-MusicLibrary
 * Authors:	Jacob Taylor and Tristan Emma
 * Editors:	Tristan Emma and Jacob Taylor
 * Purpose:	Contains view functionality for project
 * 			The main method builds the MusicStore and
 * 			creates the LibraryModel, then loops over
 * 			user inputed commands until the Exit command
 * 			is called.
 * 
 * Overview: Search Add LIst, Playlist functions, favorite/rate
 * 			 The command interface accepts "Search" to search
 * 			 either the library or the store for songs and albums
 * 			 "Add" allows the use to add songs or albums to the 
 * 			 library. Typing "List" lists the names of whatever
 * 			 object is further specified. "Playlist" opens playlist
 * 			 actions create, add song and remove song. Finally,
 * 			 "Favorite" and "Rate" allow for their respective
 * 			 actions towards songs.
 */

import java.util.Scanner;

import model.LibraryModel;
import model.MusicStore;
import model.UserManager;

public class Main {
	public static void main(String[] args) {
		Scanner      systemIn = new Scanner(System.in);
		
		System.out.println("Welcome to the Command-Line based Music Library");
		System.out.println("Written by Jacob Taylor and Tristan Emma");
		
		
		
		System.out.println("Loading Music Store...");
		MusicStore   store    = new MusicStore();
				

		System.out.println("Loading user database...");
		UserManager userMan = new UserManager();
		boolean doNotExit = true;;
		
		while(doNotExit) {
			System.out.println("To login to an existing account, type: \"Login\"");
			System.out.println("To create a new account, type: \"Create\"");
			System.out.println("To exit, type: \"Exit\"");
			String input = systemIn.nextLine().strip();
			
			if(input.equals("Exit")) {
				doNotExit = false;
			} else if (input.equals("Create")) {
				boolean exitIfElseBlock = false;
				System.out.println("Please enter a username (with no spaces):");
				String username = systemIn.next();
				while(userMan.isUser(username) && !exitIfElseBlock) {
					System.out.println("Username already in use, pick a different one:");
					username = systemIn.next();
					if(username.equals("Exit"))
						exitIfElseBlock = true;
				}
				
				if(!exitIfElseBlock) {
					System.out.println("Please enter a password (with no spaces):");
					String password = systemIn.next();
				
					runCommandLoop(systemIn, store, userMan.createUser(username, password));
				}
			} else if (input.equals("Login")) {
				boolean exitIfElseBlock = false;
				System.out.println("Please enter your username:");
				String username = systemIn.next();
				while(!userMan.isUser(username) && !exitIfElseBlock) {
					System.out.println("Username doesnt exist, try again or type \"Exit\":");
					username = systemIn.next();
					if(username.equals("Exit"))
						exitIfElseBlock = true;
				}
				
				if(!exitIfElseBlock) {
					System.out.println("Please enter your password:");
					String password = systemIn.next();
					
					while(!userMan.checkCredentials(username, password) && !exitIfElseBlock) {
						System.out.println("Incorrect password, try again or type \"Exit\"");
						password = systemIn.next();
						
						if(password.equals("Exit"))
							exitIfElseBlock = true;
					}
					
					if(!exitIfElseBlock)
						runCommandLoop(systemIn, store, userMan.login(username, password, store));
				}
			} else {
				System.out.println("Command not recognized, try again");
			} // end if else block
		} // end login while loop
	} // end main method
	
	public static void runCommandLoop(Scanner systemIn, MusicStore store, LibraryModel library) {
		System.out.println("To use the program type in a a command and press enter, or type \"Help\" and press enter to get started with a list of commands");
		
		String command = systemIn.nextLine().strip();
		while(!command.equals("Exit")) {
			if(command.equals("Help"))
				printCommandList();
			else if(command.equals("Search"))
				searchCommand(systemIn, store, library);
			else if (command.equals("Add"))
				addCommand(systemIn, store, library);
			else if (command.equals("List"))
				listCommand(systemIn, library);
			else if (command.equals("Playlist"))
				playlistCommand(systemIn, library);
			else if (command.equals("Favorite"))
				favoriteCommand(systemIn, library);
			else if (command.equals("Rate"))
				rateCommand(systemIn, library);
			else if (command.equals("Play"))
				playCommand(systemIn, library);
			else if (command.equals("Shuffle")) 
				shuffleCommand(systemIn, library);
			else if (command.equals("Remove"))
				removeCommand(systemIn, library);
			else
				System.out.println("Unknown Command");
			
			System.out.println("Ready for next command:");
			command = systemIn.nextLine();
		}
	}

	
	public static void printCommandList() {
		System.out.print("Help:        ");
		System.out.println("Prints out list of commands.");
		
		System.out.print("Search:      ");
		System.out.println("Allows you to search the Library or the Music Store for Songs Albums and Playlists by Artist or Title");
		
		System.out.print("Add:         ");
		System.out.println("Allows you to add Songs or Albums to the Library");
		
		System.out.print("List:        ");
		System.out.println("Will print out a list of the songs, artists, albums, playlists, or favorited songs");
		
		System.out.print("Playlist:    ");
		System.out.println("Allows you to create a new playlist, or add and remove songs from a playlist");
		
		System.out.print("Favorite:    ");
		System.out.println("Allows you to favorite a song from the Library");
		
		System.out.print("Rate:        ");
		System.out.println("Allows you to rate a song from the Library on a scale of 1-5");
		
		System.out.print("Play:        ");
		System.out.println("Allows you to play a song (and shuffle) songs and playlists");
		
		System.out.print("Remove:      ");
		System.out.println("Allows you to remove a song or album from the Library");
	}
	
	public static void searchCommand(Scanner s, MusicStore ms, LibraryModel library) {
		System.out.println("Would you like to search your Library or the Music Store?");
		System.out.println("Enter \"Library\" or \"Store\" or anything else to go back");
		
		String answer = s.nextLine().strip();
		
		if(answer.equals("Library")) {
			System.out.println("Would you like to search Songs or Albums?");
			System.out.println("Enter \"Song\" or \"Album\"");
			String songOrAlbum = s.nextLine().strip();
			
			System.out.println("Would you like to search by Title or Artist or Genre?");
			System.out.println("Enter \"Title\" or \"Artist\" or \"Genre\"");
			String titleOrArtist = s.nextLine().strip();
			
			System.out.printf("Enter the %s of the %s to search for \n", titleOrArtist, songOrAlbum);
			String key = s.nextLine().strip();
			
			String searchResults = library.search(key, songOrAlbum, titleOrArtist);
			
			if("".equals(searchResults))
				System.out.printf("No %ss of %s found\n", songOrAlbum, titleOrArtist);
			else {
				System.out.print(searchResults);
				
				System.out.println("If you would like to see more information about a song, type \"Info\" anything else will return");
				String moreInfo = s.nextLine().strip();;
				
				if(moreInfo.equals("Info")) {
					System.out.println("Enter Song Title from Search:");
					String songTitle = s.nextLine().strip();
					System.out.println("Enter Song Artist from Search:");
					String songArtist = s.nextLine().strip();
					
					moreInfo = library.getSongInfo(songTitle, songArtist);
					
					if(moreInfo.equals(""))
						System.out.println("No song found");
					else
						System.out.println(moreInfo);
				}
			}
		} else if (answer.equals("Store")) {
			System.out.println("Would you like to search Songs or Albums?");
			System.out.println("Enter \"Song\" or \"Album\"");
			String songOrAlbum = s.nextLine().strip();
			
			System.out.println("Would you like to search by Title or Artist?");
			System.out.println("Enter \"Title\" or \"Artist\"");
			String titleOrArtist = s.nextLine().strip();
			
			System.out.printf("Enter the %s of the %s to search for \n", titleOrArtist, songOrAlbum);
			String key = s.nextLine().strip();
			
			String searchResults = ms.search(key, songOrAlbum, titleOrArtist);
			
			if("".equals(searchResults))
				System.out.printf("No %ss of %s found\n", songOrAlbum, titleOrArtist);
			else
				System.out.print(searchResults);
			
		} else {
			System.out.println("Going back...");
		}
	}
	
	public static void addCommand(Scanner s, MusicStore ms, LibraryModel library) {
		System.out.println("Would you like to add a Song or an Album?");
		System.out.println("Enter \"Song\" or \"Album\" or anything else to go back");
		
		String answer = s.nextLine().strip();
		
		if (answer.equals("Song")) {
			System.out.println("What song would you like to add? (Ex: \"Tired\")");
			
			String song = s.nextLine().strip();
			
			if (ms.checkSongInStore(song)) {
				System.out.println("Who is the artist?");
				String artist = s.nextLine().strip();
				
				if (library.checkSongInLibrary(song,artist)) {
					System.out.println("Song already in library!");
				} else {
					if (library.addSong(song, artist,ms)) {
						System.out.println("Successfully added");
					} else {
						System.out.println("Song not found.");
					}
				}
			} else {
				System.out.printf("%s not found in store\n", song);
			}
		} else if (answer.equals("Album")) {
			System.out.println("What album would you like to add? (Ex: \"Begin Again\")");
			
			String album = s.nextLine().strip();
			
			if (ms.checkAlbumInStore(album)) {
				if (library.getAlbums().contains(album)) {
					System.out.println("Album already in library!");
				} else {
					System.out.println("Who is the artist?");
					String artist = s.nextLine().strip();
					if (library.addAlbum(album, artist,ms)) {
						System.out.println("Successfully added");
					} else {
						System.out.println("Album not found.");
					}
				}
			} else {
				System.out.printf("%s not found in store\n", album);
			}
		} else {
			System.out.println("Going back...");
		}
	}
	
	public static void listCommand(Scanner s, LibraryModel library) {
		System.out.println("What would you like to list?");
		System.out.println("Enter \"Songs\", \"Artists\", \"Albums\", \"Playlists\", \"Favorites\", or anything else to go back");
		
		String answer = s.nextLine().strip();
		
		if (answer.equals("Songs")) {
			System.out.println("What would you like the songs sorted by?");
			System.out.println("Enter \"Title\" \"Artist\" or \"Rating\" or anything else to go back");
			answer = s.nextLine().strip();
			
			if(answer.equals("Title"))
				System.out.println(library.getSortedSongTitlesTitle("Ascending"));
			else if (answer.equals("Artist"))
				System.out.println(library.getSortedSongTitlesArtist("Ascending"));
			else if (answer.equals("Rating"))
				System.out.println(library.getSortedSongTitlesRating("Ascending"));
			else 
				System.out.println("Going back...");
		} else if (answer.equals("Artists")) {
			System.out.println(library.getArtists());
		} else if (answer.equals("Albums")) {
			System.out.println(library.getAlbums());
		} else if (answer.equals("Playlists")) {
			System.out.println(library.getPlaylists());
		} else if (answer.equals("Favorites")) {
			System.out.println(library.getFavoriteSongs());
		} else {
			System.out.println("Going back...");
		}
	}
	
	public static void playlistCommand(Scanner s, LibraryModel library) {
		System.out.println("Which playlist action do you want to take?");
		System.out.println("Enter \"Create\", \"Add Song\", \"Remove Song\", or anything else to go back");
		
		String answer = s.nextLine().strip();
		
		if (answer.equals("Create")) {
			System.out.println("Enter your new playlist's name:");
			String name = s.nextLine().strip();
			
			if (name.length() != 0) {
				if (library.createPlaylist(name)) {
					System.out.println("Playlist created");
				} else {
					System.out.println("Playlist already exists, choose a different name");
				}
			} else {
				System.out.println("No name entered");
			}
		} else if (answer.equals("Add Song")) {
			System.out.println("Enter the playlist you want to add to:");
			String playlistName = s.nextLine().strip();
			
			if (library.checkPlaylistExistence(playlistName)) {
				System.out.println("Enter the song you want to add:");
				String songName = s.nextLine().strip();
				
				System.out.println("Enter the artist of the song:");
				String artist = s.nextLine().strip();
				
				if (library.checkSongInLibrary(songName, artist)) {
					if (library.addToPlaylist(songName, artist, playlistName)) {
						System.out.println("Successfully added");
					} else {
						System.out.println("Playlist not found, check spelling");
					}
				} else {
					System.out.println("Song not found");
				}
			} else {
				System.out.println("Playlist not found");
			}
		} else if (answer.equals("Remove Song")) {
			System.out.println("Enter the playlist you want to remove from:");
			String playlistName = s.nextLine().strip();
			
			if (library.checkPlaylistExistence(playlistName)) {
				System.out.println("Enter the song you want to remove:");
				String songName = s.nextLine().strip();
				
				System.out.println("Enter the artist of the song:");
				String artist = s.nextLine().strip();
				
				if (library.checkSongInLibrary(songName, artist)) {
					if (library.checkSongInPlaylist(songName, artist, playlistName)) {
						if (library.removeFromPlaylist(songName, artist, playlistName)) {
							System.out.println("Successfully removed");
						} else {
							System.out.println("Playlist not found, check spelling");
						}
					} else {
						System.out.println("Song not found in playlist");
					}
				} else {
					System.out.println("Song not found in library");
				}
			} else {
				System.out.println("Playlist not found");
			}
		} else {
			System.out.println("Going back...");
		}
	}
	
	public static void favoriteCommand(Scanner s, LibraryModel library) {
		System.out.println("What is the name of the song would you like to favorite? (Example: \"Tired\")");
		String songName = s.nextLine().strip();
		
		System.out.println("Enter the artist of the song:");
		String artist = s.nextLine().strip();
		
		if (library.checkSongInLibrary(songName, artist)) {
			library.markFavorite(songName, artist);
			System.out.println("Favorites updated");
		} else {
			System.out.println("Song not found");
			System.out.println("Going back...");
		}
	}
	
	public static void rateCommand(Scanner s, LibraryModel library) {
		System.out.println("What song would you like to rate? (Example: \"Tired\")");
		String songName = s.nextLine().strip();
		
		System.out.println("Enter the artist of the song:");
		String artist = s.nextLine().strip();
		
		if (library.checkSongInLibrary(songName, artist)) {
			System.out.printf("What would you like to rate %s? Enter an integer from 1 to 5", songName);
			System.out.println("");
			
			String rating = s.nextLine().strip();
			try {
				int ratingInt = Integer.parseInt(rating);
				if (ratingInt < 1 || ratingInt > 5) {
					System.out.println("Input was not between 1 and 5");
				} else {
					library.rateSong(songName, artist, ratingInt);
					System.out.println("Rating updated");
				}
				
			} catch (NumberFormatException e) {
				System.out.println("Input was not an integer");
			}
		} else {
			System.out.println("Song not found");
			System.out.println("Going back...");
		}
	}
	
	public static void removeCommand(Scanner s, LibraryModel library) {
		System.out.println("Would you like to remove a Song or an Album?");
		System.out.println("Enter \"Song\" or \"Album\"");
		String songOrAlbum = s.nextLine().strip();
		
		System.out.printf("What %s would you like to remove?\n", songOrAlbum);
		String songName = s.nextLine().strip();
		
		System.out.printf("Enter the artist of the %s:\n", songOrAlbum);
		String artist = s.nextLine().strip();
		
		if(songOrAlbum.equals("Song")) {
			boolean worked = library.removeSong(songName, artist);
			if(!worked)
				System.out.println("Song not found, removal failed");
		} else if (songOrAlbum.equals("Album")) {
			boolean worked = library.removeAlbum(songName, artist);
			if(!worked)
				System.out.println("Album not found, removal failed");
		} else {
			System.out.printf("%s not removable, try \"Song\" or \"Album\" \n");
			System.out.println("returning...");
		}
	}
	
	public static void playCommand(Scanner s, LibraryModel library) {
		System.out.println("Would you like to search for a song or a playlist");
		System.out.println("Enter \"Song\" or \"Playlist\"");
		String songOrPlaylist = s.nextLine().strip();
		
		if(songOrPlaylist.equals("Song")) {
			System.out.println("Enter title of song");
			String songName = s.nextLine().strip();
			
			System.out.println("Enter artist");
			String artist = s.nextLine().strip();
			
			if(library.checkSongInLibrary(songName, artist))
				library.playSong(songName, artist);
			else
				System.out.printf("Song \"%s\" by \"%s\"not found \n", songName, artist);
		} else if(songOrPlaylist.equals("Playlist")) {
			String playlistName = s.nextLine().strip();
			if(library.checkPlaylistExistence(playlistName))
				library.playPlaylist(playlistName);
			else
				System.out.printf("Playlist \"%s\" not found \n", playlistName);
		} else {
			System.out.printf("%s not usable\n", songOrPlaylist);
			System.out.println("returning...");
		}
	}
	
	public static void shuffleCommand(Scanner s, LibraryModel library) {
		System.out.println("Would you like to shuffle the library or a playlist");
		System.out.println("Enter \"Library\" or \"Playlist\"");
		String libOrPlaylist = s.nextLine().strip();
		
		if(libOrPlaylist.equals("Library")) {
			
		} else if (libOrPlaylist.equals("Playlist")) {
			
		} else {
			System.out.printf("%s not shufflable\n", libOrPlaylist);
		}
	}
}
