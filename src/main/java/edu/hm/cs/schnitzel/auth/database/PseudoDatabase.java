/*
 * Autoren:     N.Dassler, P.Konopac
 * E-Mail:      dassler@hm.edu, konopac@hm.edu
 * Team:        schnitzel
 * Vorlesung:   Software Architektur
 * Dozent:      A.Boettcher
 */
package edu.hm.cs.schnitzel.auth.database;

import java.util.HashSet;
import java.util.Set;

import edu.hm.cs.schnitzel.entities.Book;
import edu.hm.cs.schnitzel.entities.Disc;

/**
 *
 * @author konopac
 */
public class PseudoDatabase {

    // Object Variables
    // -------------------------------------------------------------------------
    private final Set<Book> books;
    private final Set<Disc> discs;

    // Constructors
    // -------------------------------------------------------------------------
    /**
     * Use default filled database.
     */
    public PseudoDatabase() {
        books = new HashSet<>();
        discs = new HashSet<>();

        books.add(new Book("Tolkien", "12345-321",
                2, "The Lord of the Rings 1"));
        books.add(new Book("Tolkien", "12345-322",
                1, "The Lord of the Rings 2"));
        books.add(new Book("Tolkien", "12345-323",
                1, "The Lord of the Rings 3"));
        books.add(new Book("Rowling", "99999-123",
                0, "Harry Potter"));

        discs.add(new Disc("9123-1234", 0, 2, "nicfel", "flip", "title"));
        discs.add(new Disc("2342-3043", 1, 1, "affe", "kuh", "schnitzel"));
        discs.add(new Disc("8723-4823", 2, 0,
                "Peter Jackson", "...", "The Lord of the Rings"));
    }

    /**
     * Fill your own database.
     *
     * @param booksInput are the books to be present on database start
     * @param discsInput are the discs to be present on database start
     */
    public PseudoDatabase(final Set<Book> booksInput,
            final Set<Disc> discsInput) {
        this.books = booksInput;
        this.discs = discsInput;
    }

    // Public Getter
    // -------------------------------------------------------------------------
    /**
     * Get Books.
     *
     * @return all books
     */
    public final Set<Book> getBooks() {
        return books;
    }

    /**
     * Get Discs.
     *
     * @return all discs
     */
    public final Set<Disc> getDiscs() {
        return discs;
    }
}
