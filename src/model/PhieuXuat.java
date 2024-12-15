/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Tran Nhat Sinh
 */
public class PhieuXuat extends Phieu{
    
    private String khachHang;
    private String sdt;

    public PhieuXuat() {
    }  

    public PhieuXuat(
            String maPhieu, 
            Timestamp thoiGianTao, 
            String nguoiTao, 
            ArrayList<ChiTietPhieu> CTPhieu, 
            double tongTien,
            String khachHang,
            String sdt
    ) {
        super(maPhieu, thoiGianTao, nguoiTao, CTPhieu, tongTien);
        this.khachHang = khachHang;
        this.sdt = sdt;
    }

    public String getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(String khachHang) {
        this.khachHang = khachHang;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    
    
}
