package clinical.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ContactUs extends JFrame implements ActionListener{
    
    private JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16;
    private ImageIcon i1,i3,i4,i6;
    private Image i2,i5;
    private JButton back;
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==back){
            this.setVisible(false);
        }
    }
    
    ContactUs()
    {
        
        ImageIcon i20 = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/back.png"));
        Image i21 = i20.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon i22 = new ImageIcon(i21);
        
        back = new JButton(i22);
        back.setBounds(30,10,30,30);
        back.addActionListener(this);
        add(back);
        
        l1=new JLabel("Get in touch");
        l1.setFont(new Font("Times New Roman",Font.BOLD | Font.ITALIC,30));
        l1.setForeground(Color.BLUE);
        l1.setBounds(115,0,250,100);
        add(l1);
        
        l2=new JLabel("Need additional information");
        l2.setFont(new Font("Times New Roman",Font.ITALIC,20));
        l2.setForeground(Color.BLACK);
        l2.setBounds(75,110,400,50);
        add(l2);
        
        l3=new JLabel("or have a question or comment?");
        l3.setFont(new Font("Times New Roman",Font.ITALIC,20));
        l3.setForeground(Color.BLACK);
        l3.setBounds(65,135,400,50);
        add(l3);
        
        l4=new JLabel("Please feel free to email us");
        l4.setFont(new Font("Times New Roman",Font.ITALIC,20));
        l4.setForeground(Color.BLACK);
        l4.setBounds(80,180,400,50);
        add(l4);
        
        l5=new JLabel("and we'll get back to you as soon");
        l5.setFont(new Font("Times New Roman",Font.ITALIC,20));
        l5.setForeground(Color.BLACK);
        l5.setBounds(55,205,400,50);
        add(l5);
        
        l6=new JLabel("as possible.");
        l6.setFont(new Font("Times New Roman",Font.ITALIC,20));
        l6.setForeground(Color.BLACK);
        l6.setBounds(135,230,400,50);
        add(l6);
        
        i1 = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/black.jpg"));
        i2 = i1.getImage().getScaledInstance(300,485,Image.SCALE_DEFAULT);
        i3 = new ImageIcon(i2);
        
        l7= new JLabel(i3);
        l7.setBounds(400,0,300,485);
        add(l7);
        
        i4 = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/gmail.png"));
        i5 = i4.getImage().getScaledInstance(80,70,Image.SCALE_DEFAULT);
        i6 = new ImageIcon(i5);
        
        l8= new JLabel(i6);
        l8.setBounds(104,20,86,70);
        l7.add(l8);
        
        l9=new JLabel("Uddhav");
        l9.setFont(new Font("Times New Roman",Font.PLAIN,18));
        l9.setForeground(Color.GRAY);
        l9.setBounds(118,55,100,100);
        l7.add(l9);
        
        l10=new JLabel("19xxxxx@xxxsat.edu.in");
        l10.setFont(new Font("Times New Roman",Font.ITALIC,18));
        l10.setForeground(Color.GRAY);
        l10.setBounds(52,75,300,100);
        l7.add(l10);
        
        l11=new JLabel("Ishan");
        l11.setFont(new Font("Times New Roman",Font.PLAIN,18));
        l11.setForeground(Color.GRAY);
        l11.setBounds(128,110,100,100);
        l7.add(l11);
        
        l12=new JLabel("19xxxxx@xxxsat.edu.in");
        l12.setFont(new Font("Times New Roman",Font.ITALIC,18));
        l12.setForeground(Color.GRAY);
        l12.setBounds(52,130,300,100);
        l7.add(l12);
        
        l13=new JLabel("Priyansh");
        l13.setFont(new Font("Times New Roman",Font.PLAIN,18));
        l13.setForeground(Color.GRAY);
        l13.setBounds(115,165,100,100);
        l7.add(l13);
        
        l14=new JLabel("19xxxxx@xxxsat.edu.in");
        l14.setFont(new Font("Times New Roman",Font.ITALIC,18));
        l14.setForeground(Color.GRAY);
        l14.setBounds(52,185,300,100);
        l7.add(l14);
        
        l15=new JLabel("Vraj");
        l15.setFont(new Font("Times New Roman",Font.PLAIN,18));
        l15.setForeground(Color.GRAY);
        l15.setBounds(131,220,100,100);
        l7.add(l15);
        
        l16=new JLabel("19xxxxx@xxxsat.edu.in");
        l16.setFont(new Font("Times New Roman",Font.ITALIC,18));
        l16.setForeground(Color.GRAY);
        l16.setBounds(52,240,300,100);
        l7.add(l16);
        
        setSize(700,400);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        setTitle("Contact Us");
        setResizable(false);
        setLayout(null);
        setVisible(true);
    }

public static void main(String args[]){
        new ContactUs();
        }
}
