/*
Navicat MySQL Data Transfer

Source Server         : tonghang
Source Server Version : 50625
Source Host           : 127.0.0.1:3306
Source Database       : tonghang

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2016-07-17 15:37:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_user`;
CREATE TABLE `t_admin_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `permission` int(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_banner
-- ----------------------------
DROP TABLE IF EXISTS `t_banner`;
CREATE TABLE `t_banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL COMMENT '跳转地址',
  `img` varchar(255) DEFAULT NULL COMMENT '展示图',
  `state` int(11) DEFAULT NULL COMMENT '0禁用1启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_check_reason
-- ----------------------------
DROP TABLE IF EXISTS `t_check_reason`;
CREATE TABLE `t_check_reason` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_circle
-- ----------------------------
DROP TABLE IF EXISTS `t_circle`;
CREATE TABLE `t_circle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `hot` int(255) DEFAULT NULL,
  `datetime` datetime DEFAULT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4,
  `page_view` int(11) DEFAULT '0',
  `favour` int(11) DEFAULT '0',
  `comment` int(11) DEFAULT '0',
  `share` int(255) DEFAULT '0' COMMENT '分享数',
  `type` int(255) DEFAULT NULL COMMENT '1同行圈2资讯3服务',
  `pid` int(11) DEFAULT NULL,
  `checked` int(255) DEFAULT NULL,
  `pics` text,
  `area` varchar(255) DEFAULT NULL,
  `trade_id` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL COMMENT '分享链接',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_circle_like
-- ----------------------------
DROP TABLE IF EXISTS `t_circle_like`;
CREATE TABLE `t_circle_like` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL,
  `circle_id` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_circle_seen
-- ----------------------------
DROP TABLE IF EXISTS `t_circle_seen`;
CREATE TABLE `t_circle_seen` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `circle_d` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_city
-- ----------------------------
DROP TABLE IF EXISTS `t_city`;
CREATE TABLE `t_city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `province_id` int(11) DEFAULT NULL,
  `en_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=418 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text,
  `circle_id` int(11) DEFAULT NULL,
  `pid_id` int(11) DEFAULT NULL,
  `reply_id` int(11) DEFAULT NULL,
  `checked` int(255) DEFAULT NULL,
  `datetime` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_favorite
-- ----------------------------
DROP TABLE IF EXISTS `t_favorite`;
CREATE TABLE `t_favorite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fav_id` int(11) DEFAULT NULL COMMENT '收藏的同行圈或者服务id',
  `type` enum('1','3','2') DEFAULT NULL COMMENT '1同行圈2资讯3服务',
  `pid` int(11) DEFAULT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '收藏时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_friend
-- ----------------------------
DROP TABLE IF EXISTS `t_friend`;
CREATE TABLE `t_friend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `fid` int(11) DEFAULT NULL,
  `confirm` int(255) DEFAULT '0',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `state` int(1) DEFAULT '1' COMMENT '1未读取2已读取',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_notification
-- ----------------------------
DROP TABLE IF EXISTS `t_notification`;
CREATE TABLE `t_notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL COMMENT '通知接收方id',
  `content` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `content_id` int(11) DEFAULT NULL,
  `datetime` datetime DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `pro_id` int(11) DEFAULT NULL COMMENT '通知产生方id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_phone
-- ----------------------------
DROP TABLE IF EXISTS `t_phone`;
CREATE TABLE `t_phone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `name` varchar(255) DEFAULT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT '4' COMMENT '1男2女3保密4未填写',
  `position` varchar(255) DEFAULT NULL,
  `college` varchar(255) DEFAULT NULL,
  `education` varchar(255) DEFAULT NULL COMMENT '学历',
  `trade_id` int(11) DEFAULT NULL,
  `province_id` int(11) DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '签标',
  `device` varchar(255) DEFAULT NULL COMMENT '1表示Android，2表示ios，3表示其他',
  `longitude` double(255,0) DEFAULT NULL,
  `latitude` double(255,0) DEFAULT NULL,
  `state` int(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL COMMENT '0表示普通组，1表示重要组',
  `company` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_province
-- ----------------------------
DROP TABLE IF EXISTS `t_province`;
CREATE TABLE `t_province` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `en_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_reason_circle
-- ----------------------------
DROP TABLE IF EXISTS `t_reason_circle`;
CREATE TABLE `t_reason_circle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reason_id` int(11) DEFAULT NULL,
  `circle_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_reason_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_reason_comment`;
CREATE TABLE `t_reason_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reason_id` int(11) DEFAULT NULL,
  `comment` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_reason_service
-- ----------------------------
DROP TABLE IF EXISTS `t_reason_service`;
CREATE TABLE `t_reason_service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reason_id` int(11) DEFAULT NULL,
  `service_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_service
-- ----------------------------
DROP TABLE IF EXISTS `t_service`;
CREATE TABLE `t_service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `pictures` text,
  `pid` int(11) DEFAULT NULL,
  `checked` int(11) DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_share
-- ----------------------------
DROP TABLE IF EXISTS `t_share`;
CREATE TABLE `t_share` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '虚拟主键',
  `pid` int(11) NOT NULL COMMENT '用户id',
  `cid` int(11) NOT NULL COMMENT '同行圈id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_track
-- ----------------------------
DROP TABLE IF EXISTS `t_track`;
CREATE TABLE `t_track` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL,
  `target_pid` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `state` int(1) DEFAULT '1' COMMENT '1未读取2已读取',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_trade
-- ----------------------------
DROP TABLE IF EXISTS `t_trade`;
CREATE TABLE `t_trade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `en_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
