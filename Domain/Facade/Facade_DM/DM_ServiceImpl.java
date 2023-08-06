package Domain.Facade.Facade_DM;

import java.util.List;

import Domain.Model.DienMay;
import Pesistence.DAO_DM.HangDM_DAO;
import Pesistence.DAO_DM.HangDM_DAOImpl;
import Pesistence.DAO_DM.HangDM_JdbcGateway;

public class DM_ServiceImpl implements DM_Service{

    private HangDM_DAO dm_DAORemote;

    public DM_ServiceImpl() {
        dm_DAORemote = new HangDM_DAOImpl(new HangDM_JdbcGateway());
    }

    @Override
    public void addDM(DienMay dienMay) {
        dm_DAORemote.addDM(dienMay);
    }

    @Override
    public void updateDM(DienMay dienMay) {
        dm_DAORemote.updateDM(dienMay);
    }

    @Override
    public void deleteDM(int idDM) {
        dm_DAORemote.deleteDM(idDM);
    }

    @Override
    public List<DienMay> getAllDM() {
        return dm_DAORemote.getAllDM();
    }
    
}
