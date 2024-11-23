-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: quanlimaytinh
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `fullName` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `userName` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(60) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `role` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` int DEFAULT NULL,
  `email` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`userName`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `chamcong`
--
DROP TABLE IF EXISTS `chamcong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chamcong` (
  `maChamCong` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `ngayChamCong` timestamp NULL DEFAULT NULL,
  `nguoiChamCong` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `gioVao` timestamp NULL DEFAULT NULL,
  `gioRa` timestamp NULL DEFAULT NULL,
  `gioLamThem` int NULL DEFAULT 0,
  PRIMARY KEY (`maChamCong`),
  CONSTRAINT `FK_ChamCong_Account` FOREIGN KEY (`nguoiChamCong`) REFERENCES `account` (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `chitietphieunhap`
--

DROP TABLE IF EXISTS `chitietphieunhap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitietphieunhap` (
  `maPhieu` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `maMay` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `soLuong` int DEFAULT NULL,
  `donGia` double DEFAULT NULL,
  PRIMARY KEY (`maPhieu`,`maMay`),
  KEY `FK_ChiTietPhieuNhap_SanPham` (`maMay`),
  CONSTRAINT `FK_ChiTietPhieuNhap_SanPham` FOREIGN KEY (`maMay`) REFERENCES `sanpham` (`maMay`),
  CONSTRAINT `FK_ChiTietPhieuNhap_PhieuNhap` FOREIGN KEY (`maPhieu`) REFERENCES `phieunhap` (`maPhieu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `chitietphieuxuat`
--

DROP TABLE IF EXISTS `chitietphieuxuat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitietphieuxuat` (
  `maPhieu` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `maMay` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `soLuong` int DEFAULT NULL,
  `donGia` double DEFAULT NULL,
  PRIMARY KEY (`maPhieu`,`maMay`),
  KEY `FK_ChiTietPhieuXuat_SanPham` (`maMay`),
  CONSTRAINT `FK_ChiTietPhieuXuat_SanPham` FOREIGN KEY (`maMay`) REFERENCES `sanpham` (`maMay`),
  CONSTRAINT `FK_ChiTietPhieuXuat_PhieuXuat` FOREIGN KEY (`maPhieu`) REFERENCES `phieuxuat` (`maPhieu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sanpham`
--


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

--
-- Table structure for table `nhacungcap`
--

DROP TABLE IF EXISTS `nhacungcap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhacungcap` (
  `maNhaCungCap` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `tenNhaCungCap` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Sdt` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `diaChi` varchar(150) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`maNhaCungCap`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `phieunhap`
--

DROP TABLE IF EXISTS `phieunhap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phieunhap` (
  `maPhieu` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `thoiGianTao` timestamp NULL DEFAULT NULL,
  `nguoiTao` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `maNhaCungCap` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `tongTien` double NOT NULL,
  PRIMARY KEY (`maPhieu`),
  KEY `FK_PhieuNhap_NhaCungCap` (`maNhaCungCap`),
  KEY `FK_PhieuNhap_Account` (`nguoiTao`),
  CONSTRAINT `FK_PhieuNhap_Account` FOREIGN KEY (`nguoiTao`) REFERENCES `account` (`userName`),
  CONSTRAINT `FK_PhieuNhap_NhaCungCap` FOREIGN KEY (`maNhaCungCap`) REFERENCES `nhacungcap` (`maNhaCungCap`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `phieuxuat`
--

DROP TABLE IF EXISTS `phieuxuat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phieuxuat` (
  `maPhieu` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `thoiGianTao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `nguoiTao` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `tongTien` double NOT NULL,
  PRIMARY KEY (`maPhieu`),
  KEY `FK_PhieuXuat_Account` (`nguoiTao`),
  CONSTRAINT `FK_PhieuXuat_Account` FOREIGN KEY (`nguoiTao`) REFERENCES `account` (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-22 13:57:19

