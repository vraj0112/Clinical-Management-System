package clinical.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SeePatientInfo extends JFrame implements ActionListener {
    
    JLabel patientInfoLabel,dispMobileLabel,dispNameLabel,dispAgeLabel,dispBirthLabel,dispGenderLabel,dispBloodLabel;
    JLabel DarkBackGroundLabel,mobileLabel,appointmentIdLabel,nameLabel,genderLabel,birthLabel,ageLabel,bloodLabel;
    JButton search,Back,reset;
    JTextField tokenTextField;
    String token;
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==search){
            try{
                connection c = new connection();
                c.createConnection();
                token = tokenTextField.getText();
                String str = "Select * from appointment where tokenid = '"+token+"'";
                ResultSet rs = c.s.executeQuery(str);
                if(rs.next()){
                    dispMobileLabel.setText(rs.getString("mobile"));
                    dispNameLabel.setText(rs.getString("name"));
                    dispGenderLabel.setText(rs.getString("gender"));
                    dispBirthLabel.setText(rs.getString("birthdate"));
                    dispAgeLabel.setText(rs.getString("age"));
                    dispBloodLabel.setText(rs.getString("bloodgroup"));
                }else{
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Token Number");
                    dispMobileLabel.setText("");
                    dispNameLabel.setText("");
                    dispGenderLabel.setText("");
                    dispBirthLabel.setText("");
                    dispAgeLabel.setText("");
                    dispBloodLabel.setText("");
                    tokenTextField.setText("");
                }
                c.disconnectConnection();
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }else if(ae.getSource()==Back){
            new Doctor().setVisible(true);
            this.setVisible(false);
        }else if(ae.getSource()==reset){
            tokenTextField.setText("");
            dispMobileLabel.setText("");
            dispNameLabel.setText("");
            dispGenderLabel.setText("");
            dispBirthLabel.setText("");
            dispBloodLabel.setText("");
            dispAgeLabel.setText("");
        }
    }
    
    SeePatientInfo(){
        
        ImageIcon  tempDarkBackGroundImport = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/DarkBackGround.png"));
        Image temp = tempDarkBackGroundImport.getImage().getScaledInstance(600, 50, Image.SCALE_DEFAULT);
        ImageIcon DarkBackGroundImport = new ImageIcon(temp);
        
        DarkBackGroundLabel = new JLabel(DarkBackGroundImport);
        DarkBackGroundLabel.setBounds(0,0,600,50);
        add(DarkBackGroundLabel);
        
        patientInfoLabel = new JLabel("Patient Info");
        patientInfoLabel.setBounds(200,0,200,50);
        patientInfoLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        patientInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        patientInfoLabel.setForeground(Color.WHITE);
        DarkBackGroundLabel.add(patientInfoLabel);
        
        appointmentIdLabel=new JLabel("Appointment Id :");
        appointmentIdLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        appointmentIdLabel.setBounds(40,60,200,50);
        add(appointmentIdLabel);
        
        tokenTextField=new JTextField();
        tokenTextField.setBounds(250,70,60,30);
        tokenTextField.setFont(new Font("Times New Roman",Font.PLAIN,25));
        add(tokenTextField);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/search.png"));
        Image i2 = i1.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        
        search=new JButton(i3);
        search.addActionListener(this);
        search.setBounds(309,70,40,29);
        add(search);
        
        nameLabel=new JLabel("Name : ");
        nameLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        nameLabel.setBounds(40,120,90,50);
        add(nameLabel);
        
        dispNameLabel=new JLabel();
        dispNameLabel.setBounds(250,130,300,30);
        dispNameLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        add(dispNameLabel);
        
        genderLabel=new JLabel("Gender : ");
        genderLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        genderLabel.setBounds(40,180,100,50);
        add(genderLabel);
        
        dispGenderLabel = new JLabel();
        dispGenderLabel.setBounds(250,190,300,30);                                              
        dispGenderLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        add(dispGenderLabel);
        
        birthLabel=new JLabel("Birth Date : ");
        birthLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        birthLabel.setBounds(40,240,200,50);
        add(birthLabel);
        
        dispBirthLabel = new JLabel();
        dispBirthLabel.setBounds(250,250,300,30);                                              
        dispBirthLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        add(dispBirthLabel);
        
        ageLabel=new JLabel("Age : ");
        ageLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        ageLabel.setBounds(40,300,200,50);
        add(ageLabel);
        
        dispAgeLabel=new JLabel();
        dispAgeLabel.setBounds(250,310,300,30);
        dispAgeLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        add(dispAgeLabel);
           
        bloodLabel=new JLabel("Blood Group : ");
        bloodLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        bloodLabel.setBounds(40,360,200,50);
        add(bloodLabel);
        
        dispBloodLabel=new JLabel();
        dispBloodLabel.setBounds(250,370,300,30);
        dispBloodLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        add(dispBloodLabel);
        
        mobileLabel=new JLabel("Mobile Number : ");
        mobileLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        mobileLabel.setBounds(40,420,200,50);
        add(mobileLabel);
        
        dispMobileLabel=new JLabel();
        dispMobileLabel.setBounds(250,430,300,30);
        dispMobileLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        add(dispMobileLabel);
        
        reset=new JButton("Reset");
        reset.setBounds(150,490,100,40);
        reset.addActionListener(this);
        reset.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(reset);
        
        Back=new JButton("Back");
        Back.setBounds(350,490,100,40);
        Back.addActionListener(this);
        Back.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(Back);
        
        setSize(600,600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);        
        
        setTitle("Patient Info");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setVisible(true);
    }
    
    public static void main(String args[]){
        new SeePatientInfo();
    }
}