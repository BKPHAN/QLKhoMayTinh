DROP TABLE IF EXISTS `loaisanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loaisanpham` (
  `maLoaiSanPham` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `tenLoaiSanPham` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`maLoaiSanPham`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `sanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sanpham` (
  `maMay` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `loaiMay` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `tenMay` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `soLuong` int NOT NULL DEFAULT '0',
  `gia` double NOT NULL DEFAULT '0',
  `tiLeLai` double NOT NULL DEFAULT '0',
  `xuatXu` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `trangThai` int DEFAULT NULL,
  PRIMARY KEY (`maMay`),
  CONSTRAINT `FK_SanPham_LoaiSanPham` FOREIGN KEY (`loaiMay`) REFERENCES `loaisanpham` (`maLoaiSanPham`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `chitietsanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitietsanpham` (
  `maChiTiet` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `maMay` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `tenThuocTinh` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `giaTriThuocTinh` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`maChiTiet`),
  CONSTRAINT `FK_ChiTietSanPham_SanPham` FOREIGN KEY (`maMay`) REFERENCES `sanpham` (`maMay`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;