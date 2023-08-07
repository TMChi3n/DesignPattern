package Domain.Model;

public class DienMay extends KhoHang{
    private int baoHanh;
    private double congSuat;

    public DienMay(int id, String name, int soLuongTon, double donGia, int baoHanh, double congSuat){
        super(id, name, soLuongTon, donGia);
        this.baoHanh = baoHanh;
        this.congSuat = congSuat;
    }

    @Override
    public double VAT(){
        double vat;
        vat = donGia * 0.1;
        notifyObserver();
        return vat;
    }

    public int getBaoHanh() {
        return baoHanh;
    }

    public double getCongSuat() {
        return congSuat;
    }

    public void setBaoHanh(int baoHanh) {
        this.baoHanh = baoHanh;
    }

    public void setCongSuat(double congSuat) {
        this.congSuat = congSuat;
    }

}
