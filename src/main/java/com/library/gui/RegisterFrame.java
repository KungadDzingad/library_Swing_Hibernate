package com.library.gui;

import com.library.LibraryManagementSystem;
import com.library.exceptions.PasswordsDontMatchException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame implements GetsPanel{

    private JPanel root;
    private JButton zarejestrujButton;
    private JTextField mailTextField;
    private JTextField peselTextField;
    private JPasswordField passwordTextField;
    private JTextField loginTextField;
    private JTextField nameTextField;
    private JTextField lastNameTextField;
    private JPasswordField repeatPasswordTextField;
    private JLabel errorLabel;

    public RegisterFrame(){
        errorLabel.setVisible(false);
        zarejestrujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    register();
                } catch (PasswordsDontMatchException exc) {
                    errorLabel.setVisible(true);
                }
            }
        });
    }

    private void register() throws PasswordsDontMatchException {
        String password = passwordTextField.getText();
        String password2 = repeatPasswordTextField.getText();
        if(!password.equals(password2))
            throw new PasswordsDontMatchException();

        String mail = mailTextField.getText();
        String login = loginTextField.getText();
        String name = nameTextField.getText();
        String lastName = lastNameTextField.getText();
        long pesel = Long.parseLong(peselTextField.getText());

        if(LibraryManagementSystem.getSystem().registerClient(mail,login,password,name,lastName,pesel)) {
            int option = JOptionPane.showConfirmDialog(null,null,"Pomyslna Rejestracja",JOptionPane.OK_OPTION);
            if(option == JOptionPane.OK_OPTION) {
                MainFrame.getFrame().setPanel(new LoginFrame());
            }
        }else{
            errorLabel.setVisible(true);
        }

    }

    @Override
    public JPanel getPanel() {
        return root;
    }


}
