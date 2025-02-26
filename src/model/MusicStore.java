package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MusicStore {
	/*
	 * 		Instance Variables
	 */
	ArrayList<Album> albums;
	
	
	/*
	 * 		Constructor
	 */
	public MusicStore() {
		Scanner inFile = null;
		try {
			inFile = new Scanner(new File("albums.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("no albums.txt file in folder \n make sure files are in proper directories\n");
			System.exit(1);
		}
		
		ArrayList<String> fileNames = getFileNames(inFile);
		
		this.albums = createAlbums(fileNames);
	}
 
	
	/*
	 * 		Constructor helper functions
	 */
	
	/*
	 * creates the main storage of all information
	 * in the MusicStore by going through the files
	 * in the passed list and instantiating every 
	 * song and every album 
	 * Returns the ArrayList of albums for the constructor
	 */
	private ArrayList<Album> createAlbums(ArrayList<String> files) {
		ArrayList<Album> albums = new ArrayList<>();
		Scanner currFile = null;
		
		for(String f : files) {
			// try loading in file, exit if it doesn't exist
			try {
				currFile = new Scanner(new File(f));
			} catch(FileNotFoundException e) {
				System.out.printf("Missing file: %s \n please add file and restart program to add album\n", f);
				System.exit(1);
			}
			
			// local variables to the for loop, loaded in for
			// each file
			String songInfo[]  = currFile.nextLine().split(",");
			ArrayList<Song> songArr = new ArrayList<>();
			
			// go through the rest of the lines and create song
			// array for the Album object			
			while(currFile.hasNextLine()) {
				Song temp = new Song(currFile.nextLine(), songInfo[1], songInfo[0]);
				songArr.add(temp);
			}
			

			// add a newly constructed album object from
			// information parsed from file to albums array
			albums.add(new Album(songInfo[0], songInfo[1], songInfo[2], songInfo[3], songArr));

		}
		
		return albums;
	}
	
	/*
	 * Parses the file names from the information
	 * in albums.txt
	 * Returns: ArrayList<> of Strings in the format:  "<album title>_<artist>.txt" 
	 */
	private ArrayList<String> getFileNames(Scanner file) {
		ArrayList<String> retArr = new ArrayList<>();
		
		while(file.hasNextLine()){
			String temp[] = file.nextLine().split(",");
			StringBuffer sb = new StringBuffer();
			
			sb.append(temp[0]);
			sb.append("_");
			sb.append(temp[1]);
			sb.append(".txt");
			
			retArr.add(new String(sb));
		}
		
		return retArr;
	}

	/*
	 * 		Search Methods
	 */
	public String search(String s, String songOrAlbum, String titleOrArtist) {
		if(songOrAlbum.equals("Song") && titleOrArtist.equals("Title"))
			return searchSongTitle(s);
		if(songOrAlbum.equals("Song") && titleOrArtist.equals("Artist"))
			return searchSongArtist(s);
		if(songOrAlbum.equals("Album") && titleOrArtist.equals("Title"))
			return searchAlbumTitle(s);
		if(songOrAlbum.equals("Album") && titleOrArtist.equals("Artist"))
			return searchAlbumArtist(s);
		
		return "Incorrect parameters sent, check code\n";
	}
	
	private String searchSongTitle(String val) {
		String retval = "";
		
		for(Album a : this.albums) 
			for(Song s : a.getSongs()) 
				if(val.equals(s.getTitle())) 
					retval = retval + s.toString();

		return retval;
	}
	
	private String searchSongArtist(String val) {
		String retval = "";
		
		for(Album a : this.albums)
			if(val.equals(a.getArtist()))
				for(Song s : a.getSongs())
					retval = retval + s.toString();
		
		return retval;
	}
	
	private String searchAlbumTitle(String val) {
		String retval = "";
		
		for(Album a : this.albums) {
			if(val.equals(a.getName())) {
				retval = retval + a.toString();
				for(Song s: a.getSongs())
					retval = retval + s.getTitle() + "\n";
			}
		}
		return retval;
	}
	
	private String searchAlbumArtist(String val) {
		String retval = "";
		
		for(Album a : this.albums) {
			if(val.equals(a.getArtist())) {
				retval = retval + a.toString();
				for(Song s: a.getSongs())
					retval = retval + s.getTitle() + "\n";
			}
		}
		return retval;
	}
	
	// this might just be temporary, idk if theres a real need for this
	ArrayList<Album> getAlbums(){		
		return new ArrayList<Album>(albums);
	}
	
	public boolean checkSongInStore(String songName) {
		for (Album a : albums)
			for (Song s : a.getSongs())
				if (s.getTitle().equals(songName))
					return true;
		return false;
	}
	
	public boolean checkAlbumInStore(String albumName) {
		for (Album a : albums)
			if (a.getName().equals(albumName))
				return true;
		return false;
	}
	
	ArrayList<Song> matchSongsInStore(String songName){
		ArrayList<Song> songs = new ArrayList<Song>();
		
		for (Album a : albums)
			for (Song s : a.getSongs())
				if (s.getTitle().equals(songName))
					songs.add(s);

		return songs;
	}
	
	ArrayList<Album> matchAlbumsInStore(String albumName){
		ArrayList<Album> albums = new ArrayList<Album>();
		
		for (Album a : albums)
			if (a.getName().equals(albumName))
				albums.add(a);

		return albums;
	}

}
