package Ontap;

import javax.swing.*;
import java.awt.*;

/**
 * @author : Ti
 * @mailto : nhatcuongti@mail.com
 * @created : 10:53 PM, 12/7/2021, Tuesday,
 * @Comment :
 **/
public class SinhVienFrame extends JFrame {
    JPanel mainPanel = new JPanel();
    JButton Back;

    SinhVienFrame(){
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setContentPane(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        addPageStart();
        addCenter();
        addPageWest();

        setVisible(true);
        pack();
    }

    private void addPageStart() {
        Back = new JButton("Return");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(Back);

        mainPanel.add(panel, BorderLayout.PAGE_START);
    }

    private void addPageWest() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel panelInformation = new JPanel();
        panelInformation.setLayout(new GridBagLayout());
        addInformationComponent(panelInformation);

        panel.add(panelInformation);
        mainPanel.add(panel, BorderLayout.WEST);
    }

    private void addInformationComponent(JPanel panelInformation) {

        JLabel IDText = new JLabel("Student ID");
        JTextField IDTextField = new JTextField();
        panelInformation.add(IDText);
        panelInformation.add(IDTextField);

        JLabel NameText = new JLabel("Student Name");
        JTextField NameTextField = new JTextField();
        panelInformation.add(NameText);
        panelInformation.add(NameTextField);

        JLabel GPAText = new JLabel("GPA");
        JTextField GPATextField = new JTextField();
        panelInformation.add(GPAText);
        panelInformation.add(GPATextField);

        JLabel NoteText = new JLabel("Note");
        JTextArea NoteTextField = new JTextArea();
        panelInformation.add(NoteText);
        panelInformation.add(NoteTextField);

        JLabel ImageText = new JLabel("Image");
        JTextField ImageTextField = new JTextField();
        panelInformation.add(ImageText);
        panelInformation.add(ImageTextField);





    }


    private void addCenter() {
        String[] columnNames = {"ID", "Name", "GPA", "Address"};
        String[][] data = {
                {"19127388", "Bùi Nguyễn Nhật Hào", "6.5", "Lộc Ninh"},
                {"19127599", "Nguyễn Văn Minh Triết", "6.5", "Quảng Nam"}
        };

        JTable tables = new JTable(data, columnNames);
        JScrollPane sp = new JScrollPane(tables);

        mainPanel.add(sp, BorderLayout.CENTER);
    }



}
