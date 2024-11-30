package controller;

import dao.MayTinhDAO;
import dao.SanPhamDAO;
import java.util.ArrayList;
import model.MayTinh;
import model.SanPham;

/**
 *
 * @author sinh
 */
public class SearchProduct {

    public static SearchProduct getInstance() {
        return new SearchProduct();
    }

    public ArrayList<SanPham> searchTatCa(String text) {
        ArrayList<SanPham> result = new ArrayList<>();
        ArrayList<SanPham> armt = SanPhamDAO.getInstance().selectAll();
        String searchText = text.toLowerCase(); // Chuyển input tìm kiếm về chữ thường
        for (var sp : armt) {
            if (sp.getTrangThai() == 1) {
                // Gộp tất cả các trường thành một chuỗi sử dụng concat
                String combinedFields = sp.getMaMay()
                                          .concat(" ")
                                          .concat(sp.getTenMay())
                                          .concat(" ")
                                          .concat(String.valueOf(sp.getSoLuong()))
                                          .concat(" ")
                                          .concat(String.valueOf((int) sp.getGia()))
                                          .toLowerCase();
                if (combinedFields.contains(searchText)) {
                    result.add(sp);
                }
            }
        }
        return result;
    }
    

    public ArrayList<SanPham> searchMaMay(String text) {
        ArrayList<SanPham> result = new ArrayList<>();
        ArrayList<SanPham> armt = SanPhamDAO.getInstance().selectAll();
        for (var sp : armt) {
            if (sp.getTrangThai() == 1) {
                if (sp.getMaMay().toLowerCase().contains(text.toLowerCase())) {
                    result.add(sp);
                }
            }
        }
        return result;
    }

    public ArrayList<SanPham> searchTenMay(String text) {
        ArrayList<SanPham> result = new ArrayList<>();
        ArrayList<SanPham> armt = SanPhamDAO.getInstance().selectAll();
        for (var sp : armt) {
            if (sp.getTrangThai() == 1) {
                if (sp.getTenMay().toLowerCase().contains(text.toLowerCase())) {
                    result.add(sp);
                }
            }
        }
        return result;
    }

    public ArrayList<SanPham> searchSoLuong(String text) {
        ArrayList<SanPham> result = new ArrayList<>();
        ArrayList<SanPham> armt = SanPhamDAO.getInstance().selectAll();
        for (var sp : armt) {
            if (sp.getTrangThai() == 1) {
                if (text.length() != 0) {
                    if (sp.getSoLuong() > Integer.parseInt(text)) {
                        result.add(sp);
                    }
                } else {
                    result.add(sp);
                }
            }
        }
        return result;
    }

    public ArrayList<SanPham> searchDonGia(String text) {
        ArrayList<SanPham> result = new ArrayList<>();
        ArrayList<SanPham> armt = SanPhamDAO.getInstance().selectAll();
        for (var sp : armt) {
            if (sp.getTrangThai() == 1) {

                if (text.length() != 0) {
                    if (sp.getGia() > Integer.parseInt(text)) {
                        result.add(sp);
                    }
                }
                else {
                    result.add(sp);
                }
            } 
        }
        return result;
    }

    public ArrayList<MayTinh> searchDungLuong(String text) {
        ArrayList<MayTinh> result = new ArrayList<>();
        ArrayList<MayTinh> armt = MayTinhDAO.getInstance().selectAllExist();
        for (var mt : armt) {
            if (mt.getRom().toLowerCase().contains(text.toLowerCase())) {
                result.add(mt);
            }
        }
        return result;
    }

//    public ArrayList<MayTinh> searchXuatXu(String text) {
//        ArrayList<MayTinh> result = new ArrayList<>();
//        ArrayList<MayTinh> armt = MayTinhDAO.getInstance().selectAllExist();
//        for (var mt : armt) {
//            if (mt.getXuatXu().toLowerCase().contains(text.toLowerCase())) {
//                result.add(mt);
//            }
//        }
//        return result;
//    }


//    public MayTinh searchId(String text) {
//        MayTinh result = new MayTinh();
//        ArrayList<MayTinh> armt = MayTinhDAO.getInstance().selectAllExist();
//        for (var mt : armt) {
//            if (mt.getMaMay().toLowerCase().contains(text.toLowerCase())) {
//                return mt;
//            }
//        }
//        return null;
//    }
}
