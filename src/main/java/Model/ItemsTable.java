
package Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Kareem Ashraf Mohamed
 */
public class ItemsTable extends AbstractTableModel {
    
    private ArrayList <InvItems> ITMArray;
    private String []Columns;
    
    public ItemsTable(ArrayList<InvItems> itemsArray) {
       this.Columns = new String[]{"No.","Item Name", "Item Price", "Count", "Item Total"};
        this.ITMArray = itemsArray;
    }


    @Override
    public int getRowCount() {
        return ITMArray == null ? 0 : ITMArray.size();
    }
    
    @Override
    public int getColumnCount() {
        return Columns.length;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        if (ITMArray == null){
            return "";
        }
        else {
        
        InvItems invItems = ITMArray.get(rowIndex);
        switch (columnIndex){
            
            case 0 ->{
            
                return invItems.getHeader().getOverallNumber();
            }
            
            case 1 -> {
                return invItems.getItemTitle();
            }
            case 2 -> {
                return invItems.getPr();
            }
            case 3 -> {
                return invItems.getCount();
            }
            case 4 -> {
                return invItems.getTotalCost();
            }
        }
        }return "";
    }
    public String getColumnName(int column) {
      //return Name of col;

        return Columns[column]; 
    }
    
}
