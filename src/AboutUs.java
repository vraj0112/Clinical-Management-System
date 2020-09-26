
package clinical.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class AboutUs extends JFrame implements ActionListener{
    
    private JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15;
    private ImageIcon i1,i3,i4,i6,i7,i9,i10,i12;
    private Image i2,i5,i8,i11;
    private JButton back;
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==back){
            this.setVisible(false);
        }
    }
    
    AboutUs(){
        
        ImageIcon i20 = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/back.png"));
        Image i21 = i20.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon i22 = new ImageIcon(i21);
        
        back = new JButton(i22);
        back.setBounds(30,10,30,30);
        back.addActionListener(this);
        add(back);
        
        l1=new JLabel("Clinical");
        l1.setFont(new Font("Times New Roman",Font.BOLD | Font.ITALIC,30));
        l1.setForeground(Color.BLUE);
        l1.setBounds(285,0,250,100);
        add(l1);
        
        l2=new JLabel("Management System");
        l2.setFont(new Font("Times New Roman",Font.BOLD | Font.ITALIC,30));
        l2.setForeground(Color.BLUE);
        l2.setBounds(205,30,400,100);
        add(l2);
        
        l3=new JLabel("Clinical Management System (CMS) is specially designed for most of the clinic");
        l3.setFont(new Font("Times New Roman",Font.PLAIN | Font.ITALIC,20));
        l3.setForeground(Color.BLACK);
        l3.setBounds(10,105,650,30);
        add(l3);
        
        l4=new JLabel("to keep track of daily clicnical operation. CMS is developed to reduce existing");
        l4.setFont(new Font("Times New Roman",Font.PLAIN | Font.ITALIC,20));
        l4.setForeground(Color.BLACK);
        l4.setBounds(10,125,650,30);
        add(l4);
        
        l5=new JLabel("paper based method of keeping clinic activities and improve service effectiveness");
        l5.setFont(new Font("Times New Roman",Font.PLAIN | Font.ITALIC,20));
        l5.setForeground(Color.BLACK);
        l5.setBounds(10,145,670,30);
        add(l5);
        
        l6=new JLabel("and efficiency. The interfaces and input authentication are developed using Core");
        l6.setFont(new Font("Times New Roman",Font.PLAIN | Font.ITALIC,20));
        l6.setForeground(Color.BLACK);
        l6.setBounds(10,165,660,30);
        add(l6);
        
        l7=new JLabel("Java. The database is developed using MySQL database.");
        l7.setFont(new Font("Times New Roman",Font.PLAIN | Font.ITALIC,20));
        l7.setForeground(Color.BLACK);
        l7.setBounds(10,185,650,30);
        add(l7);
        
        l8=new JLabel("Developer's Team");
        l8.setFont(new Font("Times New Roman",Font.BOLD,20));
        l8.setForeground(Color.BLUE);
        l8.setBounds(10,235,200,30);
        add(l8);
        
        i1 = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/Uddhav.jpg"));
        i2 = i1.getImage().getScaledInstance(130,130,Image.SCALE_DEFAULT);
        i3 = new ImageIcon(i2);
        
        l8 = new JLabel(i3);
        l8.setBounds(40,280,125,110);
        add(l8);
        
        l9=new JLabel("Uddhav");
        l9.setFont(new Font("Times New Roman",Font.BOLD,20));
        l9.setForeground(Color.BLACK);
        l9.setBounds(70,400,200,30);
        add(l9);
        
        i4 = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/Ishan.jpg"));
        i5 = i4.getImage().getScaledInstance(130,110,Image.SCALE_DEFAULT);
        i6 = new ImageIcon(i5);
        
        l10 = new JLabel(i6);
        l10.setBounds(200,280,125,110);
        add(l10);
        
        l11=new JLabel("Ishan");
        l11.setFont(new Font("Times New Roman",Font.BOLD,20));
        l11.setForeground(Color.BLACK);
        l11.setBounds(240,400,200,30);
        add(l11);
        
        
        i7 = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/Priyansh.jpg"));
        i8 = i7.getImage().getScaledInstance(125,115,Image.SCALE_DEFAULT);
        i9 = new ImageIcon(i8);
        
        l12 = new JLabel(i9);
        l12.setBounds(360,280,125,110);
        add(l12);
        
        l13=new JLabel("Priyansh");
        l13.setFont(new Font("Times New Roman",Font.BOLD,20));
        l13.setForeground(Color.BLACK);
        l13.setBounds(385,400,200,30);
        add(l13);
        
        
        i10 = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/Vraj.jpg"));
        i11 = i10.getImage().getScaledInstance(145,135,Image.SCALE_DEFAULT);
        i12 = new ImageIcon(i11);
        
        l14 = new JLabel(i12);
        l14.setBounds(520,280,125,110);
        add(l14);
        
        l15=new JLabel("Vraj");
        l15.setFont(new Font("Times New Roman",Font.BOLD,20));
        l15.setForeground(Color.BLACK);
        l15.setBounds(560,400,200,30);
        add(l15);
        
        setSize(700,485);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        setTitle("Read-Me About Us");
        setResizable(false);
        setLayout(null);
        setVisible(true);
        
    }
    
    public static void main(String args[]){
        new AboutUs();
    }
}
