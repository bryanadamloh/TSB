/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsb;

import java.io.*;
import java.text.*;
import java.util.Scanner;
import java.util.Date;
public class DailyItemSales implements DailyItemSalesRecord {
    
    @Override
    public void AddItemSales() throws IOException
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("dailysales.txt"));
            BufferedReader br2 = new BufferedReader(new FileReader("item.txt"));
            Scanner scan = new Scanner(System.in);
            NumberFormat f = new DecimalFormat("#0.00");
            
            String date, itemID, item, qty;
            double total;
            
            System.out.println("Enter Item ID: ");
            itemID = scan.nextLine();
            
            while((item = br2.readLine()) != null)
            {
                String[] details = item.split(":");
                String ID = details[0];
                String name = details[1];
                String itemPrice = details[2];
                String itemQty = details[3];
                
                if(itemID.equals(ID))
                {
                    System.out.println("Item ID exists!\n");
                    System.out.println("Enter the date: ");
                    date = scan.nextLine();
                    System.out.println("Enter the quantity sold: ");
                    qty = scan.nextLine();
                    
                    int value = Integer.parseInt(qty);
                    double price = Double.parseDouble(itemPrice);
                    
                    total = value * price;
                    if(br.readLine() == null)
                    {
                        PrintWriter pw = new PrintWriter("dailysales.txt");
                        pw.write(date + ":" + itemID + ":" + name + ":" + price + ":" + qty + ":" + f.format(total));
                        pw.println();
                        pw.close();
                    }
                    else
                    {
                        BufferedWriter bw = new BufferedWriter(new FileWriter("dailysales.txt", true));
                        bw.append(date + ":" + itemID + ":" + name + ":" + price + ":" + qty + ":" + f.format(total));
                        bw.newLine();
                        bw.close();
                    }
                    
                    System.out.println("Daily Sales Item for " + itemID + " has been added with total sales of RM" + f.format(total) + "!");
                    br.close();
                    br2.close();
                    break;
                }
            }
            
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
            NumberFormat f = new DecimalFormat("#0.00");
            
            String itemID, qty, dailysales;
            double total;
            
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
                    qty = scan.nextLine();
                    
                    int newValue = Integer.parseInt(qty);
                    double price = Double.parseDouble(itemPrice);
                    
                    total = newValue * price;
                    f.format(total);
                    String sales = Double.toString(total);
                    
                    dailysales = dailysales.replace(date, date);
                    dailysales = dailysales.replace(code, code);
                    dailysales = dailysales.replace(name, name);
                    dailysales = dailysales.replace(itemPrice, itemPrice);
                    dailysales = dailysales.replace(salesQty, qty);
                    dailysales = dailysales.replace(totalsales, sales);
                    
                    bw.write(dailysales + "\n");
                    bw.flush();
                }
                else
                {
                    bw.write(dailysales + "\n");
                    bw.flush();
                }
            }
            
            br.close();
            bw.close();
            dsFile.delete();
            tempDB.renameTo(dsFile);
            
            System.out.println("Daily Sales for Item Code " + itemID + " has been succesfully modified!");
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
            
            String itemID, dailysales;
            
            System.out.println("Enter Item ID to delete");
            itemID = scan.nextLine();
            
            while((dailysales = br.readLine()) != null)
            {
                String[] details = dailysales.split(":");
                String code = details[1];
                
                if(!itemID.equals(code))
                {
                    bw.write(dailysales + "\n");
                    bw.flush();
                }
            }
            
            br.close();
            bw.close();
            dsFile.delete();
            tempDB.renameTo(dsFile);
            
            System.out.println("Daily Sales Item Code " + itemID + " has been succesfully deleted!");
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
    }
    
}
