package com.bankai.jukebox.utils.jmusixmatch.snippet.get;

import com.google.gson.annotations.SerializedName;
import com.bankai.jukebox.utils.jmusixmatch.snippet.Snippet;

public class SnippetGetBody {

    @SerializedName("snippet")
    private Snippet snippet;

    public Snippet getSnippet() {
        return snippet;
    }

    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }
}
