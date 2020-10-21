package clinical.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;

public class UpdatePatientDetails extends JFrame implements ActionListener {
    
    JLabel l0,l1,l2,l3,l4,l5,l6,l7,t2;
    JButton b1,b2,b3,reset,CalculateAge;
    JTextField t1,t3,t4;
    JTextArea ta1;
    JComboBox combo1;
    String number;
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==b1){
            try{
                connection c = new connection();
                c.createConnection();
                number = t1.getText();
                String str = "Select * from patientdetails where mobile = '"+number+"'";
                ResultSet rs = c.s.executeQuery(str);
                if(rs.next()){
                    t3.setText(rs.getString("name"));
                    t2.setText(rs.getString("age"));
                    ta1.setText(rs.getString("address"));
             
                    t4.setText(rs.getString("birthdate"));
                    
                    String bloodgroup = (String)(rs.getString("bloodgroup"));
                    if(bloodgroup.equals("A+")){
                        combo1.setSelectedIndex(1);
                    }else if(bloodgroup.equals("A-")){
                        combo1.setSelectedIndex(2);
                    }else if(bloodgroup.equals("B+")){
                        combo1.setSelectedIndex(3);
                    }else if(bloodgroup.equals("B-")){
                        combo1.setSelectedIndex(4);
                    }else if(bloodgroup.equals("AB+")){
                        combo1.setSelectedIndex(5);
                    }else if(bloodgroup.equals("AB-")){
                        combo1.setSelectedIndex(6);
                    }else if(bloodgroup.equals("O+")){
                        combo1.setSelectedIndex(7);
                    }else if(bloodgroup.equals("O-")){
                        combo1.setSelectedIndex(8);
                    }
                    
                    c.disconnectConnection();
                    c = null;
                }else{
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Mobile Number");
                }
            }catch(Exception e){
                
            }
            
        }else if(ae.getSource()==b2){
            try{
                connection c = new connection();
                c.createConnection();
                String number = t1.getText();
                String name = t3.getText();
                String age = t2.getText();
                String birth = t4.getText();
                String address = ta1.getText();
                String bloodgroup = (String)combo1.getSelectedItem();
                String str = "update patientdetails set name = '"+name+"',age ='"+age+"',birthdate = '"+birth+"',address = '"+address+"',bloodgroup ='"+bloodgroup+"' where mobile ='"+number+"'";
                c.s.executeUpdate(str);
                JOptionPane.showMessageDialog(null,"Patient Details changed Successfully");
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                ta1.setText("");
                combo1.setSelectedIndex(0);
                
                c.disconnectConnection();
                c = null;
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource()==b3){
            new Reception().setVisible(true);
            this.setVisible(false);
        }else if(ae.getSource()==reset){
            t1.setText("");
            t2.setText("");
            t3.setText("");
            t4.setText("");
            ta1.setText("");
            combo1.setSelectedIndex(0);
        }else if(ae.getSource()==CalculateAge){
            String birthdate = t4.getText();
            if(!birthdate.equals("")){
                calculateAge();
            }else{
                JOptionPane.showMessageDialog(null,"Please Add BirthDate.");
            }
        }
    }
    
    void calculateAge(){
            String dob = t4.getText();
            String dbirth[] = dob.split("/");
            int day = Integer.parseInt(dbirth[0]);
            int month = Integer.parseInt(dbirth[1]);
            int year = Integer.parseInt(dbirth[2]);
            LocalDate selectedDate = LocalDate.of(year,month,day);
            LocalDate currentDate = LocalDate.now();
            int resultYear = Period.between(selectedDate,currentDate).getYears();
            t2.setText(""+resultYear);
    }
    
    UpdatePatientDetails(){
        
        ImageIcon BlackBackGround = new ImageIcon("C:\\Users\\hp\\Desktop\\Vraj Shah\\Vraj138\\Java\\Java Project\\Final Clinincal Management System\\Clinical Management System\\src\\clinical\\management\\system\\cms\\black.jpg");
        JLabel BackGround = new JLabel(BlackBackGround);
        BackGround.setBounds(0,0,1000,50);
        add(BackGround);
        
        l0 = new JLabel("Update Patient Details");
        l0.setFont(new Font("Times New Roman",Font.PLAIN,30));
        l0.setBounds(350,0,300,50);
        l0.setHorizontalAlignment(SwingConstants.CENTER);
        l0.setForeground(Color.WHITE);
        BackGround.add(l0);
        
        ImageIcon i01 = new ImageIcon("C:\\Users\\hp\\Desktop\\Vraj Shah\\Vraj138\\Java\\Java Project\\Final Clinincal Management System\\Clinical Management System\\src\\clinical\\management\\system\\cms\\updateDark.jpg");
        Image i02 = i01.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon i03 = new ImageIcon(i02);
        
        JLabel img = new JLabel(i03);
        img.setBounds(720,80,200,200);
        add(img);
        
        l1=new JLabel("Mobile Number");
        l1.setFont(new Font("Times New Roman",Font.PLAIN,26));
        l1.setBounds(40,60,200,50);
        add(l1);
        
        t1=new JTextField();
        t1.setBounds(250,70,300,30);
        t1.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(t1);
        
        ImageIcon i1 = new ImageIcon("C:\\Users\\hp\\Desktop\\Vraj Shah\\Vraj138\\Java\\Java Project\\Final Clinincal Management System\\Clinical Management System\\src\\clinical\\management\\system\\cms\\search.png");
        Image i2 = i1.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        
        b1=new JButton(i3);
        b1.addActionListener(this);
        b1.setBounds(550,70,40,29);
        add(b1);
        
        l2=new JLabel("Name");
        l2.setFont(new Font("Times New Roman",Font.PLAIN,26));
        l2.setBounds(40,120,90,50);
        add(l2);
        
        t3=new JTextField();
        t3.setBounds(250,130,300,30);
        t3.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(t3);
        
        l5=new JLabel("Birth Date");
        l5.setFont(new Font("Times New Roman",Font.PLAIN,26));
        l5.setBounds(40,180,200,50);
        add(l5);
        
        t4 = new JTextField();
        t4.setBounds(250,190,300,30);                                              
        t4.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(t4);
        
        l4=new JLabel("Age");
        l4.setFont(new Font("Times New Roman",Font.PLAIN,26));
        l4.setBounds(40,240,90,50);
        add(l4);
        
        t2 = new JLabel();
        t2.setBounds(250,250,50,30);
        t2.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(t2);
        
        CalculateAge = new JButton("Age");
        CalculateAge.setBounds(480,250,70,30);
        CalculateAge.addActionListener(this);
        CalculateAge.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(CalculateAge);
        
        l6=new JLabel("Blood Group");
        l6.setFont(new Font("Times New Roman",Font.PLAIN,26));
        l6.setBounds(40,300,200,50);
        add(l6);
        
        String bloodGroup[] = {"Select","A+","A-","B+","B-","AB+","AB-","O+","O-"};
        combo1 = new JComboBox(bloodGroup);
        combo1.setFont(new Font("Times New Roman",Font.PLAIN,20));
        combo1.setBounds(250,310,300,30);
        add(combo1);
       
        l7=new JLabel("Address");
        l7.setFont(new Font("Times New Roman",Font.PLAIN,26));
        l7.setBounds(40,360,200,50);
        add(l7);
        
        ta1=new JTextArea();
        ta1.setFont(new Font("Times New Roman",Font.PLAIN,20));
        ta1.setBounds(250,370,300,100);
        ta1.setLineWrap(true);
        add(ta1);
        
        b2=new JButton("Update Patient Details");
        b2.setBounds(700,340,230,40);
        b2.addActionListener(this);
        b2.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(b2);
        
        reset=new JButton("Reset");
        reset.setBounds(700,415,230,40);
        reset.addActionListener(this);
        reset.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(reset);
        
        b3=new JButton("Back");
        b3.setBounds(700,490,230,40);
        b3.addActionListener(this);
        b3.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(b3);
        
        setSize(1000,600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        setTitle("Update Patient Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setVisible(true);
    }
    
    public static void main(String args[]){
        new UpdatePatientDetails();
    }
}
