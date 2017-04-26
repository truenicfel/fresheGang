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

/**
 *
 * @author nicfel
 */
public class PseudoDatabaseAccessObject implements DatabaseAccessObject {

    //Constant Variables
    //--------------------------------------------------------------------------
	
	private static final PseudoDatabase DATABASE = new PseudoDatabase();
	
    //Methods Private
    //--------------------------------------------------------------------------
	
	private boolean removeBook(String isbn) {
		DATABASE.getBooks().remove(getBook(isbn));
		return true;
	}
	
	private boolean removeDisc(String barcode) {
		DATABASE.getDiscs().remove(getDisc(barcode));
		return true;
	}
	
	//Methods Public
    //--------------------------------------------------------------------------
    
	@Override
    public boolean addBook(Book toAdd) {
        DATABASE.getBooks().add(toAdd);
        return true;
    }

    @Override
    public boolean addDisc(Disc toAdd) {
    	DATABASE.getDiscs().add(toAdd);
        return true;
    }

    @Override
    public List<Book> getBooks() {
    	final List<Book> result = new ArrayList<>();
    	result.addAll(DATABASE.getBooks());
    	return result;
    }

    @Override
    public List<Disc> getDiscs() {
    	final List<Disc> result = new ArrayList<>();
    	result.addAll(DATABASE.getDiscs());
    	return result;
    }

    @Override
    public boolean updateBook(Book toUpdate) {
    	removeBook(toUpdate.getIsbn());
    	addBook(toUpdate);
        return true;
    }

    @Override
    public boolean updateDisc(Disc toUpdate) {
    	removeDisc(toUpdate.getBarcode());
    	addDisc(toUpdate);
        return true;
    }

	@Override
	public Book getBook(String isbn) {
		Book result = new Book();
		for (final Book book : DATABASE.getBooks()) {
			if (book.getIsbn().equals(isbn)) {
				result = book;
			}
		}
		return result;
	}

	@Override
	public Disc getDisc(String barcode) {
		Disc result = new Disc();
		for (final Disc disc : DATABASE.getDiscs()) {
			if (disc.getBarcode().equals(barcode)) {
				result = disc;
			}
		}
		return result;
	}
}
