package Presentation.App;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

public class ManagementApp extends JFrame{

    private DefaultTableModel tableModel;
    private JTable table;
    private JButton tpBtn;
    private JButton dmBtn;
    private JButton ssBtn;
    private JButton sumBtn;

    public ManagementApp() {

        setTitle("Kho hàng");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Tên hàng");
        tableModel.addColumn("Số lượng tồn");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Nhà cung cấp");
        tableModel.addColumn("Ngày sản xuất");
        tableModel.addColumn("Ngày hết hạn");
        tableModel.addColumn("Bảo hành");
        tableModel.addColumn("Công suất");
        tableModel.addColumn("Nhà sản xuất");
        tableModel.addColumn("Ngày nhập kho");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // TextField
        JPanel inputPanel = new JPanel(new GridLayout(15, 2));

        // Button
        tpBtn = new JButton("Thực phẩm");
        dmBtn = new JButton("Điện máy");
        ssBtn = new JButton("Sành sứ");
        sumBtn = new JButton("Tổng số lượng hàng hóa");

        inputPanel.add(tpBtn);
        inputPanel.add(dmBtn);
        inputPanel.add(ssBtn);
        inputPanel.add(sumBtn);

        add(inputPanel, BorderLayout.SOUTH);

        tpBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pageViewTP();
            }
        });

        dmBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pageViewDM();
            }
        });

        ssBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pageViewSS();
            }
        });

        sumBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sumOfGoods();
            }
        });

    }

    private void sumOfGoods() {

        //
    }

    private void pageViewSS() {
        JFrame viewSSFrame = new viewSS();
    
        viewSSFrame.setLocationRelativeTo(null); // Hiển thị cửa sổ ở giữa màn hình
        viewSSFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Đóng frame khi bấm nút X (không thoát toàn bộ ứng dụng)

        // Hiển thị cửa sổ viewTPFrame
        viewSSFrame.setVisible(true);
        dispose();
    }

    private void pageViewDM() {
        JFrame viewDMFrame = new viewDM();

        viewDMFrame.setLocationRelativeTo(null); // Hiển thị cửa sổ ở giữa màn hình
        viewDMFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Đóng frame khi bấm nút X (không thoát toàn bộ ứng dụng)

        // Hiển thị cửa sổ viewTPFrame
        viewDMFrame.setVisible(true);
        dispose();
    }

    private void pageViewTP() {
        JFrame viewTPFrame = new viewTP();
        viewTPFrame.setLocationRelativeTo(null); // Hiển thị cửa sổ ở giữa màn hình
        viewTPFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Đóng frame khi bấm nút X (không thoát toàn bộ ứng dụng)

        // Hiển thị cửa sổ viewTPFrame
        viewTPFrame.setVisible(true);
        dispose();
    }

    

}