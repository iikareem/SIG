
package Model;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Kareem Ashraf Mohamed
 */
public class HeaderInv {
    
    private int NumberT;
    private String CustmerS;
    private Date DateD;
    private DateFormat DateF = new SimpleDateFormat("dd-MM-yyyy");
    
    private ArrayList<InvItems> ITEMS;

    public HeaderInv(){
        
    }

    public HeaderInv(int num, String customer, Date dt){
        this.NumberT = num;
        this.CustmerS = customer;
        this.DateD = dt;
    }

    public HeaderInv(int i, String customerName, String dt){
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Date getDateInDateFormat(){
        return DateD;
    }

    public void setDateInDateFormat(Date DateD){
        this.DateD = DateD;
    }

    public int getOverallNumber() {
        return NumberT;
    }

    public void setOverallNumber(int NumberT) {
        this.NumberT = NumberT;
    }

    public String getCustmerName() {
        return CustmerS;
    }

    public void setCustmerName(String CustmerS) {
        this.CustmerS = CustmerS;
    }

    @Override
    public String toString() {
        return  NumberT +"," + DateF.format(DateD)+","+ CustmerS ;
    }

   

    public ArrayList<InvItems> getItems() {
        if (ITEMS == null){
            ITEMS = new ArrayList<>();
        }
        
        return ITEMS;
    }

    public void setItems(ArrayList<InvItems> ITEMS) {
        this.ITEMS = ITEMS;
    }
    
    
    public double getTotalIvoice(){
        double total=0.0;
        for (int i =0; i<getItems().size(); i++){
            total += getItems().get(i).getTotalCost();
        }
        
        return total ;
    
    }
    
    public void addInvoice(InvItems item){
    
        getItems().add(item);
    }
            
    
}
