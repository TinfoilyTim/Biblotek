/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this booksTemplate file, choose Tools | Templates
 * and open the booksTemplate in the editor.
 */
package biblotek;

import static biblotek.Biblotek.GUI.loginGui;
import static biblotek.Biblotek.GUI.showLibrary;
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
   static List<Bok>  books = new ArrayList<>();
   static List<User> users = new ArrayList<>();
   static int currentUser = 0;
   
   
//LADDAR ANVÄNDARINFORMATION I users LISTAN
   public static void loadCredentials(){
            List<String> usersTemp = new ArrayList<>();
          try (BufferedReader reader = new BufferedReader(new FileReader("src/biblotek/users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                usersTemp.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
          int lastNr = 0;
        for (int i = 0; i < usersTemp.size()/5; i++) {
            users.add(new User(usersTemp.get(0 + lastNr), usersTemp.get(1 + lastNr),Boolean.parseBoolean(usersTemp.get(2 + lastNr)), null,Boolean.parseBoolean(usersTemp.get(4 + lastNr) )));
            lastNr += 5;
        }
   }
   
   
   //MAIN
    public static void main(String[] args) {
        
        loadCredentials();
        loginGui();

               List<String> booksTemp = new ArrayList<>();
          try (BufferedReader reader = new BufferedReader(new FileReader("src/biblotek/library.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                booksTemp.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    
        int lastNr = 0;
        for (int i = 0; i < booksTemp.size()/4; i++) {
            books.add(new Bok(booksTemp.get(0 + lastNr), booksTemp.get(1 + lastNr),booksTemp.get(2 + lastNr), parseBoolean(booksTemp.get(3 + lastNr))));
            lastNr += 4;
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
    
        //SKRIVER ÖVER LIBRARY FILEN
    public static void reDrawBooks(){
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
        //SKRIVER ÖVER LIBRARY FILEN
    public static void reDrawUsers(){
            try (PrintWriter pw = new PrintWriter("src/biblotek/users.txt")) {
                                                // Tömmer filen inför append(ix)
            } catch (IOException e) {
    System.out.println("Kunde inte radera filens innehåll: " + e.getMessage());
}
  
        for (int i = 0; i < books.size(); i++) {
           
                try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("src/biblotek/users.txt", true)));      
            pw.println(users.get(i).username);
            pw.println(users.get(i).password);
            pw.println(users.get(i).loggedIn);
            pw.println(users.get(i).borrowed);
            pw.println(users.get(i).admin);
            pw.close();
            }
            
        
        catch (IOException e){
            System.out.println("Kunde inte spara till filen");
        }  
    }
}
        //SKRIV ANVÄNDARE TILL TEXTFIL
    public static void createUser(String userN, String passW){
        String username = userN;
        String password = passW;
        
            try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("src/biblotek/users.txt", true)));
            pw.println(username);
            pw.println(password);
            pw.println("false");
            pw.println("null");
            pw.println("false");
            pw.close();
            }
            
        
        catch (IOException e){
            System.out.println("Kunde inte spara till filen");
        }
        
        
    }
    public static void login(){
        
    }
        //VISA LOGIN
   public class GUI {
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

        //LOGIN KNAPP
        loginButton.addActionListener((ActionEvent e) -> {
            String username = userText.getText();
            String password = new String(passText.getPassword());
            
            
            boolean loginSuccess = false;
            
            for (int i = 0; i < users.size(); i++) {
                if (password.equals(users.get(i).password) && username.equals(users.get(i).username)) {
                    loginSuccess = true;;
                    
                    if (users.get(i).admin == true) {
                        frame.dispose(); 
                        showLibraryAdmin();
                    } else {
                        frame.dispose(); 
                        showLibrary();
                        JOptionPane.showMessageDialog(null, "Inloggad som " + users.get(i).username);
                    }
                    break;
                }
            }
            
            if (!loginSuccess) {            
                JOptionPane.showMessageDialog(null, "Fel användarnamn eller lösenord!");
            }
        });
        //REGISTRERA
         registerButton.addActionListener((ActionEvent e) -> {
             createUser(userText.getText(),new String(passText.getPassword()));
             JOptionPane.showMessageDialog(null, "Användare registrerad!");
             loadCredentials();
        });
    }
        //VISA BIBLOTEK
    public static void showLibrary() {
        JFrame frame = new JFrame("Library");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columns = {"Author", "Title", "In Stock"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Bok b : books) {
            model.addRow(new Object[]{b.author, b.title, b.stock ? "Yes" : "No"});
        }

        JTable table = new JTable(model);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton borrowButton = new JButton("Borrow Selected Book");
        JButton returnButton = new JButton("Return Selected Book");
        buttonPanel.add(borrowButton);
        buttonPanel.add(returnButton);
             
        
        panel.add(buttonPanel, BorderLayout.SOUTH);

        
        
        //LÅNA KNAPP
        borrowButton.addActionListener((ActionEvent e) -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a book to borrow.");
            }
            
            Bok selectedBook = books.get(selectedRow);
            if (!selectedBook.stock) {
                JOptionPane.showMessageDialog(frame, "This book is already borrowed.");
            } else {
                selectedBook.stock = false;
                model.setValueAt("No", selectedRow, 2);
                JOptionPane.showMessageDialog(frame, "You have borrowed: " + selectedBook.title);
                //reDrawUsers();
                reDrawBooks();
            }
        });
        
        //LÄMNA TILLBAKA KNAPP
        returnButton.addActionListener((ActionEvent e) -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a book to return.");
                return;
            }
            
            Bok selectedBook = books.get(selectedRow);
            if (selectedBook.stock) {
                JOptionPane.showMessageDialog(frame, "This book is not borrowed.");
            } else {
                selectedBook.stock = true;
                model.setValueAt("Yes", selectedRow, 2);
                JOptionPane.showMessageDialog(frame, "You have returned: " + selectedBook.title);
                reDrawBooks();
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
    
            //VISA BIBLOTEK ADMIN
    public static void showLibraryAdmin() {
        JFrame frame = new JFrame("Library");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columns = {"Author", "Title", "In Stock", "ISBN"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Bok b : books) {
            model.addRow(new Object[]{b.author,b.title , b.stock ? "Yes" : "No", b.isbn});
        }

        JTable table = new JTable(model);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton borrowButton = new JButton("Borrow Selected Book");
        JButton returnButton = new JButton("Return Selected Book");
        JButton addButton = new JButton("+");
        JButton removeButton = new JButton("-");
        buttonPanel.add(borrowButton);
        buttonPanel.add(returnButton);
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        
        panel.add(buttonPanel, BorderLayout.SOUTH);

        
        
        //LÅNA KNAPP
        borrowButton.addActionListener((ActionEvent e) -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a book to borrow.");
            }
            
            Bok selectedBook = books.get(selectedRow);
            if (!selectedBook.stock) {
                JOptionPane.showMessageDialog(frame, "This book is already borrowed.");
            } else {
                selectedBook.stock = false;
                model.setValueAt("No", selectedRow, 2);
                JOptionPane.showMessageDialog(frame, "You have borrowed: " + selectedBook.title);
                reDrawBooks();
            }
        });
        
        //LÄMNA TILLBAKA KNAPP
        returnButton.addActionListener((ActionEvent e) -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a book to return.");
                return;
            }
            
            Bok selectedBook = books.get(selectedRow);
            if (selectedBook.stock) {
                JOptionPane.showMessageDialog(frame, "This book is not borrowed.");
            } else {
                selectedBook.stock = true;
                model.setValueAt("Yes", selectedRow, 2);
                JOptionPane.showMessageDialog(frame, "You have returned: " + selectedBook.title);
                reDrawBooks();
            }
        });
        
        //LÄGG TILL BOK KNAPP
        addButton.addActionListener((ActionEvent e) -> {
            String title = JOptionPane.showInputDialog("Skriv bokens titel:");
            if(title.equals("")){
                // försökte med !title men funkade inte hur jag än gjorde, därav en tom if sats
            }
            else{
                String author = JOptionPane.showInputDialog("Skriv bokens författare:");
                String isbn = JOptionPane.showInputDialog("ISBN för boken:");
                books.add(new Bok (title, author, isbn , true));
                reDrawBooks();
                frame.dispose();
                showLibraryAdmin();
            }
        }); 

        //TA BORT BOK KNAPP
        removeButton.addActionListener((ActionEvent e) -> {
            String remove = JOptionPane.showInputDialog("ISBN för boken som ska tas bort");
            
            if (remove == null || remove.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingen ISBN angiven.");
            } else {
                boolean removed = books.removeIf(book -> book.isbn.equals(remove.trim()));
                
                if (removed) {
                    reDrawBooks();
                    JOptionPane.showMessageDialog(null, "Boken togs bort.");
                } else {
                    JOptionPane.showMessageDialog(null, "Ingen bok med denna ISBN hittades.");
                }
                
                frame.dispose();
                showLibraryAdmin();
            }
        });


        frame.add(panel);
        frame.setVisible(true);
    }
}

}