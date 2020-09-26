package clinical.management.system;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
public class AppointmentList extends JFrame implements ActionListener{
    JTable t1;
    Object[] x = {"Appointment Id","Mobile Number","Name","Gender","Age"};
    int i=0,j=0;
    JButton reset,back;
    DefaultTableModel tableModel ;
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==reset){
            try{
                connection c = new connection();
                c.createConnection();
                String str = "delete from clinicms.appointment";
                String str2 = "alter table clinicms.appointment Auto_Increment=0";
                c.s.executeUpdate(str);
                c.s.executeUpdate(str2);
                JOptionPane.showMessageDialog(null,"Token id reset successfully");
                this.setVisible(false);
                c.disconnectConnection();
                c = null;
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource()==back){
            this.setVisible(false);
        }
    }
    
    AppointmentList(){
        reset = new JButton("Reset");
        reset.setBounds(150,450,100,30);
        reset.addActionListener(this);
        reset.setFont(new Font("Times New Roman", Font.PLAIN,20));
        add(reset);
        
        back = new JButton("Back");
        back.setBounds(350,450,100,30);
        back.addActionListener(this);
        back.setFont(new Font("Times New Roman", Font.PLAIN,20));
        add(back);
        
        t1 = new JTable()
        {
            public boolean isCellEditable(int rowIndex, int mColIndex) 
            {
                return false;
            }
        };
           
        tableModel = (DefaultTableModel)t1.getModel();
        tableModel.setColumnIdentifiers(x);
        t1.setModel(tableModel);
        t1.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        
        try{
            connection c = new connection();
            c.createConnection();
            String str = "Select * from clinicms.appointment";
            ResultSet rs = c.s.executeQuery(str);
            
            while(rs.next())
            {
                String appointmentID = rs.getString("tokenid");
                String Mobile = rs.getString("mobile");
                String Name = rs.getString("name");
                String Gender = rs.getString("gender");
                String age = rs.getString("age");
                
                
                Object[] value = {appointmentID,Mobile,Name,Gender,age};
                
                tableModel.addRow(value);
                
            }

            c.disconnectConnection();
            c = null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        JScrollPane sp = new JScrollPane(t1);
        sp.setBounds(20,20,560,400);
        add(sp);
        
        setSize(600,550);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Appointment List");
        setLayout(null);
        setVisible(true);
    }
    

    public static void main(String[] args){
        new AppointmentList();
    }
}