/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsb;

import java.io.*;
import java.util.Scanner;
public class Item implements ItemRecord{
    
    @Override
    public void addItem() throws IOException
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("item.txt"));
            Scanner scan = new Scanner(System.in);

            String code, name, price, supplier;

            System.out.println("Enter Item Code: ");
            code = scan.next();
            System.out.println("Enter Item Name: ");
            name = scan.next();
            System.out.println("Enter Item Price: ");
            price = scan.next();
            System.out.println("Enter Supplier ID: ");
            supplier = scan.next();

            if(br.readLine() == null)
            {
                PrintWriter pw = new PrintWriter("item.txt");
                pw.write(code + ":" + name + ":" + price + ":" + supplier);
                pw.println();
                pw.close();
            }
            else
            {
                BufferedWriter bw = new BufferedWriter(new FileWriter("item.txt", true));
                bw.newLine();
                bw.append(code + ":" + name + ":" + price + ":" + supplier);
                bw.close();
            }

            System.out.println("Item code " + code + " has been successfully added!");
            br.close();            
        }
        catch(IOException i)
        {
            i.printStackTrace();
        }
    }
   
    @Override
    public void modifyItem() throws IOException
    {
        try
        {
            File itemFile = new File("item.txt");
            File tempDB = new File("temp.txt");
            BufferedReader br = new BufferedReader(new FileReader(itemFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));
            Scanner scan = new Scanner(System.in);
            
            String code, name, price, supplier, item;
            
            System.out.println("Enter Item Code to modify: ");
            code = scan.nextLine();
            
            while((item = br.readLine()) != null)
            {
                String[] details = item.split(":");
                String ID = details[0];
                String itemName = details[1];
                String itemPrice = details[2];
                String supplierID = details[3];
                
                if(code.equals(ID))
                {
                    System.out.println("Enter New Item Name; ");
                    name = scan.nextLine();
                    System.out.println("Enter New Item Price: ");
                    price = scan.next();
                    System.out.println("Enter New Supplier ID: ");
                    supplier = scan.next();
                    
                    item = item.replace(itemName, name);
                    item = item.replace(itemPrice, price);
                    item = item.replace(supplierID, supplier);
                    
                    bw.write(item + "\n");
                    bw.flush();
                }
            }
            
            br.close();
            bw.close();
            itemFile.delete();
            tempDB.renameTo(itemFile);
            
            System.out.println("Item Code " + code + " has been succesfully modified!");
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
    }
    
    @Override
    public void viewItem() throws IOException
    {
        
    }
    
    @Override
    public void deleteItem() throws IOException
    {
        try
        {
            File itemFile = new File("item.txt");
            File tempDB = new File("temp.txt");
            BufferedReader br = new BufferedReader(new FileReader(itemFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));
            Scanner scan = new Scanner(System.in);
            
            String ID, item;
            System.out.println("Enter Item Code to delete: ");
            ID = scan.next();
            
            while((item = br.readLine()) != null)
            {
                String[] details = item.split(":");
                String itemID = details[0];
                
                if(!ID.equals(itemID))
                {
                    bw.write(item + "\n");
                    bw.flush();
                }
            }
            
            br.close();
            bw.close();
            itemFile.delete();
            tempDB.renameTo(itemFile);
            
            System.out.println("Item Code " + ID + " has been succesfully deleted!");
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
    }
    
}