package dto;

import java.util.List;

import model.ChiTietSanPham;

public class SanPhamDTO {
    private String maMay;
    private String maLoaiSanPham;
    private String tenLoaiSanPham;
    private String tenMay;
    private int soLuong;
    private double gia;
    private double tiLeLai;
    private String xuatXu;
    private int trangThai;
    private List<ChiTietSanPham> chiTietSanPhamList;
    private String maNhaCungCap;
    private String tenNhaCungCap;

    public SanPhamDTO(String maMay, String maLoaiSanPham, String tenLoaiSanPham, String tenMay, int soLuong, double gia, double tiLeLai, String xuatXu, int trangThai, String maNhaCungCap, String tenNhaCungCap) {
        this.maMay = maMay;
        this.maLoaiSanPham = maLoaiSanPham;
        this.tenLoaiSanPham = tenLoaiSanPham;
        this.tenMay = tenMay;
        this.soLuong = soLuong;
        this.gia = gia;
        this.tiLeLai = tiLeLai;
        this.xuatXu = xuatXu;
        this.trangThai = trangThai;
        this.maNhaCungCap = maNhaCungCap;
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public SanPhamDTO(String maMay, String maLoaiSanPham, String tenLoaiSanPham, String tenMay, int soLuong, double gia, double tiLeLai, String xuatXu, int trangThai, List<ChiTietSanPham> chiTietSanPhamList, String maNhaCungCap, String tenNhaCungCap) {
        this.maMay = maMay;
        this.maLoaiSanPham = maLoaiSanPham;
        this.tenLoaiSanPham = tenLoaiSanPham;
        this.tenMay = tenMay;
        this.soLuong = soLuong;
        this.gia = gia;
        this.tiLeLai = tiLeLai;
        this.xuatXu = xuatXu;
        this.trangThai = trangThai;
        this.chiTietSanPhamList = chiTietSanPhamList;
        this.maNhaCungCap = maNhaCungCap;
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getMaMay() {
        return maMay;
    }

    public String getMaLoaiSanPham() {
        return maLoaiSanPham;
    }

    public String getTenLoaiSanPham() {
        return tenLoaiSanPham;
    }

    public String getTenMay() {
        return tenMay;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public double getGia() {
        return gia;
    }

    public double getTiLeLai() {
        return tiLeLai;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public List<ChiTietSanPham> getChiTietSanPhamList() {
        return chiTietSanPhamList;
    }

    public String getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }
    
}
