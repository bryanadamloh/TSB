/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsb;

import java.io.*;
import java.util.Scanner;
public class Supplier implements SupplierRecord{
    
    @Override
    public void addSupplier() throws IOException
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("supplier.txt"));
            Scanner scan = new Scanner(System.in);
            
            String code, name, company, itemID;
            
            System.out.println("Enter Supplier ID: ");
            code = scan.next();
            System.out.println("Enter Supplier Name: ");
            name = scan.nextLine();
            System.out.println("Enter Company Name: ");
            company = scan.nextLine();
            System.out.println("Enter Item ID: ");
            itemID = scan.next();
            
            if(br.readLine() == null)
            {
                PrintWriter pw = new PrintWriter("supplier.txt");
                pw.write(code + ":" + name + ":" + company + ":" + itemID);
                pw.println();
                pw.close();
            }
            else
            {
                BufferedWriter bw = new BufferedWriter(new FileWriter("supplier.txt", true));
                bw.newLine();
                bw.write(code + ":" + name + ":" + company + ":" + itemID);
                bw.close();
            }
            
            System.out.println("Supplier " + code + " has been successfully added!");
            br.close(); 
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
    }
    
    @Override
    public void modifySupplier() throws IOException
    {
        
    }
    
    @Override
    public void viewSupplier() throws IOException
    {
        
    }
    
    @Override
    public void deleteSupplier() throws IOException
    {
        
    }
    
}

