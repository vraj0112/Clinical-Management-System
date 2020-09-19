//package clinical.management.system;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.sql.*;
//import java.time.*;
//
//public class Appointment extends JFrame implements ActionListener {
//    
//    JLabel l0,l1,l2,l3,l4,l5,ma,fe,l6,l7,t3;
//    JButton b1,b2,b3,ok,reset;
//    JTextField t1,t2,t4;
//    JTextArea ta1;
//    JRadioButton male,female;
//    JComboBox combo1;
//    ButtonGroup bg;
//    String number;
//    JFrame token;
//    public void actionPerformed(ActionEvent ae){
//        if(ae.getSource()==b1){
//            try{
//                connection c = new connection();
//                c.createConnection();
//                number = t1.getText();
//                String str = "Select * from patientdetails where mobile = '"+number+"'";
//                ResultSet rs = c.s.executeQuery(str); 
//                
//                if(!rs.next())
//                {
//                    JOptionPane.showMessageDialog(null, "Mobile Number Not Found");
//                }
//                t3.setText((String)rs.getString("name"));
//                String gender = (String)(rs.getString("gender"));
//                if(gender.equals("Male"))
//                {
//                    male.setSelected(true);
//                }
//                else if(gender.equals("Female"))
//                {
//                    female.setSelected(true);
//                }
//                //String temp = (String)rs.getString("age");
//                t4.setText(rs.getString("birthdate"));
//                calculateAge();
//                String bloodgroup = (String)(rs.getString("bloodgroup"));
//                if(bloodgroup.equals("A+"))
//                {
//                    combo1.setSelectedIndex(1);
//                }
//                else if(bloodgroup.equals("A-"))
//                {
//                    combo1.setSelectedIndex(2);
//                }
//                else if(bloodgroup.equals("B+"))
//                {
//                    combo1.setSelectedIndex(3);
//                }
//                else if(bloodgroup.equals("B-"))
//                {
//                    combo1.setSelectedIndex(4);
//                }
//                else if(bloodgroup.equals("AB+"))
//                {
//                    combo1.setSelectedIndex(5);
//                }
//                else if(bloodgroup.equals("AB-"))
//                {
//                    combo1.setSelectedIndex(6);
//                }
//                else if(bloodgroup.equals("O+"))
//                {
//                    combo1.setSelectedIndex(7);
//                }
//                else if(bloodgroup.equals("O-"))
//                {
//                    combo1.setSelectedIndex(8);
//                }
//                
//                //String temp2 = (String)rs.getString("mobile");
//                ta1.setText((String)rs.getString("address"));
//                c.disconnectConnection();
//                c = null;
//            }
//            catch(Exception e)
//            {
//                e.printStackTrace();
//            }
//            
//        }else if(ae.getSource()==b2)
//        {
//            String getnumber=t1.getText();
//            String getname=t3.getText();
//            String bdate=t4.getText();
//            String getage=t2.getText();
//            String getgender = "null";
//            if(male.isSelected()){
//                getgender="M";
//            }else if(female.isSelected()){
//                getgender="F";
//            }
//            String getbloodgroup = (String)combo1.getSelectedItem();
//            try{
//                connection c = new connection();
//                c.createConnection();
//                String str = "INSERT INTO clinicms.appointment (mobile,name,gender,age) values('"+getnumber+"','"+getname+"',"+getgender+",'"+getage+"')";
//                c.s.executeUpdate(str);
//                c.disconnectConnection();
//                c = null;
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//            token = new JFrame();
//            JLabel mobile = new JLabel("Mobile Number : ");
//            mobile.setBounds(20,10,150,50);
//            mobile.setFont(new Font("Times New Roman",Font.PLAIN,20));
//            token.add(mobile);
//            
//            JLabel dispmobile = new JLabel(t1.getText());
//            dispmobile.setBounds(170,10,150,50);
//            dispmobile.setFont(new Font("Times New Roman",Font.PLAIN,20));
//            token.add(dispmobile);
//            
//            JLabel name = new JLabel("Name : ");
//            name.setBounds(20,60,80,50);
//            name.setFont(new Font("Times New Roman",Font.PLAIN,20));
//            token.add(name);
//            
//            JLabel dispname = new JLabel(t3.getText());
//            dispname.setBounds(100,60,150,50);
//            dispname.setFont(new Font("Times New Roman",Font.PLAIN,20));
//            token.add(dispname);
//            
//            JLabel tokennum = new JLabel("Token Number : ");
//            tokennum.setBounds(20,110,150,50);
//            tokennum.setFont(new Font("Times New Roman",Font.PLAIN,20));
//            token.add(tokennum);
//            
//            String idtoken="";
//            try{
//                connection c =new connection();
//                c.createConnection();
//                String str2 = "Select tokenid from appointment where mobile = '"+getnumber+"'";
//                ResultSet rs = c.s.executeQuery(str2);
//                
//                while(rs.next()){
//                    idtoken = rs.getString("tokenid");
//                }
//                c.disconnectConnection();
//                c = null;
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//         
//            JLabel disptokennum = new JLabel(idtoken);
//            disptokennum.setBounds(160,110,80,50);
//            disptokennum.setFont(new Font("Times New Roman",Font.PLAIN,20));
//            token.add(disptokennum);
//            
//            ok = new JButton("OK");
//            ok.setBounds(200,180,80,30);
//            ok.setFont(new Font("Times New Roman",Font.PLAIN,20));
//            ok.addActionListener(this);
//            token.add(ok);
//            
//            token.setSize(500,300);
//            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//            token.setLocation(dim.width/2-token.getSize().width/2, dim.height/2-token.getSize().height/2);
//            
//            token.setResizable(false);
//            token.setTitle("Token Number");
//            token.setLayout(null);
//            token.setVisible(true);
//        }else if(ae.getSource()==b3){
//            new Reception().setVisible(true);
//            this.setVisible(false);
//        }else if(ae.getSource()==ok){
//            JOptionPane.showMessageDialog(null,"Appointment Confirmed.");
//            t1.setText("");
//            t2.setText("");
//            t3.setText("");
//            t4.setText("");
//            ta1.setText("");
//            combo1.setSelectedIndex(0);
//            bg.clearSelection();
//            token.setVisible(false);
//        }else if(ae.getSource()==reset){
//            t1.setText("");
//            t2.setText("");
//            t3.setText("");
//            t4.setText("");
//            ta1.setText("");
//            combo1.setSelectedIndex(0);
//            bg.clearSelection();
//        }
//    }
//    
//    void calculateAge()
//    {
//            String dob = t4.getText();
//            String dbirth[] = dob.split("/");
//            int day = Integer.parseInt(dbirth[0]);
//            int month = Integer.parseInt(dbirth[1]);
//            int year = Integer.parseInt(dbirth[2]);
//            LocalDate selectedDate = LocalDate.of(year,month,day);
//            LocalDate currentDate = LocalDate.now();
//            int resultYear = Period.between(selectedDate,currentDate).getYears();
//            t2.setText(""+resultYear);
//    }
//    
//    Appointment(){
//        l0 = new JLabel("Appointment");
//        l0.setFont(new Font("Times New Roman",Font.PLAIN,30));
//        l0.setBounds(350,0,600,70);
//        add(l0);
//        
//        l1=new JLabel("Mobile Number");
//        l1.setFont(new Font("Times New Roman",Font.PLAIN,26));
//        l1.setBounds(40,60,200,50);
//        add(l1);
//        
//        t1=new JTextField();
//        t1.setBounds(250,70,300,30);
//        t1.setFont(new Font("Times New Roman",Font.PLAIN,20));
//        add(t1);
//        
//        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/search.png"));
//        Image i2 = i1.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
//        ImageIcon i3 = new ImageIcon(i2);
//        
//        b1=new JButton(i3);
//        b1.addActionListener(this);
//        b1.setBounds(550,70,40,29);
//        add(b1);
//        
//        l2=new JLabel("Name");
//        l2.setFont(new Font("Times New Roman",Font.PLAIN,26));
//        l2.setBounds(40,120,90,50);
//        add(l2);
//        
//        t3=new JLabel();
//        t3.setBounds(250,130,300,30);
//        t3.setFont(new Font("Times New Roman",Font.PLAIN,20));
//        add(t3);
//        
//        l3=new JLabel("Gender");
//        l3.setFont(new Font("Times New Roman",Font.PLAIN,26));
//        l3.setBounds(40,180,90,50);
//        add(l3);
//        
//        male = new JRadioButton();
//        male.addActionListener(this);
//        male.setBounds(250,180,30,50);
//        add(male);
//        
//        ma=new JLabel("Male");
//        ma.setFont(new Font("Times New Roman",Font.PLAIN,26));
//        ma.setBounds(280,180,90,50);
//        add(ma);
//        
//        female = new JRadioButton();
//        female.setBounds(400,180,30,50);
//        female.addActionListener(this);
//        add(female);
//        
//        fe=new JLabel("Female");
//        fe.setFont(new Font("Times New Roman",Font.PLAIN,26));
//        fe.setBounds(430,180,90,50);
//        add(fe);
//        
//        bg = new ButtonGroup();
//        bg.add(male);
//        bg.add(female);
//        
//        l5=new JLabel("Birth Date");
//        l5.setFont(new Font("Times New Roman",Font.PLAIN,26));
//        l5.setBounds(40,240,200,50);
//        add(l5);
//        
//        t4 = new JTextField();
//        t4.setBounds(250,250,300,30);                                              
//        t4.setFont(new Font("Times New Roman",Font.PLAIN,20));
//        add(t4);
//        
//        l4=new JLabel("Age");
//        l4.setFont(new Font("Times New Roman",Font.PLAIN,26));
//        l4.setBounds(40,300,200,50);
//        add(l4);
//        
//        t2=new JTextField();
//        t2.setBounds(250,310,300,30);
//        t2.setFont(new Font("Times New Roman",Font.PLAIN,20));
//        add(t2);
//           
//        l6=new JLabel("Blood Group");
//        l6.setFont(new Font("Times New Roman",Font.PLAIN,26));
//        l6.setBounds(40,360,200,50);
//        add(l6);
//        
//        String bloodGroup[] = {"Select","A+","A-","B+","B-","AB+","AB-","O+","O-"};
//        combo1 = new JComboBox(bloodGroup);
//        combo1.setFont(new Font("Times New Roman",Font.PLAIN,20));
//        combo1.setBounds(250,370,300,30);
//        add(combo1);
//       
//        l7=new JLabel("Address");
//        l7.setFont(new Font("Times New Roman",Font.PLAIN,26));
//        l7.setBounds(40,420,200,50);
//        add(l7);
//        
//        ta1=new JTextArea(200,200);
//        ta1.setFont(new Font("Times New Roman",Font.PLAIN,20));
//        ta1.setBounds(250,430,300,100);
//        add(ta1);
//        
//        b2=new JButton("Appointment");
//        b2.setBounds(700,340,200,40);
//        b2.addActionListener(this);
//        b2.setFont(new Font("Times New Roman",Font.PLAIN,20));
//        add(b2);
//        
//        reset=new JButton("Reset");
//        reset.setBounds(700,415,200,40);
//        reset.addActionListener(this);
//        reset.setFont(new Font("Times New Roman",Font.PLAIN,20));
//        add(reset);
//        
//        b3=new JButton("Back");
//        b3.setBounds(700,490,200,40);
//        b3.addActionListener(this);
//        b3.setFont(new Font("Times New Roman",Font.PLAIN,20));
//        add(b3);
//        
//        setSize(1000,600);
//        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);        
//        
//        setTitle("Appointment");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setResizable(false);
//        setLayout(null);
//        setVisible(true);
//    }
//    
//    public static void main(String args[]){
//        new Appointment();
//    }
//}
package clinical.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.*;

