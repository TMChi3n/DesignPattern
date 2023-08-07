package Domain.Facade.Facade_TP;

import java.util.List;
import java.util.Date;

import Domain.Model.ThucPham;

public interface TP_Service {
    void addTP(ThucPham thucPham);
    void updateTP(ThucPham thucPham);
    void deleteTP(int idTP);
    List<ThucPham> getTPOneWeek(Date startDate, Date endDate);
    List<ThucPham> getAllTP();
}
