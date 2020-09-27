package clinical.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.*;

public class Appointment extends JFrame implements ActionListener {
    
    JLabel appointmentLabel,mobileLabel,nameLabel,genderLabel,ageLabel,birthLabel,addressLabel,bloodLabel,BackGround;
    JLabel dispNameLabel,dispGenderLabel,dispBirthLabel,dispAgeLabel,dispBloodLabel;
    JButton search,appointment,back,ok,reset;
    JTextField mobileTextField;
    JTextArea AddressTA;
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
                if(rs.next()){
                    dispNameLabel.setText(rs.getString("name"));
                    AddressTA.setText(rs.getString("address"));
                    if(rs.getString("gender").equals("M"))
                        dispGenderLabel.setText("Male");
                    else
                        dispGenderLabel.setText("Female");
                    dispBirthLabel.setText(rs.getString("birthdate"));
                    calculateAge();
                    dispBloodLabel.setText(rs.getString("bloodgroup"));
                }else{
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Mobile Number");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }else if(ae.getSource()==appointment){
            String getnumber=mobileTextField.getText();
            String getname=dispNameLabel.getText();
            String bdate=dispBirthLabel.getText();
            String getbloodgroup = dispBloodLabel.getText();
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
                String str = "INSERT INTO clinicms.appointment (mobile,name,gender,birthdate,age,bloodgroup) values('"+getnumber+"','"+getname+"','"+getgender+"','"+bdate+"','"+getage+"','"+getbloodgroup+"')";
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
            AddressTA.setText("");
            dispBloodLabel.setText("");
            dispGenderLabel.setText("");
            token.setVisible(false);
        }else if(ae.getSource()==reset){
            mobileTextField.setText("");
            dispAgeLabel.setText("");
            dispNameLabel.setText("");
            dispBirthLabel.setText("");
            AddressTA.setText("");
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
        
        ImageIcon i01 = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/appointmentDark.jpg"));
        Image i02 = i01.getImage().getScaledInstance(300,250,Image.SCALE_DEFAULT);
        ImageIcon i03 = new ImageIcon(i02);
        
        JLabel img = new JLabel(i03);
        img.setBounds(650,60,300,250);
        add(img);
        
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
        dispBloodLabel.setBounds(250,370,300,30);                                              
        dispBloodLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        add(dispBloodLabel);
       
        addressLabel=new JLabel("Address");
        addressLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        addressLabel.setBounds(40,420,200,50);
        add(addressLabel);
        
        AddressTA = new JTextArea();
        AddressTA.setLineWrap(true);
        AddressTA.setFont(new Font("Times New Roman",Font.PLAIN,22));
        AddressTA.setBounds(250,433,350,100);
        AddressTA.setEditable(false);
        AddressTA.setBackground( new Color(238, 238, 238) );
        add(AddressTA);

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
