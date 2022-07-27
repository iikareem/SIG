
package View;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import controller.InvActionListening;
import controller.InvSelection;
import Model.HeaderInv;
import Model.TableInv;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;

/**
 *
 * @author Kareem Ashraf Mohamed
 */
public class InvCreator extends JDialog{
    private JTextField itemNameF;
    private JTextField itemCountF;
    private JTextField itemPriceF;
    private JLabel itemNameLABEL;
    private JLabel itemCountLABEL;
    private JLabel itemPriceLABEL;
    private JButton saveBTN;
    private JButton deleteBTN;
    
    public InvCreator(MainUI frame) {
        itemNameF = new JTextField(20);
        itemNameLABEL = new JLabel("  Item Name");
        
        itemCountF = new JTextField(20);
        itemCountLABEL = new JLabel("  Item Count");
        
        itemPriceF = new JTextField(20);
        itemPriceLABEL = new JLabel("  Item Price");
        
        saveBTN = new JButton("Save New Line");
        deleteBTN = new JButton("Cancel");
        
        saveBTN.setActionCommand("newLineSave");
        deleteBTN.setActionCommand("newLineDelete");
        
        saveBTN.addActionListener(frame.getActionLis());
        deleteBTN.addActionListener(frame.getActionLis());
        setLayout(new GridLayout(4, 2));
        
        add(itemNameLABEL);
        add(itemNameF);
        add(itemCountLABEL);
        add(itemCountF);
        add(itemPriceLABEL);
        add(itemPriceF);
        add(saveBTN);
        add(deleteBTN);
        
        pack();
    }

   

    

    public JTextField getItemNameF() {
        return itemNameF;
    }

    public JTextField getItemCountF() {
        return itemCountF;
    }

    public JTextField getItemPriceF() {
        return itemPriceF;
    }
}
