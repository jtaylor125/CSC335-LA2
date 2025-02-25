package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class MusicStoreTest {

	@Test
	void testBasic() {
		MusicStore ms = new MusicStore();
		ArrayList<Album> albums = ms.getAlbums();
		
		for(Album a : albums) {
			System.out.println(a.toString());
			for(Song s : a.getSongs())
				System.out.println(s.toString());
		}
	}

}
