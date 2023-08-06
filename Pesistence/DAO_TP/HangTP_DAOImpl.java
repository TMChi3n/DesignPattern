package Pesistence.DAO_TP;

import java.util.Date;
import java.util.List;

import Domain.Model.ThucPham;

public class HangTP_DAOImpl implements HangTP_DAO{

    private HangTP_Gateway tp_GatewayRemote;

    public HangTP_DAOImpl(HangTP_Gateway tp_Gateway){
        this.tp_GatewayRemote = tp_Gateway;
    }

    @Override
    public void addTP(ThucPham thucPham) {
        tp_GatewayRemote.addTP(thucPham);
    }

    @Override
    public void updateTP(ThucPham thucPham) {
        tp_GatewayRemote.updateTP(thucPham);
    }

    @Override
    public void deleteTP(int idTP) {
        tp_GatewayRemote.deleteTP(idTP);
    }

    @Override
    public List<ThucPham> getTPOneWeek(Date startDate, Date endDate) {
        return tp_GatewayRemote.getTPOneWeek(startDate, endDate);
    }

    @Override
    public List<ThucPham> getAllTP() {
        return tp_GatewayRemote.getAllTP();
    }
    
}
