package com.library.gui;

import com.library.Account;
import com.library.LibraryManagementSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginFrame implements GetsPanel{


    private JPanel root;
    private JButton loginButton;
    private JPasswordField passwordField;
    private JTextField loginField;
    private JCheckBox jestemPracownikiemCheckBox;
    private JLabel wrongDataLabel;


    public LoginFrame( ){
        wrongDataLabel.setVisible(false);
        initListeners();
    }

    private void initListeners(){
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginField.getText();
                String password = passwordField.getText();
                if(jestemPracownikiemCheckBox.isSelected()){
                   if(LibraryManagementSystem.getSystem().loginWorker(login,password)){
                        MainFrame.getFrame().setPanel(new WorkerFrame());
                   }else{
                       wrongDataLabel.setVisible(true);
                       MainFrame.getFrame().pack();
                   }
                }else{
                    boolean logged = LibraryManagementSystem.getSystem().loginUser(login,password);
                    if(logged) {
                        MainFrame.getFrame().setPanel(new ClientFrame());
                    }else{
                        wrongDataLabel.setVisible(true);
                        MainFrame.getFrame().pack();
                    }
                }
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return root;
    }
}
