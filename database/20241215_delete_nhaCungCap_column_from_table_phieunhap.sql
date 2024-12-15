ALTER TABLE `phieunhap` 
DROP FOREIGN KEY `FK_PhieuNhap_NhaCungCap`;
ALTER TABLE `phieunhap` 
DROP COLUMN `maNhaCungCap`,
DROP INDEX `FK_PhieuNhap_NhaCungCap` ;
;
