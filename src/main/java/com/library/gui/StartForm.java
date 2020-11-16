package com.library.gui;

import javax.swing.*;

public class StartFrame implements GetsPanel{
    private JPanel root;

    private SetsPanel panelSetter;

    public StartFrame(SetsPanel setsPanel){
        panelSetter = setsPanel;
    }

    @Override
    public JPanel getPanel() {
        return root;
    }
}
