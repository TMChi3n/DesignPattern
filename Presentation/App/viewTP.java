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

import Domain.Facade.Facade_TP.*;
import Domain.Model.ThucPham;
import Presentation.CommandProcessor.Cmd_TP.Cmd_Processor_TP;
import Presentation.CommandProcessor.Cmd_TP.CommandTP;
import Presentation.CommandProcessor.Cmd_TP.VAT_TP_Cmd;

public class viewTP extends JFrame{

    private DefaultTableModel tableModel;
    private JTable table;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton findButton;
    private JButton backButton;
    private JButton VAT_Button;
    private JButton sumOfTP;
    private JTextField idTextField;
    private JTextField tenHangTextField;
    private JTextField soLuongTonTextField;
    private JTextField donGiaTextField;
    private JTextField ngaySanXuatTextField;
    private JTextField ngayHetHanTextField;
    private JTextField nhaCungCapTextField;

    private TP_Service tp_ServiceRemote;

    public viewTP() {


        tp_ServiceRemote = new TP_ServiceImpl();

        setTitle("Thực phẩm");
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
        updateButton = new JButton("Sửa");
        deleteButton = new JButton("Xóa");
        findButton = new JButton("Tìm kiếm");
        backButton = new JButton("<< Quay trở lại trang chủ");
        VAT_Button = new JButton("VAT");
        sumOfTP = new JButton("Tổng số lượng hàng hóa của thực phẩm");

        // Add
        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idTextField);
        inputPanel.add(new JLabel("Tên hàng:"));
        inputPanel.add(tenHangTextField);
        inputPanel.add(new JLabel("Số lượng tồn:"));
        inputPanel.add(soLuongTonTextField);
        inputPanel.add(new JLabel("Đơn giá:"));
        inputPanel.add(donGiaTextField);
        inputPanel.add(new JLabel("Ngày sản xuất (Thực phẩm):"));
        inputPanel.add(ngaySanXuatTextField);
        inputPanel.add(new JLabel("Ngày hết hạn (Thực phẩm):"));
        inputPanel.add(ngayHetHanTextField);
        inputPanel.add(new JLabel("Nhà cung cấp (Thực phẩm):"));
        inputPanel.add(nhaCungCapTextField);

        inputPanel.add(addButton);
        inputPanel.add(updateButton);
        inputPanel.add(deleteButton);
        inputPanel.add(findButton);
        inputPanel.add(VAT_Button);
        inputPanel.add(backButton);
        inputPanel.add(sumOfTP);

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

        findButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                findItems();
            }
        });

        VAT_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VAT_Items();
            }
        });

        sumOfTP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sumTP();
            }
        });

        loadItems();
    }

// VAT
    private void VAT_Items() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn thực phẩm có ở trên bảng");
            return;
        }

        // Get the ThucPham object from the selected row index
        ThucPham thucPham = tp_ServiceRemote.getAllTP().get(row);

         // Create the VAT command and set the ThucPham object
        CommandTP vatTPCommand = new VAT_TP_Cmd(thucPham);

        // Create the command processor
        Cmd_Processor_TP cmdProcessor = new Cmd_Processor_TP();

        // Execute the command
        cmdProcessor.execute(vatTPCommand);
    }

// LOAD ITEMS
    private void loadItems() {
        List<ThucPham> thucPham = tp_ServiceRemote.getAllTP();
        tableModel.setRowCount(0);
        for(ThucPham thuc_pham : thucPham) {
            Object[] rowData = {thuc_pham.getId(), thuc_pham.getName(), thuc_pham.getSoLuongTon(), thuc_pham.getDonGia(), thuc_pham.getNhaCungCap(), thuc_pham.getNgaySanXuat(), thuc_pham.getNgayHetHan()};
            tableModel.addRow(rowData);
        }
    }

// ADD ITEMS
    private void addItems() {
        int id = Integer.parseInt(idTextField.getText());
        String name = tenHangTextField.getText();
        int soLuongTon = Integer.parseInt(soLuongTonTextField.getText());
        double donGia = Double.parseDouble(donGiaTextField.getText());
        Date ngaySanXuat = parseDate(ngaySanXuatTextField.getText());
        Date ngayHetHan = parseDate(ngayHetHanTextField.getText());
        String nhaCungCap = nhaCungCapTextField.getText();

        if (soLuongTon < 0 || donGia <= 0) {
            JOptionPane.showMessageDialog(this, "Lỗi. Vui lòng nhập lại cho đúng thông tin");
            clearFieldS();
        } else {
            ThucPham thucPham = new ThucPham(id, name, soLuongTon, donGia, ngaySanXuat, ngayHetHan, nhaCungCap);
            tp_ServiceRemote.addTP(thucPham);

            loadItems();
            clearFieldS();
        }  

        
    }

