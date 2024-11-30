/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import database.JDBCUtil;
import dto.SanPhamDTO;
import model.ChiTietSanPham;
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
                String maNhaCungCap = rs.getString("maNhaCungCap");
                SanPham sp = new SanPham(maMay, loaiMay, tenMay, soLuong, gia, tiLeLai, xuatXu, trangThai, maNhaCungCap);
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
                String maNhaCungCap = rs.getString("maNhaCungCap");
                ketQua = new SanPham(maMay, loaiMay, tenMay, soLuong, gia, tiLeLai, xuatXu, trangThai, maNhaCungCap);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    public ArrayList<SanPhamDTO> searchAll(String keyword) {
        ArrayList<SanPhamDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "";
            sql = "SELECT s.*, l.tenLoaiSanPham, n.tenNhaCungCap ";
            sql += "FROM sanpham s ";
            sql += "LEFT JOIN loaisanpham l ON s.loaiMay=l.maLoaiSanPham ";
            sql += "LEFT JOIN nhacungcap n ON s.maNhaCungCap=n.maNhaCungCap ";
            sql += "WHERE tenLoaiSanPham LIKE ? ";
            sql += "OR maMay LIKE ? ";
            sql += "OR tenMay LIKE ? ";
            sql += "OR soLuong LIKE ? ";
            sql += "OR gia LIKE ? ";
            sql += "OR xuatXu LIKE ? ";
            sql += "OR trangThai LIKE ? ";
            sql += "OR tenNhaCungCap LIKE ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "%" + keyword + "%");
            pst.setString(2, "%" + keyword + "%");
            pst.setString(3, "%" + keyword + "%");
            pst.setString(4, "%" + keyword + "%");
            pst.setString(5, "%" + keyword + "%");
            pst.setString(6, "%" + keyword + "%");
            pst.setString(7, "%" + keyword + "%");
            pst.setString(8, "%" + keyword + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maMay = rs.getString("maMay");
                String maLoaiSanPham = rs.getString("loaiMay");
                String tenLoaiSanPham = rs.getString("tenLoaiSanPham");
                String tenMay = rs.getString("tenMay");
                int soLuong = rs.getInt("soLuong");
                double gia = rs.getDouble("gia");
                double tiLeLai = rs.getDouble("tiLeLai");
                String xuatXu = rs.getString("xuatXu");
                int trangThai = rs.getInt("trangThai");
                String maNhaCungCap = rs.getString("maNhaCungCap");
                String tenNhaCungCap = rs.getString("tenNhaCungCap");
                SanPhamDTO sp = new SanPhamDTO(
                    maMay, maLoaiSanPham, tenLoaiSanPham, tenMay, 
                    soLuong, gia, tiLeLai, xuatXu, trangThai, maNhaCungCap, tenNhaCungCap
                );
                result.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<SanPhamDTO> searchByAttribute(String attribute, String keyword) {
        ArrayList<SanPhamDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "";
            sql = "SELECT s.*, l.tenLoaiSanPham, n.tenNhaCungCap ";
            sql += "FROM sanpham s ";
            sql += "LEFT JOIN loaisanpham l ON s.loaiMay=l.maLoaiSanPham ";
            sql += "LEFT JOIN nhacungcap n ON s.maNhaCungCap=n.maNhaCungCap ";
            sql += "WHERE " + attribute + " LIKE ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "%" + keyword + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maMay = rs.getString("maMay");
                String maLoaiSanPham = rs.getString("loaiMay");
                String tenLoaiSanPham = rs.getString("tenLoaiSanPham");
                String tenMay = rs.getString("tenMay");
                int soLuong = rs.getInt("soLuong");
                double gia = rs.getDouble("gia");
                double tiLeLai = rs.getDouble("tiLeLai");
                String xuatXu = rs.getString("xuatXu");
                int trangThai = rs.getInt("trangThai");
                String maNhaCungCap = rs.getString("maNhaCungCap");
                String tenNhaCungCap = rs.getString("tenNhaCungCap");
                SanPhamDTO sp = new SanPhamDTO(
                    maMay, maLoaiSanPham, tenLoaiSanPham, tenMay,
                    soLuong, gia, tiLeLai, xuatXu, trangThai, maNhaCungCap, tenNhaCungCap
                );
                result.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public SanPhamDTO getChiTietSanPham(String maSanPham) {
        SanPhamDTO result = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "";
            sql = "SELECT s.*, l.tenLoaiSanPham, n.tenNhaCungCap ";
            sql += "FROM sanpham s ";
            sql += "LEFT JOIN loaisanpham l ON s.loaiMay=l.maLoaiSanPham ";
            sql += "LEFT JOIN nhacungcap n ON s.maNhaCungCap=n.maNhaCungCap ";
            sql += "WHERE maMay=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, maSanPham);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maMay = rs.getString("maMay");
                String maLoaiSanPham = rs.getString("loaiMay");
                String tenLoaiSanPham = rs.getString("tenLoaiSanPham");
                String tenMay = rs.getString("tenMay");
                int soLuong = rs.getInt("soLuong");
                double gia = rs.getDouble("gia");
                double tiLeLai = rs.getDouble("tiLeLai");
                String xuatXu = rs.getString("xuatXu");
                int trangThai = rs.getInt("trangThai");
                String maNhaCungCap = rs.getString("maNhaCungCap");
                String tenNhaCungCap = rs.getString("tenNhaCungCap");
                result = new SanPhamDTO(
                    maMay, maLoaiSanPham, tenLoaiSanPham, tenMay,
                    soLuong, gia, tiLeLai, xuatXu, trangThai,
                    ChiTietSanPhamDAO.getInstance().getChiTietSanPhamByMaSanPham(maSanPham),
                    maNhaCungCap, tenNhaCungCap
                );
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }

    public String getNewID() {
        String result = "SP0";
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = """
                SELECT CONCAT('SP', CAST(SUBSTRING(maMay, 3) AS UNSIGNED) + 1) AS maSanPhamMoi
                FROM sanpham
                ORDER BY CAST(SUBSTRING(maMay, 3) AS UNSIGNED) DESC
                LIMIT 1;
            """;
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                result = rs.getString("maSanPhamMoi");
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }

    public String addNewSanPham(SanPhamDTO spMoi) {
        String result = "";
        try {
            String newID = getNewID();
            SanPham sp = new SanPham(
                    newID,
                    spMoi.getMaLoaiSanPham(),
                    spMoi.getTenMay(),
                    spMoi.getSoLuong(),
                    spMoi.getGia(),
                    spMoi.getTiLeLai(),
                    spMoi.getXuatXu(),
                    1,
                    spMoi.getMaNhaCungCap());
            int insertSPResult = insert(sp);
            if (insertSPResult != 1) {
                return "Đã xảy ra lỗi khi thêm sản phẩm mới";
            }

            // Them chi tiet san pham
            List<ChiTietSanPham> chiTietSanPhamList = spMoi.getChiTietSanPhamList();
            if (chiTietSanPhamList.size() > 0) {
                int firstIndex = ChiTietSanPhamDAO.getInstance().getNewIndexID();
                for (int i = 0; i < chiTietSanPhamList.size(); i++) {
                    ChiTietSanPhamDAO.getInstance().insert(new ChiTietSanPham(
                        "CTSP" + (firstIndex + i),
                        newID,
                        chiTietSanPhamList.get(i).getTenThuocTinh(),
                        chiTietSanPhamList.get(i).getGiaTriThuocTinh()
                    ));
                }
            }

            result = "OK";

        } catch (Exception e) {
            e.printStackTrace();
            result = "Error: " + e.getMessage();
        }
        
        return result;
    }

    public String updateSanPham(SanPhamDTO spMoi) {
        String result = "";
        try {
            SanPham sp = new SanPham(
                    spMoi.getMaMay(),
                    spMoi.getMaLoaiSanPham(),
                    spMoi.getTenMay(),
                    spMoi.getSoLuong(),
                    spMoi.getGia(),
                    spMoi.getTiLeLai(),
                    spMoi.getXuatXu(),
                    1,
                    spMoi.getMaNhaCungCap());
            int updateSPResult = update(sp);
            if (updateSPResult != 1) {
                return "Đã xảy ra lỗi khi cập nhật thông tin sản phẩm!";
            }

            // Them chi tiet san pham
            List<ChiTietSanPham> chiTietSanPhamList = spMoi.getChiTietSanPhamList();
            if (chiTietSanPhamList.size() > 0) {
                ChiTietSanPhamDAO ctspDao = ChiTietSanPhamDAO.getInstance();
                for (int i = 0; i < chiTietSanPhamList.size(); i++) {
                    ChiTietSanPham checkExist = ctspDao.findByMaMayAndTenThuocTinh(
                        spMoi.getMaMay(), 
                        chiTietSanPhamList.get(i).getTenThuocTinh()
                    );
                    if (checkExist != null) {
                        if (chiTietSanPhamList.get(i).getGiaTriThuocTinh().isBlank()) {
                            ctspDao.delete(checkExist);
                        } else {
                            checkExist.setGiaTriThuocTinh(chiTietSanPhamList.get(i).getGiaTriThuocTinh());
                            ctspDao.update(checkExist);
                        }
                    } else {
                        String newID = "CTSP" + String.valueOf(ctspDao.getNewIndexID());
                        ctspDao.insert(new ChiTietSanPham(
                            newID,
                            spMoi.getMaMay(),
                            chiTietSanPhamList.get(i).getTenThuocTinh(),
                            chiTietSanPhamList.get(i).getGiaTriThuocTinh()
                        ));
                    }
                }
            }

            result = "OK";

        } catch (Exception e) {
            e.printStackTrace();
            result = "Error: " + e.getMessage();
        }

        return result;
    }

    public int updateTrangThai(String maMay, int trangThai) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE sanpham SET trangThai=? WHERE maMay=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(2, maMay);
            pst.setInt(1, trangThai);
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "Không cập nhật được sản phẩm " + maMay, "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return ketQua;
    }
}
