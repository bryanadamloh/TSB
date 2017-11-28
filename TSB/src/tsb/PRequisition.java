/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsb;

import java.io.*;
import java.text.*;
import java.util.Scanner;
import java.util.Random;
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
                        String uID = String.format("%05d", gen.nextInt(99999));                      
                        pw.write(uID + ":" + itemCode + ":" + name + ":" + qty + ":" + date + ":" + suppID);
                        pw.println();
                        pw.close();
                        System.out.println("Purchase Requisition ID " + uID + " has been created!");
                    }
                    else
                    {
                        BufferedWriter bw = new BufferedWriter(new FileWriter("PR.txt", true));
                        Random gen = new Random();
                        String uID = String.format("%05d", gen.nextInt(99999));
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
        
    }
    
    @Override
    public void viewPRequisition() throws IOException
    {
        
    }
    
    @Override
    public void deletePRequisition() throws IOException
    {
        try
        {
            File PRFile = new File("PR.txt");
            File tempDB = new File("temp2.txt");
            BufferedReader br = new BufferedReader(new FileReader(PRFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));
            Scanner scan = new Scanner(System.in);
            
            String pr;
            
        }
        catch (IOException i)
        {
            
        }
    }
    
}
