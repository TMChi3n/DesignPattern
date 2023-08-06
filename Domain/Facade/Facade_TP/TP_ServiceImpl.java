package Domain.Facade.Facade_TP;

import java.util.Date;
import java.util.List;

import Domain.Model.ThucPham;
import Pesistence.DAO_TP.HangTP_DAO;
import Pesistence.DAO_TP.HangTP_JdbcGateway;
import Pesistence.DAO_TP.HangTP_DAOImpl;

public class TP_ServiceImpl implements TP_Service{

    private HangTP_DAO tp_DAO;

    public TP_ServiceImpl(){
        tp_DAO = new HangTP_DAOImpl(new HangTP_JdbcGateway());
       
    }

    @Override
    public void addTP(ThucPham thucPham) {
        tp_DAO.addTP(thucPham);
       
    }

    @Override
    public void updateTP(ThucPham thucPham) {
        tp_DAO.updateTP(thucPham);
       
    }

    @Override
    public void deleteTP(int idTP) {
        tp_DAO.deleteTP(idTP);
        
    }

    @Override
    public List<ThucPham> getTPOneWeek(Date startDate, Date endDate) {
        
        return tp_DAO.getTPOneWeek(startDate, endDate);
    }

    @Override
    public List<ThucPham> getAllTP() {
       
        return tp_DAO.getAllTP();
    }

    
    
}
