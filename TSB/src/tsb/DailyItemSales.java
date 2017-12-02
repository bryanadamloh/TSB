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
    
    @Override
    public void EditItemSales() throws IOException
    {
        try
        {
            File dsFile = new File("dailysales.txt");
            File tempDB = new File("temp.txt");
            BufferedReader br = new BufferedReader(new FileReader(dsFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));
            Scanner scan = new Scanner(System.in);
            
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
                String code = details[2];
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
    
    @Override
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
            
            System.out.println("Enter Item Code to delete");
            dID = scan.nextLine();
            
            while((dailysales = br.readLine()) != null)
            {
                String[] details = dailysales.split(":");
                String code = details[0];
                String qty = details[4];
                int oldQty = Integer.parseInt(qty);
                
                if(!dID.equals(code))
                {
                    bw.write(dailysales + "\n");
                    bw.flush();
                    found = true;
                }
                else if(dID.equals(code))
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
    
}
