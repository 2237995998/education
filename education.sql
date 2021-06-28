/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80021
Source Host           : localhost:3306
Source Database       : education

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2021-06-28 16:19:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for choose_course
-- ----------------------------
DROP TABLE IF EXISTS `choose_course`;
CREATE TABLE `choose_course` (
  `user_id` int NOT NULL,
  `course_id` int NOT NULL,
  `usual_grade` double(4,1) DEFAULT NULL,
  `end_grade` double(4,1) DEFAULT NULL,
  `total_grade` double(4,1) DEFAULT NULL,
  `choose_status` int NOT NULL DEFAULT '0',
  `is_pass` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`course_id`),
  KEY `fk_course` (`course_id`) USING BTREE,
  CONSTRAINT `fk_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_student` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of choose_course
-- ----------------------------
INSERT INTO `choose_course` VALUES ('1', '3', '80.0', '96.0', '89.6', '1', '是');
INSERT INTO `choose_course` VALUES ('1', '4', '85.0', '92.0', '89.2', '1', '是');
INSERT INTO `choose_course` VALUES ('1', '5', null, null, null, '0', null);

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `course_id` int NOT NULL AUTO_INCREMENT,
  `course_code` varchar(255) NOT NULL,
  `course_name` varchar(255) NOT NULL,
  `course_type` varchar(255) NOT NULL,
  `school_id` int NOT NULL,
  `teacher_id` int NOT NULL,
  `course_status` int NOT NULL DEFAULT '0',
  `course_term_id` int NOT NULL,
  `course_term` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `course_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `course_place` varchar(255) NOT NULL,
  `course_class` int NOT NULL,
  `course_credit` int NOT NULL,
  `usual_weight` double NOT NULL DEFAULT '0.4',
  `end_weight` double NOT NULL DEFAULT '0.6',
  PRIMARY KEY (`course_id`),
  KEY `fk_school` (`school_id`),
  KEY `fk_teacher` (`teacher_id`),
  KEY `fk_item` (`course_term_id`),
  CONSTRAINT `fk_item` FOREIGN KEY (`course_term_id`) REFERENCES `term` (`term_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_school` FOREIGN KEY (`school_id`) REFERENCES `school` (`school_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_teacher` FOREIGN KEY (`teacher_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('3', '10003', '数据结构', '必修', '1', '2', '1', '10', '2020-2021学年第二学期', '1-17周 周一6-7节', 'X1203', '1', '2', '0.4', '0.6');
