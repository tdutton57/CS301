package com.cs301.dutton_boling.gui;

import com.cs301.dutton_boling.Calculator;
import com.cs301.dutton_boling.Input;
import com.cs301.dutton_boling.models.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;
import java.util.List;

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
    JSlider minOccurances;
    JTextField minField;
    JCheckBox minCoverage;
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
                            decisionAttrList.repaint();
                            entrySet.getAttributeNames().toArray(attrs);
                            sizeSlider.setMaximum(entrySet.getAttributeCount());
                            sizeSlider.setValue(1);
                            minOccurances.setMaximum(entrySet.getEntries().size());
                            minOccurances.setValue(1);


                        }
                    }).run();
                } else {
                    System.out.println("No Selection ");
                    file = null;
                }
            }
        });
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(openButton, gbc);

        decisionAttrList = new JList<String>(attrs);
        decisionAttrList.setLayoutOrientation(JList.VERTICAL_WRAP);
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

        minField = new JTextField();
        minField.setEnabled(false);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(minField, gbc);

        minOccurances = new JSlider();
        minOccurances.setValue(1);
        minOccurances.setBorder(new TitledBorder("Minimum Number Of Occurrences For Valid Rule"));
        minOccurances.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                minField.setText(String.valueOf(minOccurances.getValue()));
            }
        });
        minOccurances.setMinimum(1);
        minOccurances.setMaximum(100);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(minOccurances, gbc);

        minCoverage = new JCheckBox("Minimal Coverage");
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(minCoverage, gbc);


        outputArea = new JTextArea(20, 20);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.gridheight = 3;
        gbc.weighty = 1;
        add(scrollPane, gbc);


        runButton = new JButton("Run");
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runButton.setEnabled(false);
                outputArea.setText("Calculating");
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        int minimum = minOccurances.getValue();




                        List<Integer> items = new ArrayList<Integer>();
                        for (String decision : decisionAttrList.getSelectedValuesList()) {
                            items.add(entrySet.getAttributeNames().indexOf(decision));
                        }
                        entrySet.setDecisionAttributes(items);

                        //INFO

                        StringBuilder builder = new StringBuilder();
                        builder.append("Parameters: \n");
                        builder.append("Decision Attributes: ");
                        builder.append(entrySet.getDecisionArrtibuteNames());
                        builder.append("\n\n");
                        builder.append("Minimum Occurrences For Valid Rule: ");
                        builder.append(minimum);
                        builder.append("\n\n");
                        builder.append("Maximum Coverage Size: ");
                        builder.append(sizeSlider.getValue());
                        builder.append("\n\n");
                        builder.append("Calculate Minimal Coverings: ");
                        builder.append(minCoverage.isSelected());
                        builder.append("\n\n");
                        builder.append("Statisics: ");
                        builder.append("\n");
                        Set<Set<Integer>> decisionSet = powerSet(new HashSet<Integer>(entrySet.getDecisionAttributes()));
                        for(Set<Integer> set : decisionSet){
                            if(!set.isEmpty()){
                                builder.append("Distribution of values for ");
                                builder.append(entrySet.getNamesForSet(set));
                                builder.append(":\n");
                                Map<Set<Attribute>, Integer> setIntegerMap = new HashMap<Set<Attribute>, Integer>();
                                for(Entry entry : entrySet.getEntries()){
                                    Set<Attribute> attributes =  entry.getAttributes(set);
                                    if(setIntegerMap.containsKey(attributes)){
                                        setIntegerMap.put(attributes, setIntegerMap.get(attributes) + 1);
                                    }else {
                                        setIntegerMap.put(attributes, 1);
                                    }
                                }
                                for(Set<Attribute> attributes : setIntegerMap.keySet()){
                                    builder.append(attributes);
                                    builder.append(" Occurrences: ");
                                    builder.append(setIntegerMap.get(attributes));
                                    builder.append("\n");
                                }
                                builder.append("\n");
                            }
                        }
                        Map<Covering, List<Rule>> rules = Calculator.calculateRules(entrySet, sizeSlider.getValue(), minCoverage.isSelected());
                        Map<Covering, List<Rule>> outputRules = new HashMap<Covering, List<Rule>>();
                        for (Covering covering : rules.keySet()) {
                            Map<Rule, Integer> ruleIntegerMap = new HashMap<Rule, Integer>();
                            for (Rule rule : rules.get(covering)) {
                                if (ruleIntegerMap.containsKey(rule)) {
                                    ruleIntegerMap.put(rule, ruleIntegerMap.get(rule) + 1);
                                } else {
                                    ruleIntegerMap.put(rule, 1);
                                }
                            }

                            for (Rule rule : ruleIntegerMap.keySet()) {
                                int occurs = ruleIntegerMap.get(rule);
                                if (occurs >= minimum) {
                                    if(!outputRules.containsKey(covering)){
                                        outputRules.put(covering, new ArrayList<Rule>());
                                    }
                                    outputRules.get(covering).add(rule);
                                }
                            }
                        }



                        if (outputRules.isEmpty()) {
                            builder.append("No Rules Found");
                            outputArea.setText(builder.toString());
                        } else {

                            for (Covering covering : outputRules.keySet()) {
                                Map<Rule, Integer> ruleIntegerMap = new HashMap<Rule, Integer>();
                                for (Rule rule : rules.get(covering)) {
                                    if (ruleIntegerMap.containsKey(rule)) {
                                        ruleIntegerMap.put(rule, ruleIntegerMap.get(rule) + 1);
                                    } else {
                                        ruleIntegerMap.put(rule, 1);
                                    }
                                }
                                builder.append("Rules for covering: " + covering.toString() + "\n");

                                for (Rule rule : ruleIntegerMap.keySet()) {
                                    int occurs = ruleIntegerMap.get(rule);
                                    if (occurs >= minimum) {
                                        builder.append("\t");
                                        builder.append(rule.toString());
                                        builder.append(" Occurrences: ");
                                        builder.append(occurs);
                                        builder.append("\n");
                                    }
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
        gbc.gridy = 9;
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

    public static Set<Set<Integer>> powerSet(Set<Integer> originalSet) {
        Set<Set<Integer>> sets = new HashSet<Set<Integer>>();
        if (originalSet.isEmpty()) {
            sets.add(new HashSet<Integer>());
            return sets;
        }
        List<Integer> list = new ArrayList<Integer>(originalSet);
        Integer head = list.get(0);
        Set<Integer> rest = new HashSet<Integer>(list.subList(1, list.size()));
        for (Set<Integer> set : powerSet(rest)) {
            Set<Integer> newSet = new HashSet<Integer>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }
        return sets;
    }


}
