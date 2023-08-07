package Persistence.DAO_DM;

import java.util.List;

import Domain.Model.DienMay;

public interface HangDM_Gateway {
    void addDM(DienMay dienMay);
    void updateDM(DienMay dienMay);
    void deleteDM(int idDM);
    List<DienMay> getAllDM();
}
