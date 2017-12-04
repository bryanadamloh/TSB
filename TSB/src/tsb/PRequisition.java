/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsb;

import java.io.*;
import java.util.Scanner;
import java.util.Random;
import java.util.StringTokenizer;
public class PRequisition implements PRequisitionRecord {
    
    @Override
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
    
    @Override
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
    
    @Override
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
    
    @Override
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
    
}
