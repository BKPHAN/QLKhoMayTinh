ALTER TABLE `phieunhap` 
DROP FOREIGN KEY `FK_PhieuNhap_NhaCungCap`;
ALTER TABLE `phieunhap` 
DROP COLUMN `maNhaCungCap`,
DROP INDEX `FK_PhieuNhap_NhaCungCap` ;
;


ALTER TABLE `quanlimaytinh`.`phieuxuat` 
ADD COLUMN `khachHang` VARCHAR(45) NULL AFTER `tongTien`,
ADD COLUMN `sdt` VARCHAR(45) NULL AFTER `khachHang`;
