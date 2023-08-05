package Domain.Facade.Facade_SS;

import java.util.List;

import Domain.Model.SanhSu;

public interface SS_Service {
    void addSS(SanhSu sanhSu);
    void updateSS(SanhSu sanhSu);
    void deleteSS(int idSS);
    List<SanhSu> getAllSS();
}
