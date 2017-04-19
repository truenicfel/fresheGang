/*
 * Autoren:     N.Dassler, P.Konopac
 * E-Mail:      dassler@hm.edu, konopac@hm.edu
 * Team:        schnitzel
 * Vorlesung:   Software Architektur
 * Dozent:      A.Boettcher
 */
package edu.hm.cs.schnitzel.services;

import edu.hm.cs.schnitzel.dataExchange.Result;
import edu.hm.cs.schnitzel.entities.Book;
import edu.hm.cs.schnitzel.entities.Disc;

/**
 *
 * @author nicfel
 */
public interface Service {
    
   Result addBook(Book toAdd);
   Result addDisc(Disc toAdd);
   Result getBooks();
   Result getDiscs();
   Result updateBook(Book toUpdate);
   Result updateDisc(Disc toUpdate);
    
}
