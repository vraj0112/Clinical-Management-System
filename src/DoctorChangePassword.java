package clinical.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DoctorChangePassword extends JFrame implements ActionListener{
    
    private JLabel l1,l2,l3,l4;
    private JButton b1,b2;
    private JPasswordField p1,p2,p3;
    
    DoctorChangePassword(){
        
        l1=new JLabel("Old Password");
        l1.setFont(new Font("Times New Roman",Font.PLAIN,17));
        l1.setBounds(60,220,100,30);
        add(l1);
        
        p1=new JPasswordField();
        p1.setFont(new Font("Times New Roman",Font.PLAIN,15));
        p1.setBounds(200,220,150,30);
        add(p1);
		
	l2=new JLabel("New Password");
        l2.setFont(new Font("Times New Roman",Font.PLAIN,17));
        l2.setBounds(60,260,100,30);
        add(l2);
        
        p2=new JPasswordField();
        p2.setFont(new Font("Times New Roman",Font.PLAIN,15));
        p2.setBounds(200,260,150,30);
        add(p2);
		
        l3=new JLabel("Confirm Password");
        l3.setFont(new Font("Times New Roman",Font.PLAIN,17));
        l3.setBounds(60,300,150,30);
        add(l3);
        
        p3=new JPasswordField();
        p3.setFont(new Font("Times New Roman",Font.PLAIN,15));
        p3.setBounds(200,300,150,30);
        add(p3);
        
        b1=new JButton("Ok");
        b1.setFont(new Font("Times New Roman",Font.PLAIN,17));
        b1.setBounds(60,370,120,30);
        b1.addActionListener(this);
        add(b1);
        
        b2=new JButton("Back");
        b2.setFont(new Font("Times New Roman",Font.PLAIN,17));
        b2.setBounds(210,370,120,30);
        b2.addActionListener(this);
        add(b2);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/img2.png"));
        Image i2 = i1.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        
        l4 = new JLabel(i3);
        l4.setBounds(100,10,200,200);
        add(l4);
        
        setSize(400,490);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setTitle("Change Password");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==b1){
            String password = p1.getText();
            String newpass = p2.getText();
            String confirmpass = p3.getText();
            connection c = new connection();
            c.createConnection();
            String str = "select * from credential where designation = 'Doctor' and  password = '"+password+"'";
            String str1 = "update credential set password = '"+newpass+"' where designation = 'Doctor'";
            try{
                ResultSet rs = c.s.executeQuery(str);
                if(rs.next()){
                    if(newpass.equals(confirmpass)){
                        c.s.executeUpdate(str1);
                        JOptionPane.showMessageDialog(null,"Password Changed Successfully");
                    }else{
                        JOptionPane.showMessageDialog(null,"Password doesn't match with a New Password","Alert",JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Incorrect Old Password.","Alert",JOptionPane.ERROR_MESSAGE);
                }
                p1.setText("");
                p2.setText("");
                p3.setText("");
                
                c.disconnectConnection();
                c = null;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(ae.getSource()==b2){
            this.setVisible(false);
        }
    }
    public static void main(String[] args){
        new DoctorChangePassword();
    }
}