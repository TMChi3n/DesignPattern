package Pesistence.DAO_TP;

import java.util.Date;
import java.util.List;

import Domain.Model.ThucPham;

public class HangTP_DAOImpl implements HangTP_DAO{

    private HangTP_Gateway tp_Gateway;

    public HangTP_DAOImpl(HangTP_Gateway tp_Gateway){
        this.tp_Gateway = tp_Gateway;
    }

    @Override
    public void addTP(ThucPham thucPham) {
        tp_Gateway.addTP(thucPham);
    }

    @Override
    public void updateTP(ThucPham thucPham) {
        tp_Gateway.updateTP(thucPham);
    }

    @Override
    public void deleteTP(int idTP) {
        tp_Gateway.deleteTP(idTP);
    }

    @Override
    public List<ThucPham> getTPOneWeek(Date startDate, Date endDate) {
        return tp_Gateway.getTPOneWeek(startDate, endDate);
    }

    @Override
    public List<ThucPham> getAllTP() {
        return tp_Gateway.getAllTP();
    }
    
}
