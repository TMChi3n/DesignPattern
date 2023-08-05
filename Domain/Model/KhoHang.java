
package Domain.Model;

import Domain.Observer.Publisher;

public class KhoHang extends Publisher{
    protected int id;
    protected String name;
    protected int soLuongTon;
    protected double donGia;

    public KhoHang(int id, String name, int soLuongTon, double donGia){
        this.id = id;
        this.name = name;
        this.soLuongTon = soLuongTon;
        this.donGia = donGia;
    }

    @Override
    public String toString() {
        return "Mã hàng: " + id + ", Tên hàng: " + name + "Số lượng tồn: " + soLuongTon + "Đơn giá: " + donGia;
    }

    public double getDonGia() {
        return donGia;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public double VAT(){
        notifyObserver();
        return 0;
    }
}
