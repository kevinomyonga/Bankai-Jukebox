package com.bankai.jukebox.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RadioStationTest {

    @Test
    public void testSetTitle() {
        RadioStation radioStation = new RadioStation();
        radioStation.setTitle("Test Title");
        assertEquals("Test Title", radioStation.getTitle());
    }

    @Test
    public void testSetDescription() {
        RadioStation radioStation = new RadioStation();
        radioStation.setDescription("Test Description");
        assertEquals("Test Description", radioStation.getDescription());
    }

    @Test
    public void testSetGenre() {
        RadioStation radioStation = new RadioStation();
        radioStation.setGenre("Test Genre");
        assertEquals("Test Genre", radioStation.getGenre());
    }

    @Test
    public void testSetCountry() {
        RadioStation radioStation = new RadioStation();
        radioStation.setCountry("Test Country");
        assertEquals("Test Country", radioStation.getCountry());
    }

    @Test
    public void testSetLanguage() {
        RadioStation radioStation = new RadioStation();
        radioStation.setLanguage("Test Language");
        assertEquals("Test Language", radioStation.getLanguage());
    }

    @Test
    public void testSetLogo() {
        RadioStation radioStation = new RadioStation();
        radioStation.setLogo("Test Logo");
        assertEquals("Test Logo", radioStation.getLogo());
    }

    @Test
    public void testSetSource1() {
        RadioStation radioStation = new RadioStation();
        radioStation.setSource1("Test Source1");
        assertEquals("Test Source1", radioStation.getSource1());
    }

    @Test
    public void testSetSource2() {
        RadioStation radioStation = new RadioStation();
        radioStation.setSource2("Test Source2");
        assertEquals("Test Source2", radioStation.getSource2());
    }

    @Test
    public void testSetSource3() {
        RadioStation radioStation = new RadioStation();
        radioStation.setSource3("Test Source3");
        assertEquals("Test Source3", radioStation.getSource3());
    }

    @Test
    public void testSetSource4() {
        RadioStation radioStation = new RadioStation();
        radioStation.setSource4("Test Source4");
        assertEquals("Test Source4", radioStation.getSource4());
    }

    @Test
    public void testSetSource5() {
        RadioStation radioStation = new RadioStation();
        radioStation.setSource5("Test Source5");
        assertEquals("Test Source5", radioStation.getSource5());
    }

    @Test
    public void testSetSource6() {
        RadioStation radioStation = new RadioStation();
        radioStation.setSource6("Test Source6");
        assertEquals("Test Source6", radioStation.getSource6());
    }
}
