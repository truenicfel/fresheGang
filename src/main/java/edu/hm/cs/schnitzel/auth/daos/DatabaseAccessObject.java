/*
 * Autoren:     N.Dassler, P.Konopac
 * E-Mail:      dassler@hm.edu, konopac@hm.edu
 * Team:        schnitzel
 * Vorlesung:   Software Architektur
 * Dozent:      A.Boettcher
 */
package edu.hm.cs.schnitzel.auth.daos;

import java.util.List;

import edu.hm.cs.schnitzel.entities.Book;
import edu.hm.cs.schnitzel.entities.Disc;

/**
 *
 * @author konopac
 */
public interface DatabaseAccessObject {

    /**
     * Add a book.
     *
     * DAO method to add a single book
     *
     * @param toAdd is the book to be added
     * @return true, if the process was successful.
     */
    boolean addBook(final Book toAdd);

    /**
     * Add a disc.
     *
     * DAO method to add a single disc
     *
     * @param toAdd is the disc to be added
     * @return true, if the process was successful.
     */
    boolean addDisc(final Disc toAdd);

    /**
     * Get all books.
     *
     * DAO method to get all books
     *
     * @return all books
     */
    List<Book> getBooks();

    /**
     * Get all discs.
     *
     * DAO method to get all discs
     *
     * @return all discs
     */
    List<Disc> getDiscs();

    /**
     * Get a book.
     *
     * DAO method to get a single book
     *
     * @param isbn is the isbn number of the wanted book
     * @return the wanted book
     */
    Book getBook(final String isbn);

    /**
     * Get a disc.
     *
     * DAO method to get a single disc
     *
     * @param barcode is the barcode of the wanted disc
     * @return the wanted disc
     */
    Disc getDisc(final String barcode);

    /**
     * Update a book.
     *
     * DAO method to update a single book (changing the isbn number is not
     * allowed)
     *
     * @param toUpdate is the updated book
     * @return true, if the process was successful.
     */
    boolean updateBook(final Book toUpdate);

    /**
     * Update a disc.
     *
     * DAO method to update a single disc (changing the barcode is not allowed)
     *
     * @param toUpdate is the updated disc
     * @return true, if the process was successful.
     */
    boolean updateDisc(final Disc toUpdate);

}
