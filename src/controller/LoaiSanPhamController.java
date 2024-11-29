package controller;


import dao.LoaiSanPhamDAO;
import model.LoaiSanPham;

public class LoaiSanPhamController {
    private static LoaiSanPhamController instance;
    private LoaiSanPhamDAO lspDAO;

    private LoaiSanPhamController() {
        lspDAO = LoaiSanPhamDAO.getInstance();
    }

    public static LoaiSanPhamController getInstance() {
        if (instance == null) {
            instance = new LoaiSanPhamController();
        }
        return instance;
    }

    public int addNewLSP(String lsp) {
        LoaiSanPham newLsp = new LoaiSanPham(lspDAO.generateNewID(), lsp);
        return lspDAO.insert(newLsp);
    }
}
