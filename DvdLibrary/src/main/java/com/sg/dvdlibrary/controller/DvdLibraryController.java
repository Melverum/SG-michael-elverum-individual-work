
package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryDaoException;
import com.sg.dvdlibrary.dto.Dvd;
import com.sg.dvdlibrary.ui.DvdLibraryView;
import java.util.List;

/**
 *
 * @author Melve
 */
public class DvdLibraryController {

    DvdLibraryView view;
    DvdLibraryDao dao;


    public DvdLibraryController(DvdLibraryDao dao, DvdLibraryView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection;
        int counter = 0;
        try {
            loadDvds();
            while (keepGoing) {
                
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listDvds();
                        break;
                    case 2:
                        searchDvd();
                        break;
                    case 3:
                        createDvd();
                        counter++;
                        break;
                    case 4:
                        removeDvd();
                        counter++;
                        break;
                    case 5:
                        editDvd();
                        counter++;
                        break;
                    case 6:
                        saveChanges();
                        counter = 0;
                        break;
                    case 7:
                        saveCheck(counter);
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (DvdLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void loadDvds() throws DvdLibraryDaoException {
        dao.getAllDvds();
    }

    private void createDvd() throws DvdLibraryDaoException {

        view.displayCreateDvdBanner();
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getDvdTitle(), newDvd);
        view.displayCreateSuccessBanner();
    }

    private void listDvds() throws DvdLibraryDaoException {
        view.displayDisplayAllBanner();
        List<Dvd> dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);
    }

    private void searchDvd() throws DvdLibraryDaoException {
        view.displayDisplayDvdBanner();
        String dvdTitle = view.getDvdTitleChoice();
        Dvd dvd = dao.searchDvd(dvdTitle);
        view.displayDvd(dvd);
    }

    private void editDvd() throws DvdLibraryDaoException {
        view.displayEditDvdBanner();
        String dvdTitle = view.getDvdTitleChoice();
        Dvd editDvd = dao.searchDvd(dvdTitle);
        view.displayDvd(editDvd);
        view.getEditDvdInfo(dvdTitle, editDvd);
        dao.editDvd(dvdTitle, editDvd);
        view.displayEditSuccessBanner(editDvd);
    }

    private void removeDvd() throws DvdLibraryDaoException {
        view.displayRemoveDvdBanner();
        String dvdTitle = view.getDvdTitleChoice();
        Dvd dvd = dao.removeDvd(dvdTitle);
        view.displayRemoveSuccessBanner(dvd);
    }

    private void saveChanges() throws DvdLibraryDaoException {
        dao.saveChanges();
        view.displaySaveBanner();
    }

    private void saveCheck(int counter) throws DvdLibraryDaoException {
        int save = view.runSaveChecker(counter);
        if (save == 1){
            dao.saveChanges();
            view.displaySaveBanner();
        }
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
