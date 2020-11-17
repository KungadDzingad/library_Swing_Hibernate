package com.library.gui;

import com.library.*;
import com.library.exceptions.BookCreationException;
import com.library.gui.input.InputBook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class WorkerFrame implements GetsPanel{

    private Map<Integer, Book> bookMap;
    private Map<Integer, BookReservation> bookReservationMap;
    private Map<Integer, Client> clientMap;

    private JPanel root;

    private JTable bookTable;
    private DefaultTableModel bookTableModel;

    private JTable reservationTable;
    private DefaultTableModel reserbationTableModel;

    private JTable clientTable;
    private DefaultTableModel clientTableModel;


    private JLabel welcomeLabel;
    private JButton deleteBookButton;
    private JButton deleteReservationButton;
    private JButton deleteClientButton;
    private JButton addBookButton;
    private JButton logoutButton;

    public WorkerFrame() {
        if (LibraryManagementSystem.getSystem().getLoggedUser() instanceof Worker)
            welcomeLabel.setText("Witaj: " + ((Worker) LibraryManagementSystem.getSystem().getLoggedUser()).getWorkerID());

        initAllBooksTable();
        initReservationTable();
        initClientTable();
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LibraryManagementSystem.getSystem().logout();
                MainFrame.getFrame().setPanel(new LoginFrame());
            }
        });
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });
        deleteBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeBook();
            }
        });
        deleteReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeReservation();
            }
        });
        deleteClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeClient();
            }
        });
    }

    private void addBook(){
        try {
            new InputBook().input();
        }catch (BookCreationException e){
            JOptionPane.showMessageDialog(null,JOptionPane.ERROR_MESSAGE,"Nie udalo sie stworzyc ksiazki",JOptionPane.ERROR_MESSAGE);
        }
        inflateAllBooksTable();
    }

    private void removeClient(){
        int row = clientTable.getSelectedRow();
        if(row>=0){
            LibraryManagementSystem.getSystem().removeClient(clientMap.get(row));
            inflateClientTable();
        }
    }

    private void removeBook(){
        int row = bookTable.getSelectedRow();
        if(row >=0){
            Book book = bookMap.get(row);
            LibraryManagementSystem.getSystem().removeBook(book);
            inflateAllBooksTable();
        }
    }

    private void removeReservation(){
        int row = reservationTable.getSelectedRow();
        if(row >= 0){
            BookReservation reservation = bookReservationMap.get(row);
            LibraryManagementSystem.getSystem().cancelReservation(reservation);
            inflateReservationTable();
        }
    }

    private void initAllBooksTable(){
        bookTableModel = new DefaultTableModel();
        bookTableModel.addColumn("Nazwa");
        bookTableModel.addColumn("Autor");
        bookTableModel.addColumn("Kategoria");
        bookTableModel.addColumn("Wydawca");
        bookTableModel.addColumn("Dostepnosc");
        bookTable.setModel(bookTableModel);
        inflateAllBooksTable();
    }

    private void inflateAllBooksTable(){
        bookMap = new HashMap<>();
        bookTableModel.setRowCount(0);
        int row =0;
        for (Book book : LibraryManagementSystem.getSystem().getBooks()) {
            String available= book.isAvailable() ? "TAK" : "NIE";
            bookTableModel.addRow(new Object[]{
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

    private void initReservationTable(){
        reserbationTableModel = new DefaultTableModel();
        reserbationTableModel.addColumn("Id Rezerwacji");
        reserbationTableModel.addColumn("Nr Karty Bibliotecznej");
        reserbationTableModel.addColumn("Od");
        reserbationTableModel.addColumn("Do");
        reservationTable.setModel(reserbationTableModel);
        inflateReservationTable();
    }

    private void inflateReservationTable(){
        reserbationTableModel.setRowCount(0);
        bookReservationMap = new HashMap<>();
        int row =0;
        for (Account user : LibraryManagementSystem.getSystem().getUsers()) {
            if(user instanceof Client){
                for (BookReservation bookReservation : ((Client) user).getBookReservations()) {
                    reserbationTableModel.addRow(new Object[]{
                            bookReservation.getId(),
                            ((Client) user).getLibraryCard(),
                            bookReservation.getFrom().toString(),
                            bookReservation.getTo().toString()
                    });
                    bookReservationMap.put(row,bookReservation);
                    row++;
                }
            }
        }

    }

    private void initClientTable(){
        clientTableModel = new DefaultTableModel();
        Object[] columns = new Object[]{"Karta Biblioteczna", "Imię", "Nazwisko","Login","Mail"};
        for (Object column : columns) {
            clientTableModel.addColumn(column);
        }
        clientTable.setModel(clientTableModel);
        inflateClientTable();
    }

    private void inflateClientTable(){
        clientMap = new HashMap<>();
        clientTableModel.setRowCount(0);
        int row=0;
        for (Account user : LibraryManagementSystem.getSystem().getUsers()) {
            if(user instanceof Client){
                clientMap.put(row, (Client)user);
                row++;
                clientTableModel.addRow(new Object[]{
                        ((Client) user).getLibraryCard(),
                        user.getName(),
                        user.getLastName(),
                        user.getLogin(),
                        user.getMail()
                });
            }
        }
    }

    @Override
    public JPanel getPanel() {
        return root;
    }
}
