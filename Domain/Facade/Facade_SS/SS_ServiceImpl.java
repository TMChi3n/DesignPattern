package Domain.Facade.Facade_SS;

import java.util.List;

import Domain.Model.SanhSu;
import Pesistence.DAO_SS.*;


public class SS_ServiceImpl implements SS_Service{
    private HangSS_DAO ss_DAO;

    public SS_ServiceImpl(){
        ss_DAO = new HangSS_DAOImpl(new HangSS_JdbcGateway());
    }

    @Override
    public void addSS(SanhSu sanhSu) {
        ss_DAO.addSS(sanhSu);
    }

    @Override
    public void updateSS(SanhSu sanhSu) {
        ss_DAO.updateSS(sanhSu);    
    }

    @Override
    public void deleteSS(int idSS) {
        ss_DAO.deleteSS(idSS);    
    }

    @Override
    public List<SanhSu> getAllSS() {
        return ss_DAO.getAllSS();    
    }
}
