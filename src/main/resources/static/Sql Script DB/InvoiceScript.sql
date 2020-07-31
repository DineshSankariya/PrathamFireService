CREATE TABLE `invoice` (
  `Invid` int(11) NOT NULL AUTO_INCREMENT,
  `Bank` varchar(45) DEFAULT NULL,
  `Item` varchar(45) DEFAULT NULL,
  `Code` varchar(45) DEFAULT NULL,
  `Capacity` int(11) DEFAULT NULL,
  `Rate` double DEFAULT NULL,
  `NOS` int(11) DEFAULT NULL,
  `Amount` double DEFAULT NULL,
  `Paymentstatus` varchar(45) DEFAULT NULL,
  `invoicedate` varchar(100) DEFAULT NULL,
  `c_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Invid`),
  KEY `cid_idx` (`c_id`),
  CONSTRAINT `cid` FOREIGN KEY (`c_id`) REFERENCES `client` (`cid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
