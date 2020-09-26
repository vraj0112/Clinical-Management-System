package clinical.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ChemistLogin extends JFrame implements ActionListener{
    JLabel l1,l2,l3;
    JButton b1,b2;
    JTextField t1;
    JPasswordField p1;
    JCheckBox c1;
    
    ChemistLogin(){
        
        l1=new JLabel("Password");
        l1.setFont(new Font("Times New Roman",Font.PLAIN,17));
        l1.setBounds(70,250,100,30);
        add(l1);
        
        p1=new JPasswordField();
        p1.setFont(new Font("Times New Roman",Font.PLAIN,15));
        p1.setBounds(150,250,150,30);
        add(p1);
        
        c1=new JCheckBox();
        c1.setBounds(150,280,20,30);
        c1.addActionListener(this);
        add(c1);
        
        l2=new JLabel("Show Password");
        l2.setBounds(170,280,100,30);
        add(l2);
        
        b1=new JButton("Login");
        b1.setFont(new Font("Times New Roman",Font.PLAIN,17));
        b1.setBounds(60,330,120,30);
        b1.addActionListener(this);
        add(b1);
        
        b2=new JButton("Back");
        b2.setFont(new Font("Times New Roman",Font.PLAIN,17));
        b2.setBounds(210,330,120,30);
        b2.addActionListener(this);
        add(b2);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/img8.png"));
        Image i2 = i1.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        
        l3 = new JLabel(i3);
        l3.setBounds(100,10,200,200);
        add(l3);
        
        setSize(400,450);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        setTitle("Chemist Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==b1){
            String password = p1.getText();
            connection c = new connection();
            c.createConnection();
            String str = "select * from credential where designation = 'Chemist' and password = '"+password+"'";
            try{
                ResultSet rs = c.s.executeQuery(str);
                if(rs.next()){
                    new Chemist().setVisible(true);
                    this.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(null,"Invalid Password.");
                }
                
                c.disconnectConnection();
                c = null;
            }
            catch(Exception e)
            {  
               e.printStackTrace(); 
            }
        }
        else if(ae.getSource()==b2){
            new Login().setVisible(true);
            this.setVisible(false);
        }
       
        
        if(ae.getSource()==c1){
            if(c1.isSelected()){
                p1.setEchoChar((char)0);
            }else{
                p1.setEchoChar('*');
            }
        }
    }
    public static void main(String[] args){
        new ChemistLogin();
    }
}