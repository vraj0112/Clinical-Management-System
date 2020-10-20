package clinical.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;  
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.File;

public class SeeMedicineInfo extends JFrame implements ActionListener{
    
    JLabel l0,l1,DarkBackGroundLabel,nameLabel,dispNameLabel,mobileLabel,dispMobileLabel;
    JTextField  tokenTextField;
    JButton search,generateBill,Back;
    JTextArea bill;
    JPanel info,billPanel;
    JTable medicineTable;
    DefaultTableModel MedicineTableModel;
    String dd,mm,yyyy;
    String hh="",mt="";
    String MobileNumber="";
    JFrame pastEntry;
    JTable PastEntriesTB;
    DefaultTableModel PastEntryTableModel;
    Dimension dim;
    Double consultingFees = 0.00;
    
    
    public void actionPerformed(ActionEvent ae)
    {
       if(ae.getSource()==generateBill){
            int medicineRowCount = medicineTable.getRowCount();
            Double[] medicinePrice = new Double[50];
            Double[] amount = new Double[50];
            Double[] quantity = new Double[50];
            Double total=0.0,NetTotal=0.0;
            try{
                connection c = new connection();
                c.createConnection();
                for(int i=0;i<medicineRowCount;i++){
                    String stmnt = "Select medicineprice from clinicms.medicineinfo where medicinename = '"+(String)MedicineTableModel.getValueAt(i,0)+"'";
                    ResultSet rs = c.s.executeQuery(stmnt);
                    while(rs.next()){
                        medicinePrice[i]=rs.getDouble("medicineprice");
                    }
                }
                
                c.disconnectConnection();
            }catch(Exception e){
                e.printStackTrace();
            }
            String dispbillnumber=" ";
            try{
                connection c = new connection();
                c.createConnection();
                String Date = dd+"/"+mm+"/"+yyyy;
                String stmnt = "Insert into clinicms.billinfo(mobile,date) values ('"+MobileNumber+"','"+Date+"')";
                c.s.executeUpdate(stmnt);
                String stmnt1 = "Select billno from clinicms.billinfo where mobile = '"+MobileNumber+"'";
                ResultSet rs = c.s.executeQuery(stmnt1);
                while(rs.next()){
                    dispbillnumber =rs.getString("billno");
                }
                c.disconnectConnection();
            }catch(Exception e){
                e.printStackTrace();
            }
            String netTotalPrecision = " ";
            try
            {
                String PathName = "F:\\SGP-1\\Database\\" + MobileNumber + "\\Bill\\" +dd+"_"+mm+"_"+yyyy+"_"+hh+"_"+mt+".pdf";
                Document doc = new Document();
                PdfWriter.getInstance(doc, new FileOutputStream(PathName));
                Paragraph HorizontalLine = new Paragraph("------------------------------------------------------------------------------------------------------------------------------------------");
                doc.setMargins(20f,20f,5f,5f);
                doc.open();
                com.itextpdf.text.Rectangle ps = doc.getPageSize();
                doc.add(HorizontalLine);
                com.itextpdf.text.Font HeadingFont =  FontFactory.getFont(FontFactory.TIMES, 25f,Font.BOLD, BaseColor.RED);
                Paragraph Heading = new Paragraph("Clinical Management System",HeadingFont);
                Heading.setAlignment(Element.ALIGN_CENTER);
                doc.add(Heading);

                doc.add(HorizontalLine);
                              
                PdfPTable info = new PdfPTable(2);
                info.setWidthPercentage(100);
                float[] columnWidth={400f,195f};
                info.setWidthPercentage(columnWidth,ps);
                
                PdfPCell PatientNameCell = new PdfPCell();
                PatientNameCell.setPhrase(new Phrase("Name :            "+dispNameLabel.getText()));
                PatientNameCell.setBorder(0);
                info.addCell(PatientNameCell);
                
                PdfPCell BillNoCell = new PdfPCell();
                BillNoCell.setPhrase(new Phrase("Bill No. :   "+dispbillnumber));
                BillNoCell.setBorder(0);
                info.addCell(BillNoCell);
                
                PdfPCell MobileCell = new PdfPCell();
                MobileCell.setPhrase(new Phrase("Mobile No. :    "+dispMobileLabel.getText()));
                MobileCell.setBorder(0);
                info.addCell(MobileCell);
                
                PdfPCell DateCell = new PdfPCell();
                DateCell.setPhrase(new Phrase("Date :       "+dd+"/"+mm+"/"+yyyy));
                DateCell.setBorder(0);
                info.addCell(DateCell);
                
                doc.add(info);
                
                doc.add(HorizontalLine);
                
                PdfPTable billFormat = new PdfPTable(4);
                billFormat.setWidthPercentage(100);
                float[] columnWidth1 = {365f,60f,70f,95f};
                billFormat.setWidthPercentage(columnWidth1,ps);
                
                PdfPCell DescriptionCell = new PdfPCell();
                DescriptionCell.setPhrase(new Phrase("Description"));
                DescriptionCell.setBorder(0);
                DescriptionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                billFormat.addCell(DescriptionCell);
                
                PdfPCell QuantityCell = new PdfPCell();
                QuantityCell.setPhrase(new Phrase("Quantity"));
                QuantityCell.setBorder(0);
                QuantityCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                billFormat.addCell(QuantityCell);
                
                PdfPCell PriceCell = new PdfPCell();
                PriceCell.setPhrase(new Phrase("Price Per Item"));
                PriceCell.setBorder(0);
                PriceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                billFormat.addCell(PriceCell);
                
                PdfPCell AmountCell = new PdfPCell();
                AmountCell.setPhrase(new Phrase("Amount"));
                AmountCell.setBorder(0);
                AmountCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                billFormat.addCell(AmountCell);
                
                doc.add(billFormat);
                
                doc.add(HorizontalLine);
                
                PdfPTable billDetails = new PdfPTable(4);
                billDetails.setWidthPercentage(100);
                billDetails.setWidthPercentage(columnWidth1,ps);
                String totalPrecision = " ";
                String[] amountPrecision = new String[50];
                medicineRowCount = medicineTable.getRowCount();
                for(int i=0;i<medicineRowCount;i++){
                    PdfPCell MedicineName = new PdfPCell();
                    MedicineName.setPhrase(new Phrase((String) MedicineTableModel.getValueAt(i, 0)));
                    //MedicineName.setHorizontalAlignment(Element.ALIGN_CENTER);
                    MedicineName.setBorder(0);
                    billDetails.addCell(MedicineName);
                    
                    PdfPCell MedicineQuantity = new PdfPCell();
                    MedicineQuantity.setPhrase(new Phrase((String) MedicineTableModel.getValueAt(i, 7)));
                    MedicineQuantity.setHorizontalAlignment(Element.ALIGN_CENTER);
                    MedicineQuantity.setBorder(0);
                    billDetails.addCell(MedicineQuantity);
                    
                    PdfPCell MedicinePrice = new PdfPCell();
                    MedicinePrice.setPhrase(new Phrase(medicinePrice[i].toString()));
                    MedicinePrice.setHorizontalAlignment(Element.ALIGN_CENTER);
                    MedicinePrice.setBorder(0);
                    billDetails.addCell(MedicinePrice);
                    
                    quantity[i]= new Double((MedicineTableModel.getValueAt(i, 7)).toString());
                    
                    amount[i]=quantity[i]*medicinePrice[i];
                    total=total+amount[i];
                    
                    totalPrecision = String.format("%.2f",total);
                    amountPrecision[i] = String.format("%.2f",amount[i]);
                    
                    PdfPCell Amount = new PdfPCell();
                    Amount.setPhrase(new Phrase(amountPrecision[i]));
                    Amount.setHorizontalAlignment(Element.ALIGN_CENTER);
                    Amount.setBorder(0);
                    billDetails.addCell(Amount);
                }
                
                doc.add(billDetails);
                
                PdfPTable netAmountDetails = new PdfPTable(2);
                netAmountDetails.setWidthPercentage(100);
                columnWidth[0]=495f;
                columnWidth[1]=95f;
                netAmountDetails.setWidthPercentage(columnWidth,ps);
                
                doc.add(HorizontalLine);
                
                PdfPCell TotalCell = new PdfPCell();
                TotalCell.setPhrase(new Phrase("Total   : "));
                TotalCell.setBorder(0);
                TotalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                netAmountDetails.addCell(TotalCell);
                
                PdfPCell dispTotalCell = new PdfPCell();
                dispTotalCell.setPhrase(new Phrase(totalPrecision));
                dispTotalCell.setBorder(0);
                dispTotalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                netAmountDetails.addCell(dispTotalCell);
                
                PdfPCell ConsultingCell = new PdfPCell();
                ConsultingCell.setPhrase(new Phrase("Consulting Fees   : "));
                ConsultingCell.setBorder(0);
                ConsultingCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                netAmountDetails.addCell(ConsultingCell);
                
                PdfPCell dispConsultingCell = new PdfPCell();
                dispConsultingCell.setPhrase(new Phrase(consultingFees.toString()));
                dispConsultingCell.setBorder(0);
                dispConsultingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                netAmountDetails.addCell(dispConsultingCell);
                
                PdfPCell NetTotalCell = new PdfPCell();
                NetTotalCell.setPhrase(new Phrase("Net Total   : "));
                NetTotalCell.setBorder(0);
                NetTotalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                netAmountDetails.addCell(NetTotalCell);
                
                NetTotal=total+consultingFees;
                netTotalPrecision = String.format("%.2f",NetTotal);
                
                PdfPCell dispNetTotalCell = new PdfPCell();
                dispNetTotalCell.setPhrase(new Phrase(netTotalPrecision));
                dispNetTotalCell.setBorder(0);
                dispNetTotalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                netAmountDetails.addCell(dispNetTotalCell);
                
                doc.add(netAmountDetails);
                
                PdfPTable taxLine = new PdfPTable(1);
                taxLine.setWidthPercentage(100);
                float[] columnWidth2={590f};
                taxLine.setWidthPercentage(columnWidth2,ps);
                
                doc.add(HorizontalLine);
                
                PdfPCell taxLineCell = new PdfPCell();
                taxLineCell.setPhrase(new Phrase("*****Inclusive of All Taxes*****"));
                taxLineCell.setBorder(0);
                taxLineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                taxLineCell.setVerticalAlignment(Element.ALIGN_CENTER);
                taxLine.addCell(taxLineCell);
                
                doc.add(taxLine);
                
                PdfPTable footerPart = new PdfPTable(1);
                footerPart.setWidthPercentage(100);
                footerPart.setWidthPercentage(columnWidth2,ps);
                
                doc.add(HorizontalLine);
                doc.add(new Paragraph(" "));
                doc.add(new Paragraph(" "));
                
                PdfPCell forPharmacy = new PdfPCell();
                forPharmacy.setPhrase(new Phrase("For Pharmacy Use"));
                forPharmacy.setBorder(0);
                forPharmacy.setHorizontalAlignment(Element.ALIGN_RIGHT);
                forPharmacy.setVerticalAlignment(Element.ALIGN_BOTTOM);
                footerPart.addCell(forPharmacy);
                
                doc.add(footerPart);
                    
                doc.close();
                JOptionPane.showMessageDialog(null,"Bill Generated Successfully.");
            }catch(Exception e){
                e.printStackTrace();
            }
            try{
                connection c = new connection();
                c.createConnection();
                String stmnt = "Update clinicms.billinfo set nettotal = '"+netTotalPrecision+"' where billno = '"+dispbillnumber+"'";
                c.s.executeUpdate(stmnt);
                c.disconnectConnection();
            }catch(Exception e){
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null,"Bill is Opening. Please Wait for a while");
            File pdfFile = new File("F:\\SGP-1\\Database\\"+MobileNumber+"\\Bill\\"+dd+"_"+mm+"_"+yyyy+"_"+hh+"_"+mt+".pdf");
            try{
                if(Desktop.isDesktopSupported()){
                    Desktop.getDesktop().open(pdfFile);
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }else if(ae.getSource()==Back){
            new Chemist().setVisible(true);
            this.setVisible(false);
        }
    }
    
    SeeMedicineInfo()
    {
        ImageIcon  tempDarkBackGroundImport = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/DarkBackGround.png"));
        Image temp = tempDarkBackGroundImport.getImage().getScaledInstance(660, 50, Image.SCALE_DEFAULT);
        ImageIcon DarkBackGroundImport = new ImageIcon(temp);
        
        DarkBackGroundLabel = new JLabel(DarkBackGroundImport);
        DarkBackGroundLabel.setBounds(0,0,660,50);
        add(DarkBackGroundLabel);
        
        l0 = new JLabel("Medicine Info");
        l0.setBounds(230,0,200,50);
        l0.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        l0.setHorizontalAlignment(SwingConstants.CENTER);
        l0.setForeground(Color.WHITE);
        DarkBackGroundLabel.add(l0);
        
        info = new JPanel();
        info.setLayout(null);
        info.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Info", TitledBorder.LEADING,TitledBorder.TOP, null, new Color(0,0,0)));
        info.setBounds(20,110,600,430);
        add(info);
        
        nameLabel=new JLabel("Name : ");
        nameLabel.setFont(new Font("Times New Roman",Font.PLAIN,23));
        nameLabel.setBounds(40,24,200,50);
        info.add(nameLabel);
        
        dispNameLabel=new JLabel();
        dispNameLabel.setFont(new Font("Times New Roman",Font.PLAIN,23));
        dispNameLabel.setBounds(115,24,200,50);
        info.add(dispNameLabel);
        
        mobileLabel=new JLabel("Mobile Number : ");
        mobileLabel.setFont(new Font("Times New Roman",Font.PLAIN,23));
        mobileLabel.setBounds(40,72,200,50);
        info.add(mobileLabel);
        
        dispMobileLabel=new JLabel();
        dispMobileLabel.setFont(new Font("Times New Roman",Font.PLAIN,23));
        dispMobileLabel.setBounds(210,72,200,50);
        info.add(dispMobileLabel);
        
        l1=new JLabel("Token Number");
        l1.setFont(new Font("Times New Roman",Font.PLAIN,23));
        l1.setBounds(170,60,200,50);
        
        add(l1);
        
        tokenTextField=new JTextField();
        tokenTextField.setBounds(325,70,80,30);
        tokenTextField.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(tokenTextField);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/search.png"));
        Image i2 = i1.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        
        search=new JButton(i3);
        search.setBounds(405,70,40,29);
        search.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        searchactionPerformed(e);
                    }
                }
        );
        add(search);
        
