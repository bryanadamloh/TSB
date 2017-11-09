/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsb;

import java.io.*;
import java.io.IOException;
import java.util.Scanner;

public class Start {

    public static void main(String[] args) throws IOException 
    {
        Scanner scan = new Scanner(System.in);
        
        ItemRecord i = new Item();
        AdminRecord a = new Admin();
        SupplierRecord s = new Supplier();
        
        String username, password;
        String choice;
        String cont = "y";
        int access = 0;
        
          //Login Page
        System.out.println("Welcome to TSB Order Management System\n");
        System.out.println("Please login to continue\n");
        
        System.out.println("Username:");
        username = scan.next();
        System.out.println("Password:");
        password = scan.next();
        
        access = new Start().findUser(username, password);
        
        do{
            switch (access) {
                case 0:
                    System.out.println("Welcome to Admin Main Menu\n\n");
                    System.out.println("1 ===> Add New User");
                    System.out.println("2 ===> Item Entry");
                    System.out.println("3 ===> Supplier Entry");
                    System.out.print("\n");
                    System.out.println("Please enter your choice:");
                    choice = scan.next();
                    
                    if(choice.equals("1"))
                    {
                        a.AddUser();
                    }
                    else if(choice.equals("2"))
                    {
                        System.out.println("Item Entry Menu\n");
                        System.out.println("1 ===> Add New Item");
                        System.out.println("1 ===> Modify Existing Item");
                        System.out.println("1 ===> Delete Item");
                        choice = scan.next();
                        
                        if(choice.equals("1"))
                        {
                            i.addItem();
                        }
                        else if(choice.equals("2"))
                        {
                            i.modifyItem();
                        }
                        else if(choice.equals("3"));
                        {
                            i.deleteItem();
                        }
                    }   
                    
                    System.out.println("Do you wish to continue to Main Menu? (Y/N)");
                    cont = scan.next();
                    break;
                    
                case 1:
                    System.out.println("Welcome to Sales Manager Main Menu\n\n");
                    System.out.println("1 ===> Item Entry");
                    System.out.println("2 ===> Supplier Entry");
                    System.out.println("3 ===> Daily Item-Wise Sales Entry");
                    System.out.println("4 ===> Purchase Requisition");
                    System.out.println("5 ===> List of Purchase Orders");
                    choice = scan.next();
                    
                    if(choice.equals("1"))
                    {
                        System.out.println("Item Entry Menu\n");
                        System.out.println("1 ===> Add New Item");
                        System.out.println("1 ===> Modify Existing Item");
                        System.out.println("1 ===> Delete Item");
                        choice = scan.next();
                        
                        if(choice.equals("1"))
                        {
                            i.addItem();
                        }
                        else if(choice.equals("2"))
                        {
                            i.modifyItem();
                        }
                        else if(choice.equals("3"));
                        {
                            i.deleteItem();
                        }
                    }
                    else if(choice.equals("2"))
                    {
                        System.out.println("Supplier Entry Menu\n");
                        System.out.println("1 ===> Add New Supplier");
                        System.out.println("1 ===> Modify Existing Supplier");
                        System.out.println("1 ===> Delete Supplier");
                        choice = scan.next();
                        
                        if(choice.equals("1"))
                        {
                            s.addSupplier();
                        }
                        else if(choice.equals("2"))
                        {
                            s.modifySupplier();
                        }
                        else if(choice.equals("3"));
                        {
                            s.deleteSupplier();
                        }
                    }   
                    
                    System.out.println("Do you wish to continue to Main Menu? (Y/N)");
                    cont = scan.next();
                    break;
                    
                case 2:
                    System.out.println("Welcome to Purchase Manager Main Menu\n\n");
                    System.out.println("1 ===> List Of Items");
                    System.out.println("2 ===> List Of Suppliers");
                    System.out.println("3 ===> Purchase Requisition");
                    System.out.println("4 ===> Generate Purchase Orders");
                    System.out.println("5 ===> List Of Purchase Orders");
                    
                    
                    System.out.println("Do you wish to continue to Main Menu? (Y/N)");
                    cont = scan.next();                    
                    break;
                    
                default:
                    break;
            }
        }
        while(cont.equalsIgnoreCase("y"));
    }   
    
    
    public int findUser(String username, String pass)
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("user.txt"));
            String user;
            while((user = br.readLine()) != null)
            {
                String[] details = user.split(":");
                String name = details[1];
                String password = details[2];
                String role = details[3];
                
                if(name.equals(username) && password.equals(pass) && role.equals("Admin"))
                {
                    return 0;
                }
                else if(name.equals(username) && password.equals(pass) && role.equals("SM"))
                {
                    return 1;
                }
                else if(name.equals(username) && password.equals(pass) && role.equals("PM"))
                {
                    return 2;
                }
            }
            
            br.close();
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
        
        return -1;
    }

}