public class Appointment extends JFrame implements ActionListener {
    
    JLabel appointmentLabel,mobileLabel,nameLabel,genderLabel,ageLabel,birthLabel,addressLabel,bloodLabel,BackGround;
    JLabel dispNameLabel,dispAddressLabel,dispGenderLabel,dispBirthLabel,dispAgeLabel,dispBloodLabel;
    JButton search,appointment,back,ok,reset;
    JTextField mobileTextField;
    String number;
    JFrame token;
    ImageIcon BlackBackGround;
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==search){
            try{
                connection c = new connection();
                c.createConnection();
                
                number = mobileTextField.getText();
                String str = "Select * from clinicms.patientdetails where mobile = '"+number+"'";
                ResultSet rs = c.s.executeQuery(str);
                while(rs.next()){
                    dispNameLabel.setText(rs.getString("name"));
                    dispAddressLabel.setText(rs.getString("address"));
                    if(rs.getString("gender").equals("M"))
                        dispGenderLabel.setText("Male");
                    else
                        dispGenderLabel.setText("Female");
                    dispBirthLabel.setText(rs.getString("birthdate"));
                    calculateAge();
                    dispBloodLabel.setText(rs.getString("bloodgroup"));
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }else if(ae.getSource()==appointment){
            String getnumber=mobileTextField.getText();
            String getname=dispNameLabel.getText();
            String getage=dispAgeLabel.getText();
            String getgender=dispGenderLabel.getText();
            if(getgender.equals("Male"))
            {
                getgender = "M";
            }
            else
            {
                getgender = "F";
            }
            try{
                connection c = new connection();
                c.createConnection();
                String str = "INSERT INTO clinicms.appointment (mobile,name,gender,age) values('"+getnumber+"','"+getname+"','"+getgender+"','"+getage+"')";
                c.s.executeUpdate(str);
                c.disconnectConnection();
            }catch(Exception e){
                e.printStackTrace();
            }
            token = new JFrame();
            JLabel mobile = new JLabel("Mobile Number : ");
            mobile.setBounds(20,10,150,50);
            mobile.setFont(new Font("Times New Roman",Font.PLAIN,20));
            token.add(mobile);
            
            JLabel dispmobile = new JLabel(mobileTextField.getText());
            dispmobile.setBounds(170,10,150,50);
            dispmobile.setFont(new Font("Times New Roman",Font.PLAIN,20));
            token.add(dispmobile);
            
            JLabel name = new JLabel("Name : ");
            name.setBounds(20,60,80,50);
            name.setFont(new Font("Times New Roman",Font.PLAIN,20));
            token.add(name);
            
            JLabel dispname = new JLabel(dispNameLabel.getText());
            dispname.setBounds(100,60,150,50);
            dispname.setFont(new Font("Times New Roman",Font.PLAIN,20));
            token.add(dispname);
            
            JLabel tokennum = new JLabel("Token Number : ");
            tokennum.setBounds(20,110,150,50);
            tokennum.setFont(new Font("Times New Roman",Font.PLAIN,20));
            token.add(tokennum);
            
            String idtoken="";
            try{
                connection c =new connection();
                c.createConnection();
                String str2 = "Select tokenid from clinicms.appointment where mobile = '"+getnumber+"'";
                ResultSet rs = c.s.executeQuery(str2);
                while(rs.next()){
                    idtoken = rs.getString("tokenid");
                }
                c.disconnectConnection();
            }catch(Exception e){
                e.printStackTrace();
            }
         
            JLabel disptokennum = new JLabel(idtoken);
            disptokennum.setBounds(160,110,80,50);
            disptokennum.setFont(new Font("Times New Roman",Font.PLAIN,20));
            token.add(disptokennum);
            
            ok = new JButton("OK");
            ok.setBounds(200,180,80,30);
            ok.setFont(new Font("Times New Roman",Font.PLAIN,20));
            ok.addActionListener(this);
            token.add(ok);
            
            token.setSize(500,270);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            token.setLocation(dim.width/2-token.getSize().width/2, dim.height/2-token.getSize().height/2);
            token.setResizable(false);
            token.setTitle("Token Number");
            token.setLayout(null);
            token.setVisible(true);
        }else if(ae.getSource()==back){
            new Reception().setVisible(true);
            this.setVisible(false);
        }else if(ae.getSource()==ok){
            JOptionPane.showMessageDialog(null,"Appointment Confirmed.");
            mobileTextField.setText("");
            dispAgeLabel.setText("");
            dispNameLabel.setText("");
            dispBirthLabel.setText("");
            dispAddressLabel.setText("");
            dispBloodLabel.setText("");
            dispGenderLabel.setText("");
            token.setVisible(false);
        }else if(ae.getSource()==reset){
            mobileTextField.setText("");
            dispAgeLabel.setText("");
            dispNameLabel.setText("");
            dispBirthLabel.setText("");
            dispAddressLabel.setText("");
            dispBloodLabel.setText("");
            dispGenderLabel.setText("");
        }
    }
    
    void calculateAge(){
            String dob = dispBirthLabel.getText();
            String dbirth[] = dob.split("/");
            int day = Integer.parseInt(dbirth[0]);
            int month = Integer.parseInt(dbirth[1]);
            int year = Integer.parseInt(dbirth[2]);
            LocalDate selectedDate = LocalDate.of(year,month,day);
            LocalDate currentDate = LocalDate.now();
            int resultYear = Period.between(selectedDate,currentDate).getYears();
            dispAgeLabel.setText(""+resultYear);
    }
    
    Appointment(){
        
        ImageIcon BlackBackGround = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/black.jpg"));
        JLabel BackGround = new JLabel(BlackBackGround);
        BackGround.setBounds(0,0,1000,50);
        add(BackGround);
        
        appointmentLabel = new JLabel("Appointment");
        appointmentLabel.setFont(new Font("Times New Roman",Font.PLAIN,30));
        appointmentLabel.setBounds(350,0,250,50);
        appointmentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        appointmentLabel.setForeground(Color.WHITE);
        BackGround.add(appointmentLabel);
        
        mobileLabel=new JLabel("Mobile Number");
        mobileLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        mobileLabel.setBounds(40,60,200,50);
        add(mobileLabel);
        
        mobileTextField=new JTextField();
        mobileTextField.setBounds(250,70,300,30);
        mobileTextField.setFont(new Font("Times New Roman",Font.PLAIN,22));
        add(mobileTextField);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/search.png"));
        Image i2 = i1.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        
        search=new JButton(i3);
        search.addActionListener(this);
        search.setBounds(550,70,40,29);
        add(search);
        
        nameLabel=new JLabel("Name");
        nameLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        nameLabel.setBounds(40,120,90,50);
        add(nameLabel);
        
        dispNameLabel=new JLabel();
        dispNameLabel.setBounds(250,130,600,30);
        dispNameLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        add(dispNameLabel);
        
        genderLabel=new JLabel("Gender");
        genderLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        genderLabel.setBounds(40,180,90,50);
        add(genderLabel);
        
        dispGenderLabel = new JLabel();
        dispGenderLabel.setBounds(250,190,300,30);                                              
        dispGenderLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        add(dispGenderLabel);
        
        birthLabel=new JLabel("Birth Date");
        birthLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        birthLabel.setBounds(40,240,200,50);
        add(birthLabel);
        
        dispBirthLabel = new JLabel();
        dispBirthLabel.setBounds(250,250,300,30);                                              
        dispBirthLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        add(dispBirthLabel);
        
        ageLabel=new JLabel("Age");
        ageLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        ageLabel.setBounds(40,300,200,50);
        add(ageLabel);
        
        dispAgeLabel=new JLabel();
        dispAgeLabel.setBounds(250,310,300,30);
        dispAgeLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        add(dispAgeLabel);
           
        bloodLabel=new JLabel("Blood Group");
        bloodLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        bloodLabel.setBounds(40,360,200,50);
        add(bloodLabel);
        
        dispBloodLabel = new JLabel();
        dispBloodLabel.setBounds(250,360,300,30);                                              
        dispBloodLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        add(dispBloodLabel);
       
        addressLabel=new JLabel("Address");
        addressLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        addressLabel.setBounds(40,420,200,50);
        add(addressLabel);
        
        dispAddressLabel=new JLabel();
        dispAddressLabel.setFont(new Font("Times New Roman",Font.PLAIN,20));
        dispAddressLabel.setBounds(250,390,600,100);
        add(dispAddressLabel);
        
        appointment=new JButton("Appointment");
        appointment.setBounds(700,340,200,40);
        appointment.addActionListener(this);
        appointment.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(appointment);
        
        reset=new JButton("Reset");
        reset.setBounds(700,415,200,40);
        reset.addActionListener(this);
        reset.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(reset);
        
        back=new JButton("Back");
        back.setBounds(700,490,200,40);
        back.addActionListener(this);
        back.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(back);
        
        setSize(1000,600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        setTitle("Appointment");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setVisible(true);
    }
    
    public static void main(String args[]){
        new Appointment();
    }
}