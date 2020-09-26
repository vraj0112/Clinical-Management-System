package clinical.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Chemist extends JFrame implements ActionListener
{
    ImageIcon Background1;// Background2;
    ImageIcon Chemist;

    ImageIcon SeeMedicineInfoImage,AddNewMedicineImage;

    JButton SeeMedicineInfo,AddNewMedicine;
    JButton ChangePass, LogOut;

    JLabel SeeMedicineInfoLabel,AddNewMedicineLabel;

    ImageIcon TempImage;
    Image temp;
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==SeeMedicineInfo){
            new SeeMedicineInfo().setVisible(true);
            this.setVisible(false);
        }else if(ae.getSource()==AddNewMedicine){
            new AddNewMedicine().setVisible(true);
            this.setVisible(false);
        }else if(ae.getSource()==ChangePass){
            new ChemistChangePassword().setVisible(true);
        }else if(ae.getSource()==LogOut){
            new Login().setVisible(true);
            this.setVisible(false);
        }
    }
    
    Chemist()
    {

        Background1 = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/DarkBackGround.png"));

        JLabel LeftPenal = new JLabel(Background1);
        LeftPenal.setBounds(5, 5, 298, 435);
        add(LeftPenal);

        TempImage = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/img8.png"));
        temp = TempImage.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        Chemist = new ImageIcon(temp);

        TempImage = null;
        temp = null;

        JLabel ChemistImage = new JLabel(Chemist);
        ChemistImage.setBounds(20, 10, 298, 300);
        LeftPenal.add(ChemistImage);

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


        JLabel Name = new JLabel("Chemist");
        Name.setBounds(0, 330, 298, 55);
        Name.setFont(new Font("Times New Roman", Font.PLAIN, 45));
        Name.setHorizontalAlignment(SwingConstants.CENTER);
        Name.setForeground(Color.WHITE);
        LeftPenal.add(Name);


        TempImage = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/see_medicineinfo.png"));
        temp = TempImage.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        SeeMedicineInfoImage = new ImageIcon(temp);

        TempImage = null;
        temp = null;

        SeeMedicineInfo = new JButton(SeeMedicineInfoImage);
        SeeMedicineInfo.addActionListener(this);
        SeeMedicineInfo.setBounds(410, 25, 200, 200);
        add(SeeMedicineInfo);



        TempImage = new ImageIcon(ClassLoader.getSystemResource("clinical/management/system/cms/Add-medicine.png"));
        temp = TempImage.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        AddNewMedicineImage = new ImageIcon(temp);

        TempImage = null;
        temp = null;

        AddNewMedicine = new JButton(AddNewMedicineImage);
        AddNewMedicine.addActionListener(this);
        AddNewMedicine.setBounds(680, 25, 200, 200);
        add(AddNewMedicine);


        SeeMedicineInfoLabel = new JLabel("See Medicine Info");
        SeeMedicineInfoLabel.setBounds(410, 200, 200, 100);
        SeeMedicineInfoLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        SeeMedicineInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        SeeMedicineInfoLabel.setForeground(Color.BLACK);
        add(SeeMedicineInfoLabel);

        AddNewMedicineLabel = new JLabel("Add New Medicine");
        AddNewMedicineLabel.setBounds(680, 200, 200, 100);
        AddNewMedicineLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        AddNewMedicineLabel.setHorizontalAlignment(SwingConstants.CENTER);
        AddNewMedicineLabel.setForeground(Color.BLACK);
        add(AddNewMedicineLabel);
        
        setSize(1000,600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        setTitle("Chemist");
        setResizable(false);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        new Chemist();
    }
}
