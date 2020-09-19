package clinical.management.system;

import javax.swing.*;
import java.awt.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;
import java.io.File;

public class AddPatient extends JFrame implements ActionListener {
    
    JLabel AddPetientLabel;
    
    JLabel NameLabel;
    JTextField NameTF;
    
    JLabel GenderLabel;
    JRadioButton MaleRB,FemaleRB;
    ButtonGroup GenderButtonGroup;
    
    JLabel BirthDateLabel;
    JDateChooser BirthDateTF;
    
    JLabel AgeLabel;
    JTextField AgeTF;
    
    JLabel BloodGroupLabel;
    JComboBox BloodGroupCMB;
    
    JLabel MobileNumberLabel;
    JTextField MobileNumberTF;
    
    JLabel AddressLabel;
    JTextArea AddressTA;
    
    JButton SubmitBT,ResetBT,BackBT;
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==SubmitBT)
        {
            String name = NameTF.getText();
            String gender = "";
            if(MaleRB.isSelected()){
                gender="M";
            }else if(FemaleRB.isSelected()){
                gender="F";
            }
            String age = AgeTF.getText();
            int AGE = Integer.parseInt(age);
            String birthdate =((JTextField)BirthDateTF.getDateEditor().getUiComponent()).getText();
            String bloodgroup = (String)BloodGroupCMB.getSelectedItem();
            String mobile = MobileNumberTF.getText();
            String address = AddressTA.getText();
            
            if(name.equals("") || gender.equals("") || age.equals("") || birthdate.equals("") || bloodgroup.equals("") || mobile.equals("") || address.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Fill All The Fields...");
            }
            else
            {
                try
                {
                    new File("F:\\SGP-1\\Database\\"+mobile).mkdir();
                    connection c = new connection();
                    c.createConnection();
                    String InsertPetient = "INSERT INTO patientdetails values('"+ mobile +"','"+name+"','"+gender+"','"+AGE+"','"+birthdate+"','"+bloodgroup+"','"+address+"')";
                    c.s.executeUpdate(InsertPetient);
                    String Create_Database = "create database Patient" + mobile;
                    try
                    {
                        c.s.executeUpdate(Create_Database);
                    }
                    catch(Exception e)
                    {
                        System.out.println("Hello");
                    }
                    JOptionPane.showMessageDialog(null,"Patient Details Added Successfully.");
                    NameTF.setText("");
                    GenderButtonGroup.clearSelection();
                    BirthDateTF.setCalendar(null);
                    AgeTF.setText("");
                    BloodGroupCMB.setSelectedIndex(0);
                    MobileNumberTF.setText("");
                    AddressTA.setText("");
                    String CreateTable = "create table Patient" + mobile + ".time_record (dd varchar(3), mm varchar(3), yyyy varchar(5), hh varchar(3), mt varchar(3), Precautions varchar(150), Allergies varchar(150), Disease varchar(50))";
                    c.s.executeUpdate(CreateTable);
                    c.disconnectConnection();
                    c = null;
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
            
        }
        else if(ae.getSource()== ResetBT)
        {
            NameTF.setText("");
            GenderButtonGroup.clearSelection();
            BirthDateTF.setCalendar(null);
            AgeTF.setText("");
            BloodGroupCMB.setSelectedIndex(0);
            MobileNumberTF.setText("");
            AddressTA.setText("");
        }
        else if(ae.getSource() == BackBT)
        {
            new Reception().setVisible(true);
            this.setVisible(false);
        }
    }
    
    AddPatient(){
        
        AddPetientLabel = new JLabel("Add Patient Details");
        AddPetientLabel.setFont(new Font("Times New Roman",Font.PLAIN,30));
        AddPetientLabel.setBounds(350,0,600,70);
        add(AddPetientLabel);
        
        NameLabel=new JLabel("Name");
        NameLabel.setFont(new Font("Times New Roman",Font.PLAIN,26));
        NameLabel.setBounds(40,60,200,50);
        add(NameLabel);
        
        NameTF=new JTextField();
        NameTF.setBounds(250,70,300,30);
        NameTF.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(NameTF);
        
        GenderLabel=new JLabel("Gender");
        GenderLabel.setFont(new Font("Times New Roman",Font.PLAIN,26));
        GenderLabel.setBounds(40,120,90,50);
        add(GenderLabel);
        
        MaleRB = new JRadioButton("Male");
        MaleRB.setBounds(250,120,150,50);
        MaleRB.setFont(new Font("Times New Roman",Font.PLAIN,26));
        add(MaleRB);
        MaleRB.addActionListener(this);
        
        FemaleRB = new JRadioButton("Female");
        FemaleRB.setBounds(400,120,150,50);
        FemaleRB.setFont(new Font("Times New Roman",Font.PLAIN,26));
        FemaleRB.addActionListener(this);
        add(FemaleRB);
        
        GenderButtonGroup = new ButtonGroup();
        GenderButtonGroup.add(MaleRB);
        GenderButtonGroup.add(FemaleRB);

        
        BirthDateLabel = new JLabel("BirthDate");
        BirthDateLabel.setFont(new Font("Times New Roman",Font.PLAIN,26));
        BirthDateLabel.setBounds(40,180,200,50);
        add(BirthDateLabel);
        
        BirthDateTF = new JDateChooser();
        BirthDateTF.setBounds(250,190,300,30);
        BirthDateTF.setDateFormatString("dd/MM/yyyy");
        BirthDateTF.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(BirthDateTF);
        
        AgeLabel = new JLabel("Age");
        AgeLabel.setFont(new Font("Times New Roman",Font.PLAIN,26));
        AgeLabel.setBounds(40,240,90,50);
        add(AgeLabel);
        
        AgeTF = new JTextField();
        AgeTF.setBounds(250,250,300,30);
        AgeTF.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(AgeTF);
        
        BloodGroupLabel = new JLabel("Blood Group");
        BloodGroupLabel.setFont(new Font("Times New Roman",Font.PLAIN,26));
        BloodGroupLabel.setBounds(40,300,200,50);
        add(BloodGroupLabel);
        
        String BloodGroupName[] = {"Select","A+","A-","B+","B-","AB+","AB-","O+","O-"};
        BloodGroupCMB = new JComboBox(BloodGroupName);
        BloodGroupCMB.setFont(new Font("Times New Roman",Font.PLAIN,20));
        BloodGroupCMB.setBounds(250,315,300,30);
        add(BloodGroupCMB);
         
        MobileNumberLabel = new JLabel("Mobile Number");
        MobileNumberLabel.setFont(new Font("Times New Roman",Font.PLAIN,26));
        MobileNumberLabel.setBounds(40,360,200,50);
        add(MobileNumberLabel);
        
        MobileNumberTF = new JTextField();
        MobileNumberTF.setBounds(250,375,300,30);
        MobileNumberTF.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(MobileNumberTF);
        
        AddressLabel = new JLabel("Address");
        AddressLabel.setFont(new Font("Times New Roman",Font.PLAIN,26));
        AddressLabel.setBounds(40,420,200,50);
        add(AddressLabel);
        
        AddressTA = new JTextArea();
        AddressTA.setFont(new Font("Times New Roman",Font.PLAIN,20));
        AddressTA.setBounds(250,430,300,100);
        add(AddressTA);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/addpatient.jpg"));
        Image i2 = i1.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        
        JLabel img = new JLabel(i3);
        img.setBounds(650,0,300,300);
        add(img);
        
        SubmitBT = new JButton("Submit");
        SubmitBT.setBounds(700,340,200,40);
        SubmitBT.addActionListener(this);
        SubmitBT.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(SubmitBT);
        
        ResetBT = new JButton("Reset");
        ResetBT.setBounds(700,415,200,40);
        ResetBT.addActionListener(this);
        ResetBT.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(ResetBT);
        
        BackBT = new JButton("Back");
        BackBT.setBounds(700,490,200,40);
        BackBT.addActionListener(this);
        BackBT.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(BackBT);
       
        setSize(1000,600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        setTitle("Add Patient");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setVisible(true);
    }
    
    public static void main(String args[]){
        new AddPatient();
    }
}