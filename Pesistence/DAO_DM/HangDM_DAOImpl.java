package Pesistence.DAO_DM;

import java.util.List;

import Domain.Model.DienMay;

public class HangDM_DAOImpl implements HangDM_DAO{

    private HangDM_Gateway dm_GatewayRemote;

    public HangDM_DAOImpl(HangDM_Gateway dm_Gateway){
        this.dm_GatewayRemote = dm_Gateway;
    }

    @Override
    public void addDM(DienMay dienMay) {
        dm_GatewayRemote.addDM(dienMay);    
    }

    @Override
    public void updateDM(DienMay dienMay) {
        dm_GatewayRemote.updateDM(dienMay);    
    }

    @Override
    public void deleteDM(int idDM) {
        dm_GatewayRemote.deleteDM(idDM);    
    }

    @Override
    public List<DienMay> getAllDM() {
        return dm_GatewayRemote.getAllDM();    
    }
    
}