// UPDATE ITEMS    
    private void updateItems() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn thực phẩm trên bảng để cập nhật");
        }

        int id = Integer.parseInt(idTextField.getText());
        String name = tenHangTextField.getText();
        int soLuongTon = Integer.parseInt(soLuongTonTextField.getText());
        double donGia = Double.parseDouble(donGiaTextField.getText());
        Date ngaySanXuat = parseDate(ngaySanXuatTextField.getText());
        Date ngayHetHan = parseDate(ngayHetHanTextField.getText());
        String nhaCungCap = nhaCungCapTextField.getText();

        if (soLuongTon < 0 || donGia <= 0) {
            JOptionPane.showMessageDialog(this, "Lỗi. Vui lòng nhập lại cho đúng thông tin");
            clearFieldS();
        } else {
            ThucPham thucPham = new ThucPham(id, name, soLuongTon, donGia, ngaySanXuat, ngayHetHan, nhaCungCap);
            tp_ServiceRemote.addTP(thucPham);

            loadItems();
            clearFieldS();
        }
    }

// DELETE ITEMS
    private void deleteItems() {
        int row = table.getSelectedRow();
        if(row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn thực phẩm trên bảng để xóa");
            return;
        }

        int id = (int) tableModel.getValueAt(row, 0);
        tp_ServiceRemote.deleteTP(id);

        loadItems();
    }

// FIND ITEMS BY WEEK    
    private void findItems() {
        
        try {
            // Lấy ngày sản xuất và hạn sử dụng từ đầu vào của người dùng
            String NSX_String = ngaySanXuatTextField.getText();
            String NHH_String = ngayHetHanTextField.getText();

            // Chuyển đổi ngày từ chuỗi thành đối tượng Ngày
            Date ngaySanXuat = parseDate(NSX_String);
            Date ngayHetHan = parseDate(NHH_String);

            // Kiểm tra việc định dạng ngày
            if (ngaySanXuat == null || ngayHetHan == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập theo đúng định dang yyyy/MM/dd");
                return;
            }

            long differenceInDays = (ngayHetHan.getTime() - ngaySanXuat.getTime()) / (1000 * 60 * 60 * 24);

            if (differenceInDays <= 7) {
                JOptionPane.showMessageDialog(this, "Tìm thấy hàng còn tồn tại.");
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy hàng.");
            }
        } catch (NumberFormatException ex) {
            // Handle the case when the input is not a valid number
            JOptionPane.showMessageDialog(this, "Vui lòng nhập the đúng định dạng yyyy/MM/dd");
        }

        loadItems();
    }

// CONVERT DATE     
    private Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        try {
            // Chuyển đổi đối tượng chuỗi thành ngày
            Date date = dateFormat.parse(dateString);
            return date;
            
        } catch (ParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Vui lòng nhập cho đúng định dạng yyyy/MM/dd");
        }

        return null; // Return null if the parsing fails
    
        
    }

// CLEAR FIELDS WHEN ADD SUCCESS    
    private void clearFieldS() {
        idTextField.setText("");
        tenHangTextField.setText("");
        soLuongTonTextField.setText("");
        donGiaTextField.setText("");
        ngaySanXuatTextField.setText("");
        ngayHetHanTextField.setText("");
        nhaCungCapTextField.setText("");
    }

// BACK VIEW PAGE    
    private void backPage() {
        JFrame viewPage = new ManagementApp();
        viewPage.setLocationRelativeTo(null);
        viewPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        viewPage.setVisible(true);
        dispose();
    }

    private void sumTP(){
        Map<String, Integer> typeQuantityMap = new HashMap<>();

        // Lặp lại danh sách các đối tượng KhoHang và tính tổng số lượng cho từng loại
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String type = (String) tableModel.getValueAt(i, 1); // Assuming the type is in the second column (index 1)
            int quantity = (int) tableModel.getValueAt(i, 2); // Assuming the quantity is in the third column (index 2)

            //Kiểm tra xem loại đã có chưa the map, if yes, update the total quantity, nếu không thì, add it to the map
            typeQuantityMap.put(type, typeQuantityMap.getOrDefault(type, 0) + quantity);
        }

        // Tạo một chuỗi để hiển thị kết quả trong hộp thoại
        StringBuilder result = new StringBuilder();
        result.append("Tổng số lượng hàng hóa của thực phẩm:\n");
        for (Map.Entry<String, Integer> entry : typeQuantityMap.entrySet()) {
            result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        // Hiển thị kết quả trong hộp thoại
        JOptionPane.showMessageDialog(this, result.toString());
    }

}
