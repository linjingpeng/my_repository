SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS tb_student;
DROP TABLE IF EXISTS tb_university_major;
DROP TABLE IF EXISTS tb_major;
DROP TABLE IF EXISTS tb_university;




/* Create Tables */

-- 专业表
CREATE TABLE tb_major
(
	major_id bigint NOT NULL AUTO_INCREMENT COMMENT '专业编号',
	major_name varchar(50) COMMENT '专业名称',
	PRIMARY KEY (major_id)
) COMMENT = '专业表';


-- 学生表
CREATE TABLE tb_student
(
	student_id bigint NOT NULL AUTO_INCREMENT COMMENT '学生编号',
	student_name varchar(20) COMMENT '学生名',
	major_id bigint NOT NULL COMMENT '专业编号',
	university_id bigint NOT NULL COMMENT '大学编号',
	PRIMARY KEY (student_id)
) COMMENT = '学生表';


-- 大学表
CREATE TABLE tb_university
(
	university_id bigint NOT NULL AUTO_INCREMENT COMMENT '大学编号',
	university_name varchar(50) COMMENT '大学名称',
	PRIMARY KEY (university_id)
) COMMENT = '大学表';


-- 大学专业关联表
CREATE TABLE tb_university_major
(
	university_id bigint NOT NULL COMMENT '大学编号',
	major_id bigint NOT NULL COMMENT '专业编号'
) COMMENT = '大学专业关联表';



/* Create Foreign Keys */

ALTER TABLE tb_student
	ADD FOREIGN KEY (major_id)
	REFERENCES tb_major (major_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE tb_university_major
	ADD FOREIGN KEY (major_id)
	REFERENCES tb_major (major_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE tb_student
	ADD FOREIGN KEY (university_id)
	REFERENCES tb_university (university_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE tb_university_major
	ADD FOREIGN KEY (university_id)
	REFERENCES tb_university (university_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



