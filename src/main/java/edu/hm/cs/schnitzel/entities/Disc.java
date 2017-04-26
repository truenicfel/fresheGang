/*
 * Autoren:     N.Dassler, P.Konopac
 * E-Mail:      dassler@hm.edu, konopac@hm.edu
 * Team:        schnitzel
 * Vorlesung:   Software Architektur
 * Dozent:      A.Boettcher
 */
package edu.hm.cs.schnitzel.entities;

/**
 * Represents Discs.
 * This class will be able to be parsed from + to json with jackson.
 * @author N.Dassler, P.Konopac
 */
public class Disc extends Resource {

    //Object Variables
    //--------------------------------------------------------------------------
    /**
     * Identifies as disc (like a isbn for books).
     */
    private final String barcode;
    /**
     * The release year of the disc.
     */
    private final int year;
    /**
     * The fsk of the disc.
     */
    private final int fsk;
    /**
     * The director of the disc (only for movies).
     */
    private final String director;
    /**
     * The writer of the disc (only for movies).
     */
    private final String writer;

    //Constructors
    //--------------------------------------------------------------------------
    
    /**
     * Initializes disc with only default values.
     * Necessary to parse json with jackson.
     */
    public Disc() {
        this("");
    }
    /**
     * Creates a new book and initializes with a title (other values will be
     * default).
     *
     * @param title The title of the disc.
     */
    public Disc(String title) {
        this("", 0, 0, "", "", title);
    }

    public Disc(String barcode, int year, int fsk, String director, String writer, String title) {
        super(title);
        this.barcode = barcode;
        this.year = year;
        this.fsk = fsk;
        this.director = director;
        this.writer = writer;
    }
    
    /**
     * A copy constructor.
     * This will copy all value from other Disc to this new Disc.
     * @param other The other disc which will be copied.
     */
    public Disc(Disc other) {
        this(other.getBarcode(), other.getYear(), other.getFsk(),
                other.getDirector(), other.getWriter(), other.getTitle());
    }

    //Methods Private
    //--------------------------------------------------------------------------
    //Methods Public
    //--------------------------------------------------------------------------
    /**
     * Returns barcode.
     *
     * @return The barcode.
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * Returns release year.
     *
     * @return The release year.
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns fsk.
     *
     * @return The fsk.
     */
    public int getFsk() {
        return fsk;
    }

    /**
     * Returns director.
     *
     * @return The director.
     */
    public String getDirector() {
        return director;
    }

    /**
     * Returns writer.
     *
     * @return The writer.
     */
    public String getWriter() {
        return writer;
    }

    //Getter + Setter (also Private)
    //--------------------------------------------------------------------------
}
