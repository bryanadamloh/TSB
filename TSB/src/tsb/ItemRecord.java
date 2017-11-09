/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsb;

import java.io.IOException;
public interface ItemRecord {

    public void addItem() throws IOException;
    public void viewItem() throws IOException;
    public void modifyItem() throws IOException;
    public void deleteItem() throws IOException;
    
}
