package com.cs301.dutton_boling.gui;

import com.cs301.dutton_boling.Calculator;
import com.cs301.dutton_boling.Input;
import com.cs301.dutton_boling.models.Covering;
import com.cs301.dutton_boling.models.EntrySet;
import com.cs301.dutton_boling.models.Rule;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Stewart
 * Date: 12/4/13
 * Time: 9:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main extends JApplet {

    JTextField fileName;
    JButton openButton;
    JButton runButton;
    JTextArea outputArea;
    JList<String> decisionAttrList;
    JSlider sizeSlider;
    JTextField sizeField;
    String[] attrs = new String[]{"Select a File"};
    File file = null;
    EntrySet entrySet = null;

    @Override
    public void init() {
        super.init();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        fileName = new JTextField("No File Selected");
        fileName.setEnabled(false);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        add(fileName, gbc);

        openButton = new JButton("Open");
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
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            entrySet = Input.buildEntries(file);
                            attrs = new String[entrySet.getAttributeCount()];
                            decisionAttrList.setListData(attrs);
                            entrySet.getAttributeNames().toArray(attrs);
                            sizeSlider.setMaximum(entrySet.getAttributeCount());

                        }
                    }).run();
                } else {
                    System.out.println("No Selection ");
                    file = null;
                }
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(openButton, gbc);

        decisionAttrList = new JList<String>(attrs);
        decisionAttrList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        decisionAttrList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        decisionAttrList.setBorder(new TitledBorder("Decision Attributes"));
        decisionAttrList.setFixedCellHeight(35);
        decisionAttrList.setFixedCellHeight(35);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(decisionAttrList, gbc);

        sizeField = new JTextField();
        sizeField.setEnabled(false);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(sizeField, gbc);

        sizeSlider = new JSlider();
        sizeSlider.setValue(1);
        sizeSlider.setBorder(new TitledBorder("Max Covering Size"));
        sizeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                sizeField.setText(String.valueOf(sizeSlider.getValue()));
            }
        });
        sizeSlider.setMinimum(1);
        sizeSlider.setMaximum(77);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(sizeSlider, gbc);

        outputArea = new JTextArea(20, 20);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight = 3;
        gbc.weighty = 1;
        add(scrollPane, gbc);


        runButton = new JButton("Run");
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runButton.setEnabled(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<Integer> items = new ArrayList<Integer>();
                        for (String decision : decisionAttrList.getSelectedValuesList()) {
                            items.add(entrySet.getAttributeNames().indexOf(decision));
                        }
                        entrySet.setDecisionAttributes(items);
                        Map<Covering, List<Rule>> rules = Calculator.calculateRules(entrySet, sizeSlider.getValue());
                        if (rules.isEmpty()) {
                            outputArea.setText("No Rules Found");
                        } else {
                            Set<Covering> coveringSet = rules.keySet();
                            StringBuilder builder = new StringBuilder();
                            for (Covering covering : coveringSet) {

                                builder.append(covering.toString() + "\n");
                                for (Rule rule : rules.get(covering)) {
                                    builder.append("\t");
                                    builder.append(rule.toString());
                                    builder.append("\n");
                                }
                            }
                            outputArea.setText(builder.toString());
                        }

                        runButton.setEnabled(true);
                    }
                }).run();
            }
        });
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        add(runButton, gbc);


    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("RICO");
        Main main = new Main();
        frame.setBounds(0, 0, 700, 900);
        //frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        frame.getContentPane().add(main);
        main.init();
        frame.setVisible(true);
    }


}
