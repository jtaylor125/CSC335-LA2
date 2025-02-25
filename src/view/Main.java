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
		LibraryModel library  = new LibraryModel();
		
		System.out.println("Initializing Library...");
		MusicStore   store    = new MusicStore();
		
		System.out.println("To use the program type in a a command and press enter, or type \"Help\" and press enter to get started with a list of commands");
		
		String command = systemIn.nextLine().strip();
		while(!command.equals("Exit")) {
			if(command.equals("Help"))
				printCommandList();
			else if(command.equals("Search"))
				searchCommand(systemIn, store);
			
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
	
	public static void searchCommand(Scanner s, MusicStore ms) {
		System.out.println("Would you like to search your Library or the Music Store?");
		System.out.println("Enter \"Library\" or \"Store\" or anything else to go back");
		
		String answer = s.nextLine().strip();
		
		if(answer.equals("Library")) {
			// TODO add search functionality for Library
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
				System.out.println(searchResults);
			
		} else {
			System.out.println("Going back...");
		}
	}
}
