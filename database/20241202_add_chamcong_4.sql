--
-- Table structure for table `chamcong`
--
DROP TABLE IF EXISTS `chamcong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chamcong` (
  `maChamCong` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `ngayChamCong` varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `nguoiChamCong` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `gioVao` varchar(8) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `gioRa` varchar(8) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`maChamCong`),
  CONSTRAINT `FK_ChamCong_Account` FOREIGN KEY (`nguoiChamCong`) REFERENCES `account` (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;