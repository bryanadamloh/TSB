/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsb;

import java.io.*;
import java.util.Scanner;
public class DailyItemSales implements DailyItemSalesRecord {
    
    @Override
    public void AddItemSales() throws IOException
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("dailysales.txt"));
            Scanner scan = new Scanner(System.in);
            
            String
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
    }
    
    @Override
    public void EditItemSales() throws IOException
    {
        
    }
    
    @Override
    public void DeleteItemSales() throws IOException
    {
        
    }
    
}
