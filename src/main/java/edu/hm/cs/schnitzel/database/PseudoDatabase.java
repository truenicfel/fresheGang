/*
 * Autoren:     N.Dassler, P.Konopac
 * E-Mail:      dassler@hm.edu, konopac@hm.edu
 * Team:        schnitzel
 * Vorlesung:   Software Architektur
 * Dozent:      A.Boettcher
 */
package edu.hm.cs.schnitzel.database;

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
	// --------------------------------------------------------------------------

	private final Set<Book> books;
	private final Set<Disc> discs;

	// Constructors
	// --------------------------------------------------------------------------

	/**
	 * Use default filled database.
	 */
	public PseudoDatabase() {
		books = new HashSet<>();
		discs = new HashSet<>();

		books.add(new Book("Tolkien", "12345-321", 1980, "The Lord of the Rings 1"));
		books.add(new Book("Tolkien", "12345-322", 1985, "The Lord of the Rings 2"));
		books.add(new Book("Tolkien", "12345-323", 1990, "The Lord of the Rings 3"));
		books.add(new Book("Rowling", "99999-123", 2000, "Harry Potter"));

		discs.add(new Disc("9123-1234", 2017, 18, "nicfel", "flip", "title"));
		discs.add(new Disc("2342-3043", 2000, 6, "affe", "kuh", "schnitzel"));
		discs.add(new Disc("8723-4823", 1980, 0, "Peter Jackson", "...", "The Lord of the Rings"));
	}

	/**
	 * Fill your own database.
	 * 
	 * @param books
	 *            are the books to be present on database start
	 * @param discs
	 *            are the discs to be present on database start
	 */
	public PseudoDatabase(Set<Book> books, Set<Disc> discs) {
		this.books = books;
		this.discs = discs;
	}

	// Public Getter
	// --------------------------------------------------------------------------

	/**
	 * Get Books.
	 * 
	 * @return all books
	 */
	public Set<Book> getBooks() {
		return books;
	}

	/**
	 * Get Discs.
	 * 
	 * @return all discs
	 */
	public Set<Disc> getDiscs() {
		return discs;
	}
}
