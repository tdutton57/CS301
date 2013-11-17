package com.cs301.dutton_boling.gui;

import com.cs301.dutton_boling.Calculator;
import com.cs301.dutton_boling.Input;
import com.cs301.dutton_boling.models.*;


import javax.swing.*;
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
                    EntrySet entrySet = Input.buildEntries(file);
                    //TODO Let user select decision attributes
                    entrySet.setDecisionAttributes(2,3,4);
                    testSets();
                    Calculator.calculateRules(entrySet);
                    System.out.println("Done");
                }else{

                }
            }
        });
    }

    private void testSets() {
        Nominal attr1 = new Nominal("42");
        Binary attr2 = new Binary(true);
        Nominal attr3 = new Nominal("87");
        Nominal attr4 = new Nominal("42");
        Binary attr5 = new Binary(true);
        Nominal attr6 = new Nominal("87");

        Set<Attribute> set1 = new HashSet<Attribute>();
        set1.add(attr1);
        set1.add(attr2);
        set1.add(attr3);

        Set<Attribute> set2 = new HashSet<Attribute>();
        set2.add(attr1);
        set2.add(attr3);
        set2.add(attr2);

        Set<Attribute> set3 = new HashSet<Attribute>();
        set3.add(attr3);
        set3.add(attr1);
        set3.add(attr2);

        Set<Attribute> set4 = new HashSet<Attribute>();
        set4.add(attr2);
        set4.add(attr1);

        Set<Attribute> set5 = new HashSet<Attribute>();
        set5.add(attr4);
        set5.add(attr5);
        set5.add(attr6);

        Boolean test = set1.equals(set2);
        Boolean test2 = set2.equals(set3);
        Boolean test3 = set3.equals(set1);
        Boolean test5 = set1.equals(set5);

        List<Attribute> listOne = new ArrayList<Attribute>();
        List<Attribute> listTwo = new ArrayList<Attribute>();

        listOne.add(attr1);
        listOne.add(attr2);
        listOne.add(attr3);

        listTwo.add(attr4);
        listTwo.add(attr5);
        listTwo.add(attr6);



        Boolean test6 = listOne.equals(listTwo);

        Attribute attribute = new Binary(true);
        Attribute attribute1 = new Binary(true);

        Boolean bool = new Boolean(true);
        Boolean bool1 = new Boolean(true);

        Boolean test7 = attribute.equals(attribute1);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(test).append(" ").append(test2).append(" ").append(test3).append(" ").append(test5).append(" ").append(test6).append(" ").append(test7);
        System.out.println(stringBuilder.toString());

        Map<Set<Attribute>, Integer> setIntegerMap = new HashMap<Set<Attribute>, Integer>();

        setIntegerMap.put(set1, 2);

        System.out.println(setIntegerMap.get(set2));
        System.out.println(setIntegerMap.get(set3));

        Map<Set<Attribute>, List<Integer>>  setListMap = new HashMap<Set<Attribute>, List<Integer>>();
        List<Integer> list1 = new ArrayList<Integer>();
        list1.add(1);
        list1.add(2);

        setListMap.put(set1, list1);
        Boolean test4 = setListMap.containsKey(set2);
        List<Integer> list2 = setListMap.get(set2);
        List<Integer> list3 = setListMap.get(set4);

        System.out.println("Done");

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
