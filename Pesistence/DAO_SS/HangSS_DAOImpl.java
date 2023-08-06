package Pesistence.DAO_SS;

import java.util.List;

import Domain.Model.SanhSu;

public class HangSS_DAOImpl implements HangSS_DAO{

    private HangSS_Gateway ss_GatewayRemote;

    public HangSS_DAOImpl(HangSS_Gateway ss_Gateway){
        this.ss_GatewayRemote = ss_Gateway;
    }

    @Override
    public void addSS(SanhSu sanhSu) {
        ss_GatewayRemote.addSS(sanhSu);    
    }

    @Override
    public void updateSS(SanhSu sanhSu) {
        ss_GatewayRemote.updateSS(sanhSu);    
    }

    @Override
    public void deleteSS(int idSS) {
        ss_GatewayRemote.deleteSS(idSS);    
    }

    @Override
    public List<SanhSu> getAllSS() {
        return ss_GatewayRemote.getAllSS();    
    }
    
}
