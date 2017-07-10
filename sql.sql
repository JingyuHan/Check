SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `users`;
create table `users`(
	`uid` int auto_increment primary key,
	`username` varchar(255) not null,
	`password` varchar(255) not null
	)ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `users` VALUES(0,'zhangsan','123456');

DROP TABLE IF EXISTS `buyers`;
create table `buyers` (
	`bid` int auto_increment primary key,
	`bname` varchar(255) not null,
	`tel` varchar(11) not null,
	`address` varchar(255) not null
	)ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
DROP TABLE IF EXISTS `sellers`;
create table `sellers` (
	`sid` int auto_increment primary key,
	`sname` varchar(255) not null,
	`tel` varchar(11) not null,
	`address` varchar(255) not null
	)ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
DROP TABLE IF EXISTS `goods`;
create table `goods` (
	`gid` varchar(8) primary key,
	`gname` varchar(255) not null,
	`price` int not null,
	`amount` int not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
DROP TABLE IF EXISTS `orderin`;
create table `orderin` (
	`id` int auto_increment primary key,
	`oiid` varchar(16) not null,
	`gname` varchar(255) not null,
	`price` int not null,
	`amount` int not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `orderout`;
create table `orderout` (
	`id` int auto_increment primary key,
	`ooid` varchar(16) not null,
	`gname` varchar(255) not null,
	`price` int not null,
	`amount` int not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `orders`;
create table `orders` (
	`oid` varchar(16) primary key,
	`type` boolean not null,
	`price_sh` int not null,
	`price_ac` int not null,
	`user` varchar(255) not null,
	`customer` varchar(255) not null,
	`date` datetime not null,
	`note` varchar(255)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;





