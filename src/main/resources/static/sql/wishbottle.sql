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

 Date: 26/02/2020 15:52:02
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
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of friend
-- ----------------------------
INSERT INTO `friend` VALUES (1, 1, 2);
INSERT INTO `friend` VALUES (2, 6, 2);
INSERT INTO `friend` VALUES (3, 6, 1);
INSERT INTO `friend` VALUES (4, 1, 6);

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
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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

-- ----------------------------
-- Table structure for tree_reply
-- ----------------------------
DROP TABLE IF EXISTS `tree_reply`;
CREATE TABLE `tree_reply`  (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT 'c',
  `treehole_id` int(255) NOT NULL COMMENT '树洞布告id 非空 外键约束参照treehole(id)',
  `replyer_id` int(255) NOT NULL COMMENT '回复者id 非空 外键约束参照user(id)',
  `answer_id` int(255) NOT NULL DEFAULT 0 COMMENT '指向的树洞回复id 非空 参照tree_reply(id) 默认为0表示此条回复指向树洞布告',
  `time` datetime(6) NULL DEFAULT NULL COMMENT '回复时间 非空',
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '回复内容（回复的话只能文字）',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态 非空 0-发布 1-管理员删除 默认为0',
  `likes` int(255) NOT NULL DEFAULT 0 COMMENT '点赞数 非空 默认为0',
  `version` int(255) NOT NULL DEFAULT 0 COMMENT '乐观锁 版本号',
  `report` int(11) NOT NULL DEFAULT 0 COMMENT '举报数 默认为0',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `5`(`treehole_id`) USING BTREE,
  INDEX `6`(`replyer_id`) USING BTREE,
  INDEX `7`(`answer_id`) USING BTREE,
  CONSTRAINT `fk_5` FOREIGN KEY (`treehole_id`) REFERENCES `treehole` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_6` FOREIGN KEY (`replyer_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tree_reply
-- ----------------------------
INSERT INTO `tree_reply` VALUES (1, 9, 1, 0, '2020-02-14 15:10:03.000000', '回复你们的问题', 1, 0, 0, 0);
INSERT INTO `tree_reply` VALUES (2, 9, 2, 0, '2020-02-11 15:11:58.000000', '回复2', 1, 0, 0, 0);
INSERT INTO `tree_reply` VALUES (3, 9, 6, 0, '2020-02-27 15:12:19.000000', '回复3', 1, 0, 0, 0);
INSERT INTO `tree_reply` VALUES (4, 9, 6, 1, '2020-02-20 15:13:02.000000', '回复4', 1, 0, 0, 0);
INSERT INTO `tree_reply` VALUES (5, 9, 6, 0, '2020-02-06 15:13:23.000000', '回复5', 0, 0, 0, 0);
INSERT INTO `tree_reply` VALUES (6, 9, 1, 1, '2020-02-11 16:41:06.000000', '回复6', 1, 0, 0, 0);
INSERT INTO `tree_reply` VALUES (7, 9, 2, 1, '2020-02-23 16:41:58.000000', '回复7', 1, 0, 0, 0);
INSERT INTO `tree_reply` VALUES (8, 9, 6, 1, '2020-02-25 16:42:21.000000', '回复8', 1, 0, 0, 0);
INSERT INTO `tree_reply` VALUES (9, 9, 6, 0, '2020-02-23 13:16:43.897000', '你的文章非常精彩 谢谢分享！', 1, 0, 0, 0);
INSERT INTO `tree_reply` VALUES (10, 9, 6, 1, '2020-02-23 13:43:06.817000', '你评论中说的好像有问题！', 1, 0, 0, 0);
INSERT INTO `tree_reply` VALUES (11, 9, 6, 0, '2020-02-24 02:56:05.525000', '测试文章评论 +1！', 1, 0, 0, 0);
INSERT INTO `tree_reply` VALUES (12, 9, 6, 0, '2020-02-24 02:56:21.988000', '测试文章评论 +1！', 1, 1, 0, 1);
INSERT INTO `tree_reply` VALUES (13, 9, 6, 1, '2020-02-24 02:57:15.371000', '测试评论回复 +1！', 1, 0, 0, 0);

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
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态 0-删除 1-发布 非空 默认为1',
  `likes` int(255) NOT NULL DEFAULT 0 COMMENT '点赞数 非空 默认为0',
  `report` int(255) NOT NULL DEFAULT 0 COMMENT '举报数(向管理员报告) 非空 默认为0',
  `top` int(11) NOT NULL DEFAULT 0 COMMENT '置顶状态(由管理员操作) 0-时间排序 1-置顶',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '树洞标题 非空 默认”标题”',
  `voice` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '语音链接地址',
  `version` int(255) NOT NULL DEFAULT 0 COMMENT '乐观锁 版本号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `4`(`writer_id`) USING BTREE,
  CONSTRAINT `fk_4` FOREIGN KEY (`writer_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of treehole
-- ----------------------------
INSERT INTO `treehole` VALUES (1, 1, '2020-02-13 17:31:39.000000', '‘000’', 10, 8, 'xxxx', 1, 6, 0, 0, 'xxxx', 'xxxx', 0);
INSERT INTO `treehole` VALUES (2, 1, '2020-02-14 17:32:55.000000', '0000', 10, 8, 'xxxx', 1, 8, 0, 0, 'xxx', 'xxxx', 0);
INSERT INTO `treehole` VALUES (3, 1, '2020-02-11 17:33:38.000000', '00000', 7, 6, 'xxxxx', 1, 6, 0, 0, 'xxxx', 'xxxx', 0);
INSERT INTO `treehole` VALUES (4, 1, '2020-02-26 17:34:19.000000', '000000', 13, 6, 'xxxxxx', 1, 6, 0, 0, 'xxxxx', 'xxxxx', 0);
INSERT INTO `treehole` VALUES (6, 6, '2020-02-22 16:12:53.184000', '我的树洞内容', 1, 0, '3ccdf461-3c38-4ce1-8d36-9dd9dcc02aa2.gif+0a9d88ef-3358-48a3-88a7-dfd5e9d0d9a8.png', 1, 0, 0, 0, '这是我的树洞标题', '0d1a1cc7-8143-4825-8e56-a53e2d17255a.mp3', 0);
INSERT INTO `treehole` VALUES (7, 6, '2020-02-23 06:00:39.262000', '我的树洞内容01', 1, 0, '50345dce-2c02-4e75-a4d7-bf40cecbdc39.gif+7788a1b1-847a-442a-b1d5-b544e8ad31da.png', 1, 0, 0, 0, '标题01', '0f058e94-5310-4d88-983e-05626e960b9e.mp3', 0);
INSERT INTO `treehole` VALUES (8, 6, '2020-02-23 06:01:17.627000', '我的树洞内容02', 4, 0, '4661f51e-1afc-40ca-8715-e8a8959ca108.gif+8a953c28-8278-488c-8096-401b9c278047.png', 1, 0, 0, 0, '标题02', NULL, 0);
INSERT INTO `treehole` VALUES (9, 6, '2020-02-23 06:01:50.380000', '我的树洞内容02', 1, 3, NULL, 1, 2, 1, 0, '标题02', '029e9d8c-36e3-4022-99b6-1f7eaf1dc885.mp3', 0);
INSERT INTO `treehole` VALUES (13, 6, '2020-02-26 03:57:22.138000', '为什么要写这些内容？', 0, 0, 'http://localhost:8080/treehole/releaseArticle.jpg+http://localhost:8080/treehole/r1d23321.png+http://localhost:8080/treehole/xxxxxx.png', 1, 0, 0, 0, '我的标题是什么呢？', 'http://localhost:8080/treehole/re2313434fdfds.mp3', 0);
INSERT INTO `treehole` VALUES (14, 6, '2020-02-26 03:57:45.581000', '为什么要写这些内容21212？', 0, 0, 'http://localhost:8080/treehole/releaseArticle.jpg', 1, 0, 0, 0, '我的标题是什么122121212？', 'http://localhost:8080/treehole/re2313434fdfds.mp3', 0);
INSERT INTO `treehole` VALUES (15, 6, '2020-02-26 03:57:56.324000', '为什么要写这些内容21212xxxxxxxxxxxx？', 0, 0, NULL, 1, 0, 0, 0, '我的标题是什么122xxxxxx？', 'http://localhost:8080/treehole/re2313434fdfds.mp3', 0);
INSERT INTO `treehole` VALUES (16, 6, '2020-02-26 03:58:11.264000', '为什么要写这些内容21212xxxyyyyyyxxxx？', 0, 0, 'http://localhost:8080/treehole/releaseArticle.jpg', 1, 0, 0, 0, '我的标题是什么122yyyyyyyyyx？', NULL, 0);
INSERT INTO `treehole` VALUES (17, 6, '2020-02-26 03:58:23.988000', '为什么要写这些zzzzzzzzz？', 0, 0, NULL, 1, 0, 0, 0, '我的标题是什zzzzzzzzzz？', NULL, 0);

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
  `tags` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户标签 多个用\'+\'分隔',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '红红', '000', 0, 18, '哈哈哈', '北京', NULL, NULL, 1, NULL, NULL);
INSERT INTO `user` VALUES (2, '绿绿', '000', 0, 18, '切切切', '北京', NULL, NULL, 1, NULL, NULL);
INSERT INTO `user` VALUES (6, '00', 'ds', 0, 18, '我有我的young', 'wh', NULL, NULL, 1, NULL, '文艺+去+斜杠');

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
  `trow_time` datetime(6) NULL DEFAULT NULL COMMENT '心愿瓶抛掷时间 ',
  `pick_time` datetime(6) NULL DEFAULT NULL COMMENT '心愿瓶拾取时间',
  `update_time` datetime(6) NULL DEFAULT NULL COMMENT '草稿修改时间',
  `picker_id` int(255) NULL DEFAULT NULL COMMENT '捡瓶子的用户id 外键约束参照user(id)',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '心愿瓶标题',
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '心愿瓶内容 文本内容',
  `status` int(11) NULL DEFAULT 0 COMMENT '状态 0-心愿海 1-被捞取 2-捞取者删除 3-发布者删除 4-两者都删 5-管理员删除 6-保存在草稿箱 非空',
  `type` int(11) NULL DEFAULT NULL COMMENT '心愿瓶类型 ',
  `report` int(255) NOT NULL DEFAULT 0 COMMENT '举报数 非空 默认为0',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `0`(`writer_id`) USING BTREE,
  INDEX `1`(`picker_id`) USING BTREE,
  CONSTRAINT `fk_0` FOREIGN KEY (`writer_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_1` FOREIGN KEY (`picker_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
