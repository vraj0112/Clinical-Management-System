package clinical.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateContact extends JFrame implements ActionListener{
    
    JLabel l0,l1,l2,l3;
    JTextField t1,t2,t3;
    ImageIcon i1,i3;
    Image i2;
    JButton b1,b2,b3;
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==b1){
            try{
                connection c = new connection();
                c.createConnection();
                String number = t1.getText();
                String str = "Select * from patientdetails where mobile = '"+number+"'";
                ResultSet rs = c.s.executeQuery(str);
                while(rs.next()){
                    t2.setText(rs.getString("name"));
                }
                
                c.disconnectConnection();
                c = null;
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource()==b2){
            try{
                connection c = new connection();
                c.createConnection();
                String number = t1.getText();
                String new_number = t3.getText();
                String str = "update patientdetails set mobile = '"+new_number+"' where mobile = '"+number+"'";
                c.s.executeUpdate(str);
                JOptionPane.showMessageDialog(null,"Contact Number changed Successfully");
                t1.setText("");
                t2.setText("");
                t3.setText("");
                
                c.disconnectConnection();
                c = null;
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource()==b3){
            this.setVisible(false);
        }
    }
    
     UpdateContact(){
        l0 = new JLabel("Update Contact");
        l0.setFont(new Font("Times New Roman",Font.PLAIN,25));
        l0.setBounds(165,0,300,70);
        add(l0);
        
        l1=new JLabel("Mobile Number");
        l1.setFont(new Font("Times New Roman",Font.PLAIN,20));
        l1.setBounds(27,52,200,50);
        add(l1);
        
        t1=new JTextField();
        t1.setBounds(220,65,200,25);
        t1.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(t1);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/search.png"));
        Image i2 = i1.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        
        b1=new JButton(i3);
        b1.addActionListener(this);
        b1.setBounds(420,65,35,25);
        add(b1);
        
        l2=new JLabel("Name");
        l2.setFont(new Font("Times New Roman",Font.PLAIN,20));
        l2.setBounds(27,90,100,50);
        add(l2);
        
        t2=new JTextField();
        t2.setBounds(220,100,200,25);
        t2.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(t2);
        
        l3=new JLabel("New Mobile Number");
        l3.setFont(new Font("Times New Roman",Font.PLAIN,20));
        l3.setBounds(27,123,200,50);
        add(l3);
        
        t3=new JTextField();
        t3.setBounds(220,135,200,25);
        t3.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(t3);
        
        b2=new JButton("Update");
        b2.addActionListener(this);
        b2.setBounds(95,180,110,30);
        b2.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(b2);
    
        b3=new JButton("Back");
        b3.addActionListener(this);
        b3.setBounds(265,180,110,30);
        b3.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(b3);
        
        setSize(500,265);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        setTitle("Update Contact");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setVisible(true);
     }
     public static void main(String args[]){
        new UpdateContact();
    }
}
