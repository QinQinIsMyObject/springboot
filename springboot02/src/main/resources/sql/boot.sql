/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : boot

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 16/10/2020 19:14:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ucenter
-- ----------------------------
DROP TABLE IF EXISTS `ucenter`;
CREATE TABLE `ucenter`  (
  `uid` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `uname` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `age` int NOT NULL COMMENT '用户年龄',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ucenter
-- ----------------------------
INSERT INTO `ucenter` VALUES (1, '张三', 10);
INSERT INTO `ucenter` VALUES (2, '李四', 8);
INSERT INTO `ucenter` VALUES (3, '王二', 13);
INSERT INTO `ucenter` VALUES (4, '赵五', 18);
INSERT INTO `ucenter` VALUES (5, '钱九', 33);
INSERT INTO `ucenter` VALUES (6, '孙十', 66);
INSERT INTO `ucenter` VALUES (7, '周一', 99);
INSERT INTO `ucenter` VALUES (8, '武五', 55);
INSERT INTO `ucenter` VALUES (9, '郑七', 21);

SET FOREIGN_KEY_CHECKS = 1;
