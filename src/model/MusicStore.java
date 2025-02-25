package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MusicStore {
	ArrayList<Album> albums;
	
	
	// Constructor
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
 
	
	private ArrayList<Album> createAlbums(ArrayList<String> files) {
		ArrayList<Album> albums = new ArrayList<>();
		Scanner currFile = null;
		
		for(String f : files) {
			try {
				currFile = new Scanner(new File(f));
			} catch(FileNotFoundException e) {
				System.out.printf("Missing file: %s \n please add file and restart program to add album\n", f);
				System.exit(1);
			}
			
			
			String songInfo[]  = currFile.nextLine().split(",");
			ArrayList<Song> songArr = new ArrayList<>();
			
			while(currFile.hasNextLine()) {
				Song temp = new Song(currFile.next(), songInfo[1], songInfo[0]);
				songArr.add(temp);
			}
			
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
	
	// should probably return a copy? songs are mutable?
	ArrayList<Album> getAlbums(){		
		return albums;
	}
}
