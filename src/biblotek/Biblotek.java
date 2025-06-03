/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblotek;

import static biblotek.Biblotek.books;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Boolean.parseBoolean;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 05timerl
 */
public class Biblotek {
   static List<Bok> books = new ArrayList<>();
   
   
    public static void main(String[] args) {
        
        
          loginGui();
        
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
        
             // Skapa frame
        JFrame frame = new JFrame("Library");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       
        // Tabell modell, användaren kan inte skriva i
        String[] columns = {"Author", "Title", "In Stock"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
        public boolean isCellEditable(int row, int column) {
        return false; 
    }
       
};

    
    
          
        
         // lägger till böcker 
        for (Bok b : books) {
            model.addRow(new Object[]{b.author,b.title , b.stock ? "Yes" : "No"});
        }

        // Skapar tabellen
        JTable table = new JTable(model);

        // Gör tabellen skrollbar
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(); // uses FlowLayout by default
        JButton borrowButton = new JButton("Borrow Selected Book");
        JButton returnButton = new JButton("Return Selected Book");
        buttonPanel.add(borrowButton);
        buttonPanel.add(returnButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);


        borrowButton.addActionListener(new ActionListener() {
            @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a book to borrow.");
            return;
        }

        Bok selectedBook = books.get(selectedRow);
        if (selectedBook.stock == false) {
            JOptionPane.showMessageDialog(frame, "This book is already borrowed.");
        } else {
            selectedBook.stock = false;
            model.setValueAt("No", selectedRow, 2); // Update "In Stock" column
            JOptionPane.showMessageDialog(frame, "You have borrowed: " + selectedBook.title);
        }
        
         reDraw();       //uppdaterar textfilen efter varje lån
    }
    });
       
        returnButton.addActionListener(new ActionListener() {
            @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a book to borrow.");
            return;
        }

        Bok selectedBook = books.get(selectedRow);
        
        if (selectedBook.stock == true) {
            JOptionPane.showMessageDialog(frame, "You dont have this book.");
        } else {
            selectedBook.stock = true;
            model.setValueAt("Yes", selectedRow, 2); // Update "In Stock" column
            JOptionPane.showMessageDialog(frame, "You have returned: " + selectedBook.title);
        }
         reDraw();       //refreshar textfilen after varje lån
    
    }
    
});

        frame.add(panel);
        // Make the frame visible
        frame.setVisible(true);

        // addBook();
        for (int i = 0; i < books.size(); i++) {
            
            
        }
       
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
                    in = JOptionPane.showInputDialog("Skriv in ett namn");
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
    public static void reDraw(){
            try (PrintWriter pw = new PrintWriter("src/biblotek/library.txt")) {
                                                // Tömmer filen inför append(ix)
            } catch (IOException e) {
    System.out.println("Kunde inte radera filens innehåll: " + e.getMessage());
}
  
        for (int i = 0; i < books.size(); i++) {
           
                try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("src/biblotek/library.txt", true)));      
            pw.println(books.get(i).author);
            pw.println(books.get(i).title);
            pw.println(books.get(i).isbn);
            pw.println(books.get(i).stock);
            pw.close();
            }
            
        
        catch (IOException e){
            System.out.println("Kunde inte spara till filen");
        }  
    }
}
    public static void createUser(){
        String username = JOptionPane.showInputDialog("skriv användarnamn", null);
        String password = JOptionPane.showInputDialog("skriv lösenord", null);
        User user = new User(username,password);
            try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("src/biblotek/users.txt", true)));
            pw.println("Username=" + username);
            pw.println("Password=" + password);
            pw.println("LoggedIn=" + "false");
            pw.println("Borrowed=" + "null");
            pw.println("Admin=" + "false");
            pw.println();
            pw.close();
            }
            
        
        catch (IOException e){
            System.out.println("Kunde inte spara till filen");
        }
        
        
    }
    public static void login(){
        
    }
    public class LoginGUI {
        public static void loginGui() {
            JFrame frame = new JFrame("Logga in");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 180);
            frame.setLocationRelativeTo(null);

            JPanel panel = new JPanel(new GridLayout(4, 2));

            JLabel userLabel = new JLabel("Användarnamn:");
            JTextField userText = new JTextField();

            JLabel passLabel = new JLabel("Lösenord:");
            JPasswordField passText = new JPasswordField();

            JButton loginButton = new JButton("Logga in");
            JButton registerButton = new JButton("Registrera");

            panel.add(userLabel);
            panel.add(userText);
            panel.add(passLabel);
            panel.add(passText);
            panel.add(loginButton);
            panel.add(registerButton);

            frame.getContentPane().add(panel);
            frame.setVisible(true);
        }
}
}