/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsb;

import java.io.*;
import java.util.Scanner;
import java.util.Random;
public class Admin extends SalesPurchaseManager{
    
    public void AddUser() throws IOException
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("user.txt"));
            Scanner scan = new Scanner(System.in);

            String username, password, role;

            System.out.print("Enter your username: ");
            username = scan.next();
            System.out.print("Enter your password: ");
            password = scan.next();
            System.out.print("Enter role for this user (Sales Manager/Purchase Manager): ");
            role = scan.nextLine();

            if(br.readLine() != null && role.equals("Sales Manager"))
            {
                SMAdd(username, password, role);
            }
            else if (br.readLine() != null && role.equals("Purchase Manager"))
            {
                PMAdd(username, password, role);
            }

            br.close();
            System.out.println("Registration Success!");
        }
        catch(IOException i)
        {
            i.printStackTrace();
        }
    }
    
    public void SMAdd(String username, String password, String role) throws IOException
    {
        BufferedWriter bw = new BufferedWriter(new FileWriter("user.txt", true));
        Random gen = new Random();
        String randomNum = String.format("SM" + "%04d", gen.nextInt(9999));
        
        bw.newLine();
        bw.write(randomNum + ":" + username + ":" + password + ":" + role);
        bw.close();
    }
    
    public void PMAdd(String username, String password, String role) throws IOException
    {
        BufferedWriter bw = new BufferedWriter(new FileWriter("user.txt", true));
        Random gen = new Random();
        String randomNum = String.format("PM" + "%04d", gen.nextInt(9999));

        bw.newLine();
        bw.write(randomNum + ":" + username + ":" + password + ":" + role);
        bw.close();
    }
    
}

