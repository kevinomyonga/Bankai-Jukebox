package com.bankai.jukebox.utils.jmusixmatch.subtitle.get;

import com.google.gson.annotations.SerializedName;
import com.bankai.jukebox.utils.jmusixmatch.subtitle.Subtitle;

public class SubtitleGetBody {

    @SerializedName("subtitle")
    private Subtitle subtitle;

    public Subtitle getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(Subtitle subtitle) {
        this.subtitle = subtitle;
    }
}
