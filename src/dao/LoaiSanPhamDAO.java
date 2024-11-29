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
import model.LoaiSanPham;

/**
 *
 * @author GNT
 */
public class LoaiSanPhamDAO implements DAOInterface<LoaiSanPham> {

    private static LoaiSanPhamDAO instance;

    private LoaiSanPhamDAO() {
    }

    public static LoaiSanPhamDAO getInstance() {
        if (instance == null) {
            instance = new LoaiSanPhamDAO();
        }
        return instance;
    }

    @Override
    public int insert(LoaiSanPham t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO loaisanpham (maLoaiSanPham, tenLoaiSanPham) VALUES (?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaLoaiSanPham());
            pst.setString(2, t.getTenLoaiSanPham());
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "Không thêm được loại sản phẩm " + t.getMaLoaiSanPham(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int update(LoaiSanPham t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE loaisanpham SET tenLoaiSanPham=? WHERE maLoaiSanPham=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(2, t.getMaLoaiSanPham());
            pst.setString(1, t.getTenLoaiSanPham());
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "Không cập nhật được loại sản phẩm " + t.getMaLoaiSanPham(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int delete(LoaiSanPham t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM loaisanpham WHERE maLoaiSanPham=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaLoaiSanPham());
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "Không xóa được loại sản phẩm " + t.getMaLoaiSanPham(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public ArrayList<LoaiSanPham> selectAll() {
        ArrayList<LoaiSanPham> ketQua = new ArrayList<LoaiSanPham>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM loaisanpham";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maLoaiSanPham = rs.getString("maLoaiSanPham");
                String tenLoaiSanPham = rs.getString("tenLoaiSanPham");
                LoaiSanPham lsp = new LoaiSanPham(maLoaiSanPham, tenLoaiSanPham);
                ketQua.add(lsp);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public LoaiSanPham selectById(String t) {
        LoaiSanPham ketQua = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM loaisanpham WHERE maLoaiSanPham=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maLoaiSanPham = rs.getString("maLoaiSanPham");
                String tenLoaiSanPham = rs.getString("tenLoaiSanPham");
                ketQua = new LoaiSanPham(maLoaiSanPham, tenLoaiSanPham);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    public LoaiSanPham findByName(String t) {
        LoaiSanPham ketQua = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM loaisanpham WHERE tenLoaiSanPham=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maLoaiSanPham = rs.getString("maLoaiSanPham");
                String tenLoaiSanPham = rs.getString("tenLoaiSanPham");
                ketQua = new LoaiSanPham(maLoaiSanPham, tenLoaiSanPham);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    public String generateNewID() {
        String result = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = """
                SELECT CONCAT('LSP', CAST(SUBSTRING(maLoaiSanPham, 4) AS UNSIGNED) + 1) AS maLoaiSanPhamMoi
                FROM loaisanpham
                ORDER BY CAST(SUBSTRING(maLoaiSanPham, 4) AS UNSIGNED) DESC
                LIMIT 1;
            """;
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                result = rs.getString("maLoaiSanPhamMoi");
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }
    
}
