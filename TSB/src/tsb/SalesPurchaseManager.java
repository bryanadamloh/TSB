/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsb;

import java.io.*;
import java.util.Scanner;
public class SalesPurchaseManager {
    
    public void addItem() throws IOException
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("item.txt"));
            BufferedReader br2 = new BufferedReader(new FileReader("supplier.txt"));
            
            Scanner scan = new Scanner(System.in);

            String code, name, supplier, qty, supp;
            double price;
            boolean found = false;
            
            System.out.println("Enter Item Code: ");
            code = scan.next();
            System.out.println("Enter Item Name: ");
            name = scan.next();
            System.out.println("Enter Item Price: ");
            price = scan.nextDouble();
            System.out.println("Enter Item Quantity: ");
            qty = scan.next();
            System.out.println("Enter Supplier ID: ");
            supplier = scan.next();
            
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
                    price = scan.next();
                    System.out.println("Enter New Item Qty: ");
                    qty = scan.next();
                    System.out.println("Enter New Supplier ID: ");
                    supplier = scan.next();
                    
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
                bw.newLine();
                bw.write(code + ":" + name + ":" + company + ":" + contact);
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
            DailyItemSales dis = new DailyItemSales();
            
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
                    
                    dis.setQuantity(value);
                    dis.setPrice(price);
                    
                    if(br.readLine() == null)
                    {
                        PrintWriter pw = new PrintWriter("dailysales.txt");
                        pw.write(date + ":" + itemID + ":" + name + ":" + price + ":" + qty + ":" + dis.calculateTotal());
                        pw.println();
                        pw.close();
                    }
                    else
                    {
                        BufferedWriter bw = new BufferedWriter(new FileWriter("dailysales.txt", true));
                        bw.append(date + ":" + itemID + ":" + name + ":" + price + ":" + qty + ":" + dis.calculateTotal());
                        bw.newLine();
                        bw.close();
                    }

                    System.out.println("Daily Sales Item for " + itemID + " has been added with total sales of RM" + dis.calculateTotal() + "!");
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
            
            String itemID, newQty, dailysales;
            double total;
            boolean found = false;
            
            System.out.println("Enter Item Code to modify: ");
            itemID = scan.nextLine();
            
            while((dailysales = br.readLine()) != null)
            {
                String[] details = dailysales.split(":");
                String date = details[0];
                String code = details[1];
                String name = details[2];
                String itemPrice = details[3];
                String salesQty = details[4];
                String totalsales = details[5];
                
                if(itemID.equals(code))
                {
                    System.out.println("Item ID exists!\n");
                    System.out.println("Enter New Quantity Sold: ");
                    newQty = scan.nextLine();
                    
                    int newValue = Integer.parseInt(newQty);
                    double price = Double.parseDouble(itemPrice);
                    
                    total = newValue * price;
                    String sales = Double.toString(total);
                    
                    dailysales = dailysales.replace(date, date);
                    dailysales = dailysales.replace(code, code);
                    dailysales = dailysales.replace(name, name);
                    dailysales = dailysales.replace(itemPrice, itemPrice);
                    dailysales = dailysales.replace(salesQty, newQty);
                    dailysales = dailysales.replace(totalsales, sales);
                    
                    bw.write(dailysales + "\n");
                    bw.flush();
                    found = true;
                    changeItemQty(code, salesQty, newValue);
                }
                else
                {
                    bw.write(dailysales + "\n");
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
            dsFile.delete();
            tempDB.renameTo(dsFile);
            
            if(found)
            {
                System.out.println("Daily Sales for Item Code " + itemID + " has been succesfully modified!");
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
            
            String itemID, dailysales;
            boolean found = false;
            
            System.out.println("Enter Item Code to delete");
            itemID = scan.nextLine();
            
            while((dailysales = br.readLine()) != null)
            {
                String[] details = dailysales.split(":");
                String code = details[1];
                String qty = details[4];
                int oldQty = Integer.parseInt(qty);
                
                if(!itemID.equals(code))
                {
                    bw.write(dailysales + "\n");
                    bw.flush();
                    found = true;
                }
                else if(itemID.equals(code))
                {
                    ModifyItemQty(oldQty, code);
                    found = true;
                }
                else
                {
                    found = false;
                }
            }
            if(!found)
            {
                System.out.println("Invalid Item ID. Please Try Again!");
            }
            
            br.close();
            bw.close();
            dsFile.delete();
            tempDB.renameTo(dsFile);
            
            if(found){
                System.out.println("Daily Sales Item Code " + itemID + " has been succesfully deleted!");
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
}
