package Domain.Facade.Facade_DM;

import java.util.List;

import Domain.Model.DienMay;

public interface DM_Service {
    void addDM(DienMay dienMay);
    void updateDM(DienMay dienMay);
    void deleteDM(int idDM);
    List<DienMay> getAllDM();
}
