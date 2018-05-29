-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.5.28 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 growthrecord 的数据库结构
DROP DATABASE IF EXISTS `growthrecord`;
CREATE DATABASE IF NOT EXISTS `growthrecord` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `growthrecord`;


-- 导出  表 growthrecord.admin 结构
DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `password` varchar(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 正在导出表  growthrecord.admin 的数据：~1 rows (大约)
DELETE FROM `admin`;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` (`id`, `password`) VALUES
	(1, 'admin');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;


-- 导出  表 growthrecord.bodyrecord 结构
DROP TABLE IF EXISTS `bodyrecord`;
CREATE TABLE IF NOT EXISTS `bodyrecord` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` int(10) unsigned NOT NULL COMMENT '生理记录的类型, 0: 身高, 1: 体重, 2: 第一次, 3: 其它',
  `note` varchar(150) NOT NULL COMMENT '该类型数据',
  `recordDate` date NOT NULL COMMENT '时间',
  `kidId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bodyrecord_kid` (`kidId`),
  CONSTRAINT `FK_bodyrecord_kid` FOREIGN KEY (`kidId`) REFERENCES `kid` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8 COMMENT='生理记录表';

-- 正在导出表  growthrecord.bodyrecord 的数据：~27 rows (大约)
DELETE FROM `bodyrecord`;
/*!40000 ALTER TABLE `bodyrecord` DISABLE KEYS */;
INSERT INTO `bodyrecord` (`id`, `type`, `note`, `recordDate`, `kidId`) VALUES
	(31, 0, '97', '1995-01-01', 10),
	(32, 0, '100', '1995-06-01', 10),
	(33, 0, '104', '1996-01-01', 10),
	(34, 0, '108', '1996-06-01', 10),
	(35, 0, '111', '1997-01-01', 10),
	(36, 0, '114', '1997-06-01', 10),
	(37, 0, '117', '1998-01-01', 10),
	(38, 0, '120', '1998-06-01', 10),
	(39, 0, '124', '1999-01-01', 10),
	(40, 0, '127', '1999-06-01', 10),
	(41, 0, '130', '2000-01-01', 10),
	(42, 0, '133', '2000-06-01', 10),
	(43, 0, '135', '2001-01-01', 10),
	(44, 0, '138', '2001-06-01', 10),
	(45, 0, '140', '2002-01-01', 10),
	(46, 0, '143', '2002-06-01', 10),
	(47, 0, '145', '2003-01-01', 10),
	(48, 0, '148', '2003-06-01', 10),
	(49, 0, '151', '2004-01-01', 10),
	(50, 0, '155', '2004-06-01', 10),
	(51, 0, '160', '2005-01-01', 10),
	(52, 0, '163', '2005-06-01', 10),
	(53, 0, '166', '2006-01-01', 10),
	(54, 0, '168', '2006-06-01', 10),
	(55, 0, '170', '2007-01-01', 10),
	(56, 0, '171', '2007-06-01', 10),
	(57, 0, '172', '2008-01-01', 10);
/*!40000 ALTER TABLE `bodyrecord` ENABLE KEYS */;


-- 导出  表 growthrecord.figurerecord 结构
DROP TABLE IF EXISTS `figurerecord`;
CREATE TABLE IF NOT EXISTS `figurerecord` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `recordDate` date NOT NULL,
  `note` varchar(250) NOT NULL,
  `realFileName` varchar(250) NOT NULL,
  `sourceFileName` varchar(250) NOT NULL,
  `type` int(11) NOT NULL,
  `kidId` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_figure_kid` (`kidId`),
  CONSTRAINT `FK_figure_kid` FOREIGN KEY (`kidId`) REFERENCES `kid` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8 COMMENT='身影';

-- 正在导出表  growthrecord.figurerecord 的数据：~23 rows (大约)
DELETE FROM `figurerecord`;
/*!40000 ALTER TABLE `figurerecord` DISABLE KEYS */;
INSERT INTO `figurerecord` (`id`, `recordDate`, `note`, `realFileName`, `sourceFileName`, `type`, `kidId`) VALUES
	(75, '2016-06-01', '有钱，任性', '4ff57dfa-d54e-404a-ac36-7e873345e39d', '00fa25eb9cc924b8d6c4ef78076d066b.jpg', 0, 10),
	(76, '2016-06-01', '棒棒的', 'd487c194-633d-49d2-9ac1-2b586d1565bc', '0ccc015563eeea9e223538292aa998b1.jpg', 0, 10),
	(77, '2016-06-01', '纸人', '3487102d-d810-4861-9b36-b6824dafcfc2', '1f9820aa938a83a42e8be944b25f7667.jpeg', 0, 10),
	(78, '2016-06-01', '我爱刷牙', '2b58ca30-56db-4123-9af0-7e5340e0e244', '4c932cc9da3dea67e28c565666feba6c.jpeg', 0, 10),
	(79, '2016-06-01', '妈妈给讲故事了', '00df64ad-7d5a-425f-a5f8-dec7e33ab5a9', '6c4ccbf2dd256004f44195653a228aa4.jpg', 0, 10),
	(80, '2016-06-01', '孩子，你为何流泪了', '6cdae99c-8e35-4c6b-b85f-1a650e212664', '16e706c635f8c0ed6dd8bbee5eb84604.jpg', 0, 10),
	(81, '2015-06-01', '怎么办，懵逼了', '7d9ba07f-58c1-46f4-b35b-aeed89de2750', '22aa1bee8aaaa8a35dc0151ed17a2958.jpeg', 0, 10),
	(82, '2016-06-01', '冰冰冰冰冰激凌', 'ad1409e4-525d-40d9-b13b-6868727ae86a', '87d6b48cde8f2885ab5dca8d068e52b3.jpg', 0, 10),
	(83, '2016-04-01', '可爱的小孩', 'b45863bc-153e-4df2-9a44-e83a138e4680', '31367dcbc5e2db1a2e1f827084c4aa94.jpg', 0, 10),
	(84, '2016-06-01', '在思考什么呢？？？', '48cda866-3390-4343-bdd4-5273155b4cac', '25496317cf8f668a669670b229146b14.jpg', 0, 10),
	(85, '2016-06-01', '坏笑', 'f7e3d32a-7e34-4991-8543-9a34b27b832a', 'c623f4203db292bd880ba87385b81b75.jpg', 0, 10),
	(86, '2016-01-01', '问号问号问号问号问号，好多问号', '6b5b3550-79ef-4116-8e48-aea2cb05c5b4', 'cdd6a23c894d377b74c6b3e18cb4ec29.jpg', 0, 10),
	(87, '2014-06-01', '好开心啊', '13159957-006a-46fd-ae58-026b843bc438', 'eaad18d1447c62ec378628d26a8704c6.png', 0, 10),
	(88, '2016-06-01', '诸葛亮', '4e7d8202-d5de-4b3c-ae46-7612b86aa160', 'f135ac69635961fd8746c6b4894bb035.jpg', 0, 10),
	(89, '2013-06-01', '尔康', '47ca4eb9-060b-40c7-acff-56f955605beb', 'fd36ac3ab83a152906e67e980a38bdc7.jpg', 0, 10),
	(90, '2012-06-01', '尔康', '38e17511-9934-45d7-b5a4-fe5ca85191e3', 'fd36ac3ab83a152906e67e980a38bdc7.jpg', 0, 10),
	(91, '2016-06-01', '姣宁', '4b141f76-531f-4bca-80aa-0bf1256d1901', 'IMG_20100103_021538.jpg', 0, 10),
	(92, '2015-06-01', '姣宁', 'fecac8f3-0f19-4707-8958-0cb2169e8b15', 'IMG_20100103_023038.jpg', 0, 10),
	(93, '2016-06-01', '大个酱油', '4a58157a-d94b-417d-8fd2-22423d991d53', 'road.png', 0, 10),
	(94, '2016-02-09', '“成长脚印”的设计与实现', '300cf2f8-a96f-4748-abbb-df6463e3486d', '360截图-22557437.jpg', 0, 10),
	(95, '2016-06-01', '南瓜王杰克', 'b093cdbd-bebc-4ca4-8a92-db8c7f032c8b', 'f2b3bccf93f10a95a2d1d99bdba4c34a.jpg', 0, 10),
	(96, '2016-06-01', '可爱的杰克', 'dc2703e0-ebdd-409b-918b-6d64775cdd83', 'ce3fa2aac58326762d7dcea87b7eaec9.jpg', 0, 10),
	(97, '2016-06-02', '孩子学会走路了，走得很扎实', 'd91d4cbd-52f1-4ed2-bea3-a472ad66b03a', 'longshendao - xiaguang.mp4', 1, 10);
/*!40000 ALTER TABLE `figurerecord` ENABLE KEYS */;


-- 导出  表 growthrecord.home 结构
DROP TABLE IF EXISTS `home`;
CREATE TABLE IF NOT EXISTS `home` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(25) DEFAULT NULL COMMENT '家庭名称',
  `note` varchar(140) DEFAULT NULL COMMENT '家庭描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='家庭';

-- 正在导出表  growthrecord.home 的数据：~6 rows (大约)
DELETE FROM `home`;
/*!40000 ALTER TABLE `home` DISABLE KEYS */;
INSERT INTO `home` (`id`, `name`, `note`) VALUES
	(6, '哈哈', ''),
	(7, '', ''),
	(8, '', ''),
	(9, '', ''),
	(10, '', ''),
	(11, '', '');
/*!40000 ALTER TABLE `home` ENABLE KEYS */;


-- 导出  表 growthrecord.invite 结构
DROP TABLE IF EXISTS `invite`;
CREATE TABLE IF NOT EXISTS `invite` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `gongId` int(10) unsigned NOT NULL COMMENT '邀请用户的id',
  `shouId` int(10) unsigned NOT NULL COMMENT '被邀用户的id',
  `agree` tinyint(4) DEFAULT '0' COMMENT '0:未回复 1:同意 null: 拒绝',
  `leading` int(10) unsigned DEFAULT NULL COMMENT '以id为leading的用户为主导进行合并, null表示不合并',
  `read` tinyint(4) DEFAULT '0' COMMENT '对方回复是否已读 0:未读 1:已读',
  PRIMARY KEY (`id`),
  KEY `FK_Invite_user` (`gongId`),
  KEY `FK_Invite_user_2` (`shouId`),
  CONSTRAINT `FK_Invite_user` FOREIGN KEY (`gongId`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_Invite_user_2` FOREIGN KEY (`shouId`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='邀请表';

-- 正在导出表  growthrecord.invite 的数据：~1 rows (大约)
DELETE FROM `invite`;
/*!40000 ALTER TABLE `invite` DISABLE KEYS */;
INSERT INTO `invite` (`id`, `gongId`, `shouId`, `agree`, `leading`, `read`) VALUES
	(3, 6, 7, 0, 6, 0);
/*!40000 ALTER TABLE `invite` ENABLE KEYS */;


-- 导出  表 growthrecord.kid 结构
DROP TABLE IF EXISTS `kid`;
CREATE TABLE IF NOT EXISTS `kid` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `nickname` varchar(50) NOT NULL COMMENT '昵称',
  `sex` enum('男','女') NOT NULL COMMENT '性别',
  `birthday` date NOT NULL COMMENT '生日',
  `youstart` date DEFAULT NULL,
  `youend` date DEFAULT NULL,
  `xiaostart` date DEFAULT NULL,
  `xiaoend` date DEFAULT NULL,
  `chustart` date DEFAULT NULL,
  `chuend` date DEFAULT NULL,
  `gaostart` date DEFAULT NULL,
  `gaoend` date DEFAULT NULL,
  `dastart` date DEFAULT NULL,
  `daend` date DEFAULT NULL,
  `homeId` int(10) unsigned NOT NULL COMMENT '家庭id',
  PRIMARY KEY (`id`),
  KEY `FK_kid_home` (`homeId`),
  CONSTRAINT `FK_kid_home` FOREIGN KEY (`homeId`) REFERENCES `home` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='孩子';

-- 正在导出表  growthrecord.kid 的数据：~3 rows (大约)
DELETE FROM `kid`;
/*!40000 ALTER TABLE `kid` DISABLE KEYS */;
INSERT INTO `kid` (`id`, `name`, `nickname`, `sex`, `birthday`, `youstart`, `youend`, `xiaostart`, `xiaoend`, `chustart`, `chuend`, `gaostart`, `gaoend`, `dastart`, `daend`, `homeId`) VALUES
	(10, '王五', '小五', '男', '1992-01-08', NULL, NULL, '1999-09-01', '2005-07-01', NULL, NULL, NULL, NULL, NULL, NULL, 11),
	(12, '你好', '不好', '男', '1991-02-02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6),
	(13, '王六', '小六', '男', '2000-01-08', NULL, NULL, NULL, NULL, '2006-09-01', '2008-07-01', NULL, NULL, NULL, NULL, 11);
/*!40000 ALTER TABLE `kid` ENABLE KEYS */;


-- 导出  表 growthrecord.studyrecord 结构
DROP TABLE IF EXISTS `studyrecord`;
CREATE TABLE IF NOT EXISTS `studyrecord` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `competition` varchar(250) NOT NULL COMMENT '赛事、活动',
  `grade` varchar(50) NOT NULL COMMENT '成绩',
  `recordDate` date NOT NULL COMMENT '时间',
  `kidId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_studyrecord_kid` (`kidId`),
  CONSTRAINT `FK_studyrecord_kid` FOREIGN KEY (`kidId`) REFERENCES `kid` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- 正在导出表  growthrecord.studyrecord 的数据：~3 rows (大约)
DELETE FROM `studyrecord`;
/*!40000 ALTER TABLE `studyrecord` DISABLE KEYS */;
INSERT INTO `studyrecord` (`id`, `competition`, `grade`, `recordDate`, `kidId`) VALUES
	(19, '四六级', '及格', '2016-06-01', 10),
	(21, '作文竞赛', '优秀', '2004-03-01', 10),
	(22, '植树活动', '小红花', '2002-04-05', 10);
/*!40000 ALTER TABLE `studyrecord` ENABLE KEYS */;


-- 导出  表 growthrecord.user 结构
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `sex` enum('男','女') NOT NULL COMMENT '性别',
  `homeId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `FK_user_home` (`homeId`),
  CONSTRAINT `FK_user_home` FOREIGN KEY (`homeId`) REFERENCES `home` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 正在导出表  growthrecord.user 的数据：~6 rows (大约)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `username`, `password`, `email`, `sex`, `homeId`) VALUES
	(6, 'ziji', '123456', 'hechao@qq.com', '男', 6),
	(7, 'laopo', '123456', 'laopo@qq.com', '女', 7),
	(10, 'xxxxxx', '123456', 'bierenlaopo@qq.com', '女', 9),
	(11, 'xiaoqie', '123456', 'xiaoqie@qq.com', '女', 10),
	(12, 'otherhusband', '123456', 'gong@qq.com', '男', 11),
	(14, 'otherwife', '123456', 'shou@qq.com', '女', 11);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


-- 导出  表 growthrecord.vaccineplan 结构
DROP TABLE IF EXISTS `vaccineplan`;
CREATE TABLE IF NOT EXISTS `vaccineplan` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `note` varchar(250) DEFAULT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `minAge` int(11) NOT NULL COMMENT '月份',
  `maxAge` int(11) NOT NULL COMMENT '月份',
  `site` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='疫苗安排计划';

-- 正在导出表  growthrecord.vaccineplan 的数据：~0 rows (大约)
DELETE FROM `vaccineplan`;
/*!40000 ALTER TABLE `vaccineplan` DISABLE KEYS */;
/*!40000 ALTER TABLE `vaccineplan` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
