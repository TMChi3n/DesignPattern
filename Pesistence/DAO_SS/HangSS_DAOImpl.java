package Pesistence.DAO_SS;

import java.util.List;

import Domain.Model.SanhSu;

public class HangSS_DAOImpl implements HangSS_DAO{

    private HangSS_Gateway ss_Gateway;

    public HangSS_DAOImpl(HangSS_Gateway ss_Gateway){
        this.ss_Gateway = ss_Gateway;
    }

    @Override
    public void addSS(SanhSu sanhSu) {
        ss_Gateway.addSS(sanhSu);    
    }

    @Override
    public void updateSS(SanhSu sanhSu) {
        ss_Gateway.updateSS(sanhSu);    
    }

    @Override
    public void deleteSS(int idSS) {
        ss_Gateway.deleteSS(idSS);    
    }

    @Override
    public List<SanhSu> getAllSS() {
        return ss_Gateway.getAllSS();    
    }
    
}
