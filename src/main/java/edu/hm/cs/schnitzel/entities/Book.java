/*
 * Autoren:     N.Dassler, P.Konopac
 * E-Mail:      dassler@hm.edu, konopac@hm.edu
 * Team:        schnitzel
 * Vorlesung:   Software Architektur
 * Dozent:      A.Boettcher
 */
package edu.hm.cs.schnitzel.entities;

import java.util.Objects;

/**
 * Represents Books. This class will be able to be parsed from + to json.
 *
 * @author N.Dassler, P.Konopac
 */
public class Book extends Resource {

    //Object Variables
    //--------------------------------------------------------------------------
    /**
     * The author of the book.
     */
    private String author;
    /**
     * Identifies the book.
     */
    private String isbn;
    /**
     * The release year of the book.
     */
    private int year;

    //Constructors
    //--------------------------------------------------------------------------
    /**
     * Initializes book with only default values. Necessary for parsing json
     * with jackson.
     */
    public Book() {
        this("");
    }

    /**
     * Initializes book with title (all others will be default values).
     *
     * @param title The title of the book.
     */
    public Book(final String title) {
        this("", "", 0, title);
    }

    /**
     * Initializes book with given values.
     *
     * @param authorInput The author as a String.
     * @param isbnInput The isbn as a String.
     * @param yearInput The year as an int.
     * @param title The title as an String.
     */
    public Book(final String authorInput, final String isbnInput,
            final int yearInput, final String title) {
        super(title);
        this.author = authorInput;
        this.isbn = isbnInput;
        this.year = yearInput;
    }

    /**
     * A copy constructor. This will copy all values from other Book to this new
     * book.
     *
     * @param other The book which will be copied.
     */
    public Book(final Book other) {
        this(other.getAuthor(), other.getIsbn(),
                other.getYear(), other.getTitle());
    }

    //Methods Private
    //--------------------------------------------------------------------------
    //Methods Public
    //--------------------------------------------------------------------------
    /**
     * Returns the author of the book.
     *
     * @return The author.
     */
    public final String getAuthor() {
        return author;
    }

    /**
     * Setter for author.
     *
     * @param authorInput is the author
     */
    public final void setAuthor(final String authorInput) {
        this.author = authorInput;
    }

    /**
     * Returns the isbn.
     *
     * @return The isbn.
     */
    public final String getIsbn() {
        return isbn;
    }

    /**
     * Setter for isbn.
     *
     * @param isbnInput is the isbn
     */
    public final void setIsbn(final String isbnInput) {
        this.isbn = isbnInput;
    }

    /**
     * Returns the release year of the book.
     *
     * @return The release year.
     */
    public final int getYear() {
        return year;
    }

    /**
     * Setter for year.
     *
     * @param yearInput is the year
     */
    public final void setYear(final int yearInput) {
        this.year = yearInput;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.isbn);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (!Objects.equals(this.isbn, other.isbn)) {
            return false;
        }
        return true;
    }

    //Getter + Setter (also Private)
    //--------------------------------------------------------------------------
}
