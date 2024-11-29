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

    public SanPhamDTO() {
    }

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

    public void setMaMay(String maMay) {
        this.maMay = maMay;
    }

    public void setMaLoaiSanPham(String maLoaiSanPham) {
        this.maLoaiSanPham = maLoaiSanPham;
    }

    public void setTenLoaiSanPham(String tenLoaiSanPham) {
        this.tenLoaiSanPham = tenLoaiSanPham;
    }

    public void setTenMay(String tenMay) {
        this.tenMay = tenMay;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public void setTiLeLai(double tiLeLai) {
        this.tiLeLai = tiLeLai;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public void setChiTietSanPhamList(List<ChiTietSanPham> chiTietSanPhamList) {
        this.chiTietSanPhamList = chiTietSanPhamList;
    }

    public void setMaNhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }
    
    
}
