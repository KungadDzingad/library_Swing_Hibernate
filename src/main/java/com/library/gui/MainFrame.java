package com.library.gui;


import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame{

    private static MainFrame frame;

    public static MainFrame getFrame(){
        if(frame == null)
            frame = new MainFrame();
        return frame;
    }

    private JPanel root;
    private GetsPanel panelGetter;

    private MainFrame(){
        setPanel(new StartForm());
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public void setPanel(GetsPanel getter) {
        panelGetter = getter;

        if(root!=null)
            remove(root);
        setLayout(new GridLayout());
        root = panelGetter.getPanel();
        add(root);
        setContentPane(root);
        pack();
        //setSize();
    }

    public GetsPanel getPanelGetter(){
        return panelGetter;
    }
    private void setSize(){
        setSize(400,400);
    }
}
