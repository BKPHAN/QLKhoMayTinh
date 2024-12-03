package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import database.JDBCUtil;
import dto.ChamCongDTO;
import model.ChamCong;

public class ChamCongDAO implements DAOInterface<ChamCong>{

    private static ChamCongDAO instance;

    private ChamCongDAO() {
    }

    public static ChamCongDAO getInstance() {
        if (instance == null) {
            instance = new ChamCongDAO();
        }
        return instance;
    }

    @Override
    public int insert(ChamCong t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = """
                INSERT INTO chamcong 
                (maChamCong, ngayChamCong, nguoiChamCong, gioVao, gioRa) 
                VALUES (?,?,?,?,?)
            """;
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaChamCong());
            pst.setString(2, t.getNgayChamCong());
            pst.setString(3, t.getNguoiChamCong());
            pst.setString(4, t.getGioVao());
            pst.setString(5, t.getGioRa());
            
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "Không thêm được chấm công " + t.getMaChamCong(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int update(ChamCong t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = """
                        UPDATE chamcong SET
                        gioVao=?, gioRa=?
                        WHERE maChamCong=?
                    """;
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getGioVao());
            pst.setString(2, t.getGioRa());
            pst.setString(3, t.getMaChamCong());

            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "Không cập nhật được thông tin chấm công " + t.getMaChamCong(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int delete(ChamCong t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = """
                        DELETE FROM chamcong
                        WHERE maChamCong=?
                    """;
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaChamCong());

            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "Không xóa được chấm công " + t.getMaChamCong(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public ArrayList<ChamCong> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ChamCong selectById(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<ChamCongDTO> findByNguoiChamCong(String maNguoiChamCongParam) {
        List<ChamCongDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "";
            sql += "SELECT c.*, a.fullName AS tenNguoiChamCong ";
            sql += "FROM chamcong c ";
            sql += "LEFT JOIN account a ON a.userName=c.nguoiChamCong ";
            sql += "WHERE a.status=1 AND c.nguoiChamCong like ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "%" + maNguoiChamCongParam + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maChamCong = rs.getString("maChamCong");
                String ngayChamCong = rs.getString("ngayChamCong");
                String maNguoiChamCong = rs.getString("nguoiChamCong");
                String tenNguoiChamCong = rs.getString("tenNguoiChamCong");
                String gioVao = rs.getString("gioVao");
                String gioRa = rs.getString("gioRa");
                ChamCongDTO cc = new ChamCongDTO(maChamCong, ngayChamCong, maNguoiChamCong, tenNguoiChamCong, gioVao,
                        gioRa);
                calcWorkTime(cc);
                result.add(cc);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }

    public List<ChamCongDTO> findByDate(Date fromDate, Date toDate) {
        List<ChamCongDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "";
            sql += "SELECT c.*, a.fullName AS tenNguoiChamCong ";
            sql += "FROM chamcong c ";
            sql += "LEFT JOIN account a ON a.userName=c.nguoiChamCong ";
            sql += "WHERE a.status=1 AND c.ngayChamCong BETWEEN ? AND ?";
            PreparedStatement pst = con.prepareStatement(sql);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            pst.setString(1, sdf.format(fromDate));
            pst.setString(2, sdf.format(toDate));
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maChamCong = rs.getString("maChamCong");
                String ngayChamCong = rs.getString("ngayChamCong");
                String maNguoiChamCong = rs.getString("nguoiChamCong");
                String tenNguoiChamCong = rs.getString("tenNguoiChamCong");
                String gioVao = rs.getString("gioVao");
                String gioRa = rs.getString("gioRa");
                ChamCongDTO cc = new ChamCongDTO(maChamCong, ngayChamCong, maNguoiChamCong, tenNguoiChamCong, gioVao,
                        gioRa);
                calcWorkTime(cc);
                result.add(cc);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }

    public ChamCong findByNgayChamCongAndNguoiChamCong(String ngayChamCongParam, String nguoiChamCongParam) {
        ChamCong result = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM chamcong WHERE ngayChamCong=? AND nguoiChamCong=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, ngayChamCongParam);
            pst.setString(2, nguoiChamCongParam);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maChamCong = rs.getString("maChamCong");
                String ngayChamCong = rs.getString("ngayChamCong");
                String nguoiChamCong = rs.getString("nguoiChamCong");
                String gioVao = rs.getString("gioVao");
                String gioRa = rs.getString("gioRa");
                
                result = new ChamCong(maChamCong, ngayChamCong, nguoiChamCong, gioVao, gioRa);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }

    public String chamcong(String maNguoiChamCong) {
        String result = "";

        try {
            // Lấy thời gian hiện tại
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            
            String today = now.format(dateFormatter);
            String time = now.format(timeFormatter);
            // Kiểm tra user đã chấm công ngày hôm nay chưa
            ChamCong checkExits = findByNgayChamCongAndNguoiChamCong(today, maNguoiChamCong);
            if (checkExits != null) {
                checkExits.setGioRa(time);
                update(checkExits);
            } else {
                checkExits = new ChamCong(getNewID(), today, maNguoiChamCong, time, null);
                insert(checkExits);
            }
            result = "OK";

        } catch (Exception e) {
            e.printStackTrace();
            result = "Đã xảy ra lỗi: " + e.getMessage();
        }
        return result;
    }

    public String getNewID() {
        String result = "CC0";
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = """
                        SELECT CONCAT('CC', CAST(SUBSTRING(maChamCong, 3) AS UNSIGNED) + 1) AS maChamCongMoi
                        FROM chamcong
                        ORDER BY CAST(SUBSTRING(maChamCong, 3) AS UNSIGNED) DESC
                        LIMIT 1;
                    """;
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                result = rs.getString("maChamCongMoi");
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }

    public List<ChamCongDTO> getAll() {
        List<ChamCongDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "";
            sql += "SELECT c.*, a.fullName AS tenNguoiChamCong ";
            sql += "FROM chamcong c ";
            sql += "LEFT JOIN account a ON a.userName=c.nguoiChamCong ";
            sql += "WHERE a.status=1";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maChamCong = rs.getString("maChamCong");
                String ngayChamCong = rs.getString("ngayChamCong");
                String maNguoiChamCong = rs.getString("nguoiChamCong");
                String tenNguoiChamCong = rs.getString("tenNguoiChamCong");
                String gioVao = rs.getString("gioVao");
                String gioRa = rs.getString("gioRa");
                ChamCongDTO cc = new ChamCongDTO(maChamCong, ngayChamCong, maNguoiChamCong, tenNguoiChamCong, gioVao, gioRa);
                calcWorkTime(cc);
                result.add(cc);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }

    void calcWorkTime(ChamCongDTO cc) {

        // Định dạng thời gian
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime workStart = LocalTime.parse(cc.getGioVao(), timeFormatter);
        LocalTime workEnd = LocalTime.parse(cc.getGioRa(), timeFormatter);

        // Thời gian ca hành chính
        LocalTime shiftStart = LocalTime.of(8, 0, 0);
        LocalTime shiftEnd = LocalTime.of(17, 0, 0);
        
        LocalTime lunchStart = LocalTime.of(12, 0, 0);
        LocalTime lunchEnd = LocalTime.of(13, 0, 0);

        // Tính toán thời gian làm việc trong ca hành chính
        LocalTime effectiveStart = workStart.isBefore(shiftStart) ? shiftStart : workStart;
        LocalTime effectiveEnd = workEnd.isAfter(shiftEnd) ? shiftEnd : workEnd;

        Duration workInShift = (workStart.isBefore(shiftEnd) && workEnd.isAfter(shiftStart)) 
                ? Duration.between(effectiveStart, effectiveEnd) 
                : Duration.ZERO;
        double workInShiftHours = workInShift.toMinutes() / 60.0;
        if (effectiveStart.isBefore(lunchStart) && effectiveEnd.isAfter(lunchEnd)) {
            workInShiftHours -= 1;
        }
        cc.setTrongGioHanhChinh(workInShiftHours);
        

        // Tính toán thời gian làm việc ngoài ca hành chính
        Duration workBeforeShift = workStart.isBefore(shiftStart) 
                ? Duration.between(workStart, shiftStart) 
                : Duration.ZERO;

        Duration workAfterShift = workEnd.isAfter(shiftEnd) 
                ? Duration.between(shiftEnd, workEnd) 
                : Duration.ZERO;

        Duration workOutOfShift;
        if (workStart.isBefore(shiftEnd)) {
            workOutOfShift = workBeforeShift.plus(workAfterShift);
        } else {
            workOutOfShift = Duration.between(workStart, workEnd);
        }
        
        double workOutOfShiftHours = workOutOfShift.toMinutes() / 60.0;
        cc.setNgoaiGioHanhChinh(workOutOfShiftHours);
        //
        cc.setTong(workInShiftHours + workOutOfShiftHours);
    }
    
}
