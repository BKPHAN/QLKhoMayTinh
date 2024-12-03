ALTER TABLE `sanpham` 
ADD COLUMN `maNhaCungCap` VARCHAR(50) NOT NULL DEFAULT 'FPT' AFTER `trangThai`;
ALTER TABLE `sanpham` 
ADD CONSTRAINT `FK_SanPham_NhaCungCap`
  FOREIGN KEY (`maNhaCungCap`)
  REFERENCES `nhacungcap` (`maNhaCungCap`);