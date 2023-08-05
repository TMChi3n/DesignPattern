package Pesistence.DAO_DM;

import java.util.List;

import Domain.Model.DienMay;

public interface HangDM_DAO {
    void addDM(DienMay dienMay);
    void updateDM(DienMay dienMay);
    void deleteDM(int idDM);
    List<DienMay> getAllDM();
}

