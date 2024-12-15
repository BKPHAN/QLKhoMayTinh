/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Tran Nhat Sinh
 */
public class PhieuNhap extends Phieu {


    public PhieuNhap() {
    }

    public PhieuNhap(String maPhieu, Timestamp thoiGianTao, String nguoiTao, ArrayList<ChiTietPhieu> CTPhieu, double tongTien) {
        super(maPhieu, thoiGianTao, nguoiTao, CTPhieu, tongTien);
    }

    @Override
    public String toString() {
        return "PhieuNhap{" + "maPhieu"+ this.getMaPhieu()+'}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PhieuNhap other = (PhieuNhap) obj;
        return Objects.equals(this.getMaPhieu(), other.getMaPhieu()) && Double.doubleToLongBits(this.getTongTien()) != Double.doubleToLongBits(other.getTongTien()
        );
    }
    

    
}
