SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS tb_charge;
DROP TABLE IF EXISTS tb_complaint;
DROP TABLE IF EXISTS tb_hire;
DROP TABLE IF EXISTS tb_owner;
DROP TABLE IF EXISTS tb_house;
DROP TABLE IF EXISTS tb_parkingspace;
DROP TABLE IF EXISTS tb_repair;




/* Create Tables */

-- 收费表
CREATE TABLE tb_charge
(
	charge_id bigint NOT NULL AUTO_INCREMENT COMMENT '收费编号',
	house_id bigint COMMENT '收费房间号',
	charge_mouth date COMMENT '收费年月份',
	water_charge double COMMENT '水费',
	electric_charge double COMMENT '电费',
	gas_charge double COMMENT '燃气费',
	isPay int COMMENT '是否缴费，0未缴费，1已缴费',
	pay_date date COMMENT '缴费日期',
	PRIMARY KEY (charge_id)
) COMMENT = '收费表';


-- 投诉信息表
CREATE TABLE tb_complaint
(
	complain_id bigint NOT NULL COMMENT '投诉编号',
	complain_people varchar(20) COMMENT '投诉人',
	complain_phone int COMMENT '投诉人电话',
	complain_desc varchar(500) COMMENT '投诉描述',
	complain_date date COMMENT '投诉日期',
	complain_rname varchar(20) COMMENT '投诉受理人姓名',
	deal_desc varchar(500) COMMENT '处理描述',
	deal_status int COMMENT '处理状态，0未处理，1已处理',
	PRIMARY KEY (complain_id)
) COMMENT = '投诉信息表';


-- 车位出租信息表
CREATE TABLE tb_hire
(
	hire_id bigint NOT NULL AUTO_INCREMENT COMMENT '出租编号',
	park_id bigint COMMENT '车位号',
	hire_name varchar(50) COMMENT '租借人姓名',
	hire_house_no varchar(50) COMMENT '租借人房间号',
	hire_phone int COMMENT '租借人手机号',
	hire_charge int COMMENT '租金，元/年',
	hire_start_date date COMMENT '租借开始日期',
	hire_date int COMMENT '租借年限，年',
	PRIMARY KEY (hire_id)
) COMMENT = '车位出租信息表';


-- 房屋表
CREATE TABLE tb_house
(
	house_id bigint NOT NULL AUTO_INCREMENT COMMENT '房屋编号',
	house_no varchar(50) COMMENT '房间号，如1#101',
	house_shape varchar(50) COMMENT '房屋户型',
	house_floor_id varchar(50) COMMENT '房屋楼栋编号',
	house_cell_id varchar(50) COMMENT '房屋单元编号',
	house_area double COMMENT '房屋面积',
	own_id bigint COMMENT '户主编号',
	PRIMARY KEY (house_id)
) COMMENT = '房屋表';


-- 户主表
CREATE TABLE tb_owner
(
	own_id bigint NOT NULL AUTO_INCREMENT COMMENT '户主 编号',
	owner_name varchar(50) COMMENT '户主名',
	owner_gender int COMMENT '性别，0为男，1为女',
	owner_phone int(11) COMMENT '户主电话',
	owner_identityid varchar(30) COMMENT '户主身份证',
	register_time datetime COMMENT '入住时间',
	PRIMARY KEY (own_id)
) COMMENT = '户主表';


-- 车位信息表
CREATE TABLE tb_parkingspace
(
	park_id bigint NOT NULL AUTO_INCREMENT COMMENT '车位号',
	park_location varchar(200) COMMENT '车位位置',
	park_length double COMMENT '车位长度',
	park_width double COMMENT '车位宽度',
	park_charge int COMMENT '车位费用',
	park_status int COMMENT '车位状态，0已出租，1未出租',
	PRIMARY KEY (park_id)
) COMMENT = '车位信息表';


-- 报修信息表
CREATE TABLE tb_repair
(
	repair_id bigint NOT NULL COMMENT '报修编号',
	house_id bigint COMMENT '报修房屋号',
	repair_type int COMMENT '报修类别，0水，1电，2燃气，3其他',
	repair_desc varchar(500) COMMENT '报修描述',
	repair_time datetime COMMENT '报修时间，now',
	repair_status int COMMENT '处理状态，0未处理，1为已安排处理，2处理完成',
	repair_arrage_time datetime COMMENT '安排维修时间',
	repair_people varchar(20) COMMENT '维修人',
	material_charge double COMMENT '材料费',
	repair_charge double COMMENT '维修费',
	complete_time datetime COMMENT '完成维修时间',
	isPay int COMMENT '是否交完费，0是，1没有',
	pay_date datetime COMMENT '缴费日期',
	PRIMARY KEY (repair_id)
) COMMENT = '报修信息表';



/* Create Foreign Keys */

ALTER TABLE tb_owner
	ADD FOREIGN KEY (own_id)
	REFERENCES tb_house (house_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



