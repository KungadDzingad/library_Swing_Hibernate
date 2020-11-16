package com.library.gui.input;

import com.library.Library;
import com.library.LibraryManagementSystem;
import com.library.exceptions.BookCreationException;

import javax.swing.*;

public class InputBook {

    private JTextField isbnField;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField categoryField;
    private JTextField publisherField;
    private JTextField numberOfPagesField;
    private JTextField bookItemsField;

    public InputBook(){
        isbnField = new JTextField();
        titleField = new JTextField();
        authorField = new JTextField();
        categoryField = new JTextField();
        publisherField = new JTextField();
        numberOfPagesField = new JTextField();
        bookItemsField = new JTextField();
    }

    public void input() throws BookCreationException {
        Object[] objects = new Object[]{
                "ISBM",isbnField,
                "Tytuł",titleField,
                "Autor",authorField,
                "Kategoria",categoryField,
                "Wydawca",publisherField,
                "Ilosc Stron",numberOfPagesField,
                "Ile egzemplarzy",bookItemsField,
        };

        int option = JOptionPane.showConfirmDialog(null,objects,"Dodaj ksiazke",JOptionPane.OK_CANCEL_OPTION);
        if(option == JOptionPane.OK_OPTION) {
            try {
                long isbn = Long.parseLong(isbnField.getText());
                String title = titleField.getText();
                String author = authorField.getText();
                String category = categoryField.getText();
                String publisher = publisherField.getText();
                int pages = Integer.parseInt(numberOfPagesField.getText());
                int items = Integer.parseInt(bookItemsField.getText());

                LibraryManagementSystem.getSystem().addNewBook(isbn,title,author,category,publisher,pages,items);

            }catch (Exception e){
                throw new BookCreationException();
            }

        }
    }
}
