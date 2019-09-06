/*
 Navicat Premium Data Transfer

 Source Server         : bigdog1
 Source Server Type    : MySQL
 Source Server Version : 100134
 Source Host           : localhost:3306
 Source Schema         : articlemanager

 Target Server Type    : MySQL
 Target Server Version : 100134
 File Encoding         : 65001

 Date: 22/05/2019 19:13:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for news_table
-- ----------------------------
DROP TABLE IF EXISTS `news_table`;
CREATE TABLE `news_table`  (
  `news_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `title` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `post_time` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`news_id`) USING BTREE,
  INDEX `news_table_ibfk_1`(`user_id`) USING BTREE,
  CONSTRAINT `news_table_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_table` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of news_table
-- ----------------------------
INSERT INTO `news_table` VALUES (1, 1, '德狗是怎么炼成的！', '教育', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus consequatur inventore, hic maxime optio nemout quidem atque, eos, officiis corporis cumque voluptatum porro commodi id. Quidem fugiat, earum incidunt?Loremipsum dolor sit <span style=\"background-color:#99BB00;\">amet, consectetur adipisicing elit. Sed doloribus maxime nesciunt cum nulla hic recusandaedignissimos nemo blanditiis. Accusantium praesentium, veniam distinctio fugit, repellat ipsum nesciunt deseruntpariatur deleniti.Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus consequatur inventore, hic maxime optio nemout quidem atque, eos, officiis corporis cumque voluptatum porro commodi id. Quidem fugiat, earum incidunt?Loremipsum dolor sit amet, consectetur adipisicing elit. Sed doloribus maxime nesciunt cum nulla hic recusandaedignissimos nemo blanditiis. Accusantium praesentium, veniam distinctio fugit, repellat ipsum nesciunt deseruntpariatur deleniti.</span>', '1545963570000');
INSERT INTO `news_table` VALUES (3, 2, '我是欢锅锅', '热点', '我真的是欢锅锅&nbsp; &nbsp;哈哈哈哈哈哈哈啊哈！！！', '1545895080000');
INSERT INTO `news_table` VALUES (6, 1, '过滤器啊！', '热点', '为什么我的过滤器又炸啦？！', '1545901899000');
INSERT INTO `news_table` VALUES (23, 1, 'JSP实训', '教育', '<p>\n	<span style=\"color:#60D978;background-color:#FFFFFF;\">丹尼斯哦啊的尿素你都我安静的骄傲是哦的决赛哦&nbsp;</span><span style=\"color:#60D978;background-color:#FFFFFF;\">丹尼斯哦啊的尿素你都我安静的骄傲是哦的决赛哦&nbsp;</span><span style=\"color:#60D978;background-color:#FFFFFF;\">丹尼斯哦啊的尿素你都我安静的骄傲是哦的决赛哦&nbsp;</span><span style=\"color:#60D978;background-color:#FFFFFF;\">丹尼斯哦啊的尿素你都我安静的骄傲是哦的决赛哦&nbsp;</span><span style=\"color:#60D978;background-color:#FFFFFF;\">丹尼斯哦啊的尿素你都我安静的骄傲是哦的决赛哦&nbsp;</span><span style=\"color:#60D978;background-color:#FFFFFF;\">丹尼斯哦啊的尿素你都我安静的骄傲是哦的决赛哦&nbsp;</span><span><span style=\"color:#60D978;background-color:#FFFFFF;\">丹尼斯哦啊的尿素你都我安</span><span style=\"color:#60D978;background-color:#FFFFFF;\">静的骄傲是哦的决赛哦&nbsp;</span></span><span style=\"color:#60D978;background-color:#FFFFFF;\">丹尼斯哦啊的尿素你都我安静的骄傲是哦的决赛哦&nbsp;</span><span style=\"color:#60D978;background-color:#FFFFFF;\">丹尼斯哦啊的尿素你都我安静的骄傲是哦的决赛哦&nbsp;</span><span style=\"color:#60D978;background-color:#FFFFFF;\">丹尼斯哦啊的尿素你都我安静的骄傲是哦的决赛哦&nbsp;</span><span style=\"color:#60D978;background-color:#FFFFFF;\">丹尼斯哦啊的尿素你都我安静的骄傲是哦的决赛哦&nbsp;</span><span style=\"color:#60D978;background-color:#999999;\"><span style=\"background-color:#FFFFFF;\">丹尼斯哦啊的尿素你都我</span><span style=\"background-color:#FFFFFF;\"></span><span style=\"background-color:#FFFFFF;\">安静的骄傲是哦的决赛哦&nbsp;</span></span><span style=\"color:#60D978;background-color:#FFFFFF;\">丹尼斯哦啊的尿素你都我安静的骄傲是哦的决赛哦&nbsp;</span><span style=\"color:#60D978;background-color:#FFFFFF;\">丹尼斯哦啊的尿素你都我安静的骄傲是哦的决赛哦&nbsp;</span><span style=\"color:#60D978;background-color:#FFFFFF;\">丹尼斯哦啊的尿素你都我安静的骄傲是哦的决赛哦&nbsp;</span>\n</p>\n<p>\n	<span style=\"color:#60D978;background-color:#FFFFFF;\"><img src=\"/articleManager/upload/image/20181228/20181228101159_122.jpg\" alt=\"\" /><br />\n</span>\n</p>', '1545963124000');

-- ----------------------------
-- Table structure for user_table
-- ----------------------------
DROP TABLE IF EXISTS `user_table`;
CREATE TABLE `user_table`  (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` char(11) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_table
-- ----------------------------
INSERT INTO `user_table` VALUES (1, 'bigdog', '123456', '18002831326', '1', '东莞南城广东科技学院');
INSERT INTO `user_table` VALUES (2, 'xiaohuanhuan', '12345678', '13838385438', '1', 'dongguan');
INSERT INTO `user_table` VALUES (3, '老师', '1234567', '13838385438', '0', '东莞');

SET FOREIGN_KEY_CHECKS = 1;
