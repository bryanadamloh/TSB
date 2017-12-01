/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsb;

import java.io.*;
import java.util.Scanner;
import java.util.Random;
public class PurchaseOrder implements PurchaseOrderRecord{
    
    @Override
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
                    
                    if(br.readLine() == null && status.equals("Accept"))
                    {
                        PrintWriter pw = new PrintWriter("order.txt");
                        Random gen = new Random();
                        String uID = String.format("PO" + "%04d", gen.nextInt(9999));
                        pw.write(uID + ":" + prID + ":" + date + ":" + Start.username + ":" + status);
                        pw.println();
                        pw.close();
                        System.out.println("Purchase Order ID " + uID + " has been approved!");
                    }
                    else if(br.readLine() == null && status.equals("Reject"))
                    {
                        PrintWriter pw = new PrintWriter("order.txt");
                        Random gen = new Random();
                        String uID = String.format("PO" + "%04d", gen.nextInt(9999));
                        pw.write(uID + ":" + prID + ":" + date + ":" + Start.username + ":" + status);
                        pw.println();
                        pw.close();
                        System.out.println("Purchase Order ID " + uID + " has been rejected!");
                    }
                    else if(br.readLine() != null && status.equals("Accept"))
                    {
                        BufferedWriter bw = new BufferedWriter(new FileWriter("order.txt", true));
                        Random gen = new Random();
                        String uID = String.format("PO" + "%04d", gen.nextInt(9999));
                        bw.write(uID + ":" + prID + ":" + date + ":" + Start.username + ":" + status);
                        bw.newLine();
                        bw.close();
                        System.out.println("Purchase Order ID " + uID + " has been approved!");
                    }
                    else if(br.readLine() != null && status.equals("Reject"))
                    {
                        BufferedWriter bw = new BufferedWriter(new FileWriter("order.txt", true));
                        Random gen = new Random();
                        String uID = String.format("PO" + "%04d", gen.nextInt(9999));
                        bw.write(uID + ":" + prID + ":" + date + ":" + Start.username + ":" + status);
                        bw.newLine();
                        bw.close();
                        System.out.println("Purchase Order ID " + uID + " has been approved!");
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
    
    @Override
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
    
    @Override
    public void viewPurchaseOrder() throws IOException
    {
        
    }
    
    @Override
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
