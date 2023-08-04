package ThucPham;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Frame.view;

public class viewTP extends JFrame {

    private DefaultTableModel tableModel;
    private JTable table;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton findButton;
    private JButton backButton;
    private JTextField idTextField;
    private JTextField tenHangTextField;
    private JTextField soLuongTonTextField;
    private JTextField donGiaTextField;
    private JTextField ngaySanXuatTextField;
    private JTextField ngayHetHanTextField;
    private JTextField nhaCungCapTextField;

    public viewTP() {

        setTitle("Kho hàng");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Tên hàng");
        tableModel.addColumn("Số lượng tồn");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Nhà cung cấp");
        tableModel.addColumn("Ngày sản xuất");
        tableModel.addColumn("Ngày hết hạn");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // TextField
        JPanel inputPanel = new JPanel(new GridLayout(15, 2));
        idTextField = new JTextField();
        tenHangTextField = new JTextField();
        soLuongTonTextField = new JTextField();
        donGiaTextField = new JTextField();
        nhaCungCapTextField = new JTextField();
        ngaySanXuatTextField = new JTextField();
        ngayHetHanTextField = new JTextField();

        // Button
        addButton = new JButton("Thêm");
        editButton = new JButton("Sửa");
        deleteButton = new JButton("Xóa");
        findButton = new JButton("Tìm kiếm");
        backButton = new JButton("<< Quay trở lại trang chủ");

        // Add
        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idTextField);
        inputPanel.add(new JLabel("Tên hàng:"));
        inputPanel.add(tenHangTextField);
        inputPanel.add(new JLabel("Số lượng tồn:"));
        inputPanel.add(soLuongTonTextField);
        inputPanel.add(new JLabel("Đơn giá:"));
        inputPanel.add(donGiaTextField);
        inputPanel.add(new JLabel("Nhà cung cấp (Thực phẩm):"));
        inputPanel.add(nhaCungCapTextField);
        inputPanel.add(new JLabel("Ngày sản xuất (Thực phẩm):"));
        inputPanel.add(ngaySanXuatTextField);
        inputPanel.add(new JLabel("Ngày hết hạn (Thực phẩm):"));
        inputPanel.add(ngayHetHanTextField);

        inputPanel.add(addButton);
        inputPanel.add(editButton);
        inputPanel.add(deleteButton);
        inputPanel.add(findButton);
        inputPanel.add(backButton);

        add(inputPanel, BorderLayout.SOUTH);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                backPage();
            }
        });
    }

    private void backPage() {
        JFrame viewPage = new view();
        viewPage.setLocationRelativeTo(null);
        viewPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        viewPage.setVisible(true);
        dispose();
    }
}