        medicineTable= new JTable()
        {
            public boolean isCellEditable(int rowIndex, int mColIndex) 
            {
                return false;
            }
        };
        Object[] MedicineTable_column_name = {"Medicine","Type","Dosage","Morning","Noon","Evening","Before/After Meal","Quantity"};
        MedicineTableModel = (DefaultTableModel)medicineTable.getModel();
        MedicineTableModel.setColumnIdentifiers(MedicineTable_column_name);
        medicineTable.setModel(MedicineTableModel);
        medicineTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        medicineTable.getColumnModel().getColumn(1).setPreferredWidth(50);
        medicineTable.getColumnModel().getColumn(2).setPreferredWidth(40);
        medicineTable.getColumnModel().getColumn(3).setPreferredWidth(30);
        medicineTable.getColumnModel().getColumn(4).setPreferredWidth(20);
        medicineTable.getColumnModel().getColumn(5).setPreferredWidth(30);
        medicineTable.getColumnModel().getColumn(7).setPreferredWidth(30);
              
        JScrollPane sp = new JScrollPane(medicineTable);
        sp.setBounds(5,140,590,280);
        info.add(sp);
        
        generateBill=new JButton("Generate Bill");
        generateBill.setBounds(145,555,150,40);
        generateBill.addActionListener(this);
        generateBill.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(generateBill);
        
