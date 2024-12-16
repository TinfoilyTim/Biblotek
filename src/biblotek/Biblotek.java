/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblotek;

import static java.lang.Boolean.parseBoolean;
import java.util.ArrayList;
import java.util.List;

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
        temp.add("To the lighthouse");
        temp.add("Virginia Woolf");
        temp.add("978-0141183411");
        temp.add("true");
        temp.add("Pride and Prejudice");
        temp.add("Jane Austen");
        temp.add("978-0141439518");
        temp.add("true");
        int lastNr = 0;
        
        for (int i = 0; i < temp.size()/4; i++) {
            books.add(new Bok(temp.get(0 + lastNr), temp.get(1 + lastNr),temp.get(2 + lastNr), parseBoolean(temp.get(3 + lastNr))));
            lastNr += 4;
        }
        
        
        System.out.println(books.get(1).title);
    }
    
}
