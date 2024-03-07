package com.bankai.jukebox.models;

import com.google.gson.annotations.SerializedName;

public class RadioStation {
    @SerializedName("Title")
    private String title;

    @SerializedName("Description")
    private String description;

    @SerializedName("Genre")
    private String genre;

    @SerializedName("Country")
    private String country;

    @SerializedName("Language")
    private String language;

    @SerializedName("Logo")
    private String logo;

    @SerializedName("Source1")
    private String source1;

    @SerializedName("Source2")
    private String source2;

    @SerializedName("Source3")
    private String source3;

    @SerializedName("Source4")
    private String source4;

    @SerializedName("Source5")
    private String source5;

    @SerializedName("Source6")
    private String source6;

    // Getters and Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSource1() {
        return source1;
    }

    public void setSource1(String source1) {
        this.source1 = source1;
    }

    public String getSource2() {
        return source2;
    }

    public void setSource2(String source2) {
        this.source2 = source2;
    }

    public String getSource3() {
        return source3;
    }

    public void setSource3(String source3) {
        this.source3 = source3;
    }

    public String getSource4() {
        return source4;
    }

    public void setSource4(String source4) {
        this.source4 = source4;
    }

    public String getSource5() {
        return source5;
    }

    public void setSource5(String source5) {
        this.source5 = source5;
    }

    public String getSource6() {
        return source6;
    }

    public void setSource6(String source6) {
        this.source6 = source6;
    }

    @Override
    public String toString() {
        return "RadioStation{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", genre='" + genre + '\'' +
                ", country='" + country + '\'' +
                ", language='" + language + '\'' +
                ", logo='" + logo + '\'' +
                ", source1='" + source1 + '\'' +
                ", source2='" + source2 + '\'' +
                ", source3='" + source3 + '\'' +
                ", source4='" + source4 + '\'' +
                ", source5='" + source5 + '\'' +
                ", source6='" + source6 + '\'' +
                '}';
    }
}
