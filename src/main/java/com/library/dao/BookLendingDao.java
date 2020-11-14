package com.library.dao;

import com.library.BookLending;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class BookLendingDao extends GeneralDao<BookLending> {

    public BookLendingDao(EntityManagerFactory factory) {
        super(factory);
    }

    @Override
    public List<BookLending> getAll() {
        return null;
    }

}
