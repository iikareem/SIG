
package Model;

import View.MainUI;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Kareem Ashraf Mohamed
 */
public class TableInv extends AbstractTableModel  {
    
    private ArrayList <HeaderInv>invArr;
    private String []Colums;

    public TableInv(ArrayList<HeaderInv> invoicesArray) {
       this.Colums = new String[]{"No.", "Date", "Customer", "Total"};
        this.invArr = invoicesArray;
    }

    @Override
    public int getRowCount() {// Reserve number of rows need to draw 
        return invArr.size();
      // return 5; 
    }

    @Override
    public int getColumnCount() {// Reserve number of colum need to draw 
        return Colums.length;
        //return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       // return "test";
       // To draw each cell value 
        HeaderInv inv = invArr.get(rowIndex);
        switch (columnIndex){
            case 0 -> {
                return inv.getOverallNumber();
            }
            case 1 -> {
                return MainUI.dateFormat.format( inv.getDateInDateFormat());
            }
            case 2 -> {
                return inv.getCustmerName();
            }
            case 3 -> {
                return inv.getTotalIvoice();
            }
        }return "";
    }
    @Override
    public String getColumnName(int column) {
      //return "Column";

        return Colums[column]; 
}
    
}

          
        

          
    

    
    
    
    

