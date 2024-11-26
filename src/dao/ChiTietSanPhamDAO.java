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
import model.ChiTietSanPham;

/**
 *
 * @author GNT
 */
public class ChiTietSanPhamDAO implements DAOInterface<ChiTietSanPham> {

    private static ChiTietSanPhamDAO instance;

    private ChiTietSanPhamDAO() {
    }

    public static ChiTietSanPhamDAO getInstance() {
        if (instance == null) {
            instance = new ChiTietSanPhamDAO();
        }
        return instance;
    }

    @Override
    public int insert(ChiTietSanPham t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO chitietsanpham (maChiTiet, maMay, tenThuocTinh, giaTriThuocTinh) VALUES (?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaChiTiet());
            pst.setString(2, t.getMaMay());
            pst.setString(3, t.getTenThuocTinh());
            pst.setString(4, t.getGiaTriThuocTinh());
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "Không thêm được chi tiết sản phẩm " + t.getMaChiTiet(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int update(ChiTietSanPham t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE chitietsanpham SET maMay=?, tenThuocTinh=?, giaTriThuocTinh=? WHERE maChiTiet=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(4, t.getMaChiTiet());
            pst.setString(1, t.getMaMay());
            pst.setString(2, t.getTenThuocTinh());
            pst.setString(3, t.getGiaTriThuocTinh());
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "Không cập nhật được chi tiết sản phẩm " + t.getMaChiTiet(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int delete(ChiTietSanPham t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM chitietsanpham WHERE maChiTiet=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaChiTiet());
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "Không xóa được chi tiết sản phẩm " + t.getMaChiTiet(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public ArrayList<ChiTietSanPham> selectAll() {
        ArrayList<ChiTietSanPham> ketQua = new ArrayList<ChiTietSanPham>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM chitietsanpham";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maChiTiet = rs.getString("maChiTiet");
                String maMay = rs.getString("maMay");
                String tenThuocTinh = rs.getString("tenThuocTinh");
                String giaTriThuocTinh = rs.getString("giaTriThuocTinh");
                ChiTietSanPham lsp = new ChiTietSanPham(maChiTiet, maMay, tenThuocTinh, giaTriThuocTinh);
                ketQua.add(lsp);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public ChiTietSanPham selectById(String t) {
        ChiTietSanPham ketQua = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM chitietsanpham WHERE maChiTiet=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maChiTiet = rs.getString("maChiTiet");
                String maMay = rs.getString("maMay");
                String tenThuocTinh = rs.getString("tenThuocTinh");
                String giaTriThuocTinh = rs.getString("giaTriThuocTinh");
                ketQua = new ChiTietSanPham(maChiTiet, maMay, tenThuocTinh, giaTriThuocTinh);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }
    
}
