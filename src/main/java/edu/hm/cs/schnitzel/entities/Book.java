/*
 * Autoren:     N.Dassler, P.Konopac
 * E-Mail:      dassler@hm.edu, konopac@hm.edu
 * Team:        schnitzel
 * Vorlesung:   Software Architektur
 * Dozent:      A.Boettcher
 */
package edu.hm.cs.schnitzel.entities;

/**
 * Represents Books.
 * This class will be able to be parsed from + to json.
 * @author N.Dassler, P.Konopac
 */
public class Book extends Resource {

    //Object Variables
    //--------------------------------------------------------------------------
    /**
     * The author of the book.
     */
    private final String author;
    /**
     * Identifies the book.
     */
    private final String isbn;
    /**
     * The release year of the book.
     */
    private final int year;

    //Constructors
    //--------------------------------------------------------------------------
    /**
     * Initializes book with only default values.
     * Necessary for parsing json with jackson.
     */
    public Book() {
        this("");
    }
    
    /**
     * Initializes book with title (all others will be default values).
     *
     * @param title The title of the book.
     */
    public Book(String title) {
        this("", "", 0, title);
    }

    /**
     * Initializes book with given values.
     *
     * @param author The author as a String.
     * @param isbn The isbn as a String.
     * @param year The year as an int.
     * @param title The title as an String.
     */
    public Book(String author, String isbn, int year, String title) {
        super(title);
        this.author = author;
        this.isbn = isbn;
        this.year = year;
    }

    //Methods Private
    //--------------------------------------------------------------------------
    //Methods Public
    //--------------------------------------------------------------------------
    /**
     * Returns the author of the book.
     * @return The author.
     */
    public String getAuthor() {
        return author;
    }
    /**
     * Returns the isbn.
     * @return The isbn.
     */
    public String getIsbn() {
        return isbn;
    }
    /**
     * Returns the release year of the book.
     * @return The release year.
     */
    public int getYear() {
        return year;
    }

    
    //Getter + Setter (also Private)
    //--------------------------------------------------------------------------
}
