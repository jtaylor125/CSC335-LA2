package view;

import java.util.Scanner;

import model.LibraryModel;
import model.MusicStore;

public class Main {
	public static void main(String[] args) {
		Scanner      systemIn = new Scanner(System.in);
		
		System.out.println("Welcome to the Command-Line based Music Library");
		System.out.println("Written by Jacob Taylor and Tristan Emma");
		
		
		
		System.out.println("Loading Music Store...");
		MusicStore   store    = new MusicStore();
				
		System.out.println("Initializing Library...");
		LibraryModel library  = new LibraryModel();
		
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
		System.out.println("Allows you to favorite a song from thee Library");
		
		System.out.print("Rate:        ");
		System.out.println("Allows you to rate a song from the Library on a scale of 1-5");
	}
	
	public static void searchCommand(Scanner s, MusicStore ms, LibraryModel library) {
		System.out.println("Would you like to search your Library or the Music Store?");
		System.out.println("Enter \"Library\" or \"Store\" or anything else to go back");
		
		String answer = s.nextLine().strip();
		
		if(answer.equals("Library")) {
			System.out.println("Would you like to search Songs or Albums?");
			System.out.println("Enter \"Song\" or \"Album\"");
			String songOrAlbum = s.nextLine().strip();
			
			System.out.println("Would you like to search by Title or Artist?");
			System.out.println("Enter \"Title\" or \"Artist\"");
			String titleOrArtist = s.nextLine().strip();
			
			System.out.printf("Enter the %s of the %s to search for \n", titleOrArtist, songOrAlbum);
			String key = s.nextLine().strip();
			
			String searchResults = library.search(key, songOrAlbum, titleOrArtist);
			
			if("".equals(searchResults))
				System.out.printf("No %ss of %s found\n", songOrAlbum, titleOrArtist);
			else
				System.out.print(searchResults);
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
			System.out.println("What song would you like to add? (Ex: \"Tired\"");
			
			String song = s.nextLine().strip();
			
			if (ms.checkSongInStore(song)) {
				if (library.getSongTitles().contains(song)) {
					System.out.println("Song already in library!");
				} else {
					System.out.println("Who is the artist?");
					String artist = s.nextLine().strip();
					library.addSong(song, artist,ms);
					System.out.println("Successfully added");
				}
			} else {
				System.out.printf("%s not found in store\n", song);
			}
		} else if (answer.equals("Album")) {
			System.out.println("What album would you like to add? (Ex: \"Begin Again\"");
			
			String album = s.nextLine().strip();
			
			if (ms.checkAlbumInStore(album)) {
				if (library.getAlbums().contains(album)) {
					System.out.println("Album already in library!");
				} else {
					System.out.println("Who is the artist?");
					String artist = s.nextLine().strip();
					library.addSong(album, artist,ms);
					System.out.println("Successfully added");
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
			System.out.println(library.getSongTitles());
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
				library.createPlaylist(name);
				System.out.println("Playlist created");
			} else {
				System.out.println("No name entered");
			}
		} else if (answer.equals("Add Song")) {
			System.out.println("Enter the playlist you want to add to:");
			String playlistName = s.nextLine().strip();
			
			if (library.checkPlaylistExistence(playlistName)) {
				System.out.println("Enter the song you want to add:");
				String songName = s.nextLine().strip();
				
				if (library.checkSongInLibrary(songName)) {
					library.addToPlaylist(songName, playlistName);
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
				
				if (library.checkSongInLibrary(songName)) {
					if (library.checkSongInPlaylist(songName, playlistName)) {
						library.removeFromPlaylist(songName, playlistName);
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
		System.out.println("What song would you like to favorite? (Example: \"Tired\")");
		System.out.println("Enter anything else to go back");
		
		String answer = s.nextLine().strip();
		
		if (library.checkSongInLibrary(answer)) {
			library.markFavorite(answer);
			System.out.println("Favorites updated");
		} else {
			System.out.println("Song not found");
			System.out.println("Going back...");
		}
	}
	
	public static void rateCommand(Scanner s, LibraryModel library) {
		System.out.println("What song would you like to rate? (Example: \"Tired\")");
		System.out.println("Enter anything else to go back");
		
		String answer = s.nextLine().strip();
		
		if (library.checkSongInLibrary(answer)) {
			System.out.printf("What would you like to rate %s? Enter an integer from 1 to 5",answer);
			System.out.println("");
			
			String rating = s.nextLine().strip();
			try {
				int ratingInt = Integer.parseInt(rating);
				if (ratingInt < 1 || ratingInt > 5) {
					System.out.println("Input was not between 1 and 5");
				} else {
					library.rateSong(answer, ratingInt);
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
}
