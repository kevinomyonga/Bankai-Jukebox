package com.bankai.jukebox.models;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlaylistTest {

    @Test
    void testPlaylistCreation() {
        Playlist playlist = new Playlist(1, "Zen State Of Mind", "Kevin Omyonga");
        assertEquals(1, playlist.getId());
        assertEquals("Zen State Of Mind", playlist.getTitle());
        assertEquals("Kevin Omyonga", playlist.getCreator());
        assertFalse(playlist.isPublic());
        assertFalse(playlist.isEditable());
        assertNull(playlist.getImageURI());
        assertEquals(new ArrayList<Song>(), playlist.getSongs());
    }

    @Test
    void testPlaylistWithImage() {
        URI imageURI = URI.create("file:///path/to/image.jpg");
        Playlist playlist = new Playlist(2, "Zen State Of Mind", "Kevin Omyonga", imageURI);
        assertEquals(imageURI, playlist.getImageURI());
    }

//    @Test
//    void testAddSong() {
//        Playlist playlist = new Playlist(3, "Test Playlist");
//        Song song1 = new Song("Song 1", "Artist 1");
//        Song song2 = new Song("Song 2", "Artist 2");
//
//        playlist.addSong(song1);
//        assertEquals(1, playlist.getSongs().size());
//        assertEquals(song1, playlist.getSong(0));
//
//        playlist.addSong(song2);
//        assertEquals(2, playlist.getSongs().size());
//        assertEquals(song2, playlist.getSong(1));
//    }

    @Test
    void testSetEditable() {
        Playlist playlist = new Playlist(4, "Editable Playlist");
        assertFalse(playlist.isEditable());
        playlist.setEditable(true);
        assertTrue(playlist.isEditable());
    }

    @Test
    void testSetPublic() {
        Playlist playlist = new Playlist(5, "Public Playlist");
        assertFalse(playlist.isPublic());
        playlist.setPublic(true);
        assertTrue(playlist.isPublic());
    }
}
