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
 * Represents Discs. This class will be able to be parsed from + to json with
 * jackson.
 *
 * @author N.Dassler, P.Konopac
 */
public class Disc extends Resource {

    //Object Variables
    //--------------------------------------------------------------------------
    /**
     * Identifies as disc (like a isbn for books).
     */
    private String barcode;
    /**
     * The release year of the disc.
     */
    private int year;
    /**
     * The fsk of the disc.
     */
    private int fsk;
    /**
     * The director of the disc (only for movies).
     */
    private String director;
    /**
     * The writer of the disc (only for movies).
     */
    private String writer;

    //Constructors
    //--------------------------------------------------------------------------
    /**
     * Initializes disc with only default values. Necessary to parse json with
     * jackson.
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
    public Disc(final String title) {
        this("", 0, 0, "", "", title);
    }

    public Disc(final String barcodeInput, final int yearInput,
            final int fskInput, final String directorInput,
            final String writerInput, final String title) {
        super(title);
        this.barcode = barcodeInput;
        this.year = yearInput;
        this.fsk = fskInput;
        this.director = directorInput;
        this.writer = writerInput;
    }

    /**
     * A copy constructor. This will copy all value from other Disc to this new
     * Disc.
     *
     * @param other The other disc which will be copied.
     */
    public Disc(final Disc other) {
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
    public final String getBarcode() {
        return barcode;
    }

    /**
     * Setter for barcode.
     *
     * @param barcodeInput is the barcode
     */
    public final void setBarcode(final String barcodeInput) {
        this.barcode = barcodeInput;
    }

    /**
     * Returns release year.
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

    /**
     * Returns fsk.
     *
     * @return The fsk.
     */
    public final int getFsk() {
        return fsk;
    }

    /**
     * Setter for fsk.
     *
     * @param fskInput is the fsk
     */
    public final void setFsk(final int fskInput) {
        this.fsk = fskInput;
    }

    /**
     * Returns director.
     *
     * @return The director.
     */
    public final String getDirector() {
        return director;
    }

    /**
     * Setter for director.
     *
     * @param directorInput is the director
     */
    public final void setDirector(final String directorInput) {
        this.director = directorInput;
    }

    /**
     * Returns writer.
     *
     * @return The writer.
     */
    public final String getWriter() {
        return writer;
    }

    /**
     * Setter for writer.
     *
     * @param writerInput is the writer
     */
    public final void setWriter(final String writerInput) {
        this.writer = writerInput;
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.barcode);
        return hash;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Disc other = (Disc) obj;
        return Objects.equals(this.barcode, other.barcode);
    }

    //Getter + Setter (also Private)
    //--------------------------------------------------------------------------
}
