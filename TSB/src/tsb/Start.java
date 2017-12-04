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

    public static String username, password;
    public static String cont = "y";
    public static int choice;
    public static int access = 0;
    public static void main(String[] args) throws IOException 
    {
        Scanner scan = new Scanner(System.in);
        
        ItemRecord i = new Item();
        Admin a = new Admin();
        SupplierRecord s = new Supplier();
        DailyItemSalesRecord ds = new DailyItemSales();
        PRequisitionRecord pr = new PRequisition();
        PurchaseOrderRecord po = new PurchaseOrder();
        
        //Login Page
        System.out.println("Welcome to TSB Order Management System\n");
        System.out.println("Please login to continue\n");
        
        System.out.println("Username:");
        username = scan.next();
        System.out.println("Password:");
        password = scan.next();
        
        access = new Start().getUser(username, password);
        
        if(access == 0 || access == 1 || access == 2)
        {
            do{
                switch (access) {
                    case 0:
                        System.out.println("Welcome to Admin Main Menu\n\n");
                        System.out.println("1 ===> Add New User");
                        System.out.println("2 ===> View User");
                        System.out.println("3 ===> Item Entry");
                        System.out.println("4 ===> Supplier Entry");
                        System.out.println("5 ===> Daily Item-Wise Sales Entry");
                        System.out.println("6 ===> Purchase Requisition");
                        System.out.println("7 ===> Purchase Order");
                        System.out.println("0 ===> Log Out");
                        System.out.print("\n");
                        System.out.println("Please enter your choice:");
                        choice = scan.nextInt();

                        if(choice == 1)
                        {
                            a.AddUser();
                            System.out.println("Do you wish to continue to Main Menu? (Y/N)");
                            cont = scan.next();
                        }
                        else if(choice == 2)
                        {
                            a.ViewUser();
                            System.out.println("Do you wish to continue to Main Menu? (Y/N)");
                            cont = scan.next();
                        }
                        else if(choice == 3)
                        {
                            System.out.println("Item Entry Menu\n");
                            System.out.println("1 ===> Add New Item");
                            System.out.println("2 ===> Modify Existing Item");
                            System.out.println("3 ===> View Items");
                            System.out.println("4 ===> Delete Item");
                            System.out.print("\n");
                            System.out.println("Please enter your choice:");
                            choice = scan.nextInt();

                            switch (choice) 
                            {
                                case 1:
                                    a.addItem();
                                    break;
                                case 2:
                                    a.modifyItem();
                                    break;
                                case 3:
                                    a.viewItem();
                                    break;
                                case 4:
                                    a.deleteItem();
                                    break;
                                default:
                                    break;
                            }

                            System.out.println("Do you wish to continue to Main Menu? (Y/N)");
                            cont = scan.next();
                        }
                        else if(choice == 4)
                        {
                            System.out.println("Supplier Entry Menu\n");
                            System.out.println("1 ===> Add New Supplier");
                            System.out.println("2 ===> Modify Existing Supplier");
                            System.out.println("3 ===> View Supplier");
                            System.out.println("4 ===> Delete Supplier");
                            System.out.print("\n");
                            System.out.println("Please enter your choice:");
                            choice = scan.nextInt();

                            switch (choice) 
                            {
                                case 1:
                                    a.addSupplier();
                                    break;
                                case 2:
                                    a.modifySupplier();
                                    break;
                                case 3:
                                    a.viewSupplier();
                                    break;
                                case 4:
                                    a.deleteSupplier();
                                    break;
                                default:
                                    break;
                            }

                            System.out.println("Do you wish to continue to Main Menu? (Y/N)");
                            cont = scan.next();
                        }
                        else if(choice == 5)
                        {
                            System.out.println("Daily Item-Wise Sales Entry\n");
                            System.out.println("1 ===> Add New Daily Item Sales");
                            System.out.println("2 ===> Modify Daily Item Sales");
                            System.out.println("3 ===> Delete Existed Daily Item Sales");
                            System.out.println("\n");
                            System.out.println("Please enter your choice:");
                            choice = scan.nextInt();

                            switch (choice) 
                            {
                                case 1:
                                    a.AddItemSales();
                                    break;
                                case 2:
                                    a.EditItemSales();
                                    break;
                                case 3:
                                    a.DeleteItemSales();
                                    break;
                                default:
                                    break;
                            }

                            System.out.println("Do you wish to continue to Main Menu? (Y/N)");
                            cont = scan.next();
                        }
                        else if(choice == 6)
                        {
                            System.out.println("Purchase Requisition\n");
                            System.out.println("1 ===> Create New Purchase Requisition");
                            System.out.println("2 ===> Modify Existing Purchase Requisition");
                            System.out.println("3 ===> View Purchase Requisition");
                            System.out.println("4 ===> Delete Purchase Requisition");
                            System.out.println("\n");
                            System.out.println("Please enter your choice:");
                            choice = scan.nextInt();

                            switch (choice) 
                            {
                                case 1:
                                    a.addPRequisition();
                                    break;
                                case 2:
                                    a.editPRequisition();
                                    break;
                                case 3:
                                    a.viewPRequisition();
                                    break;
                                case 4:
                                    a.deletePRequisition();
                                    break;
                                default:
                                    break;
                            }

                            System.out.println("Do you wish to continue to Main Menu? (Y/N)");
                            cont = scan.next();
                        }
                        else if(choice == 7)
                        {
                            System.out.println("Purchase Order\n");
                            System.out.println("1 ===> Create New Purchase Order");
                            System.out.println("2 ===> Modify Existing Purchase Order");
                            System.out.println("3 ===> View Purchase Order");
                            System.out.println("4 ===> Delete Purchase Order");
                            System.out.println("\n");
                            System.out.println("Please enter your choice:");
                            choice = scan.nextInt();

                            switch (choice)
                            {
                                case 1:
                                    a.addPurchaseOrder();
                                    break;
                                case 2:
                                    a.editPurchaseOrder();
                                    break;
                                case 3:
                                    a.viewPurchaseOrder();
                                    break;
                                case 4:
                                    a.deletePurchaseOrder();
                                    break;
                                default:
                                    break;
                            }

                            System.out.println("Do you wish to continue to Main Menu? (Y/N)");
                            cont = scan.next();
                        }
                        else if(choice == 0)
                        {
                            Start.main(args);
                        }
                        break;

                    case 1:
                        System.out.println("Welcome to Sales Manager Main Menu\n\n");
                        System.out.println("1 ===> Item Entry");
                        System.out.println("2 ===> Supplier Entry");
                        System.out.println("3 ===> Daily Item-Wise Sales Entry");
                        System.out.println("4 ===> Purchase Requisition");
                        System.out.println("5 ===> List of Purchase Orders");
                        System.out.println("0 ===> Log Out");
                        System.out.print("\n");
                        System.out.println("Please enter your choice:");
                        choice = scan.nextInt();

                        if(choice == 1)
                        {
                            System.out.println("Item Entry Menu\n");
                            System.out.println("1 ===> Add New Item");
                            System.out.println("2 ===> Modify Existing Item");
                            System.out.println("3 ===> View Item");
                            System.out.println("4 ===> Delete Item");
                            System.out.print("\n");
                            System.out.println("Please enter your choice:");
                            choice = scan.nextInt();

                            switch (choice) 
                            {
                                case 1:
                                    i.addItem();
                                    break;
                                case 2:
                                    i.modifyItem();
                                    break;
                                case 3:
                                    i.viewItem();
                                    break;
                                case 4:
                                    i.deleteItem();
                                    break;
                                default:
                                    break;
                            }

                            System.out.println("Do you wish to continue to Main Menu? (Y/N)");
                            cont = scan.next();
                        }
                        else if(choice == 2)
                        {
                            System.out.println("Supplier Entry Menu\n");
                            System.out.println("1 ===> Add New Supplier");
                            System.out.println("2 ===> Modify Existing Supplier");
                            System.out.println("3 ===> View Supplier");
                            System.out.println("4 ===> Delete Supplier");
                            System.out.print("\n");
                            System.out.println("Please enter your choice:");
                            choice = scan.nextInt();

                            switch (choice) 
                            {
                                case 1:
                                    s.addSupplier();
                                    break;
                                case 2:
                                    s.modifySupplier();
                                    break;
                                case 3:
                                    s.viewSupplier();
                                    break;
                                case 4:
                                    s.deleteSupplier();
                                    break;
                                default:
                                    break;
                            }

                            System.out.println("Do you wish to continue to Main Menu? (Y/N)");
                            cont = scan.next();
                        }   
                        else if(choice == 3)
                        {
                            System.out.println("Daily Item-Wise Sales Entry\n");
                            System.out.println("1 ===> Add New Daily Item Sales");
                            System.out.println("2 ===> Modify Daily Item Sales");
                            System.out.println("3 ===> Delete Existed Daily Item Sales");
                            System.out.println("\n");
                            System.out.println("Please enter your choice:");
                            choice = scan.nextInt();

                            switch (choice) 
                            {
                                case 1:
                                    ds.AddItemSales();
                                    break;
                                case 2:
                                    ds.EditItemSales();
                                    break;
                                case 3:
                                    ds.DeleteItemSales();
                                    break;
                                default:
                                    break;
                            }

                            System.out.println("Do you wish to continue to Main Menu? (Y/N)");
                            cont = scan.next();
                        }
                        else if(choice == 4)
                        {
                            System.out.println("Purchase Requisition\n");
                            System.out.println("1 ===> Create New Purchase Requisition");
                            System.out.println("2 ===> Modify Existing Purchase Requisition");
                            System.out.println("3 ===> View Purchase Requisition");
                            System.out.println("4 ===> Delete Purchase Requisition");
                            System.out.println("\n");
                            System.out.println("Please enter your choice:");
                            choice = scan.nextInt();

                            switch (choice) 
                            {
                                case 1:
                                    pr.addPRequisition();
                                    break;
                                case 2:
                                    pr.editPRequisition();
                                    break;
                                case 3:
                                    pr.viewPRequisition();
                                    break;
                                case 4:
                                    pr.deletePRequisition();
                                    break;
                                default:
                                    break;
                            }

                            System.out.println("Do you wish to continue to Main Menu? (Y/N)");
                            cont = scan.next();
                        }
                        else if(choice == 5)
                        {
                            po.viewPurchaseOrder();
                            System.out.println("Do you wish to continue to Main Menu? (Y/N)");
                            cont = scan.next();
                        }
                        else if(choice == 0)
                        {
                            Start.main(args);
                        }
                        break;

                    case 2:
                        System.out.println("Welcome to Purchase Manager Main Menu\n\n");
                        System.out.println("1 ===> List Of Items");
                        System.out.println("2 ===> List Of Suppliers");
                        System.out.println("3 ===> Display Purchase Requisition");
                        System.out.println("4 ===> Purchase Orders");
                        System.out.println("0 ===> Log Out");
                        System.out.print("\n");
                        System.out.println("Please enter your choice:");
                        choice = scan.nextInt();

                        if(choice == 1)
                        {
                            i.viewItem();
                            System.out.println("Do you wish to continue to Main Menu? (Y/N)");
                            cont = scan.next();
                        }
                        else if(choice == 2)
                        {
                            s.viewSupplier();
                            System.out.println("Do you wish to continue to Main Menu? (Y/N)");
                            cont = scan.next();
                        }
                        else if(choice == 3)
                        {
                            pr.viewPRequisition();
                            System.out.println("Do you wish to continue to Main Menu? (Y/N)");
                            cont = scan.next();
                        }
                        else if(choice == 4)
                        {
                            System.out.println("Purchase Order\n");
                            System.out.println("1 ===> Create New Purchase Order");
                            System.out.println("2 ===> Modify Existing Purchase Order");
                            System.out.println("3 ===> View Purchase Order");
                            System.out.println("4 ===> Delete Purchase Order");
                            System.out.println("\n");
                            System.out.println("Please enter your choice:");
                            choice = scan.nextInt();

                            switch (choice)
                            {
                                case 1:
                                    po.addPurchaseOrder();
                                    break;
                                case 2:
                                    po.editPurchaseOrder();
                                    break;
                                case 3:
                                    po.viewPurchaseOrder();
                                    break;
                                case 4:
                                    po.deletePurchaseOrder();
                                    break;
                                default:
                                    break;
                            }

                            System.out.println("Do you wish to continue to Main Menu? (Y/N)");
                            cont = scan.next();
                        }
                        else if(choice == 0)
                        {
                            Start.main(args);
                        }
                        break;

                    default:
                        break;
                }
            }while(cont.equalsIgnoreCase("y"));
        }
        else
        {
            System.out.println("Invalid Username or Password. Please Try Again!\n");
            Start.main(args);
        }
    }   

    public int getUser(String username, String pass)
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
                else if(name.equals(username) && password.equals(pass) && role.equals("Sales Manager"))
                {
                    return 1;
                }
                else if(name.equals(username) && password.equals(pass) && role.equals("Purchase Manager"))
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
