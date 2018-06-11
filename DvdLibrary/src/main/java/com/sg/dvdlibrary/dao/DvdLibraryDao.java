package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;
import java.util.List;

/**
 *
 * @author Melve
 */
public interface DvdLibraryDao {

    /**
     * Adds the given Dvd to the library and associates it with the given dvd
     * title. If there is already a dvd associated with the given dvd title it
     * will return that dvd object, otherwise it will return null.
     *
     * @param dvdTitle with which dvd is to be associated
     * @param dvd dvd to be added to the roster
     * @return the Dvd object previously associated with the given dvd title if
     * it exists, null otherwise
     * @throws com.sg.dvdlibrary.dao.DvdLibraryDaoException
     */
    Dvd addDvd(String dvdTitle, Dvd dvd)
            throws DvdLibraryDaoException;

    /**
     * Returns a String array containing the dvd titles of all dvds in the
     * library.
     *
     * @return String array containing the titles of all the dvds in the library
     * @throws com.sg.dvdlibrary.dao.DvdLibraryDaoException
     *
     */
    List<Dvd> getAllDvds()
            throws DvdLibraryDaoException;

    /**
     * Returns the dvd object associated with the given dvd title. Returns null
     * if no such dvd exists
     *
     * @param dvdTitle title of the dvd to retrieve
     * @return the dvd object associated with the given dvd title, null if no
     * such title exists
     * @throws com.sg.dvdlibrary.dao.DvdLibraryDaoException
     */
    Dvd searchDvd(String dvdTitle)
            throws DvdLibraryDaoException;

    /**
     * Removes from the roster the dvd associated with the given title. Returns
     * the dvd object that is being removed or null if there is no dvd
     * associated with the given title
     *
     * @param dvdTitle title of dvd to be removed
     * @return dvd object that was removed or null if no dvd was associated with
     * the given dvd title
     * @throws com.sg.dvdlibrary.dao.DvdLibraryDaoException
     *
     */
    Dvd removeDvd(String dvdTitle)
            throws DvdLibraryDaoException;

    /**
     * Edits from the Library the dvd associated with the given title. Returns
     * the dvd object and listArray
     *
     * @param dvdTitle
     * 
     * @param dvd
     * @return 
     * @throws com.sg.dvdlibrary.dao.DvdLibraryDaoException
     */
    Dvd editDvd(String dvdTitle, Dvd dvd)
            throws DvdLibraryDaoException;

    /**
     * Saves all the information
     *
     * @throws com.sg.dvdlibrary.dao.DvdLibraryDaoException
     */

    void saveChanges()
            throws DvdLibraryDaoException;
    
    void writeAuditEntry(String entry) throws DvdLibraryDaoException;
}
