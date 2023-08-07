package Presentation.App;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PublicKey;
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
import Domain.Observer.Publisher;
import Domain.Observer.Subcriber;
import Presentation.CommandProcessor.Cmd_SS.Cmd_Processor_SS;
import Presentation.CommandProcessor.Cmd_SS.CommandSS;
import Presentation.CommandProcessor.Cmd_SS.VAT_SS_Cmd;

public class viewSS extends JFrame implements Subcriber{

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

    private SS_Service ss_ServiceRemote;
    private Publisher publisherRemote;
    
    public viewSS() {

        ss_ServiceRemote = new SS_ServiceImpl();
        publisherRemote = new Publisher();
        publisherRemote.addObserver(this);

        setTitle("Sành sứ");
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
            JOptionPane.showMessageDialog(this, "Chọn hàng trên bảng để tính thuế");
            return;
        }

        // Get the ThucPham object from the selected row index
        SanhSu sanhSuRemote = ss_ServiceRemote.getAllSS().get(row);

         // Create the VAT command and set the ThucPham object
        CommandSS vatSSCommand = new VAT_SS_Cmd(sanhSuRemote);

        // Create the command processor
        Cmd_Processor_SS cmdProcessor = new Cmd_Processor_SS();

        // Execute the command
        cmdProcessor.execute(vatSSCommand);
    }

// LOAD ITEMS   
    private void loadItems() {
        List<SanhSu> sanhSuRemote = ss_ServiceRemote.getAllSS();
        tableModel.setRowCount(0);
        for(SanhSu sanh_su : sanhSuRemote) {
            Object[] rowData = {sanh_su.getId(), sanh_su.getName(), sanh_su.getSoLuongTon(), sanh_su.getDonGia(), sanh_su.getNhaSanXuat(), sanh_su.getNgayNhapKho()};
            tableModel.addRow(rowData);
        }
    }

// DELETE ITEMS    
    private void deleteItems() {
        int row = table.getSelectedRow();
        if(row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn hàng có trên bảng để xóa");
            return;
        }

        int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa hàng?", "Xác nhận xóa hàng", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            int id = (int) tableModel.getValueAt(row, 0);
            ss_ServiceRemote.deleteSS(id);
            loadItems();
        }
            publisherRemote.notifyObserver();
    }

// UPDATE ITEMS    
    private void updateItems() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn hàng có trên bảng để cập nhật");

        }

        int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn cập nhật hàng?", "Xác nhận cập nhật hàng", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(idTextField.getText());
            String name = tenHangTextField.getText();
            int soLuongTon = Integer.parseInt(soLuongTonTextField.getText());
            double donGia = Double.parseDouble(donGiaTextField.getText());
            String nhaSanXuat = nhaSanXuatTextField.getText();
            Date ngayNhapKho = parseDate(ngayNhapKhoTextField.getText());

            if (soLuongTon < 0 || donGia <= 0) {
                JOptionPane.showMessageDialog(this, "Lỗi. Vui lòng nhập lại cho đúng thông tin");
                clearFieldS();
            } else {
                SanhSu sanhSuRemote = new SanhSu(id, name, soLuongTon, donGia, nhaSanXuat, ngayNhapKho);
                ss_ServiceRemote.updateSS(sanhSuRemote);

                loadItems();
                clearFieldS();
            }
            clearFieldS();

        }
 
        publisherRemote.notifyObserver();
        
    }

// ADD ITEMS    
    private void addItems() {
        int id = Integer.parseInt(idTextField.getText());
        String name = tenHangTextField.getText();
        int soLuongTon = Integer.parseInt(soLuongTonTextField.getText());
        double donGia = Double.parseDouble(donGiaTextField.getText());
        String nhaSanXuat = nhaSanXuatTextField.getText();
        Date ngayNhapKho = parseDate(ngayNhapKhoTextField.getText());

        if (soLuongTon < 0 || donGia <= 0) {
            JOptionPane.showMessageDialog(this, "Lỗi. Vui lòng nhập lại cho đúng thông tin");
            clearFieldS();
        } else {
            SanhSu sanhSuRemote = new SanhSu(id, name, soLuongTon, donGia, nhaSanXuat, ngayNhapKho);
            ss_ServiceRemote.addSS(sanhSuRemote);

            loadItems();
            clearFieldS();
        }  

        publisherRemote.notifyObserver();
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

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String type = (String) tableModel.getValueAt(i, 1);
            int quantity = (int) tableModel.getValueAt(i, 2); // Giả sử số lượng ở cột thứ ba (index 2)

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

    @Override
    public void update() {
        loadItems();
    }
}
