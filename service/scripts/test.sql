-- 删除表
DROP TABLE IF EXISTS `User`;

-- 创建测试表
CREATE TABLE `User` (
   `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
   `name` varchar(64) NOT NULL DEFAULT '' COMMENT '名称',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- 增加测试数据
insert into User (name) value ('jack');
insert into User (name) value ('tom');
insert into User (name) value ('robin');
