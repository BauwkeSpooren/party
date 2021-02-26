package be.thomasmore.party.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Artist {
    @Id
    private int id;
    private String artistName;
    private String bio;
    private String genre;
    private String linkMoreInfo;
    private String portfolio;

    Artist() {

    }

    public Artist(int id, String artistName, String bio, String genre, String linkMoreInfo, String portfolio) {
        this.id = id;
        this.artistName = artistName;
        this.bio = bio;
        this.genre = genre;
        this.linkMoreInfo = linkMoreInfo;
        this.portfolio = portfolio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getLinkMoreInfo() {
        return linkMoreInfo;
    }

    public void setLinkMoreInfo(String linkMoreInfo) {
        this.linkMoreInfo = linkMoreInfo;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }
}
