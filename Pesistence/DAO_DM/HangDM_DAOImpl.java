package Pesistence.DAO_DM;

import java.util.List;

import Domain.Model.DienMay;

public class HangDM_DAOImpl implements HangDM_DAO{

    private HangDM_Gateway dm_Gateway;

    public HangDM_DAOImpl(HangDM_Gateway dm_Gateway){
        this.dm_Gateway = dm_Gateway;
    }

    @Override
    public void addDM(DienMay dienMay) {
        dm_Gateway.addDM(dienMay);    
    }

    @Override
    public void updateDM(DienMay dienMay) {
        dm_Gateway.updateDM(dienMay);    
    }

    @Override
    public void deleteDM(int idDM) {
        dm_Gateway.deleteDM(idDM);    
    }

    @Override
    public List<DienMay> getAllDM() {
        return dm_Gateway.getAllDM();    
    }
    
}
