package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Melve
 */
public class DvdLibraryDaoFileImpl implements DvdLibraryDao {

    public static final String LIBRARY_FILE = "dvdLibrary.txt";
    public static final String AUDIT_FILE = "auditfile.txt";
    public static final String DELIMITER = "::";
    private Map<String, Dvd> dvds = new HashMap<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    

    @Override
    public Dvd addDvd(String dvdTitle, Dvd dvd) throws DvdLibraryDaoException {
        
        Dvd newDvd = dvds.put(dvdTitle, dvd);
        writeLibrary();
        return newDvd;
    }

    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryDaoException {
        loadLibrary();
        return new ArrayList<Dvd>(dvds.values());
    }

    @Override
    public Dvd searchDvd(String dvdTitle) throws DvdLibraryDaoException {
        loadLibrary();
        return dvds.get(dvdTitle);
    }

    @Override
    public Dvd removeDvd(String dvdTitle) throws DvdLibraryDaoException {
        Dvd removedDvd = dvds.remove(dvdTitle);
        writeLibrary();
        return removedDvd;
    }

    @Override
    public Dvd editDvd(String dvdTitle, Dvd dvd) throws DvdLibraryDaoException {
        loadLibrary();
        Dvd editedDvd = null;
        
        if (dvdTitle != null){
        editedDvd = dvds.replace(dvdTitle, dvd);
        writeLibrary();
        }
 
        return editedDvd;
    }

    @Override
    public void saveChanges() throws DvdLibraryDaoException {
        writeLibrary();
    }

    private void loadLibrary() throws DvdLibraryDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryDaoException(
                    "-_- Could not load roster data into memory.", e);
        }

        String currentLine;

        String[] currentTokens;
        
        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Dvd currentDvd = new Dvd(currentTokens[0]);
            currentDvd.setReleaseDate(LocalDate.parse(currentTokens[1], formatter));
            currentDvd.setMpaaRating(currentTokens[2]);
            currentDvd.setDirectorsName(currentTokens[3]);
            currentDvd.setStudio(currentTokens[4]);
            currentDvd.setUserRatingAndOrNote(currentTokens[5]);

            //Put the current students in the map!
            dvds.put(currentDvd.getDvdTitle(), currentDvd);
        }
        //When all finished, close the scanner.
        scanner.close();
    }

    private void writeLibrary() throws DvdLibraryDaoException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e) {
            throw new DvdLibraryDaoException(
                    "Could not save student data.", e);
        }

        List<Dvd> dvdList = this.getAllDvds();
        for (Dvd currentDvd : dvdList) {

            out.println(currentDvd.getDvdTitle() + DELIMITER
                    + currentDvd.getReleaseDate().format(formatter) + DELIMITER
                    + currentDvd.getMpaaRating() + DELIMITER
                    + currentDvd.getDirectorsName() + DELIMITER
                    + currentDvd.getStudio() + DELIMITER
                    + currentDvd.getUserRatingAndOrNote());
            // force PrintWriter to write line to the file
            out.flush();
        }
        //close for clean up
        out.close();
    }
    
    @Override
    public void writeAuditEntry(String entry) throws DvdLibraryDaoException {
        
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new DvdLibraryDaoException("Could not persist audit information.", e);
        }
 
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }

}