INSERT INTO `course` VALUES ('4', '1004', 'c语言', '必修', '1', '6', '1', '10', '2020-2021学年第二学期', '1-17周 周四8-9节', 'X9306', '1', '2', '0.4', '0.6');
INSERT INTO `course` VALUES ('5', '1005', '英语', '限选', '1', '6', '1', '10', '2020-2021学年第二学期', '1-17周 周四8-9节', 'X1206', '1', '1', '0.4', '0.6');
INSERT INTO `course` VALUES ('17', '1234', '线性代数', '必修', '1', '2', '1', '10', '2020-2021学年第二学期', '1-17周 周四1-2节', 'X9605', '1', '2', '0.3', '0.7');
INSERT INTO `course` VALUES ('18', '26565', '概率论', '必修', '1', '2', '1', '10', '2020-2021学年第二学期', '1-17周 周午3-5节', 'X2132', '1', '3', '0.4', '0.6');
INSERT INTO `course` VALUES ('19', '2312', '离散数学', '必修', '1', '2', '0', '8', '2019-2020学年第二学期', '1-17周 周三1-2节', 'X3620', '1', '2', '0.3', '0.7');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `permission_id` int NOT NULL AUTO_INCREMENT,
  `permission_code` varchar(20) NOT NULL,
  `permission_name` varchar(20) NOT NULL,
  `father_id` int NOT NULL,
  `path` varchar(255) NOT NULL,
  `is_menu` int NOT NULL,
  PRIMARY KEY (`permission_id`),
  UNIQUE KEY `permission_code` (`permission_code`),
  UNIQUE KEY `permission_name` (`permission_name`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', 'course', '选课相关', '0', '/course', '1');
INSERT INTO `permission` VALUES ('2', 'course:choose', '选课', '1', '/course/choose', '1');
INSERT INTO `permission` VALUES ('3', 'course:drop', '退课', '1', '/course/drop', '0');
INSERT INTO `permission` VALUES ('4', 'course:result', '选课结果', '1', '/course/result', '1');
INSERT INTO `permission` VALUES ('5', 'course:history', '历史课程', '1', '/course/history', '1');
INSERT INTO `permission` VALUES ('6', 'grade', '成绩相关', '0', '/grade', '1');
INSERT INTO `permission` VALUES ('7', 'grade:this', '本学期成绩', '6', '/grade/this', '1');
INSERT INTO `permission` VALUES ('8', 'grade:history', '历史成绩', '6', '/grade/history', '1');
INSERT INTO `permission` VALUES ('9', 'grade_manage', '成绩管理', '0', '/grade_manage', '0');
INSERT INTO `permission` VALUES ('10', 'grade_manage:in', '录入成绩', '9', '/grade_manage/in', '0');
INSERT INTO `permission` VALUES ('11', 'course_start', '查看开课', '0', '/course_start', '1');
INSERT INTO `permission` VALUES ('12', 'course_start:history', '历史开课', '11', '/course_start/history', '1');
INSERT INTO `permission` VALUES ('13', 'course_start:this', '本学期开课', '11', '/course_start/this', '1');
INSERT INTO `permission` VALUES ('14', 'showCourseStudent', '查看课程学生名单', '11', '/showCourseStudent', '0');
INSERT INTO `permission` VALUES ('15', 'course_manage', '课程管理', '0', '/course_manage', '1');
INSERT INTO `permission` VALUES ('16', 'course_manage:add', '增加课程', '15', '/course_manage/add', '1');
INSERT INTO `permission` VALUES ('17', 'course_manage:look', '查看课程', '15', '/course_manage/look', '1');
INSERT INTO `permission` VALUES ('18', 'course_manage:delete', '删除课程', '15', '/course_manage/delete', '0');
INSERT INTO `permission` VALUES ('19', 'course_manage:update', '修改课程', '15', '/course_manage/update', '0');
INSERT INTO `permission` VALUES ('20', 'term', '学期管理', '0', '/term', '1');
INSERT INTO `permission` VALUES ('21', 'term:set', '设置当前学期', '20', '/term/set', '1');
INSERT INTO `permission` VALUES ('22', 'choose_manage', '选课管理', '0', '/choose_manage', '1');
INSERT INTO `permission` VALUES ('23', 'choose_manage:open', '开放选课', '22', '/choose_manage/open', '1');
INSERT INTO `permission` VALUES ('24', 'choose_manage:close', '关闭选课', '22', '/choose_manage/close', '0');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '学生');
INSERT INTO `role` VALUES ('3', '教务');
INSERT INTO `role` VALUES ('2', '教师');
INSERT INTO `role` VALUES ('4', '系统管理员');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `role_id` int NOT NULL,
  `permission_id` int NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `fk_permission` (`permission_id`),
  CONSTRAINT `fk_permission` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`permission_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_role_p` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1');
INSERT INTO `role_permission` VALUES ('1', '2');
INSERT INTO `role_permission` VALUES ('1', '3');
INSERT INTO `role_permission` VALUES ('1', '4');
INSERT INTO `role_permission` VALUES ('1', '5');
INSERT INTO `role_permission` VALUES ('1', '6');
INSERT INTO `role_permission` VALUES ('1', '7');
INSERT INTO `role_permission` VALUES ('1', '8');
INSERT INTO `role_permission` VALUES ('2', '9');
INSERT INTO `role_permission` VALUES ('2', '10');
INSERT INTO `role_permission` VALUES ('2', '11');
INSERT INTO `role_permission` VALUES ('2', '12');
INSERT INTO `role_permission` VALUES ('2', '13');
INSERT INTO `role_permission` VALUES ('2', '14');
INSERT INTO `role_permission` VALUES ('3', '14');
INSERT INTO `role_permission` VALUES ('3', '15');
INSERT INTO `role_permission` VALUES ('3', '16');
INSERT INTO `role_permission` VALUES ('3', '17');
INSERT INTO `role_permission` VALUES ('3', '18');
INSERT INTO `role_permission` VALUES ('3', '19');
INSERT INTO `role_permission` VALUES ('4', '20');
INSERT INTO `role_permission` VALUES ('4', '21');
INSERT INTO `role_permission` VALUES ('4', '22');
INSERT INTO `role_permission` VALUES ('4', '23');
INSERT INTO `role_permission` VALUES ('4', '24');

-- ----------------------------
-- Table structure for school
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school` (
  `school_id` int NOT NULL AUTO_INCREMENT,
  `school_name` varchar(255) NOT NULL,
  PRIMARY KEY (`school_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of school
-- ----------------------------
INSERT INTO `school` VALUES ('1', '计算机学院');

