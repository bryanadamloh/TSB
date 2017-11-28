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
    private int oldQty;
    private int totalQty;
    
    /**
     * This is the calculate the total sales
     * for Daily Sales Item.
     * @return total;
     */
    public double calculateTotal()
    {
        double total;
        total = price * qty;
        return total;
    }
    
    /**
     * This is to calculate the total new quantity after
     * daily sales item quantity has been modified.
     * @return totalNewQty;
     */
    public int calculateTotalQty()
    {
        int totalNewQty;
        totalNewQty = totalQty + oldQty - qty;
        return totalNewQty;
    }
    
    /**
     * Set the quantity of the item.
     * @param qty 
     */
    public void setQuantity(int qty)
    {
        this.qty = qty;
    }
    
    /**
     * Set the price of the item.
     * @param price 
     */
    public void setPrice(double price)
    {
        this.price = price;
    }
    
    /**
     * Set the quantity which the user has entered
     * in the modify section of DSI.
     * @param qty 
     */
    public void setModifiedQty(int qty)
    {
        this.qty = qty;
    }
    
    /**
     * Set the quantity which haven't been modified
     * in Daily Sales Item text file.
     * @param oldQty 
     */
    public void setOldQty(int oldQty)
    {
        this.oldQty = oldQty;
    }
    
    /**
     * Set the quantity of the item in
     * Item text file.
     * @param totalQty 
     */
    public void setItemQty(int totalQty)
    {
        this.totalQty = totalQty;
    }
}   

