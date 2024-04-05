package com.bankai.jukebox.utils.jmusixmatch.entity.lyrics.get;

import com.bankai.jukebox.utils.jmusixmatch.entity.lyrics.Lyrics;
import com.google.gson.annotations.SerializedName;

public class LyricsGetBody {
	
    @SerializedName("lyrics")
    private Lyrics lyrics;

    public void setLyrics(Lyrics lyrics) {
        this.lyrics = lyrics;
    }

    public Lyrics getLyrics() {
        return lyrics;
    }
}
