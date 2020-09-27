package clinical.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class UpdateContact extends JFrame implements ActionListener{
    
    JLabel UpdateContactLabel,MobileNumberLabel,NameLabel,NewMobileNumberLabel,dispNameLabel;
    JTextField getMobileNumberTF,NewMobileNumberTF;
    ImageIcon i1,i3;
    Image i2;
    JButton SerachBT,UpdateBT,BackBT,ResetBT;
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==BackBT){
            this.setVisible(false);
        }
    }
    
     UpdateContact(){
        UpdateContactLabel = new JLabel("Update Contact");
        UpdateContactLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        UpdateContactLabel.setBounds(165,0,300,70);
        add(UpdateContactLabel);
        
        MobileNumberLabel=new JLabel("Mobile Number");
        MobileNumberLabel.setFont(new Font("Times New Roman",Font.PLAIN,20));
        MobileNumberLabel.setBounds(27,52,200,50);
        add(MobileNumberLabel);
        
        getMobileNumberTF=new JTextField();
        getMobileNumberTF.setBounds(220,65,200,25);
        getMobileNumberTF.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(getMobileNumberTF);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/search.png"));
        Image i2 = i1.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        
        SerachBT=new JButton(i3);
        SerachBT.setBounds(420,65,35,25);
        add(SerachBT);
        SerachBT.addActionListener
        (
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        SearchBTactionPerformed(e);
                    }
                }
                
        );
        
        NameLabel=new JLabel("Name");
        NameLabel.setFont(new Font("Times New Roman",Font.PLAIN,20));
        NameLabel.setBounds(27,90,100,50);
        add(NameLabel);
        
        dispNameLabel=new JLabel();
        dispNameLabel.setBounds(220,100,200,25);
        dispNameLabel.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(dispNameLabel);
        
        NewMobileNumberLabel=new JLabel("New Mobile Number");
        NewMobileNumberLabel.setFont(new Font("Times New Roman",Font.PLAIN,20));
        NewMobileNumberLabel.setBounds(27,123,200,50);
        add(NewMobileNumberLabel);
        
        NewMobileNumberTF=new JTextField();
        NewMobileNumberTF.setBounds(220,135,200,25);
        NewMobileNumberTF.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(NewMobileNumberTF);
        
        UpdateBT=new JButton("Update");
        UpdateBT.setBounds(57,180,110,30);
        UpdateBT.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(UpdateBT);
        UpdateBT.addActionListener
        (
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        UpdateBTactionPerfomed(e);
                    }
                }
        );
        
        ResetBT = new JButton("Reset");
        ResetBT.setBounds(187,180,110,30);
        ResetBT.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(ResetBT);
        ResetBT.addActionListener
        (
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        ResetBTactionPerfomed(e);
                    }
                }
        );
    
        BackBT=new JButton("Back");
        BackBT.addActionListener(this);
        BackBT.setBounds(317,180,110,30);
        BackBT.setFont(new Font("Times New Roman",Font.PLAIN,20));
        add(BackBT);
        BackBT.addActionListener(this);
        
        setSize(500,265);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        setTitle("Update Contact");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setVisible(true);
     }
     
     public void SearchBTactionPerformed(ActionEvent e)
     {
        if(getMobileNumberTF.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please Enter Mobile Old Number");
        }
        else
        {
            try
            {
                connection c = new connection();
                c.createConnection();

                String number = getMobileNumberTF.getText();
                String str = "Select * from clinicms.patientdetails where mobile = '"+number+"'";
                ResultSet rs = c.s.executeQuery(str);
                
                if(rs.next())
                {
                    dispNameLabel.setText(rs.getString("name"));
                    getMobileNumberTF.setEditable(false);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Old Mobile Number");
                    getMobileNumberTF.setText("");
                    dispNameLabel.setText("");
                }

                c.disconnectConnection();
                c = null;
            }
            catch(Exception E)
            {
                E.printStackTrace();
            }
        }
     }
     
     public void UpdateBTactionPerfomed(ActionEvent e)
     {
        if(dispNameLabel.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please Enter Old Mobile Number Or Press Search Button");
        }
        else
        {
            if(NewMobileNumberTF.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Please Enter New Mobile Number");
            }
            else
            {
                try
                {                   
                    connection c = new connection();
                    c.createConnection();
                    String number = getMobileNumberTF.getText();
                    String new_number = NewMobileNumberTF.getText();
                    String str = "update clinicms.patientdetails set mobile = '"+new_number+"' where mobile = '"+number+"'";
                    c.s.executeUpdate(str);
                    
                    String stmnt = "update clinicms.appointment set mobile = '"+new_number+"' where mobile = '"+number+"'";
                    c.s.executeUpdate(stmnt);
                    
                    String stmnt1 = "update clinicms.billinfo set mobile = '"+new_number+"' where mobile = '"+number+"'";
                    c.s.executeUpdate(stmnt1);
                    
                    String NewDatabaseCreation = "create database patient"+new_number+";";
                    c.s.executeUpdate(NewDatabaseCreation);

                    String getAllTable = "Show tables from patient"+number+";";
                    ResultSet rs = c.s.executeQuery(getAllTable);
                    
                    ArrayList <String> table_names = new ArrayList<String>();
                    int i =0;
                    while(rs.next())
                    {
                        table_names.add(rs.getString(1));
                        i++;       
                    }
                    
                    for(int k =0; k<i ; k++)
                    {         
                        String copyTable = "rename table patient"+number+"."+table_names.get(k)+" to patient"+new_number+"."+table_names.get(k);
                        c.s.executeUpdate(copyTable);
                    }

                    File sourceFile = new File("D:\\SGP-1\\Database\\"+number);
                    File destFile = new File("D:\\SGP-1\\Database\\"+new_number);
                    
                    if(sourceFile.renameTo(destFile))
                    {
                        
                    }
                    
                    String dropDatabase = "Drop database patient"+number;
                    c.s.executeUpdate(dropDatabase);
                                     
                    JOptionPane.showMessageDialog(null,"Contact Number changed Successfully");
                    
                    getMobileNumberTF.setText("");
                    getMobileNumberTF.setEditable(true);
                    dispNameLabel.setText("");
                    NewMobileNumberTF.setText("");

                    c.disconnectConnection();
                    c = null;
                }
                catch(Exception E){E.printStackTrace();}
            }
        }     
    }
     
     public void ResetBTactionPerfomed(ActionEvent e)
     {
         getMobileNumberTF.setText("");
         getMobileNumberTF.setEditable(true);
         dispNameLabel.setText("");
         NewMobileNumberTF.setText("");
     }
     
     public static void main(String args[]){
        new UpdateContact();
    }

    
}
