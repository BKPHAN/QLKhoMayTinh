ALTER TABLE `chitietphieunhap` 
DROP FOREIGN KEY `FK_ChiTietPhieuNhap_MayTinh`;
ALTER TABLE `chitietphieunhap` 
DROP INDEX `FK_ChiTietPhieuNhap_MayTinh` ;

ALTER TABLE `chitietphieuxuat` 
DROP FOREIGN KEY `FK_ChiTietPhieuXuat_MayTinh`;
ALTER TABLE `chitietphieuxuat` 
DROP INDEX `FK_ChiTietPhieuXuat_MayTinh` ;

ALTER TABLE `chitietphieunhap` 
ADD CONSTRAINT `FK_ChiTietPhieuNhap_SanPham`
  FOREIGN KEY (`maMay`)
  REFERENCES `sanpham` (`maMay`);

ALTER TABLE `chitietphieuxuat` 
ADD CONSTRAINT `FK_ChiTietPhieuXuat_SanPham`
  FOREIGN KEY (`maMay`)
  REFERENCES `sanpham` (`maMay`);

DROP TABLE `quanlimaytinh`.`maytinh`;