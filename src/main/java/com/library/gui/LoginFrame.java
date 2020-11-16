package com.library.gui;

import javax.swing.*;

public class LoginForm implements GetsPanel{


    private JPanel root;
    private JButton button1;
    private JPasswordField passwordField;
    private JTextField loginField;
    private JCheckBox jestemPracownikiemCheckBox;

    private SetsPanel setter;

    public LoginForm(SetsPanel panelSetter){
        setter = panelSetter;
    }

    @Override
    public JPanel getPanel() {
        return root;
    }
}
