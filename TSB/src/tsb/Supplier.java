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
            code = scan.nextLine();
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
        try
        {
            File supplierFile = new File("supplier.txt");
            File tempDB = new File("temp.txt");
            BufferedReader br = new BufferedReader(new FileReader(supplierFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));
            Scanner scan = new Scanner(System.in);
            
            String ID, name, company, itemID, supplier;
            
            System.out.println("Enter Supplier ID to modify: ");
            ID = scan.nextLine();
            
            while((supplier = br.readLine()) != null)
            {
                String[] details = supplier.split(":");
                String suppID = details[0];
                String suppName = details[1];
                String suppCom = details[2];
                String itemCode = details[3];
                
                if(suppID.equals(ID))
                {
                    System.out.println("Enter New Supplier Name: ");
                    name = scan.nextLine();
                    System.out.println("Enter New Supplier Company: ");
                    company = scan.nextLine();
                    System.out.println("Enter New Item Code: ");
                    itemID = scan.next();
                    
                    supplier = supplier.replace(suppName, name);
                    supplier = supplier.replace(suppCom, company);
                    supplier = supplier.replace(itemCode, itemID);
                    
                    bw.write(supplier + "\n");
                    bw.flush();
                }
                else
                {
                    bw.write(supplier + "\n");
                    bw.flush();
                }
            }
            
            br.close();
            bw.close();
            supplierFile.delete();
            tempDB.renameTo(supplierFile);
            
            System.out.println("Supplier ID " + ID + " has been modified successfully!");
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
    }
    
    @Override
    public void viewSupplier() throws IOException
    {
        
    }
    
    @Override
    public void deleteSupplier() throws IOException
    {
        try
        {
            File supplierFile = new File("supplier.txt");
            File tempDB = new File("temp.txt");
            BufferedReader br = new BufferedReader(new FileReader(supplierFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));
            Scanner scan = new Scanner(System.in);
            
            String ID, supplier;
            System.out.println("Enter Supplier ID to delete: ");
            ID = scan.next();
            
            while((supplier = br.readLine()) != null)
            {
                String[] details = supplier.split(":");
                String suppID = details[0];
                
                if(!ID.equals(suppID))
                {
                    bw.write(supplier + "\n");
                    bw.flush();
                }
            }
            
            br.close();
            bw.close();
            supplierFile.delete();
            tempDB.renameTo(supplierFile);
            
            System.out.println("Supplier ID " + ID + " has been successfully deleted!");
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
    }
    
}

