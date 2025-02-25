package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MusicStore {
	/*
	 * 		Setters
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
			String songInfo[]  = currFile.next().split(",");
			ArrayList<Song> songArr = new ArrayList<>();
			
			// go through the rest of the lines and create song
			// array for the Album object
			while(currFile.hasNext()) {
				Song temp = new Song(currFile.next(), songInfo[1], songInfo[0]);
				songArr.add(temp);
			}
			
			// add a newly constructed album object from
			// information parsed from file to albums array
			albums.add(new Album(songInfo[0], songInfo[1], songInfo[2], songInfo[4], songArr));
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
		
		while(file.hasNext()){
			String temp[] = file.next().split(",");
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
	public void search(String s, String songOrAlbum, String titleOrArtist) {
		return;
	}
	
}
