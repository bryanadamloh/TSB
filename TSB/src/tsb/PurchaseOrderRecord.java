/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsb;

import java.io.IOException;
public interface PurchaseOrderRecord {
    
    public void addPurchaseOrder() throws IOException;
    public void editPurchaseOrder() throws IOException;
    public void viewPurchaseOrder() throws IOException;
    public void deletePurchaseOrder() throws IOException;
    
}
