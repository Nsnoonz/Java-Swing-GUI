-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 19, 2021 at 11:36 AM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.3.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `62011270006`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `categoryID` int(2) NOT NULL,
  `categoryName` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`categoryID`, `categoryName`) VALUES
(1, 'FILA'),
(2, 'NIKE');

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE `invoice` (
  `invoiceID` int(3) NOT NULL,
  `invoiceDate` timestamp NULL DEFAULT NULL,
  `invoiceTotal` int(11) DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `supplierID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`invoiceID`, `invoiceDate`, `invoiceTotal`, `total_price`, `status`, `supplierID`) VALUES
(1, '2021-04-16 17:26:21', 10, 1200, 0, 1),
(2, '2021-04-19 04:27:46', 10, 1200, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `invoice_details`
--

CREATE TABLE `invoice_details` (
  `invoiceDetails` int(11) NOT NULL,
  `Quantity` int(11) DEFAULT NULL,
  `Amount` double DEFAULT NULL,
  `invoiceID` int(3) DEFAULT NULL,
  `productID` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `invoice_details`
--

INSERT INTO `invoice_details` (`invoiceDetails`, `Quantity`, `Amount`, `invoiceID`, `productID`) VALUES
(1, 10, 1200, 1, 1),
(2, 10, 1200, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE `member` (
  `memberID` int(11) NOT NULL,
  `memName` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `memSur` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Phone` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Email` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Password` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Address` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`memberID`, `memName`, `memSur`, `Phone`, `Email`, `Password`, `Address`) VALUES
(1, 'noon', 'nsjd', '0945530050', 'nsj@noon.com', NULL, 'heart'),
(2, 'Tiffany', 'Hwang', '0945530050', 'xo_lovephani@SNSD', '17206494', '528 ม.1 ห้อง 6 หอพักรุ่งนภา ถนนมหาลัยมหาสารคาม');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `productID` int(3) NOT NULL,
  `productName` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `productDetails` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  `productTime` timestamp NULL DEFAULT current_timestamp(),
  `unitcost` double DEFAULT NULL,
  `unitprice` double DEFAULT NULL,
  `amount` int(3) DEFAULT NULL,
  `categoryID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`productID`, `productName`, `productDetails`, `productTime`, `unitcost`, `unitprice`, `amount`, `categoryID`) VALUES
(1, 'hot bucket', 'yellow cream', '2021-04-16 17:23:45', 120, 250, 25, 1);

-- --------------------------------------------------------

--
-- Table structure for table `receipt`
--

CREATE TABLE `receipt` (
  `receiptID` int(11) NOT NULL,
  `Date` timestamp NULL DEFAULT NULL,
  `Total` int(11) DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `memberID` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `receipt`
--

INSERT INTO `receipt` (`receiptID`, `Date`, `Total`, `total_price`, `memberID`) VALUES
(1, '2021-04-16 18:12:40', 1, 250, 2),
(2, '2021-04-19 04:29:20', 1, 250, 2),
(3, '2021-04-19 04:30:43', 5, 1250, 2);

-- --------------------------------------------------------

--
-- Table structure for table `receiptdetails`
--

CREATE TABLE `receiptdetails` (
  `receiptDetails` int(11) NOT NULL,
  `Quantity` int(11) DEFAULT NULL,
  `Amount` double DEFAULT NULL,
  `receiptID` int(11) DEFAULT NULL,
  `productID` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `receiptdetails`
--

INSERT INTO `receiptdetails` (`receiptDetails`, `Quantity`, `Amount`, `receiptID`, `productID`) VALUES
(1, 1, 250, 1, 1),
(2, 1, 250, 2, 1),
(3, 5, 1250, 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `stock`
--

CREATE TABLE `stock` (
  `stockID` int(11) NOT NULL,
  `supplierID` int(11) DEFAULT NULL,
  `productID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `stock`
--

INSERT INTO `stock` (`stockID`, `supplierID`, `productID`) VALUES
(1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

CREATE TABLE `suppliers` (
  `supplierID` int(8) NOT NULL,
  `supplierName` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `supplierAddress` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Phone` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Email` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `suppliers`
--

INSERT INTO `suppliers` (`supplierID`, `supplierName`, `supplierAddress`, `Phone`, `Email`) VALUES
(1, 'SNSD', 'warehouse1', '99999999999', 'Snsd9@gamil.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`categoryID`);

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`invoiceID`);

--
-- Indexes for table `invoice_details`
--
ALTER TABLE `invoice_details`
  ADD PRIMARY KEY (`invoiceDetails`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`memberID`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`productID`);

--
-- Indexes for table `receipt`
--
ALTER TABLE `receipt`
  ADD PRIMARY KEY (`receiptID`);

--
-- Indexes for table `receiptdetails`
--
ALTER TABLE `receiptdetails`
  ADD PRIMARY KEY (`receiptDetails`);

--
-- Indexes for table `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`stockID`);

--
-- Indexes for table `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`supplierID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `categoryID` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `invoice`
--
ALTER TABLE `invoice`
  MODIFY `invoiceID` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `invoice_details`
--
ALTER TABLE `invoice_details`
  MODIFY `invoiceDetails` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `memberID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `productID` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `receipt`
--
ALTER TABLE `receipt`
  MODIFY `receiptID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `receiptdetails`
--
ALTER TABLE `receiptdetails`
  MODIFY `receiptDetails` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `stock`
--
ALTER TABLE `stock`
  MODIFY `stockID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `supplierID` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
