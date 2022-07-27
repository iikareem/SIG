
package controller;

import Model.HeaderInv;
import Model.TableInv;
import Model.InvItems;
import Model.ItemsTable;
import View.MainUI;
import View.InvHeaderCreator;
import View.InvCreator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Kareem Ashraf Mohamed
 */
public class InvActionListening implements ActionListener{
    private MainUI FRAME;
    private InvHeaderCreator HeaderD;
   
    private InvCreator ItemsD;
    
    
    public InvActionListening(MainUI frame) {
        this.FRAME=frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Load Files" -> {
                try {
                    loadFiles();
                } catch (IOException | ParseException ex) {
                    Logger.getLogger(InvActionListening.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
            case "Save Files" -> {
                try {
                    saveFiles();
                } catch (IOException ex) {
                    Logger.getLogger(InvActionListening.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

                
            case "New Invoice" -> createNewInvoice();
                
            case "Delete Invoice" -> deleteInvoice();
        
            case "Save Invoice" -> createNewLine();
                
            case "Cancel Changes" -> deleteLine();
            
            case"addNewInvoice"-> {
                try {
                    addNewInovice();
                } catch (ParseException ex) {
                    Logger.getLogger(InvActionListening.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            
            case"cancelInvoice"-> cancelInvoice();
            
            case"newLineSave" -> newLineSave();
            case"newLineDelete"-> newLineDelete();
            

        }
        
    }

    

    private void createNewInvoice() {
        
        HeaderD = new InvHeaderCreator(FRAME);
        HeaderD.setVisible(true);
    }
    
    
    private void deleteInvoice() {
        int selectedInvoiceIndex = FRAME.getInvoicesTable().getSelectedRow();
        if(selectedInvoiceIndex != -1){
        
            FRAME.getInvoicessArr().remove(selectedInvoiceIndex);
            FRAME.getHeadTModel().fireTableDataChanged();
            
            FRAME.getInvoicesItemsTable().setModel(new ItemsTable (null));
            FRAME.setItemsArr(null);
            FRAME.getCusName().setText("");
            FRAME.getInvID().setText("");
            FRAME.getInvTotal().setText("");
            FRAME.getInvDate().setText("");
        
        
        }
        
        
    }
    private void createNewLine() {
        ItemsD = new InvCreator(FRAME);
        ItemsD.setVisible(true);
    }
    
     
    
    private void deleteLine() {
        
        int selectedLine = FRAME.getInvoicesItemsTable().getSelectedRow();
        int selectedInvoice = FRAME.getInvoicesTable().getSelectedRow();
        if(selectedLine !=-1){
        
            FRAME.getItemsArr().remove(selectedLine);
            ItemsTable itemsTableModel =(ItemsTable) FRAME.getInvoicesItemsTable().getModel();
            itemsTableModel.fireTableDataChanged();
            FRAME.getInvTotal().setText(""+FRAME.getInvoicessArr().get(selectedInvoice).getTotalIvoice());
            FRAME.getHeadTModel().fireTableDataChanged();
            FRAME.getInvoicesTable().setRowSelectionInterval(selectedInvoice, selectedInvoice);
        }
            
    }

    

   

    

    private void saveFiles() throws IOException {
        ArrayList <HeaderInv> invoicesSave= FRAME.getInvoicessArr();
        JFileChooser fileChooser=new JFileChooser ();
        try{
        int result = fileChooser.showSaveDialog(FRAME);
        if(result == JFileChooser.APPROVE_OPTION){
            
            File headerFile = fileChooser.getSelectedFile();
            FileWriter headFileWriter = new FileWriter(headerFile);
            String headers="";
            String lines="";
             JOptionPane.showMessageDialog(FRAME, "File Header Saved successfully", "Attention", JOptionPane.WARNING_MESSAGE);
            
            for(HeaderInv invoicesHead : invoicesSave){
                headers += invoicesHead.toString();
                headers += "\n";
            for(InvItems invoicesitems : invoicesHead.getItems()){
                lines += invoicesitems.toString();
                lines += "\n";
            }    
             
        }
                  

            headers=headers.substring(0, headers.length()-1);
            lines=lines.substring(0, lines.length()-1);
            result = fileChooser.showSaveDialog(FRAME);
            File lineFile = fileChooser.getSelectedFile();
            FileWriter lineFileWriter = new FileWriter(lineFile);
            JOptionPane.showMessageDialog(FRAME, "File Line Saved successfully", "Attention", JOptionPane.WARNING_MESSAGE);
            headFileWriter.write(headers);
            lineFileWriter.write(lines);
            headFileWriter.close();
            lineFileWriter.close();
        }
    }catch (IOException ex){
        
        JOptionPane.showMessageDialog(FRAME, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        
        
    }}

    
    
    private void loadFiles() throws IOException, ParseException {
        JOptionPane.showMessageDialog(FRAME, "Please Select Header File!", "Attention", JOptionPane.WARNING_MESSAGE);
        JFileChooser fileChooser=new JFileChooser();
        try{
        int result= fileChooser.showOpenDialog(FRAME);
        
        if (result==JFileChooser.APPROVE_OPTION)
        {
            File headerFile = fileChooser.getSelectedFile();
            Path headerpath = Paths.get(headerFile.getAbsolutePath());
            List<String> headerLines= Files.readAllLines(headerpath);
            
            ArrayList <HeaderInv>invHeaders=new ArrayList<>(); // Array to get All Invoice Data 
            // get file invoice header Data 
            
            for(String headerLine:headerLines){
            // Invoice Header Data 
                String []invData=headerLine.split(",");
                String el1 = invData[0];             //Invoice num (int)
                String el2=invData[1];               //Date      (Date)
                String cusName=invData[2];          // Customer name (String )
                
                 int invId=Integer.parseInt(el1);
                Date invDt=MainUI.dateFormat.parse(el2);
                //object from class invoice header
                HeaderInv header=new HeaderInv(invId, cusName, invDt); // Array to get line by line 
                
                invHeaders.add(header);
            }
            FRAME.setInvoicessArr(invHeaders);
            JOptionPane.showMessageDialog(FRAME, "Please Select Items File!", "Attention", JOptionPane.WARNING_MESSAGE);
            // get file invoice Line Data 
            result=fileChooser.showOpenDialog(FRAME);
            if(result==JFileChooser.APPROVE_OPTION)
            {
                File itemsFile=fileChooser.getSelectedFile();
                Path itemsPath=Paths.get(itemsFile.getAbsolutePath());
                List<String> itemsLines=Files.readAllLines(itemsPath);
                ArrayList<InvItems> invItems=new ArrayList<>();

            for (String itemsLine: itemsLines )
            {
            
                String [] itemsData = itemsLine.split(",");
                //Invoice Line of items Data 
                String el1 = itemsData[0];                 // Invoice Num (int)
                String itemName=itemsData[1];             // Item Name     (String)
                String el3=itemsData[2];                 //Price          (double)
                String el4=itemsData[3];                // Count         (int)
                 
                 int Id=Integer.parseInt(el1);
                 double itemPrice=Double.parseDouble(el3);
                 int itemCount=Integer.parseInt(el4);
                 
                 HeaderInv inv=FRAME.getInvObject(Id);
                 
                 InvItems items=new InvItems(itemName, itemPrice, itemCount, inv);
                 inv.getItems().add(items);
            }
            
            }
            TableInv headerTableModel=new TableInv(invHeaders);
            FRAME.setHeadTModel(headerTableModel);
            FRAME.getInvoicesTable().setModel(headerTableModel);
            System.out.println("Files Read");
               }
        }catch (IOException | ParseException ex){
            JOptionPane.showMessageDialog(FRAME, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        }

    private void addNewInovice() throws ParseException {
       HeaderD.setVisible(false);
       String customerName=HeaderD.getCustNameTXT().getText();
       
       
       String str=HeaderD.getInvDTXT().getText();
       
       Date dt = new Date();
       try{
       dt = MainUI.dateFormat.parse(str);
       }catch (ParseException ex){
       
           JOptionPane.showMessageDialog(FRAME, "Cann't Parse Date", "Resetting to today", JOptionPane.ERROR_MESSAGE);
       }
       
       int invNum=0;
       for(HeaderInv inv : FRAME.getInvoicessArr()){
       
           if(inv.getOverallNumber() > invNum) invNum = inv.getOverallNumber();
       }
       invNum++;
       
       HeaderInv invoice = new HeaderInv(invNum,customerName,dt);
       FRAME.getInvoicessArr().add(invoice);
       FRAME.getHeadTModel().fireTableDataChanged();
       
      
       HeaderD.dispose();
       HeaderD = null;
       
       
    }

    private void cancelInvoice() {
       HeaderD.setVisible(false);
       HeaderD.dispose();
       HeaderD = null; 
    }

    private void newLineDelete() {
        ItemsD.setVisible(false);
       ItemsD.dispose();
       ItemsD = null; 
        
    }

    private void newLineSave() {
        ItemsD.setVisible(false);
        String itemName = ItemsD.getItemNameF().getText();
        String count = ItemsD.getItemCountF().getText();
        String price = ItemsD.getItemPriceF().getText();
        
        int itemCount= 1 ;
        double itemPrice = 1;
        
        try{
        
            itemCount = Integer.parseInt(count);
        }catch(NumberFormatException ex){
        
            JOptionPane.showMessageDialog(FRAME, "Cannot Convert Number", "Invaild Number Format", JOptionPane.ERROR_MESSAGE);
        }try{
        
            itemPrice = Double.parseDouble(price);
        }catch(NumberFormatException ex){
        
            JOptionPane.showMessageDialog(FRAME, "Cannot Convert Price", "Invaild Number Format", JOptionPane.ERROR_MESSAGE);
        }
        int selectedInvoice = FRAME.getInvoicesTable().getSelectedRow();
        if (selectedInvoice !=-1){
            
            HeaderInv numHeader = FRAME.getInvoicessArr().get(selectedInvoice);
            InvItems items = new InvItems(itemName, itemPrice, itemCount, numHeader);
            //numHeader.getItems().add(items);
            FRAME.getItemsArr().add(items);
            ItemsTable itemsTableModel = (ItemsTable) FRAME.getInvoicesItemsTable().getModel();
            itemsTableModel.fireTableDataChanged();
            FRAME.getHeadTModel().fireTableDataChanged();

        }
       FRAME.getInvoicesTable().setRowSelectionInterval(selectedInvoice, selectedInvoice);
       ItemsD.dispose();
       ItemsD = null; 
        
    }
    }
    

