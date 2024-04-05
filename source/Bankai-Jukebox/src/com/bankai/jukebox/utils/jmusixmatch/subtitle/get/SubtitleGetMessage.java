package com.bankai.jukebox.utils.jmusixmatch.subtitle.get;

import com.google.gson.annotations.SerializedName;

public class SubtitleGetMessage {

    @SerializedName("message")
    private SubtitleGetContainer container;

    public SubtitleGetContainer getContainer() {
        return container;
    }

    public void setContainer(SubtitleGetContainer container) {
        this.container = container;
    }
}
