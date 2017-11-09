/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsb;

import java.io.IOException;
public interface SupplierRecord {
    
    public void addSupplier() throws IOException;
    public void modifySupplier() throws IOException;
    public void viewSupplier() throws IOException;
    public void deleteSupplier() throws IOException;
   
}