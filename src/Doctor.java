package clinical.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Doctor extends JFrame implements ActionListener
{
    ImageIcon Background1;
    ImageIcon Doctor;

    ImageIcon PatientInfoImage,PastRecordImage,AddReportImage,AppointmentlistImage;

    JButton PatientInfo,PastRecord,AddReport,AppointList;
    JButton ChangePass, LogOut;

    JLabel PatientInfoLabel,PastRecordLabel,AddReportLabel;

    ImageIcon TempImage;
    Image temp;
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==PatientInfo){
            new SeePatientInfo().setVisible(true);
            this.setVisible(false);
        }else if(ae.getSource()==PastRecord){
           new SeePastReport().setVisible(true);
        }else if(ae.getSource()==AddReport){
            new AddReport().setVisible(true);
            this.setVisible(false);
        }else if(ae.getSource()==ChangePass){
            new DoctorChangePassword().setVisible(true);
        }else if(ae.getSource()==LogOut){
            new Login().setVisible(true);
            this.setVisible(false);
        }else if(ae.getSource()==AppointList){
            new AppointmentList().setVisible(true);
        }
    }
    
    Doctor()
    {

        Background1 = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/DarkBackGround.png"));

        JLabel LeftPenal = new JLabel(Background1);
        LeftPenal.setBounds(5, 5, 298, 435);
        add(LeftPenal);

        TempImage = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/img2.png"));
        temp = TempImage.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        Doctor = new ImageIcon(temp);

        TempImage = null;
        temp = null;

        JLabel DoctorImage = new JLabel(Doctor);
        DoctorImage.setBounds(0, 10, 298, 300);
        LeftPenal.add(DoctorImage);

        ChangePass = new JButton("Change Password");
        ChangePass.setBounds(5, 445, 298, 55);
        ChangePass.addActionListener(this);
        ChangePass.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        add(ChangePass);

        LogOut = new JButton("Log Out");
        LogOut.setBounds(5, 505, 298, 55);
        LogOut.addActionListener(this);
        LogOut.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        add(LogOut);


        JLabel Name = new JLabel("Doctor");
        Name.setBounds(5, 330, 298, 55);
        Name.setFont(new Font("Times New Roman", Font.PLAIN, 45));
        Name.setHorizontalAlignment(SwingConstants.CENTER);
        Name.setForeground(Color.WHITE);
        LeftPenal.add(Name);


        TempImage = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/patientinfo.jpg"));
        temp = TempImage.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        PatientInfoImage = new ImageIcon(temp);

        TempImage = null;
        temp = null;

        PatientInfo = new JButton(PatientInfoImage);
        PatientInfo.addActionListener(this);
        PatientInfo.setBounds(410, 25, 200, 200);
        add(PatientInfo);



        TempImage = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/pastrecords.png"));
        temp = TempImage.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        PastRecordImage = new ImageIcon(temp);

        TempImage = null;
        temp = null;

        PastRecord = new JButton(PastRecordImage);
        PastRecord.addActionListener(this);
        PastRecord.setBounds(680, 25, 200, 200);
        add(PastRecord);


        TempImage = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/Report&Medicine.png"));
        temp = TempImage.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        AddReportImage = new ImageIcon(temp);

        TempImage = null;
        temp = null;

        AddReport = new JButton(AddReportImage);
        AddReport.addActionListener(this);
        AddReport.setBounds(410, 300, 200, 200);
        add(AddReport);

        PatientInfoLabel = new JLabel("Patient Info");
        PatientInfoLabel.setBounds(410, 200, 200, 100);
        PatientInfoLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        PatientInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        PatientInfoLabel.setForeground(Color.BLACK);
        add(PatientInfoLabel);

        PastRecordLabel = new JLabel("Past Reports");
        PastRecordLabel.setBounds(680, 200, 200, 100);
        PastRecordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        PastRecordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        PastRecordLabel.setForeground(Color.BLACK);
        add(PastRecordLabel);

        AddReportLabel = new JLabel("Add New Report");
        AddReportLabel.setBounds(410, 475, 200, 100);
        AddReportLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        AddReportLabel.setHorizontalAlignment(SwingConstants.CENTER);
        AddReportLabel.setForeground(Color.BLACK);
        add(AddReportLabel);
        
        TempImage = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/appont_list.png"));
        temp = TempImage.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        AppointmentlistImage = new ImageIcon(temp);
        TempImage = null;
        temp = null;
        
        AppointList = new JButton(AppointmentlistImage);
        AppointList.addActionListener(this);
        AppointList.setBounds(910,20,50,50);
        add(AppointList);
        
        setSize(1000,600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        
        setTitle("Doctor");
        setResizable(false);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        new Doctor();
    }
}

