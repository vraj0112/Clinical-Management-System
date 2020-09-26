package clinical.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Reception extends JFrame implements ActionListener
{
    ImageIcon Background1;// Background2;
    ImageIcon Receptionist;

    ImageIcon AddUserImage,AppointmentImage,EditKeyImage,EditProfileImage,AppointmentlistImage;

    JButton AddUser,Appointment,EditKey,EditProfile,AppointList;
    JButton ChangePass, LogOut;

    JLabel AddPetientLabel,EditProfileLabel,EditKeyLabel,AppointmentLabel;

    ImageIcon TempImage;
    Image temp;
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==AddUser){
            new AddPatient().setVisible(true);
            this.setVisible(false);
        }else if(ae.getSource()==Appointment){
            new Appointment().setVisible(true);
            this.setVisible(false);
        }else if(ae.getSource()==EditKey){
            new UpdateContact().setVisible(true);
        }else if(ae.getSource()==EditProfile){
            new UpdatePatientDetails().setVisible(true);
            this.setVisible(false);
        }else if(ae.getSource()==ChangePass){
            new ReceptionChangePassword().setVisible(true);
        }else if(ae.getSource()==LogOut){
            new Login().setVisible(true);
            this.setVisible(false);
        }else if(ae.getSource()==AppointList){
            new AppointmentList().setVisible(true);
        }
    }
    
    Reception()
    {
        Background1 = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/DarkBackGround.png"));

        JLabel LeftPenal = new JLabel(Background1);
        LeftPenal.setBounds(5, 5, 298, 435);
        add(LeftPenal);

        TempImage = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/Receptionist.png"));
        temp = TempImage.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        Receptionist = new ImageIcon(temp);

        TempImage = null;
        temp = null;

        JLabel ReceptionistImage = new JLabel(Receptionist);
        ReceptionistImage.setBounds(0, 10, 298, 300);
        LeftPenal.add(ReceptionistImage);

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


        JLabel Name = new JLabel("Reception");
        Name.setBounds(5, 350, 298, 55);
        Name.setFont(new Font("Times New Roman", Font.PLAIN, 45));
        Name.setHorizontalAlignment(SwingConstants.CENTER);
        Name.setForeground(Color.WHITE);
        LeftPenal.add(Name);


        TempImage = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/AddUser2.png"));
        temp = TempImage.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        AddUserImage = new ImageIcon(temp);

        TempImage = null;
        temp = null;

        AddUser = new JButton(AddUserImage);
        AddUser.addActionListener(this);
        AddUser.setBounds(410, 25, 200, 200);
        add(AddUser);

        

        TempImage = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/Appointment11.png"));
        temp = TempImage.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        AppointmentImage = new ImageIcon(temp);

        TempImage = null;
        temp = null;

        Appointment = new JButton(AppointmentImage);
        Appointment.addActionListener(this);
        Appointment.setBounds(680, 25, 200, 200);
        add(Appointment);

        TempImage = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/Mobileupdate4.png"));
        temp = TempImage.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        EditKeyImage = new ImageIcon(temp);

        TempImage = null;
        temp = null;

        EditKey = new JButton(EditKeyImage);
        EditKey.addActionListener(this);
        EditKey.setBounds(680, 300, 200, 200);
        add(EditKey);


        TempImage = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/Picture1.png"));
        temp = TempImage.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        EditProfileImage = new ImageIcon(temp);

        TempImage = null;
        temp = null;

        EditProfile = new JButton(EditProfileImage);
        EditProfile.addActionListener(this);
        EditProfile.setBounds(410, 300, 200, 200);
        add(EditProfile);

        AddPetientLabel = new JLabel("Add Patient");
        AddPetientLabel.setBounds(410, 200, 200, 100);
        AddPetientLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        AddPetientLabel.setHorizontalAlignment(SwingConstants.CENTER);
        AddPetientLabel.setForeground(Color.BLACK);
        add(AddPetientLabel);

        AppointmentLabel = new JLabel("Appointment");
        AppointmentLabel.setBounds(680, 200, 200, 100);
        AppointmentLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        AppointmentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        AppointmentLabel.setForeground(Color.BLACK);
        add(AppointmentLabel);

        EditProfileLabel = new JLabel("Update Patient Info");
        EditProfileLabel.setBounds(410, 475, 200, 100);
        EditProfileLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        EditProfileLabel.setHorizontalAlignment(SwingConstants.CENTER);
        EditProfileLabel.setForeground(Color.BLACK);
        add(EditProfileLabel);

        EditKeyLabel = new JLabel("Update Contact No.");
        EditKeyLabel.setBounds(680, 475, 200, 100);
        EditKeyLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        EditKeyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        EditKeyLabel.setForeground(Color.BLACK);
        add(EditKeyLabel);
        
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
        
        setTitle("Reception");
        setResizable(false);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        new Reception();
    }
}
