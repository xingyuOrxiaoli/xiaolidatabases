CREATE TABLE `documents` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '文档ID',
  `name` varchar(254) NOT NULL COMMENT '文档名称',
  `path` varchar(254) NOT NULL COMMENT '存储路径',
  `type` varchar(50) NOT NULL COMMENT '文档类型',
  `size` int NOT NULL COMMENT '文件大小(字节)',
  `upload_time` datetime NOT NULL COMMENT '上传时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `description` text COMMENT '文档描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `contacts` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '联系人ID',
  `name` varchar(100) NOT NULL COMMENT '姓名',
  `phone` varchar(20) NOT NULL COMMENT '手机号',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `address` varchar(254) DEFAULT NULL COMMENT '地址',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `custom_fields` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '字段ID',
  `table_name` varchar(50) NOT NULL COMMENT '所属表名',
  `field_name` varchar(50) NOT NULL COMMENT '字段名称',
  `field_type` varchar(20) NOT NULL COMMENT '字段类型',
  `field_label` varchar(100) NOT NULL COMMENT '字段标签',
  `is_required` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否必填',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `life_cust` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `note_time` datetime NOT NULL COMMENT '时间',
  `desc` text NOT NULL COMMENT '简述',
  `money` double NOT NULL DEFAULT '0' COMMENT '金额',
  `flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'false表示支出 true表示收入',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `life_cust_label` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '生活开销类别标签的ID',
  `label_name` varchar(254) NOT NULL COMMENT '生活开销类别标签名称',
  `create_time` datetime NOT NULL COMMENT '创造时间',
  `update_time` datetime NOT NULL,
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `exist` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1表示还存在 0表示删除了',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `life_cust_label_relation` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '映射表的ID',
  `life_cust_id` int NOT NULL COMMENT 'life_cust表的ID',
  `life_cust_label_id` int NOT NULL COMMENT 'life_cust_label表的ID',
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `life_cust_id` (`life_cust_id`),
  KEY `life_cust_label_id` (`life_cust_label_id`),
  CONSTRAINT `life_cust_label_relation_ibfk_1` FOREIGN KEY (`life_cust_id`) REFERENCES `life_cust` (`id`),
  CONSTRAINT `life_cust_label_relation_ibfk_2` FOREIGN KEY (`life_cust_label_id`) REFERENCES `life_cust_label` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `life_use_username_password` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '生活使用账号密码表的ID',
  `name` varchar(254) DEFAULT NULL COMMENT '账号平台名称',
  `username` varchar(254) NOT NULL COMMENT '账号',
  `password` varchar(254) NOT NULL COMMENT '密码',
  `url` varchar(254) DEFAULT NULL COMMENT '网站URL',
  `isHasWeb` tinyint(1) DEFAULT '1' COMMENT '是否有web',
  `isHasApp` tinyint(1) DEFAULT '1' COMMENT '是否有app',
  `isNeedVPN` tinyint(1) DEFAULT '0' COMMENT '是否需要vpn',
  `isPhone` tinyint(1) DEFAULT '0' COMMENT '是否是手机号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;