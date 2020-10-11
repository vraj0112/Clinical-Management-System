package clinical.management.system;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.sql.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.util.concurrent.ExecutionException;

public class AddReport extends JFrame implements ActionListener
{
    JLabel DarkBackGroundLabel,addReport,token,name,dispname,medicineType,MedicineNameLabel,dosage,QuantityType,QuantityLabel,DosageParameter;
    JLabel Symptoms,effectLabel,OtherSymptomLabel,PrecautionsLabel,AllergyLabel,DateLabel,TimeLabel,dispDate,dispTime;
    JTextField entertoken,enterDosage,Quantity,OtherSymptomText,PrecautionsText,AllergyText;
    JButton searchToken,Add,Reset,Back,InsertMed,EditMed,DeleteMed,InsertSymptoms,EditSymptoms,DeleteSymptoms,UpdateSymptoms,UpdateMed,PastEntries;
    JPanel addMedicine,addSymptoms,addOthers;
    String number;
    JComboBox MedicineTypeCMB,MedicineNameCMB,effectlevelCMB,symptomsCMB;
    JCheckBox MorningCB, AtnoonCB, EveningCB,ConsultingFees;
    ButtonGroup RadioGroup;
    JRadioButton BeforeMealRB, AfterMealRB;
    JTable medicineTable,symptomsTable;

    int i=0,j=0;
    String MobileNumber;
    DefaultTableModel MedicineTableModel,SymptomsTableModel;
    
    JLabel DiseaseLabel;
    JTextField DiseaseTF;
    
    JFrame pastEntry;
    
    String dd,mm,yyyy,hh,mt;
    
    String Precautions,Allergy,Disease,Name,tokennumber;
    
    Dimension dim ;
    
