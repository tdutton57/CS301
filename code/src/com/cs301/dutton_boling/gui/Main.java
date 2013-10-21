package com.cs301.dutton_boling.gui;

import com.cs301.dutton_boling.Input;
import com.cs301.dutton_boling.models.Entry;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class Main extends JDialog {
    private JPanel contentPane;
    private JTextField fileName;
    private JButton openButton;
    private JTextArea output;
    private JButton runButton;
    private File file = null;

    public Main() {
        setContentPane(contentPane);
        setModal(true);
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame parent = new Frame();
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                chooser.setAcceptAllFileFilterUsed(false);
                if (chooser.showOpenDialog(Main.this) == JFileChooser.APPROVE_OPTION) {
                    file = chooser.getSelectedFile();
                }
                else {
                    System.out.println("No Selection ");
                    file = null;
                }
            }

        });

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(file != null){
                    List<Entry> entires = Input.buildEntries(file);
                    System.out.println("Done");
                }else{

                }
            }
        });
    }


    public Dimension getPreferredSize(){
        return new Dimension(600, 600);
    }


    public static void main(String[] args) {
        Main dialog = new Main();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
