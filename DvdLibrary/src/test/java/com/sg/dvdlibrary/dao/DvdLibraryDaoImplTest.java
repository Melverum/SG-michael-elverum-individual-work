/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Melve
 */
public class DvdLibraryDaoImplTest {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    DvdLibraryDao dao = new DvdLibraryDaoFileImpl();

    public DvdLibraryDaoImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws DvdLibraryDaoException {
        List<Dvd> dvds = dao.getAllDvds();
        for (Dvd d : dvds) {
            String dvdTitle = d.getDvdTitle();
            dao.removeDvd(dvdTitle);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addDvd method, of class DvdLibraryDao.
     */
    @Test
    public void testAddDvd() throws Exception {

        Dvd dvd = new Dvd("Step Brothers");
        dvd.setDirectorsName("Michael");
        dvd.setMpaaRating("R");
        dvd.setReleaseDate(LocalDate.parse("03/06/1999", formatter));
        dvd.setStudio("Elverum Studios");
        dvd.setUserRatingAndOrNote("4/5 (very funny)");

        dao.addDvd(dvd.getDvdTitle(), dvd);

        Dvd daoDvd = dao.searchDvd("Step Brothers");
        assertEquals(daoDvd, dvd);

    }

//    @Test
//    public void testGetAllDvds() throws Exception {
//
//        Dvd dvd = new Dvd("Step Brothers");
//        dvd.setDirectorsName("Michael");
//        dvd.setMpaaRating("R");
//        dvd.setReleaseDate(LocalDate.parse("03/06/1999", formatter));
//        dvd.setStudio("Elverum Studios");
//        dvd.setUserRatingAndOrNote("4/5 (very funny)");
//        dao.addDvd(dvd.getDvdTitle(), dvd);
//
//        Dvd dvd2 = new Dvd("Mike");
//        dvd2.setDirectorsName("Mark");
//        dvd2.setMpaaRating("R");
//        dvd.setReleaseDate(LocalDate.parse("03/06/1999", formatter));
//        dvd2.setStudio("Elverum Studios");
//        dvd2.setUserRatingAndOrNote("3/5 (not bad)");
//        dao.addDvd(dvd2.getDvdTitle(), dvd2);
//
//        List<Dvd> dvds = dao.getAllDvds();
//
//        assertEquals(2, dvds.size());
//
//    }

    @Test
    public void testRemoveDvd() throws Exception {
    
        
    Dvd dvd = new Dvd("Step Brothers");
        dvd.setDirectorsName("Michael");
        dvd.setMpaaRating("R");
       dvd.setReleaseDate(LocalDate.parse("03/06/1999", formatter));
        dvd.setStudio("Elverum Studios");
        dvd.setUserRatingAndOrNote("4/5 (very funny)");
        
        dao.addDvd(dvd.getDvdTitle(), dvd);
        Dvd daoDvd = dao.searchDvd(dvd.getDvdTitle());
        assertEquals(daoDvd.getDvdTitle(), dvd.getDvdTitle());
        
        dao.removeDvd("Step Brothers");
        
        daoDvd = dao.searchDvd("Step Brothers");
        assertNull(daoDvd);
        
    }
    /**
     * Test of editDvd method, of class DvdLibraryDao.
     */
 //   @Test
//    public void testEditDvd() throws Exception {
//        Dvd dvd = new Dvd("Step Brothers");
//        dvd.setDirectorsName("Michael");
//        dvd.setMpaaRating("R");
//       dvd.setReleaseDate(LocalDate.parse("03/06/1999", formatter));
//        dvd.setStudio("Elverum Studios");
//        dvd.setUserRatingAndOrNote("4/5 (very funny)");
//        dao.addDvd(dvd.getDvdTitle(), dvd);
//        
//        Dvd daoDvd = dao.searchDvd(dvd.getDvdTitle());
//        assertEquals(daoDvd, dvd);
//        
//        dvd.setReleaseDate(LocalDate.parse("03/06/1999", formatter));
//        dao.editDvd(dvd.getDvdTitle(), dvd);
//        
//        daoDvd = dao.searchDvd("Step Brothers");
//        
//        assertEquals(daoDvd, dvd);
//    }

   
}
