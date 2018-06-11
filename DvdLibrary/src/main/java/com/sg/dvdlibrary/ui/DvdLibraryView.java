package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.Dvd;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Melve
 */
public class DvdLibraryView {
    
    private UserIO io;
    
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        
    
    public DvdLibraryView(UserIO io) {
        this.io = io;
    }
    
    public int printMenuAndGetSelection() {
        io.print("=== Main Menu ===");
        io.print("1. List Dvd Titles");
        io.print("2. Search for Dvd");
        io.print("3. Create Dvd");
        io.print("4. Remove Dvd");
        io.print("5. Edit Dvd");
        io.print("6. Save Changes");
        io.print("7. Exit");
        
        return io.readInt("Please select from the above choices.", 1, 7);
    }
    
    public Dvd getNewDvdInfo() {
        String dvdTitle = io.readString("Please enter Dvd Title");
        LocalDate releaseDate = io.readDate("Please enter the release date (MM/dd/yyyy)");
        String mpaaRating = io.readString("Please enter mpaaRating");
        String director = io.readString("Please enter Director");
        String studio = io.readString("Enter the films Studio");
        String userRating = io.readString("Please enter your Rating And/Or Note");
        Dvd currentDvd = new Dvd(dvdTitle);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setMpaaRating(mpaaRating);
        currentDvd.setDirectorsName(director);
        currentDvd.setStudio(studio);
        currentDvd.setUserRatingAndOrNote(userRating);
        return currentDvd;
    }
    
    public Dvd getEditDvdInfo(String dvdTitle, Dvd dvd) {
        Dvd currentDvd = dvd;
        if (currentDvd != null){
        LocalDate releaseDate = io.readDate("Please enter Release date (MM/dd/yyyy)");
        String mpaaRating = io.readString("Please enter mpaaRating");
        String director = io.readString("Please enter Director");
        String studio = io.readString("Enter the films Studio");
        String userRating = io.readString("Please enter your Rating And/Or Note");
        currentDvd = new Dvd(dvdTitle);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setMpaaRating(mpaaRating);
        currentDvd.setDirectorsName(director);
        currentDvd.setStudio(studio);
        currentDvd.setUserRatingAndOrNote(userRating);
        }
        
        return currentDvd;
    }
    
    public void displayCreateDvdBanner() {
        io.print("=== Create DVD ===");
    }
    
    public void displayCreateSuccessBanner() {
        io.readString(
                "Dvd successfully created.  Please hit enter to continue");
    }
    
    public void displayDvdList(List<Dvd> dvdList) {
        for (Dvd currentDvd : dvdList) {
            io.print(currentDvd.getDvdTitle() + " - "
                    + currentDvd.getReleaseDate().format(formatter) + " - "
                    + currentDvd.getMpaaRating() + " - "
                    + currentDvd.getDirectorsName() + " - "
                    + currentDvd.getStudio() + " - "
                    + currentDvd.getUserRatingAndOrNote());
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayDisplayAllBanner() {
        io.print("=== Display All Dvds ===");
    }
    
    public void displayDisplayDvdBanner() {
        io.print("=== Display Dvd ===");
    }
    
    public void displayEditDvdBanner() {
        io.print("=== Edit Dvd ===");
    }
    
    public int runSaveChecker(int counter) {
        int save = 0;
        if (counter > 0) {
            save = io.readInt("You have unsaved changes.  Would you like to save?"
                    + "\n1. Yes" + "\n2. No",
                    1, 2);
        }
        if (save == 1) {
            save = 1;
        } else {
            save = 2;
        }
        
        return save;
    }
    
    public void displayEditSuccessBanner(Dvd dvd) {
       if (dvd != null){
        io.print("Dvd successfully edited.");
        }
        else{
            io.print("No such Dvd.");
        }
        io.readString("Please hit enter to continue");
    }
    
    public String getDvdTitleChoice() {
        return io.readString("Please enter the Dvd Title.");
    }
    
    public void displayDvd(Dvd dvd) {
        if (dvd != null) {
            io.print(dvd.getDvdTitle() + " - " + dvd.getReleaseDate() + " - " + dvd.getMpaaRating());
            io.print(" - " + dvd.getDirectorsName() + " - " + dvd.getUserRatingAndOrNote());
            io.print("");
        } else {
            io.print("No such Dvd.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayRemoveDvdBanner() {
        io.print("=== Remove Dvd ===");
    }
    
    public void displayRemoveSuccessBanner(Dvd dvd) {
        if (dvd != null){
        io.print("Dvd successfully removed.");
        }
        else{
            io.print("No such Dvd.");
        }
        io.readString("Please hit enter to continue");
    }
    
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }
    
    public void displaySaveBanner() {
        io.readString("Save Succesful! Please hit enter to continue");
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
}
