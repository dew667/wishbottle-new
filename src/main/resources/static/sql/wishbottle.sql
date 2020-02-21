/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : wishbottle

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 20/02/2020 20:22:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend`  (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '好友条目id 主键 非空 自增',
  `mine_id` int(255) NOT NULL COMMENT '我的id(关注者id) 非空 外键约束参照user(id)',
  `friend_id` int(255) NOT NULL COMMENT '被关注者id 非空 外键约束参照user(id)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `10`(`mine_id`) USING BTREE,
  INDEX `11`(`friend_id`) USING BTREE,
  CONSTRAINT `fk_10` FOREIGN KEY (`mine_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_11` FOREIGN KEY (`friend_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of friend
-- ----------------------------
INSERT INTO `friend` VALUES (1, 1, 2);
INSERT INTO `friend` VALUES (2, 1, 5);
INSERT INTO `friend` VALUES (3, 2, 5);

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '树洞日志id 主键 非空 自增',
  `writer_id` int(255) NOT NULL COMMENT '作者id 非空 外键约束参照user(id)',
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文本内容',
  `pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片 多个图片用”-”分隔',
  `time` datetime(6) NULL DEFAULT NULL COMMENT '创建时间 非空',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态 0-删除 1-保存',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `8`(`writer_id`) USING BTREE,
  CONSTRAINT `fk_8` FOREIGN KEY (`writer_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager`  (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '管理员id 主键 非空 自增',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号 非空',
  `psd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码(6-18位，头尾不能空格) 非空',
  `type` int(11) NULL DEFAULT NULL COMMENT '0-普通管理员 1-超级管理员',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '消息id 主键 非空 自增',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '消息内容',
  `flag` int(11) NULL DEFAULT NULL COMMENT '是否已读标记',
  `target_id` int(255) NULL DEFAULT NULL COMMENT '消息目标用户id 非空 外键约束参照user(id)',
  `type` int(255) NOT NULL COMMENT '1-通知公告2-心愿瓶回复 3-树洞回复 4-管理员警告 5-首页滚动消息',
  `source_id` int(255) NULL DEFAULT NULL COMMENT '心愿瓶回复或者树洞回复的id （来自心愿或者树洞表）',
  `pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片地址 通知公告或警告等可能有图片',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `9`(`target_id`) USING BTREE,
  CONSTRAINT `fk_9` FOREIGN KEY (`target_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for session
