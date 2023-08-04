package Frame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.*;


import ThucPham.viewTP;
import DienMay.viewDM;
import SanhSu.viewSS;

public class view extends JFrame{

    private DefaultTableModel tableModel;
    private JTable table;
    private JButton tpBtn;
    private JButton dmBtn;
    private JButton ssBtn;
    private JButton sumBtn;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private JFrame currentPage;

    public view() {

        setTitle("Kho hàng");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // TextField
        JPanel inputPanel = new JPanel(new GridLayout(15, 2));

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        add(mainPanel, BorderLayout.CENTER);

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
        JFrame viewDMFrame = new viewTP();

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