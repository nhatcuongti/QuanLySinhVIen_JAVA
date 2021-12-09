package Ontap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author : Ti
 * @mailto : nhatcuongti@mail.com
 * @created : 9:18 PM, 12/7/2021, Tuesday,
 * @Comment :
 **/
public class LoginFrame extends JFrame implements ActionListener {
    JPanel mainPanel = new JPanel();
    JLabel headerLabel = new JLabel("Connect to database");
    JTextField userDatabase, databaseName;
    JPasswordField passwordDatabase;
    JButton btnConnect = new JButton("Connect");

    LoginFrame(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setContentPane(mainPanel);
        setTitle("Quản lý sinh viên");

        //Processing
        mainPanel.setLayout(new BorderLayout());

        processPageStart();
        processPageCenter();


        pack();
        setVisible(true);
    }

    void processPageStart(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        mainPanel.add(panel, BorderLayout.PAGE_START);
        panel.add(headerLabel);

        headerLabel.setFont(new Font("Serif", Font.BOLD, 30));

    }

    private void processPageCenter() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 5, 3));

        JLabel userLabel = new JLabel("User:");
        userDatabase = new JTextField();
        panel.add(userLabel);
        panel.add(userDatabase);

        JLabel passwordLabel = new JLabel("Password:");
        passwordDatabase = new JPasswordField();
        panel.add(passwordLabel);
        panel.add(passwordDatabase);

        JLabel databaseLabel = new JLabel("Database Name:");
        databaseName = new JTextField();
        panel.add(databaseLabel);
        panel.add(databaseName);

        btnConnect.setBackground(new Color(0x27ae60));
        btnConnect.setForeground(Color.white);
        btnConnect.addActionListener(this);
        panel.add(btnConnect);

        mainPanel.add(panel, BorderLayout.PAGE_END);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        DBSinhVien db = new DBSinhVien(userDatabase.getText(),passwordDatabase.getText(), databaseName.getText());
        if (db.isValid()){
            dispose();
            SinhVienFrame svFrame = new SinhVienFrame();
        }
        else{
            JOptionPane.showMessageDialog(null, "Your information is not valid", "Failed to connect", JOptionPane.CANCEL_OPTION);
        }

    }
}
