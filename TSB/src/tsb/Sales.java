/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsb;

import java.text.*;
public class Sales {
    
    private double price = 0.00;
    private int qty = 0;
    
    public double calculateTotal()
    {
        double total;
        total = price * qty;
        return total;
    }
    
    public void setQuantity(int qty)
    {
        this.qty = qty;
    }
    
    public void setPrice(double price)
    {
        this.price = price;
    }
        
}   

