package clinical.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {
    
    private JButton phb,drb,reb,about,contact;
    private JLabel l1,l2,l3,l4,l5;
    private ImageIcon i1,i3,i4,i6,i7,i9,i10,i12,i13,i15,i16,i18;
    private Image i2,i5,i8,i11,i14,i17;
    Login(){
        
        i1 = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/img3.jpg"));
        i2 = i1.getImage().getScaledInstance(1000,600,Image.SCALE_DEFAULT);
        i3 = new ImageIcon(i2);
        
        l1 = new JLabel(i3);
        l1.setBounds(0,0,1000,600);
        add(l1);
        
        i10 = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/img4.png"));
        i11 = i10.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        i12 = new ImageIcon(i11);
        
        reb = new JButton(i12);
        reb.setBounds(90,140,200,200);
        reb.addActionListener(this);
        l1.add(reb);
        
        l4 = new JLabel("Reception");
        l4.setFont(new Font("Times New Roman",Font.BOLD,25));
        l4.setBounds(145,350,170,40);
        l1.add(l4);
        
        i7 = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/img2.png"));
        i8 = i7.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        i9 = new ImageIcon(i8);
        
        drb = new JButton(i9);
        drb.setBounds(390,140,200,200);
        drb.addActionListener(this);
        l1.add(drb);
        
        l3 = new JLabel("Doctor");
        l3.setFont(new Font("Times New Roman",Font.BOLD,25));
        l3.setBounds(450,350,150,40);
        l1.add(l3);
        
        i4 = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/img8.png"));
        i5 = i4.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        i6 = new ImageIcon(i5);
        
        phb = new JButton(i6);
        phb.setBounds(700,140,200,200);
        phb.addActionListener(this);
        l1.add(phb);
        
        l2 = new JLabel("Chemist");
        l2.setFont(new Font("Times New Roman",Font.BOLD,25));
        l2.setBounds(750,350,150,40);
        l1.add(l2);
        
        l5 = new JLabel("Home");
        l5.setFont(new Font("Times New Roman",Font.PLAIN,40));
        l5.setBounds(440,10,150,70);
        l1.add(l5);
        
        i13  =new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/about.png"));
        i14 = i13.getImage().getScaledInstance(35,45,Image.SCALE_DEFAULT);
        i15 = new ImageIcon(i14);
        
        about = new JButton(i15);
        about.setBounds(870,500,50,50);
        about.addActionListener(this);
        about.setOpaque(false);
        about.setContentAreaFilled(false);
        about.setBorderPainted(false);
      
        l1.add(about);
        
        i16  =new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/contact.png"));
        i17 = i16.getImage().getScaledInstance(35,45,Image.SCALE_DEFAULT);
        i18 = new ImageIcon(i17);
        
        contact = new JButton(i18);
        contact.addActionListener(this);
        contact.setBounds(920,500,50,50);
        contact.setOpaque(false);
        contact.setContentAreaFilled(false);
        contact.setBorderPainted(false);
        l1.add(contact);
        
        setSize(1000,600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setTitle("Home");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==phb){
            new ChemistLogin().setVisible(true);
            this.setVisible(false);
        }else if(ae.getSource()==drb){
            new DoctorLogin().setVisible(true);
            this.setVisible(false);
        }else if(ae.getSource()==reb){
            new ReceptionistLogin().setVisible(true);
            this.setVisible(false);
        }else if(ae.getSource()==about){
            new AboutUs().setVisible(true); 
        }else if(ae.getSource()==contact){
            new ContactUs().setVisible(true);
        }
    }
    
    public static void main(String args[]){
        new Login();
    }
    
}