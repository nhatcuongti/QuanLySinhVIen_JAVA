package Ontap;

import javax.swing.*;
import javax.swing.table.TableColumn;
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
//        setPreferredSize(new Dimension(700, 500));

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


        InformationPanel informationPanel = new InformationPanel();
        ButtonPanel buttonPanel = new ButtonPanel();


        panel.add(informationPanel);
        panel.add(buttonPanel);
        mainPanel.add(panel, BorderLayout.WEST);
    }

    private void addCenter() {
        String[] columnNames = {"ID", "Name", "GPA", "Address"};
        String[][] data = {
                {"19127388", "Bùi Nguyễn Nhật Hào", "6.5", "Lộc Ninh"},
                {"19127599", "Nguyễn Văn Minh Triết", "6.5", "Quảng Nam"}
        };

        TableSinhVien tbSV = new TableSinhVien(data, columnNames);
        tbSV.insertData(new String[]{"19123456", "abc", "6.0", "Loc Ninh"});

        JScrollPane sp = new JScrollPane(tbSV);
        mainPanel.add(sp, BorderLayout.CENTER);
    }



}
