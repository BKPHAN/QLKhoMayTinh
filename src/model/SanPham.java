/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author GNT
 */
public class SanPham {
    private String maMay;
    private String loaiMay;
    private String tenMay;
    private int soLuong;
    private double gia;
    private double tiLeLai;
    private String xuatXu;
    private int trangThai;
    private String maNhaCungCap;

    public SanPham() {
    }

    public SanPham(String maMay, String loaiMay, String tenMay, int soLuong, double gia, double tiLeLai, String xuatXu, int trangThai, String maNhaCungCap) {
        this.maMay = maMay;
        this.loaiMay = loaiMay;
        this.tenMay = tenMay;
        this.soLuong = soLuong;
        this.gia = gia;
        this.tiLeLai = tiLeLai;
        this.xuatXu = xuatXu;
        this.trangThai = trangThai;
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getMaMay() {
        return maMay;
    }

    public void setMaMay(String maMay) {
        this.maMay = maMay;
    }

    public String getLoaiMay() {
        return loaiMay;
    }

    public void setLoaiMay(String loaiMay) {
        this.loaiMay = loaiMay;
    }

    public String getTenMay() {
        return tenMay;
    }

    public void setTenMay(String tenMay) {
        this.tenMay = tenMay;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public double getTiLeLai() {
        return tiLeLai;
    }

    public void setTiLeLai(double tiLeLai) {
        this.tiLeLai = tiLeLai;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }
    
}
