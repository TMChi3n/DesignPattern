package Persistence.DAO_SS;

import java.util.List;

import Domain.Model.SanhSu;

public interface HangSS_Gateway {
    void addSS(SanhSu sanhSu);
    void updateSS(SanhSu sanhSu);
    void deleteSS(int idSS);
    List<SanhSu> getAllSS();
}
