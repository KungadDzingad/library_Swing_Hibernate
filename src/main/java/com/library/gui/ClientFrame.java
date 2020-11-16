package com.library.gui;

import com.library.*;
import com.library.exceptions.BookNotAvailableException;
import com.library.exceptions.WrongReservationInputException;
import com.library.gui.input.InputReservation;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientFrame implements GetsPanel {


    private static Map<Integer, BookReservation> bookReservationMap;
    private static Map<Integer, Book> bookMap;

    private JPanel root;
    private JLabel welcomeLabel;
    private JButton logoutButton;

    private JTable allBooksTable;
    private DefaultTableModel allBooksTableModel;

    private JTable myReservationsTable;
    private DefaultTableModel myReservationTableModel;

    private JButton reserveButton;
    private JButton cancelButton;

    public ClientFrame(){
        String clientName = LibraryManagementSystem.getSystem().getLoggedUser().getName();
        String clientLastName= LibraryManagementSystem.getSystem().getLoggedUser().getLastName();
        welcomeLabel.setText("Witaj " +clientName + " " + clientLastName);

        initAllBooksTable();
        initMyReservationsTable();
        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reserve();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelReservation();
            }
        });
    }

    private void cancelReservation(){
        int row = myReservationsTable.getSelectedRow();
        BookReservation reservation = bookReservationMap.get(row);
        System.out.println(row);
        LibraryManagementSystem.getSystem().cancelReservation(reservation);
        inflateMyReservationsTable();
    }

    private void reserve(){
        int row = allBooksTable.getSelectedRow();
        Book book = bookMap.get(row);
        if(row >=0) {
            try {
                new InputReservation(book).input();
            } catch (WrongReservationInputException | BookNotAvailableException ignored) {
                JOptionPane.showConfirmDialog(null,null,"Zle Dane",JOptionPane.ERROR_MESSAGE);
            }
        }else{
            int option = JOptionPane.showConfirmDialog(null,null,"Prosze wybrac ksiazke",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initAllBooksTable(){
        allBooksTableModel = new DefaultTableModel();
        allBooksTableModel.addColumn("Nazwa");
        allBooksTableModel.addColumn("Autor");
        allBooksTableModel.addColumn("Kategoria");
        allBooksTableModel.addColumn("Wydawca");
        allBooksTableModel.addColumn("Dostepnosc");
        allBooksTable.setModel(allBooksTableModel);
        inflateAllBooksTable();
    }

    private void inflateAllBooksTable(){
        bookMap = new HashMap<>();
        allBooksTableModel.setRowCount(0);
        int row =0;
        for (Book book : LibraryManagementSystem.getSystem().getBooks()) {
            String available= book.isAvailable() ? "TAK" : "NIE";
            allBooksTableModel.addRow(new Object[]{
                    book.getTitle(),
                    book.getAuthor(),
                    book.getCategory(),
                    book.getPublisher(),
                    available
            });
            bookMap.put(row,book);
            row++;
        }
    }

    private void initMyReservationsTable(){
        myReservationTableModel = new DefaultTableModel();
        myReservationTableModel.addColumn("Nazwa Książki");
        myReservationTableModel.addColumn("Od");
        myReservationTableModel.addColumn("Do");
        myReservationsTable.setModel(myReservationTableModel);
        inflateMyReservationsTable();
    }

    private void inflateMyReservationsTable(){
        bookReservationMap =new HashMap<>();

        myReservationTableModel.setRowCount(0);
        Account user = LibraryManagementSystem.getSystem().getLoggedUser();
        List<BookReservation> reservationList = null;
        int row = 0;
        if(user instanceof Client) {
            reservationList = ((Client) user).getBookReservations();
            for (BookReservation reservation : reservationList) {
                String name = reservation.getBookItem().getBook().getTitle();
                String from = reservation.getFrom().toString();
                String to = reservation.getTo().toString();

                myReservationTableModel.addRow(new Object[]{name,from,to});
                bookReservationMap.put(row,reservation);
                row++;
            }
        }
    }

    @Override
    public JPanel getPanel() {
        return root;
    }
}
