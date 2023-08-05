package Presentation.App;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Domain.Facade.Facade_DM.DM_Service;
import Domain.Facade.Facade_DM.DM_ServiceImpl;
import Domain.Model.DienMay;
import Presentation.CommandProcessor.Cmd_DM.Cmd_Processor_DM;
import Presentation.CommandProcessor.Cmd_DM.CommandDM;
import Presentation.CommandProcessor.Cmd_DM.VAT_DM_Cmd;

public class viewDM extends JFrame {

    private DefaultTableModel tableModel;
    private JTable table;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton backButton;
    private JButton VAT_Button;
    private JTextField idTextField;
    private JTextField tenHangTextField;
    private JTextField soLuongTonTextField;
    private JTextField donGiaTextField;
    private JTextField baoHanhTextField;
    private JTextField congSuatTextField;

    private DM_Service dm_Service;

    public viewDM() {

        dm_Service = new DM_ServiceImpl();

        setTitle("Điện máy");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Tên hàng");
        tableModel.addColumn("Số lượng tồn");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Bảo hành");
        tableModel.addColumn("Công suất");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // TextField
        JPanel inputPanel = new JPanel(new GridLayout(15, 2));
        idTextField = new JTextField();
        tenHangTextField = new JTextField();
        soLuongTonTextField = new JTextField();
        donGiaTextField = new JTextField();
        baoHanhTextField = new JTextField();
        congSuatTextField = new JTextField();

        // Button
        addButton = new JButton("Thêm");
        updateButton = new JButton("Sửa");
        deleteButton = new JButton("Xóa");
        backButton = new JButton("<< Quay trở lại trang chủ");
        VAT_Button = new JButton("VAT");

        // Add
        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idTextField);
        inputPanel.add(new JLabel("Tên hàng:"));
        inputPanel.add(tenHangTextField);
        inputPanel.add(new JLabel("Số lượng tồn:"));
        inputPanel.add(soLuongTonTextField);
        inputPanel.add(new JLabel("Đơn giá:"));
        inputPanel.add(donGiaTextField);
        inputPanel.add(new JLabel("Bảo hành:"));
        inputPanel.add(baoHanhTextField);
        inputPanel.add(new JLabel("Công suất:"));
        inputPanel.add(congSuatTextField);

        inputPanel.add(addButton);
        inputPanel.add(updateButton);
        inputPanel.add(deleteButton);
        inputPanel.add(backButton);
        inputPanel.add(VAT_Button);

        add(inputPanel, BorderLayout.SOUTH);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                backPage();
            }
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addItems();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateItems();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteItems();
            }
        });

        VAT_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VAT_Items();
            }
        });

        loadItems();

    }

    private void VAT_Items() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a ThucPham item to calculate VAT.");
            return;
        }

        // Get the ThucPham object from the selected row index
        DienMay dienMay = dm_Service.getAllDM().get(row);

        CommandDM vatDMCommand = new VAT_DM_Cmd(dienMay);

        Cmd_Processor_DM cmdProcessor = new Cmd_Processor_DM();

        cmdProcessor.execute(vatDMCommand);
    }

    // LOAD ITEMS   
    private void loadItems() {
        List<DienMay> dienMay = dm_Service.getAllDM();
        tableModel.setRowCount(0);
        for(DienMay dien_may : dienMay) {
            Object[] rowData = {dien_may.getId(), dien_may.getName(), dien_may.getSoLuongTon(), dien_may.getDonGia(), dien_may.getBaoHanh(), dien_may.getCongSuat()};
            tableModel.addRow(rowData);
        }
    }

    private void addItems() {
        int id = Integer.parseInt(idTextField.getText());
        String name = tenHangTextField.getText();
        int soLuongTon = Integer.parseInt(soLuongTonTextField.getText());
        double donGia = Double.parseDouble(donGiaTextField.getText());
        int baoHanh = Integer.parseInt(baoHanhTextField.getText());
        double congSuat = Double.parseDouble(congSuatTextField.getText());

        DienMay dienMay = new DienMay(id, name, soLuongTon, donGia, baoHanh, congSuat);
        dm_Service.addDM(dienMay);

        loadItems();
        clearFieldS();
    }

    private void updateItems() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a goods to edit");
        }

        int id = Integer.parseInt(idTextField.getText());
        String name = tenHangTextField.getText();
        int soLuongTon = Integer.parseInt(soLuongTonTextField.getText());
        double donGia = Double.parseDouble(donGiaTextField.getText());
        int baoHanh = Integer.parseInt(baoHanhTextField.getText());
        double congSuat = Double.parseDouble(congSuatTextField.getText());

        DienMay dienMay = new DienMay(id, name, soLuongTon, donGia, baoHanh, congSuat);
        dm_Service.addDM(dienMay);

        loadItems();
    }

    private void deleteItems() {
        int row = table.getSelectedRow();
        if(row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a goods to delete");
            return;
        }

        int id = (int) tableModel.getValueAt(row, 0);
        dm_Service.deleteDM(id);
        loadItems();
    }

// CLEAR FIELDS    
    private void clearFieldS() {
        idTextField.setText("");
        tenHangTextField.setText("");
        soLuongTonTextField.setText("");
        donGiaTextField.setText("");
        baoHanhTextField.setText("");
        congSuatTextField.setText("");
    }


    protected void backPage() {
        JFrame viewPage = new ManagementApp();
        viewPage.setLocationRelativeTo(null);
        viewPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        viewPage.setVisible(true);
        dispose();
    }
}
