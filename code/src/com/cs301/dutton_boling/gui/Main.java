package com.cs301.dutton_boling.gui;

import com.cs301.dutton_boling.Calculator;
import com.cs301.dutton_boling.Input;
import com.cs301.dutton_boling.models.*;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;
import java.util.List;

public class Main extends JDialog {
    private JPanel contentPane;
    private JTextField fileName;
    private JButton openButton;
    private JTextArea output;
    private JButton runButton;
    private File file = null;

    public Main() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentPane(contentPane);
        setModal(true);
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame parent = new Frame();
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                chooser.setFileFilter(new FileNameExtensionFilter("arff file", "arff"));
                chooser.setAcceptAllFileFilterUsed(false);
                if (chooser.showOpenDialog(Main.this) == JFileChooser.APPROVE_OPTION) {
                    file = chooser.getSelectedFile();
                    fileName.setText(file.getName());
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
                    EntrySet entrySet = Input.buildEntries(file);
                    output.append("Calculating Coverings \n");
                    //TODO Let user select decision attributes
                    entrySet.setDecisionAttributes(2, 3, 4);
                    Calculator.calculateRules(entrySet);
                    output.append("Done \n");
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