        Back=new JButton("Back");
        Back.setBounds(345,555,150,40);
        Back.addActionListener(this);
        Back.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(Back);
        
        setTitle("Medicine Info");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setSize(660,650);
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setVisible(true);
    }
    
    public void searchactionPerformed(ActionEvent e)
    {
        String token =  tokenTextField.getText();

        if(token.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please Enter TokenNumber");
            dispNameLabel.setText("");
            dispMobileLabel.setText("");
            tokenTextField.setText("");
        }
        else
        {    
            int No_Of_Row = MedicineTableModel.getRowCount();
            for(int i=No_Of_Row - 1 ; i>=0 ; i--)
            {
                MedicineTableModel.removeRow(i);
            }
        
            try
            {
                connection c = new connection();
                c.createConnection();
            
                String getData = "Select * from clinicms.appointment where tokenid = '"+token+"'";
                ResultSet rs = c.s.executeQuery(getData);
                if(rs.next())
                {   
                    String name = rs.getString("name");
                    String mobile = rs.getString("mobile");
                    
                    dispNameLabel.setText(name);
                    dispMobileLabel.setText(mobile);
                    
                    MobileNumber = dispMobileLabel.getText();
                    java.util.Date TodayDate = new java.util.Date();
                    String dd_formate = "dd";
                    String mm_formate = "MM";
                    String yyyy_formate = "YYYY";
                    SimpleDateFormat SDFdd = new SimpleDateFormat(dd_formate);
                    dd = SDFdd.format(TodayDate);
                    SimpleDateFormat SDFmm = new SimpleDateFormat(mm_formate);
                    mm = SDFmm.format(TodayDate);
                    SimpleDateFormat SDFyyyy = new SimpleDateFormat(yyyy_formate);
                    yyyy = SDFyyyy.format(TodayDate);
                    
                    pastEntry = new JFrame();
        
                    JLabel TokenLabel = new JLabel("Token id : ");
                    TokenLabel.setBounds(20,20,150,50);
                    TokenLabel.setFont(new Font("Times New Roman",Font.PLAIN,20));
                    pastEntry.add(TokenLabel);

                    JLabel dispTokenLabel = new JLabel(token);
                    dispTokenLabel.setBounds(120,30,70,30);
                    dispTokenLabel.setFont(new Font("Times New Roman",Font.PLAIN,20));
                    pastEntry.add(dispTokenLabel);

                    JLabel NameLabel = new JLabel("Name : ");
                    NameLabel.setBounds(20,60,150,50);
                    NameLabel.setFont(new Font("Times New Roman",Font.PLAIN,20));
                    pastEntry.add(NameLabel);

                    JLabel dispNameLabel = new JLabel(name);
                    dispNameLabel.setBounds(120,70,200,30);
                    dispNameLabel.setFont(new Font("Times New Roman",Font.PLAIN,20));
                    pastEntry.add(dispNameLabel);
            
                    PastEntriesTB = new JTable()
                    {
                        public boolean isCellEditable(int row, int column){
                            return false;
                        } 
                    };

                    PastEntryTableModel = (DefaultTableModel)PastEntriesTB.getModel();
                    PastEntriesTB.setModel(PastEntryTableModel);
                    Object []PastEntriesTableCloumnModel = {"Time Record"};
                    PastEntryTableModel.setColumnIdentifiers(PastEntriesTableCloumnModel);
                    PastEntriesTB.setFont(new Font("Times New Roman",Font.PLAIN,18));

                    JScrollPane sp = new JScrollPane(PastEntriesTB);
                    sp.setBounds(5,110,570,270);
                    pastEntry.add(sp);
                    
                    rs = null;
                    String gethhmt = "Select * from patient"+MobileNumber+".time_record where dd = '"+dd+"' AND mm = '"+mm+"' AND yyyy = '"+yyyy+"'";
                    rs = c.s.executeQuery(gethhmt);

                    while(rs.next())
                    {
                        String Date = "";
                        Date += rs.getString("dd");
                        Date += "/";
                        Date += rs.getString("mm");
                        Date += "/";
                        Date += rs.getString("yyyy");
                        Date += "   ";
                        Date += rs.getString("hh");
                        Date += ":";
                        Date += rs.getString("mt");

                        Object[] Value = {Date};

                        PastEntryTableModel.addRow(Value);
                    }


                    JButton LoadData = new JButton("Load Data");
                    LoadData.setBounds(100,400,130,40);
                    LoadData.setFont(new Font("Times New Roman",Font.PLAIN,20));
                    LoadData.addActionListener(
                        new ActionListener()
                        {
                            public void actionPerformed(ActionEvent e)
                            {
                                LoadDataactionPerformed(e);
                            }
                        }  
                    );
                    pastEntry.add(LoadData);

                    JButton BackPE = new JButton("Back");
                    BackPE.setBounds(350,400,130,40);
                    BackPE.setFont(new Font("Times New Roman",Font.PLAIN,20));
                    BackPE.addActionListener(
                        new ActionListener()
                        {
                            public void actionPerformed(ActionEvent e)
                            {
                                BackPEactionPerformed(e);
                            }
                        }
                    );
                    pastEntry.add(BackPE);

                    pastEntry.setSize(600,500);
                    pastEntry.setLocation(dim.width/2-pastEntry.getSize().width/2, dim.height/2-pastEntry.getSize().height/2);
                    pastEntry.setResizable(false);
                    pastEntry.setTitle("Past Entries");
                    pastEntry.setLayout(null);
                    pastEntry.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null,"Please Enter valid Token Number.");
                }             
            }
            catch(Exception ae){}
        }
    }
    
    public void LoadDataactionPerformed(ActionEvent e)
    {
        if(PastEntriesTB.getSelectedRowCount() == 1)
        {
            int row_no = PastEntriesTB.getSelectedRow();
           
            String DateToOpen = (String)PastEntryTableModel.getValueAt(row_no, 0);
            hh = DateToOpen.substring(13,15);
            mt = DateToOpen.substring(16,18);

            try
            {
                connection c = new connection();
                c.createConnection(); 

                String stmnt = "select * from patient"+MobileNumber+".m_"+dd+"_"+mm+"_"+yyyy+"_"+hh+"_"+mt;
                ResultSet rs = c.s.executeQuery(stmnt);
                
                while(rs.next())
                {
                    String medicinename = rs.getString("medicinename");
                    String medicinetype = rs.getString("medicinetype");
                    String dosage = rs.getString("dosage");
                    String inmorning = rs.getString("inmorning");
                    String innoon = rs.getString("innoon");
                    String inevening = rs.getString("inevening");
                    String b_a_meal = rs.getString("b_a_meal");
                    String quantity = rs.getString("quantity");
                    
                    Object[] value = {medicinename , medicinetype , dosage , inmorning, innoon, inevening, b_a_meal, quantity};
                    MedicineTableModel.addRow(value);
                }
                
                stmnt = "select Consulting from patient"+MobileNumber+".time_record where dd="+dd+" and mm="+mm+" and yyyy="+yyyy+" and hh="+hh+" and mt="+mt;
                rs = c.s.executeQuery(stmnt);
                rs.next();
                
                if(rs.getString("Consulting").equals("1"))
                {
                    consultingFees = 200.00;
                }
                else
                {
                    consultingFees = 0.00;
                }
            }
            catch(Exception E)
            {
                JOptionPane.showMessageDialog(null, "Hekkkp");
                E.printStackTrace();
            }
            pastEntry.setVisible(false);
        }
        else if(PastEntriesTB.getSelectedRowCount() > 1)
        {
            JOptionPane.showMessageDialog(null, "Please Select Only One Record");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please Select record");
        }
    }
    
    public void BackPEactionPerformed(ActionEvent e)
    {
        pastEntry.setVisible(false);
    }
    
    public static void main(String[] args){
         new SeeMedicineInfo();
    }
}
