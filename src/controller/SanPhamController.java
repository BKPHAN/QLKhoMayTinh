package controller;

import java.util.ArrayList;

import dao.SanPhamDAO;
import dto.SanPhamDTO;;

public class SanPhamController {
    private static SanPhamController instance;
    private SanPhamDAO spDAO;

    private SanPhamController() {
        spDAO = SanPhamDAO.getInstance();
    }

    public static SanPhamController getInstance() {
        if (instance == null) {
            instance = new SanPhamController();
        }
        return instance;
    }

    public ArrayList<SanPhamDTO> searchAll(String keyword) {
        return spDAO.searchAll(keyword);
    }

    public ArrayList<SanPhamDTO> searchByAttribute(String attribute, String keyword) {
        return spDAO.searchByAttribute(attribute, keyword);
    }

    public SanPhamDTO getChiTietSanPham(String maSanPham) {
        return spDAO.getChiTietSanPham(maSanPham);
    }
}
