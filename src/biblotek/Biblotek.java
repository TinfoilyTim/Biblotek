/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblotek;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Boolean.parseBoolean;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author 05timerl
 */
public class Biblotek {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Bok> books = new ArrayList<>();
        List<String> temp = new ArrayList<>();
          try (BufferedReader reader = new BufferedReader(new FileReader("src/biblotek/library.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                temp.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    
        int lastNr = 0;
        for (int i = 0; i < temp.size()/4; i++) {
            books.add(new Bok(temp.get(0 + lastNr), temp.get(1 + lastNr),temp.get(2 + lastNr), parseBoolean(temp.get(3 + lastNr))));
            lastNr += 4;
        }
        
        
        addBook();
       System.out.println(books.get(1).author);
    }

    private static void addBook() {
        for (int i = 0; i < 3; i++) {
            try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("src/biblotek/library.txt", true)));
            while(true){
                String in = JOptionPane.showInputDialog("Skriv in ett namn");
                
                if (in.equals("")) {
                    break;
                }
                else{
                    pw.println(in);
                    in = JOptionPane.showInputDialog("Skriv in din score");
                    pw.println(in);
                }
                
 
            }
            pw.close();
        }
        catch (IOException e){
            System.out.println("Kunde inte spara till filen");
        }
    }

    
    
    } 
}    

