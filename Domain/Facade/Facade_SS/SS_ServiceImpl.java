package Domain.Facade.Facade_SS;

import java.util.List;

import Domain.Model.SanhSu;
import Persistence.DAO_SS.*;


public class SS_ServiceImpl implements SS_Service{
    private HangSS_DAO ss_DAORemote;

    public SS_ServiceImpl(){
        ss_DAORemote = new HangSS_DAOImpl(new HangSS_JdbcGateway());
    }

    @Override
    public void addSS(SanhSu sanhSu) {
        ss_DAORemote.addSS(sanhSu);
    }

    @Override
    public void updateSS(SanhSu sanhSu) {
        ss_DAORemote.updateSS(sanhSu);    
    }

    @Override
    public void deleteSS(int idSS) {
        ss_DAORemote.deleteSS(idSS);    
    }

    @Override
    public List<SanhSu> getAllSS() {
        return ss_DAORemote.getAllSS();    
    }
}
