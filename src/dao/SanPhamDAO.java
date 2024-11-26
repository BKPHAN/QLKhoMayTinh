/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import database.JDBCUtil;
import model.SanPham;

/**
 *
 * @author GNT
 */
public class SanPhamDAO implements DAOInterface<SanPham> {

    private static SanPhamDAO instance;

    private SanPhamDAO() {
    }

    public static SanPhamDAO getInstance() {
        if (instance == null) {
            instance = new SanPhamDAO();
        }
        return instance;
    }

    @Override
    public int insert(SanPham t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO sanpham (maMay, loaiMay, tenMay, soLuong, gia, tiLeLai, xuatXu, trangThai) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaMay());
            pst.setString(2, t.getLoaiMay());
            pst.setString(3, t.getTenMay());
            pst.setInt(4, t.getSoLuong());
            pst.setDouble(5, t.getGia());
            pst.setDouble(6, t.getTiLeLai());
            pst.setString(7, t.getXuatXu());
            pst.setInt(8, t.getTrangThai());
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "Không thêm được sản phẩm " + t.getMaMay(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int update(SanPham t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE sanpham SET loaiMay=?, tenMay=?, soLuong=?, gia=?, tiLeLai=?, xuatXu=?, trangThai=? WHERE maMay=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(8, t.getMaMay());
            pst.setString(1, t.getLoaiMay());
            pst.setString(2, t.getTenMay());
            pst.setInt(3, t.getSoLuong());
            pst.setDouble(4, t.getGia());
            pst.setDouble(5, t.getTiLeLai());
            pst.setString(6, t.getXuatXu());
            pst.setInt(7, t.getTrangThai());
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "Không cập nhật được sản phẩm " + t.getMaMay(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int delete(SanPham t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM sanpham WHERE maMay=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaMay());
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "Không xóa được sản phẩm " + t.getMaMay(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public ArrayList<SanPham> selectAll() {
        ArrayList<SanPham> ketQua = new ArrayList<SanPham>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM sanpham";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maMay = rs.getString("maMay");
                String loaiMay = rs.getString("loaiMay");
                String tenMay = rs.getString("tenMay");
                int soLuong = rs.getInt("soLuong");
                double gia = rs.getDouble("gia");
                double tiLeLai = rs.getDouble("tiLeLai");
                String xuatXu = rs.getString("xuatXu");
                int trangThai = rs.getInt("trangThai");
                SanPham sp = new SanPham(maMay, loaiMay, tenMay, soLuong, gia, tiLeLai, xuatXu, trangThai);
                ketQua.add(sp);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public SanPham selectById(String t) {
        SanPham ketQua = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM sanpham WHERE maMay=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maMay = rs.getString("maMay");
                String loaiMay = rs.getString("loaiMay");
                String tenMay = rs.getString("tenMay");
                int soLuong = rs.getInt("soLuong");
                double gia = rs.getDouble("gia");
                double tiLeLai = rs.getDouble("tiLeLai");
                String xuatXu = rs.getString("xuatXu");
                int trangThai = rs.getInt("trangThai");
                ketQua = new SanPham(maMay, loaiMay, tenMay, soLuong, gia, tiLeLai, xuatXu, trangThai);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

}
