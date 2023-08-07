package Pesistence.DAO_TP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.util.Date;

import Domain.Model.ThucPham;

public class HangTP_JdbcGateway implements HangTP_Gateway {

    private Connection connection;

    public HangTP_JdbcGateway() {
        String db = "jdbc:sqlserver://localhost:1433;databaseName=KhoHang;trustServerCertificate=true";
        String username = "sa";
        String password = "123456789";
        try {
            connection = DriverManager.getConnection(db, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addTP(ThucPham thucPham) {
        String sql = "INSERT INTO ThucPham (id, name, quantityOnHand, unitPrice, productionDateTP, expiryDateTP, supplier)" 
        + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, thucPham.getId());
            statement.setString(2, thucPham.getName());
            statement.setInt(3, thucPham.getSoLuongTon());
            statement.setInt(4, (int) thucPham.getDonGia());
            statement.setDate(5, new java.sql.Date(thucPham.getNgaySanXuat().getTime()));
            statement.setDate(6, new java.sql.Date(thucPham.getNgayHetHan().getTime()));
            statement.setString(7, thucPham.getNhaCungCap());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateTP(ThucPham thucPham) {
        String sql = "UPDATE ThucPham SET id = ?, name = ?, quantityOnHand = ?, unitPrice = ?, productionDateTP = ?, expiryDateTP = ?, supplier = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, thucPham.getId());
            statement.setString(2, thucPham.getName());
            statement.setInt(3, thucPham.getSoLuongTon());
            statement.setInt(4, (int) thucPham.getDonGia());
            statement.setDate(5, new java.sql.Date(thucPham.getNgaySanXuat().getTime()));
            statement.setDate(6, new java.sql.Date(thucPham.getNgayHetHan().getTime()));
            statement.setString(7, thucPham.getNhaCungCap());
            statement.setInt(8, thucPham.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTP(int idTP) {
        String sql = "DELETE FROM ThucPham WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idTP);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ThucPham> getTPOneWeek(Date startDate, Date endDate) {
        List<ThucPham> thucPham = new ArrayList<>();
        String sql = "SELECT * FROM ThucPham WHERE productionDateTP >= ? AND expiryDateTP <= ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(startDate.getTime()));
            statement.setDate(2, new java.sql.Date(endDate.getTime()));

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int soLuongTon = resultSet.getInt("quantityOnHand");
                    double donGia = resultSet.getDouble("unitPrice");
                    String nhaCungCap = resultSet.getString("supplier");
                    Date ngaySanXuat = resultSet.getDate("productionDateTP");
                    Date ngayHetHan = resultSet.getDate("expiryDateTP");

                    ThucPham thuc_pham = new ThucPham(id, name, soLuongTon, donGia, ngaySanXuat, ngayHetHan, nhaCungCap);
                    thucPham.add(thuc_pham);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return thucPham;
    }

    @Override
    public List<ThucPham> getAllTP() {
        List<ThucPham> thucPham = new ArrayList<>();
        String sql = "SELECT * FROM ThucPham";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int soLuongTon = resultSet.getInt("quantityOnHand");
                double donGia = resultSet.getDouble("unitPrice");
                Date ngaySanXuat = resultSet.getDate("productionDateTP");
                Date ngayHetHan = resultSet.getDate("expiryDateTP");
                String nhaCungCap = resultSet.getString("supplier");

                ThucPham thuc_pham = new ThucPham(id, name, soLuongTon, donGia, ngaySanXuat, ngayHetHan, nhaCungCap);
                thucPham.add(thuc_pham);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return thucPham;
    }

    
}

