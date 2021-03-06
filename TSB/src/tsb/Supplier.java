/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsb;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;
public class Supplier implements SupplierRecord{
    
    public String code;
    @Override
    public void addSupplier() throws IOException
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("supplier.txt"));
            Scanner scan = new Scanner(System.in);
            
            String name, company, contact;
            boolean found = false;
            
            System.out.println("Enter Supplier ID: ");
            code = scan.nextLine();
            if(checkSupplierCode(code) == 0)
            {
                System.out.println("Supplier Code exists! Please try again!");
                addSupplier();
            }
            else
            {
                System.out.println("Enter Supplier Name: ");
                name = scan.nextLine();
                System.out.println("Enter Company Name: ");
                company = scan.nextLine();
                System.out.println("Enter Supplier Contact Number: ");
                contact = scan.nextLine();

                if(br.readLine() == null)
                {
                    PrintWriter pw = new PrintWriter("supplier.txt");
                    pw.write(code + ":" + name + ":" + company + ":" + contact);
                    pw.println();
                    pw.close();
                }
                else
                {
                    BufferedWriter bw = new BufferedWriter(new FileWriter("supplier.txt", true));
                    bw.write(code + ":" + name + ":" + company + ":" + contact);
                    bw.newLine();
                    bw.close();
                }

                System.out.println("Supplier " + code + " has been successfully added!");
                br.close();
                found = true;
                
            }
            
            if(!found)
            {
                System.out.println("Invalid Supplier ID. Please try again!");
            }
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
    }
    
    public int checkSupplierCode(String code) throws IOException
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("supplier.txt"));
            
            String item;
            
            while((item = br.readLine()) != null)
            {
                String[] details = item.split(":");
                String ID = details[0];
                
                if(code.equals(ID))
                {
                    br.close();
                    return 0;
                }
            }
            
            br.close();
        }
        catch(IOException i)
        {
            i.printStackTrace();
        }
        
        return 1;
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
            
            String name, company, contact, supplier;
            boolean found = false;
            
            System.out.println("Enter Supplier ID to modify: ");
            code = scan.nextLine();
            
            while((supplier = br.readLine()) != null)
            {
                String[] details = supplier.split(":");
                String suppID = details[0];
                String suppName = details[1];
                String suppCom = details[2];
                String suppCont = details[3];
                
                if(suppID.equals(code))
                {
                    System.out.println("Enter New Supplier Name: ");
                    name = scan.nextLine();
                    System.out.println("Enter New Supplier Company: ");
                    company = scan.nextLine();
                    System.out.println("Enter New Supplier Contact: ");
                    contact = scan.nextLine();
                    
                    supplier = supplier.replace(suppName, name);
                    supplier = supplier.replace(suppCom, company);
                    supplier = supplier.replace(suppCont, contact);
                    
                    bw.write(supplier + "\n");
                    bw.flush();
                    found = true;
                }
                else
                {
                    bw.write(supplier + "\n");
                    bw.flush();
                    found = false;
                }
            }
            if(!found)
            {
                System.out.println("Invalid Item ID. Please Try Again!");
            }
            
            br.close();
            bw.close();
            supplierFile.delete();
            tempDB.renameTo(supplierFile);
            
            if(found)
            {
                System.out.println("Supplier ID " + code + " has been modified successfully!");
            }
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
    }
    
    @Override
    public void viewSupplier() throws IOException
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("supplier.txt"));
            
            String supplier;
            
            System.out.println("---------------------------------------------------------");
            System.out.println("Supplier ID   Name        Company          Contact Number");
            System.out.println("---------------------------------------------------------");
            
            while((supplier = br.readLine()) != null)
            {
                StringTokenizer st = new StringTokenizer(supplier, ":");
                System.out.println("  " + st.nextToken() + "	    " + st.nextToken() + "	" + st.nextToken() + "	    " + st.nextToken());     
            }
            
            System.out.println("---------------------------------------------------------");
            br.close();
        }
        catch(IOException i)
        {
            i.printStackTrace();
        }
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
            boolean found = false;
            
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
                    found = false;
                }
                else if(ID.equals(suppID))
                {
                    found = true;
                }
                else
                {
                    found = false;
                }
            }
            
            if(!found)
            {
                System.out.println("Invalid Supplier ID. Please Try Again!");
            }
            
            br.close();
            bw.close();
            supplierFile.delete();
            tempDB.renameTo(supplierFile);
            
            if(found)
            {
                System.out.println("Supplier ID " + ID + " has been successfully deleted!");
            }
            
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
    }
    
}

