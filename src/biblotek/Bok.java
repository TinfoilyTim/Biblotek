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
    String author;
    String title;
    String isbn;
    boolean stock;
    
   public Bok(){
        this.author = "Unknown author";
        this.title = "Unknown title";
        this.isbn = "Unknown isbn";
        this.stock = false;
    } 
   
    public Bok (String author, String title, String isbn, boolean stock){
        this.author = author;
        this.title = title;
        this.isbn = isbn;
        this.stock = stock;
    }
    
}