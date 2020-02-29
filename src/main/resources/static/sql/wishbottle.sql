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

 Date: 29/02/2020 22:51:21
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
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of friend
-- ----------------------------
INSERT INTO `friend` VALUES (1, 1, 2);
INSERT INTO `friend` VALUES (4, 1, 6);
INSERT INTO `friend` VALUES (5, 6, 1);
INSERT INTO `friend` VALUES (20, 6, 2);

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '树洞日志id 主键 非空 自增',
  `writer_id` int(11) NOT NULL COMMENT '作者id 非空 外键约束参照user(id)',
  `content` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文本内容',
  `time` datetime(6) NULL DEFAULT NULL COMMENT '创建时间 非空',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态 0-删除 1-保存',
  `title` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `emotion` int(11) NULL DEFAULT NULL COMMENT '心情 1-51种心情',
  `weather` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '天气',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `8`(`writer_id`) USING BTREE,
  CONSTRAINT `fk_8` FOREIGN KEY (`writer_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES (2, 6, '这是我的日记内容', '2020-02-25 02:12:56.621000', 1, '这是我的日记标题', 1, '晴');
INSERT INTO `log` VALUES (4, 6, '新的日记内容', '2020-02-25 02:43:47.004000', 1, '新的题木', 3, '晴');
INSERT INTO `log` VALUES (5, 6, '新的日记内ggggg容', '2020-02-25 02:43:54.882000', 1, '新的 gggg', 3, '晴');
INSERT INTO `log` VALUES (6, 6, '新的adf内gzcdg容', '2020-02-25 02:44:00.963000', 1, '新的 xczcxg', 3, '晴');
INSERT INTO `log` VALUES (7, 6, '新的文章内容什么是？', '2020-02-25 02:44:06.981000', 1, '新的文章标题是什么', 2, '阴');
INSERT INTO `log` VALUES (8, 6, '反对大师傅', '2020-02-25 11:29:01.000000', 1, '日记xxx', 1, '阴');

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
-- Table structure for pick
-- ----------------------------
DROP TABLE IF EXISTS `pick`;
CREATE TABLE `pick`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `wishbottle_id` int(11) NULL DEFAULT NULL COMMENT '心愿瓶id 参照wishbottle(id)',
  `picker_id` int(11) NULL DEFAULT NULL COMMENT '拾取者id',
  `pick_time` datetime(0) NULL DEFAULT NULL COMMENT '拾取时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pick
-- ----------------------------
INSERT INTO `pick` VALUES (1, 1, 6, '2020-02-28 20:18:16');
INSERT INTO `pick` VALUES (3, 4, 6, '2020-02-07 20:18:22');
INSERT INTO `pick` VALUES (32, 9, 6, '2020-02-29 04:49:40');
INSERT INTO `pick` VALUES (33, 8, 6, '2020-02-29 04:49:49');
INSERT INTO `pick` VALUES (34, 6, 1, '2020-02-12 13:55:07');
INSERT INTO `pick` VALUES (35, 6, 2, '2020-02-28 13:55:20');
INSERT INTO `pick` VALUES (36, 10, 1, '2020-02-04 13:55:56');
INSERT INTO `pick` VALUES (37, 10, 2, '2020-01-29 13:56:04');
INSERT INTO `pick` VALUES (38, 11, 1, '2020-02-26 14:26:00');
INSERT INTO `pick` VALUES (39, 12, 2, '2020-02-04 14:26:13');
INSERT INTO `pick` VALUES (40, 11, 2, '2020-02-04 14:26:56');

-- ----------------------------
-- Table structure for session
-- ----------------------------
DROP TABLE IF EXISTS `session`;
CREATE TABLE `session`  (
  `open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '获取到的openId',
  `session_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '同user表中id进行参照',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of session
-- ----------------------------
INSERT INTO `session` VALUES ('onXqv4hwBiYdmkoWO93c8UEpCv5o', 'aZPxLB+M8ET05NfTDkl/yw==', 6);

-- ----------------------------
-- Table structure for tag_admin
-- ----------------------------
DROP TABLE IF EXISTS `tag_admin`;
CREATE TABLE `tag_admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '签名',
  `color` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '颜色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tag_admin
-- ----------------------------
INSERT INTO `tag_admin` VALUES (1, '文艺青年', '#FFFFFF');
INSERT INTO `tag_admin` VALUES (2, '斜杠青年', '#FFFFFF');
INSERT INTO `tag_admin` VALUES (3, '屌丝青年', '#FFFFFF');
INSERT INTO `tag_admin` VALUES (4, '大咖', '#FFFFFF');

-- ----------------------------
-- Table structure for tag_user
-- ----------------------------
DROP TABLE IF EXISTS `tag_user`;
CREATE TABLE `tag_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id 参照user(id)',
  `tag_id` int(11) NULL DEFAULT NULL COMMENT '标签id 参照tag(id)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tag_user
-- ----------------------------
INSERT INTO `tag_user` VALUES (4, 6, 1);
INSERT INTO `tag_user` VALUES (5, 6, 2);
INSERT INTO `tag_user` VALUES (6, 6, 4);

-- ----------------------------
-- Table structure for tree_comment
-- ----------------------------
DROP TABLE IF EXISTS `tree_comment`;
CREATE TABLE `tree_comment`  (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT 'c',
  `treehole_id` int(255) NOT NULL COMMENT '树洞布告id 非空 外键约束参照treehole(id)',
  `replyer_id` int(255) NOT NULL COMMENT '评论者id 非空 外键约束参照user(id)',
  `time` datetime(6) NULL DEFAULT NULL COMMENT '评论回复时间 非空',
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评论回复内容（回复的话只能文字）',
  `likes` int(255) NOT NULL DEFAULT 0 COMMENT '点赞数 非空 默认为0',
  `report` int(11) NOT NULL DEFAULT 0 COMMENT '举报数 默认为0',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `5`(`treehole_id`) USING BTREE,
  INDEX `6`(`replyer_id`) USING BTREE,
  CONSTRAINT `fk_5` FOREIGN KEY (`treehole_id`) REFERENCES `treehole` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_6` FOREIGN KEY (`replyer_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tree_comment
-- ----------------------------
INSERT INTO `tree_comment` VALUES (1, 9, 1, '2020-02-14 15:10:03.000000', '回复你们的问题', 0, 0);
INSERT INTO `tree_comment` VALUES (2, 9, 2, '2020-02-11 15:11:58.000000', '回复2', 0, 0);
INSERT INTO `tree_comment` VALUES (3, 9, 6, '2020-02-27 15:12:19.000000', '回复3', 0, 0);
INSERT INTO `tree_comment` VALUES (4, 9, 6, '2020-02-20 15:13:02.000000', '回复4', 0, 0);
INSERT INTO `tree_comment` VALUES (5, 9, 6, '2020-02-06 15:13:23.000000', '回复5', 0, 0);
INSERT INTO `tree_comment` VALUES (6, 9, 1, '2020-02-11 16:41:06.000000', '回复6', 0, 0);
INSERT INTO `tree_comment` VALUES (7, 9, 2, '2020-02-23 16:41:58.000000', '回复7', 0, 0);
INSERT INTO `tree_comment` VALUES (8, 9, 6, '2020-02-25 16:42:21.000000', '回复8', 0, 0);
INSERT INTO `tree_comment` VALUES (9, 9, 6, '2020-02-23 13:16:43.897000', '你的文章非常精彩 谢谢分享！', 0, 0);
INSERT INTO `tree_comment` VALUES (10, 9, 6, '2020-02-23 13:43:06.817000', '你评论中说的好像有问题！', 0, 0);
INSERT INTO `tree_comment` VALUES (11, 9, 6, '2020-02-24 02:56:05.525000', '测试文章评论 +1！', 0, 0);
INSERT INTO `tree_comment` VALUES (12, 9, 6, '2020-02-24 02:56:21.988000', '测试文章评论 +1！', 1, 1);
INSERT INTO `tree_comment` VALUES (13, 9, 6, '2020-02-24 02:57:15.371000', '测试评论回复 +1！', 0, 0);
INSERT INTO `tree_comment` VALUES (14, 9, 6, '2020-02-28 03:44:28.829000', 'lllllllllll', 1, 1);

-- ----------------------------
-- Table structure for tree_history
-- ----------------------------
DROP TABLE IF EXISTS `tree_history`;
CREATE TABLE `tree_history`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '浏览记录条目id 主键 非空 自增',
  `treehole_id` int(11) NOT NULL COMMENT '树洞id 非空 外键约束参照treehole(id)',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '所属者id 参照user表id',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '浏览时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_treehole`(`treehole_id`) USING BTREE,
  CONSTRAINT `fk_treehole` FOREIGN KEY (`treehole_id`) REFERENCES `treehole` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tree_history
-- ----------------------------
INSERT INTO `tree_history` VALUES (2, 8, 6, '2020-02-24 08:38:01');
INSERT INTO `tree_history` VALUES (3, 9, 6, '2020-02-24 08:38:17');
INSERT INTO `tree_history` VALUES (4, 6, 6, '2020-02-24 08:38:20');
INSERT INTO `tree_history` VALUES (5, 7, 6, '2020-02-24 08:38:24');
INSERT INTO `tree_history` VALUES (6, 4, 6, '2020-02-24 12:54:28');
INSERT INTO `tree_history` VALUES (7, 4, 6, '2020-02-24 12:54:38');
INSERT INTO `tree_history` VALUES (8, 3, 6, '2020-02-24 14:42:20');
INSERT INTO `tree_history` VALUES (9, 18, 6, '2020-02-27 08:30:20');
INSERT INTO `tree_history` VALUES (10, 17, 6, '2020-02-27 08:30:47');
INSERT INTO `tree_history` VALUES (11, 18, NULL, '2020-02-28 03:11:47');

-- ----------------------------
-- Table structure for tree_reply
-- ----------------------------
DROP TABLE IF EXISTS `tree_reply`;
CREATE TABLE `tree_reply`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `comment_id` int(11) NULL DEFAULT NULL COMMENT '指向的评论id 参照tree_comment(id)',
  `replyer_id` int(11) NULL DEFAULT NULL COMMENT '回复者id 参照user(id)',
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '内容',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '发表时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tree_reply
-- ----------------------------
INSERT INTO `tree_reply` VALUES (1, 1, 6, 'xxx', '2020-02-27 22:09:47');
INSERT INTO `tree_reply` VALUES (2, 1, 6, 'xxx', '2020-02-27 22:09:56');
INSERT INTO `tree_reply` VALUES (3, 1, 6, 'xxx', '2020-02-27 22:10:06');
INSERT INTO `tree_reply` VALUES (4, 1, 6, 'xxx', '2020-02-27 22:10:16');
INSERT INTO `tree_reply` VALUES (5, 1, 6, 'lllllllllll', '2020-02-28 03:50:22');

-- ----------------------------
-- Table structure for treehole
-- ----------------------------
DROP TABLE IF EXISTS `treehole`;
CREATE TABLE `treehole`  (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '树洞布告id 主键 非空 自增',
  `writer_id` int(255) NOT NULL COMMENT '树洞作者id 非空 外键约束参照user(id)',
  `time` datetime(6) NULL DEFAULT NULL COMMENT '树洞发表时间 非空',
  `content` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '树洞内容 文本',
  `views` int(255) NOT NULL DEFAULT 0 COMMENT '浏览量 非空 默认为0',
  `reply_num` int(255) NOT NULL DEFAULT 0 COMMENT '回复量 非空 默认为0',
  `pic` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片地址链接（多张图片的话，地址用分号‘+’隔开）',
  `likes` int(255) NOT NULL DEFAULT 0 COMMENT '点赞数 非空 默认为0',
  `report` int(255) NOT NULL DEFAULT 0 COMMENT '举报数(向管理员报告) 非空 默认为0',
  `top` int(11) NOT NULL DEFAULT 0 COMMENT '置顶状态(由管理员操作) 0-时间排序 1-置顶',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '树洞标题 非空 默认”标题”',
  `voice` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '语音链接地址',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `4`(`writer_id`) USING BTREE,
  CONSTRAINT `fk_4` FOREIGN KEY (`writer_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of treehole
-- ----------------------------
INSERT INTO `treehole` VALUES (1, 1, '2020-02-13 17:31:39.000000', '‘000’', 10, 8, 'xxxx', 6, 0, 0, 'xxxx', 'xxxx');
INSERT INTO `treehole` VALUES (2, 1, '2020-02-14 17:32:55.000000', '0000', 10, 8, 'xxxx', 8, 0, 0, 'xxx', 'xxxx');
INSERT INTO `treehole` VALUES (3, 1, '2020-02-11 17:33:38.000000', '00000', 7, 6, 'xxxxx', 6, 0, 0, 'xxxx', 'xxxx');
INSERT INTO `treehole` VALUES (4, 1, '2020-02-26 17:34:19.000000', '000000', 13, 6, 'xxxxxx', 6, 0, 0, 'xxxxx', 'xxxxx');
INSERT INTO `treehole` VALUES (6, 6, '2020-02-22 16:12:53.184000', '我的树洞内容', 1, 0, '3ccdf461-3c38-4ce1-8d36-9dd9dcc02aa2.gif+0a9d88ef-3358-48a3-88a7-dfd5e9d0d9a8.png', 0, 0, 0, '这是我的树洞标题', '0d1a1cc7-8143-4825-8e56-a53e2d17255a.mp3');
INSERT INTO `treehole` VALUES (7, 6, '2020-02-23 06:00:39.262000', '我的树洞内容01', 1, 0, '50345dce-2c02-4e75-a4d7-bf40cecbdc39.gif+7788a1b1-847a-442a-b1d5-b544e8ad31da.png', 0, 0, 0, '标题01', '0f058e94-5310-4d88-983e-05626e960b9e.mp3');
INSERT INTO `treehole` VALUES (8, 6, '2020-02-23 06:01:17.627000', '我的树洞内容02', 4, 0, '4661f51e-1afc-40ca-8715-e8a8959ca108.gif+8a953c28-8278-488c-8096-401b9c278047.png', 0, 0, 0, '标题02', NULL);
INSERT INTO `treehole` VALUES (9, 6, '2020-02-23 06:01:50.380000', '我的树洞内容02', 1, 4, NULL, 2, 1, 0, '标题02', '029e9d8c-36e3-4022-99b6-1f7eaf1dc885.mp3');
INSERT INTO `treehole` VALUES (13, 6, '2020-02-26 03:57:22.138000', '为什么要写这些内容？', 0, 0, 'http://localhost:8080/treehole/releaseArticle.jpg+http://localhost:8080/treehole/r1d23321.png+http://localhost:8080/treehole/xxxxxx.png', 0, 0, 0, '我的标题是什么呢？', 'http://localhost:8080/treehole/re2313434fdfds.mp3');
INSERT INTO `treehole` VALUES (14, 6, '2020-02-26 03:57:45.581000', '为什么要写这些内容21212？', 0, 0, 'http://localhost:8080/treehole/releaseArticle.jpg', 0, 0, 0, '我的标题是什么122121212？', 'http://localhost:8080/treehole/re2313434fdfds.mp3');
INSERT INTO `treehole` VALUES (15, 6, '2020-02-26 03:57:56.324000', '为什么要写这些内容21212xxxxxxxxxxxx？', 0, 0, NULL, 0, 0, 0, '我的标题是什么122xxxxxx？', 'http://localhost:8080/treehole/re2313434fdfds.mp3');
INSERT INTO `treehole` VALUES (16, 6, '2020-02-26 03:58:11.264000', '为什么要写这些内容21212xxxyyyyyyxxxx？', 0, 0, 'http://localhost:8080/treehole/releaseArticle.jpg', 0, 0, 0, '我的标题是什么122yyyyyyyyyx？', NULL);
INSERT INTO `treehole` VALUES (17, 6, '2020-02-26 03:58:23.988000', '为什么要写这些zzzzzzzzz？', 1, 0, NULL, 0, 0, 0, '我的标题是什zzzzzzzzzz？', NULL);
INSERT INTO `treehole` VALUES (18, 6, '2020-02-27 08:13:40.056000', '柳暗花明又一村', 2, 0, '/ds/ds', 1, 1, 0, '山重水复疑无路', '/ll/ll');
INSERT INTO `treehole` VALUES (19, 6, '2020-02-28 03:11:10.629000', 'jjjjjjjjjj', 0, 0, '22222+1111', 1, 1, 0, 'xxxx-------', 'zzzzz');

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
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '红红', '000', 0, 18, '哈哈哈', '北京', NULL, NULL, 1, NULL);
INSERT INTO `user` VALUES (2, '绿绿', '000', 0, 18, '切切切', '北京', NULL, NULL, 1, NULL);
INSERT INTO `user` VALUES (6, '00', 'ds', 0, 38, '孤帆远影碧空净', 'wh', NULL, NULL, 1, 'wh---------wh');
INSERT INTO `user` VALUES (7, '游客', 'zzz', 0, 18, NULL, '北京', NULL, NULL, 1, NULL);

-- ----------------------------
-- Table structure for wishbottle
-- ----------------------------
DROP TABLE IF EXISTS `wishbottle`;
CREATE TABLE `wishbottle`  (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `writer_id` int(255) NOT NULL COMMENT '心愿瓶的作者id 非空 外键约束参照user(id)',
  `throw_time` datetime(6) NULL DEFAULT NULL COMMENT '心愿瓶抛掷时间 ',
  `update_time` datetime(6) NULL DEFAULT NULL COMMENT '草稿修改时间',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '心愿瓶标题',
  `content` varchar(7000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '心愿瓶内容 文本内容',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '状态  1-可捞取 2-发布者删除 3-管理员删除 4-保存在草稿箱 5-从草稿箱删除',
  `type` int(11) NULL DEFAULT NULL COMMENT '心愿瓶类型 ',
  `report` int(11) NOT NULL DEFAULT 0 COMMENT '举报数 非空 默认为0',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `0`(`writer_id`) USING BTREE,
  CONSTRAINT `fk_0` FOREIGN KEY (`writer_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wishbottle
-- ----------------------------
INSERT INTO `wishbottle` VALUES (1, 1, '2020-02-26 08:50:18.294000', NULL, '这是我的心愿一首', '谢谢你的关心', 1, 1, 0);
INSERT INTO `wishbottle` VALUES (3, 1, NULL, '2020-02-27 01:46:36.124000', '飞流直下三千尺', '疑是银河落九天', 4, 1, 0);
INSERT INTO `wishbottle` VALUES (4, 2, '2020-02-27 01:47:23.384000', NULL, '山重水复疑无路', '柳暗花明又一村', 1, 1, 0);
INSERT INTO `wishbottle` VALUES (5, 2, '2020-02-27 14:13:30.000000', NULL, 'fsd', 'fsd', 3, 1, 0);
INSERT INTO `wishbottle` VALUES (6, 6, '2020-02-28 07:38:30.986000', NULL, '几处早莺争暖树', '谁家新燕啄春泥', 1, 2, 0);
INSERT INTO `wishbottle` VALUES (7, 6, NULL, '2020-02-28 07:41:24.804000', '阡陌交通', '鸡犬相闻', 4, 2, 0);
INSERT INTO `wishbottle` VALUES (8, 2, '2020-02-20 12:01:52.000000', NULL, '朝辞白帝彩云间', '千里江陵一日还', 1, 1, 0);
INSERT INTO `wishbottle` VALUES (9, 1, '2020-02-12 12:03:09.000000', NULL, '白发三千丈', '白发三千丈', 1, 1, 0);
INSERT INTO `wishbottle` VALUES (10, 6, '2020-02-29 12:07:10.000000', NULL, '百代之过客', '百代之过客', 1, 1, 0);
INSERT INTO `wishbottle` VALUES (11, 6, '2020-02-05 14:25:06.000000', NULL, '木兰', '木兰', 1, 2, 0);
INSERT INTO `wishbottle` VALUES (12, 6, '2020-02-19 14:25:33.000000', NULL, '密码', '密码', 2, 1, 0);
INSERT INTO `wishbottle` VALUES (13, 2, '2020-02-29 09:28:50.552000', NULL, '软件工程', '软件工程', 1, 1, 0);
INSERT INTO `wishbottle` VALUES (14, 6, '2020-02-05 17:50:38.000000', '2020-02-18 17:50:45.000000', '计算机', '九三级', 5, 1, 0);

SET FOREIGN_KEY_CHECKS = 1;
