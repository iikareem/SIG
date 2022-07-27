
package View;


import controller.InvActionListening;
import controller.InvSelection;
import Model.HeaderInv;
import Model.TableInv;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Kareem Ashraf Mohamed
 */
public class InvHeaderCreator extends JDialog {
    private JTextField custNameTXT;
    private JTextField invDTXT;
    private JLabel custNameLABEL;
    private JLabel invDLABEL;
    private JButton AddBTN;
    private JButton CANCELBTN;

    public InvHeaderCreator(MainUI frame) {
        custNameLABEL = new JLabel("Customer Name :");
        custNameTXT = new JTextField(20);
        invDLABEL = new JLabel("Invoice Date :");
        invDTXT = new JTextField(20);
        AddBTN = new JButton("Add Invoice");
        CANCELBTN = new JButton("Cancel");
        
        AddBTN.setActionCommand("addNewInvoice");
        CANCELBTN.setActionCommand("cancelInvoice");
        
        AddBTN.addActionListener(frame.getActionLis());
        CANCELBTN.addActionListener(frame.getActionLis());
        setLayout(new GridLayout(3, 2));
        
        add(invDLABEL);
        add(invDTXT);
        add(custNameLABEL);
        add(custNameTXT);
        add(AddBTN);
        add(CANCELBTN);
        
        pack();
        
    }

    public JTextField getCustNameTXT() {
        return custNameTXT;
    }

    public JTextField getInvDTXT() {
        return invDTXT;
    }
    
}
