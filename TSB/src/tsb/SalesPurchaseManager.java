/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsb;

import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
public class SalesPurchaseManager {
    
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
            if(checkItemCode(code) == 0)
            {
                System.out.println("Item Code exists! Please enter a different Item Code!");
                addItem();
            }
            else
            {
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
            
        }
        catch(IOException i)
        {
            i.printStackTrace();
        }
    }
   
    public int checkItemCode(String code) throws IOException
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("item.txt"));
            
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
    
    public void viewItem() throws IOException
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("item.txt"));
            
            String item;
            
            System.out.println("---------------------------------------------------------------");
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
                    found = false;
                }
                else if(ID.equals(itemID))
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
    
    public void addSupplier() throws IOException
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("supplier.txt"));
            Scanner scan = new Scanner(System.in);
            
            String code, name, company, contact;
            
            System.out.println("Enter Supplier ID: ");
            code = scan.nextLine();
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
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
    }
    
    public void modifySupplier() throws IOException
    {
        try
        {
            File supplierFile = new File("supplier.txt");
            File tempDB = new File("temp.txt");
            BufferedReader br = new BufferedReader(new FileReader(supplierFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));
            Scanner scan = new Scanner(System.in);
            
            String ID, name, company, contact, supplier;
            boolean found = false;
            
            System.out.println("Enter Supplier ID to modify: ");
            ID = scan.nextLine();
            
            while((supplier = br.readLine()) != null)
            {
                String[] details = supplier.split(":");
                String suppID = details[0];
                String suppName = details[1];
                String suppCom = details[2];
                String suppCont = details[3];
                
                if(suppID.equals(ID))
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
                System.out.println("Supplier ID " + ID + " has been modified successfully!");
            }
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
    }
    
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
    
    public void AddItemSales() throws IOException
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("dailysales.txt"));
            BufferedReader br2 = new BufferedReader(new FileReader("item.txt"));
            Scanner scan = new Scanner(System.in);
            Sales s = new Sales();
            
            String date, itemID, item, qty;
            boolean found = false;
            
            System.out.println("Enter Item ID: ");
            itemID = scan.nextLine();
            
            while((item = br2.readLine()) != null)
            {
                String[] details = item.split(":");
                String ID = details[0];
                String name = details[1];
                String itemPrice = details[2];
                String itemQty = details[3];
                int oriQty = Integer.parseInt(itemQty);
                
                if(itemID.equals(ID))
                {
                    System.out.println("Item ID exists!");
                    System.out.println("Enter the date: ");
                    date = scan.nextLine();
                    System.out.println("Enter the quantity sold: ");
                    qty = scan.nextLine();
                    
                    int value = Integer.parseInt(qty);
                    double price = Double.parseDouble(itemPrice);
                    
                    s.setQuantity(value);
                    s.setPrice(price);
                    
                    if(br.readLine() == null)
                    {
                        PrintWriter pw = new PrintWriter("dailysales.txt");
                        pw.write(1 + ":" + date + ":" + itemID + ":" + name + ":" + price + ":" + qty + ":" + s.calculateTotal());
                        pw.println();
                        pw.close();
                    }
                    else
                    {
                        int count;
                        count = readCounter();
                        BufferedWriter bw = new BufferedWriter(new FileWriter("dailysales.txt", true));
                        bw.append(count+1 + ":" + date + ":" + itemID + ":" + name + ":" + price + ":" + qty + ":" + s.calculateTotal());
                        bw.newLine();
                        bw.close();
                    }

                    System.out.println("Daily Sales Item for " + itemID + " has been added with total sales of RM" + s.calculateTotal() + "!");
                    br.close();
                    br2.close();
                    DeductItemQty(itemID ,oriQty, value);
                    found = true;
                    break;
                }
                else
                {
                    found = false;
                }
            }
            if (!found)
            {
                System.out.println("Invalid Item Code! Please Try Again!");
            }
            
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
    }
    
    /*
        Return the counter of the text file
    */
    public int readCounter() throws IOException
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("dailysales.txt"));
            int counter = 1;
            String ds;
            
            while((ds = br.readLine()) != null)
            {
                String[] details = ds.split(":");
                String ID = details[0];
                
                if(counter == 1)
                {
                    counter = Integer.parseInt(ID);
                }
                else if(Integer.parseInt(ID) > counter)
                {
                    counter = Integer.parseInt(ID);
                }
            }
            
            br.close();
            return(counter);
        }
        catch(IOException i)
        {
            i.printStackTrace();
        }

        return -1;
    }
    
    public void DeductItemQty(String code, int oriQty, int salesQty) throws IOException
    {
        try
        {
            File itemFile = new File("item.txt");
            File tempDB2 = new File("temp2.txt");
            BufferedReader br = new BufferedReader(new FileReader(itemFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB2));
            
            String item;
            int totalQty;
            
            while((item = br.readLine()) != null)
            {
                String[] details = item.split(":");
                String ID = details[0];
                String itemName = details[1];
                String itemPrice = details[2];
                String itemQty = details[3];
                String supplierID = details[4];

                totalQty = oriQty - salesQty;
                String newQty = Integer.toString(totalQty);
                if(ID.equals(code))
                {
                    item = item.replace(itemName, itemName);
                    item = item.replace(itemPrice, itemPrice);
                    item = item.replace(itemQty, newQty);
                    item = item.replace(supplierID, supplierID);
                    
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
            tempDB2.renameTo(itemFile);
            
            System.out.println("Item Code " + code + "'s quantity has been deducted!");
        }
        catch (IOException i)
        {
           i.printStackTrace();
        }
    }
    
    public void EditItemSales() throws IOException
    {
        try
        {
            File dsFile = new File("dailysales.txt");
            File tempDB = new File("temp.txt");
            BufferedReader br = new BufferedReader(new FileReader(dsFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));
            Scanner scan = new Scanner(System.in);
            Sales s = new Sales();
            
            String dID, newQty, dailysales;
            double total;
            boolean found = false;
            
            System.out.println("Enter Daily Sales ID to modify: ");
            dID = scan.nextLine();
            
            while((dailysales = br.readLine()) != null)
            {
                String[] details = dailysales.split(":");
                String ID = details[0];
                String date = details[1];
                String itemCode = details[2];
                String name = details[3];
                String itemPrice = details[4];
                String salesQty = details[5];
                String totalsales = details[6];
                
                if(dID.equals(ID))
                {
                    System.out.println("Daily Sales ID exists!\n");
                    System.out.println("Enter New Quantity Sold: ");
                    newQty = scan.nextLine();
                    
                    int newValue = Integer.parseInt(newQty);
                    double price = Double.parseDouble(itemPrice);
                    
                    s.setPrice(price);
                    s.setQuantity(newValue);
                    total = s.calculateTotal();
                    
                    String newSales = Double.toString(total);
                    String qty = Integer.toString(newValue);
                    
                    dailysales = dailysales.replace(date, date);
                    dailysales = dailysales.replace(itemCode, itemCode);
                    dailysales = dailysales.replace(name, name);
                    dailysales = dailysales.replace(itemPrice, itemPrice);
                    dailysales = dailysales.replace(salesQty, qty);
                    dailysales = dailysales.replace(totalsales, newSales);
                    
                    bw.write(dailysales + "\n");
                    bw.flush();
                    found = true;
                    changeItemQty(itemCode, salesQty, newValue);
                }
                else
                {
                    bw.write(dailysales + "\n");
                    bw.flush();
                }
            }
            if(!found)
            {
                System.out.println("Invalid Daily Sales ID. Please Try Again!");
            }
            
            br.close();
            bw.close();
            dsFile.delete();
            tempDB.renameTo(dsFile);
            
            if(found)
            {
                System.out.println("Daily Sales ID " + dID + " has been succesfully modified!");
            }
            
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
    }
    
    public void changeItemQty(String code, String salesQty, int newQty) throws IOException
    {
        try
        {
            File itemFile = new File("item.txt");
            File tempDB3 = new File("temp3.txt");
            BufferedReader br = new BufferedReader(new FileReader(itemFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB3));
            Sales s = new Sales();
            
            String item;
            
            while((item = br.readLine()) != null)
            {
                String[] details = item.split(":");
                String ID = details[0];
                String itemName = details[1];
                String itemPrice = details[2];
                String itemQty = details[3];
                String supplierID = details[4];
                
                int iQty = Integer.parseInt(itemQty);
                int sQty = Integer.parseInt(salesQty);
                
                if(code.equals(ID))
                {
                    s.setItemQty(iQty);
                    s.setModifiedQty(newQty);
                    s.setOldQty(sQty);
                    int qty = s.calculateTotalQty();
                    String Qty = Integer.toString(qty);
                    
                    item = item.replace(itemName, itemName);
                    item = item.replace(itemPrice, itemPrice);
                    item = item.replace(itemQty, Qty);
                    item = item.replace(supplierID, supplierID);
                    
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
            tempDB3.renameTo(itemFile);
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
    }
    
    public void DeleteItemSales() throws IOException
    {
        try
        {
            File dsFile = new File("dailysales.txt");
            File tempDB = new File("temp.txt");
            BufferedReader br = new BufferedReader(new FileReader(dsFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));
            Scanner scan = new Scanner(System.in);
            
            String dID, dailysales;
            boolean found = false;
            
            System.out.println("Enter Daily Item Sales ID to delete");
            dID = scan.nextLine();
            
            while((dailysales = br.readLine()) != null)
            {
                String[] details = dailysales.split(":");
                String code = details[0];
                String itemID = details[2];
                String qty = details[5];
                int oldQty = Integer.parseInt(qty);
                
                if(!dID.equals(code))
                {
                    bw.write(dailysales + "\n");
                    bw.flush();
                    found = false;
                }
                else if(dID.equals(code))
                {
                    ModifyItemQty(oldQty, itemID);
                    found = true;
                }
                else
                {
                    found = false;
                }
            }
            if(!found)
            {
                System.out.println("Invalid Daily Sales ID. Please Try Again!");
            }
            
            br.close();
            bw.close();
            dsFile.delete();
            tempDB.renameTo(dsFile);
            
            if(found){
                System.out.println("Daily Sales ID " + dID + " has been succesfully deleted!");
            }
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
    }
    
    public void ModifyItemQty(int oldQty, String code) throws IOException
    {
        try
        {
            
            File itemFile = new File("item.txt");
            File tempDB1 = new File("temp1.txt");
            BufferedReader br1 = new BufferedReader(new FileReader(itemFile));
            BufferedWriter bw1 = new BufferedWriter(new FileWriter(tempDB1));

            String item;
            int totalQty;

            while((item = br1.readLine()) != null)
            {
                String[] detail = item.split(":");
                String ID = detail[0];
                String itemName = detail[1];
                String itemPrice = detail[2];
                String itemQty = detail[3];
                String supplierID = detail[4];

                int oriQty = Integer.parseInt(itemQty);
                totalQty = oldQty + oriQty;

                String newQty = Integer.toString(totalQty);
                if(code.equals(ID))
                {
                    item = item.replace(itemName, itemName);
                    item = item.replace(itemPrice, itemPrice);
                    item = item.replace(itemQty, newQty);
                    item = item.replace(supplierID, supplierID);

                    bw1.write(item + "\n");
                    bw1.flush();
                }
                else
                {
                    bw1.write(item + "\n");
                    bw1.flush();
                }
            }

            br1.close();
            bw1.close();
            itemFile.delete();
            tempDB1.renameTo(itemFile);

            System.out.println("Item Code " + code + "'s quantity has been modified!");
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
    }
    
    public void addPRequisition() throws IOException
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("PR.txt"));
            BufferedReader br2 = new BufferedReader(new FileReader("item.txt"));
            Scanner scan = new Scanner(System.in);
            
            String itemCode, qty, item, date;
            boolean found = false;
            
            System.out.println("Enter the Item Code for Restock: ");
            itemCode = scan.nextLine();
            
            while((item = br2.readLine()) != null)
            {
                String[] details = item.split(":");
                String ID = details[0];
                String name = details[1];
                String suppID = details[4];
                
                if(itemCode.equals(ID))
                {
                    System.out.println("Item Code exists!");
                    System.out.println("Enter the Quantity needed to restock: ");
                    qty = scan.nextLine();
                    System.out.println("Enter the date required to restock: ");
                    date = scan.nextLine();
                    
                    if(br.readLine() == null)
                    {
                        PrintWriter pw = new PrintWriter("PR.txt");
                        Random gen = new Random();
                        String uID = String.format("PR" + "%04d", gen.nextInt(9999));                      
                        pw.write(uID + ":" + itemCode + ":" + name + ":" + qty + ":" + date + ":" + suppID);
                        pw.println();
                        pw.close();
                        System.out.println("Purchase Requisition ID " + uID + " has been created!");
                    }
                    else
                    {
                        BufferedWriter bw = new BufferedWriter(new FileWriter("PR.txt", true));
                        Random gen = new Random();
                        String uID = String.format("PR" + "%04d", gen.nextInt(9999));
                        bw.write(uID + ":" + itemCode + ":" + name + ":" + qty + ":" + date + ":" + suppID);
                        bw.newLine();
                        bw.close();
                        System.out.println("Purchase Requisition ID " + uID + " has been created!");
                    }
                    
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
                System.out.println("Invalid Item Code! Please Try Again!");
            }
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
    }
    
    public void editPRequisition() throws IOException
    {
        try
        {
            File PRFile = new File("PR.txt");
            File tempDB = new File("temp.txt");
            BufferedReader br = new BufferedReader(new FileReader(PRFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));
            Scanner scan = new Scanner(System.in);
            
            String uID, qty, date, PR;
            boolean found = false;
            
            System.out.println("Enter Purchase Requisition ID to modify: ");
            uID = scan.next();
            
            while((PR = br.readLine()) != null)
            {
                String[] details = PR.split(":");
                String ID = details[0];
                String itemCode = details[1];
                String itemName = details[2];
                String itemQty = details[3];
                String prDate = details[4];
                String suppID = details[5];
                
                if(ID.equals(uID))
                {
                    System.out.println("Purchase Requisition ID exists!");
                    System.out.println("Enter New Quantity needed to restock: ");
                    qty = scan.next();
                    System.out.println("Enter New Date: ");
                    date = scan.next();
                    
                    PR = PR.replace(itemCode, itemCode);
                    PR = PR.replace(itemName, itemName);
                    PR = PR.replace(itemQty, qty);
                    PR = PR.replace(prDate, date);
                    PR = PR.replace(suppID, suppID);
                    
                    bw.write(PR + "\n");
                    bw.flush();
                    found = true;
                }
                else
                {
                    bw.write(PR + "\n");
                    bw.flush();
                }
            }
            
            if(!found)
            {
                System.out.println("Invalid Purchase Requisition ID. Please Try Again!");
            }
            
            br.close();
            bw.close();
            PRFile.delete();
            tempDB.renameTo(PRFile);
            
            if(found)
            {
                System.out.println("Purchase Requisition ID " + uID + " has been successfully modified!");
            }
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
    }
    
    public void viewPRequisition() throws IOException
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("PR.txt"));
            
            String PR;
            
            System.out.println("------------------------------------------------------------------------------------");
            System.out.println("Requisition ID    Item Code         Item Name     Quantity     Date      Supplier ID");
            System.out.println("------------------------------------------------------------------------------------");
            
            while((PR = br.readLine()) != null)
            {
                StringTokenizer st = new StringTokenizer(PR, ":");
                System.out.println("  " + st.nextToken() + "	    " + st.nextToken() + "	    " + st.nextToken() + "	    " + st.nextToken() + "	    " + st.nextToken() + "	    " + st.nextToken());
            }
            
            System.out.println("------------------------------------------------------------------------------------");
            br.close();
        }
        catch(IOException i)
        {
            i.printStackTrace();
        }
    }
    
    public void deletePRequisition() throws IOException
    {
        try
        {
            File PRFile = new File("PR.txt");
            File tempDB = new File("temp.txt");
            BufferedReader br = new BufferedReader(new FileReader(PRFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));
            Scanner scan = new Scanner(System.in);
            
            String prID, PR;
            boolean found = false;
            
            System.out.println("Enter Purchase Requisition ID to delete: ");
            prID = scan.next();
            
            while((PR = br.readLine()) != null)
            {
                String[] details = PR.split(":");
                String ID = details[0];
                
                if(!prID.equals(ID))
                {
                    bw.write(PR + "\n");
                    bw.flush();
                    found = false;
                }
                else if(prID.equals(ID))
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
                System.out.println("Invalid Purchase Requisition ID. Please Try Again!");
            }
            
            br.close();
            bw.close();
            PRFile.delete();
            tempDB.renameTo(PRFile);
            
            if(found)
            {
                System.out.println("Purchase Requisition ID " + prID + " has been successfully deleted!");
            }
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
    }
    
    public void addPurchaseOrder() throws IOException
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("order.txt"));
            BufferedReader br2 = new BufferedReader(new FileReader("PR.txt"));
            Scanner scan = new Scanner(System.in);
            
            String prID, status , PR;
            boolean found = false;
            
            System.out.println("Enter the Purchase Requisition ID: ");
            prID = scan.next();
            
            while((PR = br2.readLine()) != null)
            {
                String[] details = PR.split(":");
                String ID = details[0];
                String date = details[4];
                
                if(prID.equals(ID))
                {
                    System.out.println("Purchase Requisition ID exists!");
                    System.out.println("Do you want to approve this PR?(Either Accept/Reject)");
                    status = scan.next();
                    
                    if(br.readLine() == null)
                    {
                        PrintWriter pw = new PrintWriter("order.txt");
                        Random gen = new Random();
                        String uID = String.format("PO" + "%04d", gen.nextInt(9999));
                        pw.write(uID + ":" + prID + ":" + date + ":" + Start.username + ":" + status);
                        pw.println();
                        pw.close();
                        if(status.equals("Accept"))
                        {
                            System.out.println("Purchase Order ID " + uID + " has been accepted!");
                        }
                        else
                        {
                            System.out.println("Purchase Order ID " + uID + " has been rejected!");
                        }
                        
                    }
                    else
                    {
                        BufferedWriter bw = new BufferedWriter(new FileWriter("order.txt", true));
                        Random gen = new Random();
                        String uID = String.format("PO" + "%04d", gen.nextInt(9999));
                        bw.write(uID + ":" + prID + ":" + date + ":" + Start.username + ":" + status);
                        bw.newLine();
                        bw.close();
                        if(status.equals("Accept"))
                        {
                            System.out.println("Purchase Order ID " + uID + " has been accepted!");
                        }
                        else
                        {
                            System.out.println("Purchase Order ID " + uID + " has been rejected!");
                        }
                    }
                    
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
                System.out.println("Invalid Item Code! Please Try Again!");
            }
        }
        catch(IOException i)
        {
            i.printStackTrace();
        }
    }
    
    public void editPurchaseOrder() throws IOException
    {
        try
        {
            File orderFile = new File("order.txt");
            File tempDB = new File("temp.txt");
            BufferedReader br = new BufferedReader(new FileReader(orderFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));
            Scanner scan = new Scanner(System.in);
            
            String poID, status, PO;
            boolean found = false;
            
            System.out.println("Enter Purchase Order ID to modify: ");
            poID = scan.next();
            
            while((PO = br.readLine()) != null)
            {
                String[] details = PO.split(":");
                String ID = details[0];
                String prID = details[1];
                String date = details[2];
                String username = details[3];
                String postatus = details[4];
                
                if(ID.equals(poID))
                {
                    System.out.println("Purchase Order ID exists!");
                    System.out.println("Enter New Status: (Either Accept/Reject)");
                    status = scan.next();
                    
                    PO = PO.replace(prID, prID);
                    PO = PO.replace(date, date);
                    PO = PO.replace(username, Start.username);
                    PO = PO.replace(postatus, status);
                    
                    bw.write(PO + "\n");
                    bw.flush();
                    found = true;
                }
                else
                {
                    bw.write(PO + "\n");
                    bw.flush();
                }
            }
            
            if(!found)
            {
                System.out.println("Invalid Purchase Order ID. Please Try Again!");
            }
            
            br.close();
            bw.close();
            orderFile.delete();
            tempDB.renameTo(orderFile);
            
            if(found)
            {
                System.out.println("Purchase Order ID " + poID + " has been successfully modified!");
            }
        }
        catch(IOException i)
        {
            i.printStackTrace();
        }
    }
    
    public void viewPurchaseOrder() throws IOException
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("order.txt"));
            
            String PO;
            
            System.out.println("-----------------------------------------------------------------");
            System.out.println("Order ID   Requisition ID   Date         Updated By        Status");
            System.out.println("-----------------------------------------------------------------");
            
            while((PO = br.readLine()) != null)
            {
                StringTokenizer st = new StringTokenizer(PO, ":");
                System.out.println("  " + st.nextToken() + "	" + st.nextToken() + "	    " + st.nextToken() + "	    " + st.nextToken() + "		" + st.nextToken());                
            }
            
            System.out.println("-----------------------------------------------------------------");
            br.close();
        }
        catch(IOException i)
        {
            i.printStackTrace();
        }
    }
    
    public void deletePurchaseOrder() throws IOException
    {
        try
        {
            File orderFile = new File("order.txt");
            File tempDB = new File("temp.txt");
            BufferedReader br = new BufferedReader(new FileReader(orderFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));
            Scanner scan = new Scanner(System.in);
            
            String poID, PO;
            boolean found = false;
            
            System.out.println("Enter Purchase Order ID to delete: ");
            poID = scan.next();
            
            while((PO = br.readLine()) != null)
            {
                String[] details = PO.split(":");
                String ID = details[0];
                
                if(!poID.equals(ID))
                {
                    bw.write(PO + "\n");
                    bw.flush();
                    found = false;
                }
                else if(poID.equals(ID))
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
                System.out.println("Invalid Purchase Order ID. Please Try Again!");
            }
            
            br.close();
            bw.close();
            orderFile.delete();
            tempDB.renameTo(orderFile);
            
            if(found)
            {
                System.out.println("Purchase Order ID " + poID + " has been successfully deleted!");
            }
        }
        catch(IOException i)
        {
            i.printStackTrace();
        }
    }
}
