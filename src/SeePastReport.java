package clinical.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;



public class SeePastReport extends JFrame implements ActionListener
{
    JLabel DarkBackGround;
    JLabel PastReportLabel;
    
    JLabel TokenNoLabel;
    JTextField getTokenNo;
    JButton ShowBT;
    
    JLabel PatientNameLabel,dispNameLabel;
    
    JTable PastReportsTB;
    DefaultTableModel PastReportTableModel;
    
    JButton ResetBT,OpenBT,BackBT;
    
    String MobileNumber;
    
    public SeePastReport()
    { 
        setLayout(null);
        ImageIcon BlackBackGround = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/black.jpg"));
        JLabel BackGround = new JLabel(BlackBackGround);
        BackGround.setBounds(0,0,600,50);
        add(BackGround);
        
        PastReportLabel = new JLabel("Past Report");
        PastReportLabel.setBounds(225,0,150,50);
        PastReportLabel.setFont(new Font("Times New Roman", Font.PLAIN , 30));
        PastReportLabel.setForeground(Color.WHITE);
        BackGround.add(PastReportLabel);
        
        TokenNoLabel = new JLabel("Token No. :");
        TokenNoLabel.setBounds(95,60,100,30);
        TokenNoLabel.setFont(new Font("Times New Roman", Font.PLAIN , 18));
        add(TokenNoLabel);
        
        getTokenNo = new JTextField();
        getTokenNo.setBounds(215, 60, 100, 30);
        getTokenNo.setFont(new Font("Times New Roman", Font.PLAIN , 18));
        this.add(getTokenNo);
        
        ShowBT = new JButton("Show");
        ShowBT.setBounds(335,60, 100, 30);
        ShowBT.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        add(ShowBT);
        ShowBT.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        ShowBTactionPerformed(e);
                    }
                }
        );
        
        PatientNameLabel = new JLabel("Name :");
        PatientNameLabel.setBounds(170,100,60,30);
        PatientNameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        add(PatientNameLabel);
        
        dispNameLabel = new JLabel();
        dispNameLabel.setBounds(250,100,200,30);
        dispNameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        add(dispNameLabel);
        
        PastReportsTB = new JTable()
        {
            public boolean isCellEditable(int row, int column){
                return false;
            } 
        };
        
        PastReportTableModel = (DefaultTableModel)PastReportsTB.getModel();
        PastReportsTB.setModel(PastReportTableModel);
        Object []PastReportsTableCloumnModel = {"Time Record"};
        PastReportTableModel.setColumnIdentifiers(PastReportsTableCloumnModel);
        PastReportsTB.setFont(new Font("Times New Roman",Font.PLAIN,18));
                
        JScrollPane sp = new JScrollPane(PastReportsTB);
        sp.setBounds(5,150,577,200);
        add(sp);
        
        ResetBT = new JButton("Reset");
        ResetBT.setBounds(120, 370, 100, 30);
        ResetBT.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        add(ResetBT);
        ResetBT.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        ResetBTactionPerformed(e);
                    }
                }
        );
        
        OpenBT = new JButton("Open");
        OpenBT.setBounds(240, 370, 100, 30);
        OpenBT.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        add(OpenBT);
        OpenBT.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        OpenBTactionPrformed(e);
                    }
                }
        );
        
        BackBT = new JButton("Back");
        BackBT.setBounds(360, 370, 100, 30);
        BackBT.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        add(BackBT);
        BackBT.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        BackBTactionPrformed(e);
                    }
                }
        );
        
        
        this.setSize(600,450);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        this.setVisible(true);
        
        this.setResizable(false);
        //this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void ShowBTactionPerformed(ActionEvent e)
    {
        PastReportTableModel.setRowCount(0);
        String TokenNo,PatientName;
        if(getTokenNo.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please Enter Token No...");
        }
        else
        {
            TokenNo = getTokenNo.getText();
            try
            {
                connection c = new connection();
                c.createConnection();

                String getMobileNumber = "select * from clinicms.appointment where tokenid = '"+TokenNo+"'";
                ResultSet rs = c.s.executeQuery(getMobileNumber);
                rs.next();

                MobileNumber = rs.getString("mobile");
                PatientName = rs.getString("name");
                rs = null;

                dispNameLabel.setText(PatientName);
                
                String getTimeLine = "select * from patient"+MobileNumber+".time_record";
                rs = c.s.executeQuery(getTimeLine);

                while(rs.next())
                {
                    String Date = "";
                    Date += rs.getString("dd");
                    Date += "/";
                    Date += rs.getString("mm");
                    Date += "/";
                    Date += rs.getString("yyyy");
                    Date += "   ";
                    Date += rs.getString("hh");
                    Date += ":";
                    Date += rs.getString("mt");

                    Object[] Value = {Date};

                    PastReportTableModel.addRow(Value);
                }
            }catch(Exception E){}
        }
    }
    
    public void ResetBTactionPerformed(ActionEvent e)
    {
        resetFields();
    }
    
    public void OpenBTactionPrformed(ActionEvent e)
    {
        String dd,mm, yyyy ,hh ,mt;
        if(PastReportsTB.getSelectedRowCount()==1)
        {
            int RowToOpen = PastReportsTB.getSelectedRow();
            String DateToOpen = (String)PastReportTableModel.getValueAt(RowToOpen, 0);
            dd = DateToOpen.substring(0,2);
            mm = DateToOpen.substring(3,5);
            yyyy = DateToOpen.substring(6,10);
            hh = DateToOpen.substring(13,15);
            mt = DateToOpen.substring(16,18);

            try
            {
                Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler " + "F:\\SGP-1\\Database\\"+MobileNumber+"\\Report\\" + dd +"_"+mm+"_"+yyyy+"_"+hh+"_"+mt+".pdf");
            }
            catch(Exception E)
            {
                JOptionPane.showMessageDialog(null, "Hekkkp");
                E.printStackTrace();
            }
//            openpdf("F:\\SGP-1\\Database\\"+MobileNumber+"\\Report\\" + dd +"_"+mm+"_"+yyyy+"_"+hh+"_"+mt+".pdf");
        }
        else if(PastReportsTB.getSelectedRowCount()>1)
        {
            JOptionPane.showMessageDialog(null, "Please Select Only One Report");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please Select Report");
        }
    }
    
    public void BackBTactionPrformed(ActionEvent e)
    {
        this.setVisible(false);
    }
    
    public static void main(String args[])
    {
        new SeePastReport();
    }
    
    public void resetFields()
    {
       getTokenNo.setText("");
       dispNameLabel.setText("");
       int rowCount = PastReportsTB.getRowCount();
       for(int i=rowCount-1 ; i>=0 ; i--)
       {
           PastReportTableModel.removeRow(i);
       }
    }

    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