-- ----------------------------
-- Table structure for term
-- ----------------------------
DROP TABLE IF EXISTS `term`;
CREATE TABLE `term` (
  `term_id` int NOT NULL AUTO_INCREMENT,
  `term_name` varchar(255) NOT NULL,
  `term_status` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`term_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of term
-- ----------------------------
INSERT INTO `term` VALUES ('1', '2016-2017学年第一学期', '0');
INSERT INTO `term` VALUES ('2', '2016-2017学年第二学期', '0');
INSERT INTO `term` VALUES ('3', '2017-2018学年第一学期', '0');
INSERT INTO `term` VALUES ('4', '2017-2018学年第二学期', '0');
INSERT INTO `term` VALUES ('5', '2018-2019学年第一学期', '0');
INSERT INTO `term` VALUES ('6', '2018-2019学年第二学期', '0');
INSERT INTO `term` VALUES ('7', '2019-2020学年第一学期', '0');
INSERT INTO `term` VALUES ('8', '2019-2020学年第二学期', '0');
INSERT INTO `term` VALUES ('9', '2020-2021学年第一学期', '0');
INSERT INTO `term` VALUES ('10', '2020-2021学年第二学期', '1');
INSERT INTO `term` VALUES ('11', '2021-2022学年第一学期', '0');
INSERT INTO `term` VALUES ('12', '2021-2022学年第二学期', '0');
INSERT INTO `term` VALUES ('13', '2022-2023学年第一学期', '0');
INSERT INTO `term` VALUES ('14', '2022-2023学年第二学期', '0');
INSERT INTO `term` VALUES ('15', '2023-2024学年第一学期', '0');
INSERT INTO `term` VALUES ('16', '2023-2024学年第二学期', '0');
INSERT INTO `term` VALUES ('17', '2024-2025学年第一学期', '0');
INSERT INTO `term` VALUES ('18', '2024-2025学年第二学期', '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(255) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `school_id` int NOT NULL,
  `sex` varchar(255) NOT NULL,
  `tel` varchar(100) NOT NULL,
  `student_class` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`) USING BTREE,
  KEY `fk_sch` (`school_id`),
  CONSTRAINT `fk_sch` FOREIGN KEY (`school_id`) REFERENCES `school` (`school_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '20201001', '4d3d7008836ee74feebc32a43ed24181', 'wcwad12', '张三', '1', '男', '12135', '计算机2018-01班');
INSERT INTO `user` VALUES ('2', '20201002', '4d3d7008836ee74feebc32a43ed24181', 'wcwad12', '李四', '1', '女', '215454', null);
INSERT INTO `user` VALUES ('3', '20201003', '4d3d7008836ee74feebc32a43ed24181', 'wcwad12', '王五', '1', '男', '1548', null);
INSERT INTO `user` VALUES ('4', '20201004', '4d3d7008836ee74feebc32a43ed24181', 'wcwad12', '管理员', '1', '男', '1558', null);
INSERT INTO `user` VALUES ('5', '20201005', '4d3d7008836ee74feebc32a43ed24181', 'wcwad12', '丽丽', '1', '女', '2166', '计算机2018-02班');
INSERT INTO `user` VALUES ('6', '20201006', '4d3d7008836ee74feebc32a43ed24181', 'wcwad12', '呜呜', '1', '男', '1158', null);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_role` (`role_id`),
  CONSTRAINT `fk_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1');
INSERT INTO `user_role` VALUES ('5', '1');
INSERT INTO `user_role` VALUES ('2', '2');
INSERT INTO `user_role` VALUES ('6', '2');
INSERT INTO `user_role` VALUES ('3', '3');
INSERT INTO `user_role` VALUES ('4', '4');
DROP TRIGGER IF EXISTS `tri_choose_course_update`;
DELIMITER ;;
CREATE TRIGGER `tri_choose_course_update` BEFORE UPDATE ON `choose_course` FOR EACH ROW BEGIN
	DECLARE endWe DOUBLE;
	DECLARE usualWe DOUBLE;
	SET endWe = (SELECT end_weight FROM course WHERE course_id = new.course_id);
	SET usualWe = (SELECT usual_weight FROM course WHERE course_id = new.course_id);
	SET new.total_grade = new.usual_grade*usualWe + new.end_grade*endWe;
	IF (new.total_grade>=60) THEN
		SET new.is_pass = '是' ;
	END IF;
	IF (new.total_grade<60)  THEN
		SET new.is_pass = '否' ;
	END IF;
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `tri_course_insert`;
DELIMITER ;;
CREATE TRIGGER `tri_course_insert` BEFORE INSERT ON `course` FOR EACH ROW BEGIN
	declare c int;
	declare b VARCHAR(255);
	SET c = NEW.course_term_id; 
	SET b = (SELECT term_name FROM term WHERE term_id = c);
  SET new.course_term = b;
	IF((SELECT term_status FROM term WHERE term_id = c)=1) THEN
		SET NEW.course_status = 1;
	END IF;
END
;;
DELIMITER ;
