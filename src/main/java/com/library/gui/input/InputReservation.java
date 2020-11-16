package com.library.gui.input;

import com.library.Book;
import com.library.BookReservation;
import com.library.LibraryManagementSystem;
import com.library.exceptions.BookNotAvailableException;
import com.library.exceptions.WrongReservationInputException;

import java.sql.Date;
import javax.swing.*;

public class InputReservation implements Inputs<BookReservation>{

    private JTextField fromYearTextField;
    private JTextField fromMonthTextField;
    private JTextField fromDayTextField;

    private JTextField toYearTextField;
    private JTextField toMonthTextField;
    private JTextField toDayTextField;

    Book book;

    public InputReservation(Book book){
        this.book = book;

        fromYearTextField = new JTextField();
        fromMonthTextField = new JTextField();
        fromDayTextField = new JTextField();

        toYearTextField = new JTextField();
        toMonthTextField = new JTextField();
        toDayTextField = new JTextField();
    }


    @Override
    public void input() throws WrongReservationInputException, BookNotAvailableException {
        Object[] objects = new Object[]{
                "Od kiedy rezerwacja",
                "rok",fromYearTextField,
                "miesiac", fromMonthTextField,
                "dzien", fromDayTextField,
                "Do kiedy (max 3 tygodnie)",
                "rok",toYearTextField,
                "miesiac", toMonthTextField,
                "dzien", toDayTextField
        };

        String frameTitle ="Zarezerwuj: "+book.getTitle()+ " - " + book.getAuthor();
        int option = JOptionPane.showConfirmDialog(null,objects,frameTitle,JOptionPane.OK_CANCEL_OPTION);
        if(option == JOptionPane.OK_OPTION) {
            int fromYear = Integer.parseInt(fromYearTextField.getText()) - 1900;
            int fromMonth = Integer.parseInt(fromMonthTextField.getText());
            int fromDay = Integer.parseInt(fromDayTextField.getText());
            int toYear = Integer.parseInt(toYearTextField.getText()) - 1900;
            int toMonth = Integer.parseInt(toMonthTextField.getText());
            int toDay = Integer.parseInt(toDayTextField.getText());

            if(fromMonth > 12 || toMonth> 12|| toDay > 31 || fromDay>31)
                throw new WrongReservationInputException();

            Date from = new Date(fromYear,fromMonth,fromDay);
            Date to = new Date(toYear,toMonth,toDay);


            LibraryManagementSystem.getSystem().addClientReservation(book,from,to);

        }

        throw new WrongReservationInputException();
    }
}
