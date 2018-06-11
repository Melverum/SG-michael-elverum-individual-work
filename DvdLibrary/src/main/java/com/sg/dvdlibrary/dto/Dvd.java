package com.sg.dvdlibrary.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 *
 * @author Melve
 */
public class Dvd {

    private String dvdTitle;
    private LocalDate releaseDate;
    private String mpaaRating;
    private String directorsName;
    private String studio;
    private String userRatingAndOrNote;
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.dvdTitle);
        hash = 13 * hash + Objects.hashCode(this.releaseDate);
        hash = 13 * hash + Objects.hashCode(this.mpaaRating);
        hash = 13 * hash + Objects.hashCode(this.directorsName);
        hash = 13 * hash + Objects.hashCode(this.studio);
        hash = 13 * hash + Objects.hashCode(this.userRatingAndOrNote);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dvd other = (Dvd) obj;
        if (!Objects.equals(this.dvdTitle, other.dvdTitle)) {
            return false;
        }
        if (!Objects.equals(this.releaseDate, other.releaseDate)) {
            return false;
        }
        if (!Objects.equals(this.mpaaRating, other.mpaaRating)) {
            return false;
        }
        if (!Objects.equals(this.directorsName, other.directorsName)) {
            return false;
        }
        if (!Objects.equals(this.studio, other.studio)) {
            return false;
        }
        if (!Objects.equals(this.userRatingAndOrNote, other.userRatingAndOrNote)) {
            return false;
        }
        return true;
    }

    
    public Dvd(String dvdTitle) {
        this.dvdTitle = dvdTitle;
    }

    public String getDvdTitle() {
        return dvdTitle;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
       
        this.releaseDate = releaseDate;
        
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getDirectorsName() {
        return directorsName;
    }

    public void setDirectorsName(String directorsName) {
        this.directorsName = directorsName;
    }

    public String getUserRatingAndOrNote() {
        return userRatingAndOrNote;
    }

    public void setUserRatingAndOrNote(String userRatingAndOrNote) {
        this.userRatingAndOrNote = userRatingAndOrNote;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }
    @Override
    public String toString() {
    return "Title: " + dvdTitle + " |Release-Date: " + releaseDate + "|Rating: " 
            + mpaaRating;
}

}
