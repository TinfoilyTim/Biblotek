/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblotek;

/**
 *
 * @author 05timerl
 */
public class Bok {
    String title;
    String author;
    String isbn;
    boolean stock;
    
   public Bok(){
        this.title = "Unknown title";
        this.author = "Unknown author";
        this.isbn = "Unknown isbn";
        this.stock = false;
        
        
    } 
   
    public Bok (String title, String author, String isbn, boolean stock){
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.stock = stock;
    }
    
    
}