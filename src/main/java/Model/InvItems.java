
package Model;

/**
 *
 * @author Kareem Ashraf Mohamed
 */
public class InvItems {
    
    private int invoiceID;
    private String itemNAME;
    private double itemPRICE;
    private int itemCOUNT;
    
    private HeaderInv Head;

    public InvItems() {
    }

    public InvItems(String item, double price, int count, HeaderInv header) {
        
        this.itemNAME = item;
        
        this.itemPRICE = price;
        this.itemCOUNT = count;
        this.Head = header;
    }

    public HeaderInv getHeader() {
        return Head;
    }

    public void setHeader(HeaderInv Head) {
        this.Head = Head;
    }

    public String getItemTitle() {
        return itemNAME;
    }

    public void setItemTitle(String itemNAME) {
        this.itemNAME = itemNAME;
    }

    public double getPr() {
        return itemPRICE;
    }

    public void setPr(double itemPRICE) { // pr for price
        this.itemPRICE = itemPRICE;
    }

    public int getCount() {
        return itemCOUNT;
    }

    public void setCount(int itemCOUNT) {
        this.itemCOUNT = itemCOUNT;
    }
    
    public double getTotalCost(){
    return itemPRICE*itemCOUNT;
    }

    @Override
    public String toString() {
        return Head.getOverallNumber() + ","  + itemNAME + "," + itemPRICE + "," + itemCOUNT;
    }

    public int getInvCode() {
        return invoiceID;
    }

    public void setInvCode(int invoiceID) {
        this.invoiceID = invoiceID;
    }
    
    
}
