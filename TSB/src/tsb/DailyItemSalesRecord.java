/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsb;

import java.io.IOException;
public interface DailyItemSalesRecord {
    
    public void AddItemSales() throws IOException;
    public void EditItemSales() throws IOException;
    public void DeleteItemSales() throws IOException;
    
}
