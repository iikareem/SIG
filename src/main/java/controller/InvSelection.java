
package controller;

import Model.HeaderInv;
import Model.InvItems;
import Model.ItemsTable;
import View.MainUI;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Kareem Ashraf Mohamed
 */
public class InvSelection implements ListSelectionListener{

    private MainUI Frame;
    @Override
    public void valueChanged(ListSelectionEvent e) {
        //Get ID of Selected line in invoice table
        int numInvSelected=Frame.getInvoicesTable().getSelectedRow();
        System.out.println("Selected Invoice" +numInvSelected );
        if(numInvSelected != -1){
        //Get Data of Selected Line
        HeaderInv invSelected =Frame.getInvoicessArr().get(numInvSelected);
        ArrayList<InvItems> rowSelected=invSelected.getItems();
        ItemsTable itemsTableModel=new ItemsTable(rowSelected);
        Frame.setItemsArr(rowSelected);
        Frame.getInvoicesItemsTable().setModel(itemsTableModel);
        Frame.getCusName().setText(invSelected.getCustmerName());
        Frame.getInvID().setText(""+invSelected.getOverallNumber());
        Frame.getInvTotal().setText(""+invSelected.getTotalIvoice());
        Frame.getInvDate().setText(MainUI.dateFormat.format(invSelected.getDateInDateFormat()));
        }
        
              
        
    }

    public InvSelection(MainUI frame) {
        this.Frame = frame;
    }
    
    
    
}
