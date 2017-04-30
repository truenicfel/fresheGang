/*
 * Autoren:     N.Dassler, P.Konopac
 * E-Mail:      dassler@hm.edu, konopac@hm.edu
 * Team:        schnitzel
 * Vorlesung:   Software Architektur
 * Dozent:      A.Boettcher
 */
package edu.hm.cs.schnitzel.daos;

import java.util.ArrayList;
import java.util.List;

import edu.hm.cs.schnitzel.database.PseudoDatabase;
import edu.hm.cs.schnitzel.entities.Book;
import edu.hm.cs.schnitzel.entities.Disc;
import java.util.HashSet;

/**
 * PseudoDatabaseAccessObject.
 *
 * This implementation of DatabaseAccessObject uses PseudoDatabase as a database
 *
 * @author konopac
 */
public class PseudoDatabaseAccessObject implements DatabaseAccessObject {

    //Constant Variables
    //--------------------------------------------------------------------------
    private static final PseudoDatabase DATABASE
            = new PseudoDatabase(new HashSet<Book>(), new HashSet<>());

    //Methods Public Static
    //--------------------------------------------------------------------------

    /**
     * Clear the database.
     * Mostly used for testing
     */
    public static final void clear() {
    	DATABASE.getBooks().clear();
    	DATABASE.getDiscs().clear();
    }

    //Methods Private
    //--------------------------------------------------------------------------
    /**
     * Remove a book.
     *
     * @param isbn is the isbn number of the book to remove
     * @return true
     */
    private boolean removeBook(final String isbn) {
        DATABASE.getBooks().remove(getBook(isbn));
        return true;
    }

    /**
     * Remove a disc.
     *
     * @param barcode is the barcode of the disc to remove
     * @return true
     */
    private boolean removeDisc(final String barcode) {
        DATABASE.getDiscs().remove(getDisc(barcode));
        return true;
    }

    //Methods Public
    //--------------------------------------------------------------------------
    @Override
    public final boolean addBook(final Book toAdd) {
        return DATABASE.getBooks().add(toAdd);
    }

    @Override
    public final boolean addDisc(final Disc toAdd) {
        return DATABASE.getDiscs().add(toAdd);
    }

    @Override
    public final List<Book> getBooks() {
        final List<Book> result = new ArrayList<>();
        result.addAll(DATABASE.getBooks());
        return result;
    }

    @Override
    public final List<Disc> getDiscs() {
        final List<Disc> result = new ArrayList<>();
        result.addAll(DATABASE.getDiscs());
        return result;
    }

    @Override
    public final boolean updateBook(final Book toUpdate) {
        final boolean removed = removeBook(toUpdate.getIsbn());
        final boolean added = addBook(toUpdate);
        return removed && added;
    }

    @Override
    public final boolean updateDisc(final Disc toUpdate) {
    	final boolean removed = removeDisc(toUpdate.getBarcode());
    	final boolean added = addDisc(toUpdate);
    	return removed && added;
    }

    @Override
    public final Book getBook(final String isbn) {
        Book result = null;
        for (final Book book : DATABASE.getBooks()) {
            if (book.getIsbn().equals(isbn)) {
                result = book;
            }
        }
        return result;
    }

    @Override
    public final Disc getDisc(final String barcode) {
        Disc result = null;
        for (final Disc disc : DATABASE.getDiscs()) {
            if (disc.getBarcode().equals(barcode)) {
                result = disc;
            }
        }
        return result;
    }
}
