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

    //Object Variables
    //--------------------------------------------------------------------------
	private static final PseudoDatabase DATABASE = new PseudoDatabase();
	
    //Constructors
    //--------------------------------------------------------------------------
    //Methods Private
    //--------------------------------------------------------------------------

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
        return true;
    }

    @Override
    public boolean updateDisc(Disc toUpdate) {
        return true;
    }

	@Override
	public Book getBook(String isbn) {
		return null;
	}

	@Override
	public Disc getDisc(String barcode) {
		// TODO Auto-generated method stub
		return null;
	}
    
    //Getter + Setter (also Private)
    //--------------------------------------------------------------------------
}
