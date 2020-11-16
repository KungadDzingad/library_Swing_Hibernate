package com.library;


import com.library.exceptions.BookNotAvailableException;
import com.library.gui.MainFrame;
import com.mysql.cj.util.TimeUtil;

import java.time.LocalDate;
import java.util.Calendar;
import java.sql.Date;

public class Library {

    private static MainFrame frame;

    public static void main(String[] args) {
        LibraryManagementSystem.getSystem();
        frame = MainFrame.getFrame();

//        Book book = LibraryManagementSystem.getSystem().getBooks().get(3);
//        LibraryManagementSystem.getSystem().loginUser("anowak","anowak");
//
//        Date from = new Date(2022-1900,1,1);
//        Date to = new Date(2022-1900,2,1);
//
//        try {
//            LibraryManagementSystem.getSystem().addClientReservation(book,from,to);
//        } catch (BookNotAvailableException e) {
//            e.printStackTrace();
//            System.out.println("ELO");
//        }
//        book = LibraryManagementSystem.getSystem().getBooks().get(3);
//        for (BookItem bookItem : book.getBookItems()) {
//            for (BookReservation reservation : bookItem.getReservations()) {
//                System.out.println(reservation.getFrom());
//            }
//            System.out.println();
//        }

    }
}
