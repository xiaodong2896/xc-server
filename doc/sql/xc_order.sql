/*
SQLyog v10.2 
MySQL - 5.7.21-log : Database - xc_order
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE
DATABASE /*!32312 IF NOT EXISTS*/`xc_order` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE
`xc_order`;

/*Table structure for table `xc_orders` */

DROP TABLE IF EXISTS `xc_orders`;

CREATE TABLE `xc_orders`
(
    `order_number`  varchar(32) NOT NULL COMMENT '订单号',
    `initial_price` float(8, 2
) DEFAULT NULL COMMENT '定价',
  `price` float(8,2) DEFAULT NULL COMMENT '交易价',
  `start_time` datetime NOT NULL COMMENT '起始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `status` varchar(32) DEFAULT NULL COMMENT '交易状态',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `details` varchar(1000) DEFAULT NULL COMMENT '订单明细',
  PRIMARY KEY (`order_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `xc_orders` */

insert into `xc_orders`(`order_number`, `initial_price`, `price`, `start_time`, `end_time`, `status`, `user_id`,
                        `details`)
values ('299036931059486720', 0.01, 0.01, '2018-04-05 12:26:00', '2018-04-05 14:26:00', '401001', '49',
        '[{\"itemId\":\"4028e581617f945f01617f9dabc40000\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"299036931059486720\"}]'),
       ('299118286820741120', 0.01, 0.01, '2018-04-05 17:49:17', '2018-04-05 19:49:17', '401001', '49',
        '[{\"itemId\":\"4028e58161bd22e60161bd23672a0001\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"299118286820741120\"}]'),
       ('299118455888941056', 0.01, 0.01, '2018-04-05 17:49:57', '2018-04-05 19:49:57', '401001', '49',
        '[{\"itemId\":\"4028e58161bd22e60161bd23672a0001\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"299118455888941056\"}]'),
       ('299144273360982016', 0.01, 0.01, '2018-04-05 19:32:33', '2018-04-05 21:32:33', '401002', '49',
        '[{\"itemId\":\"4028e58161bcf7f40161bcf8b77c0000\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"299144273360982016\"}]'),
       ('299202627802370048', 0.01, 0.01, '2018-04-05 23:24:26', '2018-04-06 01:24:26', '401001', '49',
        '[{\"itemId\":\"4028e58161bd22e60161bd23672a0001\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"299202627802370048\"}]'),
       ('299226261577142272', 0.01, 0.01, '2018-04-06 00:58:50', '2018-04-06 02:58:50', '401001', '49',
        '[{\"itemId\":\"4028e58161bd3b380161bd3bcd2f0000\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"299226261577142272\"}]'),
       ('299226499146715136', 0.01, 0.01, '2018-04-06 00:59:17', '2018-04-06 02:59:17', '401001', '49',
        '[{\"itemId\":\"4028e58161bd3b380161bd3bcd2f0000\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"299226499146715136\"}]'),
       ('317320500991102976', 0.01, 0.01, '2018-05-25 23:18:23', '2018-05-26 01:18:23', '401001', '49',
        '[{\"itemId\":\"4028e58161bd22e60161bd23672a0001\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"317320500991102976\"}]'),
       ('317320549372399616', 0.01, 0.01, '2018-05-25 23:18:35', '2018-05-26 01:18:35', '401001', '49',
        '[{\"itemId\":\"4028e58161bd22e60161bd23672a0001\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"317320549372399616\"}]'),
       ('317326221119983616', 0.01, 0.01, '2018-05-25 23:41:07', '2018-05-26 01:41:07', '401001', '49',
        '[{\"itemId\":\"402885816243d2dd016243f24c030002\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"317326221119983616\"}]'),
       ('317532756458737664', 0.01, 0.01, '2018-05-26 13:21:49', '2018-05-26 15:21:49', '401001', '49',
        '[{\"itemId\":\"4028e58161bd22e60161bd23672a0001\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"317532756458737664\"}]'),
       ('317532808245809152', 0.01, 0.01, '2018-05-26 13:22:01', '2018-05-26 15:22:01', '401001', '49',
        '[{\"itemId\":\"4028e58161bd22e60161bd23672a0001\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"317532808245809152\"}]'),
       ('317532890600968192', 0.01, 0.01, '2018-05-26 13:22:21', '2018-05-26 15:22:21', '401001', '49',
        '[{\"itemId\":\"4028e58161bd22e60161bd23672a0001\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"317532890600968192\"}]'),
       ('317587489890373632', 99.00, 99.00, '2018-05-26 16:59:18', '2018-05-26 18:59:18', '401001', '49',
        '[{\"itemId\":\"402885816243d2dd016243f24c030002\",\"itemNum\":1,\"itemPrice\":99.0,\"orderNumber\":\"317587489890373632\"}]'),
       ('317590240250695680', 99.00, 99.00, '2018-05-26 17:10:14', '2018-05-26 19:10:14', '401001', '49',
        '[{\"itemId\":\"402885816243d2dd016243f24c030002\",\"itemNum\":1,\"itemPrice\":99.0,\"orderNumber\":\"317590240250695680\"}]'),
       ('317600970689613824', 99.00, 99.00, '2018-05-26 17:52:52', '2018-05-26 19:52:52', '401001', '49',
        '[{\"itemId\":\"402885816243d2dd016243f24c030002\",\"itemNum\":1,\"itemPrice\":99.0,\"orderNumber\":\"317600970689613824\"}]'),
       ('317651443140399104', 99.00, 99.00, '2018-05-26 21:13:26', '2018-05-26 23:13:26', '401001', '49',
        '[{\"itemId\":\"402885816243d2dd016243f24c030002\",\"itemNum\":1,\"itemPrice\":99.0,\"orderNumber\":\"317651443140399104\"}]'),
       ('317651457044516864', 1.01, 1.01, '2018-05-26 21:13:29', '2018-05-26 23:13:29', '401001', '49',
        '[{\"itemId\":\"402885816243d2dd016243f24c030002\",\"itemNum\":1,\"itemPrice\":99.0,\"orderNumber\":\"317651457044516864\"}]'),
       ('318344201396162560', 0.01, 0.01, '2018-05-28 19:06:12', '2018-05-28 21:06:12', '401002', '49',
        '[{\"itemId\":\"402885816243d2dd016243f24c030002\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"318344201396162560\"}]'),
       ('319855888196571136', 0.01, 0.01, '2018-06-01 23:13:07', '2018-06-02 01:13:07', '401002', '49',
        '[{\"endTime\":1585709437000,\"itemId\":\"4028e58161bd22e60161bd23672a0001\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"319855888196571136\",\"startTime\":1522551031000}]'),
       ('319867386222481408', 0.01, 0.01, '2018-06-01 23:58:48', '2018-06-02 01:58:48', '401001', '49',
        '[{\"endTime\":1585709437000,\"itemId\":\"4028e58161bd22e60161bd23672a0001\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"319867386222481408\",\"startTime\":1522551031000,\"valid\":\"204001\"}]'),
       ('319867403872112640', 0.01, 0.01, '2018-06-01 23:58:52', '2018-06-02 01:58:52', '401002', '49',
        '[{\"endTime\":1585709437000,\"itemId\":\"4028e58161bd22e60161bd23672a0001\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"319867403872112640\",\"startTime\":1522551031000,\"valid\":\"204001\"}]');

/*Table structure for table `xc_orders_detail` */

DROP TABLE IF EXISTS `xc_orders_detail`;

CREATE TABLE `xc_orders_detail`
(
    `id`           varchar(32) NOT NULL,
    `order_number` varchar(32) NOT NULL COMMENT '订单号',
    `item_id`      varchar(32) NOT NULL COMMENT '商品id',
    `item_num`     int(8) NOT NULL COMMENT '商品数量',
    `item_price`   float(8, 2
) NOT NULL COMMENT '金额',
  `valid` varchar(32) NOT NULL COMMENT '课程有效性',
  `start_time` datetime NOT NULL COMMENT '课程开始时间',
  `end_time` datetime NOT NULL COMMENT '课程结束时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `xc_orders_detail_unique` (`order_number`,`item_id`),
  CONSTRAINT `fk_xc_orders_detail_order_number` FOREIGN KEY (`order_number`) REFERENCES `xc_orders` (`order_number`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `xc_orders_detail` */

insert into `xc_orders_detail`(`id`, `order_number`, `item_id`, `item_num`, `item_price`, `valid`, `start_time`,
                               `end_time`)
values ('297e02f76397e0d7016397e11eba0000', '317320500991102976', '4028e58161bd22e60161bd23672a0001', 1, 0.01, '',
        '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
       ('297e02f76397e0d7016397e14b190002', '317320549372399616', '4028e58161bd22e60161bd23672a0001', 1, 0.01, '',
        '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
       ('297e02f76397e0d7016397f5ed510004', '317326221119983616', '402885816243d2dd016243f24c030002', 1, 0.01, '',
        '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
       ('297e02f7639ae4fc01639ae54fa70000', '317532756458737664', '4028e58161bd22e60161bd23672a0001', 1, 0.01, '',
        '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
       ('297e02f7639ae4fc01639ae57c940002', '317532808245809152', '4028e58161bd22e60161bd23672a0001', 1, 0.01, '',
        '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
       ('297e02f7639ae4fc01639ae5c9440004', '317532890600968192', '4028e58161bd22e60161bd23672a0001', 1, 0.01, '',
        '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
       ('297e02f7639ae4fc01639bac6ae90006', '317587489890373632', '402885816243d2dd016243f24c030002', 1, 99.00, '',
        '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
       ('297e02f7639ae4fc01639bb66c500008', '317590240250695680', '402885816243d2dd016243f24c030002', 1, 99.00, '',
        '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
       ('297e02f7639ae4fc01639bdd75d1000a', '317600970689613824', '402885816243d2dd016243f24c030002', 1, 99.00, '',
        '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
       ('297e02f7639c1a3c01639c95149c0000', '317651443140399104', '402885816243d2dd016243f24c030002', 1, 99.00, '',
        '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
       ('297e02f7639c1a3c01639c9520db0002', '317651457044516864', '402885816243d2dd016243f24c030002', 1, 99.00, '',
        '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
       ('297e02f763a6634d0163a66d529b0000', '318344201396162560', '402885816243d2dd016243f24c030002', 1, 0.01, '',
        '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
       ('402881e663bbbf210163bbe8cf120000', '319855888196571136', '4028e58161bd22e60161bd23672a0001', 1, 0.01, '',
        '2018-04-01 10:50:31', '2020-04-01 10:50:37'),
       ('402881e663bc11970163bc12a26e0000', '319867386222481408', '4028e58161bd22e60161bd23672a0001', 1, 0.01, '204001',
        '2018-04-01 10:50:31', '2020-04-01 10:50:37'),
       ('402881e663bc11970163bc12b2370002', '319867403872112640', '4028e58161bd22e60161bd23672a0001', 1, 0.01, '204001',
        '2018-04-01 10:50:31', '2020-04-01 10:50:37'),
       ('4028858162940c510162940de2100000', '299036931059486720', '4028e581617f945f01617f9dabc40000', 1, 0.01, '',
        '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
       ('4028858162944c9801629535db390000', '299118286820741120', '4028e58161bd22e60161bd23672a0001', 1, 0.01, '',
        '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
       ('4028858162944c980162953676d80002', '299118455888941056', '4028e58161bd22e60161bd23672a0001', 1, 0.01, '',
        '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
       ('402885816295920a0162959464640000', '299144273360982016', '4028e58161bcf7f40161bcf8b77c0000', 1, 0.01, '',
        '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
       ('402885816295be9901629668af9c0000', '299202627802370048', '4028e58161bd22e60161bd23672a0001', 1, 0.01, '',
        '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
       ('402885816295be99016296bf1df10002', '299226261577142272', '4028e58161bd3b380161bd3bcd2f0000', 1, 0.01, '',
        '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
       ('402885816295be99016296bf85b70004', '299226499146715136', '4028e58161bd3b380161bd3bcd2f0000', 1, 0.01, '',
        '0000-00-00 00:00:00', '0000-00-00 00:00:00');

/*Table structure for table `xc_orders_pay` */

DROP TABLE IF EXISTS `xc_orders_pay`;

CREATE TABLE `xc_orders_pay`
(
    `id`           varchar(32) NOT NULL,
    `order_number` varchar(32) NOT NULL COMMENT '订单号',
    `pay_number`   varchar(32) DEFAULT NULL COMMENT '支付系统订单号',
    `status`       varchar(32) NOT NULL COMMENT '交易状态',
    PRIMARY KEY (`id`),
    UNIQUE KEY `xc_orders_pay_order_number_unique` (`order_number`),
    UNIQUE KEY `xc_orders_pay_pay_number_unique` (`pay_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `xc_orders_pay` */

insert into `xc_orders_pay`(`id`, `order_number`, `pay_number`, `status`)
values ('297e02f76397e0d7016397e11ec00001', '317320500991102976', NULL, '402001'),
       ('297e02f76397e0d7016397e14b190003', '317320549372399616', NULL, '402001'),
       ('297e02f76397e0d7016397f5ed510005', '317326221119983616', NULL, '402001'),
       ('297e02f7639ae4fc01639ae54fac0001', '317532756458737664', NULL, '402001'),
       ('297e02f7639ae4fc01639ae57c940003', '317532808245809152', NULL, '402001'),
       ('297e02f7639ae4fc01639ae5c9440005', '317532890600968192', NULL, '402001'),
       ('297e02f7639ae4fc01639bac6aeb0007', '317587489890373632', NULL, '402001'),
       ('297e02f7639ae4fc01639bb66c500009', '317590240250695680', NULL, '402001'),
       ('297e02f7639ae4fc01639bdd75d1000b', '317600970689613824', NULL, '402001'),
       ('297e02f7639c1a3c01639c95149f0001', '317651443140399104', NULL, '402001'),
       ('297e02f7639c1a3c01639c9520db0003', '317651457044516864', NULL, '402001'),
       ('297e02f763a6634d0163a66d529f0001', '318344201396162560', NULL, '402002'),
       ('402881e663bbbf210163bbe8cf160001', '319855888196571136', NULL, '402002'),
       ('402881e663bc11970163bc12a2740001', '319867386222481408', NULL, '402001'),
       ('402881e663bc11970163bc12b2370003', '319867403872112640', NULL, '402002'),
       ('40288581627cdb0e01627ce2f8ad0009', '297406656009342976', NULL, '402001'),
       ('4028858162863b6d0162864231b60001', '298066138997592064', NULL, '402001'),
       ('4028858162940c510162940de2150001', '299036931059486720', NULL, '402001'),
       ('4028858162944c9801629535db3f0001', '299118286820741120', NULL, '402001'),
       ('4028858162944c980162953676d80003', '299118455888941056', NULL, '402001'),
       ('402885816295920a0162959464670001', '299144273360982016', NULL, '402002'),
       ('402885816295be9901629668afa30001', '299202627802370048', NULL, '402001'),
       ('402885816295be99016296bf1df10003', '299226261577142272', NULL, '402001'),
       ('402885816295be99016296bf85b70005', '299226499146715136', NULL, '402001');

/*Table structure for table `xc_task` */

DROP TABLE IF EXISTS `xc_task`;

CREATE TABLE `xc_task`
(
    `id`            varchar(32) NOT NULL COMMENT '任务id',
    `create_time`   datetime     DEFAULT NULL,
    `update_time`   datetime     DEFAULT NULL,
    `delete_time`   datetime     DEFAULT NULL,
    `task_type`     varchar(32)  DEFAULT NULL COMMENT '任务类型',
    `mq_exchange`   varchar(64)  DEFAULT NULL COMMENT '交换机名称',
    `mq_routingkey` varchar(64)  DEFAULT NULL COMMENT 'routingkey',
    `request_body`  varchar(512) DEFAULT NULL COMMENT '任务请求的内容',
    `version`       int(10) DEFAULT NULL COMMENT '乐观锁版本号',
    `status`        varchar(32)  DEFAULT NULL COMMENT '任务状态',
    `errormsg`      varchar(512) DEFAULT NULL COMMENT '任务错误信息',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `xc_task` */

/*Table structure for table `xc_task_his` */

DROP TABLE IF EXISTS `xc_task_his`;

CREATE TABLE `xc_task_his`
(
    `id`            varchar(32) NOT NULL COMMENT '任务id',
    `create_time`   datetime     DEFAULT NULL,
    `update_time`   datetime     DEFAULT NULL,
    `delete_time`   datetime     DEFAULT NULL,
    `task_type`     varchar(32)  DEFAULT NULL COMMENT '任务类型',
    `mq_exchange`   varchar(64)  DEFAULT NULL COMMENT '交换机名称',
    `mq_routingkey` varchar(64)  DEFAULT NULL COMMENT 'routingkey',
    `request_body`  varchar(512) DEFAULT NULL COMMENT '任务请求的内容',
    `version`       int(10) DEFAULT '0' COMMENT '乐观锁版本号',
    `status`        varchar(32)  DEFAULT NULL COMMENT '任务状态',
    `errormsg`      varchar(512) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `xc_task_his` */

insert into `xc_task_his`(`id`, `create_time`, `update_time`, `delete_time`, `task_type`, `mq_exchange`,
                          `mq_routingkey`, `request_body`, `version`, `status`, `errormsg`)
values ('10', '2018-04-04 22:58:20', '2018-07-20 12:24:10', '2018-07-16 12:24:36', NULL, 'ex_learning_addchoosecourse',
        'addchoosecourse', '{\"userId\":\"49\",\"courseId\":\"4028e581617f945f01617f9dabc40000\"}', NULL, '10201',
        NULL),
       ('11', '2018-07-16 12:28:03', '2018-07-20 12:24:10', '2018-07-16 12:29:11', NULL, 'ex_learning_addchoosecourse',
        'addchoosecourse', '{\"userId\":\"49\",\"courseId\":\"4028e581617f945f01617f9dabc40001\"}', NULL, NULL, NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
