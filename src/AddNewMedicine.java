package clinical.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AddNewMedicine extends JFrame {
    
    JLabel medicineTypeLabel,medicineLabel,priceLabel,DarkBackGroundLabel,addMedicine,priceParameter;
    JComboBox medicineTypeCMB;
    JTextField medicineTextField,priceTextField;
    JButton Add,Edit,Delete,Update,Back,Refresh;
    JTable MedicineTable;
    DefaultTableModel MedicineTableModel;
    String getMedicineName;
    
    AddNewMedicine(){
        
        ImageIcon  tempDarkBackGroundImport = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/DarkBackGround.png"));
        Image temp = tempDarkBackGroundImport.getImage().getScaledInstance(700, 50, Image.SCALE_DEFAULT);
        ImageIcon DarkBackGroundImport = new ImageIcon(temp);
        
        DarkBackGroundLabel = new JLabel(DarkBackGroundImport);
        DarkBackGroundLabel.setBounds(0,0,700,50);
        add(DarkBackGroundLabel);
        
        addMedicine = new JLabel("Add Medicine");
        addMedicine.setBounds(250,0,200,50);
        addMedicine.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        addMedicine.setHorizontalAlignment(SwingConstants.CENTER);
        addMedicine.setForeground(Color.WHITE);
        DarkBackGroundLabel.add(addMedicine);
        
        medicineTypeLabel = new JLabel("Medicine Type : ");
        medicineTypeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        medicineTypeLabel.setBounds(20,70,170,30);
        add(medicineTypeLabel);
        
        medicineTypeCMB = new JComboBox();
        medicineTypeCMB.addItem("Select Type");
        medicineTypeCMB.addItem("Tablet");
        medicineTypeCMB.addItem("Syrup");
        medicineTypeCMB.addItem("Ointment");
        medicineTypeCMB.addItem("Spray");
        medicineTypeCMB.addItem("Gargle");
        medicineTypeCMB.addItem("Capsule");
        medicineTypeCMB.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        medicineTypeCMB.setBounds(210,70,170,30);
        add(medicineTypeCMB);
        medicineTypeCMB.addActionListener
        (
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        medicineTypeCMBactionPerformed(e);
                    }
                }
        );
        
        medicineLabel = new JLabel("Medicine Name : ");
        medicineLabel.setBounds(20,120,175,30);
        medicineLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        add(medicineLabel);
        
        medicineTextField = new JTextField();
        medicineTextField.setBounds(210,120,300,30);
        medicineTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        add(medicineTextField);
        
        priceLabel=new JLabel("Price : ");
        priceLabel.setBounds(20,170,150,30);
        priceLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        add(priceLabel);
        
        priceTextField = new JTextField();
        priceTextField.setBounds(110,170,80,30);
        priceTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        add(priceTextField);
        
        priceParameter = new JLabel();
        priceParameter.setBounds(200,170,200,30);
        priceParameter.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        add(priceParameter);
        
        Add = new JButton("Add");
        Add.setBounds(50,240,100,30);
        Add.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        add(Add);
        Add.addActionListener
        (
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        AddactionPerformed(e);
                    }
                }
        );
        
        Edit = new JButton("Edit");
        Edit.setBounds(200,240,100,30);
        Edit.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        add(Edit);
        Edit.addActionListener
        (
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        EditactionPerformed(e);
                    }
                }
        );
        
        Delete = new JButton("Delete");
        Delete.setBounds(350,240,100,30);
        Delete.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        add(Delete);
        Delete.addActionListener
        (
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        DeleteactionPerformed(e);
                    }
                }
        );
        
        Update = new JButton("Update");
        Update.setBounds(500,240,100,30);
        Update.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        add(Update);
        Update.addActionListener
        (
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        UpdateactionPerformed(e);
                    }
                }
        );
        
        MedicineTable = new JTable(){
            public boolean isCellEditable(int rowIndex, int mColIndex) 
            {
                return false;
            }
        };
        Object[] values = {"Medicine","Type","Price"};
        MedicineTableModel = (DefaultTableModel)MedicineTable.getModel();
        MedicineTableModel.setColumnIdentifiers(values);
        MedicineTable.setModel(MedicineTableModel);
        
        
        JScrollPane sp = new JScrollPane(MedicineTable);
        sp.setBounds(20,300,600,300);
        add(sp);
        
        ImageIcon tempRefresh = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/refresh.png"));
        Image temp2 = tempRefresh.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon RefreshImport = new ImageIcon(temp2);
        
        Refresh = new JButton(RefreshImport);
        Refresh.setBounds(620,300,35,35);
        add(Refresh);
        Refresh.addActionListener
        (
          new ActionListener(){
              public void actionPerformed(ActionEvent e){
                  RefreshactionPerformed(e);
              }
              
          }    
        );
        
        Back = new JButton("Back");
        Back.setBounds(550,615,100,30);
        Back.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        add(Back);
        Back.addActionListener
        (
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        BackactionPerformed(e);
                    }
                }
        );
        
        connection c = new connection();
        c.createConnection();
        try
        {
            String str = "Select * from clinicms.medicineinfo order by medicinetype";
            ResultSet rs = c.s.executeQuery(str);
            while(rs.next())
            {
                String medicineName=rs.getString("medicinename");
                String medicineType=rs.getString("medicinetype");
                String medicinePrice=rs.getString("medicineprice");
                Object Data[] = {medicineName,medicineType,medicinePrice};
                MedicineTableModel.addRow(Data);
            }
        }
        catch(Exception E)
        {
            E.printStackTrace();
        }
        setSize(700,700);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setResizable(false);
        setTitle("Add Medicine");
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void medicineTypeCMBactionPerformed(ActionEvent ae){
        if(medicineTypeCMB.getSelectedItem().equals("Tablet")){
            priceParameter.setText("Rupees / Pill");
        }else if(medicineTypeCMB.getSelectedItem().equals("Syrup")){
            priceParameter.setText("Rupees / Bottle");
        }else if(medicineTypeCMB.getSelectedItem().equals("Ointment")){
            priceParameter.setText("Rupees / Tube");
        }else if(medicineTypeCMB.getSelectedItem().equals("Spray")){
            priceParameter.setText("Rupees / Bottle");
        }else if(medicineTypeCMB.getSelectedItem().equals("Gargle")){
            priceParameter.setText("Rupees / Bottle");
        }else if(medicineTypeCMB.getSelectedItem().equals("Capsule")){
            priceParameter.setText("Rupees / Capsule");
        }else if(medicineTypeCMB.getSelectedItem().equals("Select Type")){
            priceParameter.setText("");
        }
    }
    
    public void AddactionPerformed(ActionEvent ae){
        if(medicineTypeCMB.getSelectedIndex() == 0 || medicineTextField.getText().isEmpty() ||priceTextField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Fill All The Data");
        }
        else{
            String medicinetype = (String)medicineTypeCMB.getSelectedItem();
            String medicinename = medicineTextField.getText();
            String medicineprice = priceTextField.getText();
            try{
                connection c = new connection();
                c.createConnection();
                String str = "INSERT INTO clinicms.medicineinfo(medicinename,medicinetype,medicineprice) values ('"+medicinename+"','"+medicinetype+"','"+Double.parseDouble(medicineprice)+"')";
                c.s.executeUpdate(str);
                JOptionPane.showMessageDialog(null,"New Medicine Added Successfully now Click on Refresh.");
                medicineTypeCMB.setSelectedIndex(0);
                medicineTextField.setText("");
                priceTextField.setText("");
                priceParameter.setText("");
                c.disconnectConnection();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public void EditactionPerformed(ActionEvent ae){
        
        if(MedicineTable.getSelectedRowCount() == 1)
        {
            int rowno = MedicineTable.getSelectedRow();
            medicineTypeCMB.setSelectedItem((String)MedicineTableModel.getValueAt(rowno, 1));
            medicineTextField.setText((String)MedicineTableModel.getValueAt(rowno, 0));
            priceTextField.setText((String)MedicineTableModel.getValueAt(rowno, 2));
            getMedicineName=medicineTextField.getText();
        }
        else if(MedicineTable.getSelectedRowCount() > 1)
        {
            JOptionPane.showMessageDialog(null, "Please Select Only One Row.");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please Select Row To Edit.");
        }
    }
    
    public void DeleteactionPerformed(ActionEvent ae){
        if(MedicineTable.getSelectedRowCount() == 1)
        {
            int rowno = MedicineTable.getSelectedRow();
            medicineTypeCMB.setSelectedItem((String)MedicineTableModel.getValueAt(rowno, 1));
            medicineTextField.setText((String)MedicineTableModel.getValueAt(rowno, 0));
            priceTextField.setText((String)MedicineTableModel.getValueAt(rowno, 2));
            String nameMedicine = medicineTextField.getText();
            try{
                connection c =new connection();
                c.createConnection();
                String str = "delete from clinicms.medicineinfo where medicinename ='"+nameMedicine+"'";
                c.s.executeUpdate(str);
                resetFields();
                JOptionPane.showMessageDialog(null,"Medicine Deleted Successfully");
                c.disconnectConnection();
            }catch(Exception e){
                e.printStackTrace();
            }
            MedicineTableModel.removeRow(MedicineTable.getSelectedRow());
        }
        else if(MedicineTable.getSelectedRowCount() > 1)
        {
            JOptionPane.showMessageDialog(null, "Please select only one one row");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Plese select row to delete");
        }
    }
    
    public void UpdateactionPerformed(ActionEvent ae){
        if(MedicineTable.getSelectedRowCount() == 1)
        {
            if(medicineTypeCMB.getSelectedIndex() == 0 || medicineTextField.getText().equals("") || priceTextField.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Please Fill All The Field");
            }
            else
            {
                int rowno = MedicineTable.getSelectedRow();
                MedicineTableModel.setValueAt((String)medicineTypeCMB.getSelectedItem(), rowno, 1);
                MedicineTableModel.setValueAt((String)medicineTextField.getText(),rowno, 0);
                MedicineTableModel.setValueAt((String)priceTextField.getText(),rowno, 2);
                String typeMedicine = (String)medicineTypeCMB.getSelectedItem();
                String nameMedicine = medicineTextField.getText();
                String priceMedicine = priceTextField.getText();
                try{
                    connection c =new connection();
                    c.createConnection();
                    String str = "update clinicms.medicineinfo set medicinename ='"+nameMedicine+"',medicinetype = '"+typeMedicine+"',medicineprice = '"+priceMedicine+"' where medicinename ='"+getMedicineName+"'";
                    c.s.executeUpdate(str);
                    resetFields();
                    JOptionPane.showMessageDialog(null,"Medicine Updated Successfully");
                    c.disconnectConnection();
                }catch(Exception e){
                    e.printStackTrace();
                }
                resetFields();
            }
        }
        else if(MedicineTable.getSelectedRowCount() > 1)
        {
            JOptionPane.showMessageDialog(null, "Please Select Only One Row.");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please Select Row To Edit.");
        } 
    }
    
    public void BackactionPerformed(ActionEvent ae){
        new Chemist().setVisible(true);
        this.setVisible(false);
    }
    
    public void RefreshactionPerformed(ActionEvent ae){
        try{
            int rowCount=MedicineTableModel.getRowCount();
            for(int i=rowCount-1;i>=0;i--){
                MedicineTableModel.removeRow(i);
            }
            connection c = new connection();
            c.createConnection();
            String str = "Select * from clinicms.medicineinfo order by medicinetype";
            ResultSet rs = c.s.executeQuery(str);
            while(rs.next()){
                String medicineName=rs.getString("medicinename");
                String medicineType=rs.getString("medicinetype");
                String medicinePrice=rs.getString("medicineprice");
                Object Data[] = {medicineName,medicineType,medicinePrice};
                MedicineTableModel.addRow(Data);
            }
            //MedicineTable.getColumnModel().getColumn(0).setPreferredWidth(150);
            //MedicineTable.getColumnModel().getColumn(1).setPreferredWidth(50);
            //MedicineTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void resetFields(){
        medicineTypeCMB.setSelectedIndex(0);
        medicineTextField.setText("");
        priceTextField.setText("");
        priceParameter.setText("");
    }
    
    public static void main(String[] args) {
        new AddNewMedicine();
    }
}