-- ----------------------------
DROP TABLE IF EXISTS `session`;
CREATE TABLE `session`  (
  `open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '获取到的openId',
  `session_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '同user表中id进行参照',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of session
-- ----------------------------
INSERT INTO `session` VALUES ('onXqv4hwBiYdmkoWO93c8UEpCv5o', '21EsiH8vLTrqvCJt4brUfA==', 5);

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `id` int(11) NOT NULL COMMENT '标签条目id 主键 非空 自增',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id(拥有者id) 非空 外键约束参照user(id)',
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签内容 文本',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `tag_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tree_history
-- ----------------------------
DROP TABLE IF EXISTS `tree_history`;
CREATE TABLE `tree_history`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '浏览记录条目id 主键 非空 自增',
  `treehole_id` int(11) NOT NULL COMMENT '树洞id 非空 外键约束参照treehole(id)',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '浏览时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_treehole`(`treehole_id`) USING BTREE,
  CONSTRAINT `fk_treehole` FOREIGN KEY (`treehole_id`) REFERENCES `treehole` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tree_reply
-- ----------------------------
DROP TABLE IF EXISTS `tree_reply`;
CREATE TABLE `tree_reply`  (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '树洞回复id 主键 非空 自增',
  `treehole_id` int(255) NOT NULL COMMENT '树洞布告id 非空 外键约束参照treehole(id)',
  `replyer_id` int(255) NOT NULL COMMENT '回复者id 非空 外键约束参照user(id)',
  `answer_id` int(255) NOT NULL DEFAULT 0 COMMENT '指向的树洞回复id 非空 外键约束参照tree_reply(id) 默认为0表示此条回复指向树洞布告',
  `time` datetime(6) NULL DEFAULT NULL COMMENT '回复时间 非空',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '回复内容（回复的话只能文字）',
  `status` int(11) NOT NULL COMMENT '状态 非空 0-发布 1-管理员删除 默认为0',
  `likes` int(255) NOT NULL DEFAULT 0 COMMENT '点赞数 非空 默认为0',
  `version` int(255) NOT NULL DEFAULT 0 COMMENT '乐观锁 版本号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `5`(`treehole_id`) USING BTREE,
  INDEX `6`(`replyer_id`) USING BTREE,
  INDEX `7`(`answer_id`) USING BTREE,
  CONSTRAINT `fk_5` FOREIGN KEY (`treehole_id`) REFERENCES `treehole` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_6` FOREIGN KEY (`replyer_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_7` FOREIGN KEY (`answer_id`) REFERENCES `tree_reply` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for treehole
-- ----------------------------
DROP TABLE IF EXISTS `treehole`;
CREATE TABLE `treehole`  (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '树洞布告id 主键 非空 自增',
  `writer_id` int(255) NOT NULL COMMENT '树洞作者id 非空 外键约束参照user(id)',
  `time` datetime(6) NULL DEFAULT NULL COMMENT '树洞发表时间 非空',
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '树洞内容 文本',
  `views` int(255) NOT NULL DEFAULT 0 COMMENT '浏览量 非空 默认为0',
  `reply_num` int(255) NOT NULL DEFAULT 0 COMMENT '回复量 非空 默认为0',
  `pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片地址（多张图片的话，地址用分号‘-’隔开）',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态 0-删除 1-发布 非空 默认为1',
  `likes` int(255) NOT NULL DEFAULT 0 COMMENT '点赞数 非空 默认为0',
  `report` int(255) NOT NULL DEFAULT 0 COMMENT '举报数(向管理员报告) 非空 默认为0',
  `top` int(11) NOT NULL DEFAULT 0 COMMENT '置顶状态(由管理员操作) 0-时间排序 1-置顶',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '树洞标题 非空 默认”标题”',
  `voice` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '语音链接地址',
  `version` int(255) NOT NULL DEFAULT 0 COMMENT '乐观锁 版本号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `4`(`writer_id`) USING BTREE,
  CONSTRAINT `fk_4` FOREIGN KEY (`writer_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id，主键 非空 自增',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '游客' COMMENT '用户昵称 非空',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '头像地址 非空',
  `gender` int(11) NOT NULL DEFAULT 0 COMMENT '0-男 1-女 非空 默认0',
  `age` int(11) NOT NULL DEFAULT 18 COMMENT '年龄1-150之间 非空 默认18',
  `signature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '个性签名 最多255字符心愿瓶',
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '北京' COMMENT '城市 非空 默认北京',
  `create_time` datetime(6) NULL DEFAULT NULL COMMENT '创建用户时间 非空',
  `update_time` datetime(6) NULL DEFAULT NULL COMMENT '用户最后修改时间',
  `ban` int(255) NOT NULL DEFAULT 1 COMMENT '封禁状态(由管理员操作) 0-封禁 1-正常',
  `my_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '个性化背景图片地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '红红', '000', 0, 18, NULL, '北京', NULL, NULL, 1, NULL);
INSERT INTO `user` VALUES (2, '绿绿', '000', 0, 18, NULL, '北京', NULL, NULL, 1, NULL);
INSERT INTO `user` VALUES (5, '00', 'xxxx', 1, 18, NULL, 'wh', NULL, NULL, 1, NULL);

-- ----------------------------
-- Table structure for wish_reply
-- ----------------------------
DROP TABLE IF EXISTS `wish_reply`;
CREATE TABLE `wish_reply`  (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '回复id 主键 非空 自增',
  `wishbottle_id` int(255) NOT NULL COMMENT '心愿瓶id 非空 外键约束参照wish_bottle(id)',
  `replyer_id` int(255) NOT NULL COMMENT '回复者id 非空 外键约束参照user(id)',
  `time` datetime(6) NULL DEFAULT NULL COMMENT '回复时间 非空',
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '回复内容 非空 文本内容或语音文件链接',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `2`(`wishbottle_id`) USING BTREE,
  INDEX `3`(`replyer_id`) USING BTREE,
  CONSTRAINT `fk_2` FOREIGN KEY (`wishbottle_id`) REFERENCES `wishbottle` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_3` FOREIGN KEY (`replyer_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wishbottle
-- ----------------------------
DROP TABLE IF EXISTS `wishbottle`;
CREATE TABLE `wishbottle`  (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '心愿id 主键 非空 自增',
  `writer_id` int(255) NOT NULL COMMENT '心愿瓶的作者id 非空 外键约束参照user(id)',
  `time` datetime(6) NULL DEFAULT NULL COMMENT '心愿发表时间 ',
  `picker_id` int(255) NULL DEFAULT NULL COMMENT '捡瓶子的用户id 外键约束参照user(id)',
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '心愿瓶内容 文本内容或语音文件链接',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '状态 0-没被捞 1-被捞取 2捞取者删除 3-发布者删除 4-两者都删 5-管理员删除 6-保存在草稿箱 非空',
  `pic` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片链接地址（多张图片的话，地址用分号‘-’隔开）',
  `report` int(255) NOT NULL DEFAULT 0 COMMENT '举报数 非空 默认为0',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `0`(`writer_id`) USING BTREE,
  INDEX `1`(`picker_id`) USING BTREE,
  CONSTRAINT `fk_0` FOREIGN KEY (`writer_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_1` FOREIGN KEY (`picker_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
