package com.example.duan1.model;

public class GiohangModel {
    public int idsp;
    public String tensp;
    public long giasp;
    public String hinhsp;
    public int soluongsp;
    public String Tinh;
    public String Huyen;

    public String getHuyen() {
        return Huyen;
    }

    public void setHuyen(String huyen) {
        Huyen = huyen;
    }

    public GiohangModel(int idsp, String tensp, long giasp, String hinhsp, int soluongsp, String Tinh, String Huyen) {
        this.idsp = idsp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.hinhsp = hinhsp;
        this.soluongsp = soluongsp;
        this.Tinh=Tinh;
        this.Huyen=Huyen;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public long getGiasp() {
        return giasp;
    }

    public void setGiasp(long giasp) {
        this.giasp = giasp;
    }

    public String getHinhsp() {
        return hinhsp;
    }

    public void setHinhsp(String hinhsp) {
        this.hinhsp = hinhsp;
    }

    public int getSoluongsp() {
        return soluongsp;
    }

    public void setSoluongsp(int soluongsp) {
        this.soluongsp = soluongsp;
    }
    public String getTinh() {
        return Tinh;
    }

    public void setTinh(String tinh) {
        this.Tinh = tinh;
    }
}
