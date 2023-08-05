package Presentation.App;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Domain.Facade.Facade_SS.SS_Service;
import Domain.Facade.Facade_SS.SS_ServiceImpl;
import Domain.Model.SanhSu;
import Presentation.CommandProcessor.Cmd_SS.Cmd_Processor_SS;
import Presentation.CommandProcessor.Cmd_SS.CommandSS;
import Presentation.CommandProcessor.Cmd_SS.VAT_SS_Cmd;

public class viewSS extends JFrame{

    private DefaultTableModel tableModel;
    private JTable table;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton backButton;
    private JButton VAT_Button;
    private JButton sumOfSS;
    private JTextField idTextField;
    private JTextField tenHangTextField;
    private JTextField soLuongTonTextField;
    private JTextField donGiaTextField;
    private JTextField nhaSanXuatTextField;
    private JTextField ngayNhapKhoTextField;

    private SS_Service ss_Service;
    
    public viewSS() {

        ss_Service = new SS_ServiceImpl();

        setTitle("sành sứ");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Tên hàng");
        tableModel.addColumn("Số lượng tồn");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Nhà sản xuất");
        tableModel.addColumn("Ngày nhập kho");
        
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // TextField
        JPanel inputPanel = new JPanel(new GridLayout(15, 2));
        idTextField = new JTextField();
        tenHangTextField = new JTextField();
        soLuongTonTextField = new JTextField();
        donGiaTextField = new JTextField();
        nhaSanXuatTextField = new JTextField();
        ngayNhapKhoTextField = new JTextField();

        // Button
        addButton = new JButton("Thêm");
        updateButton = new JButton("Sửa");
        deleteButton = new JButton("Xóa");
        backButton = new JButton("<< Quay trở lại trang chủ");
        VAT_Button = new JButton("VAT");
        sumOfSS = new JButton("Tổng số lượng hàng hóa của sành sứ");

        // Add
        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idTextField);
        inputPanel.add(new JLabel("Tên hàng:"));
        inputPanel.add(tenHangTextField);
        inputPanel.add(new JLabel("Số lượng tồn:"));
        inputPanel.add(soLuongTonTextField);
        inputPanel.add(new JLabel("Đơn giá:"));
        inputPanel.add(donGiaTextField);
        inputPanel.add(new JLabel("Nhà sản xuất:"));
        inputPanel.add(nhaSanXuatTextField);
        inputPanel.add(new JLabel("Ngày nhập kho:"));
        inputPanel.add(ngayNhapKhoTextField);

        inputPanel.add(addButton);
        inputPanel.add(updateButton);
        inputPanel.add(deleteButton);
        inputPanel.add(backButton);
        inputPanel.add(VAT_Button);
        inputPanel.add(sumOfSS);

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

        sumOfSS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sumSS();
            }
        });

        loadItems();
    }

// VAT   
    private void VAT_Items() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a ThucPham item to calculate VAT.");
            return;
        }

        // Get the ThucPham object from the selected row index
        SanhSu sanhSu = ss_Service.getAllSS().get(row);

         // Create the VAT command and set the ThucPham object
        CommandSS vatSSCommand = new VAT_SS_Cmd(sanhSu);

        // Create the command processor
        Cmd_Processor_SS cmdProcessor = new Cmd_Processor_SS();

        // Execute the command
        cmdProcessor.execute(vatSSCommand);
    }

// LOAD ITEMS   
    private void loadItems() {
        List<SanhSu> sanhSu = ss_Service.getAllSS();
        tableModel.setRowCount(0);
        for(SanhSu sanh_su : sanhSu) {
            Object[] rowData = {sanh_su.getId(), sanh_su.getName(), sanh_su.getSoLuongTon(), sanh_su.getDonGia(), sanh_su.getNhaSanXuat(), sanh_su.getNgayNhapKho()};
            tableModel.addRow(rowData);
        }
    }

// DELETE ITEMS    
    private void deleteItems() {
        int row = table.getSelectedRow();
        if(row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a goods to delete");
            return;
        }

        int id = (int) tableModel.getValueAt(row, 0);
        ss_Service.deleteSS(id);
        loadItems();
    }

// UPDATE ITEMS    
    private void updateItems() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a goods to edit");
        }

        int id = Integer.parseInt(idTextField.getText());
        String name = tenHangTextField.getText();
        int soLuongTon = Integer.parseInt(soLuongTonTextField.getText());
        double donGia = Double.parseDouble(donGiaTextField.getText());
        String nhaSanXuat = nhaSanXuatTextField.getText();
        Date ngayNhapKho = parseDate(ngayNhapKhoTextField.getText());

        SanhSu sanhSu = new SanhSu(id, name, soLuongTon, donGia, nhaSanXuat, ngayNhapKho);
        ss_Service.updateSS(sanhSu);

        loadItems();
    }

// ADD ITEMS    
    private void addItems() {
        int id = Integer.parseInt(idTextField.getText());
        String name = tenHangTextField.getText();
        int soLuongTon = Integer.parseInt(soLuongTonTextField.getText());
        double donGia = Double.parseDouble(donGiaTextField.getText());
        String nhaSanXuat = nhaSanXuatTextField.getText();
        Date ngayNhapKho = parseDate(ngayNhapKhoTextField.getText());

        SanhSu sanhSu = new SanhSu(id, name, soLuongTon, donGia, nhaSanXuat, ngayNhapKho);
        ss_Service.addSS(sanhSu);

        loadItems();
        clearFieldS();
    }

// CONVERT DATE TO STRING    
    private Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        try {
            // Parse the date string to a Date object
            Date date = dateFormat.parse(dateString);
            return date;
            
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle the parsing exception as needed
            // For example, show an error message to the user.
        }

        return null; // Return null if the parsing fails
    
        
    }

// CLEAR FIELDS    
    private void clearFieldS() {
        idTextField.setText("");
        tenHangTextField.setText("");
        soLuongTonTextField.setText("");
        donGiaTextField.setText("");
        nhaSanXuatTextField.setText("");
        ngayNhapKhoTextField.setText("");
    }

// BACK TO VIEW PAGE    
    private void backPage() {
        JFrame viewPage = new ManagementApp();
        viewPage.setLocationRelativeTo(null);
        viewPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        viewPage.setVisible(true);
        dispose();
    }

    private void sumSS(){
        Map<String, Integer> typeQuantityMap = new HashMap<>();

        // Iterate through the list of KhoHang objects and calculate the total quantity for each type
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String type = (String) tableModel.getValueAt(i, 1); // Assuming the type is in the second column (index 1)
            int quantity = (int) tableModel.getValueAt(i, 2); // Assuming the quantity is in the third column (index 2)

            // Check if the type is already in the map, if yes, update the total quantity, otherwise, add it to the map
            typeQuantityMap.put(type, typeQuantityMap.getOrDefault(type, 0) + quantity);
        }

        // Create a string to display the results in the dialog box
        StringBuilder result = new StringBuilder();
        result.append("Tổng số lượng hàng hóa của sành sứ:\n");
        for (Map.Entry<String, Integer> entry : typeQuantityMap.entrySet()) {
            result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        // Show the results in a dialog box
        JOptionPane.showMessageDialog(this, result.toString());
    }
}
