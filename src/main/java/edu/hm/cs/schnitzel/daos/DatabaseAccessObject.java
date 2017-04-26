/*
 * Autoren:     N.Dassler, P.Konopac
 * E-Mail:      dassler@hm.edu, konopac@hm.edu
 * Team:        schnitzel
 * Vorlesung:   Software Architektur
 * Dozent:      A.Boettcher
 */
package edu.hm.cs.schnitzel.daos;

import edu.hm.cs.schnitzel.entities.Book;
import edu.hm.cs.schnitzel.entities.Disc;
import java.util.List;

/**
 *
 * @author nicfel
 */
public interface DatabaseAccessObject {
    
    boolean addBook(Book toAdd);

    boolean addDisc(Disc toAdd);

    List<Book> getBooks();

    List<Disc> getDiscs();

    Book getBook(String isbn);
    
    Disc getDisc(String barcode);

    boolean updateBook(Book toUpdate);

    boolean updateDisc(Disc toUpdate);

}
