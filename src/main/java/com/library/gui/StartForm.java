package com.library.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartForm implements GetsPanel{
    private JPanel root;
    private JButton loginButton;
    private JButton registerButton;


    public StartForm(){

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.getFrame().setPanel(new LoginFrame());
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.getFrame().setPanel(new RegisterFrame());
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return root;
    }
}
