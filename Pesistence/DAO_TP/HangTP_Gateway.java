package Pesistence.DAO_TP;

import java.util.Date;
import java.util.List;

import Domain.Model.ThucPham;

public interface HangTP_Gateway {
    void addTP(ThucPham thucPham);
    void updateTP(ThucPham thucPham);
    void deleteTP(int idTP);
    List<ThucPham> getTPOneWeek(Date startDate, Date endDate);
    List<ThucPham> getAllTP();
}
