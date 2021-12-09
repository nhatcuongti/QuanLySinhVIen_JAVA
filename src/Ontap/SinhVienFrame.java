package Ontap;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author : Ti
 * @mailto : nhatcuongti@mail.com
 * @created : 10:53 PM, 12/7/2021, Tuesday,
 * @Comment :
 **/
public class SinhVienFrame extends JFrame {
    JPanel mainPanel = new JPanel();
    TableSinhVien tbSV;
    ButtonPanel buttonPanel;
    InformationPanel informationPanel;
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


        informationPanel = new InformationPanel();
        buttonPanel = new ButtonPanel(informationPanel, tbSV);


        panel.add(informationPanel);
        panel.add(buttonPanel);
        mainPanel.add(panel, BorderLayout.WEST);
    }

    private void addCenter() {
        String[] columnNames = {"ID", "Name", "GPA", "Address", "Image", "Note"};
        String[][] data = {
                {"19127388", "Bùi Nguyễn Nhật Hào", "6.5", "Lộc Ninh", "Hao.jpg" , "SV CNTT"},
                {"19127599", "Nguyễn Văn Minh Triết", "6.5", "Quảng Nam", "Hao.jpg", "SVCNTT"}
        };


        TableModel model = new DefaultTableModel(columnNames, 0);
        tbSV = new TableSinhVien(model);

        tbSV.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Mouse Clicked Table!!");
                String[] selectedRowData = tbSV.getSelectedData();
                informationPanel.insertData(selectedRowData);
            }
        });


        JScrollPane sp = new JScrollPane(tbSV);
        mainPanel.add(sp, BorderLayout.CENTER);
    }



}
