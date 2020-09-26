package clinical.management.system;

import javax.swing.*;
import java.awt.*;

public class ClinicalManagementSystem extends JFrame{
    
    ClinicalManagementSystem()
    {   
        ImageIcon leftbg = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/dricon.png"));
        Image dricon = leftbg.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon dricon2 = new ImageIcon(dricon);
        
        JLabel leftdoctoricon = new JLabel(dricon2);
        leftdoctoricon.setBounds(0,0,200,200);
        add(leftdoctoricon);
     
        ImageIcon rightbg = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/dricon.png"));
        Image dricon3 = rightbg.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon dricon4 = new ImageIcon(dricon3);
        
        JLabel doctoricon = new JLabel(dricon4);
        doctoricon.setBounds(790,0,200,200);
        add(doctoricon);
        
        JLabel title = new JLabel("Clinical");
        title.setForeground(Color.RED);
        title.setFont(new Font("Times New Roman",Font.ITALIC | Font.BOLD,42));
        title.setBounds(410,20,150,40);
        add(title);
        
        JLabel titleext = new JLabel("Management System");
        titleext.setForeground(Color.RED);
        titleext.setFont(new Font("Times New Roman",Font.ITALIC | Font.BOLD,42));
        titleext.setBounds(310,70,400,50);
        add(titleext);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/img1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000,600,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        
        JLabel l1 = new JLabel(i3);
        l1.setBounds(0,0,1000,600);
        add(l1);
        
        setSize(1000,600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        setTitle("Clinical Management System");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);    
        try{
            Thread.sleep(3000);
            this.setVisible(false);
            new Login().setVisible(true);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new ClinicalManagementSystem();
    }
    
}