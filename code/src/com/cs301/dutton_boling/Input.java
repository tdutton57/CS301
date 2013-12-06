package com.cs301.dutton_boling;

import com.cs301.dutton_boling.factories.EntryFactory;
import com.cs301.dutton_boling.models.Entry;
import com.cs301.dutton_boling.models.EntrySet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Stewart
 * Date: 10/20/13
 * Time: 8:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class Input {

    public static EntrySet buildEntries(File file){
        EntrySet entrySet = new EntrySet();
        EntryFactory ef = new EntryFactory();
        List<Entry> entries = new ArrayList<Entry>();
        List<String> names = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while (!(line = br.readLine()).startsWith("@attribute"));
            do{
                String[] attribute = line.split(" ");
                names.add(attribute[1]);
                if(attribute[2].equals("numeric")){
                    ef.add(attribute[1], EntryFactory.AttributeType.NUMERIC);
                }else if (attribute[2].equals("binary")){
                    ef.add(attribute[1], EntryFactory.AttributeType.BINARY);
                }else if(attribute[2].contains("{")){
                    String strip = attribute[2].replace("{", "");
                    strip = strip.replace("}", "");
                    String[] vals = strip.split(",");
                    ef.add(attribute[1], EntryFactory.AttributeType.NOMINAL, vals);
                }
            }while ((line = br.readLine()).startsWith("@attribute"));
            while (!(line = br.readLine()).equals("@data"));
            while ((line = br.readLine()) != null){
                entries.add(ef.buildEntry(line.split(",")));
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        entrySet.setAttributeNames(names);
        entrySet.setEntries(entries);
        return entrySet;
    }
}