    JTable PastEntriesTB;
    DefaultTableModel PastEntryTableModel;
   
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==searchToken){
            try{
                connection c = new connection();
                c.createConnection();
                number = entertoken.getText();
                String str = "Select name,mobile from clinicms.appointment where tokenid = '"+number+"'";
                ResultSet rs = c.s.executeQuery(str);
                while(rs.next()){
                    dispname.setText(rs.getString("name"));
                    MobileNumber = (String)rs.getString("mobile");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource()==Back){
            new Doctor().setVisible(true);
            this.setVisible(false);
        }
    }
    
    AddReport()
    {
        
        ImageIcon  tempDarkBackGroundImport = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/DarkBackGround.png"));
        Image temp = tempDarkBackGroundImport.getImage().getScaledInstance(1300, 50, Image.SCALE_DEFAULT);
        ImageIcon DarkBackGroundImport = new ImageIcon(temp);
        
        DarkBackGroundLabel = new JLabel(DarkBackGroundImport);
        DarkBackGroundLabel.setBounds(0,0,1300,50);
        add(DarkBackGroundLabel);
        
        addReport = new JLabel("Add Report");
        addReport.setBounds(550,0,200,50);
        addReport.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        addReport.setHorizontalAlignment(SwingConstants.CENTER);
        addReport.setForeground(Color.WHITE);
        DarkBackGroundLabel.add(addReport);
        
        token = new JLabel("Token : ");
        token.setBounds(20,60,100,50);
        token.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        add(token);
        
        entertoken = new JTextField();
        entertoken.setBounds(120,70,70,30);
        entertoken.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        add(entertoken);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/search.png"));
        Image i2 = i1.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        ImageIcon search = new ImageIcon(i2);
        
        searchToken=new JButton(search);
        searchToken.setBounds(190,70,40,29);
        add(searchToken);
        searchToken.addActionListener
        (
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        searchTokenactionPerformed(e);
                    }
                }
        );
        
        PastEntries=new JButton("Past Entries");
        PastEntries.setBounds(250,70,150,29);
        PastEntries.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(PastEntries);
        PastEntries.addActionListener
        (
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        PastEntriesactionPerformed(e);
                    }
                }
        );
        
        ConsultingFees = new JCheckBox("Take Consulting Fees");
        ConsultingFees.setBounds(450,70,200,30);
        ConsultingFees.setFont(new Font("Times New Roman",Font.PLAIN,18));
        ConsultingFees.setSelected(true);
        add(ConsultingFees);
        
        name = new JLabel("Name : ");
        name.setBounds(20,100,100,50);
        name.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        add(name);
        
        dispname = new JLabel();
        dispname.setBounds(120,100,900,50);
        dispname.setFont(new Font("Times New Roman", Font.PLAIN, 23));
        add(dispname);
        
        DateLabel = new JLabel("Date : ");
        DateLabel.setBounds(1000,70,100,30);
        DateLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        add(DateLabel);
        
        TimeLabel = new JLabel("Time : ");
        TimeLabel.setBounds(1000,110,100,30);
        TimeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        add(TimeLabel);
        
        Date TodayDate = new Date();
        String date_formate = "dd-MMM-yyyy";
        String dd_formate = "dd";
        String mm_formate = "MM";
        String yyyy_formate = "YYYY";
        String hh_formate = "HH";
        String mt_formate = "mm";
        String time_formate = "HH:mm";
        SimpleDateFormat SDFDate = new SimpleDateFormat(date_formate);
        SimpleDateFormat SDFTime = new SimpleDateFormat(time_formate);
        String cur_date = SDFDate.format(TodayDate);
        String cur_time = SDFTime.format(TodayDate);
        SimpleDateFormat SDFdd = new SimpleDateFormat(dd_formate);
        dd = SDFdd.format(TodayDate);
        SimpleDateFormat SDFmm = new SimpleDateFormat(mm_formate);
        mm = SDFmm.format(TodayDate);
        SimpleDateFormat SDFyyyy = new SimpleDateFormat(yyyy_formate);
        yyyy = SDFyyyy.format(TodayDate);
        SimpleDateFormat SDFhh = new SimpleDateFormat(hh_formate);
        hh = SDFhh.format(TodayDate);
        SimpleDateFormat SDFmt = new SimpleDateFormat(mt_formate);
        mt = SDFmt.format(TodayDate);        

        dispDate = new JLabel(cur_date);
        dispDate.setBounds(1070,70,130,30);
        dispDate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        add(dispDate);
        
        dispTime = new JLabel(cur_time);
        dispTime.setBounds(1070,110,130,30);
        dispTime.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        add(dispTime);
        
        addSymptoms = new JPanel();
        addSymptoms.setLayout(null);
        addSymptoms.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Symptoms", TitledBorder.LEADING,TitledBorder.TOP, null, new Color(0,0,0)));
        addSymptoms.setBounds(20,150,600,400);
        add(addSymptoms);
        
        Symptoms = new JLabel("Symptoms : ");
        Symptoms.setBounds(20,20,150,30);
        Symptoms.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        addSymptoms.add(Symptoms);
        
        String s[] = {"Select","Fever","Vomiting","Swelling","Pain","Itching","Rashes","Cough","Sneezing","Headache","Common Cold","Other"};
        symptomsCMB = new JComboBox(s);
        symptomsCMB.setFont(new Font("Times New Roman",Font.PLAIN,20));
        AutoCompleteDecorator.decorate(symptomsCMB);
        symptomsCMB.setBounds(130,20,175,30);
        addSymptoms.add(symptomsCMB);
    
        effectLabel = new JLabel("Effect Level : ");
        effectLabel.setFont(new Font("Times New Roman",Font.PLAIN,18));
        effectLabel.setBounds(330,20,150,30);
        addSymptoms.add(effectLabel);

        String effectlevel[] = {"Select","Moderate","High","Extreme"};
        effectlevelCMB = new JComboBox(effectlevel);
        effectlevelCMB.setFont(new Font("Times New Roman",Font.PLAIN,20));
        effectlevelCMB.setBounds(450,20,120,30);
        addSymptoms.add(effectlevelCMB);
        
        OtherSymptomLabel=new JLabel("Other Symptoms : ");
        OtherSymptomLabel.setFont(new Font("Times New Roman",Font.PLAIN,20));
        OtherSymptomLabel.setBounds(20,70,150,30);
        addSymptoms.add(OtherSymptomLabel);
        
        InsertSymptoms = new JButton("Insert");
        InsertSymptoms.setBounds(25,130,100,30);
        InsertSymptoms.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        addSymptoms.add(InsertSymptoms);
        InsertSymptoms.addActionListener
        (
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        InsertSymptomsactionPerformed(e);
                    }
                }
        );
  
        EditSymptoms = new JButton("Edit");
        EditSymptoms.setBounds(175,130,100,30);
        EditSymptoms.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        addSymptoms.add(EditSymptoms);
        EditSymptoms.addActionListener
        (
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    EditSymptomsactionPerformed(e);
                }
            }
        );
        
        DeleteSymptoms = new JButton("Delete");
        DeleteSymptoms.setBounds(325,130,100,30);
        DeleteSymptoms.addActionListener(this);
        DeleteSymptoms.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        addSymptoms.add(DeleteSymptoms);
        DeleteSymptoms.addActionListener
        (
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        DeleteSymptomsactionPerformed(e);
                    }
                }
        );
        
        UpdateSymptoms = new JButton("Update");
        UpdateSymptoms.setBounds(475,130,100,30);
        UpdateSymptoms.addActionListener(this);
        UpdateSymptoms.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        addSymptoms.add(UpdateSymptoms);
        UpdateSymptoms.addActionListener
        (
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        UpdateSymptomsactionPerformed(e);
                    }
                }
        );
        
        symptomsTable= new JTable()
        {
            public boolean isCellEditable(int rowIndex, int mColIndex) 
            {
                return false;
            }
        };
        Object[] Symptoms_Table_column_name = {"Symptmos","Level","Other"};
        SymptomsTableModel = (DefaultTableModel)symptomsTable.getModel();
        SymptomsTableModel.setColumnIdentifiers(Symptoms_Table_column_name);
        symptomsTable.setModel(SymptomsTableModel);
        symptomsTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        symptomsTable.getColumnModel().getColumn(1).setPreferredWidth(30);
        symptomsTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        
        JScrollPane SymptomsScroll = new JScrollPane(symptomsTable);
        SymptomsScroll.setBounds(5,195,590,200);
        addSymptoms.add(SymptomsScroll);
        
        OtherSymptomText=new JTextField("Null");
        OtherSymptomText.setBounds(180,70,180,30);
        OtherSymptomText.setFont(new Font("Times New Roman",Font.PLAIN,20));
        addSymptoms.add(OtherSymptomText);
    
        addOthers = new JPanel();
        addOthers.setLayout(null);
        addOthers.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Others", TitledBorder.LEADING,TitledBorder.TOP, null, new Color(0,0,0)));
        addOthers.setBounds(20,560,600,200);
        add(addOthers);
        
        PrecautionsLabel = new JLabel("Precautions : ");
        PrecautionsLabel.setFont(new Font("Times New Roman",Font.PLAIN,20));
        PrecautionsLabel.setBounds(20,30,120,30);
        addOthers.add(PrecautionsLabel);
        
        PrecautionsText = new JTextField("Null");
        PrecautionsText.setFont(new Font("Times New Roman",Font.PLAIN,20));
        PrecautionsText.setBounds(150,30,400,30);
        addOthers.add(PrecautionsText);

        AllergyLabel = new JLabel("Allergies : ");
        AllergyLabel.setFont(new Font("Times New Roman",Font.PLAIN,20));
        AllergyLabel.setBounds(20,90,120,30);
        addOthers.add(AllergyLabel);
        
        AllergyText = new JTextField("Null");
        AllergyText.setFont(new Font("Times New Roman",Font.PLAIN,20));
        AllergyText.setBounds(150,90,400,30);
        addOthers.add(AllergyText);
        
        DiseaseLabel = new JLabel("Disease : ");
        DiseaseLabel.setFont(new Font("Times New Roman",Font.PLAIN,20));
        DiseaseLabel.setBounds(20,150,400,30);
        addOthers.add(DiseaseLabel);
        
        DiseaseTF = new JTextField("Null");
        DiseaseTF.setFont(new Font("Times New Roman",Font.PLAIN,20));
        DiseaseTF.setBounds(150,150,400,30);
        addOthers.add(DiseaseTF);
        
        addMedicine = new JPanel();
        addMedicine.setLayout(null);
        addMedicine.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Medicine", TitledBorder.LEADING,TitledBorder.TOP, null, new Color(0,0,0)));
        addMedicine.setBounds(650,150,600,610);
        add(addMedicine);

        medicineType = new JLabel("Medicine Type : ");
        medicineType.setBounds(20,20,150,30);
        medicineType.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        addMedicine.add(medicineType);
        
        MedicineTypeCMB = new JComboBox();
        MedicineTypeCMB.addItem("Select Type");
        MedicineTypeCMB.addItem("Tablet");
        MedicineTypeCMB.addItem("Syrup");
        MedicineTypeCMB.addItem("Ointment");
        MedicineTypeCMB.addItem("Capsule");
        MedicineTypeCMB.addItem("Spray");
        MedicineTypeCMB.addItem("Gargle");
        MedicineTypeCMB.setFont(new Font("Times New Roman",Font.PLAIN,20));
        MedicineTypeCMB.setBounds(170, 20, 250, 30);
        addMedicine.add(MedicineTypeCMB);
        MedicineTypeCMB.addActionListener
        (
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        MedicineTypeCMBactionPerformed(e);
                    }
                }
        );
        
        MedicineNameLabel = new JLabel("Select Medicine :");
        MedicineNameLabel.setBounds(20,80, 150, 30);
        MedicineNameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        addMedicine.add(MedicineNameLabel);
        
        MedicineNameCMB = new JComboBox();
        MedicineNameCMB.setBounds(170,80, 250, 30);
        MedicineNameCMB.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        AutoCompleteDecorator.decorate(MedicineNameCMB);
        addMedicine.add(MedicineNameCMB);
        
        dosage = new JLabel("Dosage : ");
        dosage.setBounds(20,140,150,30);
        dosage.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        addMedicine.add(dosage);
        
        enterDosage = new JTextField();
        enterDosage.setBounds(170,140,200,30);
        enterDosage.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        addMedicine.add(enterDosage);
        
        DosageParameter = new JLabel();
        DosageParameter.setFont(new Font("Times New Roman" , Font.PLAIN, 17));
        DosageParameter.setBounds(400, 140, 100, 30);
        addMedicine.add(DosageParameter);
        
        MorningCB = new JCheckBox(" Morning");
        MorningCB.setBounds(50, 200, 100, 30);
        MorningCB.setFont(new Font("Times New Roman", Font.PLAIN , 19));
        addMedicine.add(MorningCB);
   
        AtnoonCB = new JCheckBox(" After Noon");   
        AtnoonCB.setBounds(170, 200, 140, 30);
        AtnoonCB.setFont(new Font("Times New Roman", Font.PLAIN , 19));
        addMedicine.add(AtnoonCB);

        EveningCB = new JCheckBox(" Evening");
        EveningCB.setBounds(310, 200, 100, 30);
        EveningCB.setFont(new Font("Times New Roman", Font.PLAIN , 19));
        addMedicine.add(EveningCB);
        
        RadioGroup = new ButtonGroup();
        
        BeforeMealRB = new JRadioButton(" Before Meal");
        BeforeMealRB.setBounds(80, 250, 150, 30);
        BeforeMealRB.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        addMedicine.add(BeforeMealRB);
               
        AfterMealRB = new JRadioButton(" After Meal");
        AfterMealRB.setBounds(230, 250, 150, 30);
        AfterMealRB.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        addMedicine.add(AfterMealRB);
        
        RadioGroup.add(AfterMealRB);
        RadioGroup.add(BeforeMealRB);
        
        QuantityLabel = new JLabel("Quantity :");
        QuantityLabel.setBounds(20, 300, 100, 30);
        QuantityLabel.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        addMedicine.add(QuantityLabel);
        
        Quantity = new JTextField();
        Quantity.setBounds(120, 300,  100, 30);
        Quantity.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        addMedicine.add(Quantity);
        
        QuantityType = new JLabel();
        QuantityType.setBounds(230, 300, 130, 30);
        QuantityType.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        addMedicine.add(QuantityType);
        
        InsertMed = new JButton("Insert");
        InsertMed.setBounds(25,350,100,30);
        InsertMed.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        addMedicine.add(InsertMed);
        InsertMed.addActionListener
        (
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        InsertMedactionPerformed(e);
                    }
                }
        );
        
        EditMed = new JButton("Edit");
        EditMed.setBounds(175,350,100,30);
        EditMed.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        addMedicine.add(EditMed);
        EditMed.addActionListener
        (
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        EditMedactionPerformed(e);
                    }
                }                
        );
        
        DeleteMed = new JButton("Delete");
        DeleteMed.setBounds(325,350,100,30);
        DeleteMed.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        addMedicine.add(DeleteMed);
        DeleteMed.addActionListener
        (
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        DeleteMedactionPerformed(e);
                    }
                }
        );
        
        UpdateMed = new JButton("Update");
        UpdateMed.setBounds(475,350,100,30);
        UpdateMed.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        addMedicine.add(UpdateMed);
        UpdateMed.addActionListener
        (
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        UpdateMedactionPerformed(e);
                    }
                }
        );  
        
        
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
        medicineTable.getColumnModel().getColumn(4).setPreferredWidth(30);
        medicineTable.getColumnModel().getColumn(5).setPreferredWidth(30);
        medicineTable.getColumnModel().getColumn(7).setPreferredWidth(40);
              
        JScrollPane sp = new JScrollPane(medicineTable);
        sp.setBounds(5,405,590,200);
        addMedicine.add(sp);
        
        Add = new JButton("Add");
        Add.setBounds(470,780,100,40);
        Add.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        add(Add);
        Add.addActionListener
        (
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        AddactionPerformed(e);
                    }
                }
        );
        
        Reset = new JButton("Reset");
        Reset.setBounds(600,780,100,40);
        Reset.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        add(Reset);
        Reset.addActionListener
        (
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    ResetactionPerformed(e);
                }
            }
        );
        
        Back = new JButton("Back");
        Back.setBounds(730,780,100,40);
        Back.addActionListener(this);
        Back.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        add(Back);
        
        setSize(1300,900);
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setResizable(false);
        setTitle("Add Report");
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void MedicineTypeCMBactionPerformed(ActionEvent e)
    {
        connection c = new connection();
        c.createConnection();
        String stmnt = null;
        if(MedicineTypeCMB.getSelectedItem().equals("Tablet"))
        {
            stmnt = "select medicinename from clinicms.medicineinfo where medicinetype = 'Tablet' order by medicinename ASC";
            DosageParameter.setText("Pills");
            QuantityType.setText("No Of Pills");
        }
        else if(MedicineTypeCMB.getSelectedItem().equals("Syrup"))
        {
            stmnt = "select medicinename from clinicms.medicineinfo where medicinetype = 'Syrup' order by medicinename ASC";
            DosageParameter.setText("ml");
            QuantityType.setText("No Of Bottle");
        }
        else if(MedicineTypeCMB.getSelectedItem().equals("Ointment"))
        {
            stmnt = "select medicinename from clinicms.medicineinfo where medicinetype = 'Ointment' order by medicinename ASC";
            DosageParameter.setText("Times");
            QuantityType.setText("No Of Tubes");
        }
        else if(MedicineTypeCMB.getSelectedItem().equals("Capsule")){
            
            stmnt = "select medicinename from clinicms.medicineinfo where medicinetype = 'Capsule' order by medicinename ASC";
            DosageParameter.setText("Capsule");
            QuantityType.setText("No Of Capsule");
        }
        else if(MedicineTypeCMB.getSelectedItem().equals("Spray")){
            
            stmnt = "select medicinename from clinicms.medicineinfo where medicinetype = 'Spray' order by medicinename ASC";
            DosageParameter.setText("Times");
            QuantityType.setText("No Of Bottle");
        }
        else if(MedicineTypeCMB.getSelectedItem().equals("Gargle")){
            stmnt = "select medicinename from clinicms.medicineinfo where medicinetype = 'Gargle' order by medicinename ASC";
            DosageParameter.setText("Times");
            QuantityType.setText("No Of Bottle");
        }
        else
        {
            stmnt = "";
            MedicineNameCMB.removeAllItems();
            DosageParameter.setText("");
            QuantityType.setText("");
            RadioGroup.clearSelection();
            MorningCB.setSelected(false);
            AtnoonCB.setSelected(false);
            EveningCB.setSelected(false);
        }
        try
        {
            ResultSet list = c.s.executeQuery(stmnt);
            MedicineNameCMB.removeAllItems();
                    
            while(list.next())
            {
                String ListItem = list.getString("medicinename");
                MedicineNameCMB.addItem(ListItem);                    
            }
        }
        catch(Exception E){
            
        }
    }
    
    public void AddactionPerformed(ActionEvent e)
    {
        String nameCheck=dispname.getText();
        if(nameCheck.equals("")){
            JOptionPane.showMessageDialog(null,"You must have to Search correct Token first.");
        }
        else{
            connection c = new connection();
            c.createConnection();
        
            try
            {
                String allData = "select * from clinicms.patientdetails where mobile = '" + MobileNumber + "'";
                ResultSet rs = c.s.executeQuery(allData);
                rs.next();
            
                String Gender = rs.getString("gender");
                String Age = rs.getString("age");

                rs = null;

                String PathName = "F:\\SGP-1\\Database\\" + MobileNumber + "\\Report\\" +dd+"_"+mm+"_"+yyyy+"_"+hh+"_"+mt+".pdf";
                Document doc = new Document();
                PdfWriter.getInstance(doc, new FileOutputStream(PathName));
                doc.setMargins(20f,20f,5f,5f);
                doc.open();

                com.itextpdf.text.Font HeadingFont =  FontFactory.getFont(FontFactory.TIMES,32f,Font.BOLD, BaseColor.RED);
                Paragraph Heading = new Paragraph("Clinical Management System",HeadingFont);
                Heading.setAlignment(Element.ALIGN_CENTER);
                doc.add(Heading);

                doc.add(new Paragraph(" "));
                com.itextpdf.text.Rectangle ps = doc.getPageSize();
                Paragraph HorizontalLine = new Paragraph("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                doc.add(HorizontalLine);


                PdfPTable info = new PdfPTable(2);
                info.setWidthPercentage(100);
                float []columnwidth = {400f,195f};
                info.setWidthPercentage(columnwidth, ps);
                PdfPCell PetientNameCell = new PdfPCell();
                PetientNameCell.setPhrase(new Phrase("Name    : " + dispname.getText()));
                PetientNameCell.setBorder(0);
                info.addCell(PetientNameCell);

                PdfPCell DateCell = new PdfPCell();
                DateCell.setPhrase(new Phrase("Date : " + " "+dd+"/"+mm+"/"+yyyy));
                DateCell.setBorder(0);
                info.addCell(DateCell);

                PdfPCell GenderCell = new PdfPCell();
                GenderCell.setPhrase(new Phrase("Gender : " + Gender));
                GenderCell.setBorder(0);
                info.addCell(GenderCell);

                PdfPCell AgeCell = new PdfPCell();
                AgeCell.setPhrase(new Phrase("Age : " +Age));
                AgeCell.setBorder(0);
                info.addCell(AgeCell);

                doc.add(info);
                doc.add(HorizontalLine);

                doc.add(new Paragraph(" "));

                com.itextpdf.text.Font TableTitleFont =  FontFactory.getFont(FontFactory.TIMES,16f,Font.BOLD, BaseColor.DARK_GRAY);
                Paragraph SymptomTableTitle = new Paragraph(": Symptoms :",TableTitleFont);
                SymptomTableTitle.setAlignment(Element.ALIGN_CENTER);
                doc.add(SymptomTableTitle);

                doc.add(new Paragraph(" "));

                PdfPTable SymptomsTable = new PdfPTable(3);
                SymptomsTable.setWidthPercentage(100);

                PdfPCell SymptomNameTitle = new PdfPCell();
                SymptomNameTitle.setPhrase(new Phrase("Symptom"));
                SymptomNameTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
                SymptomsTable.addCell(SymptomNameTitle);

                PdfPCell EffectLevelTitle = new PdfPCell();
                EffectLevelTitle.setPhrase(new Phrase("EffectLevel"));
                EffectLevelTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
                SymptomsTable.addCell(EffectLevelTitle);

                PdfPCell OtherNotesTitle = new PdfPCell();
                OtherNotesTitle.setPhrase(new Phrase("Other"));
                OtherNotesTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
                SymptomsTable.addCell(OtherNotesTitle);

                PdfPCell SymptomNametemp = new PdfPCell();
                SymptomNametemp.setPhrase(new Phrase(" "));
                SymptomNametemp.setHorizontalAlignment(Element.ALIGN_CENTER);
                SymptomNametemp.setBorder(0);
                SymptomsTable.addCell(SymptomNametemp);

                PdfPCell EffectLeveltemp = new PdfPCell();
                EffectLeveltemp.setPhrase(new Phrase(" "));
                EffectLeveltemp.setHorizontalAlignment(Element.ALIGN_CENTER);
                EffectLeveltemp.setBorder(0);
                SymptomsTable.addCell(EffectLeveltemp);

                PdfPCell OtherNotestemp = new PdfPCell();
                OtherNotestemp.setPhrase(new Phrase(" "));
                OtherNotestemp.setHorizontalAlignment(Element.ALIGN_CENTER);
                OtherNotestemp.setBorder(0);
                SymptomsTable.addCell(OtherNotestemp);

                int symptomsRowCount = symptomsTable.getRowCount();
                for(int i=0;i<symptomsRowCount ; i++)
                {
                    PdfPCell SymptomName = new PdfPCell();
                    SymptomName.setPhrase(new Phrase((String) SymptomsTableModel.getValueAt(i, 0)));
                    SymptomName.setHorizontalAlignment(Element.ALIGN_CENTER);
                    SymptomsTable.addCell(SymptomName);

                    PdfPCell EffectLevel = new PdfPCell();
                    EffectLevel.setPhrase(new Phrase((String)SymptomsTableModel.getValueAt(i, 1)));
                    EffectLevel.setHorizontalAlignment(Element.ALIGN_CENTER);
                    SymptomsTable.addCell(EffectLevel);

                    PdfPCell OtherNotes = new PdfPCell();
                    OtherNotes.setPhrase(new Phrase((String)SymptomsTableModel.getValueAt(i, 2)));
                    OtherNotes.setHorizontalAlignment(Element.ALIGN_CENTER);
                    SymptomsTable.addCell(OtherNotes);
                }
                doc.add(SymptomsTable);

                doc.add(new Paragraph(" "));

                Paragraph MedicineTableTitle = new Paragraph(": Medicines :",TableTitleFont);
                MedicineTableTitle.setAlignment(Element.ALIGN_CENTER);
                doc.add(MedicineTableTitle);

                doc.add(new Paragraph(" "));

                PdfPTable MedicinesTable = new PdfPTable(8);
                MedicinesTable.setWidthPercentage(100);
                float []medicinecolumnwidth = {200f,75f,50f,50f,50f,50f,50f,50f};
                MedicinesTable.setWidthPercentage(medicinecolumnwidth, ps);

                PdfPCell MedicineNameTitle = new PdfPCell();
                MedicineNameTitle.setPhrase(new Phrase("Medicine Name"));
                MedicineNameTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
                MedicinesTable.addCell(MedicineNameTitle);

                PdfPCell MedicineTypeTitle = new PdfPCell();
                MedicineTypeTitle.setPhrase(new Phrase("Medicine Type"));
                MedicineTypeTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
                MedicinesTable.addCell(MedicineTypeTitle);

                PdfPCell DosageTitle = new PdfPCell();
                DosageTitle.setPhrase(new Phrase("Dosage"));
                DosageTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
                MedicinesTable.addCell(DosageTitle);

                PdfPCell MorningTitle = new PdfPCell();
                MorningTitle.setPhrase(new Phrase("Morning"));
                MorningTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
                MedicinesTable.addCell(MorningTitle);

                PdfPCell NoonTitle = new PdfPCell();
                NoonTitle.setPhrase(new Phrase("Noon"));
                NoonTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
                MedicinesTable.addCell(NoonTitle);

                PdfPCell EveningTitle = new PdfPCell();
                EveningTitle.setPhrase(new Phrase("Evening"));
                EveningTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
                MedicinesTable.addCell(EveningTitle);

                PdfPCell B_A_MealTitle = new PdfPCell();
                B_A_MealTitle.setPhrase(new Phrase("B/A Meal"));
                B_A_MealTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
                MedicinesTable.addCell(B_A_MealTitle);

                PdfPCell QuantityTitle = new PdfPCell();
                QuantityTitle.setPhrase(new Phrase("Quantity"));
                QuantityTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
                MedicinesTable.addCell(QuantityTitle);

                PdfPCell MedicineNametemp = new PdfPCell();
                MedicineNametemp.setPhrase(new Phrase(" "));
                MedicineNametemp.setHorizontalAlignment(Element.ALIGN_CENTER);
                MedicineNametemp.setBorder(0);
                MedicinesTable.addCell(MedicineNametemp);

                PdfPCell MedicineTypeTemp = new PdfPCell();
                MedicineTypeTemp.setPhrase(new Phrase(""));
                MedicineTypeTemp.setHorizontalAlignment(Element.ALIGN_CENTER);
                MedicineTypeTemp.setBorder(0);
                MedicinesTable.addCell(MedicineTypeTemp);

                PdfPCell Dosagetemp = new PdfPCell();
                Dosagetemp.setPhrase(new Phrase(" "));
                Dosagetemp.setHorizontalAlignment(Element.ALIGN_CENTER);
                Dosagetemp.setBorder(0);
                MedicinesTable.addCell(Dosagetemp);

                PdfPCell Morningtemp  = new PdfPCell();
                Morningtemp.setPhrase(new Phrase(" "));
                Morningtemp.setHorizontalAlignment(Element.ALIGN_CENTER);
                Morningtemp.setBorder(0);
                MedicinesTable.addCell(Morningtemp);

                PdfPCell Noontemp = new PdfPCell();
                Noontemp.setPhrase(new Phrase(" "));
                Noontemp.setHorizontalAlignment(Element.ALIGN_CENTER);
                Noontemp.setBorder(0);
                MedicinesTable.addCell(Noontemp);

                PdfPCell Eveningtemp = new PdfPCell();
                Eveningtemp.setPhrase(new Phrase(" "));
                Eveningtemp.setHorizontalAlignment(Element.ALIGN_CENTER);
                Eveningtemp.setBorder(0);
                MedicinesTable.addCell(Eveningtemp);

                PdfPCell B_A_Mealtemp = new PdfPCell();
                B_A_Mealtemp.setPhrase(new Phrase(" "));
                B_A_Mealtemp.setHorizontalAlignment(Element.ALIGN_CENTER);
                B_A_Mealtemp.setBorder(0);
                MedicinesTable.addCell(B_A_Mealtemp);

                PdfPCell Quantitytemp = new PdfPCell();
                Quantitytemp.setPhrase(new Phrase(" "));
                Quantitytemp.setHorizontalAlignment(Element.ALIGN_CENTER);
                Quantitytemp.setBorder(0);
                MedicinesTable.addCell(Quantitytemp);

                int medicinesRowCount = medicineTable.getRowCount();
                for(int i=0;i<medicinesRowCount ; i++)
                {
                    PdfPCell MedicineName = new PdfPCell();
                    MedicineName.setPhrase(new Phrase((String)MedicineTableModel.getValueAt(i, 0)));
                    MedicineName.setHorizontalAlignment(Element.ALIGN_CENTER);
                    MedicinesTable.addCell(MedicineName);

                    PdfPCell medicineType = new PdfPCell();
                    medicineType.setPhrase(new Phrase((String)MedicineTableModel.getValueAt(i, 1)));
                    medicineType.setHorizontalAlignment(Element.ALIGN_CENTER);
                    MedicinesTable.addCell(medicineType);

                    PdfPCell Dosage = new PdfPCell();
                    Dosage.setPhrase(new Phrase((String)MedicineTableModel.getValueAt(i, 2)));
                    Dosage.setHorizontalAlignment(Element.ALIGN_CENTER);
                    MedicinesTable.addCell(Dosage);

                    PdfPCell Morning = new PdfPCell();
                    Morning.setPhrase(new Phrase((String)MedicineTableModel.getValueAt(i, 3)));
                    Morning.setHorizontalAlignment(Element.ALIGN_CENTER);
                    MedicinesTable.addCell(Morning);

                    PdfPCell Noon = new PdfPCell();
                    Noon.setPhrase(new Phrase((String)MedicineTableModel.getValueAt(i, 4)));
                    Noon.setHorizontalAlignment(Element.ALIGN_CENTER);
                    MedicinesTable.addCell(Noon);

                    PdfPCell Evening = new PdfPCell();
                    Evening.setPhrase(new Phrase((String)MedicineTableModel.getValueAt(i, 5)));
                    Evening.setHorizontalAlignment(Element.ALIGN_CENTER);
                    MedicinesTable.addCell(Evening);

                    PdfPCell B_A_Meal = new PdfPCell();
                    B_A_Meal.setPhrase(new Phrase((String)MedicineTableModel.getValueAt(i, 6)));
                    B_A_Meal.setHorizontalAlignment(Element.ALIGN_CENTER);
                    MedicinesTable.addCell(B_A_Meal);

                    PdfPCell Quantity= new PdfPCell();
                    Quantity.setPhrase(new Phrase((String)MedicineTableModel.getValueAt(i, 7)));
                    Quantity.setHorizontalAlignment(Element.ALIGN_CENTER);
                    MedicinesTable.addCell(Quantity);
                }

                doc.add(MedicinesTable);

                doc.add(new Paragraph(" "));

                com.itextpdf.text.Font OtherThingsFont =  FontFactory.getFont(FontFactory.TIMES,16f,Font.BOLD, BaseColor.DARK_GRAY);
                Paragraph PrecautionsTitle = new Paragraph("Precautions : ", OtherThingsFont);
                Paragraph Precautions = new Paragraph(PrecautionsText.getText());
                Precautions.setAlignment(Element.ALIGN_JUSTIFIED);

                doc.add(PrecautionsTitle);
                doc.add(Precautions);

                doc.add(new Paragraph(" "));

                Paragraph AllergyTitle = new Paragraph("Allergy : ", OtherThingsFont);
                Paragraph Allergy = new Paragraph(AllergyText.getText());
                Allergy.setAlignment(Element.ALIGN_JUSTIFIED);

                doc.add(AllergyTitle);
                doc.add(Allergy);

                doc.add(new Paragraph(" "));

                Paragraph DiseaseTitle = new Paragraph("Disease : ", OtherThingsFont);
                Paragraph Disease = new Paragraph(DiseaseTF.getText());
                Disease.setAlignment(Element.ALIGN_JUSTIFIED);

                doc.add(DiseaseTitle);
                doc.add(Disease); 

                doc.close();

            }
            catch(Exception E){
                E.printStackTrace();
            }

            try
            {

                Precautions = PrecautionsText.getText();
                Allergy = AllergyText.getText();
                Disease = DiseaseTF.getText();

                String consulting = null;
                    
                if(ConsultingFees.isSelected())
                {
                    consulting = "1";
                }
                else
                {
                    consulting = "0";
                }

                String AddTime ="insert into patient" + MobileNumber + ".time_record (dd,mm,yyyy,hh,mt,Precautions,Allergies,Disease,Consulting) value ('" +dd+ "','" +mm+ "','" +yyyy+ "','" +hh+ "','" +mt+ "','" +Precautions+ "','" +Allergy+ "','" +Disease+ "','"+consulting+"')";
                c.s.executeUpdate(AddTime);
           

                String Create_Symptoms_Table = "create table patient" + MobileNumber + ".S_" +dd+"_"+mm+"_"+yyyy+"_"+hh+"_"+mt+" (symptomname varchar(20), effectlevel varchar(15), othernotes varchar(30))";
                String Create_Medicines_Table = "create table patient" + MobileNumber + ".M_" +dd+"_"+mm+"_"+yyyy+"_"+hh+"_"+mt+" (medicinename varchar(30), medicinetype varchar(15), dosage varchar(10), inmorning enum('Yes','No'), innoon enum('Yes','No'), inevening enum('Yes','No'), b_a_meal enum('Before Meal','After meal'), quantity varchar(10))";
                c.s.executeUpdate(Create_Symptoms_Table);
                c.s.executeUpdate(Create_Medicines_Table);

                int medicineRowCount = medicineTable.getRowCount();
                for(int i=0 ; i<medicineRowCount ; i++)
                {
                    String value = "insert into patient"+MobileNumber+".M_"+dd+"_"+mm+"_"+yyyy+"_"+hh+"_"+mt+" (medicinename , medicinetype , dosage , inmorning, innoon, inevening, b_a_meal, quantity) value('"+(String)MedicineTableModel.getValueAt(i, 0)+"','"+(String)MedicineTableModel.getValueAt(i, 1)+"','"+(String)MedicineTableModel.getValueAt(i, 2)+"','"+(String)MedicineTableModel.getValueAt(i, 3)+"','"+(String)MedicineTableModel.getValueAt(i, 4)+"','"+(String)MedicineTableModel.getValueAt(i, 5)+"','"+(String)MedicineTableModel.getValueAt(i, 6)+"','"+(String)MedicineTableModel.getValueAt(i, 7)+"')";
                    c.s.executeUpdate(value);
                }

                int symptomsRowCount = symptomsTable.getRowCount();
                for(int i=0;i<symptomsRowCount ; i++)
                {
                    String value = "insert into patient"+MobileNumber+".S_" +dd+"_"+mm+"_"+yyyy+"_"+hh+"_"+mt+" (symptomname, effectlevel, othernotes) value('"+SymptomsTableModel.getValueAt(i, 0)+"','"+SymptomsTableModel.getValueAt(i, 1)+"','"+SymptomsTableModel.getValueAt(i, 2)+"')";
                    c.s.executeUpdate(value);
                }  
                JOptionPane.showMessageDialog(null,"Report Added Successfully.");
                MedicineTableModel.setRowCount(0);
                SymptomsTableModel.setRowCount(0);        
                PrecautionsText.setText("Null");
                AllergyText.setText("Null");
                DiseaseTF.setText("Null");
            }
            catch(Exception E)
            {
                E.printStackTrace();
            }
        }
        
        dispname.setText("");
        entertoken.setText("");
    }
    
    public void InsertMedactionPerformed(ActionEvent e)
    {
        if(MedicineTypeCMB.getSelectedIndex() == 0 || enterDosage.getText().isEmpty() || (!MorningCB.isSelected() && !AtnoonCB.isSelected() && !EveningCB.isSelected() ) || (!AfterMealRB.isSelected() && !BeforeMealRB.isSelected()) ||  Quantity.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please Fill All The Data");
        }
        else
        {
            String name = (String)MedicineNameCMB.getSelectedItem();
            String MedicineType = (String)MedicineTypeCMB.getSelectedItem();
            String Dosage = enterDosage.getText();
            String InMorning="No",InNoon = "No",InEvening = "No";
            if(MorningCB.isSelected())
            {
                InMorning = "Yes";
            }
            if(AtnoonCB.isSelected())
            {
                InNoon = "Yes";
            }
            if(EveningCB.isSelected())
            {
                InEvening = "Yes";
            }
            String B_A_Meal;
            if(BeforeMealRB.isSelected())
            {
                B_A_Meal = "Before Meal";
            }
            else
            {
                B_A_Meal = "After Meal";
            }
            String MedicineQuantity = Quantity.getText();
        
            Object[] Data = new Object[]{name,MedicineType,Dosage,InMorning,InNoon,InEvening,B_A_Meal,MedicineQuantity};
            MedicineTableModel.addRow(Data);
            resetMedicineFileds();    
        }
    }
    
    public void resetMedicineFileds()
    {
            MedicineTypeCMB.setSelectedIndex(0);
            DosageParameter.setText("");
            QuantityType.setText("");
            RadioGroup.clearSelection();
            MorningCB.setSelected(false);
            AtnoonCB.setSelected(false);
            EveningCB.setSelected(false);
            Quantity.setText("");
            enterDosage.setText("");
    }
    
    public void DeleteMedactionPerformed(ActionEvent e)
    {
        if(medicineTable.getSelectedRowCount() == 1)
        {
            MedicineTableModel.removeRow(medicineTable.getSelectedRow());
        }
        else if(medicineTable.getSelectedRowCount() > 1)
        {
            JOptionPane.showMessageDialog(null, "Please select only one row");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Plese select row to delete");
        }
            
    }
    
    public void InsertSymptomsactionPerformed(ActionEvent e)
    {
        if(effectlevelCMB.getSelectedIndex() == 0 || symptomsCMB.getSelectedIndex() == 0 || OtherSymptomText.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please Fill All The Data");
        }
        else
        {
            String Symptom = (String)symptomsCMB.getSelectedItem();
            String EffectLevel = (String)effectlevelCMB.getSelectedItem();
            String OtherNotes = OtherSymptomText.getText();
        
            Object[] Data = new Object[]{Symptom,EffectLevel,OtherNotes};
            SymptomsTableModel.addRow(Data);
            resetsymptomsFields();
        }
    }
        
    public void resetsymptomsFields()
    {
        effectlevelCMB.setSelectedIndex(0);
        symptomsCMB.setSelectedIndex(0);
        OtherSymptomText.setText("Null");
    }
    
    public void DeleteSymptomsactionPerformed(ActionEvent e)
    {
        if(symptomsTable.getSelectedRowCount() == 1)
        {
            SymptomsTableModel.removeRow(symptomsTable.getSelectedRow());
            resetsymptomsFields();
        }
        else if(symptomsTable.getSelectedRowCount() > 1)
        {
            JOptionPane.showMessageDialog(null, "Please select only one one row");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Plese select row to delete");
        }
    }
    
    public void EditSymptomsactionPerformed(ActionEvent e)
    {
        if(symptomsTable.getSelectedRowCount() == 1)
        {
            int rowno = symptomsTable.getSelectedRow();
            symptomsCMB.setSelectedItem((String)SymptomsTableModel.getValueAt(rowno, 0));
            effectlevelCMB.setSelectedItem((String)SymptomsTableModel.getValueAt(rowno, 1));
            OtherSymptomText.setText((String)SymptomsTableModel.getValueAt(rowno, 2));
            
        }
        else if(symptomsTable.getSelectedRowCount() > 1)
        {
            JOptionPane.showMessageDialog(null, "Please Select Only One Row.");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please Select Row To Edit.");
        }
    }
    
    public void UpdateSymptomsactionPerformed(ActionEvent e)
    {
        if(symptomsTable.getSelectedRowCount() == 1)
        {
            if(symptomsCMB.getSelectedIndex() == 0 || effectlevelCMB.getSelectedIndex() == 0 || OtherSymptomText.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Please Fill All The Field");
            }
            else
            {
                int rowno = symptomsTable.getSelectedRow();
                SymptomsTableModel.setValueAt((String)symptomsCMB.getSelectedItem(), rowno, 0);
                SymptomsTableModel.setValueAt((String)effectlevelCMB.getSelectedItem(),rowno, 1);
                SymptomsTableModel.setValueAt((String)OtherSymptomText.getText(),rowno, 2);
                resetsymptomsFields();
            }
        }
        else if(symptomsTable.getSelectedRowCount() > 1)
        {
            JOptionPane.showMessageDialog(null, "Please Select Only One Row.");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please Select Row To Edit.");
        }        
    }
    
    public void UpdateMedactionPerformed(ActionEvent e)
    {     
        if(medicineTable.getSelectedRowCount() == 1)
        {
            if(MedicineTypeCMB.getSelectedIndex() == 0 || enterDosage.getText().isEmpty() || (!MorningCB.isSelected() && !AtnoonCB.isSelected() && !EveningCB.isSelected() ) || (!AfterMealRB.isSelected() && !BeforeMealRB.isSelected()) ||  Quantity.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Please Fill All The Data");
            }
            else
            {
                int rowno = medicineTable.getSelectedRow();
                MedicineTableModel.setValueAt((String)MedicineNameCMB.getSelectedItem(), rowno, 0);
                MedicineTableModel.setValueAt((String)MedicineTypeCMB.getSelectedItem(), rowno, 1);
                MedicineTableModel.setValueAt(enterDosage.getText(), rowno, 2);
                String InMorning="No",InNoon = "No",InEvening = "No";
                if(MorningCB.isSelected())
                {
                    InMorning = "Yes";
                }
                if(AtnoonCB.isSelected())
                {
                    InNoon = "Yes";
                }
                if(EveningCB.isSelected())
                {
                    InEvening = "Yes";
                }
                MedicineTableModel.setValueAt(InMorning, rowno, 3);
                MedicineTableModel.setValueAt(InNoon, rowno, 4);
                MedicineTableModel.setValueAt(InEvening, rowno, 5);
                String B_A_Meal;
                if(BeforeMealRB.isSelected())
                {
                    B_A_Meal = "Before Meal";
                }
                else
                {
                    B_A_Meal = "After Meal";
                }
                MedicineTableModel.setValueAt(B_A_Meal, rowno, 6);
                MedicineTableModel.setValueAt(Quantity.getText(), rowno, 7);
                resetMedicineFileds();    
            }
        }
        else if(medicineTable.getSelectedRowCount() > 1)
        {
            JOptionPane.showMessageDialog(null, "Please Select Only One Row.");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please Select Row To Edit.");
        }        
    }
    
    public void EditMedactionPerformed(ActionEvent e)
    {
        if(medicineTable.getSelectedRowCount() == 1)
        {
            int rowno = medicineTable.getSelectedRow();
            MedicineNameCMB.setSelectedItem((String)MedicineTableModel.getValueAt(rowno, 0));
            MedicineTypeCMB.setSelectedItem((String)MedicineTableModel.getValueAt(rowno, 1));
            enterDosage.setText((String)MedicineTableModel.getValueAt(rowno ,2));
            String InMorning = (String)MedicineTableModel.getValueAt(rowno,3);
            String InNoon = (String)MedicineTableModel.getValueAt(rowno,4);
            String InEvening = (String)MedicineTableModel.getValueAt(rowno,5);
            if(InMorning.equals("Yes"))
            {  
                MorningCB.setSelected(true);
            }
            else
            {
                MorningCB.setSelected(false);
            }
            if(InNoon.equals("Yes"))
            {  
                AtnoonCB.setSelected(true);
            }
            else
            {
                AtnoonCB.setSelected(false);
            }
            if(InEvening.equals("Yes"))
            {  
                EveningCB.setSelected(true);
            }
            else
            {
                EveningCB.setSelected(false);
            }
            String B_A_Meal = (String)MedicineTableModel.getValueAt(rowno,6);;
            if(B_A_Meal.equals("Before Meal"))
            {
                BeforeMealRB.setSelected(true);
            }
            else if(B_A_Meal.equals("After Meal"))
            {
                AfterMealRB.setSelected(true);
            }
            Quantity.setText((String)MedicineTableModel.getValueAt(rowno, 7));
            //resetMedicineFileds(); 
        }
        else if(medicineTable.getSelectedRowCount() > 1)
        {
            JOptionPane.showMessageDialog(null, "Please Select Only One Row.");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please Select Row To Edit.");
        } 
    }
    
    public void ResetactionPerformed(ActionEvent e)
    {
        entertoken.setText("");
        dispname.setText("");
        resetsymptomsFields();
        resetMedicineFileds();
        MedicineTableModel.setRowCount(0);
        SymptomsTableModel.setRowCount(0);        
        PrecautionsText.setText("Null");
        AllergyText.setText("Null");
        DiseaseTF.setText("Null");
    }
    
    public void searchTokenactionPerformed(ActionEvent e)
    {
        try
        {
            connection c = new connection();
            c.createConnection();
            tokennumber = entertoken.getText();
            String str = "Select name,mobile from clinicms.appointment where tokenid = '"+tokennumber+"'";
            ResultSet rs = c.s.executeQuery(str);
            if(rs.next())
            {
                dispname.setText(rs.getString("name"));
                MobileNumber = (String)rs.getString("mobile");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Please Enter Valid Token Number");
            }
            Name=dispname.getText();
        }
        catch(Exception E)
        {
            E.printStackTrace();
        }
    }
    
    public void PastEntriesactionPerformed(ActionEvent e)
    {
        if(Name!=null){
            pastEntry = new JFrame();
        
            JLabel TokenLabel = new JLabel("Token id : ");
            TokenLabel.setBounds(20,20,150,50);
            TokenLabel.setFont(new Font("Times New Roman",Font.PLAIN,20));
            pastEntry.add(TokenLabel);

            JLabel dispTokenLabel = new JLabel(tokennumber);
            dispTokenLabel.setBounds(120,30,70,30);
            dispTokenLabel.setFont(new Font("Times New Roman",Font.PLAIN,20));
            pastEntry.add(dispTokenLabel);

            JLabel NameLabel = new JLabel("Name : ");
            NameLabel.setBounds(20,60,150,50);
            NameLabel.setFont(new Font("Times New Roman",Font.PLAIN,20));
            pastEntry.add(NameLabel);

            JLabel dispNameLabel = new JLabel(Name);
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
            String Token = dispTokenLabel.getText();
            String PatientName = dispNameLabel.getText();
            try
            {
                connection c = new connection();
                c.createConnection();

                String getMobileNumber = "select * from clinicms.appointment where tokenid = '"+Token+"'";
                ResultSet rs = c.s.executeQuery(getMobileNumber);
                rs.next();

                MobileNumber = rs.getString("mobile");
                PatientName = rs.getString("name");
                rs = null;

                dispNameLabel.setText(PatientName);
                
                String getTimeLine = "select * from patient"+MobileNumber+".time_record";
                rs = c.s.executeQuery(getTimeLine);

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
            }catch(Exception E){
                E.printStackTrace();
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
    
    public void BackPEactionPerformed(ActionEvent e)
    {
        pastEntry.setVisible(false);
    }
    
    public void LoadDataactionPerformed(ActionEvent e)
    {
        int medicines_row_count = medicineTable.getRowCount();
        for(int i = medicines_row_count - 1; i>=0 ; i--)
        {
            MedicineTableModel.removeRow(i);
        }
        
        int symptoms_row_count = symptomsTable.getRowCount();
        for(int i = symptoms_row_count - 1; i>=0 ; i--)
        {
            SymptomsTableModel.removeRow(i);
        }
        
        if(PastEntriesTB.getSelectedRowCount() == 1)
        {
            int row_no = PastEntriesTB.getSelectedRow();
            
            String dd,mm, yyyy ,hh ,mt;
           
            String DateToOpen = (String)PastEntryTableModel.getValueAt(row_no, 0);
            dd = DateToOpen.substring(0,2);
            mm = DateToOpen.substring(3,5);
            yyyy = DateToOpen.substring(6,10);
            hh = DateToOpen.substring(13,15);
            mt = DateToOpen.substring(16,18);

            try
            {
                connection c = new connection();
                c.createConnection();
                
                String stmnt = "select * from patient"+MobileNumber+".s_"+dd+"_"+mm+"_"+yyyy+"_"+hh+"_"+mt;
                ResultSet rs = c.s.executeQuery(stmnt);
                
                while(rs.next())
                {
                    String symptom_name = rs.getString("symptomname");
                    String effect_level = rs.getString("effectlevel");
                    String other_things = rs.getString("othernotes");
                    
                    Object[] value = new Object[]{symptom_name,effect_level,other_things};
                    SymptomsTableModel.addRow(value);
                } 
                
                stmnt = "select * from patient"+MobileNumber+".m_"+dd+"_"+mm+"_"+yyyy+"_"+hh+"_"+mt;
                rs = c.s.executeQuery(stmnt);
                
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
                
                stmnt = "select Precautions,Allergies,Disease from patient"+MobileNumber+".time_record where dd="+dd+" and mm="+mm+" and yyyy="+yyyy+" and hh="+hh+" and mt="+mt;
                rs = c.s.executeQuery(stmnt);
                
                if(rs.next())
                {
                    PrecautionsText.setText(rs.getString("Precautions"));
                    AllergyText.setText(rs.getString("Allergies"));
                    DiseaseTF.setText(rs.getString("Disease"));
                }
            }
            catch(Exception E)
            {
                JOptionPane.showMessageDialog(null, "Hekkkp");
                E.printStackTrace();
            }
        }
        
        pastEntry.setVisible(false);
    }
    
    public static void main(String args[])
    {
        new AddReport();
    }
}
