/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author GNT
 */
public class ChiTietSanPham {
    private String maChiTiet;
    private String maMay;
    private String tenThuocTinh;
    private String giaTriThuocTinh;

    public ChiTietSanPham() {
    }

    public ChiTietSanPham(String maChiTiet, String maMay, String tenThuocTinh, String giaTriThuocTinh) {
        this.maChiTiet = maChiTiet;
        this.maMay = maMay;
        this.tenThuocTinh = tenThuocTinh;
        this.giaTriThuocTinh = giaTriThuocTinh;
    }

    public String getMaChiTiet() {
        return maChiTiet;
    }

    public void setMaChiTiet(String maChiTiet) {
        this.maChiTiet = maChiTiet;
    }

    public String getMaMay() {
        return maMay;
    }

    public void setMaMay(String maMay) {
        this.maMay = maMay;
    }

    public String getTenThuocTinh() {
        return tenThuocTinh;
    }

    public void setTenThuocTinh(String tenThuocTinh) {
        this.tenThuocTinh = tenThuocTinh;
    }

    public String getGiaTriThuocTinh() {
        return giaTriThuocTinh;
    }

    public void setGiaTriThuocTinh(String giaTriThuocTinh) {
        this.giaTriThuocTinh = giaTriThuocTinh;
    }
    
    
}
