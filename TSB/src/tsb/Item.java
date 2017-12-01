/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsb;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;
public class Item implements ItemRecord{
    
    @Override
    public void addItem() throws IOException
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("item.txt"));
            BufferedReader br2 = new BufferedReader(new FileReader("supplier.txt"));
            
            Scanner scan = new Scanner(System.in);

            String code, name, supplier, qty, price, supp;
            boolean found = false;
            
            System.out.println("Enter Item Code: ");
            code = scan.nextLine();
            System.out.println("Enter Item Name: ");
            name = scan.nextLine();
            System.out.println("Enter Item Price: ");
            price = scan.nextLine();
            System.out.println("Enter Item Quantity: ");
            qty = scan.nextLine();
            System.out.println("Enter Supplier ID: ");
            supplier = scan.nextLine();
            
            while((supp = br2.readLine()) != null)
            {
                String[] details = supp.split(":");
                String suppID = details[0];
            
                if(supplier.contains(suppID))
                {
                    System.out.println("Supplier ID exists!");
                    if(br.readLine() == null)
                    {
                        PrintWriter pw = new PrintWriter("item.txt");
                        pw.write(code + ":" + name + ":" + price + ":" + qty + ":" + supplier);
                        pw.println();
                        pw.close();
                    }
                    else
                    {
                        BufferedWriter bw = new BufferedWriter(new FileWriter("item.txt", true));
                        bw.append(code + ":" + name + ":" + price + ":" + qty + ":" + supplier);
                        bw.newLine();
                        bw.close();
                    }

                    System.out.println("Item code " + code + " has been successfully added!");
                    br.close();
                    br2.close();
                    found = true;
                    break;
                }
                else
                {
                    found = false;
                }
            }
            
            if(!found)
            {
                System.out.println("Invalid Supplier ID. Please try again!");
            }
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
            
            String code, name, price, qty, supplier, item;
            
            System.out.println("Enter Item Code to modify: ");
            code = scan.nextLine();
            
            while((item = br.readLine()) != null)
            {
                String[] details = item.split(":");
                String ID = details[0];
                String itemName = details[1];
                String itemPrice = details[2];
                String itemQty = details[3];
                String supplierID = details[4];
                
                if(ID.equals(code))
                {
                    System.out.println("Enter New Item Name: ");
                    name = scan.nextLine();
                    System.out.println("Enter New Item Price: ");
                    price = scan.nextLine();
                    System.out.println("Enter New Item Qty: ");
                    qty = scan.nextLine();
                    System.out.println("Enter New Supplier ID: ");
                    supplier = scan.nextLine();
                    
                    item = item.replace(itemName, name);
                    item = item.replace(itemPrice, price);
                    item = item.replace(itemQty, qty);
                    item = item.replace(supplierID, supplier);
                    
                    bw.write(item + "\n");
                    bw.flush();
                }
                else
                {
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
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("item.txt"));
            
            String item;
            
            System.out.println("Item Code   Name        Item Price     Quantity     Supplier ID");
            System.out.println("---------------------------------------------------------------");
            
            while((item = br.readLine()) != null)
            {
                StringTokenizer st = new StringTokenizer(item, ":");
                System.out.println("  " + st.nextToken() + "	    " + st.nextToken() + "	" + "RM" + st.nextToken() + "	  	" + st.nextToken() + "           " + st.nextToken());
            }
            
            System.out.println("---------------------------------------------------------------");
            br.close();
        }
        catch(IOException i)
        {
            i.printStackTrace();
        }
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
            boolean found = false;
            
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
                    found = true;
                }
                else
                {
                    found = false;
                }
            }
            
            if(!found)
            {
                System.out.println("Invalid Item Code. Please Try Again!");
            }
            
            br.close();
            bw.close();
            itemFile.delete();
            tempDB.renameTo(itemFile);
            
            if(found)
            {
               System.out.println("Item Code " + ID + " has been succesfully deleted!"); 
            }
            
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
    }
    
}