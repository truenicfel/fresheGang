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

    /**
     * Add a book.
     *
     * Service method to add a single book
     *
     * @param toAdd is the book to be added
     * @return a Result object
     */
    Result addBook(final Book toAdd);

    /**
     * Add a disc.
     *
     * Service method to add a single disc
     *
     * @param toAdd is the disc to be added
     * @return a Result object
     */
    Result addDisc(final Disc toAdd);

    /**
     * Get all books.
     *
     * Service method to get all books
     *
     * @return all books as content of a result object
     */
    Result getBooks();

    /**
     * Get all discs.
     *
     * Service method to get all discs
     *
     * @return all discs as content of a result object
     */
    Result getDiscs();

    /**
     * Get a book.
     *
     * Service method to get a single book
     *
     * @param isbn is the isbn number of the wanted book
     * @return a Result object
     */
    Result getBook(final String isbn);

    /**
     * Get a disc.
     *
     * Service method to get a single disc
     *
     * @param barcode is the barcode of the wanted disc
     * @return a Result object
     */
    Result getDisc(final String barcode);

    /**
     * Update a book.
     *
     * Service method to update a single book (changing the isbn number is not
     * allowed)
     *
     * @param toUpdate is the updated book
     * @return a Result object
     */
    Result updateBook(final Book toUpdate);

    /**
     * Update a disc.
     *
     * Service method to update a single disc (changing the barcode is not
     * allowed)
     *
     * @param toUpdate is the updated disc
     * @return a Result object
     */
    Result updateDisc(final Disc toUpdate);

}
