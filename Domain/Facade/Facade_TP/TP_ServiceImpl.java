package Domain.Facade.Facade_TP;

import java.util.Date;
import java.util.List;

import Domain.Model.ThucPham;
import Persistence.DAO_TP.HangTP_DAO;
import Persistence.DAO_TP.HangTP_DAOImpl;
import Persistence.DAO_TP.HangTP_JdbcGateway;

public class TP_ServiceImpl implements TP_Service{

    private HangTP_DAO tp_DAORemote;

    public TP_ServiceImpl(){
        tp_DAORemote = new HangTP_DAOImpl(new HangTP_JdbcGateway());
       
    }

    @Override
    public void addTP(ThucPham thucPham) {
        tp_DAORemote.addTP(thucPham);
       
    }

    @Override
    public void updateTP(ThucPham thucPham) {
        tp_DAORemote.updateTP(thucPham);
       
    }

    @Override
    public void deleteTP(int idTP) {
        tp_DAORemote.deleteTP(idTP);
        
    }

    @Override
    public List<ThucPham> getTPOneWeek(Date startDate, Date endDate) {
        
        return tp_DAORemote.getTPOneWeek(startDate, endDate);
    }

    @Override
    public List<ThucPham> getAllTP() {
       
        return tp_DAORemote.getAllTP();
    }

    
    
}
